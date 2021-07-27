package com.company;

public class Hemp extends WildHerb {

    @Override
    protected String getImagePath() {
        return "/hemp_icon_30x30.png";
    }

    @Override
    protected void init() {
        super.init();
        habitat = new int[]{1};
        Name = "Конопля";
        drop.addResource(ResourceType.Fiber, 2);
    }
}