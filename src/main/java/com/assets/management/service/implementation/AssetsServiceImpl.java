package com.assets.management.service.implementation;

import com.assets.management.dto.*;
import com.assets.management.entity.AssetsRegister;
import com.assets.management.entity.SupportTickets;
import com.assets.management.entity.TicketResolutions;
import com.assets.management.exceptions.AssetNotRegistered;
import com.assets.management.exceptions.MaximumDeviceLimitReached;
import com.assets.management.exceptions.ValidationsException;
import com.assets.management.repository.AssetsRegisterRepository;
import com.assets.management.repository.SupportTicketsRepository;
import com.assets.management.repository.TicketResolutionsRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Autowired
    AssetsRegisterRepository assetsRegisterRepository;

    @Autowired
    SupportTicketsRepository supportTicketsRepository;

    @Autowired
    TicketResolutionsRepository ticketResolutionsRepository;

    @Override
    public AssetAPIResponseDTO registerAssets(AssetsDetailsDTO assetsDetailsDTO) {
        long assetRegisterCount = assetsRegisterRepository.countByIssuedToEmployee(assetsDetailsDTO.getIssuedToEmployee());
        if(assetRegisterCount >= 5){
            throw new MaximumDeviceLimitReached("Maximum device limit reached!");
        }
        AssetsRegister assetsRegister = new AssetsRegister();
        assetsRegister.setAssetType(assetsDetailsDTO.getAssetType());
        assetsRegister.setMake(assetsDetailsDTO.getMake());
        assetsRegister.setModelNo(assetsDetailsDTO.getModelNo());
        assetsRegister.setIssuedToEmployee(assetsDetailsDTO.getIssuedToEmployee());
        //code to check issue date should not be future date
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
        Locale.ENGLISH);
        Date date1;
        try {
             date1 = sdf.parse(String.valueOf(assetsDetailsDTO.getIssuedOn()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if(date1.before(new Date())) {
            assetsRegister.setIssuedOn(date1);
        }else if (date1.equals(new Date())){
            assetsRegister.setIssuedOn(date1);
        }else{
            throw new ValidationException("issued date should not be future date");
        }
        AssetsRegister assetsRegister1 = assetsRegisterRepository.save(assetsRegister);
        return new AssetAPIResponseDTO(200, "Asset registered successfully!",assetsRegister.getAssetId());
    }

    @Override
    public APISupportResponseDTO createSupportRequest(SupportTicketDTO supportTicketDTO) {
        SupportTickets supportTickets = new SupportTickets();
        Optional<AssetsRegister> assetsRegisterOptional = assetsRegisterRepository.findById(supportTicketDTO.getAssetId());
        if(assetsRegisterOptional.isEmpty()){
            throw new AssetNotRegistered("AssetNotRegistered Invalid asset ID");
        }
        AssetsRegister assetsRegister = assetsRegisterOptional.get();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        Date expectedResolutionDate = new Date();
        if(Objects.equals(assetsRegister.getAssetType(), "Laptop")){
            c.add(Calendar.DATE, 2); // Adding 2 days
            expectedResolutionDate = c.getTime();
            supportTickets.setExpectedResolution(c.getTime());
        }else if(Objects.equals(assetsRegister.getAssetType(), "Mobile")){
            c.add(Calendar.DATE, 5); // Adding 5 days
            expectedResolutionDate = c.getTime();
        }else if(Objects.equals(assetsRegister.getAssetType(), "DataCard") || Objects.equals(assetsRegister.getAssetType(), "HeadPhone") || Objects.equals(assetsRegister.getAssetType(), "Storage")){
            c.add(Calendar.DATE, 3); // Adding 3 days
            expectedResolutionDate = c.getTime();
        }
        supportTickets.setExpectedResolution(expectedResolutionDate);
        supportTickets.setTicketRaisedOn(new Date());
        supportTickets.setAssignedToEmployee(supportTicketDTO.getAssignedToEmployee());
        supportTickets.setAssetsRegister(assetsRegister);
        supportTickets.setTicketStatus("Open");
        supportTickets.setTicketRaisedByEmployee(supportTicketDTO.getTicketRaisedByEmployee());
        supportTicketsRepository.save(supportTickets);
        return new APISupportResponseDTO(200,"Ticket created successfully!", supportTickets.getTicketId());
    }

    @Override
    public List<SupportTicketDTOResponse> getSupportTickets(String executiveId) {
        List<SupportTickets> supportTicketsList = supportTicketsRepository.findByAssignedToEmployeeAndTicketStatus(executiveId,"Open");
        if(supportTicketsList.isEmpty()){
            throw new ValidationsException("No records found for executive :"+ executiveId);
        }
        List<SupportTicketDTOResponse> supportTicketDTOList = new ArrayList<>();
        supportTicketsList.forEach(data -> {
            SupportTicketDTOResponse supportTicketDTO = new SupportTicketDTOResponse();
            supportTicketDTO.setTicketStatus(data.getTicketStatus());
            supportTicketDTO.setTicketRaisedOn(data.getTicketRaisedOn());
            supportTicketDTO.setAssetId(data.getAssetsRegister());
            supportTicketDTO.setExpectedResolution(data.getExpectedResolution());
            supportTicketDTO.setTicketRaisedByEmployee(data.getTicketRaisedByEmployee());
            supportTicketDTO.setTicketId(data.getTicketId());
            supportTicketDTO.setAssignedToEmployee(data.getAssignedToEmployee());
            supportTicketDTOList.add(supportTicketDTO);
        });
        return supportTicketDTOList;
    }

    @Override
    public SupportTicketDetailsDTO getTicketDetails(Integer ticketId) {
        Optional<SupportTickets> supportTickets = supportTicketsRepository.findById(ticketId);
        if(supportTickets.isEmpty()){
            throw new ValidationsException("Invalid ticket Id");
        }
        SupportTickets data = supportTickets.get();
        SupportTicketDetailsDTO supportTicketDetailsDTO = new SupportTicketDetailsDTO();
        supportTicketDetailsDTO.setTicketId(data.getTicketId());
        supportTicketDetailsDTO.setAssetsRegister(data.getAssetsRegister());
        supportTicketDetailsDTO.setExpectedResolution(data.getExpectedResolution());
        supportTicketDetailsDTO.setTicketStatus(data.getTicketStatus());
        supportTicketDetailsDTO.setTicketRaisedByEmployee(data.getTicketRaisedByEmployee());
        supportTicketDetailsDTO.setTicketRaisedOn(data.getTicketRaisedOn());
        supportTicketDetailsDTO.setAssignedToEmployee(data.getAssignedToEmployee());
        return supportTicketDetailsDTO;
    }

    @Override
    public APIResponseDTO updateResolutionToTicket(RequestResolutionDTO requestResolutionDTO, Integer ticketId) {
        Optional<SupportTickets> supportTickets = supportTicketsRepository.findById(ticketId);
        if(supportTickets.isEmpty()){
            throw new ValidationsException("Invalid ticket Id");
        }
        TicketResolutions ticketResolutions = new TicketResolutions();
        ticketResolutions.setTicketId(supportTickets.get());
        ticketResolutions.setResolutionDate(new Date());
        ticketResolutions.setResolutionDescription(requestResolutionDTO.getResolution());
        ticketResolutionsRepository.save(ticketResolutions);
        return new APIResponseDTO(200,"Resolution Updated successfully");
    }

    @Override
    public APIResponseDTO updateTicketStatus(RequestUpdateStatus requestUpdateStatus, Integer ticketId) {
        Optional<SupportTickets> supportTickets = supportTicketsRepository.findById(ticketId);
        if(supportTickets.isEmpty()){
            throw new ValidationsException("Invalid ticket Id");
        }
        supportTickets.get().setTicketStatus("Resolved");
        supportTicketsRepository.save(supportTickets.get());
        return new APIResponseDTO(200,"Ticket Status Updated successfully");
    }

}
