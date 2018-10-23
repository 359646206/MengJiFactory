package com.pet.test.javabean;

/**
 * Created by yangzhengguang on 2018/9/13.
 */

public class Pet {

    /**
     * Id : d701f538-c12f-11e8-9780-faae74949400
     * IMEI : 861766031830973
     * Longitude : 113.98801
     * Latitude : 22.591902
     * Battery : 64
     * States : normal
     * Direct : 278.39
     * Coodi : 0
     * Nbcsq : 10
     * HVersion : HW_V02
     * SVersion : SW_20180827
     * ts : 1538125636
     */

    private String Id;
    private String IMEI;
    private double Longitude;
    private double Latitude;
    private int Battery;
    private String States;
    private double Direct;
    private int Coodi;
    private int Nbcsq;
    private String HVersion;
    private String SVersion;
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

    public double getDirect() {
        return Direct;
    }

    public void setDirect(double Direct) {
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
                ", ts=" + ts +
                '}';
    }
}
