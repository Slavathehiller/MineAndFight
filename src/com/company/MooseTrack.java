package com.company;

public class MooseTrack extends Track {

    @Override
    protected String getStrongImagePath() {
        return "/moose_footprint_Strong_30x30.png";
    }

    @Override
    protected String getWeakImagePath() {
        return "/moose_footprint_Weak_30x30.png";
    }

    @Override
    public int maxLifeTIme() {
        return 35;
    }

    public MooseTrack(long x, long y) {
        super(x, y);
    }
}
