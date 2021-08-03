package com.company;

import java.util.ArrayList;

public class Drop {
    public ArrayList<Resource> resources = new ArrayList<>();
    public ArrayList<Integer> artefacts = new ArrayList<>();
    public ArrayList<Equipment> equipments = new ArrayList<>();

    public String getDetails(){
        String message = "";
        for (Resource resource : resources) {
            message += "    " + resource.Name + ": " + resource.Number + "\n";
        }
        for (Equipment equipment : equipments) {
            message += "    " + equipment.Name + ": " + equipment.Number + "\n";
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
