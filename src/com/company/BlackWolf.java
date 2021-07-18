package com.company;

import javax.swing.*;

public class BlackWolf extends Monster{

    public PointOfInterest_Place CallToPack;

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    @Override
    public void Act() {
        try {
            if (CanMove()) {
                if (AttackIfPlayerNear()) {
                    return;
                }
                if (FeelRadius() >= map.DistanceToPlayer(this)) {
                    MoveToPlayer();
                    return;
                }
                if (CallToPack.IsActive()) {
                    map.MoveToward(this, CallToPack.X, CallToPack.Y);
                    return;
                }
                RandomMove();
            }
        }finally {
            CallToPack.CountDown();
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

    @Override
    public int FeelRadius() {
        return 7;
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Черный волк";
        frequencyMove = 0.8f;
        Power = 10;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 3;
        bleed.Chance = 0.4f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 3);
        CallToPack = new PointOfInterest_Place(20);
    }

    public BlackWolf(){

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
