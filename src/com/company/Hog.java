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

    @Override
    protected void init() {
        super.init();
        habitat = new int[]{2, 4};
        Name = "Кабан";
        StaminaToObtain = 10;
        Danger = 0.5f;
        Damage = 20;
        equipNeeded = EquipmentType.CorralSpear;
        drop.addResource(ResourceType.Leather, 2);
        drop.addResource(ResourceType.Meat, 2);
        frequencyMove = 30;
    }

    public Hog(int maxX, int maxY){
        super(maxX, maxY);
    }

    public Hog(){super();}

    @Override
    public void Act(){
        super.Act();
    }

}
