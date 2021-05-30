package com.company;

import javax.swing.*;

public class Inventory extends JFrame{
    private JPanel MainPanel;
    private JPanel HealthPotionPanel;
    private JPanel StaminaPotionPanel;
    private JPanel SpicedMeatPanel;
    private JButton UseSupplyButton;
    private JButton ExitButton;
    private JRadioButton HealthPotionRadioButton;
    private JLabel HealthPotionLabel;
    private JLabel StaminaPotionLabel;
    private JLabel SpicedMeatLabel;
    private JRadioButton StaminaPotionRadioButton;
    private JRadioButton SpicedMeatRadioButton;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private Supply[] supplies = new Supply[]{new HealthPotion(), new StaminaPotion(), new SpicedMeat()};
    private int NumberSelected = -1;
    Player player;

    public Inventory(Player player){
        add(MainPanel);
        setBounds(300, 300, 500, 500);
        setVisible(true);
        this.player = player;
        buttonGroup.add(HealthPotionRadioButton);
        buttonGroup.add(StaminaPotionRadioButton);
        buttonGroup.add(SpicedMeatRadioButton);
        HealthPotionRadioButton.addChangeListener((x) -> SelectSupply());
        StaminaPotionRadioButton.addChangeListener((x) -> SelectSupply());
        SpicedMeatRadioButton.addChangeListener((x) -> SelectSupply());
        UseSupplyButton.addActionListener((x) -> {
            try {
                UseSupply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ExitButton.addActionListener((x) -> this.dispose());
        RefreshSupplyNumbers();
    }

    private void SelectSupply(){
        if(HealthPotionRadioButton.isSelected()){
            NumberSelected = 0;
        }
        if(StaminaPotionRadioButton.isSelected()){
            NumberSelected = 1;
        }
        if(SpicedMeatRadioButton.isSelected()){
            NumberSelected = 2;
        }
    }

    private void RefreshSupplyNumbers(){
        HealthPotionLabel.setText("x " + player.GetSupplyNumber(HealthPotion.class));
        StaminaPotionLabel.setText("x " + player.GetSupplyNumber(StaminaPotion.class));
        SpicedMeatLabel.setText("x " + player.GetSupplyNumber(SpicedMeat.class));
    }

    private void UseSupply() throws Exception {
        if(NumberSelected < 0)
            return;
        Supply supply = player.GetSupply(supplies[NumberSelected].getClass());
        if(supply != null && supply.Number > 0)
            supply.Use(player);
        RefreshSupplyNumbers();
        player.RefreshInfo();
    }
}
