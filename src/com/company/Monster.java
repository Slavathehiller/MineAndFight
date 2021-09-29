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
    public float RangedPower = 0;
    public float RangedDistance = 0;
    protected IMap map;
    abstract protected String getImagePath();
    protected float frequencyMove = 1;
    public Buffing[] buffings;
    protected Drop drop;
    protected int FeelRadius;
    protected boolean Lycanthrope = false;
    protected float HideRadius = 0;
    protected boolean Giant = false;
    protected boolean isBlind = false;
    protected boolean Berserk = false;
    protected boolean Armed = false;
    protected boolean Regeneration = false;
    protected boolean PreventRegeneration = false;


    public void init(IMap map, int x, int y){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
        Health = getMaxHealth();
        this.X = x;
        this.Y = y;
        this.map = map;
        drop = new Drop();
    }

    public boolean getRegeneration(){
        return Regeneration;
    }

    public void setPreventRegeneration(){
        Regeneration = false;
    }

    public Drop getDrop(){
        return drop;
    }

    public Monster(){
    }

    public Monster(IMap map, Point point){
        init(map, point.X, point.Y);

    }

    public Monster(IMap map, int x, int y){
        init(map, x, y);
    }

    public boolean getLycanthrope(){
        return Lycanthrope;
    }

    public abstract void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;

    public void PostAct(){
        if(Regeneration && Health > 0 && Health < getMaxHealth()){
            var regRate = Math.min(getMaxHealth() / 10f, getMaxHealth() - Health);
            regRate = Math.round(regRate * 100f) / 100f;
            Health += regRate;
            map.addToLog(Name + " регенерирует " + regRate + " здоровья.");
        }
    }

    @Override
    public abstract float getMaxHealth();

    protected boolean CanMove() {
        var r = Math.random();
        System.out.println("r = " + r);
        return Math.random() < frequencyMove;
    }

    public boolean IsWounded(){
        return getHealth() < getMaxHealth();
    }

    public boolean CheckIfDie(){
        return true;
    }

    public int FeelRadius(){
        if(map.getPlayerIsMasked())
            return 0;
        else {
            if(isBlind)
                return FeelRadius;
            else
                return Math.max((FeelRadius - (map.getPlayer().Masked_lvl - 1) / 10), 1);
        }
    }

    @Override
    public boolean getGiant(){
        return Giant;
    }

    @Override
    public boolean getBerserk(){
        return Berserk;
    }

    @Override
    public boolean getArmed(){
        return Armed;
    }

    @Override
    public boolean getVisible(){
        if(map.getPlayer().VisionLimit < map.DistanceToPlayer(this)){
            return false;
        }
        if(HideRadius == 0){
            return true;
        }
        else {
            return map.DistanceToPlayer(this) <= HideRadius;
        }
    }

    public boolean IsPlayerNear(){
        return (X == map.getPlayer().X + 1 || X == map.getPlayer().X - 1) && Y == map.getPlayer().Y
                || ((Y == map.getPlayer().Y - 1 || Y == map.getPlayer().Y + 1) && X == map.getPlayer().X);
    }

    public void MoveToPlayer(){
        map.MoveToward(this, map.getPlayer().X, map.getPlayer().Y);
    }

    public boolean AttackIfPlayerNear(){
        if(IsPlayerNear() && !map.getPlayerIsMasked()){
            map.Attack(this, map.getPlayer());
            return true;
        }
        return false;
    }

    public boolean ShootIfPlayerInRange(String actionMessage){
        if(map.DistanceToPlayer(this) <= RangedDistance){
            map.RangedAttack(this, map.getPlayer(), actionMessage);
            return true;
        }
        return false;
    }

    public String getToolTip() {
        float displHealth = Math.round(Health * 10) / 10f;
        return Name + ". Сила " + (int)getPower() + ". Здоровье: " + displHealth + "/" + (int)getMaxHealth();
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

    @Override
    public float getMasked() {
        return 0;
    }

    @Override
    public float getRangedPower() {
        return 0;
    }

}
