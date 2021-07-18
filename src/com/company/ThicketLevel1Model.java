package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ThicketLevel1Model implements ISubLevelModel, IMap{
    Player player;
    private int maxX = 40;
    private int maxY = 30;
    private int maxObstacles = 20;
    private int minObstacles = 12;
    public String Log;
    ArrayList<IDisplayable> obstacles = new ArrayList<>();
    ArrayList<IDisplayable> monsters = new ArrayList<>();
    ArrayList<IDisplayable> chests = new ArrayList<>();
    ArrayList<ArrayList<IDisplayable>> DisplayableObjects = new ArrayList<>();
    public Boolean[] messages = new Boolean[]{false};
    private ArrayList<String> customMessages = new ArrayList<>();

    public ThicketLevel1Model(Player player) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        this.player = player;
        GenerateMonsters();
        GenerateObstacles();
        GenerateChests();
        ArrayList<IDisplayable> iPlayers = new ArrayList<>();
        iPlayers.add(player);
        DisplayableObjects.add(iPlayers);

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Integer getMaxX() {
        return maxX;
    }

    @Override
    public Integer getMaxY() {
        return maxY;
    }

    @Override
    public String getLog() {
        return Log;
    }

    @Override
    public ArrayList<IDisplayable> getMonsters() {
        return monsters;
    }

    @Override
    public String addToLog(String message) {
        return Log += message + "\n";
    }

    @Override
    public ArrayList<IDisplayable> getObstacles() {
        return obstacles;
    }

    @Override
    public ArrayList<ArrayList<IDisplayable>> getDisplayableObjects() {
        return DisplayableObjects;
    }

    @Override
    public void movePlayer(int direction) {
        int x = player.X;
        int y = player.Y;

        if (direction == Direction.UP) {
            y -= 1;
        }
        if (direction == Direction.DOWN) {
            y += 1;
        }
        if (direction == Direction.LEFT) {
            x -= 1;
        }
        if (direction == Direction.RIGHT) {
            x += 1;
        }
        if(TryMove(x, y)){
            player.X = x;
            player.Y = y;
        }
    }
    private void BattleBuffCountDown(){
        for(var i = 0; i < player.battleBuffCounters.length; i++){
            var count = player.battleBuffCounters[i];
            if(count > 0)
                player.setBattleBuff(i, count - 1);
        }
    }

    @Override
    public void tick() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        player.BattleBuffsProceed();
        BattleBuffCountDown();
        ActMonsters();
    }

    private void ActMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        for(var monster:monsters){
            ((Monster)monster).Act();
        }
    }

    private Boolean TryMove(int x, int y){
        var object = ObjectAt(x, y);
        if(object == null){
            return true;
        }
        else{
            if(object.getObjectType() == CollisionObjectTypes.Monster){
                if(player.getStamina() >= player.AttackEnergyCost) {
                    Monster monster = (Monster) object;
//                    Log += "Игрок атакует " + monster.Name + "\n";
                    Attack(player, monster);
                    player.addStamina(-player.AttackEnergyCost);
                }
                else{
                    messages[MessageIndex.NotEnoughEnergy] = true;
                }
            }
            if(object.getObjectType() == CollisionObjectTypes.Chest && !((Chest) object).isLooted){
                if(player.getStamina() == 0){
                    Log += "Недостаточно энергии для открытия сундука\n";
                    return false;
                }
                Chest chest = (Chest) object;
                player.addDrop(chest.drop);
                chest.isLooted = true;
                Log += "Вы открываете сундук и обнаруживаете: \n" + chest.drop.getDetails() + "\n";
                player.addStamina(-player.OpenChestEnergyCost);
            }
            return false;
        }
    }

    public void ClearLog(){
        Log = "";
    }

    private IDisplayable ObjectAt(int x, int y){
        if(x > maxX - 1 || x < 0 || y > maxY - 1 || y < 0){
            return new Edge();
        }
        for(var objects:DisplayableObjects){
            for(var object:objects){
                if(x == object.getX() && y == object.getY()){
                    return object;
                }
            }
        }
        return null;
    }

    private void MoveIfCan(IMovableDisplayable mover, int x, int y){
        if(canMonsterMove(x, y)){
            mover.Move(x, y);
        }
    }
    @Override
    public float RangeFromTo(int x1, int y1, int x2, int y2){
        var deltaX = x1 - x2;
        var deltaY = y1 - y2;
        return (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    @Override
    public float DistanceToPlayer(IDisplayable object){
        return DistanceToPlayer(object.getX(), object.getY());
    }

    @Override
    public float DistanceToPlayer(int x, int y){
        return RangeFromTo(player.X, player.Y, x, y);
    }

    public void MoveToward(IMovableDisplayable mover, int x, int y){
        var deltaX = Math.abs(x - mover.getX());
        var deltaY = Math.abs(y - mover.getY());
        if(deltaX > deltaY){
            if(x > mover.getX()){
                MoveIfCan(mover, mover.getX() + 1, mover.getY());
            }
            else{
                MoveIfCan(mover, mover.getX() - 1, mover.getY());
            }
        }
        else{
            if(y > mover.getY()){
                MoveIfCan(mover, mover.getX(), mover.getY() + 1);
            }
            else{
                MoveIfCan(mover, mover.getX(), mover.getY() - 1);
            }
        }
    }

    public void Attack(IFighter attacker, IFighter target){
        var diff = attacker.getPower() / target.getPower();
        var chance = Math.sqrt(diff) * 50 / 100;
        chance = Math.min(chance, 0.999);
        chance = Math.max(chance, 0.001);
        System.out.println("Шанс попадания: " + chance);
        var toHit = Math.random();
        System.out.println("Выпало: " + toHit);
        if(chance >= toHit){
            var damage = diff * 5;
            damage = Math.round(damage * 10) / 10f;
            Log += attacker.getName() + " бьет " + target.getName() + " и наносит " + damage + " урона.\n";
            target.changeHealth(-damage);
            if(target.getFighterType() == CollisionObjectTypes.Player){
                for(Buffing buffing: attacker.getBuffing()){
                    var chanceToBuff = Math.random();
                    if(buffing.Chance >= chanceToBuff){
                        ((Player)target).setBattleBuff(buffing.BuffType, buffing.Duration);
                        AddCustomMessage("Вы получаете " + BattleBuffType.names[buffing.BuffType] + ".");
                    }
                }
            }
            if(target.getHealth() <= 0){
                if(target.getFighterType() == CollisionObjectTypes.Monster) {
                    Log += target.getName() + " погибает\n";
                    if(attacker.getFighterType() == CollisionObjectTypes.Player) {
                        player.addDrop(target.getDrop());
                        Log += "Вы получаете " + target.getDrop().getDetails();
                    }
                    var monster = (Monster)target;
                    monsters.remove(monster);
//                    for(var list:DisplayableObjects){
//                        list.remove(monster);
//                    }
                }
                if(target.getFighterType() == CollisionObjectTypes.Player){
                }
            }
        } else{
            Log += attacker.getName() + " бьет " + target.getName() + " и промахивается.\n";
        }


    }

    private void AddCustomMessage(String message){
        customMessages.add(message);
    }

    public boolean getPlayerIsDead(){
        return player.getHealth() < 1;
    }

    @Override
    public Boolean getMessages(int index) {
        var result = messages[index];
        messages[index] = false;
        return result;
    }

    @Override
    public ArrayList<String> getCustomMessages() {
        return customMessages;
    }

    public Object GenerateObject(Class _class) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return _class.getDeclaredConstructor().newInstance();
   }

    private int getObstacleCount(){
        return (int) Math.round(Math.random() * ( maxObstacles - minObstacles )) + minObstacles;
    }

    public void GenerateObstacles() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        GenerateObstacles(ForestObstacle.class, getObstacleCount());
        DisplayableObjects.add(obstacles);
    }

    public void GenerateObstacles(Class _class, int number) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        for(var i = 0; i < number; i++){
            Obstacle obstacle = (Obstacle) GenerateDisplayable(_class);
            obstacles.add(obstacle);
        }
    }

    private IDisplayable GenerateDisplayable(Class _class) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        IDisplayable object = (IDisplayable) GenerateObject(_class);
        int x = (int) Math.max(Math.round(Math.random() * maxX - 1), 0);
        int y = (int) Math.max(Math.round(Math.random() * maxY - 1), 0);
        object.init(this, x, y);
        return object;
    }

    @Override
    public void GenerateMonsters(Class _class, int number) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        for(var i = 0; i < number; i++){
            Monster monster = (Monster) GenerateDisplayable(_class);
            monsters.add(monster);
        }
    }

    public void GenerateMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        GenerateMonsters(BlackWolf.class, 4);
        GenerateMonsters(BlackWolfChief.class, 1);
        GenerateMonsters(WolfKing.class, 1);

        DisplayableObjects.add(monsters);
    }

    public void GenerateChests(){
        for(var i = 0; i < 2; i++){
            int x = (int) Math.max(Math.round(Math.random() * maxX - 1), 0);
            int y = (int) Math.max(Math.round(Math.random() * maxY - 1), 0);
            Chest chest = new Chest( x, y);
            chest.drop.addRandomResource(ResourceType.Coins, 10, 150);
            chest.drop.addRandomResource(ResourceType.Ore, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Wood, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Stone, 250, 500, 0.25f);
            chest.drop.addRandomResource(ResourceType.Leather, 1, 5, 0.10f);
            chest.drop.addRandomResource(ResourceType.Fur, 1, 5, 0.10f);
            chest.drop.addRandomEquipment(EquipmentType.Sling, 1, 2, 0.05f);
            chest.drop.addRandomEquipment(EquipmentType.HuntBow, 1, 2, 0.03f);
            chest.drop.addRandomEquipment(EquipmentType.CorralSpear, 1, 1, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.BearSpear, 1, 1, 0.01f);
            chest.drop.addRandomEquipment(EquipmentType.SpyGlass, 1, 1, 0.005f);
            chests.add(chest);
        }
        DisplayableObjects.add(chests);
    }

    @Override
    public boolean canMonsterMove(int x, int y) {
        return ObjectAt(x, y) == null;
    }
}
