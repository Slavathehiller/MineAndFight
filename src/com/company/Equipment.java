package com.company;

public class Equipment {
    int Type;
    int Number;
    int ChanceToBreak;
    String Name;
    static String[] names = new String[] {"Праща", "Охотничий лук", "Загонное копье", "Рогатина", "Капкан", "Охотничья собака", "Подзорная труба"};
    static Double[] durabilities = new Double[] {0.5, 0.7, 0.6, 0.75, 0.9, 1.0, 1.0};

    public Equipment(int type)
    {
        Type = type;
        Number = 1;

        Name = Equipment.names[Type];
    }
}

