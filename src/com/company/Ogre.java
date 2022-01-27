package com.company;

import javax.swing.*;

public class Ogre extends Monster{


    @Override
    protected String getImagePath() {
        return "/ogre_icon_30x30.png";
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
        return 100;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Огр";
        frequencyMove = 0.6f;
        Power = 90;
        FeelRadius = 5;
        Giant = true;
        drop.addRandomResource(ResourceType.Coins, 5000, 50000);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 8, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 1, 8, 0.25f);
        drop.addRandomSupply(HealthPotion.class, 5,15, 0.7f);
        drop.addRandomSupply(StaminaPotion.class, 5,15, 0.7f);
        Buffing stun = new Buffing();
        stun.BuffType = BattleBuffType.Stun;
        stun.Duration = 1;
        stun.Chance = 0.7f;
        buffings = new Buffing[]{stun};
    }

    public Ogre(){

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
