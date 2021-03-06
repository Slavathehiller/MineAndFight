package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class SpiderQueen extends Monster{

    private int timeToLayEggs;
    private int maxTimeToLayEggs = 7;

    @Override
    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Королева пауков";
        frequencyMove = 0.1f;
        Power = 30;
        FeelRadius = 5;
        timeToLayEggs = maxTimeToLayEggs;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 3;
        poison.Power = 4;
        poison.Chance = 0.6f;
        buffings = new Buffing[]{poison};
        drop.addRandomResource(ResourceType.Coins, 700, 5000);
        drop.addResource(ResourceType.Fiber, 70);
        drop.addRandomEquipment(EquipmentType.HuntBow, 2, 6, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 2, 5, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 2, 5, 0.25f);
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
        if(AttackIfPlayerNear())
            return;
        CheckAndLay();
    }

    private void CheckAndLay(){
        timeToLayEggs --;
        if(timeToLayEggs < 1 && map.getMonsters().size() <= map.getMonsterLimit() - 2){
            timeToLayEggs = maxTimeToLayEggs;
            Point point = map.GenerateFreeCordsWithin(new Point(X, Y), 1);
            if(point != null) {
                map.GenerateMonster(SpiderClutch.class, point.X, point.Y);
            }
        }
    }

    @Override
    public float getMaxHealth() {
        return 100;
    }

}
