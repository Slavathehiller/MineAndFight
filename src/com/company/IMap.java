package com.company;

import java.util.ArrayList;

public interface IMap {

    boolean canMonsterMove(int x, int y);
    Player getPlayer();
    void Attack(IFighter attacker, IFighter target);
    float RangeFromTo(int x1, int y1, int x2, int y2);
    float DistanceToPlayer(int x, int y);
    float DistanceToPlayer(IDisplayable object);
    void MoveToward(IMovableDisplayable mover, int x, int y);
    ArrayList<Monster> getMonsters();
    String addToLog(String message);
}
