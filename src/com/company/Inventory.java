package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Inventory extends JDialog{
    private JPanel MainPanel;
    private JButton UseSupplyButton;
    private JButton ExitButton;
    private JPanel SuppliesPanel;
    private ArrayList<JRadioButton> radioButtons;
    private int NumberSelected = -1;
    Player player;


    public Inventory(JDialog parent, Player player){
        super(parent, "", ModalityType.DOCUMENT_MODAL);
        add(MainPanel);
        setBounds(300, 300, 500, 500);
        pack();
        this.player = player;
        UseSupplyButton.addActionListener((x) -> {
            try {
                UseSupply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ExitButton.addActionListener((x) -> this.dispose());
        RefreshSupplyNumbers();
        setVisible(true);
    }

    private void SelectSupply(){
        for(var i = 0; i < radioButtons.size(); i++){
            if(radioButtons.get(i).isSelected()){
                NumberSelected = i;
                break;
            }
        }
    }

    private void RefreshSupplyNumbers(){
        radioButtons = UIService.DisplaySupplyWithNumbers(SuppliesPanel, player.supplies);
        for(var radioButton:radioButtons){
            radioButton.addChangeListener((x) -> SelectSupply());
        }
        if(NumberSelected > -1)
            radioButtons.get(NumberSelected).setSelected(true);
    }

    private void UseSupply() throws Exception {
        if(NumberSelected < 0)
            return;
        Supply supply = player.GetSupply(player.supplies.get(NumberSelected).getClass());
        if(supply != null && supply.Number > 0) {
            supply.Use(player);
            if (supply.Number < 1) {
                NumberSelected = -1;
            }
        }
        RefreshSupplyNumbers();
        player.RefreshInfo();
    }
}
