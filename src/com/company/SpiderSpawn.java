package com.company;

import javax.swing.*;
import java.util.Objects;

public class SpiderSpawn extends Monster{

    private ImageIcon[] imageIcons;

    @Override
    protected String getImagePath() {
        return "/spider_spawn_icon_3_30x30.png";
    }

    @Override
    public void Act() {
        var result = false;
        for(int i = 0; i < GetNumberAttack(); i++) {
            result = AttackIfPlayerNear();
        }
        if(result)
            return;
        if(CanMove()){
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                MoveToPlayer();
            }
            else
                RandomMove();
        }

    }

    public int GetNumberAttack(){
        return (int)Math.ceil(getHealth() / 9);
    }


    @Override
    public float getMaxHealth() {
        return 27;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Паучий выводок";
        frequencyMove = 0.73f;
        Power = 7;
        FeelRadius = 4;
        Buffing poison = new Buffing();
        poison.BuffType = BattleBuffType.Poison;
        poison.Duration = 2;
        poison.Chance = 0.5f;
        buffings = new Buffing[]{poison};
        drop.addResource(ResourceType.Fiber, 6);
        imageIcons = new ImageIcon[]{new ImageIcon(Objects.requireNonNull(getClass().getResource("/spider_spawn_icon_1_30x30.png"))),
                                    new ImageIcon(Objects.requireNonNull(getClass().getResource("/spider_spawn_icon_2_30x30.png"))),
                                    new ImageIcon(Objects.requireNonNull(getClass().getResource("/spider_spawn_icon_3_30x30.png")))
        };
    }

    public SpiderSpawn(){

    }

    @Override
    public ImageIcon getImage() {
        return imageIcons[Math.max(GetNumberAttack() - 1, 0)];
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
