package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubLevelController implements ISubLevelController {

    ISubLevelViewer viewer;
    ISubLevelModel model;
    private Player player;
    private int maxY;
    private int maxX;

    @Override
    public void Initializate(ISubLevelViewer viewer, ISubLevelModel model) {
        this.player = model.getPlayer();
        this.maxY = model.getMaxY();
        this.maxX = model.getMaxX();
        this.viewer = viewer;
        this.model = model;
    }

    private void ProcessMessages(){
        if(model.getMessages(MessageIndex.NotEnoughEnergy)){
            viewer.ShowMessage_NotEnoughStamina();
        }
    }

    public void React(int direction){
        model.movePlayer(direction);
        model.tick();
        if(model.getPlayerIsDead()){
            viewer.PlayerDeadMessage();
            viewer.EndLevel();
        }
        ProcessMessages();
        viewer.DrawLocation();
        model.ClearLog();
    }
}

