package com.company;

public class WolfTrack extends Track{

    @Override
    protected String getStrongImagePath() {
        return "/wolf_footprint_Strong_30x30.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/wolf_footprint_Weak_30x30.png";
    }

    @Override
    public int maxLifeTIme(){
        return 30;
    }

    public WolfTrack(long x, long y){
        super(x, y);
    }
}
