package com.company;
import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

public class MainForm extends JDialog implements IInfoForm {
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
    private JLabel PickaxeLabel;
    private JLabel ArmorLabel;
    private JPanel ThicketPanel;
    private JButton ThicketButton;
    private JButton DungeonButton;
    private JButton ToCityButtonAD;
    private JPanel InfoPanel;
    private JButton ToRuinsButton;
    private JPanel AdventurePanel;
    private JButton ToAdventureButton;
    private JButton ToAdventureButtonF;
    private JPanel RuinsPanel;
    private JButton ToAdventureButtonR;
    private JPanel ShopPanel;
    private JButton BuyMagicTorchButton;
    private JButton ToShopButton;
    private JLabel MagicTorchLabel;
    private JButton GoToCityButtonS;
    private JButton BuySilverSpearButton;
    private JLabel SilverSpearLabel;
    private JButton SellOreButton;
    private JButton OreSellAmountUpButton;
    private JButton OreSellAmountDownButton;
    private JButton BuyLightRingButton;
    private JLabel LightRingLabel;
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
    private JButton BuySpyGlassButton;
    private JLabel SpyGlassLabel;
    private JLabel CorralSpearNumlbl;
    private JButton BuyCorralSpearButton;
    private JButton ExitButton;
    private JButton BuyBearSpearButton;
    private JLabel BearSpearNumlbl;
    private JButton BuyTheGiantKillerButton;
    private JLabel TheGiantKillerLabel;
    private JButton BuyFlameSwordButton;
    private JLabel FlameSwordLabel;
    private JButton BuyDuelistsSaberButton;
    private JLabel DuelistsSaberLabel;
    private JButton ToWorkshopButton;
    private JPanel ResourcePanel;
    private JProgressBar HealthBar;
    private JProgressBar StaminaBar;
    public JPanel StatsPanel;
    private JButton ToTavernButton;
    public JPanel StatePanel;
    private JPanel SupplyPanel;
    private JButton InventoryButton;
    private JButton EnterThicketLevel1Button;
    private JButton EnterThicketLevel2Button;
    private JButton EnterThicketLevel3Button;
    private JButton EnterThicketLevel4Button;
    private JButton ToAlchemistButton;
    private JLabel MaskedLabel;
    private JButton EnterRuinLevel1Button;
    private JButton EnterRuinLevel2Button;
    private JButton EnterRuinLevel3Button;
    private JButton EnterRuinLevel4Button;
    private final Timer staminaTimer;
    private final Timer healthTimer;
    private int OreSellAmount = 1000;
    private final double OreCost = 0.02;

    Player player = new Player(this);
    Shop shop = new Shop();
    public MainForm self = this;

