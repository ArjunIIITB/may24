package com.example.mhmsbmrapp.model;

public class MHP {

    private String assignedMhpId;
    private String assignedmhpName;
    private String orgId;
    private String userId;

    public MHP(String assignedMhpId, String assignedmhpName, String orgId, String userId) {
        this.assignedMhpId = assignedMhpId;
        this.assignedmhpName = assignedmhpName;
        this.orgId = orgId;
        this.userId = userId;
    }

    public String getAssignedMhpId() {
        return assignedMhpId;
    }

    public String getAssignedmhpName() {
        return assignedmhpName;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return "MHP{" +
                "assignedMhpId='" + assignedMhpId + '\'' +
                ", assignedmhpName='" + assignedmhpName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
