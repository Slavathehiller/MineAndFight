package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingViewer extends JDialog implements ISubLevelViewer {

    JLabel[][] map;
    ISubLevelModel model;
    ISubLevelController controller;
    private JPanel MainPanel;
    private JPanel LocationPanel;
    private JPanel ControllPanel;
    private JPanel LogPanel;
    private JTextArea TALogger;
    private JPanel InfoPanel;

    public SwingViewer(ISubLevelModel model, ISubLevelController controller){
        this.model = model;
        this.controller = controller;
        map = new JLabel[model.getMaxY()][model.getMaxX()];
        add(MainPanel);
        setVisible(true);
        setBounds(700, 100, 1400, 1200);
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
        InitializeControl();
        DrawLocation();
    }

    public void DrawLocation(){
        for(var i = 0; i < model.getMaxY(); i++){
            for (var j = 0; j < model.getMaxX(); j++){
                map[i][j].setIcon(null);
                map[i][j].setToolTipText(null);
            }
        }
        for(var objectArray:model.getDisplayableObjects()){
            for(var object:objectArray) {
                map[object.getY()][object.getX()].setIcon(object.getImage());
                map[object.getY()][object.getX()].setToolTipText(object.getToolTip());
            }
        }
        Log(model.getLog());
    }

    public void Log(String message){
        if (message != null && message.length() > 0)
            TALogger.setText(message + "\n" + TALogger.getText());
    }

    @Override
    public void InitializeControl() {
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int direction = Direction.UNDEFINED;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    direction = Direction.UP;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    direction = Direction.DOWN;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    direction = Direction.LEFT;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    direction = Direction.RIGHT;
                }
                controller.React(direction);
            }
        });
    }
}
