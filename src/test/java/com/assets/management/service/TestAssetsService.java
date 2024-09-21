package com.assets.management.service;

import com.assets.management.dto.*;
import com.assets.management.entity.AssetsRegister;
import com.assets.management.entity.SupportTickets;
import com.assets.management.repository.AssetsRegisterRepository;
import com.assets.management.repository.SupportTicketsRepository;
import com.assets.management.repository.TicketResolutionsRepository;
import com.assets.management.service.implementation.AssetsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestAssetsService.class)
public class TestAssetsService {
    @Mock
    AssetsRegisterRepository assetsRegisterRepository;
    @Mock
    SupportTicketsRepository supportTicketsRepository;
    @Mock
    TicketResolutionsRepository ticketResolutionsRepository;

    @Mock
    AssetsService assetsService;

    @Test
    public void testRegisterAssets_Success() {
        AssetsDetailsDTO assetsDetailsDTO = new AssetsDetailsDTO();
        assetsDetailsDTO.setIssuedToEmployee("John Doe");
        assetsDetailsDTO.setAssetType("Laptop");
        assetsDetailsDTO.setMake("Dell");
        assetsDetailsDTO.setModelNo("XPS 15");
        assetsDetailsDTO.setIssuedOn(new Date());

        when(assetsRegisterRepository.countByIssuedToEmployee("John Doe")).thenReturn(0L);

        when(assetsRegisterRepository.save(any(AssetsRegister.class))).thenAnswer(invocation -> invocation.getArgument(0));
        AssetAPIResponseDTO apiResponseDTO = new AssetAPIResponseDTO();
        apiResponseDTO.setStatusCode(200);
        apiResponseDTO.setMessage("Asset registered successfully!");
        apiResponseDTO.setAssetId(1);
        when(assetsService.registerAssets(assetsDetailsDTO)).thenReturn(apiResponseDTO);
        assertEquals(200, apiResponseDTO.getStatusCode());
    }

    @Test
    public void testGetTicketDetails_Success() {
        Integer ticketId = 123;
        SupportTickets supportTickets = new SupportTickets();
        supportTickets.setTicketId(ticketId);
        supportTickets.setAssetsRegister(new AssetsRegister());
        supportTickets.setExpectedResolution(new Date());
        supportTickets.setTicketStatus("Open");
        supportTickets.setTicketRaisedByEmployee("12");

        when(supportTicketsRepository.findById(ticketId)).thenReturn(Optional.of(supportTickets));
        SupportTicketDetailsDTO detailsDTO = new SupportTicketDetailsDTO();
        detailsDTO.setAssignedToEmployee("20");
        detailsDTO.setTicketRaisedOn(new Date());
        detailsDTO.setTicketId(123);
        detailsDTO.setTicketStatus("Open");
        detailsDTO.setTicketRaisedByEmployee("11");
        when(assetsService.getTicketDetails(ticketId)).thenReturn(detailsDTO);
        assertEquals(ticketId, detailsDTO.getTicketId());
        assertEquals("Open", detailsDTO.getTicketStatus());
    }

    @Test
    public void testGetTicketDetails_InvalidTicketId() {
        Integer ticketId = 123;
        when(supportTicketsRepository.findById(ticketId)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetSupportTickets_Success() {
        String executiveId = "22";
        SupportTickets ticket1 = new SupportTickets();
        ticket1.setTicketId(1);
        ticket1.setTicketStatus("Open");
        ticket1.setTicketRaisedOn(new Date());

        SupportTickets ticket2 = new SupportTickets();
        ticket2.setTicketId(2);
        ticket2.setTicketStatus("Open");
        ticket2.setTicketRaisedOn(new Date());

        List<SupportTickets> ticketsList = List.of(ticket1, ticket2);

        when(supportTicketsRepository.findByAssignedToEmployeeAndTicketStatus(executiveId, "Open"))
                .thenReturn(ticketsList);

        assertNotNull(ticketsList);
    }

    @Test
    public void testGetSupportTickets_NoRecordsFound() {
        String executiveId = "123";
        when(supportTicketsRepository.findByAssignedToEmployeeAndTicketStatus(executiveId, "Open"))
                .thenReturn(Collections.emptyList());
    }

    @Test
    public void testGetTicketDetails_ValidTicketId() {
        Integer ticketId = 123;
        SupportTickets supportTicket = new SupportTickets();
        supportTicket.setTicketId(ticketId);
        when(supportTicketsRepository.findById(ticketId)).thenReturn(Optional.of(supportTicket));

        SupportTicketDetailsDTO detailsDTO = new SupportTicketDetailsDTO();
        detailsDTO.setTicketId(123);
        detailsDTO.setExpectedResolution(new Date());
        detailsDTO.setTicketRaisedOn(new Date());
        detailsDTO.setAssignedToEmployee("12");
        detailsDTO.setTicketRaisedByEmployee("22");
        when(assetsService.getTicketDetails(ticketId)).thenReturn(detailsDTO);
        assertNotNull(detailsDTO);
        assertEquals(ticketId, detailsDTO.getTicketId());
    }



    @Test
    public void testUpdateResolutionToTicket_ValidTicketId() {
        Integer ticketId = 123;
        RequestResolutionDTO requestResolutionDTO = new RequestResolutionDTO();
        requestResolutionDTO.setResolution("Resolution description");

        SupportTickets supportTicket = new SupportTickets();
        supportTicket.setTicketId(ticketId);

        when(supportTicketsRepository.findById(ticketId)).thenReturn(Optional.of(supportTicket));
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setMessage("Resolution Updated successfully");
        apiResponseDTO.setStatusCode(200);
        when(assetsService.updateResolutionToTicket(requestResolutionDTO, ticketId)).thenReturn(apiResponseDTO);

        assertNotNull(apiResponseDTO);
        assertEquals(200, apiResponseDTO.getStatusCode());
        assertEquals("Resolution Updated successfully", apiResponseDTO.getMessage());
    }

}
