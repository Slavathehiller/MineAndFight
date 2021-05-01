package com.company;

public class Hog extends HuntAnimal{

    @Override
    protected String getImagePath() {
        return "/hog_icon_30x30.png";
    }

    @Override
    Track CreateTrack(){
        return new HogTrack(X, Y);
    }

    public Hog(int maxX, int maxY){
        super(maxX, maxY);
        Name = "Кабан";
        equipNeeded = EquipmentType.CorralSpear;
        drop.addResource(ResourceType.Leather, 2);
        drop.addResource(ResourceType.Meat, 2);
        frequencyMove = 30;
    }

    @Override
    public void Act(){
        super.Act();
    }

}
