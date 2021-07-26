package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class SpiderClutch extends Monster{

    private int timeToHatch = 15;

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Паучья кладка";
        frequencyMove = 0;
        Power = 3;
    }

    public SpiderClutch(){

    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Monster;
    }

    @Override
    public Object getSelf() {
        return this;
    }

    @Override
    public Buffing[] getBuffing() {
        return new Buffing[0];
    }

    @Override
    protected String getImagePath() {
        return "/spider_clutch_icon_30x30.png";
    }

    private void Hatch() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        var spiderSpawn = map.GenerateMonster(SpiderSpawn.class);
        spiderSpawn.X = X;
        spiderSpawn.Y = Y;
        this.Die();
    }

    @Override
    public void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        timeToHatch --;
        if(timeToHatch == 0){
            Hatch();
        }

    }

    @Override
    public float getMaxHealth() {
        return 5;
    }

    @Override
    public int FeelRadius() {
        return 0;
    }
}
