package com.example.project_last;

class item_getset4 {
    String title_MR, imgurl, title_R, content_R, with_p, date, rating;

    public item_getset4(String title_MR, String imgurl, String title_R, String content_R, String with_p, String date, String rating) {
        this.title_MR = title_MR;
        this.imgurl = imgurl;
        this.title_R = title_R;
        this.content_R = content_R;
        this.with_p = with_p;
        this.date = date;
        this.rating = rating;
    }

    public String getTitle_MR() {
        return title_MR;
    }

    public void setTitle_MR(String title_MR) {
        this.title_MR = title_MR;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle_R() {
        return title_R;
    }

    public void setTitle_R(String title_R) {
        this.title_R = title_R;
    }

    public String getContent_R() {
        return content_R;
    }

    public void setContent_R(String content_R) {
        this.content_R = content_R;
    }

    public String getWith_p() {
        return with_p;
    }

    public void setWith_p(String with_p) {
        this.with_p = with_p;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
