package com.assets.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "assets_register")
public class AssetsRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assetId;

    @Column(name = "issued_on")
    private Date issuedOn;

    @Column(name = "issued_to_employee", length = 6)
    private String issuedToEmployee;

    @Column(name = "model_no",length = 25)
    private String modelNo;

    @Column(name = "make", length = 25)
    private String make;

    @Column(name = "asset_type", length = 10)
    private String assetType;

    @OneToMany(mappedBy = "assetsRegister")
    private Set<SupportTickets> tickets;

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

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
