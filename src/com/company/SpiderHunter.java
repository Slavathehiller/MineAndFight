package com.company;

import javax.swing.*;

public class SpiderHunter extends Monster{

    private final int LeapRange = 3;

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
                if(LeapRange >= map.DistanceToPlayer(this) && map.DistanceToPlayer(this) > 2.1f){
                    LeapToPlayer();
                    AttackIfPlayerNear();
                    map.addToLog("Паук-охотник прыгает к своей добыче.");
                }
                else {
                    MoveToPlayer();
                }
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

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Паук-охотник";
        frequencyMove = 0.9f;
        Power = 25;
        FeelRadius = 6;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 3;
        poison.Chance = 0.6f;
        buffings = new Buffing[]{poison};
    }

    public SpiderHunter(){

    }

    private void LeapToPlayer(){
        var point = map.GenerateFreeCordsWithin(new Point(map.getPlayer().X, map.getPlayer().Y), 1);
        if(point != null){
            Move(point.X, point.Y);
        }
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