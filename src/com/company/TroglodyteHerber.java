package com.company;

import javax.swing.*;

public class TroglodyteHerber extends Monster{

    private final float healPower = 10;
    private final float healRadius = 2;

    @Override
    protected String getImagePath() {
        return "/troglodyte_herber_icon_30x30.png";
    }

    @Override
    public void Act() {
        var nearestWounded = getNearestWounded();
        if(nearestWounded != null){
            if(healRadius >= map.DistanceBetweenObjects(this, nearestWounded)){
                Heal(nearestWounded);
            }
            else {
                map.MoveTowardObject(this, nearestWounded);
            }
            return;
        }
        if(map.DistanceToPlayer(this) <= FeelRadius()){
            var nearestAlly = getNearestAlly();
            if(nearestAlly != null) {
                map.MoveTowardObject(this, nearestAlly);
                return;
            }
        }
        RandomMove();
    }

    @Override
    public float getMaxHealth() {
        return 25;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Троглодит-травник";
        frequencyMove = 0.6f;
        Power = 25;
        FeelRadius = 9;
        isBlind = true;
        drop.addRandomResource(ResourceType.Coins, 10, 35, 0.3f);
        drop.addRandomEquipment(ResourceType.SageLeaf, 1, 2, 0.2f);
        drop.addRandomEquipment(ResourceType.PlantainLeaf, 1, 2, 0.2f);
        drop.addRandomEquipment(ResourceType.MushroomCap, 1, 4, 0.5f);
    }

    public TroglodyteHerber(){

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

    public void Heal(Monster monster){
        monster.Health = Math.min(healPower + monster.Health, monster.getMaxHealth());
        map.addToLog(Name + " лечит раненого союзника.");
    }

    private Monster getNearestWounded(){
        Monster nearestWounded = null;
        for(var monster:map.getMonsters()){
            if(((Monster)monster).IsWounded()){
                if((nearestWounded == null || map.DistanceBetweenObjects(this, monster) < map.DistanceBetweenObjects(this, nearestWounded)) && map.DistanceBetweenObjects(this, monster) <= FeelRadius()){
                    nearestWounded = (Monster) monster;
                }
            }
        }
        return nearestWounded;
    }

    private Monster getNearestAlly(){
        Monster nearest = null;
        for(var monster:map.getMonsters()){
            if(monster != this && (nearest == null || map.DistanceBetweenObjects(this, monster) < map.DistanceBetweenObjects(this, nearest))) {
                nearest = (Monster) monster;
            }
        }
        return nearest;
    }


}
