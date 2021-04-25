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
        return message;
    }

    public void addResource(int resourceType, double number){
        resources.add(new Resource(resourceType));
        resources.get(resources.size() - 1).Number += number;
    }


}
