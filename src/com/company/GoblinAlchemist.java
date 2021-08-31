package com.company;

import javax.swing.*;

public class GoblinAlchemist extends Monster {

    @Override
    protected String getImagePath() {
        return "/goblin_alchemist_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Гоблин-алхимик";
        frequencyMove = 0.9f;
        Power = 5;
        FeelRadius = 5;
        RangedPower = 3;
        RangedDistance = 3;
        Buffing blindness = new Buffing();
        blindness.BuffType = BattleBuffType.Blindness;
        blindness.Duration = 2;
        blindness.Power = 25;
        blindness.Chance = 0.4f;
        buffings = new Buffing[]{blindness};
        drop.addResource(ResourceType.Fur, 1);
        drop.addRandomResource(ResourceType.Coins, 5, 20, 0.7f);
    }

    @Override
    public void Act() {
        if (CanMove()) {
            if (ShootIfPlayerInRange(" бросает склянку с зельем в ")) {
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
        return 10;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public float getRangedPower(){
        return RangedPower;
    }

    public GoblinAlchemist() {

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
