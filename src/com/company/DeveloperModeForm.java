package com.company;

import javax.swing.*;

public class DeveloperModeForm extends JFrame{
    private JButton AddALotOfMoneyButton;
    private JButton AddALotOfOreButton;
    private JButton SetVisionRadiusButton;
    private JButton AddALotOfWoodButton;
    private JTextField SetVisionField;
    private JPanel MainPanel;
    private Player player;

    public DeveloperModeForm(Player player){
        this.player = player;
        setVisible(true);
        setBounds(700, 200, 750, 500);
        add(MainPanel);
        AddALotOfMoneyButton.addActionListener((x)->AddALotOfMoney());
        AddALotOfOreButton.addActionListener((x)->AddALotOfOre());
        AddALotOfWoodButton.addActionListener((x)->AddALotOfWood());
        SetVisionRadiusButton.addActionListener((x)->SetVision());
    }

    public void AddALotOfMoney(){
        player.addResource(ResourceType.Coins, 10000000);
        player.RefreshInfo();
    }

    public void AddALotOfOre(){
        player.addResource(ResourceType.Ore, 10000000);
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

}
