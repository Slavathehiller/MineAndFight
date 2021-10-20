package com.company;

import javax.swing.*;

public class TrollPatriarch extends Monster{


    @Override
    protected String getImagePath() {
        return "/troll_patriarch_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
                return;
            }
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

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Тролль-патриарх";
        frequencyMove = 0.5f;
        Power = 60;
        FeelRadius = 4;
        Regeneration = true;
        StoneSkin = true;
        drop.addRandomResource(ResourceType.Coins, 1500, 20000);
        drop.addRandomResource(ResourceType.Stone, 8000, 40000);
        drop.addRandomEquipment(EquipmentType.HuntBow, 1, 7, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 6, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 1, 6, 0.25f);
        drop.addRandomSupply(HealthPotion.class, 1,6, 0.20f);
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.6f;
        buffings = new Buffing[]{stun};
    }

    public TrollPatriarch(){

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