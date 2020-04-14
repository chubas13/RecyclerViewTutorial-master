package com.androidfizz.recyclerviewdemo.models;
/**
 * Created by jitendra.singh on 1/10/2018
 * for RecyclerViewWithDividers
 */

public class AndroidVersion {
    private int imgResId;
    private String codeName;
    private String versionName;
    private String apiLevel;

    public AndroidVersion(int imgResId, String codeName,
                          String versionName, String apiLevel) {
        this.imgResId=imgResId;
        this.codeName = codeName;
        this.versionName = versionName;
        this.apiLevel = apiLevel;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getApiLevel() {
        return apiLevel;
    }

}
