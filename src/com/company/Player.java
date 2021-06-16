package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Player {

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

    public MineFrom InfoForm;
    public int additionalVisionArea = 0;

    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();
    public ArrayList<Supply> supplies = new ArrayList<>();
    private ArrayList<ImageIcon> buffIcons;

    public ArrayList<Integer> buffs = new ArrayList<>();
    private Timer[] buffTimers = new Timer[]{new Timer(300000, (x) -> setBuffOff(BuffTypes.FastHealthRegeneration)),
                                new Timer(300000, (x) -> setBuffOff(BuffTypes.FastStaminaRegeneration)),
                                new Timer(200000, (x) -> setBuffOff(BuffTypes.EnLargeMaxHealth)),
                                new Timer(200000, (x) -> setBuffOff(BuffTypes.EnLargeMaxStamina))
    };

    public Player(MineFrom infoForm){
        InfoForm = infoForm;
        for(int i = 0; i <= ResourceType.LastItem; i++){
            resources.add(new Resource(i));
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

    public void UpdateBuffsInfo(JPanel buffPanel){
        for(int i = buffPanel.getComponents().length - 1; i >= 0; i--){
            var lbl = buffPanel.getComponent(i);
            buffPanel.remove(lbl);
        }
        for(var buff:buffs){
            JLabel buffLabel = new JLabel();
            buffLabel.setIcon(getBuffImage(buff));
            buffLabel.setToolTipText(BuffTypes.toolTips[buff]);
            buffPanel.add(buffLabel);
        }
        buffPanel.updateUI();
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

    public void UpdateResources(JPanel resourcePanel, ArrayList<JLabel> resourceLabels){
        for(int i = resourceLabels.size() - 1; i >= 0; i--){
            var lbl = resourceLabels.get(i);
            resourcePanel.remove(lbl);
            resourceLabels.remove(lbl);
        }
        for(Resource res:resources){
            JLabel resName = new JLabel();
            resourceLabels.add(resName);
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

    private void StartTimer(int index){
        buffTimers[index].start();
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
        StartTimer(buff);
    }

    public void setBuffOff(int buff){
        buffs.remove((Object)buff);
    }

    public boolean isBuffed(int buff){
        return buffs.contains(buff);
    }

    public float getHealth() {
        return Health;
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
}
