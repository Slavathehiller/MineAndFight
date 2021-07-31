package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ThicketLevel3Model extends ThicketLevelModel{

    public boolean PlayerIsMasked = false;

    public ThicketLevel3Model(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super(player);
    }

    @Override
    public void GenerateMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
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
            Chest chest = new Chest(point.X, point.Y);
            chest.drop.addRandomResource(ResourceType.Coins, 100, 350);
            chest.drop.addRandomResource(ResourceType.Ore, 600, 800, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 600, 800, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 600, 800, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 3, 7, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 3, 7, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 3, 4, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 4, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 3, 0.005f);
            chests.add(chest);
        }
    }
}
