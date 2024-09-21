package com.assets.management.service.implementation;

import com.assets.management.dto.*;
import com.assets.management.entity.AssetsRegister;

import java.util.List;

public interface AssetsService {
    AssetAPIResponseDTO registerAssets(AssetsDetailsDTO assetsDetailsDTO);

    APISupportResponseDTO createSupportRequest(SupportTicketDTO supportTicketDTO);

    List<SupportTicketDTOResponse> getSupportTickets(String executiveId);

    SupportTicketDetailsDTO getTicketDetails(Integer ticketId);

    APIResponseDTO updateResolutionToTicket(RequestResolutionDTO requestResolutionDTO, Integer ticketId);

    APIResponseDTO updateTicketStatus(RequestUpdateStatus requestUpdateStatus, Integer ticketId);
}
