package com.assets.management.dto;

import java.util.Date;


public class AssetsDetailsDTO {
    private Date issuedOn;
    private String issuedToEmployee;
    private String modelNo;
    private String make;
    private String assetType;

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getIssuedToEmployee() {
        return issuedToEmployee;
    }

    public void setIssuedToEmployee(String issuedToEmployee) {
        this.issuedToEmployee = issuedToEmployee;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
