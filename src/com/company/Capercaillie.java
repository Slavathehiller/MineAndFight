package com.company;

public class Capercaillie extends HuntAnimal {

    @Override
    Track CreateTrack() {
        return new CapercaillieTrack(X, Y);
    }

    @Override
    protected String getImagePath() {
        return "/capercaillie_icon_30x30.png";
    }

    @Override
    protected void init() {
        super.init();
        habitat = new int[]{1, 2};
        Name = "Глухарь";
        StaminaToObtain = 5;
        equipNeeded = EquipmentType.Sling;
        drop.addResource(ResourceType.Meat, 2);
        frequencyMove = 60;
    }

    public Capercaillie(int maxX, int maxY) {
        super(maxX, maxY);
    }

    public Capercaillie(){super();}

    @Override
    public void Act() {
        super.Act();
    }

}