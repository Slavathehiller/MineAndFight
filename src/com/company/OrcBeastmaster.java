package com.company;

import javax.swing.*;
import java.util.Objects;

public class OrcBeastmaster extends Monster {

    private boolean DogReleased = false;
    ImageIcon WithoutDogImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/orc_without_dog_icon_30x30.png")));
    ImageIcon WithDogImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/orc_with_dog_icon_30x30.png")));

    @Override
    protected String getImagePath() {
        return "/orc_with_dog_icon_30x30.png";
    }

    public void init(IMap map, int x, int y) {
        super.init(map, x, y);
        Name = "Орк-Собачник";
        frequencyMove = 0.7f;
        Power = 15;
        FeelRadius = 4;
        Armed = true;
        drop.addResource(ResourceType.Fur, 3);
        drop.addRandomResource(ResourceType.Coins, 10, 50, 0.7f);

    }

    @Override
    public void Act() {
        if (AttackIfPlayerNear()) {
            return;
        }
        if (CanMove()) {
            if (FeelRadius() >= map.DistanceToPlayer(this)) {
                if(!DogReleased)
                    ReleaseDog();
                else {
                    MoveToPlayer();
                    return;
                }
            }
            RandomMove();
        }
    }

    private boolean ReleaseDog(){
        var spawnPoint = map.GetStepToward(new Point(this.X, this.Y), new Point(map.getPlayer().X, map.getPlayer().Y));
        var object = map.ObjectAt(spawnPoint);
        if(object == null) {
            map.GenerateMonster(WarDog.class, spawnPoint);
            DogReleased = true;
        }
        return DogReleased;
    }

    @Override
    public float getMaxHealth() {
        return 30;
    }

    @Override
    protected boolean CanMove() {
        return super.CanMove();
    }

    public OrcBeastmaster() {

    }

    @Override
    public ImageIcon getImage() {
        if (DogReleased) {
            return WithoutDogImage;
        }
        return WithDogImage;
    }

    @Override
    public int getObjectType() {
        return CollisionObjectTypes.Monster;
    }

    @Override
    public Object getSelf() {
        return this;
    }

    @Override
    public Buffing[] getBuffing() {
        return new Buffing[0];
    }
}
