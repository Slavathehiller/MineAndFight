package com.company;

public class Bandage extends Supply{

    public Bandage(){
        Name = "Бинт";
        Number = 1;
        recipe = new Recipe();
        recipe.addResource(ResourceType.Fiber, 5);
    }

    @Override
    protected String getImagePath() {
        return "/bandage_icon_50x50.png";
    }

    @Override
    protected String getSmallImagePath() {
        return "/bandage_icon_15x15.png";
    }

    @Override
    public void Use(Player player) throws Exception {
        if(Number < 1){
            throw new Exception("Количество бинтов равно 0");
        }
        Number--;
        player.setBattleBuffOff(BattleBuffType.Bleed);
        super.Use(player);

    }
}
