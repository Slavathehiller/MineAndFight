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
    public MineFrom InfoForm;
    public int additionalVisionArea = 0;

    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();


    public Player(MineFrom infoForm){
        InfoForm = infoForm;
        for(int i = 0; i <= ResourceType.LastItem; i++){
            resources.add(new Resource(i));
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

    public float getHealth() {
        return Health;
    }

    public void setHealth(float health) {
        if(health >= 0)
            Health = health;
        else
            Health = 0;
    }

    public float getStamina() {
        return Stamina;
    }

    public void setStamina(float stamina) {
        if(stamina >= 0)
            Stamina = stamina;
        else
            Stamina = 0;
    }

    public void addStamina(float stamina){
        setStamina(Stamina + stamina);
    }

    public void addHealth(float health){
        setHealth(Health + health);
    }

}
