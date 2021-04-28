package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HuntForm extends JDialog{
    private JPanel MainPanel;
    private JPanel InfoPanel;
    private JPanel HuntPanel;
    private JLabel Equipmentlbl;
    private JPanel ControlPanel;
    private JButton ExitButton;
    private JLabel slingNumlbl;
    private JLabel lblSling;
    private JLabel bowNumlbl;
    private JLabel lblHuntBow;
    private JLabel SpyGlassLbl;
    private JLabel lblCorralSpear;
    private JLabel corralSpearNumlbl;

    int maxX = 30;
    int maxY = 20;

    JLabel[][] map = new JLabel[maxY][maxX];
    ArrayList<HuntAnimal> animals = new ArrayList<>();
    Player player;

    ImageIcon hiddenIcon = new ImageIcon(getClass().getResource("/forest_icon_30x30.png"));
    ImageIcon hunterIcon = new ImageIcon(getClass().getResource("/hunter_30x30.png"));
    ImageIcon hunterWithDogIcon = new ImageIcon(getClass().getResource("/hunter_with_dog_30x30.png"));


    public HuntForm(Player player) {
        this.player = player;
        player.Y = maxY - 1;
        player.X = maxX / 2;
        PlayerInfoToForm();
        setVisible(true);
        setSize(250 + maxX * 30, 100 + maxY * 30);
        setLocationRelativeTo(null);
        add(MainPanel);
        GridLayout layout = new GridLayout(0, 1, 0, 0);
        HuntPanel.setLayout(layout);
        GridLayout panelLayout = new GridLayout(1, 0, 0, 0);
        for (int i = 0; i < maxY; i++) {
            JPanel panel = new JPanel();

            panel.setLayout(panelLayout);

            for (int j = 0; j < maxX; j++) {
                JLabel jlabel = new JLabel();
                map[i][j] = jlabel;
                panel.add(jlabel);
            }
            HuntPanel.add(panel);
        }


        PopulateMap();
        DrawMap();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_W && player.Y > 0){
                    player.Y -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_S && player.Y < maxY - 1){
                    player.Y += 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_A && player.X > 0){
                    player.X -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_D && player.X < maxX - 1){
                    player.X += 1;
                }
                ActObjects();
                DrawMap();
                CheckIfCatch();
            }
        });
        ExitButton.addActionListener((x) -> this.dispose());
    }

    private void ActObjects(){
        for (HuntAnimal animal:animals){
            animal.Act();
        }
    }

    public void PopulateMap(){
        double chanceToSpawn = Math.floor(Math.random()*100);
        HuntAnimal animal;
        if(chanceToSpawn < 10) {
            animal = new Hog(maxX, maxY);
        }
        else{
            if(chanceToSpawn < 40) {
                animal = new Wolf(maxX, maxY);
            }
            else{
                animal = new Hare(maxX, maxY);
            }
        }
        animal.X = Math.round(Math.random() * maxX - 1);
        animal.Y = Math.round(Math.random() * maxY - 1);
        animals.add(animal);

    }

    public void CheckIfCatch(){
        for (HuntAnimal animal:animals){
            if(animal.X == player.X && animal.Y == player.Y){
                if(player.haveEquipment(animal.equipNeeded)){
                    animals.remove(animal);
                    String message = "Ваша добыча: " + animal.Name + "\n" + "Вы получаете: \n";
                    message += animal.drop.getDetails();
                    JOptionPane.showMessageDialog(HuntPanel, message, "Охота удалась!", JOptionPane.INFORMATION_MESSAGE);
                    player.addDrop(animal.drop);
                    player.RefreshInfo();
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(HuntPanel, "У вас нет: " + Equipment.names[animal.equipNeeded] , "Охота не удалась!", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }

    private void DrawObject(int x, int y){
        for (HuntAnimal animal:animals){
            if(animal.X == x && animal.Y == y){
                map[y][x].setIcon(animal.image);
                return;
            }
        }
        map[y][x].setIcon(null);
    }

    private void DrawMap(){
        for(int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if(DistanceToPlayer(j, i) > player.SeenArea())
                    map[i][j].setIcon(hiddenIcon);
                else
                    DrawObject(j, i);
            }
        }

        if(player.numEquipment(EquipmentType.HuntDog) < 1){
            map[player.Y][player.X].setIcon(hunterIcon);
        }
        else{
            map[player.Y][player.X].setIcon(hunterWithDogIcon);
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

        var corralSpearNumber = player.numEquipment(EquipmentType.CorralSpear);
        if(corralSpearNumber > 0)
            corralSpearNumlbl.setText(Integer.toString(corralSpearNumber));
        else
            lblCorralSpear.setVisible(false);


        SpyGlassLbl.setVisible(player.haveEquipment(EquipmentType.SpyGlass));
        }

}
