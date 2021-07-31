package com.company;

import javax.swing.*;

public class WhereBear extends Monster{

    @Override
    protected String getImagePath() {
        return "/wherebear_icon_30x30.png";
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
        return 60;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Медведь-оборотень";
        frequencyMove = 0.7f;
        Power = 40;
        FeelRadius = 5;
        Lycanthrope = true;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 7;
        bleed.Chance = 0.4f;
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 2;
        stun.Chance = 0.4f;
        buffings = new Buffing[]{bleed, stun};
        drop.addResource(ResourceType.Fur, 4);
    }

    public WhereBear(){

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