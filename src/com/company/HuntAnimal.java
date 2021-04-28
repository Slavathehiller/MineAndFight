package com.company;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public abstract class HuntAnimal {
    public long X;
    public long Y;
    public String Name;
    public int MaxX;
    public int MaxY;
    public int equipNeeded;
    public ImageIcon image;
    public double frequencyMove;

    protected String getImagePath(){
        return null;
    }

    public Drop drop = new Drop();

    public HuntAnimal(int maxX, int maxY){
        this.MaxX = maxX;
        this.MaxY = maxY;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
    }


    public void Act(){
        if(CanMove()){
            long nextX;
            long nextY;
            do {
                nextX = X;
                nextY = Y;
                double a = Math.floor(Math.random() * 4);
                System.out.println("a: " + a);
                if (a == 0)
                    nextY -= 1;
                if (a == 1)
                    nextY += 1;
                if (a == 2)
                    nextX -= 1;
                if (a == 3)
                    nextX += 1;
                System.out.println("nextX: " + nextX + " nextY: " + nextY);
            }
            while (nextX < 0 || nextX >= MaxX || nextY < 0 || nextY >= MaxY);
            X = nextX;
            Y = nextY;
            System.out.println("X: " + X + " Y: " + Y);
        }
    }

    protected boolean CanMove() {
        return Math.floor(Math.random() * 100) < frequencyMove;
    }

}
