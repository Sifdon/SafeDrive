package com.bitland.safedrive;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by BLESSMORE on 12/10/2017.
 */

@IgnoreExtraProperties
public class Road {

    private String name;
    private String trafficLightsStatus;
    private double trafficVolume;
    private int carnageCount;
    private int roadBlockCount;

    public Road(String name, String trafficLightsStatus, double trafficVolume, int carnageCount, int roadBlockCount) {
        this.name = name;
        this.trafficLightsStatus = trafficLightsStatus;
        this.trafficVolume = trafficVolume;
        this.carnageCount = carnageCount;
        this.roadBlockCount = roadBlockCount;
    }

    public Road() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTrafficLightsStatus() {
        return trafficLightsStatus;
    }

    public void setTrafficLightsStatus(String trafficLightsStatus) {
        this.trafficLightsStatus = trafficLightsStatus;
    }

    public double getTrafficVolume() {
        return trafficVolume;
    }

    public void setTrafficVolume(double trafficVolume) {
        this.trafficVolume = trafficVolume;
    }

    public int getCarnageCount() {
        return carnageCount;
    }

    public void setCarnageCount(int carnageCount) {
        this.carnageCount = carnageCount;
    }

    public int getRoadBlockCount() {
        return roadBlockCount;
    }

    public void setRoadBlockCount(int roadBlockCount) {
        this.roadBlockCount = roadBlockCount;
    }
}
