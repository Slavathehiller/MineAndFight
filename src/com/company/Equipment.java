package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Equipment {
    public int Type;
    public int Number;
    public String Name;
    public ImageIcon Icon;
    public Recipe recipe = new Recipe();
    static String[] names = new String[] {"Праща", "Охотничий лук", "Загонное копье", "Рогатина", "Капкан", "Охотничья собака", "Подзорная труба"};
    static Double[] durabilities = new Double[] {0.7, 0.8, 0.75, 0.9, 0.9, 1.0, 1.0};

    static Resource[][] recipes = new Resource[][]{
            new Resource[]{new Resource(ResourceType.Fiber, 1)},
            new Resource[]{new Resource(ResourceType.Fiber, 1), new Resource(ResourceType.Wood, 200)},
            new Resource[]{new Resource(ResourceType.Ore, 500), new Resource(ResourceType.Wood, 500), new Resource(ResourceType.Leather, 1)},
            new Resource[]{new Resource(ResourceType.Ore, 1000), new Resource(ResourceType.Wood, 800), new Resource(ResourceType.Leather, 1)},
            new Resource[]{new Resource(ResourceType.Ore, 1000)},
            null,
            null
    };

    static String[] imageNames = new String[] {"/sling_Icon_15x15.png",
                                                "/bow_icon_15x15.png",
                                                "/corral_spear_icon_15x15.png",
                                                "/bear_spear_icon_15x15.png",
                                                "/trap_icon_15x15.png",
                                                "/dog_icon_15x15.png",
                                                "/spyglass_icon_15x8.png"};

    public Equipment(int type)
    {
        Type = type;
        Number = 1;
        Icon = new ImageIcon(getClass().getResource(imageNames[Type]));

        Name = Equipment.names[Type];
        if(recipes[Type] != null) {
            for (Resource res : recipes[Type]) {
                recipe.resources.add(res);
            }
        }
    }
}

