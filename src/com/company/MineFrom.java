package com.company;

import javax.swing.*;
import java.awt.event.*;

public class MineFrom extends JFrame{
    private JPanel MainPanel;
    private JPanel CityPanel;
    private JPanel MinePanel;
    private JPanel BlackSmithPanel;
    private JButton MineButton;
    private JButton ToBlackSmithButton;
    private JButton MineOreButton;
    private JButton ToCityButtonM;
    private JButton UpgradeArmorButton;
    private JButton UpgradePickaxeButton;
    private JButton ToCityButtonBS;
    private JLabel OreLabelM;
    private JLabel OreLabel;
    private JLabel PickaxeLabel;
    private JLabel ArmorLabel;
    private JPanel ThicketPanel;
    private JButton ThicketButton;
    private JButton DungeonButton;
    private JButton ToCityButtonAD;
    private JButton ToRuinsButton;
    private JPanel AdventurePanel;
    private JButton ToAdventureButton;
    private JButton ToNewCityButton;
    private JButton AttackWolfButton;
    private JButton AttackGiantSpiderButton;
    private JButton AttackWhereBearButton;
    private JButton AttackCyclopeButton;
    private JButton AttackTrollButton;
    private JButton AttackOgreButton;
    private JButton ToAdventureButtonF;
    private JPanel InfoPanel;
    private JPanel RuinsPanel;
    private JButton AttackGoblinButton;
    private JButton AttackOrcButton;
    private JButton ToAdventureButtonR;
    private JPanel ShopPanel;
    private JButton BuyMagicTorchButton;
    private JButton ToShopButton;
    private JLabel MagicTorchLabel;
    private JButton GoToCityButtonS;
    private JButton BuySilverSpearButton;
    private JLabel SilverSpearLabel;
    private JButton AddThousandOfOre;
    private JButton SellOreButton;
    private JButton OreSellAmountUpButton;
    private JButton OreSellAmountDownButton;
    private JLabel CoinsLabel;
    private JButton BuyLightRingButton;
    private JLabel LightRingLabel;
    private JLabel WoodLabel;
    private JButton ToForestButton;
    private JButton ExtractWoodButton;
    private JButton HuntButton;
    private JPanel ForestPanel;
    private JButton GoToCityButtonF;
    private JButton UpgradeAxeButton;
    private JLabel AxeLabel;
    private JButton BuySlingButton;
    private JLabel SlingNumlbl;
    private JLabel HuntBowNumlbl;
    private JButton BuyHuntBowButton;
    private JButton BuyHuntDogButton;
    private JLabel HuntDogLabel;
    private int OreSellAmount = 1000;
    private double OreCost = 0.02;

    Player player = new Player();
    Shop shop = new Shop();

