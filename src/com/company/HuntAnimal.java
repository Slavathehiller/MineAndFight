package com.company;

import javax.swing.*;

public abstract class HuntAnimal {
    public long X;
    public long Y;
    public String Name;
    public int MaxX;
    public int MaxY;
    public int equipNeeded;
    public ImageIcon image;

    protected String getImagePath(){
        return null;
    }

    public HuntAnimal(int maxX, int maxY){
        this.MaxX = maxX;
        this.MaxY = maxY;
        image = new ImageIcon(getClass().getResource(getImagePath()));
    }


    public abstract void Act();
}
