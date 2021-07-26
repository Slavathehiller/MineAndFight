package com.company;

import javax.swing.*;

public class SpiderHunter extends Monster{

    @Override
    protected String getImagePath() {
        return "/spider_hunter_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if(CanMove()){
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
                return;
            }
            RandomMove();
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

    @Override
    public int FeelRadius() {
        return 6;
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Паук-охотник";
        frequencyMove = 1f;
        Power = 20;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 3;
        poison.Chance = 0.6f;
        buffings = new Buffing[]{poison};
    }

    public SpiderHunter(){

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