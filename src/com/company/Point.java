package com.company;

public class Point {
    public int X;
    public int Y;

    public Point(int x, int y){
        X = x;
        Y = y;
    }

    public Point(Point point){
        X = point.X;
        Y = point.Y;
    }

    public boolean Equal(Point point){
        return X == point.X && Y == point.Y;
    }

    public Point(){}
}
