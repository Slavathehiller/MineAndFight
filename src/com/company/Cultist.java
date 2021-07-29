package com.company;

import javax.swing.*;

public class Cultist extends Monster{

    @Override
    protected String getImagePath() {
        return "/cultist_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (CanMove()) {
            if (AttackIfPlayerNear()) {
                return;
            }
            if(FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
                return;
            }
            else
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
        return 4;
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Культист";
        frequencyMove = 0.2f;
        Power = 10;
        drop.addRandomResource(ResourceType.Coins, 30, 70, 0.7f);
    }

    public Cultist(){

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
}