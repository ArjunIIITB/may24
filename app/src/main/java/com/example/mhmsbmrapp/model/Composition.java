package com.example.mhmsbmrapp.model;

public class Composition {


    private String name;
    private String templateId;
    private String compositionUid;
    private String serviceRequest;

    public Composition( String name, String templateId, String compositionUid) {
        this.name = name;
        this.templateId = templateId;
        this.compositionUid = compositionUid;

        System.out.println("constructor called with "+compositionUid);
    }


    public Composition( String name, String templateId, String serviceRequest, String compositionUid) {
        this.name = name;
        this.templateId = templateId;
        this.serviceRequest = serviceRequest;
        this.compositionUid = compositionUid;
    }

    public String getName() {
        return name;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getCompositionUid() {
        return compositionUid;
    }

    public String getServiceRequest() { return serviceRequest; }

    @Override
    public String toString() {
        return "Composition{" +
                "name='" + name + '\'' +
                ", templateId='" + templateId + '\'' +
                ", compositionUid='" + compositionUid + '\'' +
                ", serviceRequest='" + serviceRequest + '\'' +
                '}';
    }
}
