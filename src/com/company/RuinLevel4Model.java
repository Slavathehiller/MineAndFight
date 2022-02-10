package com.company;

public class RuinLevel4Model extends RuinLevelModel implements ISubLevelModel, IMap{

    public RuinLevel4Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateLevelBoss(new Ogre());
        for(var i = 0; i < 2; i++){
            Point point = GenerateFreeCordsWithin(new Point(1, 1), 5);
            if(point != null) {
                GenerateMonster(GiantHyena.class, point.X, point.Y);
            }
        }
        GenerateMonsters(GiantHyena.class, 2);
        GenerateMonsters(Gnoll.class, 5);
    }

    @Override
    public void GenerateChests() {
        DisplayableObjects.add(chests);
        for(var i = 0; i < 7; i++){
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
            chest.drop.addRandomSupply(Bandage.class, 1, 20, 0.1f);
            chest.drop.addRandomSupply(HealthPotion.class, 1, 10, 0.05f);
            chest.drop.addRandomSupply(StaminaPotion.class, 1, 10, 0.05f);
            chests.add(chest);
        }
    }
}