package com.company;

public class Plantain extends WildHerb{

    @Override
    protected String getImagePath() {
        return "/plantain_icon_30x30.png";
    }

    @Override
    protected void init(){
        super.init();
        habitat = new int[]{1};
        Name = "Подорожник";
        drop.addResource(ResourceType.PlantainLeaf, 2);
    }
}
