package com.company;

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
    public MineFrom InfoForm;

    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();


    public Player(MineFrom infoForm){
        InfoForm = infoForm;
        for(int i = 0; i < ResourceType.LastItem; i++){
            resources.add(new Resource(i));
        }
    }

    public void RefreshInfo(){
        InfoForm.dataFromPlayerToForm();
    }

    public void upgradePickaxe(){
        upgradePickaxe(1);
    }

    public void upgradeAxe(){
        upgradeAxe(1);
    }

    public double getResourceNumber(int resourceType){
        return resources.get(resourceType).Number;
    }

    public void addResource(int resourceType, double number){
        resources.get(resourceType).Number += number;
    }

    public void addDrop(Drop drop){
        for(var res:drop.resources){
            addResource(res.Type, res.Number);
        }
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

    public void addEquipment(int equip)
    {
        for (var i=0; i<equipments.size(); i++)
        {
            if (equipments.get(i).Type == equip) {
                equipments.get(i).Number++;
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
            return 2;
        else
            return 15;
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
}
