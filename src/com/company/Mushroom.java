package com.company;

public class Mushroom extends WildHerb{

    @Override
    protected String getImagePath() {
        return "/mushroom_icon_30x30.png";
    }

    @Override
    protected void init(){
        super.init();
        habitat = new int[]{2, 4};
        Name = "Грибы";
        drop.addResource(ResourceType.MushroomCap, 1);
    }
}
