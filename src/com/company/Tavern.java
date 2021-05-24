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
    Player player;

    public Tavern(Player player){
        setVisible(true);
        add(MainPanel);
        setBounds(600, 300, 700, 500);
        this.player = player;
        ToCityButton.addActionListener((x) -> this.dispose());
    }
}
