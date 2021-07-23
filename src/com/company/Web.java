package com.company;

import javax.swing.*;
import java.util.Objects;

public class Web implements IDisplayable{

    public int X;
    public int Y;
    public IMap map;
    public ImageIcon imageIcon;
    private GiantSpider master;

    @Override
    public void init(IMap map, int x, int y) {
        X = x;
        Y = y;
        this.map = map;
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/spider_web_icon_30x30.png")));
    }

    public Web(IMap map, int x, int y){
        init(map, x, y);
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
        return imageIcon;
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
        return "Паутина гигантского паука";
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Web;
    }

    @Override
    public Object getSelf() {
        return this;
    }


}
