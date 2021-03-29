package com.example.projetoatividade01.model.users;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;


public class Address implements Parcelable{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Parcelable geo;

    public Address(String  street, String suite, String city, String zipcode, Parcelable geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Address(Parcel parcel) {
        this.street = parcel.readString();
        this.suite = parcel.readString();
        this.city = parcel.readString();
        this.zipcode = parcel.readString();
        //Geo go = new Geo(this, this);
        //this.geo = go;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };



    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Parcelable getGeo() {
        return geo;
    }

    public void setGeo(Parcelable geo) {
        this.geo = geo;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.street);
        parcel.writeString(this.suite);
        parcel.writeString(this.city);
        parcel.writeString(this.zipcode);
        //parcel.writeParcelable(this.geo);
    }
}
