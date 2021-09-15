package com.company;

import javax.swing.*;

public class OrcBerserk extends Monster {

    @Override
    protected String getImagePath() {
        return "/orc_berserk_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Орк-берсерк";
        Berserk = true;
        frequencyMove = 0.9f;
        Power = 25;
        FeelRadius = 4;
        Armed = true;
        drop.addResource(ResourceType.Fur, 3);
        drop.addRandomResource(ResourceType.Coins, 10, 50, 0.7f);
    }

    @Override
    public void Act() {
        AttackIfPlayerNear();
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
        return 30;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public OrcBerserk() {

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