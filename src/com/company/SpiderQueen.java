package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class SpiderQueen extends Monster{

    @Override
    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Королева пауков";
        frequencyMove = 0.1f;
        Power = 30;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Bleed;
        poison.Duration = 5;
        poison.Chance = 0.6f;
        buffings = new Buffing[]{poison};
        drop.addRandomResource(ResourceType.Coins, 200, 700);
        drop.addRandomEquipment(EquipmentType.HuntBow, 2, 3, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 2, 4, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 2, 4, 0.25f);
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

    @Override
    protected String getImagePath() {
        return "/spider_queen_icon_30x30.png";
    }

    @Override
    public void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if(AttackIfPlayerNear());
            return;
    }

    @Override
    public float getMaxHealth() {
        return 100;
    }

    @Override
    public int FeelRadius() {
        return 5;
    }
}
