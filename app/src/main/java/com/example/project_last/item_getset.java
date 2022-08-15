package com.example.project_last;

import org.json.JSONException;
import org.json.JSONObject;

class item_getset {
    String je, ge, gam, be, star, url;

    public item_getset(JSONObject jsonObject) {
        try {
            String je = "제목 : " + jsonObject.getString("title").replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
            String ge = "개봉일 : " + jsonObject.getString("pubDate");
            String gam = "감독 : " + jsonObject.getString("director").replace("|", " ").replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
            String be = "주연배우 : " + jsonObject.getString("actor").replace("|", "   ").replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
            String star = "별점 : " + jsonObject.getString("userRating");
            String url = jsonObject.getString("image");

            this.je = je;
            this.ge = ge;
            this.gam = gam;
            this.be = be;
            this.star = star;
            this.url = url;


        } catch (JSONException e) {
            e.printStackTrace();
        }
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

