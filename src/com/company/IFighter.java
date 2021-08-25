package com.company;

public interface IFighter {

    float getHealth();
    void changeHealth(float health);
    int getFighterType();
    float getPower();
    String getName();
    Buffing[] getBuffing();
    Drop getDrop();
    boolean getLycanthrope();
    boolean getGiant();
    float getMaxHealth();
    float getMasked();
    float getRangedPower();

}
