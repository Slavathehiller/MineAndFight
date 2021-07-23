package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ThicketLevel2Model extends ThicketLevelModel{


    public ThicketLevel2Model(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super(player);
    }

    @Override
    protected Boolean TryMove(int x, int y){
        var result = super.TryMove(x, y);
        if(player.isWebed()){
            if(Math.random() < 0.1f){
                if(player.inWeb.getMaster() != null){
                    player.inWeb.getMaster().ClearWebWarning();
                }
                player.setWeb(null);
                return result;
            }
            else{
                return false;
            }
        }
        if(!result){
            if(ObjectAt(x, y) != null && ObjectAt(x, y).getObjectType() == CollisionObjectTypes.Web){
                Web web = (Web) ObjectAt(x, y);
                player.setWeb(web);
                if(web.getMaster() != null)
                    web.getMaster().setWebWarning(web);
                return true;
            }
        }
        return result;
    }

    @Override
    public void GenerateMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super.GenerateMonsters();
        for(int i = 0; i < 3; i++){
            GenerateSpider();
        }
        var cords = GenerateFreeCords();
        LevelBoss = new SpiderQueen();
        LevelBoss.init(this, cords.X, cords.Y);
        monsters.add(LevelBoss);
    }

    public Web GenerateWeb(){
        var cord = GenerateFreeCords();
        var web = new Web(this, cord.X, cord.Y);
        obstacles.add(web);
        return web;
    }

    private void GenerateSpider() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        GiantSpider spider = (GiantSpider) GenerateMonster(GiantSpider.class);
        var web = GenerateWeb();
        web.setMaster(spider);
    }

    @Override
    public void GenerateChests() {

    }
}
