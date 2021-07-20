package com.company;

import javax.swing.*;

public class ExitPoint extends Obstacle{

    public int X = 0;
    public int Y = 0;

    public ExitPoint(IMap map){
        init(map, 0, 0);
    }

    @Override
    public void init(IMap map, int x, int y){
        super.init(map, x, y);
    }

    @Override
    protected String getImagePath() {
        return "/exit_icon_30x30.png";
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getToolTip() {
        return "Выход";
    }

    @Override
    public Integer getX() {
        return X;
    }

    @Override
    public Integer getY() {
        return Y;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.ExitPoint;
    }

    @Override
    public Object getSelf() {
        return this;
    }

}
