package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Drop {
    public ArrayList<Resource> resources = new ArrayList<>();
    public ArrayList<Integer> artefacts = new ArrayList<>();
    public ArrayList<Equipment> equipments = new ArrayList<>();
    public ArrayList<Supply> supplies = new ArrayList<>();

    public String getDetails(){
        String message = "";
        for (Resource resource : resources) {
            message += "    " + resource.Name + ": " + Math.round(resource.Number) + "\n";
        }
        for (Equipment equipment : equipments) {
            message += "    " + equipment.Name + ": " + Math.round(equipment.Number) + "\n";
        }
        for (Supply supply : supplies) {
            message += "    " + supply.Name + ": " + Math.round(supply.Number) + "\n";
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
       return resources.size() == 0 && equipments.size() == 0 && artefacts.size() == 0 && supplies.size() == 0;
    }

    public void addRandomEquipment(int equipmentType, double minValue, double maxValue){
        addRandomEquipment(equipmentType, minValue, maxValue, 1);
    }
    public void addEquipment(int equipmentType, double number){
        equipments.add(new Equipment(equipmentType));
        equipments.get(equipments.size() - 1).Number += number - 1;
    }

    public void addRandomSupply(Class _class, long minValue, long maxValue, float chance){
        if(chance >= Math.random()){
            maxValue -= minValue;
            var count = (int) (Math.random() * ++maxValue) + minValue;
            addSupply(_class, count);
        }
    }

    public void addRandomSupply(Class _class, long minValue, long maxValue){
        addRandomSupply(_class, minValue, maxValue, 1);
    }

    public void addSupply(Class _class, long number){
        Supply supply = null;
        try{
            supply = (Supply) _class.getDeclaredConstructor().newInstance();
        }
        catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException invocationTargetException) {
            invocationTargetException.printStackTrace();
        }
        supply.Number = number;
        supplies.add(supply);
    }

}
