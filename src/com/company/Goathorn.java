package com.company;

import javax.swing.*;

public class Goathorn extends Monster{

    private int RamRadius = 3;
    private int MinRamRadius = 2;

    @Override
    protected String getImagePath() {
        return "/goathorn_icon_30x30.png";
    }

    @Override
    public void Act() {
        if (CanMove()) {
            if (AttackIfPlayerNear()) {
                return;
            }
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                if(RamRadius >= map.DistanceToPlayer(this) && map.DistanceToPlayer(this) > MinRamRadius && map.CheckPathToward(new Point(this.X, this.Y), new Point(map.getPlayer().X, map.getPlayer().Y))){
                    if(Math.random() < 0.7 && !map.getPlayerIsMasked()){
                        RamPlayer();
                        map.addToLog("Козлорог таранит игрока");
                        return;
                    }
                }
                MoveToPlayer();
                return;
            }
            RandomMove();
        }
    }

    @Override
    public float getMaxHealth() {
        return 25;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    private void RamPlayer(){
        var nearPlayerPoint = map.NearestPointToward(new Point(X, Y), new Point(map.getPlayer().X, map.getPlayer().Y));
        Move(nearPlayerPoint.X, nearPlayerPoint.Y);
        AttackIfPlayerNear();
        if(Math.random() < 0.6){
            map.getPlayer().setBattleBuff(BattleBuffType.Stun, 2);
        }
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Козлорог";
        frequencyMove = 0.8f;
        Power = 20;
        FeelRadius = 4;
        drop.addResource(ResourceType.Leather, 3);
    }

    public Goathorn(){

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