package com.company;

public class StaminaPotion extends Supply{

    public StaminaPotion(){
        Name = "Зелье энергии";
        Number = 1;
        recipe = new Recipe();
        recipe.addResource(ResourceType.SageLeaf, 6);
    }


    @Override
    protected String getImagePath() {
        return "/elixir_of_stamina_icon_50x50.png";
    }

    @Override
    protected String getSmallImagePath() {
        return "/elixir_of_stamina_icon_15x15.png";
    }

    @Override
    public void Use(Player player) throws Exception {
        if(Number < 1){
            throw new Exception("Количество зелий равна 0");
        }
        Number--;
        player.setStamina(player.getMaxStamina());
        super.Use(player);

    }

}
