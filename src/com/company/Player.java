package com.company;

import java.util.ArrayList;

public class Player {

    public int Pickaxe_lvl = 1;
    public int PickaxeUpgradeCost = 10;
    public int Axe_lvl = 1;
    public int AxeUpgradeCost = 10;
    public int Armor_lvl = 1;
    public int ArmorUpgradeCost = 100;
    public int OreCount = 0;
    public int WoodCount = 0;
    public int Coins = 0;
    public int X = 0;
    public int Y = 0;

    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();

    public void upgradePickaxe(){
        upgradePickaxe(1);
    }

    public void upgradeAxe(){
        upgradeAxe(1);
    }

    public void upgradePickaxe(int lvl){
        if(OreCount >= PickaxeUpgradeCost){
                OreCount -= PickaxeUpgradeCost;
                Pickaxe_lvl += lvl;
                PickaxeUpgradeCost *= 1.2;
            }
    }

    public void upgradeAxe(int lvl){
        if(OreCount >= AxeUpgradeCost){
            OreCount -= AxeUpgradeCost;
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
        if(haveEquipment(EquipmentType.LookingGlass))
            return 2;
        else
            return 1;
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
        if(OreCount >= ArmorUpgradeCost){
            OreCount -= ArmorUpgradeCost;
            Armor_lvl += lvl;
            ArmorUpgradeCost *= 1.2;
        }
    }
}
