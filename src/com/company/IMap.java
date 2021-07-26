package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface IMap {

    boolean canMonsterMove(int x, int y);
    Player getPlayer();
    void Attack(IFighter attacker, IFighter target);
    float RangeFromTo(int x1, int y1, int x2, int y2);
    float DistanceToPlayer(int x, int y);
    float DistanceToPlayer(IDisplayable object);
    void MoveToward(IMovableDisplayable mover, int x, int y);
    ArrayList<IDisplayable> getMonsters();
    String addToLog(String message);
    void GenerateMonsters(Class _class, int number) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    Monster GenerateMonster(Class _class) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    Point GenerateFreeCordsWithin(Point point, int r);
}
