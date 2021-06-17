package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class UIService {

    public static ArrayList<JRadioButton> DisplaySupplyWithNumbers(JPanel panel, ArrayList<Supply> supplies){
        for(int i = panel.getComponents().length - 1; i >= 0; i--){
            var lbl = panel.getComponent(i);
            panel.remove(lbl);
        }
        ButtonGroup buttonGroup = new ButtonGroup();
        var result = new ArrayList<JRadioButton>();
        for(var supply:supplies){
            JPanel OuterPanel = new JPanel();
            OuterPanel.setLayout(new BoxLayout(OuterPanel, BoxLayout.Y_AXIS));
            panel.add(OuterPanel);
            JLabel SupplyLabel = new JLabel();
            SupplyLabel.setIcon(supply.image);
            SupplyLabel.setText(" x " + supply.Number);
            SupplyLabel.setToolTipText(supply.Name);
            OuterPanel.add(SupplyLabel);
            JRadioButton radioButton = new JRadioButton();
            radioButton.setAlignmentX(JRadioButton.CENTER);
            buttonGroup.add(radioButton);
            OuterPanel.add(radioButton);
            result.add(radioButton);
        }
        panel.updateUI();
        return result;
    }
}
