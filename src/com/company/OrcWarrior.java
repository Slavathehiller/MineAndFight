package com.company;

import javax.swing.*;

public class OrcWarrior extends Monster {

    @Override
    protected String getImagePath() {
        return "/orc_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Орк-воин";
        frequencyMove = 0.7f;
        Power = 20;
        FeelRadius = 4;
        Armed = true;
        drop.addResource(ResourceType.Fur, 3);
        drop.addRandomResource(ResourceType.Coins, 10, 50, 0.7f);
        Buffing bleed = new Buffing();
        bleed.BuffType = BattleBuffType.Bleed;
        bleed.Duration = 3;
        bleed.Power = 2;
        bleed.Chance = 0.6f;
        buffings = new Buffing[]{bleed};
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
        return 30;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public OrcWarrior() {

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
