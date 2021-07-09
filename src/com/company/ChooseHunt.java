package com.company;

import javax.swing.*;
import java.awt.*;

public class ChooseHunt extends JFrame{
    private JPanel MainPanel;
    private JRadioButton HugeForestRadioButton;
    private JRadioButton MediumForestRadioButton;
    private JRadioButton SmallForestRadioButton;
    private JButton ExitButton;
    private JButton GoHuntButton;
    private JPanel InfoPanel;
    private JPanel AnimalPanel;
    private JLabel SizeLabel;
    private JPanel HerbPanel;
    private Player player;
    private int size;
    private ButtonGroup buttonGroup = new ButtonGroup();



    public ChooseHunt(Player player){
        setVisible(true);
        setBounds(700, 200, 550, 300);
        add(MainPanel);
        this.player = player;
        ExitButton.addActionListener((x) -> this.dispose());
        buttonGroup.add(SmallForestRadioButton);
        buttonGroup.add(MediumForestRadioButton);
        buttonGroup.add(HugeForestRadioButton);
        GoHuntButton.addActionListener((x) -> RunHunt());
        SmallForestRadioButton.addChangeListener((x) -> ChangeSize());
        MediumForestRadioButton.addChangeListener((x) -> ChangeSize());
        HugeForestRadioButton.addChangeListener((x) -> ChangeSize());
        SmallForestRadioButton.setSelected(true);
    }

    private void ChangeSize(){
        size = 1;
        SizeLabel.setText("10x10");
        if(MediumForestRadioButton.isSelected()){
            size = 2;
            SizeLabel.setText("20x20");
        }
        if(HugeForestRadioButton.isSelected()){
            size = 4;
            SizeLabel.setText("40x30");
        }
        for(int i = AnimalPanel.getComponents().length - 1; i >= 0; i--){
            var lbl = AnimalPanel.getComponent(i);
            AnimalPanel.remove(lbl);
        }
        var animals = HuntAnimal.GetInhabitants(size);
        for(var animal:animals){
            JLabel animalLabel = new JLabel();
            animalLabel.setIcon(animal.image);
            AnimalPanel.add(animalLabel);
        }
        AnimalPanel.updateUI();

        for(int i = HerbPanel.getComponents().length - 1; i >= 0; i--){
            var lbl = HerbPanel.getComponent(i);
            HerbPanel.remove(lbl);
        }
        var herbs = WildHerb.GetInhabitants(size);
        for(var herb:herbs){
            JLabel herbLabel = new JLabel();
            herbLabel.setIcon(herb.image);
            HerbPanel.add(herbLabel);
        }
        HerbPanel.updateUI();
    }

    private void RunHunt(){
        player.StopTimers();
        HuntForm huntForm = new HuntForm(player.InfoForm, player, size);
        player.StartTimers();
    }


}
