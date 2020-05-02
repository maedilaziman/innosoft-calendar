package com.maedi.soft.ino.calendar.model;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

public class ListObject extends ArrayList<ListObject> {

    public String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    public int i1;
    public int i2;
    public boolean bool;
    public Bitmap bitmap1;

    public ListObject() { }

    public ListObject(String s1, int i1, int i2)
    {
        this.s1 = s1;
        this.i1 = i1;
        this.i2 = i2;
    }

    public ListObject(String s1)
    {
        this.s1 = s1;
    }

    public ListObject(String s1, String s2, String s3)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public ListObject(String s1, String s2, String s3, String s4)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    public ListObject(String s1, String s2, String s3, String s4, String s5, String s6)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }

    public ListObject(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, boolean bool)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.bool = bool;
    }

    public ListObject(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, boolean bool)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.bool = bool;
    }

    public ListObject(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, boolean bool)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.s10 = s10;
        this.bool = bool;
    }

    public ListObject(String s1, String s2, String s3, int i1)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.i1 = i1;
    }

    public ListObject(Bitmap bitmap1) {
        super();
        this.bitmap1 = bitmap1;
    }

    public ListObject(String s1, Bitmap bitmap1) {
        super();
        this.s1 = s1;
        this.bitmap1 = bitmap1;
    }

    public ListObject(String s1, boolean bool) {
        super();
        this.s1 = s1;
        this.bool = bool;
    }

    public ListObject(String s1, String s2, boolean bool) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.bool = bool;
    }

    public ListObject(String s1, String s2, int i1) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.i1 = i1;
    }

    public static String[] MainMenuTabsTitle(FragmentActivity f){
        return new String[]{
                "GALLERY",
                "CAMERA"
        };
    }
}
