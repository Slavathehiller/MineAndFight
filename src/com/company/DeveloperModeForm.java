package com.company;

import javax.swing.*;

public class DeveloperModeForm extends JFrame{
    private JButton AddALotOfMoneyButton;
    private JButton AddALotOfOreButton;
    private JButton SetVisionRadiusButton;
    private JButton AddALotOfWoodButton;
    private JTextField SetVisionField;
    private JPanel MainPanel;
    private JButton ExitButton;
    private JButton AddArmorLevelButton;
    private Player player;

    public DeveloperModeForm(Player player){
        this.player = player;
        setVisible(true);
        setBounds(700, 200, 750, 250);
        add(MainPanel);
        AddALotOfMoneyButton.addActionListener((x)->AddALotOfMoney());
        AddALotOfOreButton.addActionListener((x)->AddALotOfOre());
        AddALotOfWoodButton.addActionListener((x)->AddALotOfWood());
        ExitButton.addActionListener((x) -> this.dispose());
        SetVisionRadiusButton.addActionListener((x)->SetVision());
        AddArmorLevelButton.addActionListener((x) -> IncreaseArmorLevel());
    }

    public void AddALotOfMoney(){
        player.addResource(ResourceType.Coins, 10000000);
        player.RefreshInfo();
    }

    public void AddALotOfOre(){
        player.addResource(ResourceType.Ore, 1000000000);
        player.RefreshInfo();
    }

    public void AddALotOfWood(){
        player.addResource(ResourceType.Wood, 10000000);
        player.RefreshInfo();
    }

    public void SetVision(){
        player.additionalVisionArea = Integer.parseInt(SetVisionField.getText().trim());
        player.RefreshInfo();
    }

    public void IncreaseArmorLevel(){
        player.Armor_lvl += 10;
        player.RefreshInfo();
    }

}
