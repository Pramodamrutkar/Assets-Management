package com.assets.management.dto;


public class AssetAPIResponseDTO {
    public Integer statusCode;
    public String message;
    public Integer assetId;

    public AssetAPIResponseDTO() {

    }
    public AssetAPIResponseDTO(Integer statusCode, String message, Integer assetId) {
        this.statusCode = statusCode;
        this.message = message;
        this.assetId = assetId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }
}
