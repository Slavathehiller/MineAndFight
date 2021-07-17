package com.company;

public class PointOfInterest_Place {

    public int X;
    public int Y;
    private int Counter;
    public int MaxCounter;

    public PointOfInterest_Place(){}

    public PointOfInterest_Place(int MaxCounter){
        this.MaxCounter = MaxCounter;
    }


    public boolean IsActive(){
        return Counter > 0;
    }

    public void Start(int x, int y){
        this.X = x;
        this.Y = y;
        Counter = MaxCounter;
    }

    public void CountDown(){
        if(Counter > 0){
            Counter--;
        }

    }



}
