package com.company;

import java.util.ArrayList;

public class Recipe {

    public ArrayList<Resource> resources = new ArrayList<Resource>();

    public void addResource(int resourceType, double number){
        resources.add(new Resource(resourceType));
        resources.get(resources.size() - 1).Number += number;
    }

}
