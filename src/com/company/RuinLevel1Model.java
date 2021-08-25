package com.company;

public class RuinLevel1Model extends RuinLevelModel implements ISubLevelModel, IMap{

    public RuinLevel1Model(Player player){
        super(player);
    }

    @Override
    public void GenerateMonsters(){
        super.GenerateMonsters();
        GenerateLevelBoss(new GoblinMarauder());
        GenerateMonsters(GoblinSlinger.class, 3);
    }

    @Override
    public void GenerateChests() {

    }
}
