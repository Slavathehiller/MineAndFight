package com.company;

import java.util.ArrayList;

public interface ISubLevelModel {

    Player getPlayer();
    Integer getMaxX();
    Integer getMaxY();
    ArrayList<Monster> getMonsters();
    ArrayList<ArrayList<IDisplayable>> getDisplayableObjects();

}
