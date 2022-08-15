package com.example.project_last;

import android.database.Cursor;

class item_getset2 {
    String je, ge, gam, be, star, url;

    public item_getset2(String je, String ge, String gam, String be, String star, String url) {
        this.je = je;
        this.ge = ge;
        this.gam = gam;
        this.be = be;
        this.star = star;
        this.url = url;
    }


    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getGe() {
        return ge;
    }

    public void setGe(String ge) {
        this.ge = ge;
    }

    public String getGam() {
        return gam;
    }

    public void setGam(String gam) {
        this.gam = gam;
    }

    public String getBe() {
        return be;
    }

    public void setBe(String be) {
        this.be = be;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
