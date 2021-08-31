package com.company;

import javax.swing.*;
import java.util.Objects;

public abstract class Obstacle implements IDisplayable{

    public int X;
    public int Y;
    public String Name;
    public ImageIcon image;
    protected IMap map;
    abstract protected String getImagePath();

    public void init(IMap map, int x, int y){
        if(getImagePath() == null){
            return;
        }
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
        this.X = x;
        this.Y = y;
        this.map = map;
    }

    public Obstacle(){
    }

    public Obstacle(IMap map, int x, int y){
        init(map, x, y);

    }

    @Override
    public boolean getVisible() {
        return !(map.getPlayer().VisionLimit < map.DistanceToPlayer(this));
    }

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
