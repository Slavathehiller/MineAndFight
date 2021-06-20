package com.company;

import javax.swing.*;
import java.util.Objects;

public abstract class Monster implements IDisplayable{

    public int X;
    public int Y;
    public String Name;
    public ImageIcon image;
    protected IMap map;
    abstract protected String getImagePath();

    protected void init(){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
    }

    public Monster(){
        init();
    }

    public Monster(IMap map, int x, int y){
        init();
        this.X = x;
        this.Y = y;
        this.map = map;
    }
    public abstract void Act();

}
