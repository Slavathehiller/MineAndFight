package com.company;

import java.lang.reflect.InvocationTargetException;

public abstract class ThicketLevelModel extends BaseLevelModel{

    public ThicketLevelModel(Player player){
        super(player);
    }

    public ThicketLevelModel() {
        super();
    }

    public void GenerateObstacles(){
        DisplayableObjects.add(obstacles);
        GenerateObstacles(ForestObstacle.class, getObstacleCount());
    }
}
