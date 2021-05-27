package com.company;

public class HealthPotion extends Supply{


    public HealthPotion(){
        Name = "Зелье здоровья";
        Number = 1;
        recipe = new Recipe();
        recipe.addResource(ResourceType.PlantainLeaf, 6);
    }

    @Override
    protected String getImagePath() {
        return "/elexir_of_health_icon_50x50.png";
    }

    @Override
    protected String getSmallImagePath() {
        return "/elexir_of_health_icon_15x15.png";
    }

    @Override
    public void Use(Player player) throws Exception {
        if(Number < 1){
            throw new Exception("Количество зелий равна 0");
        }
        Number--;
        player.setHealth(player.getMaxHealth());

    }
}
