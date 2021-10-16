package com.company;

public class ThicketLevel2Model extends ThicketLevelModel{


    public ThicketLevel2Model(Player player){
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
                if(player.haveArtefact(Artefacts.MagicTorch)){
                    player.inWeb.Destroy();
                    addToLog("Вы сжигаете паутину при помощи магического факела");
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
    public void GenerateMonsters(){
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

    private void GenerateSpider(){
        GiantSpider spider = (GiantSpider) GenerateMonster(GiantSpider.class);
        var web = GenerateWeb();
        web.setMaster(spider);
    }

    @Override
    public void GenerateChests(){
        DisplayableObjects.add(chests);
        for(var i = 0; i < 3; i++){
            var point = GenerateFreeCords();
            Chest chest = new Chest(point.X, point.Y, this);
            chest.drop.addRandomResource(ResourceType.Coins, 200, 700);
            chest.drop.addRandomResource(ResourceType.Ore, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 700, 2500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 2, 12, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 2, 12, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 2, 5, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 2, 5, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 3, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chest.drop.addRandomSupply(Bandage.class, 1, 3, 0.1f);
            chests.add(chest);
        }
    }
}
