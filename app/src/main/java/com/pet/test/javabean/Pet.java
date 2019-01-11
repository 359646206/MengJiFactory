package com.pet.test.javabean;

/**
 * Created by yangzhengguang on 2018/9/13.
 */

public class Pet {


    /**
     * Id : 84b6a1ee-e64a-11e8-acb0-faae74949400
     * IMEI : 867726030324174
     * Longitude : 113.98801
     * Latitude : 22.591837
     * Battery : 24
     * States : normal
     * Direct : 0
     * Coodi : 0
     * Nbcsq : 16
     * HVersion :
     * SVersion :
     * gpsCn1 : 45
     * gpsCn2 : 44
     * gpsCn3 : 42
     * ts : 1545298073
     */

    private String Id;
    private String IMEI;
    private double Longitude;
    private double Latitude;
    private int Battery;
    private String States;
    private int Direct;
    private int Coodi;
    private int Nbcsq;
    private String HVersion;
    private String SVersion;
    private String gpsCn1;
    private String gpsCn2;
    private String gpsCn3;
    private int ts;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public int getBattery() {
        return Battery;
    }

    public void setBattery(int Battery) {
        this.Battery = Battery;
    }

    public String getStates() {
        return States;
    }

    public void setStates(String States) {
        this.States = States;
    }

    public int getDirect() {
        return Direct;
    }

    public void setDirect(int Direct) {
        this.Direct = Direct;
    }

    public int getCoodi() {
        return Coodi;
    }

    public void setCoodi(int Coodi) {
        this.Coodi = Coodi;
    }

    public int getNbcsq() {
        return Nbcsq;
    }

    public void setNbcsq(int Nbcsq) {
        this.Nbcsq = Nbcsq;
    }

    public String getHVersion() {
        return HVersion;
    }

    public void setHVersion(String HVersion) {
        this.HVersion = HVersion;
    }

    public String getSVersion() {
        return SVersion;
    }

    public void setSVersion(String SVersion) {
        this.SVersion = SVersion;
    }

    public String getGpsCn1() {
        return gpsCn1;
    }

    public void setGpsCn1(String gpsCn1) {
        this.gpsCn1 = gpsCn1;
    }

    public String getGpsCn2() {
        return gpsCn2;
    }

    public void setGpsCn2(String gpsCn2) {
        this.gpsCn2 = gpsCn2;
    }

    public String getGpsCn3() {
        return gpsCn3;
    }

    public void setGpsCn3(String gpsCn3) {
        this.gpsCn3 = gpsCn3;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "Id='" + Id + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                ", Battery=" + Battery +
                ", States='" + States + '\'' +
                ", Direct=" + Direct +
                ", Coodi=" + Coodi +
                ", Nbcsq=" + Nbcsq +
                ", HVersion='" + HVersion + '\'' +
                ", SVersion='" + SVersion + '\'' +
                ", gpsCn1='" + gpsCn1 + '\'' +
                ", gpsCn2='" + gpsCn2 + '\'' +
                ", gpsCn3='" + gpsCn3 + '\'' +
                ", ts=" + ts +
                '}';
    }
}
