package com.company;

import java.lang.reflect.InvocationTargetException;

public class ThicketLevel2Model extends ThicketLevelModel{


    public ThicketLevel2Model(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        super(player);
    }

    @Override
    protected Boolean TryMove(int x, int y){
        var result = super.TryMove(x, y);
        if(player.isWebed()){
            if(Math.random() < 0.05f){
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
                web.setVisible(true);
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
        GenerateMonsters(SpiderHunter.class, 1);
        GenerateMonsters(SpiderSpawn.class, 4);
        GenerateLevelBoss(new SpiderQueen());
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
    public void GenerateChests(){
        DisplayableObjects.add(chests);
        for(var i = 0; i < 3; i++){
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y);
            chest.drop.addRandomResource(ResourceType.Coins, 50, 250);
            chest.drop.addRandomResource(ResourceType.Ore, 400, 600, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 400, 600, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 400, 600, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 2, 6, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 2, 6, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 2, 3, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 3, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 2, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 2, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 2, 0.005f);
            chests.add(chest);
        }
    }
}
