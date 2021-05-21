package com.company;

public class Moose extends HuntAnimal {

    @Override
    Track CreateTrack() {
        return new MooseTrack(X, Y);
    }

    @Override
    protected String getImagePath() {
        return "/moose_icon_30x30.png";
    }

    @Override
    protected void init() {
        super.init();
        habitat = new int[]{4};
        Name = "Лось";
        StaminaToObtain = 15;
        Danger = 0.2f;
        Damage = 35;
        equipNeeded = EquipmentType.CorralSpear;
        drop.addResource(ResourceType.Leather, 4);
        drop.addResource(ResourceType.Meat, 4);
        frequencyMove = 60;
    }

    public Moose(int maxX, int maxY) {
        super(maxX, maxY);
    }

    public Moose(){super();}

    @Override
    public void Act() {
        super.Act();
    }

}
