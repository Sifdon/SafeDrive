/**This class models a location. A location in this context is defined as a city or town. The location has a name and a
 list of roads within that location.*/
package com.bitland.safedrive.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by BLESSMORE on 12/10/2017.
 */

@IgnoreExtraProperties
public class Location implements Parcelable {

    private String name;
    private ArrayList<Road> roads;

    public Location(String name, ArrayList<Road> roads) {
        this.name = name;
        this.roads = roads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeList(roads);

    }

    private Location(Parcel in){
        name = in.readString();
        roads = in.readArrayList(ArrayList.class.getClassLoader());
    }

    public Location() {}

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel parcel) {
            return new Location(parcel);
        }

        @Override
        public Location[] newArray(int i) {
            return new Location[i];
        }
    };
}
