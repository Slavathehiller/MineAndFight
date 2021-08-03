package com.company;

import javax.swing.*;

public class Tavern extends JDialog{
    private JPanel MainPanel;
    private JButton CookMushroomSoupButton;
    private JButton CookMeatSoupButton;
    private JButton MakeSpicedMeatButton;
    private JButton ToCityButton;
    private JButton CookRostMeatButton;
    Player player;

    public Tavern(JDialog parent, Player player){
        super(parent, "", ModalityType.DOCUMENT_MODAL);
        add(MainPanel);
        setBounds(600, 300, 700, 500);
        this.player = player;
        ToCityButton.addActionListener((x) -> this.dispose());
        CookMushroomSoupButton.addActionListener((x) -> EatMushroomSoup());
        CookMeatSoupButton.addActionListener((x) -> EatMeatSoup());
        CookRostMeatButton.addActionListener((x) -> EatRostMeat());
        MakeSpicedMeatButton.addActionListener((x) -> MakeSpicedMeat());
        setVisible(true);
    }

    public void EatMushroomSoup(){
        if(player.getResourceNumber(ResourceType.MushroomCap) >= 2){
            player.addResource(ResourceType.MushroomCap, -2);
            player.setGlobalBuffOn(GlobalBuffTypes.FastStaminaRegeneration);
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
            player.setGlobalBuffOn(GlobalBuffTypes.FastHealthRegeneration);
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
            player.setGlobalBuffOn(GlobalBuffTypes.IncreaseMaxStamina);
            player.setGlobalBuffOn(GlobalBuffTypes.IncreaseMaxHealth);
            player.RefreshInfo();
            JOptionPane.showMessageDialog(this, "Вы съели жаркое \n Максимальное здоровье увеличено.\n Максимальная энергия увеличена", "Насыщение", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "Не хватает ингредиентов", "Неудача", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void MakeSpicedMeat(){
        SpicedMeat spicedMeat = new SpicedMeat();
        if(player.isEnoughResource(spicedMeat.recipe)){
            boolean isExists = false;
            for (var supply : player.getSupplies()) {
                if (supply.getClass() == spicedMeat.getClass()) {
                    isExists = true;
                    supply.Number++;
                    break;
                }
            }
            if(!isExists){
                player.getSupplies().add(spicedMeat);
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
