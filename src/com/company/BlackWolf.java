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
            AttackIfPlayerNear();
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
            while (!map.canMonsterMove(nextX, nextY) && countOfTries < 50);
            if(countOfTries >= 50){
                return;
            }
            X = nextX;
            Y = nextY;
//            System.out.println("X: " + X + " Y: " + Y);
        }
    }

    @Override
    public float getMaxHealth() {
        return 20;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    @Override
    public String getToolTip() {
        return "Черный волк. " + "Здоровье: " + Health + "/" + getMaxHealth();
    }

    public BlackWolf(IMap map, int x, int y){
        super(map, x, y);
        Name = "Черный волк";
        frequencyMove = 0.1f;
        Power = 10;
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

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Monster;
    }

    @Override
    public Object getSelf() {
        return this;
    }


}
