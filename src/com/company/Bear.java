package com.company;

public class Bear extends HuntAnimal {

    @Override
    protected String getImagePath() {
        return "/bear_icon_30x30.png";
    }

    @Override
    Track CreateTrack() {
        return new BearTrack(X, Y);
    }

    public Bear(int maxX, int maxY) {
        super(maxX, maxY);
        habitat = new int[]{4};
        Name = "Медведь";
        equipNeeded = EquipmentType.BearSpear;
        drop.addResource(ResourceType.Fur, 4);
        drop.addResource(ResourceType.Meat, 4);
        frequencyMove = 25;
    }

    @Override
    public void Act() {
        super.Act();
    }
}