    public void dataFromPlayerToForm()
    {
        SellOreButton.setText("??????????(" + OreSellAmount + ")");
        PickaxeLabel.setText("??????????: " + player.Pickaxe_lvl);
        AxeLabel.setText("??????????: " + player.Axe_lvl);
        MineOreButton.setText("???????????????? ????????(" + player.Pickaxe_lvl + ")");
        ExtractWoodButton.setText("(" + player.Axe_lvl + ")");
        UpgradePickaxeButton.setText("?????????????????? ??????????(" + player.PickaxeUpgradeCost + ")");
        UpgradeAxeButton.setText("?????????????????? ????????????(" + player.AxeUpgradeCost + ")");
        ArmorLabel.setText("????????????: " + player.Armor_lvl);
        MaskedLabel.setText("????????????????????: " + player.Masked_lvl);
        UpgradeArmorButton.setText("?????????????????? ??????????????(" + player.ArmorUpgradeCost + ")");
        MagicTorchLabel.setVisible(player.haveArtefact(Artefacts.MagicTorch));
        SilverSpearLabel.setVisible(player.haveArtefact(Artefacts.SilverSpear));
        LightRingLabel.setVisible(player.haveArtefact(Artefacts.LightRing));
        TheGiantKillerLabel.setVisible(player.haveArtefact(Artefacts.TheGiantKiller));
        FlameSwordLabel.setVisible(player.haveArtefact(Artefacts.FlameSword));
        DuelistsSaberLabel.setVisible(player.haveArtefact(Artefacts.DuelistsSaber));
        SlingNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.Sling)));
        HuntBowNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.HuntBow)));
        CorralSpearNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.CorralSpear)));
        BearSpearNumlbl.setText(Integer.toString(player.numEquipment(EquipmentType.BearSpear)));
        SpyGlassLabel.setVisible(player.haveEquipment(EquipmentType.SpyGlass));
        HuntDogLabel.setVisible(player.haveEquipment(EquipmentType.HuntDog));
        player.UpdateResources(ResourcePanel);
        player.UpdateSuppliesInfo(SupplyPanel);
    }
    public void dataFromShopToForm()
    {

    }

    public MainForm(){
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
        SellOreButton.addActionListener(TradeOreForCoins);
        UpgradePickaxeButton.addActionListener(upgradePickaxe);
        UpgradeAxeButton.addActionListener(upgradeAxe);
        UpgradeArmorButton.addActionListener(upgradeArmor);
        BuyMagicTorchButton.addActionListener(buyArtefact1);
        BuySilverSpearButton.addActionListener(buyArtefact2);
        BuyLightRingButton.addActionListener(buyArtefact3);
        BuyTheGiantKillerButton.addActionListener(buyArtefact4);
        BuyFlameSwordButton.addActionListener(buyArtefact5);
        BuyDuelistsSaberButton.addActionListener(buyArtefact6);
        GoToCityButtonS.addActionListener(goToCity);
        ToAdventureButton.addActionListener(goToAdventure);
        ToCityButtonAD.addActionListener(goToCity);
        ThicketButton.addActionListener(goToThicket);
        ToAdventureButtonF.addActionListener(goToAdventure);
        ToWorkshopButton.addActionListener(goToWorkshop);
        ToTavernButton.addActionListener(goToTavern);
        ToAlchemistButton.addActionListener(goToAlchemist);
        InventoryButton.addActionListener(OpenInventory);
        EnterThicketLevel1Button.addActionListener(EnterThicketLevel1);
        EnterThicketLevel2Button.addActionListener(EnterThicketLevel2);
        EnterThicketLevel3Button.addActionListener(EnterThicketLevel3);
        EnterThicketLevel4Button.addActionListener(EnterThicketLevel4);
        EnterRuinLevel2Button.addActionListener(EnterRuinLevel2);
        EnterRuinLevel1Button.addActionListener(EnterRuinLevel1);
        EnterRuinLevel3Button.addActionListener(EnterRuinLevel3);
        EnterRuinLevel4Button.addActionListener(EnterRuinLevel4);
        ToAdventureButtonR.addActionListener(goToAdventure);
        HuntButton.addActionListener(goHunt);
        BuySlingButton.addActionListener(buyEquipment1);
        BuyHuntBowButton.addActionListener(buyEquipment2);
        BuyHuntDogButton.addActionListener(buyEquipment3);
        BuySpyGlassButton.addActionListener(buyEquipment4);
        BuyCorralSpearButton.addActionListener(buyEquipment5);
        BuyBearSpearButton.addActionListener(buyEquipment6);
        ExitButton.addActionListener((x) -> this.dispose());

        staminaTimer = new Timer(1000, (x) -> SetPlayerStamina(player.getStamina() + player.getRegenerateStaminaRatio()));

        healthTimer = new Timer(1000, (x) -> SetPlayerHealth(player.getHealth() + player.getRegenerateHealthRatio()));

        StartTimers();

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_D){
                    DeveloperModeForm developerModeForm = new DeveloperModeForm(player);
                }
            }
        });

        shop.refreshAssortment();
        dataFromPlayerToForm();
        dataFromShopToForm();

        ActivateLocation(CityPanel);
        setSize(800, 680);
        setLocationRelativeTo(null);
        ResourcePanel.setLayout(new BoxLayout(ResourcePanel, BoxLayout.Y_AXIS));
        RefreshStats();
    }

    private void SetPlayerStamina(float stamina){
        player.setStamina(stamina);
        RefreshStaminaInfo();
    }

    private void RefreshStaminaInfo(){
        StaminaBar.setMaximum(Math.round(player.getMaxStamina()));
        StaminaBar.setValue(Math.round(player.getStamina()));
        StaminaBar.setToolTipText("?????????????? (" + player.getStamina() + "/" + player.getMaxStamina());
    }

    private void SetPlayerHealth(float health){
        player.setHealth(health);
        RefreshHealthInfo();
    }

    private boolean CanGoIntoBattle(){
        if(player.isGlobalBuff(GlobalBuffTypes.DeadlyWeakness)){
            JOptionPane.showMessageDialog(MainPanel, "?????????????????????? ???????????????? ???? ?????????????????? ?????? ???????? ?? ??????", "???????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void RefreshStats(){
        RefreshHealthInfo();
        RefreshStaminaInfo();
    }

    private void RefreshHealthInfo(){
        HealthBar.setMaximum(Math.round(player.getMaxHealth()));
        HealthBar.setValue(Math.round(player.getHealth()));
        HealthBar.setToolTipText("???????????????? (" + player.getHealth() + "/" + player.getMaxHealth());
        player.UpdateStateInfo(StatePanel);
    }

    public void StartTimers(){
        healthTimer.start();
        staminaTimer.start();
    }

    public void StopTimers(){
        healthTimer.stop();
        staminaTimer.stop();
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
            setSize(640, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToCity = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(CityPanel);
            setSize(810, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToThicket = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ThicketPanel);
            setSize(1190, 740);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToForest = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ForestPanel);
            setSize(690, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToBlackSmith = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(BlackSmithPanel);
            setSize(630, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToShop = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(ShopPanel);
            setSize(910, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToAdventure = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(AdventurePanel);
            setSize(750, 730);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goToRuins = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivateLocation(RuinsPanel);
            setSize(1150, 790);
            setLocationRelativeTo(null);
        }
    };

    private final ActionListener goHunt = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            ChooseHunt chooseHunt = new ChooseHunt(player);

            dataFromPlayerToForm();
        }
    };

    private void StartLevel(ISubLevelModel model){
        if(!CanGoIntoBattle()){
            return;
        }
        player.X = 1;
        player.Y = 1;
        player.StopTimers();
        ISubLevelController controller = new SubLevelController();
        SwingViewer swingViewer = new SwingViewer(self(), model, controller);
        player.ClearBattleBuffs();
        player.StartTimers();
        dataFromPlayerToForm();
    }

    private final ActionListener EnterThicketLevel1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new ThicketLevel1Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterThicketLevel2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new ThicketLevel2Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterThicketLevel3 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new ThicketLevel3Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterThicketLevel4 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new ThicketLevel4Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterRuinLevel1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new RuinLevel1Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterRuinLevel2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new RuinLevel2Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterRuinLevel3 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new RuinLevel3Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener EnterRuinLevel4 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            var model = new RuinLevel4Model(player);
            StartLevel(model);
        }
    };

    private final ActionListener OpenInventory = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Inventory inventory = new Inventory(self(), self, player);

            dataFromPlayerToForm();
        }
    };

    private JDialog self(){
        return this;
    }

    private final ActionListener goToWorkshop = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Workshop workshop = new Workshop(self(), player);

            dataFromPlayerToForm();
        }
    };

    private final ActionListener goToAlchemist = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Alchemist alchemist = new Alchemist(self(), player);

            dataFromPlayerToForm();
        }
    };

    private final ActionListener goToTavern = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            Tavern tavern = new Tavern(self(), player);

            dataFromPlayerToForm();
        }
    };

    private final ActionListener mineOre = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            player.addResource(ResourceType.Ore, player.Pickaxe_lvl);
            dataFromPlayerToForm();
        }
    };



    private final ActionListener extractWood = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            player.addResource(ResourceType.Wood ,player.Axe_lvl);
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
            if(player.getResourceNumber(ResourceType.Ore) >= OreSellAmount){
                player.addResource(ResourceType.Coins, OreSellAmount * OreCost);
                player.addResource(ResourceType.Ore, - OreSellAmount);

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
            if(player.getResourceNumber(ResourceType.Coins) >= 1000) {
                player.addArtefact(Artefacts.MagicTorch);
                player.addResource(ResourceType.Coins, - 1000);
                BuyMagicTorchButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 10000) {
                player.addArtefact(Artefacts.SilverSpear);
                player.addResource(ResourceType.Coins, - 10000);
                BuySilverSpearButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact3 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 100000) {
                player.addArtefact(Artefacts.LightRing);
                player.addResource(ResourceType.Coins, - 100000);
                BuyLightRingButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact4 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 100000) {
                player.addArtefact(Artefacts.TheGiantKiller);
                player.addResource(ResourceType.Coins, - 100000);
                BuyTheGiantKillerButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact5 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 10000) {
                player.addArtefact(Artefacts.FlameSword);
                player.addResource(ResourceType.Coins, - 10000);
                BuyFlameSwordButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyArtefact6 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 1000) {
                player.addArtefact(Artefacts.DuelistsSaber);
                player.addResource(ResourceType.Coins, - 1000);
                BuyDuelistsSaberButton.setEnabled(false);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment1 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 20) {
                player.addEquipment(EquipmentType.Sling);
                player.addResource(ResourceType.Coins, - 20);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment2 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 100) {
                player.addEquipment(EquipmentType.HuntBow);
                player.addResource(ResourceType.Coins, - 100);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment3 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 1000) {
                player.addEquipment(EquipmentType.HuntDog);
                player.addResource(ResourceType.Coins, - 1000);
                BuyHuntDogButton.setEnabled(false);
                HuntDogLabel.setVisible(true);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment4 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 1000) {
                player.addEquipment(EquipmentType.SpyGlass);
                player.addResource(ResourceType.Coins, - 1000);
                BuySpyGlassButton.setEnabled(false);
                SpyGlassLabel.setVisible(true);
                dataFromPlayerToForm();

            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment5 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 300) {
                player.addEquipment(EquipmentType.CorralSpear);
                player.addResource(ResourceType.Coins, - 300);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    private final ActionListener buyEquipment6 = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(player.getResourceNumber(ResourceType.Coins) >= 500) {
                player.addEquipment(EquipmentType.BearSpear);
                player.addResource(ResourceType.Coins, - 500);
                dataFromPlayerToForm();
            }
            else{
                JOptionPane.showMessageDialog(ShopPanel, "???? ?????????????? ??????????", "?????????????? ????????????????????", JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    @Override
    public void RefreshInfo() {
        dataFromPlayerToForm();
    }
}
