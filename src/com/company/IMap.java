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
    ArrayList<IDisplayable> getObstacles();
    String addToLog(String message);
    void GenerateMonsters(Class _class, int number) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    Monster GenerateMonster(Class _class) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    public Monster GenerateMonster(Class _class, int x, int y);
    Point GenerateFreeCordsWithin(Point point, int r);
    boolean CheckPathToward(Point pointFrom, Point pointTo);
    Point NearestPointToward(Point pointFrom, Point pointTo);
    public float RangeFromToObject(int x, int y, IDisplayable object);
    public float DistanceBetweenObjects(IDisplayable object1, IDisplayable object2);
    public void MoveTowardObject(IMovableDisplayable mover, IDisplayable object);
    Point GetStepToward(Point pointFrom, Point pointTo);
    IDisplayable ObjectAt(int x, int y);
    boolean getPlayerIsMasked();
    void RangedAttack(IFighter attacker, IFighter target, String actionMessage);
    int getMonsterLimit();

    IDisplayable ObjectAt(Point spawnPoint);

    Monster GenerateMonster(Class<WarDog> warDogClass, Point spawnPoint);
}
