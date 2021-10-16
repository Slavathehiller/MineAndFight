package com.company;

public class RuinLevel2Model extends RuinLevelModel implements ISubLevelModel, IMap{

    public RuinLevel2Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateMonsters(OrcWarrior.class, 3);
        GenerateMonsters(OrcBerserk.class, 1);
        GenerateMonsters(OrcBeastmaster.class, 2);
        GenerateLevelBoss(new OrcLeader());
    }

    @Override
    public void GenerateChests(){
        DisplayableObjects.add(chests);
        for(var i = 0; i < 3; i++){
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y, this);
            chest.drop.addRandomResource(ResourceType.Coins, 200, 700);
            chest.drop.addRandomResource(ResourceType.Ore, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 2, 12, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 2, 12, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 2, 5, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 5, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chest.drop.addRandomSupply(Bandage.class, 1, 3, 0.1f);
            chests.add(chest);
        }
    }
}
