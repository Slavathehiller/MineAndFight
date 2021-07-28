package com.company;

import javax.swing.*;

public class GiantSpider extends Monster{

    public PointOfInterest_Place WebWarning;
    private Web web;

    @Override
    protected String getImagePath() {
        return "/giant_spider_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if(CanMove()){
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
                return;
            }
            if(isWebWarning()){
                map.MoveToward(this, WebWarning.X, WebWarning.Y);
            }
        }
    }

    public void setWeb(Web web){
        this.web = web;
    }

    public void setWebWarning(Web web){
        WebWarning = new PointOfInterest_Place(10000000);
        WebWarning.Start(web.X, web.Y);
    }

    public void ClearWebWarning(){
        WebWarning = null;
    }

    private boolean isWebWarning(){
        return WebWarning != null;
    }

    @Override
    public void Die(){
        web.setMaster(null);
        super.Die();
    }

    @Override
    public float getMaxHealth() {
        return 25;
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
        Name = "Гигантский паук";
        frequencyMove = 0.9f;
        Power = 15;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 5;
        poison.Chance = 0.6f;
        buffings = new Buffing[]{poison};
        drop.addResource(ResourceType.Fiber, 5);
    }

    public GiantSpider(){

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