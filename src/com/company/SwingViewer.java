package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel StatsPanel;
    private JProgressBar HealthBar;
    private JProgressBar StaminaBar;
    private JPanel ResourcePanel;
    private JPanel SupplyPanel;
    private JButton InventoryButton;
    private JPanel StatePanel;

    public SwingViewer(JDialog parent, ISubLevelModel model, ISubLevelController controller){
        super(parent, "", ModalityType.DOCUMENT_MODAL);
        this.model = model;
        this.controller = controller;
        map = new JLabel[model.getMaxY()][model.getMaxX()];
        add(MainPanel);
        setBounds(700, 100, 1450, 1200);
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
        ResourcePanel.setLayout(new BoxLayout(ResourcePanel, BoxLayout.Y_AXIS));
        InventoryButton.addActionListener(OpenInventory);
        controller.Initializate(this, model);
        InitializeControl();
        DrawLocation();
        setVisible(true);
    }

    public void PlayerDeadMessage(){
        JOptionPane.showMessageDialog(LocationPanel, "Вы получили слишком много урона\n и не можете продолжать", "Неудача", JOptionPane.WARNING_MESSAGE );
    }

    public void EndLevel(){
        this.dispose();
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
        RefreshPlayerInfo();
    }

    private void RefreshStats(){
        StaminaBar.setValue(Math.round(model.getPlayer().getStamina()));
        StaminaBar.setToolTipText("Энергия (" + model.getPlayer().getStamina() + "/100)");
        HealthBar.setValue(Math.round(model.getPlayer().getHealth()));
        var health = model.getPlayer().getHealth();
        health = Math.round(health * 10) / 10f;
        HealthBar.setToolTipText("Здоровье (" + health + "/100)");
    }

    public void RefreshPlayerInfo(){
        RefreshStats();
        RefreshResourcesInfo();
        RefreshSupplyInfo();
        model.getPlayer().UpdateStateInfo(StatePanel);

    }

    @Override
    public void ShowMessage_NotEnoughStamina(){
        JOptionPane.showMessageDialog(LocationPanel, "Недостаточно энергии.", "Неудача", JOptionPane.WARNING_MESSAGE );
    }

    @Override
    public void ShowMessage_CustomMessage(String message) {
        JOptionPane.showMessageDialog(LocationPanel, message, "", JOptionPane.WARNING_MESSAGE );
    }

    private final ActionListener OpenInventory = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Inventory inventory = new Inventory(self(), model.getPlayer());

            RefreshPlayerInfo();
            self().requestFocusInWindow();
        }
    };

    private SwingViewer self(){
        return this;
    }

    public void RefreshResourcesInfo(){
        model.getPlayer().UpdateResources(ResourcePanel);
    }

    public void RefreshSupplyInfo(){
        model.getPlayer().UpdateSuppliesInfo(SupplyPanel);
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
