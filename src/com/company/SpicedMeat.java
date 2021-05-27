package com.company;

public class SpicedMeat extends Supply{

    public SpicedMeat(){
        Name = "Мясо со специями";
        Number = 1;
        recipe = new Recipe();
        recipe.addResource(ResourceType.Meat, 3);
        recipe.addResource(ResourceType.SageLeaf, 2);
    }

    @Override
    protected String getImagePath() {
        return "/spiced_meat_icon_50x50.png";
    }

    @Override
    protected String getSmallImagePath() {
        return "/spiced_meat_icon_15x15.png";
    }

    @Override
    public void Use(Player player) throws Exception {
        if(Number < 1){
            throw new Exception("Количество еды равна 0");
        }
        Number--;
        player.setHealth(player.getMaxHealth() - 50);
        player.setStamina(player.getMaxStamina() - 50);

    }
}
