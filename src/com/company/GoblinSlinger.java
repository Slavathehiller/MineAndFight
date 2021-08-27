package com.company;

import javax.swing.*;

public class GoblinSlinger extends Monster {

    @Override
    protected String getImagePath() {
        return "/goblin_slinger_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Гоблин-марадер";
        frequencyMove = 0.9f;
        Power = 7;
        FeelRadius = 7;
        RangedPower = 5;
        RangedDistance = 4;
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.2f;
        buffings = new Buffing[]{stun};
        drop.addResource(ResourceType.Fur, 1);
        drop.addRandomResource(ResourceType.Coins, 5, 20, 0.7f);
        drop.addRandomEquipment(EquipmentType.Sling, 1, 1, 0.2f);
    }

    @Override
    public void Act() {
        if (CanMove()) {
            if (ShootIfPlayerInRange(" стреляет в ")) {
                return;
            }
            if (FeelRadius() >= map.DistanceToPlayer(this) && RangedDistance < map.DistanceToPlayer(this)){
                MoveToPlayer();
                return;
            }
            RandomMove();
        }
    }

    @Override
    public float getMaxHealth() {
        return 12;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public float getRangedPower(){
        return RangedPower;
    }

    public GoblinSlinger() {

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
}
