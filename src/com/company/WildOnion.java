package com.company;

public class WildOnion extends WildHerb{

    @Override
    protected String getImagePath() {
        return "/onion_icon_30x30.png";
    }

    @Override
    protected void init(){
        super.init();
        habitat = new int[]{1, 2};
        Name = "Дикий лук";
        drop.addResource(ResourceType.WildOnionBulb, 1);
    }
}
