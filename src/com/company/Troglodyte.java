package com.company;

import javax.swing.*;

public class Troglodyte extends Monster{

    @Override
    protected String getImagePath() {
        return "/troglodyte_icon_30x30.png";
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
        return 45;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Троглодит";
        frequencyMove = 0.8f;
        Power = 60;
        FeelRadius = 5;
        isBlind = true;
        Armed = true;
        drop.addRandomResource(ResourceType.Coins, 25, 70, 0.3f);
        drop.addRandomResource(ResourceType.Ore, 100, 200, 0.5f);
        drop.addRandomResource(ResourceType.Stone, 100, 200, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 1, 0.2f);
    }

    public Troglodyte(){

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
