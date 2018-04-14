package com.deepaks.com.delhiguide.bean;

import android.os.Parcelable;

public class LocationBean implements Parcelable {
    private String name, location, entryCost, openingHour, description;
    private int imageID;


    public LocationBean(String name, String location, String entryCost, String openingHour, String description, int imageID) {
        this.name = name;
        this.location = location;
        this.entryCost = entryCost;
        this.openingHour = openingHour;
        this.description = description;
        this.imageID = imageID;
    }

    private LocationBean(android.os.Parcel in) {
        name = in.readString();
    }

    public static final Creator<LocationBean> CREATOR = new Creator<LocationBean>() {
        @Override
        public LocationBean createFromParcel(android.os.Parcel in) {
            return new LocationBean(in);
        }

        @Override
        public LocationBean[] newArray(int size) {
            return new LocationBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEntryCost() {
        return entryCost;
    }

    public String getOpeningHour() {
        return openingHour;
    }


    public int getImageID() {
        return imageID;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(location);
        parcel.writeString(entryCost);
        parcel.writeString(openingHour);
        parcel.writeInt(imageID);
    }
}
