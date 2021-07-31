package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Objects;

public class MoonMaiden extends Monster {

    private int TransformRadius = 4;
    private int TransformationCooldown = 0;
    private int TransformationFrequency = 15;
    private boolean isExposed = false;
    ImageIcon WomenImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/women_icon_30x30.png")));
    ImageIcon DemonImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/demon_icon_30x30.png")));

    @Override
    protected String getImagePath() {
        return "/women_icon_30x30.png";
    }

    public void init(IMap map, int x, int y){
        super.init(map, x, y);
        Name = "Лунная дева";
        frequencyMove = 0;
        Power = 10;
        FeelRadius = 2;
        TransformationCooldown = TransformationFrequency;
        drop.addRandomResource(ResourceType.Coins, 700, 1200);
        drop.addRandomEquipment(EquipmentType.HuntBow, 1, 3, 0.7f);
        drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 2, 0.50f);
        drop.addRandomEquipment(EquipmentType.BearSpear, 1, 1, 0.25f);
    }

    private void TransformCultist(Monster monster) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        var chance = Math.random();
        if(chance < 0.5f){
            map.GenerateMonster(Goathorn.class).init(map, monster.getX(), monster.getY());
            map.addToLog("Лунная дева обращает культиста в козлорога.");
        }
        else {
            if (chance < 0.85f){
                map.GenerateMonster(SwineMar.class).init(map, monster.getX(), monster.getY());
                map.addToLog("Лунная дева обращает культиста в свиноморфа.");
            }
            else {
                map.GenerateMonster(WhereBear.class).init(map, monster.getX(), monster.getY());
                map.addToLog("Лунная дева обращает культиста в медведя-оборотня.");
            }
        }
        map.getMonsters().remove(monster);
    }

    @Override
    public void Act() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if(isExposed){
            if(AttackIfPlayerNear())
                return;
            else {
                if(map.DistanceToPlayer(this) <= FeelRadius()){
                    map.MoveToward(this, map.getPlayer().X, map.getPlayer().Y);
                }
            }
            return;
        }
        if(map.DistanceToPlayer(this) <= FeelRadius()){
            map.addToLog("Злобный демон сбрасывает личину Лунной девы!");
            isExposed = true;
            frequencyMove = 0.9f;
            FeelRadius = 7;
            return;
        }
        TransformationCooldown--;
        var monsters =  map.getMonsters();
        for(var monster:monsters) {
            if (monster.getClass() == Cultist.class && map.RangeFromTo(monster.getX(), monster.getY(), X, Y) <= TransformRadius && TransformationCooldown < 1) {
                TransformCultist((Monster) monster);
                TransformationCooldown = TransformationFrequency;
            }
        }
    }

    @Override
    public float getMaxHealth() {
        return 100;
    }

    @Override
    public ImageIcon getImage() {
        if(isExposed){
            return DemonImage;
        }
        else {
            return WomenImage;
        }
    }

    @Override
    public float getPower(){
        if(isExposed){
            return 60;
        }
        else {
            return 10;
        }
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Monster;
    }

    @Override
    public Object getSelf() {
        return this;
    }

    @Override
    public Buffing[] getBuffing() {
        return buffings;
    }
}