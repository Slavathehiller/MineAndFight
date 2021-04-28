package com.company;

public class Wolf extends HuntAnimal{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    public Wolf(int maxX, int maxY){
        super(maxX, maxY);
        Name = "Волк";
        equipNeeded = EquipmentType.HuntBow;
        drop.addResource(ResourceType.Fur, 2);
        frequencyMove = 50;
    }

    @Override
    public void Act(){
        super.Act();
    }

}
