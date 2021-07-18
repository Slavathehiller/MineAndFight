package com.company;

import javax.swing.*;

public interface IDisplayable {

    ImageIcon getImage();
    Integer getX();
    Integer getY();
    String getToolTip();
    int getObjectType();
    Object getSelf();
    void init(IMap map, int x, int y);


}
