package com.company;

import javax.swing.*;

public class ChooseHunt extends JFrame{
    private JPanel MainPanel;
    private JRadioButton HugeForestRadioButton;
    private JRadioButton MediumForestRadioButton;
    private JRadioButton SmallForestRadioButton;
    private JButton ExitButton;
    private JButton GoHuntButton;
    private JPanel InfoPanel;
    private Player player;
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

    }

    private void RunHunt(){
        int size = 1;
        if(MediumForestRadioButton.isSelected()){
            size = 2;
        }
        if(HugeForestRadioButton.isSelected()){
            size = 4;
        }
        HuntForm huntForm = new HuntForm(player, size);


    }


}
