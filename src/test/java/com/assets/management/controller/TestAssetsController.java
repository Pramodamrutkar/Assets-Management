package com.assets.management.controller;

import com.assets.management.dto.*;
import com.assets.management.service.implementation.AssetsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(AssetsController.class)
public class TestAssetsController {

    @MockBean
    private AssetsService assetsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveAsset_success() throws Exception {
        AssetsDetailsDTO inputDTO = new AssetsDetailsDTO();
        inputDTO.setAssetType("Mobile");
        inputDTO.setMake("Lenovo");
        inputDTO.setModelNo("ABC-001");
        inputDTO.setIssuedToEmployee("11");
        inputDTO.setIssuedOn(new Date());
        AssetAPIResponseDTO mockResponse = new AssetAPIResponseDTO(200,"Asset registered successfully",1);
        when(assetsService.registerAssets(Mockito.any(AssetsDetailsDTO.class))).thenReturn(mockResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/asset/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Asset registered successfully"));
    }

    @Test
    public void testSaveSupportRequest_Success() throws Exception {
        SupportTicketDTO supportTicketDTO = new SupportTicketDTO();
        supportTicketDTO.setAssetId(1);
        supportTicketDTO.setTicketRaisedOn(new Date());
        supportTicketDTO.setAssignedToEmployee("12");
        supportTicketDTO.setTicketRaisedByEmployee("10");

        APISupportResponseDTO mockResponse = new APISupportResponseDTO(200,"Ticket created successfully", 1);
        when(assetsService.createSupportRequest(supportTicketDTO)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/supportrequests/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(supportTicketDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFetchSupportTickets_Success() throws Exception {
        String executiveId = "1";
        List<SupportTicketDTOResponse> mockResponse = Collections.singletonList(new SupportTicketDTOResponse());
        when(assetsService.getSupportTickets(executiveId)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/supportrequests/{executiveId}", executiveId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFetchTicketDetails_Success() throws Exception {
        Integer ticketId = 123;
        SupportTicketDetailsDTO mockResponse = new SupportTicketDetailsDTO();
        when(assetsService.getTicketDetails(ticketId)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/supportrequests/{ticketId}", ticketId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPutResolutionToTicket_Success() throws Exception {
        Integer ticketId = 123;
        RequestResolutionDTO requestResolutionDTO = new RequestResolutionDTO();
        requestResolutionDTO.setResolution("ticket resolved!");

        APIResponseDTO mockResponse = new APIResponseDTO(200,"Resolution updated successfully");
        when(assetsService.updateResolutionToTicket(requestResolutionDTO, ticketId)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/supportrequests/{ticketId}/resolve", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestResolutionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateSupportTicket() throws Exception {
        Integer ticketId = 123;
        RequestUpdateStatus requestUpdateStatus = new RequestUpdateStatus();
        requestUpdateStatus.setTicketStatus("Resolved");
        APIResponseDTO expectedResponse = new APIResponseDTO(200, "Status updated successfully");

        when(assetsService.updateTicketStatus(requestUpdateStatus, ticketId)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/supportrequests/update-status/{ticketId}", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestUpdateStatus)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
