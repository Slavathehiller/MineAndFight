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
        viewer.getSelf().setFocusable(true);
        viewer.getSelf().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int direction = Direction.UNDEFINED;
//                if (player.getStamina() < StepStaminaConsumption) {
//                    JOptionPane.showMessageDialog(HuntPanel, "Вы слишком устали и не можете продолжать охоту", "Невозможно продолжать!", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    direction = Direction.UP;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    direction = Direction.DOWN;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    direction = Direction.LEFT;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    direction = Direction.RIGHT;
                }
                model.movePlayer(direction);
                model.tick();
                viewer.DrawLocation();
            }
        });
    }
}

