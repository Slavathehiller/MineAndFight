package com.company;

import javax.swing.*;

public class GiantHyena extends Monster{

    @Override
    protected String getImagePath() {
        return "/giant_hyena_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
            if (FeelRadius() >= map.DistanceToPlayer(this) || map.getPlayer().getHealth() < map.getPlayer().getMaxHealth()) {
                MoveToPlayer();
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

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Гигантская гиена";
        frequencyMove = 0.8f;
        Power = 60;
        FeelRadius = 10;
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 2;
        bleed.Power = 4;
        bleed.Chance = 0.5f;
        buffings = new Buffing[]{bleed};
        drop.addResource(ResourceType.Fur, 15);
    }

    public GiantHyena(){

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