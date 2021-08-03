package com.company;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Alchemist extends JDialog{

    private JPanel ChoosePanel;
    private JPanel RecipePanel;
    private JButton CreateButton;
    private JButton ToCityButton;
    private JPanel MainPanel;
    private Player player;
    private int CurrentIndex;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private Supply[] supplies = new Supply[]{new HealthPotion(), new StaminaPotion(), new Bandage()};

    public Alchemist(JDialog parent, Player player){
        super(parent, "", Dialog.ModalityType.DOCUMENT_MODAL);
        add(MainPanel);
        setBounds(700, 200, 400, 430);
        this.player = player;
        GridLayout layout = new GridLayout(0, 3, 0, 0);
        ChoosePanel.setLayout(layout);
        var index = 0;
        for (Supply supply : supplies) {
            var elementPanel = new JPanel();
            GridLayout elementLayout = new GridLayout(0, 1, 0, 0);
            elementPanel.setLayout(elementLayout);
            ChoosePanel.add(elementPanel);
            var ImageLabel = new JLabel();
            ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ImageLabel.setIcon(supply.image);
            var TextLabel = new JLabel();
            TextLabel.setHorizontalAlignment(SwingConstants.CENTER);
            TextLabel.setText(supply.Name);
            elementPanel.add(ImageLabel);
            elementPanel.add(TextLabel);
            var elementRButton = new JRadioButton();
            elementRButton.setHorizontalAlignment(SwingConstants.CENTER);
            elementRButton.setVerticalAlignment(SwingConstants.TOP);
            int finalIndex = index;
            elementRButton.addItemListener((x) -> ChooseElement(finalIndex));
            index++;
            buttonGroup.add(elementRButton);
            elementPanel.add(elementRButton);
        }
        GridLayout recipeLayout = new GridLayout(0, 5, 0, 0);
        RecipePanel.setLayout(recipeLayout);
        CreateButton.addActionListener((x) -> Create());
        ToCityButton.addActionListener((x) -> this.dispose());

        setVisible(true);
    }
    private void ChooseElement(int index){
        for(int i = RecipePanel.getComponents().length - 1; i >= 0; i--){
            var lbl = RecipePanel.getComponent(i);
            RecipePanel.remove(lbl);
        }
        for(Resource resource:supplies[index].recipe.resources){
            var recipePanel = new JPanel();
            GridLayout elementLayout = new GridLayout(0, 1, 0, 0);
            recipePanel.setLayout(elementLayout);
            var label = new JLabel();
            label.setIcon(resource.Icon);
            label.setText(" : " + resource.Number);
            label.setToolTipText(resource.Name);
            recipePanel.add(label);
            RecipePanel.add(recipePanel);
        }
        CurrentIndex = index;
        RecipePanel.updateUI();
    }

    private void Create(){
        var recipe = supplies[CurrentIndex].recipe;
        if(player.isEnoughResource(recipe)){
            player.addSupply(supplies[CurrentIndex].getClass());
            for(var res:recipe.resources) {
                player.addResource(res.Type, -res.Number);
            }
            player.RefreshInfo();
        }
        else{
            JOptionPane.showMessageDialog(this, "Не достаточно ресурсов!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }

    }

}
