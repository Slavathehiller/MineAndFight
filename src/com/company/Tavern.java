package com.company;

import javax.swing.*;

public class Tavern extends JFrame{
    private JPanel MainPanel;
    private JButton CookMushroomSoupButton;
    private JButton CookMeatSoupButton;
    private JButton MakeElixirOfHealthButton;
    private JButton MakeElixirOfStaminaButton;
    private JButton MakeSpicedMeatButton;
    private JButton ToCityButton;
    private JButton CookRostMeatButton;
    Player player;

    public Tavern(Player player){
        setVisible(true);
        add(MainPanel);
        setBounds(600, 300, 700, 500);
        this.player = player;
        ToCityButton.addActionListener((x) -> this.dispose());
        CookMushroomSoupButton.addActionListener((x) -> EatMushroomSoup());
        CookMeatSoupButton.addActionListener((x) -> EatMeatSoup());
        CookRostMeatButton.addActionListener((x) -> EatRostMeat());
        MakeElixirOfHealthButton.addActionListener((x) -> MakeHealthPotion());
        MakeElixirOfStaminaButton.addActionListener((x) -> MakeStaminaPotion());
        MakeSpicedMeatButton.addActionListener((x) -> MakeSpicedMeat());
    }

    public void EatMushroomSoup(){
        if(player.getResourceNumber(ResourceType.MushroomCap) >= 2){
            player.addResource(ResourceType.MushroomCap, -2);
            player.setBuffOn(BuffTypes.FastStaminaRegeneration);
            player.RefreshInfo();
            JOptionPane.showMessageDialog(this, "Вы поели грибного супа \n Скорость восстановления энергии увеличена.", "Насыщение", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void EatMeatSoup(){
        if(player.getResourceNumber(ResourceType.Meat) >= 2 && player.getResourceNumber(ResourceType.WildOnionBulb) >= 2 ){
            player.addResource(ResourceType.Meat, -2);
            player.addResource(ResourceType.WildOnionBulb, -2);
            player.setBuffOn(BuffTypes.FastHealthRegeneration);
            player.RefreshInfo();
            JOptionPane.showMessageDialog(this, "Вы поели мясной похлебки \n Скорость регенерации здоровья увеличена.", "Насыщение", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void EatRostMeat(){
        if(player.getResourceNumber(ResourceType.Meat) >= 4 && player.getResourceNumber(ResourceType.WildOnionBulb) >= 2 && player.getResourceNumber(ResourceType.SageLeaf) >= 2){
            player.addResource(ResourceType.Meat, -4);
            player.addResource(ResourceType.WildOnionBulb, -2);
            player.addResource(ResourceType.SageLeaf, -2);
            player.setBuffOn(BuffTypes.EnLargeMaxStamina);
            player.setBuffOn(BuffTypes.EnLargeMaxHealth);
            player.RefreshInfo();
            JOptionPane.showMessageDialog(this, "Вы съели жаркое \n Максимальное здоровье увеличено.\n Максимальная энергия увеличена", "Насыщение", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void MakeHealthPotion(){
        HealthPotion healthPotion = new HealthPotion();
        if(player.isEnoughResource(healthPotion.recipe)){
            boolean isExists = false;
            for (var supply : player.supplies) {
                if (supply.getClass() == healthPotion.getClass()) {
                    isExists = true;
                    supply.Number++;
                    break;
                }
            }
            if(!isExists){
                player.supplies.add(healthPotion);
            }
            player.addResource(ResourceType.PlantainLeaf, -6);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
        player.RefreshInfo();
    }

    public void MakeStaminaPotion(){
        StaminaPotion staminaPotion = new StaminaPotion();
        if(player.isEnoughResource(staminaPotion.recipe)){
            boolean isExists = false;
            for (var supply : player.supplies) {
                if (supply.getClass() == staminaPotion.getClass()) {
                    isExists = true;
                    supply.Number++;
                    break;
                }
            }
            if(!isExists){
                player.supplies.add(staminaPotion);
            }
            player.addResource(ResourceType.SageLeaf, -6);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
        player.RefreshInfo();
    }

    public void MakeSpicedMeat(){
        SpicedMeat spicedMeat = new SpicedMeat();
        if(player.isEnoughResource(spicedMeat.recipe)){
            boolean isExists = false;
            for (var supply : player.supplies) {
                if (supply.getClass() == spicedMeat.getClass()) {
                    isExists = true;
                    supply.Number++;
                    break;
                }
            }
            if(!isExists){
                player.supplies.add(spicedMeat);
            }
            player.addResource(ResourceType.Meat, -3);
            player.addResource(ResourceType.SageLeaf, -2);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
        player.RefreshInfo();
    }


}
