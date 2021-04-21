package com.company;

import java.util.Random;

public class Wolf extends HuntAnimal{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    public Wolf(int maxX, int maxY){
        super(maxX, maxY);
        Name = "Волк";
        equipNeeded = EquipmentType.HuntBow;
    }

    @Override
    public void Act(){
        Random random = new Random();
        if(random.nextBoolean()){
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

}
