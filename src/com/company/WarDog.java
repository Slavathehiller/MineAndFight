package com.company;

import javax.swing.*;

public class WarDog extends Monster{

    @Override
    protected String getImagePath() {
        return "/dog_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
            MoveToPlayer();
        }
    }

    @Override
    public float getMaxHealth() {
        return 15;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Боевой пёс";
        frequencyMove = 1f;
        Power = 15;
        FeelRadius = 15;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 2;
        bleed.Power = 2;
        bleed.Chance = 0.5f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 1);
    }

    public WarDog(){

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