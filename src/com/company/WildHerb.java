package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class WildHerb {
    public long X;
    public long Y;
    public String Name;
    public ImageIcon image;

    public int[] habitat;

    protected String getImagePath(){
        return null;
    }

    protected void init(){
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(getImagePath())));
    }

    private Boolean isInhabitant(int size){
        for(var i:habitat){
            if(i == size)
                return true;
        }
        return false;
    }

    static WildHerb[] AllWildHerbs = new WildHerb[]{new WildOnion(), new Sage(), new Plantain(), new Mushroom()};

    static ArrayList<WildHerb> GetInhabitants(int size){
        ArrayList<WildHerb> result = new ArrayList<>();
        for (var herb:AllWildHerbs) {
            if(herb.isInhabitant(size))
                result.add(herb);
        }
        return result;
    }

    public Drop drop = new Drop();

    public WildHerb(){
        init();
    }
}
