package com.example.projetoatividade01.model.users;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;


public class User implements Parcelable{
    private int id;
    private String name;
    private String username;
    private String email;
    private  Parcelable address;
    private String phone;
    private String website;
    private  Parcelable company;


    public User(int id, String  name, String username, String email, Parcelable address, String phone, String website,Parcelable company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private User(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.username = parcel.readString();
        this.email = parcel.readString();
        //Address adrs = new Address(this,this,this,this,this);
        //this.address = adrs;
        this.phone = parcel.readString();
        this.website = parcel.readString();
        //Company cmpn = new Company(this,this,this);
        //this.company = cmpn;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Parcelable getAddress() {
        return address;
    }

    public void setAddress(Parcelable address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
            return website;
    }

    public void setWebSite(String website) {
        this.website = website;
    }

    public Parcelable getCompany() {
        return company;
    }

    public void setCompany(Parcelable company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.username);
        parcel.writeString(this.email);
        //parcel.writeParcelable(this.address);
        parcel.writeString(this.phone);
        parcel.writeString(this.website);
        //parcel.writeParcelable(getCompany(this.company));
    }

}
