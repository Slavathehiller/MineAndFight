package com.company;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ThicketLevel1Model implements ISubLevelModel, IMap{
    Player player;
    private int maxX = 40;
    private int maxY = 30;
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<ArrayList<IDisplayable>> DisplayableObjects = new ArrayList<>();

    public ThicketLevel1Model(Player player){
        this.player = player;
        GenerateMonsters();
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
    public ArrayList<ArrayList<IDisplayable>> getDisplayableObjects() {
        return DisplayableObjects;
    }

    @Override
    public void movePlayer(int direction) {
        if (direction == Direction.UP && player.Y > 0) {
            player.Y -= 1;
        }
        if (direction == Direction.DOWN && player.Y < maxY - 1) {
            player.Y += 1;
        }
        if (direction == Direction.LEFT && player.X > 0) {
            player.X -= 1;
        }
        if (direction == Direction.RIGHT && player.X < maxX - 1) {
            player.X += 1;
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
    public boolean canIMove(int x, int y) {
        return x < maxX && x >= 0 && y >= 0 && y < maxY;
    }
}
