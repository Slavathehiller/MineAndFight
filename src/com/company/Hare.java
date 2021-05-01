package com.company;


public class Hare extends HuntAnimal{

    @Override
    Track CreateTrack(){
        return new HareTrack(X, Y);
    }

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
        frequencyMove = 75;
    }

    @Override
    public void Act(){
        super.Act();
    }

}
