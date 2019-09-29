package com.kasun.tasteit;

public class Upload {

    private String equipmentname;
    private String ownername;
    private String price;
    private String contactnumber;
    private String publishdate;
    private String ImageUrl;

    public Upload(){

    }


    public Upload(String ename, String oname, String eprice, String cnumber, String pdate, String imageUrl){

        if(ename.trim().equals("")){
            ename = "NO Name";
        }
        if(oname.trim().equals("")){
            oname = "NO Name";
        }
        if(eprice.trim().equals("")){
            eprice = "NO Price";
        }
        if(cnumber.trim().equals("")){
            cnumber = "NO Price";
        }
        if(pdate.trim().equals("")){
            pdate = "NO Price";
        }


        equipmentname = ename;
        ownername = oname;
        price = eprice;
        contactnumber = cnumber;
        publishdate = pdate;
        ImageUrl = imageUrl;

    }
    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }


}
