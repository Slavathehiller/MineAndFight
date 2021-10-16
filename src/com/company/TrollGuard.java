package com.company;

import javax.swing.*;

public class TrollGuard extends Monster {

    IDisplayable guardedObject;
    float maxDistanceToGuardedObject = 2;

    @Override
    protected String getImagePath() {
        return "/troll_guard_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Тролль-страж";
        frequencyMove = 0.9f;
        Power = 50;
        FeelRadius = 4;
        Regeneration = true;
        drop.addRandomResource(ResourceType.Stone, 3000, 6000, 0.7f);
    }

    public void setGuardedObject(IDisplayable _guardedObject){
        guardedObject = _guardedObject;
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if(map.DistanceBetweenObjects(guardedObject, this) > maxDistanceToGuardedObject){
            map.MoveTowardObject(this, guardedObject);
            return;
        }
        if (CanMove()) {
            if (FeelRadius() >= map.DistanceToPlayer(this) && map.DistanceBetweenObjects(guardedObject, this) < maxDistanceToGuardedObject) {
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

    public TrollGuard() {

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