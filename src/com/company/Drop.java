package com.company;

import java.util.ArrayList;

public class Drop {
    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public ArrayList<Integer> artefacts = new ArrayList<Integer>();
    public ArrayList<Equipment> equipments = new ArrayList<Equipment>();

    public String getDetails(){
        String message = "";
        for(int i = 0; i < resources.size(); i++){
            message += "    " + resources.get(i).Name + ": " + resources.get(i).Number + "\n";
        }
        for(int i = 0; i < equipments.size(); i++){
            message += "    " + equipments.get(i).Name + ": " + equipments.get(i).Number + "\n";
        }
        return message;
    }

    public void addRandomResource(int resourceType, double minValue, double maxValue, float chance){
        if(chance >= Math.random()){
            maxValue -= minValue;
            var count = (int) (Math.random() * ++maxValue) + minValue;
            addResource(resourceType, count);
        }
    }

    public void addRandomResource(int resourceType, double minValue, double maxValue){
        addRandomResource(resourceType, minValue, maxValue, 1);
    }
    public void addResource(int resourceType, double number){
        resources.add(new Resource(resourceType));
        resources.get(resources.size() - 1).Number += number;
    }

    public void addRandomEquipment(int equipmentType, double minValue, double maxValue, float chance){
        if(chance >= Math.random()){
            maxValue -= minValue;
            var count = (int) (Math.random() * ++maxValue) + minValue;
            addEquipment(equipmentType, count);
        }
    }

    public boolean isEmpty(){
       return resources.size() == 0 && equipments.size() == 0 && artefacts.size() == 0;
    }

    public void addRandomEquipment(int equipmentType, double minValue, double maxValue){
        addRandomEquipment(equipmentType, minValue, maxValue, 1);
    }
    public void addEquipment(int equipmentType, double number){
        equipments.add(new Equipment(equipmentType));
        equipments.get(equipments.size() - 1).Number += number - 1;
    }


}
