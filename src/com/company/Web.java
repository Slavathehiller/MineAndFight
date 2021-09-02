package com.company;

import javax.swing.*;
import java.util.Objects;

public class Web implements IDisplayable{

    public int X;
    public int Y;
    private boolean isVisible;
    public IMap map;
    public ImageIcon imageIcon;
    private GiantSpider master;

    @Override
    public void init(IMap map, int x, int y) {
        X = x;
        Y = y;
        isVisible = Math.random() < 0.5f;
        this.map = map;
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/spider_web_icon_30x30.png")));
    }

    @Override
    public boolean getVisible() {
        if(map.getPlayer().VisionLimit < map.DistanceToPlayer(this)){
            return false;
        }
        return isVisible;
    }

    public Web(IMap map, int x, int y){
        init(map, x, y);
    }

    public void setVisible(boolean value){
        isVisible = value;
    }

    public void setMaster(GiantSpider master){
        this.master = master;
        if(master != null)
            master.setWeb(this);
    }

    public GiantSpider getMaster(){
        return master;
    }

    @Override
    public ImageIcon getImage() {
        if(isVisible)
            return imageIcon;
        else
            return null;
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
        if(isVisible)
            return "Паутина гигантского паука";
        else
            return "";
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Web;
    }

    @Override
    public Object getSelf() {
        return this;
    }

    public void Destroy(){
        setMaster(null);
        map.getObstacles().remove(this);
    }

}
