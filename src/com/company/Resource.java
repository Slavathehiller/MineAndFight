package com.company;

import javax.swing.*;

public class Resource {
    int Type;
    long Number;
    String Name;
    ImageIcon Icon;

    static String[] names = new String[] {"Руда", "Дерево", "Камень", "Монеты", "Шкура", "Кожа", "Мясо", "Дикий лук", "Шалфей", "Подорожник", "Грибы"};
    static String[] imageNames = new String[] {"/ore_icon_15x14.png",
                                                "/wood_icon_15x15.png",
                                                "/stone_icon_15x15.png",
                                                "/coins_icon_15x20.png",
                                                "/fur_icon_15x15.png",
                                                "/leather_icon_15x15.png",
                                                "/meat_icon_15x12.png",
                                                "/onion_bulb_icon_15x15.png",
                                                "/sage_leaf_icon_15x15.png",
                                                "/plantain_leaf_icon_15x15.png",
                                                "/mushroom_icon_15x15.png"
                                                };

    public Resource(int type){
        Init(type, 0);
    }

    public Resource(int type, long number)
    {
        Init(type, number);
    }

    private void Init(int type, long number){
        Type = type;
        Number = number;
        Icon = new ImageIcon(getClass().getResource(imageNames[Type]));

        Name = Resource.names[Type];
    }
}
