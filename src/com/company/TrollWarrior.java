package com.company;

import javax.swing.*;

public class TrollWarrior extends Monster{


    @Override
    protected String getImagePath() {
        return "/troll_icon_30x30.png";
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
        return 50;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Тролль-воин";
        frequencyMove = 0.7f;
        Power = 45;
        FeelRadius = 4;
        Regeneration = true;
        drop.addRandomResource(ResourceType.Stone, 2500, 6000);
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.4f;
        buffings = new Buffing[]{stun};
    }

    public TrollWarrior(){

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