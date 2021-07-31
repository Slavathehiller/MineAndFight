package com.company;

import javax.swing.*;

public class SwineMar extends Monster{

    @Override
    protected String getImagePath() {
        return "/swinemar_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (CanMove()) {
            if (AttackIfPlayerNear()) {
                return;
            }
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
        Name = "Свиноморф";
        frequencyMove = 0.7f;
        Power = 30;
        FeelRadius = 4;
        Lycanthrope = true;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 5;
        bleed.Chance = 0.6f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Leather, 3);
    }

    public SwineMar(){

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