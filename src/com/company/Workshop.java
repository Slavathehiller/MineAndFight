package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.LayoutManager;

public class Workshop extends JFrame{
    private JPanel MainPanel;
    private JList RecipeList;
    private JButton CreateButton;
    private JButton ToCityButton;
    private JPanel ComponentPanel;
    private JLabel EquipmentNameLabel;
    private JPanel ResourcesPanel;
    private Player player;
    private ArrayList<JLabel> componentLabels = new ArrayList<>();
    private ArrayList<JLabel> resourceLabel = new ArrayList<>();

    public Workshop(Player player){
        setVisible(true);
        setBounds(600, 300, 700, 500);
        this.player = player;
        add(MainPanel);
        DefaultListModel listModel = new DefaultListModel();
        for(int i = 0; i < EquipmentType.LastItem; i++){
            if(Equipment.recipes[i] != null)
                listModel.addElement(Equipment.names[i]);
        }
        RecipeList.setModel(listModel);
        ComponentPanel.setLayout(new BoxLayout(ComponentPanel, BoxLayout.Y_AXIS));
        RecipeList.addListSelectionListener((x) -> SelectedRecipe());
        CreateButton.addActionListener((x) -> CreateItem());
        ToCityButton.addActionListener((x) -> this.dispose());
        UpdateResources();
    }

    private void SelectedRecipe(){
        for(int i = componentLabels.size() - 1; i >= 0; i--){
            var lbl = componentLabels.get(i);
            ComponentPanel.remove(lbl);
            componentLabels.remove(lbl);
        }
        var equip = new Equipment(RecipeList.getSelectedIndex());
        var recipe = equip.recipe;
        EquipmentNameLabel.setText(Equipment.names[RecipeList.getSelectedIndex()]);
        EquipmentNameLabel.setIcon(equip.Icon);
        for(Resource res:recipe.resources){
            JLabel resName = new JLabel();
            componentLabels.add(resName);
            resName.setIcon(res.Icon);
            resName.setText(res.Name + ": " + res.Number);
            ComponentPanel.add(resName);
        }
        ComponentPanel.updateUI();
    }

    private void UpdateResources(){
        for(int i = resourceLabel.size() - 1; i >= 0; i--){
            var lbl = resourceLabel.get(i);
            ResourcesPanel.remove(lbl);
            resourceLabel.remove(lbl);
        }
        for(Resource res:player.resources){
            JLabel resName = new JLabel();
            resourceLabel.add(resName);
            resName.setIcon(res.Icon);
            resName.setText(": " + res.Number);
            resName.setToolTipText(res.Name);
            ResourcesPanel.add(resName);
        }
        ResourcesPanel.updateUI();
    }

    private void CreateItem(){
        var equip = new Equipment(RecipeList.getSelectedIndex());
        if(player.isEnoughResource(equip.recipe)){
            player.addEquipment(equip.Type);
            for(var res:equip.recipe.resources) {
                player.addResource(res.Type, -res.Number);
            }
            UpdateResources();
            player.RefreshInfo();
        }
        else{
            JOptionPane.showMessageDialog(this, "Не достаточно ресурсов!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }


}
