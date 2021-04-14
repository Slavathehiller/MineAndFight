package com.company;

import java.util.ArrayList;

public class Shop {

    public int lvl = 1;

    public ArrayList<Integer> artefacts = new ArrayList<Integer>();

    public void refreshAssortment(){
        artefacts = new ArrayList<Integer>();
        artefacts.add(Artefacts.MagicTorch);
        artefacts.add(Artefacts.SilverSpear);
        artefacts.add(Artefacts.LightRing);
    }

}
