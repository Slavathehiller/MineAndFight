package com.company;

import java.util.Random;

public class Hare extends HuntAnimal{


    @Override
    protected String getImagePath() {
        return "/hare_icon_30x30.png";
    }

    public Hare(int maxX, int maxY){
        super(maxX, maxY);
        Name = "Заяц";
        equipNeeded = EquipmentType.Sling;
        drop.addResource(ResourceType.Fur, 1);
        drop.addResource(ResourceType.Meat, 1);
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
