package com.funnyvideos.newvideos.models;

public class Setting {

    private String privacy_policy;
    private String comment_approval;
    private String package_name;
    private String admob_appid,admob_inter,admob_banner,admob_status,startup_status,startup_id,faninter;

    public String getFaninter() {
        return faninter;
    }

    public void setFaninter(String faninter) {
        this.faninter = faninter;
    }

    public String getStartup_status() {
        return startup_status;
    }

    public void setStartup_status(String startup_status) {
        this.startup_status = startup_status;
    }

    public String getStartup_id() {
        return startup_id;
    }

    public void setStartup_id(String startup_id) {
        this.startup_id = startup_id;
    }

    public String getAdmob_appid() {
        return admob_appid;
    }

    public void setAdmob_appid(String admob_appid) {
        this.admob_appid = admob_appid;
    }

    public String getAdmob_inter() {
        return admob_inter;
    }

    public void setAdmob_inter(String admob_inter) {
        this.admob_inter = admob_inter;
    }

    public String getAdmob_banner() {
        return admob_banner;
    }

    public void setAdmob_banner(String admob_banner) {
        this.admob_banner = admob_banner;
    }

    public String getAdmob_status() {
        return admob_status;
    }

    public void setAdmob_status(String admob_status) {
        this.admob_status = admob_status;
    }




    public String getPrivacy_policy() {
        return privacy_policy;
    }

    public void setPrivacy_policy(String privacy_policy) {
        this.privacy_policy = privacy_policy;
    }

    public String getComment_approval() {
        return comment_approval;
    }

    public void setComment_approval(String comment_approval) {
        this.comment_approval = comment_approval;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }
}
