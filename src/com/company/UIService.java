package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UIService {

    public static ArrayList<JRadioButton> DisplaySupply(JPanel panel, ArrayList<Supply> supplies){
        return DisplaySupply(panel, supplies, false);
    }

    public static ArrayList<JRadioButton> DisplaySupply(JPanel panel, ArrayList<Supply> supplies, boolean withNumbers){
        for(int i = panel.getComponents().length - 1; i >= 0; i--){
            var lbl = panel.getComponent(i);
            panel.remove(lbl);
        }
        ButtonGroup buttonGroup = new ButtonGroup();
        var result = new ArrayList<JRadioButton>();
        for(var supply:supplies){
            JPanel OuterPanel = new JPanel();
//            OuterPanel.setLayout(new BoxLayout(OuterPanel, BoxLayout.Y_AXIS));
            OuterPanel.setLayout(new GridLayout(0, 1, 0, 0));
            panel.add(OuterPanel);
            JLabel SupplyLabel = new JLabel();
            SupplyLabel.setIcon(supply.image);
            if (withNumbers)
                SupplyLabel.setText(" x " + supply.Number);
            SupplyLabel.setToolTipText(supply.Name);
            SupplyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            OuterPanel.add(SupplyLabel);
            JRadioButton radioButton = new JRadioButton();
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setVerticalAlignment(SwingConstants.TOP);
            buttonGroup.add(radioButton);
            OuterPanel.add(radioButton);
            result.add(radioButton);
        }
        panel.updateUI();
        return result;
    }

    public static void ClearPanel(JPanel panel){
        for(int i = panel.getComponents().length - 1; i >= 0; i--){
            var lbl = panel.getComponent(i);
            panel.remove(lbl);
        }
    }
}
