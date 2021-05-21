package com.company;

public class CapercaillieTrack extends Track {

    @Override
    protected String getStrongImagePath() {
        return "/capercaillie_footprint_Strong_30x30.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/capercaillie_footprint_Weak_30x30.png";
    }

    @Override
    public int maxLifeTIme() {
        return 15;
    }

    public CapercaillieTrack(long x, long y) {
        super(x, y);
    }
}