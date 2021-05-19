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
    private JLabel lblBearSpear;
    private JLabel bearSpearNumlbl;
    private int size;
    private float StepStaminaConsumption = 0.5f;

    static int smallSize = 1;
    static int mediumSize = 2;
    static int hugeSize = 4;

    int maxX;
    int maxY;

    JLabel[][] map;
    ArrayList<HuntAnimal> animals = new ArrayList<>();
    ArrayList<ArrayList<Track>> tracks = new ArrayList<>();

    Player player;

    ImageIcon hiddenIcon = new ImageIcon(getClass().getResource("/forest_icon_30x30.png"));
    ImageIcon hunterIcon = new ImageIcon(getClass().getResource("/hunter_30x30.png"));
    ImageIcon hunterWithDogIcon = new ImageIcon(getClass().getResource("/hunter_with_dog_30x30.png"));
    ImageIcon questionIcon = new ImageIcon(getClass().getResource("/question_icon_30x30.png"));

    public HuntForm(Player player, int size) {
        this.player = player;
        this.size = size;
        if(size == smallSize){
            maxX = 10;
            maxY = 10;
        }
        if(size == mediumSize){
            maxX = 20;
            maxY = 20;
        }
        if(size == hugeSize){
            maxX = 40;
            maxY = 30;
        }
        map = new JLabel[maxY][maxX];
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
                if(player.getStamina() < StepStaminaConsumption){
                    JOptionPane.showMessageDialog(HuntPanel, "Вы слишком устали и не можете продолжать охоту", "Невозможно продолжать!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
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
                player.addStamina(-StepStaminaConsumption);
                player.RefreshInfo();
            }
        });
        ExitButton.addActionListener((x) -> CloseForm());
    }

    private void CloseForm(){
        player.StartTimers();
        this.dispose();
    }

    private void ActObjects(){
        for (HuntAnimal animal:animals){
            animal.Act();
        }
        if(tracks.size() > 0) {
            for(int i = tracks.size() - 1; i >= 0; i--) {
                var trace = tracks.get(i);
                for (int j = trace.size() - 1; j >= 0; j--) {
                    var track = trace.get(j);
                    track.lifeTime -= 1;
                    if (track.lifeTime == 0) {
                        trace.remove(j);
                    }
                }
            }
        }
    }

    private HuntAnimal GenerateAnimal(){
        double chanceToSpawn = Math.floor(Math.random()*100);
        HuntAnimal animal;
        if(chanceToSpawn < 20) {
            if(chanceToSpawn < 10) {
                animal = new Hog(maxX, maxY);
            }
            else{
                animal = new Bear(maxX, maxY);
            }
        }
        else{
            if(chanceToSpawn < 40) {
                animal = new Wolf(maxX, maxY);
            }
            else{
                animal = new Hare(maxX, maxY);
            }
        }
        return animal;
    }

    public void PopulateMap(){
        for(var i = 0; i < size; i++) {
            HuntAnimal animal;
            do {
                animal = GenerateAnimal();
            }
            while (!IsAllowed(animal.habitat));
            animal.X = Math.round(Math.random() * maxX - 1);
            animal.Y = Math.round(Math.random() * maxY - 1);
            animals.add(animal);
            tracks.add(animal.tracks);
        }
    }

    private boolean IsAllowed(int[] habitat){
        for(var hab:habitat){
            if(hab == this.size){
                return true;
            }
        }
        return false;
    }

    public void CheckIfCatch(){
        for (HuntAnimal animal:animals){
            if(animal.X == player.X && animal.Y == player.Y){
                if(player.haveEquipment(animal.equipNeeded)){
                    if(player.getStamina() < animal.StaminaToObtain){
                        JOptionPane.showMessageDialog(HuntPanel, "Требуется " + animal.StaminaToObtain + " энергии, у вас " + player.getStamina() , "Охота не удалась!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    animals.remove(animal);
                    String message = "Ваша добыча: " + animal.Name + "\n" + "Вы получаете: \n";
                    message += animal.drop.getDetails();
                    JOptionPane.showMessageDialog(HuntPanel, message, "Охота удалась!", JOptionPane.INFORMATION_MESSAGE);
                    double chance = Math.random();
                    if(chance > Equipment.durabilities[animal.equipNeeded]){
                        player.addEquipment(animal.equipNeeded, -1);
                        JOptionPane.showMessageDialog(HuntPanel, "Оборудование пришло в негодность: \n" + Equipment.names[animal.equipNeeded], "Поломка!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    chance = Math.random();
                    if(chance < animal.Danger){
                        player.addHealth(-animal.Damage);
                        JOptionPane.showMessageDialog(HuntPanel, animal.Name + " наносит вам урон", "Несчастный случай", JOptionPane.INFORMATION_MESSAGE);

                    }
                    player.addStamina(-animal.StaminaToObtain);
                    player.addDrop(animal.drop);
                    player.RefreshInfo();
                    PlayerInfoToForm();
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
        for(var trace:tracks){
            for(Track track:trace){
                if(track.X == x && track.Y == y){
                    if(track.IsWeak()){
                        map[y][x].setIcon(track.weakImage);
                    }
                    else {
                        map[y][x].setIcon(track.strongImage);
                    }
                    return;
                }
            }
        }
        map[y][x].setIcon(null);
    }

    private HuntAnimal AnimalAt(int x, int y) {
        for (HuntAnimal animal : animals) {
            if (animal.X == x && animal.Y == y) {
                return animal;
            }
        }
        return null;
    }


    private void DrawMap(){
        for(int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (DistanceToPlayer(j, i) > player.SeenArea()) {
                    var animal = AnimalAt(j, i);
                    if (DistanceToPlayer(j, i) <= player.senseRadius() && animal != null) {
                        map[i][j].setIcon(questionIcon);
                    } else {
                        map[i][j].setIcon(hiddenIcon);
                    }
                } else
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
        else {
            lblSling.setVisible(false);
            slingNumlbl.setVisible(false);
        }

        var huntBowNumber = player.numEquipment(EquipmentType.HuntBow);
        if(huntBowNumber > 0)
            bowNumlbl.setText(Integer.toString(huntBowNumber));
        else {
            lblHuntBow.setVisible(false);
            bowNumlbl.setVisible(false);
        }

        var corralSpearNumber = player.numEquipment(EquipmentType.CorralSpear);
        if(corralSpearNumber > 0)
            corralSpearNumlbl.setText(Integer.toString(corralSpearNumber));
        else{
            lblCorralSpear.setVisible(false);
            corralSpearNumlbl.setVisible(false);
        }

        var bearSpearNumber = player.numEquipment(EquipmentType.BearSpear);
        if(bearSpearNumber > 0)
            bearSpearNumlbl.setText(Integer.toString(bearSpearNumber));
        else {
            lblBearSpear.setVisible(false);
            bearSpearNumlbl.setVisible(false);
        }

        SpyGlassLbl.setVisible(player.haveEquipment(EquipmentType.SpyGlass));
        }

}
