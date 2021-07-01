package com.company;

public class Edge extends Obstacle{
    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Edge;
    }
}
