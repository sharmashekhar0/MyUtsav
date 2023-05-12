package com.example.myutsav;

public class guestdatamodel {
    String guestname,guestaddress,gueststatus,guestnumber;

    public guestdatamodel(String guestname, String guestnumber,String guestaddress, String gueststatus) {
        this.guestname = guestname;
        this.guestaddress = guestaddress;
        this.gueststatus = gueststatus;
        this.guestnumber = guestnumber;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

  public String getGuestaddress() {
        return guestaddress;
    }

    public void setGuestaddress(String guestaddress) {
        this.guestaddress = guestaddress;
    }

    public String getGueststatus() {
        return gueststatus;
    }

    public void setGueststatus(String gueststatus) {
        this.gueststatus = gueststatus;
    }

    public String getGuestnumber() {
        return guestnumber;
    }

    public void setGuestnumber(String guestnumber) {
        this.guestnumber = guestnumber;
    }
}
