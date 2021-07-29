package com.company;

import java.lang.reflect.InvocationTargetException;

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
        if(target.getClass() == Cultist.class){
            if(Math.random() < 0.7){
                Log += "Игрок убивает культиста и забирает его одежду.\nМонстры вас игнорируют до первой атаки\n";
                PlayerIsMasked = true;
            }
        }

    }

    @Override
    public void GenerateChests() {

    }
}
