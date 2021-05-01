package com.company;

import javax.swing.*;
import java.util.Objects;

public abstract class Track {
    public long X;
    public long Y;

    public abstract int maxLifeTIme();

    public int lifeTime;

    public ImageIcon strongImage;
    public ImageIcon weakImage;

    protected String getStrongImagePath(){
        return null;
    }
    protected String getWeakImagePath(){
        return null;
    }

    public Track(long x, long y){
        X = x;
        Y = y;
        lifeTime = maxLifeTIme();
        strongImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(getStrongImagePath())));
        weakImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(getWeakImagePath())));
    }

    public boolean IsWeak(){
        return lifeTime < maxLifeTIme() * 0.7;
    }
}
