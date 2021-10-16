package com.company;

public class ThicketLevel3Model extends ThicketLevelModel{

    public boolean PlayerIsMasked = false;

    public ThicketLevel3Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateMonsters(Goathorn.class, 3);
        GenerateMonsters(SwineMar.class, 2);
        GenerateMonsters(WhereBear.class, 1);
        GenerateMonsters(Cultist.class, 3);
        GenerateLevelBoss(new MoonMaiden());
        for(int i = 0; i < 7; i++) {
            var cord = GenerateFreeCordsWithin(new Point(LevelBoss.X, LevelBoss.Y), 3);
            GenerateMonster(Cultist.class).init(this, cord.X, cord.Y);
        }
    }

    @Override
    public boolean getPlayerIsMasked(){
        return PlayerIsMasked;
    }

    @Override
    public void Attack(IFighter attacker, IFighter target){
        super.Attack(attacker, target);
        if(attacker.getFighterType() == CollisionObjectTypes.Player) {
            if(PlayerIsMasked) {
                PlayerIsMasked = false;
                Log += "Игрок атакует монстра. Маскировка пропадает\n";
            }
        }
        if(target.getHealth() < 1 && target.getClass() == Cultist.class){
            if(Math.random() < 0.7){
                Log += "Игрок убивает культиста и забирает его одежду.\nМонстры вас игнорируют до первой атаки\n";
                PlayerIsMasked = true;
            }
        }

    }

    @Override
    public void GenerateChests(){
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
            chest.drop.addRandomSupply(Bandage.class, 1, 4, 0.1f);
            chest.drop.addRandomSupply(HealthPotion.class, 1, 2, 0.05f);
            chest.drop.addRandomSupply(StaminaPotion.class, 1, 2, 0.05f);
            chests.add(chest);
        }
    }
}
