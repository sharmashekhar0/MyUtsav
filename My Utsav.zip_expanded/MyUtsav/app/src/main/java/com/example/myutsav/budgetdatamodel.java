package com.example.myutsav;

public class budgetdatamodel {
    String strtotal,strgift,strdecoration,strcatering,strcarbus,strother,strcamping,streventname;



    public budgetdatamodel(String streventname,String strtotal, String strgift, String strdecoration, String strcatering, String strcarbus, String strother, String strcamping) {
        this.streventname = streventname;
        this.strtotal = strtotal;
        this.strgift = strgift;
        this.strdecoration = strdecoration;
        this.strcatering = strcatering;
        this.strcarbus = strcarbus;
        this.strother = strother;
        this.strcamping = strcamping;
    }
    public String getStreventname() {
        return streventname;
    }

    public void setStreventname(String streventname) {
        this.streventname = streventname;
    }
    public String getStrtotal() {
        return strtotal;
    }

    public void setStrtotal(String strtotal) {
        this.strtotal = strtotal;
    }

    public String getStrgift() {
        return strgift;
    }

    public void setStrgift(String strgift) {
        this.strgift = strgift;
    }

    public String getStrdecoration() {
        return strdecoration;
    }

    public void setStrdecoration(String strdecoration) {
        this.strdecoration = strdecoration;
    }

    public String getStrcatering() {
        return strcatering;
    }

    public void setStrcatering(String strcatering) {
        this.strcatering = strcatering;
    }

    public String getStrcarbus() {
        return strcarbus;
    }

    public void setStrcarbus(String strcarbus) {
        this.strcarbus = strcarbus;
    }

    public String getStrother() {
        return strother;
    }

    public void setStrother(String strother) {
        this.strother = strother;
    }

    public String getStrcamping() {
        return strcamping;
    }

    public void setStrcamping(String strcamping) {
        this.strcamping = strcamping;
    }
}
