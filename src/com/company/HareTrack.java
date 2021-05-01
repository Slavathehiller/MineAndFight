package com.company;

public class HareTrack extends Track{

    @Override
    protected String getStrongImagePath() {
        return "/hare_footprint_Strong_30x30.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/hare_footprint_Weak_30x30.png";
    }

    @Override
    public int maxLifeTIme(){
        return 25;
    }

    public HareTrack(long x, long y){
        super(x, y);
    }
}
