package com.company;

public class Sage extends WildHerb{

    @Override
    protected String getImagePath() {
        return "/sage_icon_30x30.png";
    }

    @Override
    protected void init(){
        super.init();
        habitat = new int[]{1};
        Name = "Шалфей";
        drop.addResource(ResourceType.SageLeaf, 3);
    }
}
