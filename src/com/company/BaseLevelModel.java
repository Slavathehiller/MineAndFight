package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class BaseLevelModel implements ISubLevelModel, IMap{
    Player player;
    protected int maxX = 50;
    protected int maxY = 20;
    protected int maxObstacles = 20;
    protected int minObstacles = 12;
    protected int MonsterLimit = 35;
    protected Monster LevelBoss;
    public boolean LevelBossIsDead = false;
    protected boolean wantToExit = false;
    public String Log;
    protected ArrayList<IDisplayable> obstacles = new ArrayList<>();
    protected ArrayList<IDisplayable> monsters = new ArrayList<>();
    protected ArrayList<IDisplayable> chests = new ArrayList<>();
    protected ArrayList<ArrayList<IDisplayable>> DisplayableObjects = new ArrayList<>();
    public Boolean[] messages = new Boolean[]{false};
    protected ArrayList<String> customMessages = new ArrayList<>();

    public BaseLevelModel(Player player){
        this.player = player;
        player.X = 1;
        player.Y = 1;
        ArrayList<IDisplayable> iExitPoints = new ArrayList<>();
        iExitPoints.add(new ExitPoint(this));
        DisplayableObjects.add(iExitPoints);
        GenerateMonsters();
        GenerateObstacles();
        GenerateChests();
        ArrayList<IDisplayable> iPlayers = new ArrayList<>();
        iPlayers.add(player);
        DisplayableObjects.add(iPlayers);

    }

    public BaseLevelModel() {

    }

    @Override
    public boolean getPlayerIsMasked(){
        return false;
    }

    @Override
    public Monster getLevelBoss() {
        return LevelBoss;
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

    public void setWantToExit(boolean value){
        wantToExit = value;
    }

    private void ActMonsters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        for(var monster:monsters){
            ((Monster)monster).Act();
        }
    }

    public Point GenerateFreeCordsWithin(Point point, int r){
        var startPoint = new Point();
        startPoint.X  = point.X - r;
        startPoint.Y = point.Y - r;
        ArrayList<Point> points = new ArrayList<>();
        for (var y = startPoint.Y; y <= point.Y + r; y++) {
            for (var x = startPoint.X; x <= point.X + r; x++) {
                 if(ObjectAt(x, y) == null && !(x == point.X && y == point.Y) && x >= 0 && y >= 0 && x < maxX && y < maxY)
                    points.add(new Point(x, y));
            }
        }
        if(points.size() > 0)
            return points.get((int)Math.round(Math.random() * (points.size() - 1)));
        else
            return null;
    }

    protected Point GenerateFreeCords(){
        int x;
        int y;
        do {
            x = (int) Math.max(Math.round(Math.random() * maxX - 1), 0);
            y = (int) Math.max(Math.round(Math.random() * maxY - 1), 0);
        }
        while (ObjectAt(x, y) != null);
        return new Point(x, y);
    }


    protected Boolean TryMove(int x, int y){
        if(player.isBattleBuff(BattleBuffType.Stun)) {
            Log += "Вы оглушены и не можете двигаться\n";
            return false;
        }
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
            if(object.getObjectType() == CollisionObjectTypes.ExitPoint){
                wantToExit = true;
            }
            System.out.println("Препятствие в точке: " + x + " : "+ y);
            System.out.println(ObjectAt(x, y));
            return false;
        }
    }

    public boolean getWantToExit(){
        return wantToExit;
    }

    public void ClearLog(){
        Log = "";
    }

    protected IDisplayable ObjectAt(int x, int y){
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
    public float RangeFromToObject(int x, int y, IDisplayable object){
        return RangeFromTo(x, y, object.getX(), object.getY());
    }

    @Override
    public float DistanceBetweenObjects(IDisplayable object1, IDisplayable object2){
        return RangeFromTo(object1.getX(), object2.getY(), object2.getX(), object2.getY());
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
        var cord = GetStepToward(new Point(mover.getX(), mover.getY()), new Point(x, y));
        MoveIfCan(mover, cord.X, cord.Y);
    }

    @Override
    public void MoveTowardObject(IMovableDisplayable mover, IDisplayable object){
        MoveToward(mover, object.getX(), object.getY());
    }

    @Override
    public boolean CheckPathToward(Point pointFrom, Point pointTo){
        var currentPoint = new Point(GetStepToward(pointFrom, pointTo));
        while (!currentPoint.Equal(pointTo)){
            if(ObjectAt(currentPoint.X, currentPoint.Y) != null){
                return false;
            }
            currentPoint = GetStepToward(currentPoint, pointTo);
        }
        return true;
    }

    public Point NearestPointToward(Point pointFrom, Point pointTo){
        var predPoint = new Point(pointFrom);
        var currentPoint = new Point(pointFrom);
        while (!currentPoint.Equal(pointTo)){
            predPoint = new Point(currentPoint);
            currentPoint = GetStepToward(currentPoint, pointTo);
        }
        return predPoint;
    }

    public Point GetStepToward(Point pointFrom, Point pointTo){
        var result = new Point(pointFrom.X, pointFrom.Y);
        if(result.Equal(pointTo)){
            return result;
        }
        var deltaX = Math.abs(pointTo.X - pointFrom.X);
        var deltaY = Math.abs(pointTo.Y - pointFrom.Y);
        if(deltaX > deltaY){
            if(pointTo.X > pointFrom.X){
                result.X += 1;
            }
            else{
                result.X -= 1;
            }
        }
        else{
            if(pointTo.Y > pointFrom.Y){
                result.Y += 1;
            }
            else{
                result.Y -= 1;
            }
        }
        return result;
    }

    public void Attack(IFighter attacker, IFighter target){
        var diff = attacker.getPower() / target.getPower();
        var chance = Math.sqrt(diff) * 50 / 100;
        chance = Math.min(chance, 0.999);
        chance = Math.max(chance, 0.001);
        System.out.println("Расчет удара");
        System.out.println("Шанс попадания: " + chance);
        var toHit = Math.random();
        System.out.println("Выпало: " + toHit);
        if(chance >= toHit){
            float damage = diff * 5;
            damage = (float) Math.round(damage * 10) / 10f;
            if(target.getLycanthrope() && attacker.getFighterType() == CollisionObjectTypes.Player && ((Player)attacker).haveArtefact(Artefacts.SilverSpear)){
                damage *= 2;
                Log += "Серебрянное копье наносит оборотню двойной урон.\n";
            }
            if(target.getGiant() && attacker.getFighterType() == CollisionObjectTypes.Player && ((Player)attacker).haveArtefact(Artefacts.TheGiantKiller)){
                damage *= 2;
                Log += "Убийца великанов наносит великану двойной урон.\n";
            }
            Log += attacker.getName() + " бьет " + target.getName() + " и наносит " + damage + " урона.\n";
            target.changeHealth(-damage);
            if(target.getFighterType() == CollisionObjectTypes.Player){
                for(Buffing buffing: attacker.getBuffing()){
                    var chanceToBuff = Math.random();
                    if(buffing.Chance >= chanceToBuff){
                        ((Player)target).setBattleBuff(buffing.BuffType, buffing.Duration, buffing.Power);
                        Log += "Вы получаете " + BattleBuffType.names[buffing.BuffType] + ".\n";
                    }
                }
            }
            if(target.getHealth() <= 0){
                if(target.getFighterType() == CollisionObjectTypes.Monster) {
                    Log += target.getName() + " погибает\n";
                    if(attacker.getFighterType() == CollisionObjectTypes.Player && !target.getDrop().isEmpty()) {
                        player.addDrop(target.getDrop());
                        Log += "Вы получаете" + target.getDrop().getDetails();
                    }
                    var monster = (Monster)target;
                    monster.Die();
                }
            }
        } else{
            Log += attacker.getName() + " бьет " + target.getName() + " и промахивается.\n";
        }
    }

    public void RangedAttack(IFighter attacker, IFighter target, String actionMessage) {
        var diff = attacker.getPower() / target.getMasked();
        var chance = Math.sqrt(diff) * 50 / 100;
        chance = Math.min(chance, 0.999);
        chance = Math.max(chance, 0.001);
        System.out.println("Расчет стрельбы");
        System.out.println("Шанс попадания: " + chance);
        var toHit = Math.random();
        System.out.println("Выпало: " + toHit);
        if(chance >= toHit) {
            float damage = attacker.getRangedPower() / target.getPower() * 10;
            damage = (float) Math.round(damage * 10) / 10f;
            Log += attacker.getName() + actionMessage + target.getName() + " и наносит " + damage + " урона.\n";
            target.changeHealth(-damage);
            if(target.getFighterType() == CollisionObjectTypes.Player){
                for(Buffing buffing: attacker.getBuffing()){
                    var chanceToBuff = Math.random();
                    if(buffing.Chance >= chanceToBuff){
                        ((Player)target).setBattleBuff(buffing.BuffType, buffing.Duration, buffing.Power);
                        Log += "Вы получаете " + BattleBuffType.names[buffing.BuffType] + ".\n";
                    }
                }
            }
        }
        else{
            Log += attacker.getName() + " стреляет по " + target.getName() + " и промахивается.\n";
        }
    }

    private void AddCustomMessage(String message){
        customMessages.add(message);
    }

    public boolean getLevelBossHasDied(){
        if(LevelBoss.getHealth() < 1 && !LevelBossIsDead){
            LevelBossIsDead = true;
            return true;
        }
        return false;
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

    public Object GenerateObject(Class _class){
        try{
            return _class.getDeclaredConstructor().newInstance();
        }
        catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException invocationTargetException) {
            invocationTargetException.printStackTrace();
            return null;
        }
    }

    protected int getObstacleCount(){
        return (int) Math.round(Math.random() * ( maxObstacles - minObstacles )) + minObstacles;
    }

    public abstract void GenerateObstacles();

    public void GenerateObstacles(Class _class, int number){
        for(var i = 0; i < number; i++){
            Obstacle obstacle = (Obstacle) GenerateDisplayable(_class);
            obstacles.add(obstacle);
        }
    }

    public void GenerateLevelBoss(Monster levelBoss){
        var cords = GenerateFreeCordsWithin(new Point(maxX, maxY), 6);
        levelBoss.init(this, cords.X - 3, cords.Y - 3);
        LevelBoss = levelBoss;
        monsters.add(LevelBoss);
    }

    private IDisplayable GenerateDisplayable(Class _class){
        IDisplayable object = (IDisplayable) GenerateObject(_class);
        var point = GenerateFreeCords();
        object.init(this, point.X, point.Y);
        return object;
    }

    @Override
    public void GenerateMonsters(Class _class, int number){
        for(var i = 0; i < number; i++){
            GenerateMonster(_class);
        }
    }

    public Monster GenerateMonster(Class _class, int x, int y){
        var monster = GenerateMonster(_class);
        if(monster != null){
            monster.X = x;
            monster.Y = y;
        }
        return monster;
    }

    public Monster GenerateMonster(Class _class){
        if (monsters.size() <= MonsterLimit) {
            Monster monster = (Monster) GenerateDisplayable(_class);
            monsters.add(monster);
            return monster;
        }
        return null;
    }

    public void GenerateMonsters(){
        DisplayableObjects.add(monsters);
    }

    public abstract void GenerateChests();

    @Override
    public boolean canMonsterMove(int x, int y) {
        return ObjectAt(x, y) == null;
    }

    @Override
    public int getMonsterLimit(){
        return MonsterLimit;
    }
}
