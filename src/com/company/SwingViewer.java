package com.company;

import javax.swing.*;
import java.awt.*;

public class SwingViewer extends JDialog implements ISubLevelViewer {

    JLabel[][] map;
    ISubLevelModel model;
    ISubLevelController controller;
    private JPanel MainPanel;
    private JPanel LocationPanel;

    public SwingViewer(ISubLevelModel model, ISubLevelController controller){
        this.model = model;
        this.controller = controller;
        map = new JLabel[model.getMaxY()][model.getMaxX()];
        add(MainPanel);
        setVisible(true);
        setBounds(700, 100, 1200, 1200);
        GridLayout layout = new GridLayout(0, 1, 0, 0);
        LocationPanel.setLayout(layout);
        GridLayout panelLayout = new GridLayout(1, 0, 0, 0);
        for (int i = 0; i < model.getMaxY(); i++) {
            JPanel panel = new JPanel();

            panel.setLayout(panelLayout);

            for (int j = 0; j < model.getMaxX(); j++) {
                JLabel jlabel = new JLabel();
                map[i][j] = jlabel;
                panel.add(jlabel);
            }
            LocationPanel.add(panel);
        }
        controller.Initializate(this, model);
        DrawLocation();
    }

    public void DrawLocation(){
        for(var i = 0; i < model.getMaxY(); i++){
            for (var j = 0; j < model.getMaxX(); j++){
                map[i][j].setIcon(null);
            }
        }
        for(var objectArray:model.getDisplayableObjects()){
            for(var object:objectArray) {
                map[object.getY()][object.getX()].setIcon(object.getImage());
            }
        }
    }

    @Override
    public JDialog getSelf() {
        return this;
    }
}
