package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Workshop extends JDialog{
    private JPanel MainPanel;
    private JList EquipmentRecipeList;
    private JButton CreateButton;
    private JButton ToCityButton;
    private JPanel ComponentPanel;
    private JLabel EquipmentNameLabel;
    private JPanel ResourcesPanel;
    private JTabbedPane ComponentTabbedPane;
    private JList OtherRecipeList;
    private JButton UpgradeButton;
    private final Player player;

    public Workshop(JDialog parent, Player player){
        super(parent, "", ModalityType.DOCUMENT_MODAL);
        setBounds(600, 300, 700, 500);
        this.player = player;
        add(MainPanel);
        DefaultListModel listModel = new DefaultListModel();
        for(int i = 0; i < EquipmentType.LastItem; i++){
            if(Equipment.recipes[i] != null)
                listModel.addElement(Equipment.names[i]);
        }
        ComponentTabbedPane.addChangeListener((x) -> ChangeTab());
        EquipmentRecipeList.setModel(listModel);
        ComponentPanel.setLayout(new BoxLayout(ComponentPanel, BoxLayout.Y_AXIS));
        EquipmentRecipeList.addListSelectionListener((x) -> SelectedEquipmentRecipe());
        OtherRecipeList.addListSelectionListener((x) -> SelectedOtherRecipe());
        CreateButton.addActionListener((x) -> CreateEquipment());
        UpgradeButton.addActionListener((x) -> CreateOtherItem());
        ToCityButton.addActionListener((x) -> this.dispose());
        UpdateResources();
        setVisible(true);
    }

    private void ChangeTab() {
        UIService.ClearPanel(ComponentPanel);
        var index = ComponentTabbedPane.getSelectedIndex();
        if(index == 0){
            SelectedEquipmentRecipe();
        }
        if(index == 1){
            SelectedOtherRecipe();
        }
    }

    private void UpdateResources(){
        player.UpdateResources(ResourcesPanel);
    }

    private void SelectedEquipmentRecipe(){
        UIService.ClearPanel(ComponentPanel);
        var index = EquipmentRecipeList.getSelectedIndex();
        if(index < 0)
            return;
        var equip = new Equipment(index);
        FillRecipePanel(equip.recipe);
    }

    private void SelectedOtherRecipe(){
        UIService.ClearPanel(ComponentPanel);
        var recipe = getOtherRecipe(OtherRecipeList.getSelectedIndex());
        FillRecipePanel(recipe);
    }

    private void FillRecipePanel(Recipe recipe){
        UIService.ClearPanel(ComponentPanel);
        if(recipe == null)
            return;
        for(Resource res:recipe.resources){
            JLabel resName = new JLabel();
            resName.setIcon(res.Icon);
            resName.setText(res.Name + ": " + Math.round(res.Number));
            ComponentPanel.add(resName);
        }
        ComponentPanel.updateUI();
    }

    private Recipe getOtherRecipe(int selectedIndex) {
        if(selectedIndex == 0){
            return player.MaskedUpgradeCost;
        }
        return null;
    }


    private void CreateEquipment(){
        var equip = new Equipment(EquipmentRecipeList.getSelectedIndex());
        if(player.isEnoughResource(equip.recipe)){
            player.addEquipment(equip.Type);
            player.TakeResourcesForRecipe(equip.recipe);
            UpdateResources();
            player.RefreshInfo();
        }
        else{
            JOptionPane.showMessageDialog(this, "Не достаточно ресурсов!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void CreateOtherItem(){
        if(OtherRecipeList.getSelectedIndex() == 0){
            UpgradeMaskedLevel();
        }
    }

    private void UpgradeMaskedLevel(){
        if(player.isEnoughResource(player.MaskedUpgradeCost)){
            player.UpgradeMaskedLevel();
            FillRecipePanel(player.MaskedUpgradeCost);
            UpdateResources();
            player.RefreshInfo();
        }
        else {
            JOptionPane.showMessageDialog(this, "Не достаточно ресурсов!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }



}
