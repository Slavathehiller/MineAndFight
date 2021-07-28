package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class MoonMaiden extends Monster {

    private boolean FeelPLayer = false;

    @Override
    protected String getImagePath() {
        return "/women_icon_30x30.png";
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Лунная дева";
        frequencyMove = 0;
        Power = 10;
        drop.addRandomResource(ResourceType.Coins, 700, 1200);
        drop.addRandomEquipment(EquipmentType.HuntBow, 1, 3, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 2, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 1, 1, 0.25f);
    }

    @Override
    public void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
    }

    @Override
    public float getMaxHealth() {
        return 100;
    }

    @Override
    public int FeelRadius() {
        return 2;
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
        return buffings;
    }
}