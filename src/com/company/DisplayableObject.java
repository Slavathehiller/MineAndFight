package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayableObject {
    private final JLabel canvas;
    public JPanel foundation;
    public JLabel healthBar;

    public DisplayableObject(JLabel label){
        canvas = label;
        healthBar = new JLabel();
        var font = new Font("Arial", Font.PLAIN, 5);
        healthBar.setFont(font);
        foundation = new JPanel();
        foundation.setLayout(new BoxLayout(foundation, BoxLayout.Y_AXIS));
        foundation.add(healthBar);
        foundation.add(canvas);
    }

    public JLabel getCanvas(){
        return canvas;
    }

    public void setImage(ImageIcon image){
        canvas.setIcon(image);
    }

    public void setDescription(String text){
        canvas.setToolTipText(text);
    }

    public void setHealthBar(IFighter object){
        var n = 10;
        var health = "";
        if(object == null){
            health = "";
        }
        else {
            float p1 = object.getMaxHealth() / 100;
            n = Math.round((object.getHealth() / p1) / 10);
            for(var i = 0; i < n; i++){
                health += "■";
            }
        }
        if(n >= 6){
            healthBar.setForeground(new Color(0,145,0));
        }
        else{
            if(n >= 3){
                healthBar.setForeground(new Color(0xFFB602));
            }
            else {
                healthBar.setForeground(Color.RED);
            }
        }
        healthBar.setText(" " + health + " ");
    }

}
