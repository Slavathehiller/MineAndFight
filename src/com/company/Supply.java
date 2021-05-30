package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Supply {

    public String Name;
    public Recipe recipe;
    public ImageIcon image;
    public ImageIcon smallImage;
    long Number;
    abstract protected String getImagePath();
    abstract protected String getSmallImagePath();

    protected void init(){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
        smallImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(getSmallImagePath())));
    }

    public Supply(){
        init();
    }

    public void Use(Player player) throws Exception{
        if(Number < 1){
            player.supplies.remove(this);
        }
    }
}
