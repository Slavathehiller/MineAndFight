package com.company;

public class Equipment {
    int Type;
    int Number;
    String Name;
    static String[] names = new String[] {"Праща", "Охотничий лук", "Загонное копье", "Рогатина", "Капкан", "Охотничья собака", "Подзорная труба"};

    public Equipment(int type)
    {
        Type = type;
        Number = 1;

        Name = Equipment.names[Type];
    }
}

