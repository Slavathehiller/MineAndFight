package com.company;

import javax.swing.*;
import java.util.Objects;

public class Chest implements IDisplayable {

    public int X;
    public int Y;
    public ImageIcon OpenedImage;
    public ImageIcon ClosedImage;
    public boolean isLooted = false;
    public Drop drop = new Drop();
    private IMap map;

    public Chest(int x, int y, IMap map){
        this.map = map;
        X = x;
        Y = y;
        ClosedImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/chest_close_icon_30x30.png")));
        OpenedImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/chest_open_icon_30x30.png")));
    }

    @Override
    public ImageIcon getImage() {
        if(isLooted){
            return OpenedImage;
        }
        return ClosedImage;
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
        if(!isLooted)
            return "Сундук с сокровищами";
        else
            return "Пустой сундук";

    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Chest;
    }

    @Override
    public Object getSelf() {
        return this;
    }

    @Override
    public void init(IMap map, int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean getVisible() {
        return !(map.getPlayer().VisionLimit < map.DistanceToPlayer(this));
    }


}
