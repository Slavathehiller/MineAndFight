package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Player implements IDisplayable, IFighter{

    public int Pickaxe_lvl = 1;
    public int PickaxeUpgradeCost = 10;
    public int Axe_lvl = 1;
    public int AxeUpgradeCost = 10;
    public int Armor_lvl = 1;
    public int ArmorUpgradeCost = 100;
    public int X = 0;
    public int Y = 0;
    private float Health = 100;
    private float Stamina = 100;
    private float MaxHealth = 100;
    private float MaxStamina = 100;
    private float RegenerateHealthRatio = 1;
    private float RegenerateStaminaRatio = 1;
    public float AttackEnergyCost = 5;
    private ImageIcon image;

    public MainForm InfoForm;
    public int additionalVisionArea = 0;

    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();
    public ArrayList<Supply> supplies = new ArrayList<>();
    private ArrayList<ImageIcon> buffIcons;
    private ArrayList<ImageIcon> deBuffIcons;

    public ArrayList<Integer> buffs = new ArrayList<>();
    public ArrayList<Integer> deBuffs = new ArrayList<>();

    private Timer[] deBuffTimers = new Timer[]{new Timer(600000, (x) -> setDeBuffOff(DeBuffTypes.DeadlyWeakness)),
                                                new Timer(180000, (x) -> setDeBuffOff(DeBuffTypes.Bleed))
    };
    private Timer[] buffTimers = new Timer[]{new Timer(300000, (x) -> setBuffOff(BuffTypes.FastHealthRegeneration)),
                                            new Timer(300000, (x) -> setBuffOff(BuffTypes.FastStaminaRegeneration)),
                                            new Timer(200000, (x) -> setBuffOff(BuffTypes.EnLargeMaxHealth)),
                                            new Timer(200000, (x) -> setBuffOff(BuffTypes.EnLargeMaxStamina))
    };

    public Player(MainForm infoForm){
        InfoForm = infoForm;
        this.image = new ImageIcon(getClass().getResource("/hunter_30x30.png"));
        for(int i = 0; i <= ResourceType.LastItem; i++){
            resources.add(new Resource(i));
        }
        deBuffIcons = new ArrayList<>();
        for(var name:DeBuffTypes.deBuffImageNames){
            deBuffIcons.add(new ImageIcon(getClass().getResource(name)));
        }
        buffIcons = new ArrayList<>();
        for(var name:BuffTypes.buffImageNames){
            buffIcons.add(new ImageIcon(getClass().getResource(name)));
        }
    }

    public void RefreshInfo(){
        InfoForm.dataFromPlayerToForm();
        InfoForm.RefreshStats();
    }

    public void StartTimers(){
        RefreshInfo();
        InfoForm.StartTimers();
    }

    public void StopTimers(){
        InfoForm.StopTimers();
    }

    public void upgradePickaxe(){
        upgradePickaxe(1);
    }

    public void upgradeAxe(){
        upgradeAxe(1);
    }

    public long getResourceNumber(int resourceType){
        return resources.get(resourceType).Number;
    }

    public void addResource(int resourceType, double number){
        resources.get(resourceType).Number += number;
    }

    public void addResource(Resource[] resources){
        for(var res:resources){
            this.resources.get(res.Type).Number += res.Number;
        }

    }

    public void addDrop(Drop drop){
        for(var res:drop.resources){
            addResource(res.Type, res.Number);
        }
    }

    public void UpdateStateInfo(JPanel statePanel){
        for(int i = statePanel.getComponents().length - 1; i >= 0; i--){
            var lbl = statePanel.getComponent(i);
            statePanel.remove(lbl);
        }
        for(var buff:buffs){
            JLabel stateLabel = new JLabel();
            stateLabel.setIcon(getBuffImage(buff));
            stateLabel.setToolTipText(BuffTypes.toolTips[buff]);
            statePanel.add(stateLabel);
        }
        for(var deBuff:deBuffs){
            JLabel stateLabel = new JLabel();
            stateLabel.setIcon(getDeBuffImage(deBuff));
            stateLabel.setToolTipText(DeBuffTypes.toolTips[deBuff]);
            statePanel.add(stateLabel);
        }
        statePanel.updateUI();
    }



    public Supply GetSupply(Class _class){
        for(var supply: getSupplies()){
            if(supply.getClass() == _class){
                return supply;
            }
        }
        return null;
    }

    public long GetSupplyNumber(Class _class){
        for(var supply: getSupplies()){
            if(supply.getClass() == _class){
                return supply.Number;
            }
        }
        return 0;
    }

    public void UpdateResources(JPanel resourcePanel){
        for(int i = resourcePanel.getComponents().length - 1; i >= 0; i--){
            var lbl = resourcePanel.getComponent(i);
            resourcePanel.remove(lbl);
        }
        for(Resource res:resources){
            JLabel resName = new JLabel();
            resName.setIcon(res.Icon);
            resName.setText(": " + res.Number);
            resName.setToolTipText(res.Name);
            resourcePanel.add(resName);
        }
        resourcePanel.updateUI();
    }

    public void UpdateSuppliesInfo(JPanel supplyPanel){
        for(int i = supplyPanel.getComponents().length - 1; i >= 0; i--){
            var lbl = supplyPanel.getComponent(i);
            supplyPanel.remove(lbl);
        }
        for(var supply: getSupplies()){
            JLabel supplyLabel = new JLabel();
            supplyLabel.setIcon(supply.smallImage);
            supplyLabel.setToolTipText(supply.Name);
            supplyLabel.setText("x" + supply.Number);
            supplyPanel.add(supplyLabel);
        }
        supplyPanel.updateUI();
    }

    public ImageIcon getBuffImage(int buff){
        return buffIcons.get(buff);
    }

    public ImageIcon getDeBuffImage(int deBuff){
        return deBuffIcons.get(deBuff);
    }

    public void upgradePickaxe(int lvl){
        if(getResourceNumber(ResourceType.Ore) >= PickaxeUpgradeCost){
                addResource(ResourceType.Ore, -PickaxeUpgradeCost);
                Pickaxe_lvl += lvl;
                PickaxeUpgradeCost *= 1.2;
            }
    }

    public void upgradeAxe(int lvl){
        if(getResourceNumber(ResourceType.Ore) >= AxeUpgradeCost){
            addResource(ResourceType.Ore, - AxeUpgradeCost);
            Axe_lvl += lvl;
            AxeUpgradeCost *= 1.2;
        }
    }

    public void addArtefact(int artefact){
        artefacts.add(artefact);
    }

    public void addEquipment(int equip){
        addEquipment(equip, 1);
    }
    public void addEquipment(int equip, int count)
    {
        for (var i=0; i<equipments.size(); i++)
        {
            if (equipments.get(i).Type == equip) {
                equipments.get(i).Number += count;
                return;
            }
        }
        equipments.add(new Equipment(equip));
    }

    public boolean haveArtefact(int artefact){
        return artefacts.contains(artefact);

    }

    public boolean haveEquipment(int equipment){
        return numEquipment(equipment) > 0;

    }

    private void StartBuffTimer(int index){
        buffTimers[index].start();
    }

    private void StartDeBuffTimer(int index){
        deBuffTimers[index].start();
    }

    public int SeenArea(){
        if(haveEquipment(EquipmentType.SpyGlass))
            return 3 + additionalVisionArea;
        else
            return 2 + additionalVisionArea;
    }


    public int numEquipment(int equip){
        for (var i=0; i<equipments.size(); i++)
        {
            if (equipments.get(i).Type == equip)
                return equipments.get(i).Number;
        }
        return 0;
    }

    public void upgradeArmor(){
        upgradeArmor(1);
    }

    public void upgradeArmor(int lvl){
        if(getResourceNumber(ResourceType.Ore) >= ArmorUpgradeCost){
            addResource(ResourceType.Ore, -ArmorUpgradeCost);
            Armor_lvl += lvl;
            ArmorUpgradeCost *= 1.2;
        }
    }

    public double senseRadius(){
        if(haveEquipment(EquipmentType.HuntDog)){
            return 5;
        }
        else{
            return 0;
        }
    }

    public boolean isEnoughResource(Recipe recipe){
        for (var res:recipe.resources){
            if(this.getResourceNumber(res.Type) < res.Number) {
                return false;
            }
        }
        return true;
    }

    public void setBuffOn(int buff){
        if(buffs.contains(buff))
            buffs.remove((Object)buff);
        buffs.add(buff);
        StartBuffTimer(buff);
    }

    public void setBuffOff(int buff){
        buffs.remove((Object)buff);
    }

    public void setDeBuffOn(int deBuff){
        if(deBuffs.contains(deBuff))
            deBuffs.remove((Object)deBuff);
        deBuffs.add(deBuff);
        StartDeBuffTimer(deBuff);
    }

    public void setDeBuffOff(int deBuff){
        deBuffs.remove((Object)deBuff);
    }

    public boolean isBuffed(int buff){
        return buffs.contains(buff);
    }

    public boolean isDeBuffed(int deBuff){
        return deBuffs.contains(deBuff);
    }

    public float getHealth() {
        return Health;
    }

    @Override
    public void changeHealth(float health) {
        setHealth(Health + health);
    }

    @Override
    public int getFighterType() {
        return CollisionObjectTypes.Player;
    }

    @Override
    public float getPower() {
        return Armor_lvl;
    }

    @Override
    public String getName() {
        return "Игрок";
    }

    public void setHealth(float health) {
        if(health > getMaxHealth()){
            Health = getMaxHealth();
            return;
        }
        if(health < 0) {
            Health = 0;
            return;
        }
        Health = health;
    }

    public float getStamina() {
        return Stamina;
    }

    public void setStamina(float stamina) {
        if(stamina > getMaxStamina()){
            Stamina = getMaxStamina();
            return;
        }
        if(stamina < 0) {
            Stamina = 0;
            return;
        }
        Stamina = stamina;
    }

    public void addStamina(float stamina){
        setStamina(Stamina + stamina);
    }

    public void addHealth(float health){
        setHealth(Health + health);
    }

    public float getMaxHealth() {
        var result = MaxHealth;
        if(isBuffed(BuffTypes.EnLargeMaxHealth))
        {
            result *= 1.5f;
        }
        return result;
    }

    public void setMaxHealth(float maxHealth) {
        if(maxHealth > 0){
            MaxHealth = maxHealth;
        }
        else
            MaxHealth = 1;


    }

    public float getMaxStamina() {
        var result = MaxStamina;
        if(isBuffed(BuffTypes.EnLargeMaxStamina))
        {
            result *= 1.5f;
        }
        return result;
    }

    public void setMaxStamina(float maxStamina) {
        if(maxStamina > 0) {
            MaxStamina = maxStamina;
        }
        else
            MaxStamina = 1;
    }

    public float getRegenerateHealthRatio() {
        if(isDeBuffed(DeBuffTypes.DeadlyWeakness)){
            return 0;
        }

        var result = RegenerateHealthRatio;
        if(isBuffed(BuffTypes.FastHealthRegeneration))
        {
            result *= 2;
        }
        return result;
    }

    public void setRegenerateHealthRatio(float regenerateRatio) {
        RegenerateHealthRatio = regenerateRatio;
    }

    public float getRegenerateStaminaRatio() {
        if(isDeBuffed(DeBuffTypes.DeadlyWeakness)){
            return 0;
        }
        var result = RegenerateStaminaRatio;
        if(isBuffed(BuffTypes.FastStaminaRegeneration))
        {
            result *= 2;
        }
        return result;
    }

    public void setRegenerateStaminaRatio(float regenerateRatio) {
        RegenerateStaminaRatio = regenerateRatio;
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public Integer getX() {
        return X;
    }

    @Override
    public Integer getY() {
        return Y;
    }

    @Override
    public String getToolTip() {
        return null;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Player;
    }

    @Override
    public Object getSelf() {
        return this;
    }
}
