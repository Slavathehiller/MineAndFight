package com.company;

public interface IMap {

    boolean canMonsterMove(int x, int y);
    Player getPlayer();
    void Attack(IFighter attacker, IFighter target);
}
