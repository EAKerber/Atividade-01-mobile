package com.example.projetoatividade01.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;

public class Post implements Parcelable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Post(Parcel parcel) {
        this.userId = parcel.readInt();
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.body = parcel.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId (int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String title) {
        this.body = body;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.body);
    }
}
