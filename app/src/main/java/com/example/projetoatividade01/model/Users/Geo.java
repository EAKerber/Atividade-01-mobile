package com.example.projetoatividade01.model.users;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;

public class Geo implements Parcelable {
    private String lat;
    private String lng;

    public Geo(String  lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Geo(Parcel parcel) {
        this.lat = parcel.readString();
        this.lng = parcel.readString();
    }

    public static final Creator<Geo> CREATOR = new Creator<Geo>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        @Override
        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };



    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.lat);
        parcel.writeString(this.lng);
    }
}
