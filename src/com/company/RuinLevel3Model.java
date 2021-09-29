package com.company;

public class RuinLevel3Model extends RuinLevelModel implements ISubLevelModel, IMap{

    public RuinLevel3Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateLevelBoss(new TrollWarrior());
    }

    @Override
    public void GenerateChests() {
        DisplayableObjects.add(chests);
        for(var i = 0; i < 2; i++){
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y, this);
            chest.drop.addRandomResource(ResourceType.Coins, 10, 150);
            chest.drop.addRandomResource(ResourceType.Ore, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 1, 5, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 1, 5, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 1, 2, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 1, 2, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 1, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 1, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chests.add(chest);
        }
    }
}