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
        GenerateMonsters(Cobold.class, 3);
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
            Chest chest = new Chest(point.X, point.Y, this);
            chest.drop.addRandomResource(ResourceType.Coins, 5000, 15000);
            chest.drop.addRandomResource(ResourceType.Ore, 10000, 50000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 10000, 50000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 10000, 50000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 10, 90, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 10, 90, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 1, 20, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 1, 15, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 7, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 7, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chest.drop.addRandomSupply(Bandage.class, 1, 5, 0.1f);
            chest.drop.addRandomSupply(HealthPotion.class, 1, 3, 0.05f);
            chest.drop.addRandomSupply(StaminaPotion.class, 1, 3, 0.05f);
            chests.add(chest);
        }
    }
}