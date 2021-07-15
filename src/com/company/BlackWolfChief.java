package com.company;

import javax.swing.*;

public class BlackWolfChief extends Monster{

    public BlackWolfChief(IMap map, int x, int y){
        super(map, x, y);
        Name = "Черный волк вожак";
        frequencyMove = 0.1f;
        Power = 20;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 5;
        bleed.Chance = 0.6f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 4);
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

    @Override
    protected String getImagePath() {
        return "/wolf_chief_icon_30x30.png";
    }

    @Override
    public void Act() {
        if(CanMove()){
            if(AttackIfPlayerNear()){
                return;
            }
            RandomMove();
        }
    }

    @Override
    public float getMaxHealth() {
        return 35;
    }

}
