package com.company;

import javax.swing.*;

public class Hobgoblin extends Monster{

    @Override
    protected String getImagePath() {
        return "/hobgoblin_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
            if(FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
                return;
            }
            else
                RandomMove();
        }
    }


    @Override
    public float getMaxHealth() {
        return 100;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public int FeelRadius() {
        return FeelRadius;
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Хобгоблин";
        frequencyMove = 0.3f;
        Power = 30;
        FeelRadius = 4;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 7;
        bleed.Power = 3;
        bleed.Chance = 0.5f;
        Armed = true;
        buffings = new Buffing[]{bleed};
        drop.addRandomResource(ResourceType.Coins,50 ,200);
        drop.addRandomResource(ResourceType.Fur, 2, 10);
        drop.addRandomResource(ResourceType.Leather, 1, 5);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 3, 0.7f);
    }

    public Hobgoblin(){

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