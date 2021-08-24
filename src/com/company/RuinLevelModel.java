package com.company;

public abstract class RuinLevelModel extends BaseLevelModel{

    public RuinLevelModel(Player player){
        super(player);
    }

    public RuinLevelModel() {
        super();
    }

    public void GenerateObstacles(){
        DisplayableObjects.add(obstacles);
        GenerateObstacles(RuinObstacle.class, getObstacleCount());
    }
}

