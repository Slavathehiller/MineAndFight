package com.company;

public class BearTrack extends Track{
    @Override
    protected String getStrongImagePath() {
        return "/bear_footprint_Strong_icon.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/bear_footprint_Weak_icon.png";
    }

    @Override
    public int maxLifeTIme(){
        return 25;
    }

    public BearTrack(long x, long y){
        super(x, y);
    }

}
