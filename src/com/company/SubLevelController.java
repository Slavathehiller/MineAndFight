package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

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
        var customMessages = model.getCustomMessages();
        for(var i = customMessages.size() - 1; i >= 0; i--){
            var message = customMessages.get(i);
            viewer.ShowMessage_CustomMessage(message);
            customMessages.remove(message);
        }
    }

    public void React(int direction) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        model.movePlayer(direction);
        if(model.getWantToExit()){
            if(viewer.ShowMessage_AskWantToExit()){
                viewer.EndLevel();
                return;
            }
            else{
                model.setWantToExit(false);
            }
        }
        model.tick();
        if(model.getPlayerIsDead()){
            player.setGlobalBuffOn(GlobalBuffTypes.DeadlyWeakness);
            player.setHealth(1);
            viewer.PlayerDeadMessage();
            viewer.EndLevel();
            return;
        }
        ProcessMessages();
        if(model.getLevelBossHasDied()){
            if(viewer.ShowMessage_LevelBossIsDead(model.getLevelBoss().drop.getDetails())){
                viewer.EndLevel();
                return;
            }
        }
        viewer.DrawLocation();
        model.ClearLog();
    }
}

