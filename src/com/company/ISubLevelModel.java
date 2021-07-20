package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface ISubLevelModel {

    Player getPlayer();
    Integer getMaxX();
    Integer getMaxY();
    String getLog();
    void ClearLog();
    ArrayList<IDisplayable> getMonsters();
    ArrayList<IDisplayable> getObstacles();
    ArrayList<ArrayList<IDisplayable>> getDisplayableObjects();
    void movePlayer(int direction);
    boolean LevelBossIsDead = false;
    void tick() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    boolean getPlayerIsDead();
    boolean getWantToExit();
    void setWantToExit(boolean value);
    boolean getLevelBossHasDied();
    Monster getLevelBoss();
    Boolean getMessages(int index);
    ArrayList<String> getCustomMessages();

}