package com.company;

import java.lang.reflect.InvocationTargetException;

public class ThicketLevel3Model extends ThicketLevelModel{


    public ThicketLevel3Model(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super(player);
    }

    @Override
    public void GenerateMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super.GenerateMonsters();
        GenerateMonsters(Goathorn.class, 2);
        GenerateLevelBoss(new MoonMaiden());
    }

    @Override
    public void GenerateChests() {

    }
}
