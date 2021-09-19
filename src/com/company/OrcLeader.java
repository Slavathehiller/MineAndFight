package com.company;

import javax.swing.*;
import java.util.Objects;

public class OrcLeader extends Monster {

    private boolean BoarIsDead = false;
    ImageIcon WithBoarImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/orc_leader_on_boar_icon_30x30.png")));
    ImageIcon WithoutBoarImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/orc_leader_icon_30x30.png")));

    @Override
    protected String getImagePath() {
        return "/orc_leader_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Вожак орков";
        frequencyMove = 0.9f;
        Power = 35;
        FeelRadius = 5;
        Armed = true;
        drop.addResource(ResourceType.Leather, 5);
        drop.addResource(ResourceType.Meat, 8);
        drop.addRandomResource(ResourceType.Coins, 200, 1000);
    }

    @Override
    public void Act() {
        if(!BoarIsDead){
            AttackIfPlayerNear();
        }
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
        return 100;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public OrcLeader() {

    }

    @Override
    public ImageIcon getImage() {
        if(BoarIsDead){
            return WithoutBoarImage;
        }
        else {
            return WithBoarImage;
        }
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

    @Override
    public boolean CheckIfDie(){
        if(!BoarIsDead){
            BoarIsDead = true;
            Health = getMaxHealth();
            map.addToLog("Ездовой кабан лидера орков погибает.");
            return false;
        }
        return true;
    }
}