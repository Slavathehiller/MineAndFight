package com.company;

import javax.swing.*;

public class Cobold extends Monster{

    @Override
    protected String getImagePath() {
        return "/cobold_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
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
        return 40;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public int FeelRadius() {
        return FeelRadius;
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Пещерный кобольд";
        frequencyMove = 0.9f;
        Power = 50;
        FeelRadius = 6;
        HideRadius = 2;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 3;
        poison.Chance = 0.4f;
        buffings = new Buffing[]{poison};
        drop.addRandomResource(ResourceType.Coins, 100, 300);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 1, 0.2f);
    }

    public Cobold(){

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