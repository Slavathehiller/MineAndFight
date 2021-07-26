package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public abstract class Monster implements IMovableDisplayable, IFighter {

    public int X;
    public int Y;
    public String Name;
    public ImageIcon image;
    public float Health;
    public float Power;
    protected IMap map;
    abstract protected String getImagePath();
    protected float frequencyMove = 1;
    public Buffing[] buffings;
    protected Drop drop;

    public void init(IMap map, int x, int y){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
        Health = getMaxHealth();
        this.X = x;
        this.Y = y;
        this.map = map;
        drop = new Drop();
    }

    public Drop getDrop(){
        return drop;
    }

    public Monster(){
    }

    public Monster(IMap map, int x, int y){
        init(map, x, y);

    }
    public abstract void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;

    public abstract float getMaxHealth();

    protected boolean CanMove() {
        var r = Math.random();
        System.out.println("r = " + r);
        return Math.random() < frequencyMove;
    }

    public abstract int FeelRadius();

    public boolean IsPlayerNear(){
        return (X == map.getPlayer().X + 1 || X == map.getPlayer().X - 1) && Y == map.getPlayer().Y
                || ((Y == map.getPlayer().Y - 1 || Y == map.getPlayer().Y + 1) && X == map.getPlayer().X);
    }

    public void MoveToPlayer(){
        map.MoveToward(this, map.getPlayer().X, map.getPlayer().Y);
    }

    public boolean AttackIfPlayerNear(){
        if(IsPlayerNear()){
            map.Attack(this, map.getPlayer());
            return true;
        }
        return false;
    }

    public String getToolTip() {
        return Name + ". Сила " + (int)getPower() + ". Здоровье: " + Health + "/" + getMaxHealth();
    }

    protected void RandomMove(){
        int countOfTries = 0;
        int nextX;
        int nextY;
        do {
            nextX = X;
            nextY = Y;
            double a = Math.floor(Math.random() * 4);
            if (a == 0)
                nextY -= 1;
            if (a == 1)
                nextY += 1;
            if (a == 2)
                nextX -= 1;
            if (a == 3)
                nextX += 1;
            System.out.println("nextX: " + nextX + " nextY: " + nextY);
            countOfTries++;
        }
        while (!map.canMonsterMove(nextX, nextY) && countOfTries < 50);
        if(countOfTries >= 50){
            return;
        }
        X = nextX;
        Y = nextY;
    }

    public void Die(){
        map.getMonsters().remove(this);
    }

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

    @Override
    public Integer getX() {
        return X;
    }

    @Override
    public Integer getY() {
        return Y;
    }

    @Override
    public void Move(int x, int y) {
        X = x;
        Y = y;
    }

}
