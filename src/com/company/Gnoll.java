package com.company;

import javax.swing.*;

public class Gnoll extends Monster{

    @Override
    protected String getImagePath() {
        return "/gnoll_icon_30x30.png";
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
        return 40;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Гнолл";
        frequencyMove = 0.7f;
        Power = 50;
        FeelRadius = 5;
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.4f;
        buffings = new Buffing[]{stun};
        drop.addResource(ResourceType.Meat, 7);
        drop.addRandomSupply(HealthPotion.class, 1, 3, 0.3f);
        drop.addRandomSupply(StaminaPotion.class, 1, 3, 0.3f);
    }

    public Gnoll(){

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