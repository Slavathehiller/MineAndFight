package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class HuntForm extends JDialog implements IInfoForm{
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
    private JPanel StatsPanel;
    private JProgressBar HealthBar;
    private JProgressBar StaminaBar;
    private JPanel ResourcePanel;
    private JPanel SupplyPanel;
    private JButton InventoryButton;
    private final int size;
    private final float StepStaminaConsumption = 0.5f;
    public HuntForm self = this;

    static int smallSize = 1;
    static int mediumSize = 2;
    static int hugeSize = 4;

    int maxX;
    int maxY;

    JLabel[][] map;
    ArrayList<HuntAnimal> animals = new ArrayList<>();
    ArrayList<ArrayList<Track>> tracks = new ArrayList<>();
    ArrayList<WildHerb> herbs = new ArrayList<>();

    Player player;

    ImageIcon hiddenIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/forest_icon_30x30.png")));
    ImageIcon hunterIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/hunter_30x30.png")));
    ImageIcon hunterWithDogIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/hunter_with_dog_30x30.png")));
    ImageIcon questionIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/question_icon_30x30.png")));

    public HuntForm(JDialog parent, Player player, int size) {
        super(parent, "", ModalityType.DOCUMENT_MODAL);
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
        setSize(250 + maxX * 30, 100 + maxY * 30);
        setLocationRelativeTo(null);
        add(MainPanel);
        GridLayout layout = new GridLayout(0, 1, 0, 0);
        HuntPanel.setLayout(layout);
        GridLayout panelLayout = new GridLayout(1, 0, 0, 0);
        var dimension = new Dimension();
        dimension.setSize(maxX * 30, maxY * 30);
        HuntPanel.setMaximumSize(dimension);
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
                    JOptionPane.showMessageDialog(HuntPanel, "???? ?????????????? ???????????? ?? ???? ???????????? ???????????????????? ??????????", "???????????????????? ????????????????????!", JOptionPane.INFORMATION_MESSAGE);
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
                CheckIfCatch();
                ActObjects();
                CheckIfCatch();
                DrawMap();
                CheckIfFound();
                player.addStamina(-StepStaminaConsumption);
                player.RefreshInfo();
                RefreshStats();
            }
        });
        ExitButton.addActionListener((x) -> CloseForm());
        InventoryButton.addActionListener(OpenInventory);
        ResourcePanel.setLayout(new BoxLayout(ResourcePanel, BoxLayout.Y_AXIS));
        setVisible(true);
    }

    private final ActionListener OpenInventory = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Inventory inventory = new Inventory(self(), self, player);
            self().requestFocusInWindow();
        }
    };
    private JDialog self(){
        return this;
    }

    private void RefreshStats(){
        StaminaBar.setValue(Math.round(player.getStamina()));
        StaminaBar.setToolTipText("?????????????? (" + player.getStamina() + "/100)");
        HealthBar.setValue(Math.round(player.getHealth()));
        HealthBar.setToolTipText("???????????????? (" + player.getHealth() + "/100)");
        InfoPanel.updateUI();
    }


    private void CloseForm(){
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
            if(chanceToSpawn > 10){
                animal = new Hog(maxX, maxY);
            }
            else{
                if(chanceToSpawn < 5)
                    animal = new Bear(maxX, maxY);
                else
                    animal = new Moose(maxX, maxY);
            }
        }
        else{
            if(chanceToSpawn < 40) {
                animal = new Wolf(maxX, maxY);
            }
            else{
                if(chanceToSpawn < 70)
                    animal = new Hare(maxX, maxY);
                else
                    animal = new Capercaillie(maxX, maxY);
            }
        }
        return animal;
    }

    private WildHerb GenerateHerb(){
        double chanceToSpawn = Math.floor(Math.random()*100);
        WildHerb herb;
        if(chanceToSpawn < 15) {
            herb = new WildOnion();
        }
        else
            if(chanceToSpawn < 25)
                herb = new Hemp();
            else
                if(chanceToSpawn < 50)
                    herb = new Sage();
                else
                    if(chanceToSpawn < 75)
                        herb = new Plantain();
                    else
                        herb = new Mushroom();
        return herb;
    }

    public void PopulateMap(){
        for(var i = 0; i < size; i++) {
            HuntAnimal animal;
            do {
                animal = GenerateAnimal();
            }
            while (!IsAllowed(animal.habitat));
            animal.X = Math.max(Math.round(Math.random() * maxX - 1), 0);
            animal.Y = Math.max(Math.round(Math.random() * maxY - 1), 0);
            animals.add(animal);
            tracks.add(animal.tracks);
        }

        for(var i = 0; i < size * 2; i++) {
            WildHerb herb;
            do {
                herb = GenerateHerb();
            }
            while (!IsAllowed(herb.habitat));
            long x;
            long y;
            do {
                x = Math.max(Math.round(Math.random() * maxX - 1), 0);
                y = Math.max(Math.round(Math.random() * maxY - 1), 0);
            }
            while(HerbAt(x, y) != null);
            herb.X = x;
            herb.Y = y;
            herbs.add(herb);
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

    public void CheckIfFound(){
        for(WildHerb herb:herbs){
            if(herb.X == player.X && herb.Y == player.Y){
                herbs.remove(herb);
                String message = "???? ??????????: " + herb.Name + "\n" + "???? ??????????????????: \n";
                message += herb.drop.getDetails();
                JOptionPane.showMessageDialog(HuntPanel, message, "??????????????!", JOptionPane.INFORMATION_MESSAGE);
                player.addDrop(herb.drop);
                return;
            }
            player.RefreshInfo();
            PlayerInfoToForm();
        }
    }

    public void CheckIfCatch(){
        for (HuntAnimal animal:animals){
            if(animal.X == player.X && animal.Y == player.Y){
                if(player.haveEquipment(animal.equipNeeded)){
                    if(player.getStamina() < animal.StaminaToObtain){
                        JOptionPane.showMessageDialog(HuntPanel, "?????????????????? " + animal.StaminaToObtain + " ??????????????, ?? ?????? " + player.getStamina() , "?????????? ???? ??????????????!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    animals.remove(animal);
                    String message = "???????? ????????????: " + animal.Name + "\n" + "???? ??????????????????: \n";
                    message += animal.drop.getDetails();
                    JOptionPane.showMessageDialog(HuntPanel, message, "?????????? ??????????????!", JOptionPane.INFORMATION_MESSAGE);
                    double chance = Math.random();
                    if(chance > Equipment.durabilities[animal.equipNeeded]){
                        player.addEquipment(animal.equipNeeded, -1);
                        JOptionPane.showMessageDialog(HuntPanel, "???????????????????????? ???????????? ?? ????????????????????: \n" + Equipment.names[animal.equipNeeded], "??????????????!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    chance = Math.random();
                    if(chance < animal.Danger){
                        player.addHealth(-animal.Damage);
                        JOptionPane.showMessageDialog(HuntPanel, animal.Name + " ?????????????? ?????? ????????", "???????????????????? ????????????", JOptionPane.INFORMATION_MESSAGE);

                    }
                    player.addStamina(-animal.StaminaToObtain);
                    player.addDrop(animal.drop);
                    player.RefreshInfo();
                    PlayerInfoToForm();
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(HuntPanel, "?? ?????? ??????: " + Equipment.names[animal.equipNeeded] , "?????????? ???? ??????????????!", JOptionPane.INFORMATION_MESSAGE);
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
        for (WildHerb herb:herbs){
            if(herb.X == x && herb.Y == y){
                map[y][x].setIcon(herb.image);
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

    private WildHerb HerbAt(long x, long y) {
        for (WildHerb herb : herbs) {
            if (herb.X == x && herb.Y == y) {
                return herb;
            }
        }
        return null;
    }

    private void DrawMap(){
        for(int i = 0; i < maxY; i++) {
            for(int j = 0; j < maxX; j++) {
                if(DistanceToPlayer(j, i) > player.SeenArea()) {
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

        RefreshStats();
        player.UpdateResources(ResourcePanel);
        player.UpdateSuppliesInfo(SupplyPanel);

        SpyGlassLbl.setVisible(player.haveEquipment(EquipmentType.SpyGlass));
        }

    @Override
    public void RefreshInfo() {
        RefreshStats();
    }
}
