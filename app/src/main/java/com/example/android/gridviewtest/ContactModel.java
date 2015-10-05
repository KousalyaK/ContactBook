package com.example.android.gridviewtest;

import android.graphics.Bitmap;

/**
 * Created by anjana on 9/29/15.
 */
public class ContactModel {

    private String email ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Bitmap getImageIcon() {
        return ImageIcon;
    }

    public void setImageIcon(Bitmap imageIcon) {
        ImageIcon = imageIcon;
    }

    private String phNumber  ;
    private String Name  ;
    private Bitmap ImageIcon;

}
