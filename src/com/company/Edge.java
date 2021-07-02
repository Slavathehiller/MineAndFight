package com.company;

public class Edge extends Obstacle{
    @Override
    protected String getImagePath() {
        return null;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Edge;
    }
}
