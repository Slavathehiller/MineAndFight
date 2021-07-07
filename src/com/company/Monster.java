package com.company;

import javax.swing.*;
import java.util.Objects;

public abstract class Monster implements IDisplayable, IFighter {

    public int X;
    public int Y;
    public String Name;
    public ImageIcon image;
    public float Health;
    public float Power;
    protected IMap map;
    abstract protected String getImagePath();
    protected float frequencyMove = 1;

    protected void init(){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
        Health = getMaxHealth();
    }

    public Monster(){
        init();
    }

    public Monster(IMap map, int x, int y){
        init();
        this.X = x;
        this.Y = y;
        this.map = map;
    }
    public abstract void Act();

    public abstract float getMaxHealth();

    protected boolean CanMove() {
        var r = Math.random();
        System.out.println("r = " + r);
        return Math.random() < frequencyMove;
    }

    public boolean IsPlayerNear(){
        return (X == map.getPlayer().X + 1 || X == map.getPlayer().X - 1) && Y == map.getPlayer().Y
                || ((Y == map.getPlayer().Y - 1 || Y == map.getPlayer().Y + 1) && X == map.getPlayer().X);
    }

    public void AttackIfPlayerNear(){
        if(IsPlayerNear()){
            map.Attack(this, map.getPlayer());
        }
    }

    public abstract String getToolTip();

    @Override
    public float getHealth() {
        return Health;
    }

    @Override
    public void changeHealth(float health) {
        Health += health;
    }

    @Override
    public float getPower() {
        return Power;
    }

    @Override
    public String getName() {
        return Name;
    }
    @Override
    public int getFighterType() {
        return CollisionObjectTypes.Monster;
    }


}
