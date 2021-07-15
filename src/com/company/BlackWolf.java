package com.company;

import javax.swing.*;

public class BlackWolf extends Monster{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
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
        return 20;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public BlackWolf(IMap map, int x, int y){
        super(map, x, y);
        Name = "Черный волк";
        frequencyMove = 0.1f;
        Power = 10;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 3;
        bleed.Chance = 0.4f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 3);
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
