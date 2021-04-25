package com.company;

public class Resource {
    int Type;
    double Number;
    String Name;
    static String[] names = new String[] {"Руда", "Дерево", "Камень", "Монеты", "Шкура", "Мясо", "Кожа"};

    public Resource(int type)
    {
        Type = type;
        Number = 0;

        Name = Resource.names[Type];
    }
}
