package com.company;

import javax.swing.*;

public class RuinObstacle extends Obstacle{

    @Override
    protected String getImagePath() {
        var a = Math.random();
        System.out.println("a = " + a);
        if(a > 0.80) {
            return "/ruin4_icon_30x30.png";
        }
        else {
            if (a > 0.60)
                return "/ruin3_icon_30x30.png";
            else {
                if(a > 0.40)
                    return "/ruin2_icon_30x30.png";
                else {
                    return "/ruin1_icon_30x30.png";
                }
            }
        }
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Руины";
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

    @Override
    public String getToolTip() {
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
