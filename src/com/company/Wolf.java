package com.company;

public class Wolf extends HuntAnimal{

    @Override
    protected String getImagePath() {
        return "/wolf_icon_30x30.png";
    }

    @Override
    Track CreateTrack(){
        return new WolfTrack(X, Y);
    }

    public Wolf(int maxX, int maxY){
        super(maxX, maxY);
        habitat = new int[]{1, 2, 4};
        Name = "Волк";
        StaminaToObtain = 7;
        Danger = 0.3f;
        Damage = 5;
        equipNeeded = EquipmentType.HuntBow;
        drop.addResource(ResourceType.Fur, 2);
        frequencyMove = 50;
    }

    @Override
    public void Act(){
        super.Act();
    }

}
