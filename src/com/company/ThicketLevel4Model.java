package com.company;

public class ThicketLevel4Model extends ThicketLevelModel {

    public ThicketLevel4Model(Player player) {
        super(player);
    }

    @Override
    public void GenerateMonsters() {
        super.GenerateMonsters();
        GenerateMonsters(Troglodyte.class, 4);
        GenerateMonsters(TroglodyteHerber.class, 2);
        GenerateLevelBoss(new Cyclope());
    }

    @Override
    public void GenerateObstacles(){
        DisplayableObjects.add(obstacles);
        GenerateObstacles(CaveObstacle.class, getObstacleCount());
    }

    @Override
    public void GenerateChests() {
        DisplayableObjects.add(chests);
        for (var i = 0; i < 7; i++) {
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y);
            chest.drop.addRandomResource(ResourceType.Coins, 170, 700);
            chest.drop.addRandomResource(ResourceType.Ore, 700, 1000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 700, 1000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 700, 1000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 3, 9, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 3, 9, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 3, 6, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 3, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 4, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 4, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 4, 0.005f);
            chests.add(chest);
        }
    }
}