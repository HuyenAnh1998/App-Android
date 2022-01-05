package com.example.gameadmin.quanlycauhoiguilen;

import java.io.Serializable;

public class Cauhoi implements Serializable {
    String cauhoi, A, B, C, D, dapan, giaidap;
    String id;

    public Cauhoi(String cauhoi, String a, String b, String c, String d, String dapan, String giaidap) {
        this.cauhoi = cauhoi;
        A = a;
        B = b;
        C = c;
        D = d;
        this.dapan = dapan;
        this.giaidap = giaidap;
    }

    public Cauhoi(String cauhoi, String a, String b, String c, String d, String dapan, String giaidap, String id) {
        this.cauhoi = cauhoi;
        A = a;
        B = b;
        C = c;
        D = d;
        this.dapan = dapan;
        this.giaidap = giaidap;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public String getA() {
        return A;
    }

    public String getB() {
        return B;
    }

    public String getC() {
        return C;
    }

    public String getD() {
        return D;
    }

    public String getDapan() {
        return dapan;
    }

    public String getGiaidap() {
        return giaidap;
    }
}
