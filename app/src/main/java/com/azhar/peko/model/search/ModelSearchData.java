package com.azhar.peko.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ModelSearchData implements Parcelable {

    public static final Creator<ModelSearchData> CREATOR = new Creator<ModelSearchData>() {
        @Override
        public ModelSearchData createFromParcel(Parcel in) {
            return new ModelSearchData(in);
        }

        @Override
        public ModelSearchData[] newArray(int size) {
            return new ModelSearchData[size];
        }
    };
    @SerializedName("login")
    private String login;
    @SerializedName("url")
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    public ModelSearchData() {
    }

    protected ModelSearchData(Parcel in) {
        this.avatarUrl = in.readString();
        this.login = in.readString();
        this.url = in.readString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.avatarUrl);
        parcel.writeString(this.login);
        parcel.writeString(this.url);
    }

}