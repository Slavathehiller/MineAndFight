package com.company;

import javax.swing.*;

public class TrollThrower extends Monster {

    @Override
    protected String getImagePath() {
        return "/troll_thrower_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Тролль-метатель";
        frequencyMove = 0.7f;
        Power = 45;
        FeelRadius = 4;
        RangedPower = 25;
        RangedDistance = 4;
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.4f;
        buffings = new Buffing[]{stun};
        drop.addRandomResource(ResourceType.Stone, 3000, 7000, 0.5f);
    }

    @Override
    public void Act() {
        if (ShootIfPlayerInRange(" кидает камнем в ")) {
            return;
        }
        if (CanMove()) {
            if (FeelRadius() >= map.DistanceToPlayer(this) && RangedDistance < map.DistanceToPlayer(this)){
                MoveToPlayer();
                return;
            }
            RandomMove();
        }
    }

    @Override
    public float getMaxHealth() {
        return 50;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public float getRangedPower(){
        return RangedPower;
    }

    public TrollThrower() {

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
