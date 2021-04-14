package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HuntForm  extends JFrame{
    private JPanel MainPanel;
    private JPanel InfoPanel;
    private JPanel HuntPanel;
    private JLabel Equipmrntlbl;
    private JPanel ControlPanel;
    private JButton ExitButton;
    private JLabel slingNumlbl;
    private JLabel lblSling;
    private JLabel bowNumlbl;
    private JLabel lblHuntBow;

    int maxX = 20;
    int maxY = 30;

    JLabel[][] map = new JLabel[maxY][maxX];
    Player player;

    ImageIcon hiddenIcon = new ImageIcon(getClass().getResource("/forest_icon_30x30.png"));
    ImageIcon hunterIcon = new ImageIcon(getClass().getResource("/hunter_30x30.png"));
    ImageIcon hunterWithDogIcon = new ImageIcon(getClass().getResource("/hunter_with_dog_30x30.png"));


    public HuntForm(Player player) {
        this.player = player;
        player.Y = maxX - 1;
        player.X = maxY / 2;
        PlayerInfoToForm();
        setVisible(true);
        setSize(250 + maxY * 30, 100 + maxX * 30);
        setLocationRelativeTo(null);
        add(MainPanel);
        GridLayout layout = new GridLayout(0, 1, 1, 1);
        for (int i = 0; i < maxY; i++) {
            JPanel panel = new JPanel();

            panel.setLayout(layout);
            for (int j = 0; j < maxX; j++) {
                JLabel jlabel = new JLabel();
                map[i][j] = jlabel;
       //         var icon = new ImageIcon(getClass().getResource("/forest_icon_30x30.png"));
         //       jlabel.setIcon(icon);
                panel.add(jlabel);
            }
            HuntPanel.add(panel);
        }

        DrawMap();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_W && player.Y > 0){
                    player.Y -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_S && player.Y < maxX - 1){
                    player.Y += 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_A && player.X > 0){
                    player.X -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_D && player.X < maxY - 1){
                    player.X += 1;
                }
                DrawMap();
            }
        });
        ExitButton.addActionListener((x) -> this.dispose());
    }

    private void DrawMap(){
        for(int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if(DistanceToPlayer(i, j) > player.SeenArea())
                    map[i][j].setIcon(hiddenIcon);
                else
                    map[i][j].setIcon(null);
            }
        }

        if(player.numEquipment(EquipmentType.HuntDog) < 1){
            map[player.X][player.Y].setIcon(hunterIcon);
        }
        else{
            map[player.X][player.Y].setIcon(hunterWithDogIcon);
        }

    }

    private double DistanceToPlayer(int x, int y){
        return Math.floor(Math.abs(Math.sqrt(Math.pow(x - player.X, 2) + Math.pow(player.Y - y, 2))));
    }

    private void PlayerInfoToForm(){
        var slingNumber = player.numEquipment(EquipmentType.Sling);
        if (slingNumber > 0)
            slingNumlbl.setText(Integer.toString(slingNumber));
        else
            lblSling.setVisible(false);

        var huntBowNumber = player.numEquipment(EquipmentType.HuntBow);
        if(huntBowNumber > 0)
            bowNumlbl.setText(Integer.toString(huntBowNumber));
        else
            lblHuntBow.setVisible(false);


    }
}
