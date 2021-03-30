package com.example.projetoatividade01.model.users;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable{
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


    public User(int id, String  name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }


    private User(Parcel p) {
        this.id = p.readInt();
        this.name = p.readString();
        this.username = p.readString();
        this.email = p.readString();
        this.address = new Address(p.readString(),
                p.readString(),
                p.readString(),
                p.readString(),
                new Geo(p.readString(),
                        p.readString()));
        this.phone = p.readString();
        this.website = p.readString();
        this.company = new Company(p.readString(),
                p.readString(),
                p.readString());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.username);
        parcel.writeString(this.email);
        parcel.writeString(this.address.street);
        parcel.writeString(this.address.suite);
        parcel.writeString(this.address.city);
        parcel.writeString(this.address.zipcode);
        parcel.writeString(this.address.geo.lat);
        parcel.writeString(this.address.geo.lng);
        parcel.writeString(this.phone);
        parcel.writeString(this.website);
        parcel.writeString(this.company.name);
        parcel.writeString(this.company.catchPhrase);
        parcel.writeString(this.company.bs);
    }



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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
