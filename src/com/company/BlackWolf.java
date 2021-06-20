package com.company;

import javax.swing.*;

public class BlackWolf extends Monster{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    @Override
    public void Act() {
        if(CanMove()){
            int countOfTries = 0;
            int nextX;
            int nextY;
            do {
                nextX = X;
                nextY = Y;
                double a = Math.floor(Math.random() * 4);
                if (a == 0)
                    nextY -= 1;
                if (a == 1)
                    nextY += 1;
                if (a == 2)
                    nextX -= 1;
                if (a == 3)
                    nextX += 1;
                System.out.println("nextX: " + nextX + " nextY: " + nextY);
                countOfTries++;
            }
            while (!map.canIMove(nextX, nextY) && countOfTries < 50);
//            while (nextX < 0 || nextX >= MaxX || nextY < 0 || nextY >= MaxY);
            X = nextX;
            Y = nextY;
//            System.out.println("X: " + X + " Y: " + Y);
        }
    }

    protected boolean CanMove() {
//        return Math.floor(Math.random() * 100) < frequencyMove;
        return true;
    }

    public BlackWolf(IMap map, int x, int y){
        super(map, x, y);
        Name = "Черный волк";
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public Integer getX() {
        return X;
    }

    @Override
    public Integer getY() {
        return Y;
    }
}
