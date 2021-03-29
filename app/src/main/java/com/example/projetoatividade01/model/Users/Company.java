package com.example.projetoatividade01.model.users;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;

public class Company implements Parcelable {
    private String name;
    private String catchPhrase;
    private String bs;


    public Company(String  name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Company(Parcel parcel) {
        this.name = parcel.readString();
        this.catchPhrase = parcel.readString();
        this.bs = parcel.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.catchPhrase);
        parcel.writeString(this.bs);
    }
}
