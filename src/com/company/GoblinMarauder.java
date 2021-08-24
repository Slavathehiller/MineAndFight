package com.company;

import javax.swing.*;

public class GoblinMarauder extends Monster {

    @Override
    protected String getImagePath() {
        return "/goblin_marauder_icon_30x30.png";
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
        return 15;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Гоблин-марадер";
        frequencyMove = 0.9f;
        Power = 9;
        FeelRadius = 6;
        drop.addResource(ResourceType.Fur, 1);
        drop.addRandomResource(ResourceType.Coins, 5, 20, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 1, 0.02f);
    }

    public GoblinMarauder() {

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