package com.company;

import javax.swing.*;

public class Obstacle implements IDisplayable{

    @Override
    public ImageIcon getImage() {
        return null;
    }

    @Override
    public Integer getX() {
        return null;
    }

    @Override
    public Integer getY() {
        return null;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Obstacle;
    }

    @Override
    public Object getSelf() {
        return this;
    }
}
