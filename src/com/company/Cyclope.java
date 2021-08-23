package com.company;

import javax.swing.*;

public class Cyclope extends Monster{

    @Override
    protected String getImagePath() {
        return "/cyclope_icon_30x30.png";
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
        Name = "Циклоп";
        frequencyMove = 0.1f;
        Power = 80;
        FeelRadius = 4;
        Giant = true;
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.8f;
        buffings = new Buffing[]{stun};
        drop.addRandomResource(ResourceType.Coins, 10000, 100000);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 2, 15, 0.7f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 2, 15, 0.5f);
    }

    public Cyclope(){

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