package com.company;

public class BattleBuffType {

    public static final int Bleed = 0;
    public static final int Poison = 1;


    public static String[] battleBuffImageNames = new String[]{"/bleed_icon_20x20.png", "/poison_icon_20x20.png"};
    public static String[] names = new String[]{"кровотечение", "отравление"};
    public static String[] toolTips = new String[]{"Кровотечение. Здоровье уменьшается", "Отравление. Здоровье и энергия уменьшается"};
}
