package com.company;

import java.util.ArrayList;

public interface ISubLevelModel {

    Player getPlayer();
    Integer getMaxX();
    Integer getMaxY();
    String getLog();
    void ClearLog();
    ArrayList<Monster> getMonsters();
    ArrayList<Obstacle> getObstacles();
    ArrayList<ArrayList<IDisplayable>> getDisplayableObjects();
    void movePlayer(int direction);
    void tick();
    boolean getPlayerIsDead();
    Boolean getMessages(int index);

}