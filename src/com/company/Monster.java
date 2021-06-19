package com.company;

import javax.swing.*;
import java.util.Objects;

public abstract class Monster implements IDisplayable{

    public int X;
    public int Y;
    public String Name;
    public ImageIcon image;
    abstract protected String getImagePath();

    protected void init(){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
    }

    public Monster(){
        init();
    }

}
