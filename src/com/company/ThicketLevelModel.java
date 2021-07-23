package com.company;

import java.lang.reflect.InvocationTargetException;

public abstract class ThicketLevelModel extends BaseLevelModel{

    public ThicketLevelModel(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super(player);
    }

    public ThicketLevelModel() {
        super();
    }

    public void GenerateObstacles() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        DisplayableObjects.add(obstacles);
        GenerateObstacles(ForestObstacle.class, getObstacleCount());
    }
}
