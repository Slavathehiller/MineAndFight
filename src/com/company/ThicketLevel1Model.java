package com.company;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ThicketLevel1Model implements ISubLevelModel, IMap{
    Player player;
    private int maxX = 40;
    private int maxY = 30;
    private int maxObstacles = 20;
    private int minObstacles = 12;
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<ArrayList<IDisplayable>> DisplayableObjects = new ArrayList<>();

    public ThicketLevel1Model(Player player){
        this.player = player;
        GenerateMonsters();
        GenerateObstacles();
        ArrayList<IDisplayable> iPlayers = new ArrayList<>();
        iPlayers.add(player);
        DisplayableObjects.add(iPlayers);

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Integer getMaxX() {
        return maxX;
    }

    @Override
    public Integer getMaxY() {
        return maxY;
    }

    @Override
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    @Override
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public ArrayList<ArrayList<IDisplayable>> getDisplayableObjects() {
        return DisplayableObjects;
    }

    @Override
    public void movePlayer(int direction) {
        int x = player.X;
        int y = player.Y;

        if (direction == Direction.UP) {
            y -= 1;
        }
        if (direction == Direction.DOWN) {
            y += 1;
        }
        if (direction == Direction.LEFT) {
            x -= 1;
        }
        if (direction == Direction.RIGHT) {
            x += 1;
        }
        if(TryMove(x, y)){
            player.X = x;
            player.Y = y;
        }
    }

    @Override
    public void tick() {
        ActMonsters();
    }

    private void ActMonsters(){
        for(var monster:monsters){
            monster.Act();
        }
    }

    private Boolean TryMove(int x, int y){
        var object = ObjectAt(x, y);
        if(object == null){
            return true;
        }
        else{
            return false;
        }
    }

    private IDisplayable ObjectAt(int x, int y){
        if(x > maxX - 1 || x < 0 || y > maxY - 1 || y < 0){
            return new Edge();
        }
        for(var objects:DisplayableObjects){
            for(var object:objects){
                if(x == object.getX() && y == object.getY()){
                    return object;
                }
            }
        }
        return null;
    }

    public void GenerateObject(Class _class) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var object = _class.getDeclaredConstructor().newInstance();
    }

    private int getObstacleCount(){
        return (int) Math.round(Math.random() * ( maxObstacles - minObstacles )) + minObstacles;
    }

    public void GenerateObstacles(){
        ArrayList<IDisplayable> iObstacles = new ArrayList<>();
        for(var i = 0; i < getObstacleCount(); i++){
            int x = (int) Math.max(Math.round(Math.random() * maxX - 1), 0);
            int y = (int) Math.max(Math.round(Math.random() * maxY - 1), 0);
            Obstacle obstacle = new ForestObstacle(this, x, y);
            obstacles.add(obstacle);
            iObstacles.add(obstacle);
        }
        DisplayableObjects.add(iObstacles);
    }

    public void GenerateMonsters(){
        ArrayList<IDisplayable> iMonsters = new ArrayList<>();
        for(var i = 0; i < 3; i++){
            int x = (int) Math.max(Math.round(Math.random() * maxX - 1), 0);
            int y = (int) Math.max(Math.round(Math.random() * maxY - 1), 0);
            Monster monster = new BlackWolf(this, x, y);
            monsters.add(monster);
            iMonsters.add(monster);
        }
        DisplayableObjects.add(iMonsters);
    }

    @Override
    public boolean canMonsterMove(int x, int y) {
        return ObjectAt(x, y) == null;
    }
}
