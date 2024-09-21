package com.assets.management.controller;

import com.assets.management.dto.*;
import com.assets.management.service.implementation.AssetsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssetsController {

    @Autowired
    AssetsService assetsService;

    @Operation(summary = "API to register the Asset")
    @PostMapping("/asset/register")
    public ResponseEntity<AssetAPIResponseDTO> saveAsset(@RequestBody AssetsDetailsDTO assetsDetailsDTO){
        AssetAPIResponseDTO response = assetsService.registerAssets(assetsDetailsDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "API to create a support ticket")
    @PostMapping(value = "/supportrequests/new",consumes = "application/json")
    public ResponseEntity<APISupportResponseDTO> saveSupportRequest(@RequestBody SupportTicketDTO supportTicketDTO){
        APISupportResponseDTO responseDTO = assetsService.createSupportRequest(supportTicketDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "API to fetch all open tickets for support executive")
    @PostMapping("/supportrequests/{executiveId}")
    public ResponseEntity<List<SupportTicketDTOResponse>> fetchSupportTickets(@PathVariable("executiveId") String executiveId){
        List<SupportTicketDTOResponse> supportTicketDTO = assetsService.getSupportTickets(executiveId);
        return new ResponseEntity<>(supportTicketDTO, HttpStatus.OK);
    }

    @Operation(summary = "API to fetch ticket details by ticket Id")
    @GetMapping(value = "/supportrequests/{ticketId}", produces = "application/json")
    public ResponseEntity<SupportTicketDetailsDTO> fetchTicketDetails(@PathVariable("ticketId") Integer ticketId){
        SupportTicketDetailsDTO supportTicketDTO = assetsService.getTicketDetails(ticketId);
        return new ResponseEntity<>(supportTicketDTO, HttpStatus.OK);
    }

    @Operation(summary = "API to update the resolution based on ticketId")
    @PutMapping("/supportrequests/{ticketId}/resolve")
    public ResponseEntity<APIResponseDTO> putResolutionToTicket(@RequestBody RequestResolutionDTO requestResolutionDTO, @PathVariable("ticketId") Integer ticketId){
        APIResponseDTO apiResponseDTO = assetsService.updateResolutionToTicket(requestResolutionDTO,ticketId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "API to update the ticket status based on ticketId")
    @PutMapping("/supportrequests/update-status/{ticketId}")
    public ResponseEntity<APIResponseDTO> updateSupportTicket(@RequestBody RequestUpdateStatus requestUpdateStatus, @PathVariable("ticketId") Integer ticketId){
        APIResponseDTO apiResponseDTO = assetsService.updateTicketStatus(requestUpdateStatus,ticketId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

}
