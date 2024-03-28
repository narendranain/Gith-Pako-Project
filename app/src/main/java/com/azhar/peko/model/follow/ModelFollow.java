package com.azhar.peko.model.follow;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ModelFollow {

    public static final Parcelable.Creator<ModelFollow> CREATOR = new Parcelable.Creator<ModelFollow>() {
        @Override
        public ModelFollow createFromParcel(Parcel in) {
            return new ModelFollow(in);
        }

        @Override
        public ModelFollow[] newArray(int size) {
            return new ModelFollow[size];
        }
    };
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    protected ModelFollow(Parcel in) {
        avatarUrl = in.readString();
        login = in.readString();
        htmlUrl = in.readString();
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(avatarUrl);
        parcel.writeString(login);
        parcel.writeString(htmlUrl);
    }

}
