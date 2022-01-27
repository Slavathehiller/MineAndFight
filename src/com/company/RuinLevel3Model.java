package com.company;

public class RuinLevel3Model extends RuinLevelModel implements ISubLevelModel, IMap{

    public RuinLevel3Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateMonsters(TrollWarrior.class, 3);
        GenerateMonsters(TrollThrower.class, 2);
        GenerateLevelBoss(new TrollPatriarch());
        for(var i = 0; i < 2; i++){
            Point point = GenerateFreeCordsWithin(new Point(getLevelBoss().X, getLevelBoss().Y), 2);
            if(point != null) {
                ((TrollGuard)GenerateMonster(TrollGuard.class, point.X, point.Y)).setGuardedObject(getLevelBoss());
            }
        }
    }

    @Override
    public void GenerateChests() {
        DisplayableObjects.add(chests);
        for(var i = 0; i < 5; i++){
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y, this);
            chest.drop.addRandomResource(ResourceType.Coins, 1500, 4000);
            chest.drop.addRandomResource(ResourceType.Ore, 3000, 10000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 3000, 10000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 3000, 10000, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 3, 25, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 3, 25, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 3, 7, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 7, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 5, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 5, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chest.drop.addRandomSupply(Bandage.class, 1, 10, 0.1f);
            chest.drop.addRandomSupply(HealthPotion.class, 1, 5, 0.05f);
            chest.drop.addRandomSupply(StaminaPotion.class, 1, 5, 0.05f);
            chests.add(chest);
        }
    }
}