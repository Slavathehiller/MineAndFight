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

    @Override
    protected void init(){
        super.init();
        habitat = new int[]{1, 2};
        Name = "Заяц";
        StaminaToObtain = 2;
        equipNeeded = EquipmentType.Sling;
        drop.addResource(ResourceType.Fur, 1);
        drop.addResource(ResourceType.Meat, 1);
        frequencyMove = 75;
    }

    public Hare(int maxX, int maxY){
        super(maxX, maxY);
    }

    public Hare(){
        super();
    }

    @Override
    public void Act(){
        super.Act();
    }

}
