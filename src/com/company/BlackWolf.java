package com.company;

import javax.swing.*;

public class BlackWolf extends Monster{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    public BlackWolf(int x, int y){
        this.X = x;
        this.Y = y;
        Name = "Черный волк";
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public Integer getX() {
        return X;
    }

    @Override
    public Integer getY() {
        return Y;
    }
}