    public void dataFromPlayerToForm()
    {
        OreLabel.setText("Руда: " + player.OreCount);
        WoodLabel.setText("Дерево: " + player.WoodCount);
        SellOreButton.setText("Обмен(" + OreSellAmount + ")");
        CoinsLabel.setText("Монеты: " + player.Coins);
        PickaxeLabel.setText("Кирка: " + player.Pickaxe_lvl);
        AxeLabel.setText("Топор: " + player.Axe_lvl);
        MineOreButton.setText("Добывать руду(" + player.Pickaxe_lvl + ")");
        UpgradePickaxeButton.setText("Улучшение кирки(" + player.PickaxeUpgradeCost + ")");
        ArmorLabel.setText("Доспех: " + player.Armor_lvl);
        UpgradeArmorButton.setText("Улучшение доспеха(" + player.ArmorUpgradeCost + ")");
        MagicTorchLabel.setVisible(player.haveArtefact(Artefacts.MagicTorch));
        SilverSpearLabel.setVisible(player.haveArtefact(Artefacts.SilverSpear));
        LightRingLabel.setVisible(player.haveArtefact(Artefacts.LightRing));
//        HuntDogLabel.setVisible(player.haveEquipment();
        SlingNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.Sling)));
        HuntBowNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.HuntBow)));
    }
    public void dataFromShopToForm()
    {

    }


    public MineFrom(){
        setVisible(true);
        setBounds(700, 200, 750, 300);
        add(MainPanel);
        MineButton.addActionListener(goToMine);
        ToCityButtonM.addActionListener(goToCity);
        ToBlackSmithButton.addActionListener(goToBlackSmith);
        ToShopButton.addActionListener(goToShop);
        ToCityButtonBS.addActionListener(goToCity);
        ToRuinsButton.addActionListener(goToRuins);
        ToForestButton.addActionListener(goToForest);
        GoToCityButtonF.addActionListener(goToCity);
        ExtractWoodButton.addActionListener(extractWood);
        MineOreButton.addActionListener(mineOre);
        OreSellAmountUpButton.addActionListener(SellOreAmountUp);
        OreSellAmountDownButton.addActionListener(SellOreAmountDown);
        AddThousandOfOre.addActionListener(AddThousandOfOre1);
        SellOreButton.addActionListener(TradeOreForCoins);
        UpgradePickaxeButton.addActionListener(upgradePickaxe);
        UpgradeAxeButton.addActionListener(upgradeAxe);
        UpgradeArmorButton.addActionListener(upgradeArmor);
        BuyMagicTorchButton.addActionListener(buyArtefact1);
        BuySilverSpearButton.addActionListener(buyArtefact2);
        BuyLightRingButton.addActionListener(buyArtefact3);
        GoToCityButtonS.addActionListener(goToCity);
        ToAdventureButton.addActionListener(goToAdventure);
        ToCityButtonAD.addActionListener(goToCity);
        ThicketButton.addActionListener(goToThicket);
        ToAdventureButtonF.addActionListener(goToAdventure);
        AttackWolfButton.addActionListener(AttackWolf);
        AttackGiantSpiderButton.addActionListener(AttackGiantSpider);
        AttackWhereBearButton.addActionListener(AttackWhereBear);
        AttackCyclopeButton.addActionListener(AttackCyclope);
        AttackGoblinButton.addActionListener(AttackGoblin);
        AttackOrcButton.addActionListener(AttackOrc);
        AttackTrollButton.addActionListener(AttackTroll);
        AttackOgreButton.addActionListener(AttackOgre);
        ToAdventureButtonR.addActionListener(goToAdventure);
        HuntButton.addActionListener(goHunt);
        BuySlingButton.addActionListener(buyEquipment1);
        BuyHuntBowButton.addActionListener(buyEquipment2);
        BuyHuntDogButton.addActionListener(buyEquipment6);


        MinePanel.setFocusable(true);
//        MinePanel.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//                if(e.getKeyCode() == KeyEvent.VK_W){
//                    JOptionPane.showMessageDialog(MainPanel, "Нажата клавиша w");
//                }
//            }
//        });

        shop.refreshAssortment();
        dataFromPlayerToForm();
        dataFromShopToForm();

        ActivateLocation(CityPanel);
        setSize(750, 320);
        setLocationRelativeTo(null);
    }

    private void ActivateLocation(JPanel panel){
        AdventurePanel.setVisible(false);
        ThicketPanel.setVisible(false);
        ForestPanel.setVisible(false);
        MinePanel.setVisible(false);
        BlackSmithPanel.setVisible(false);
        CityPanel.setVisible(false);
        RuinsPanel.setVisible(false);
        ShopPanel.setVisible(false);
        panel.setVisible(true);
    }

    private final ActionListener goToMine = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(MinePanel);
            setSize(440, 330);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToCity = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(CityPanel);
            setSize(750, 330);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToThicket = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ThicketPanel);
            setSize(1160, 680);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToForest = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ForestPanel);
            setSize(640, 330);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToBlackSmith = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(BlackSmithPanel);
            setSize(600, 430);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToShop = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ShopPanel);
            setSize(600, 570);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToAdventure = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(AdventurePanel);
            setSize(720, 350);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToRuins = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(RuinsPanel);
            setSize(1100, 750);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goHunt = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            HuntForm huntForm = new HuntForm(player);
        }
    };

    private final ActionListener AttackWolf = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 5){
                JOptionPane.showMessageDialog(ThicketPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(ThicketPanel, "Волк побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackGiantSpiderButton.setEnabled(true);
            }
        }
    };

    private final ActionListener AttackGiantSpider = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 10){
                JOptionPane.showMessageDialog(ThicketPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(!player.artefacts.contains(Artefacts.MagicTorch)){
                JOptionPane.showMessageDialog(ThicketPanel, "Нужен артефакт: Магический факел", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }
                JOptionPane.showMessageDialog(ThicketPanel, "Гигантский паук побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackWhereBearButton.setEnabled(true);

        }
    };

    private final ActionListener AttackWhereBear = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 25){
                JOptionPane.showMessageDialog(ThicketPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!player.artefacts.contains(Artefacts.SilverSpear)){
                JOptionPane.showMessageDialog(ThicketPanel, "Нужен артефакт: Серебряное копье", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }
                JOptionPane.showMessageDialog(ThicketPanel, "Медведь-оборотень побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackCyclopeButton.setEnabled(true);

        }
    };

    private final ActionListener AttackCyclope = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 50){
                JOptionPane.showMessageDialog(ThicketPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!player.haveArtefact(Artefacts.LightRing)){
                JOptionPane.showMessageDialog(ThicketPanel, "Нужен артефакт: Кольцо света", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(ThicketPanel, "Циклоп побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(ThicketPanel, "Локация \"Чаща\" пройдена", "Поздравляю!", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    private final ActionListener AttackGoblin = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 5){
                JOptionPane.showMessageDialog(RuinsPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(RuinsPanel, "Гоблин побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackOrcButton.setEnabled(true);
            }
        }
    };

    private final ActionListener AttackOrc = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 10){
                JOptionPane.showMessageDialog(RuinsPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(RuinsPanel, "Орк побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackTrollButton.setEnabled(true);
            }
        }
    };

    private final ActionListener AttackTroll = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 25){
                JOptionPane.showMessageDialog(RuinsPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(RuinsPanel, "Тролль побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                AttackOgreButton.setEnabled(true);
            }
        }
    };

    private final ActionListener AttackOgre = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Armor_lvl < 50){
                JOptionPane.showMessageDialog(RuinsPanel, "Уровень доспеха не достаточен", "Невозможно атаковать", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(RuinsPanel, "Огр побежден", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(RuinsPanel, "Локация \"Руины\" пройдена", "Поздравляю!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    };



    private final ActionListener mineOre = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            player.OreCount += player.Pickaxe_lvl;
            dataFromPlayerToForm();
        }
    };



    private final ActionListener extractWood = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            player.WoodCount += player.Axe_lvl;
            dataFromPlayerToForm();
        }
    };

    private final ActionListener AddThousandOfOre1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            player.OreCount += 100000;
            dataFromPlayerToForm();
        }
    };

    private final ActionListener SellOreAmountUp = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(OreSellAmount <= 100000){
                OreSellAmount *= 10;
            }
            dataFromPlayerToForm();
        }
    };

    private final ActionListener SellOreAmountDown = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(OreSellAmount > 1000){
                OreSellAmount *= 0.1;
            }
            dataFromPlayerToForm();
        }
    };

    private final ActionListener TradeOreForCoins = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.OreCount >= OreSellAmount){
                player.Coins += OreSellAmount * OreCost;
                player.OreCount -= OreSellAmount;

            }
            dataFromPlayerToForm();
        }
    };

    private final ActionListener upgradePickaxe = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            player.upgradePickaxe();
            dataFromPlayerToForm();
        }
    };

    private final ActionListener upgradeAxe = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            player.upgradeAxe();
            dataFromPlayerToForm();
        }
    };

    private final ActionListener upgradeArmor = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
                player.upgradeArmor();
                dataFromPlayerToForm();
            }
    };

    private final ActionListener buyArtefact1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 500) {
                player.addArtefact(Artefacts.MagicTorch);
                player.Coins -= 500;
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 2500) {
                player.addArtefact(Artefacts.SilverSpear);
                player.Coins -= 2500;
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact3 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 5000) {
                player.addArtefact(Artefacts.LightRing);
                player.Coins -= 5000;
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 100) {
                player.addEquipment(EquipmentType.Sling);
                player.Coins -= 100;
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 500) {
                player.addEquipment(EquipmentType.HuntBow);
                player.Coins -= 500;
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment6 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.Coins >= 1000) {
                player.addEquipment(EquipmentType.HuntDog);
                player.Coins -= 1000;
                BuyHuntDogButton.setEnabled(false);
                HuntDogLabel.setVisible(true);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "Не хватает монет", "Покупка невозможна", JOptionPane.WARNING_MESSAGE);
            }
        }
    };
}