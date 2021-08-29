package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class WolfKing extends Monster {

    private boolean FeelPLayer = false;

    @Override
    protected String getImagePath() {
        return "/wolf_king_icon_30x30.png";
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Король волков";
        frequencyMove = 0.1f;
        Power = 30;
        FeelRadius = 10;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 3;
        bleed.Power = 4;
        bleed.Chance = 0.6f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 5);
        drop.addRandomResource(ResourceType.Coins, 50, 300);
        drop.addRandomEquipment(EquipmentType.HuntBow, 1, 3, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 2, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 1, 1, 0.25f);
    }

    @Override
    public void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (AttackIfPlayerNear()) {
        }
        if (AttackIfPlayerNear()) {
            return;
        }
        if(FeelRadius() >= map.DistanceToPlayer(this)) {
            if (!FeelPLayer) {
                FeelPLayer = true;
                SummonPack();
                return;
            }
        }
        else {
            FeelPLayer = false;
        }
        if(CanMove()){
            RandomMove();
        }
    }

    public void SummonPack() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        var minValue = 1;
        var maxValue = 3;
        maxValue -= minValue;
        var count = (int) (Math.random() * ++maxValue) + minValue;
        map.GenerateMonsters(BlackWolf.class, count);
        map.addToLog("Король волков использует призыв стаи");
    }

    @Override
    public float getMaxHealth() {
        return 100;
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
