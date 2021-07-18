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
    void tick() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    boolean getPlayerIsDead();
    Boolean getMessages(int index);
    ArrayList<String> getCustomMessages();

}