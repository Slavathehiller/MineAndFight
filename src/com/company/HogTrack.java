package com.company;

public class HogTrack extends Track{

    @Override
    protected String getStrongImagePath() {
        return "/boar_footprint_Strong_30x30.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/boar_footprint_Weak_30x30.png";
    }

    @Override
    public int maxLifeTIme(){
        return 35;
    }

    public HogTrack(long x, long y){
        super(x, y);
    }

}
