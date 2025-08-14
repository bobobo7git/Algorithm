

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = input();
//        game.render();
        game.run();
    }
    static Game input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] grid = new char[N+2][M+2];
        int L = 0;
        int K = 0;
        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=M; j++) {
                grid[i][j] = line.charAt(j-1);
                // Enemy
                if (grid[i][j] == '&' || grid[i][j] == 'M') K++;
                // Item Box
                else if (grid[i][j] == 'B') L++;
            }
        }

        Deque<Character> commands = new ArrayDeque<>();
        String commandString = br.readLine();
        for (char c: commandString.toCharArray()) {
            commands.offer(c);
        }

        List<String> infoList = new ArrayList<>();
        for (int i=0; i<K; i++) {
            infoList.add(br.readLine());
        }
        for (int i=0; i<L; i++) {
            infoList.add(br.readLine());
        }

        Game game = Game.getInstance();
        game.init(grid, L, K, infoList, commands);

        return game;
    }
}
class Game {
    static Game instance;
    int turn;
    static boolean isOver = false;
    static boolean BOSS_SLAINED = false;
    static boolean KILLED = false;

    static int sr, sc;
    Queue<Character> commands;
    Object[][] map;
    Player player;
    // L R U D
    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};
    private Game() {
        this.turn = 0;
    }
    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }
    public void run() {
        while (!commands.isEmpty()) {
            commandPlayer();
            if (!checkPlayerAlive()) isOver = true;
            if (isOver) {
                break;
            }
        }
        render();
        System.out.println("Passed Turns : "+turn);
        player.printPlayerStats();
        if (player.killedBy != null) {
            System.out.println("YOU HAVE BEEN KILLED BY "+player.killedBy+"..");
        }
        else if (!isOver) {
            System.out.println("Press any key to continue.");
        }
        else if (BOSS_SLAINED) {
            System.out.println("YOU WIN!");
        }
//        System.out.println(player.accessories);
    }
    public void commandPlayer() {
        if (commands.isEmpty()) return;
        char cmd = commands.poll();
        Position playerPos = player.position;
        int nr = playerPos.r;
        int nc = playerPos.c;
        if (cmd == 'L') {
            nr += dr[0];
            nc += dc[0];
        } else if (cmd == 'R') {
            nr += dr[1];
            nc += dc[1];
        } else if (cmd == 'U') {
            nr += dr[2];
            nc += dc[2];
        } else if (cmd == 'D') {
            nr += dr[3];
            nc += dc[3];
        }

        if (map[nr][nc] instanceof Passible) {
            player.move(new Position(nr, nc));
        }
//        System.out.println(player.position);
        if (map[player.position.r][player.position.c] instanceof Interactable) {
            ((Interactable) map[player.position.r][player.position.c]).interact(player);
            if (map[player.position.r][player.position.c] instanceof Removable && checkPlayerAlive()) {
                map[player.position.r][player.position.c] = new Empty(new Position(player.position.r, player.position.c));
            }
        }

        turn++;
    }
    public boolean checkPlayerAlive() {
        return player.hp > 0;
    }

    public void render() {
        for (int i=1; i<map.length-1; i++) {
            for (int j=1; j<map[0].length-1; j++) {
                if (map[i][j] instanceof Item) System.out.print("B");
                else if (map[i][j] instanceof Boss) System.out.print("M");
                else if (map[i][j] instanceof Enemy) System.out.print("&");
                else if (player.position.r == i && player.position.c == j && checkPlayerAlive()) System.out.print("@");
                else System.out.print(map[i][j]);

            }
            System.out.println();
        }
    }
    public void init(char[][] grid, int L, int K, List<String> infoList, Deque<Character> commands) {
        int N = grid.length;
        int M = grid[0].length;
        map = new Object[N][M];
        // Enemy
        for (int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(infoList.get(i));
            // R C S W A H E
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int atk = Integer.parseInt(st.nextToken());
            int def = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());
            int exp = Integer.parseInt(st.nextToken());

            Position position = new Position(r, c);
            if (grid[r][c] == 'M') map[r][c] = new Boss(position, atk, def, hp, exp, name);
            else if (grid[r][c] == '&') map[r][c] = new Monster(position, atk, def, hp, exp, name);
        }
        // Item box
        for (int i=K; i<L+K; i++) {
            StringTokenizer st = new StringTokenizer(infoList.get(i));
            // R C T S
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Position position = new Position(r, c);

            String t = st.nextToken();
            if (t.equals("W")) {
                int atk = Integer.parseInt(st.nextToken());
                Weapon weapon = new Weapon(position, atk);
                map[r][c] = weapon;
            } else if (t.equals("A")) {
                int def = Integer.parseInt(st.nextToken());
                Armor armor = new Armor(position, def);
                map[r][c] = armor;
            } else if (t.equals("O")){
                String type = st.nextToken();
                Accessory accessory = new Accessory(position, type);
                Effect effect = null;
                if (type.equals("HR")) effect = new HPRegenerationEffect();
                else if (type.equals("RE")) effect = new ReincarnationEffect();
                else if (type.equals("CO")) effect = new CourageEffect();
                else if (type.equals("EX")) effect = new ExperienceEffect();
                else if (type.equals("DX")) effect = new DexterityEffect();
                else if (type.equals("HU")) effect = new HunterEffect();
                else if (type.equals("CU")) effect = new CursedEffect();

                if (effect != null) accessory.setEffect(effect);
                map[r][c] = accessory;
            }


        }
        this.commands = commands;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Position position = new Position(i, j);
                if (i==0 || i==N-1) map[i][j] = new Wall(position);
                else if (j==0 || j==M-1) map[i][j] = new Wall(position);
                else {
                    if (grid[i][j] == '#') map[i][j] = new Wall(position);
                    else if (grid[i][j] == '@') {
                        this.player = new Player(position);
                        map[i][j] = new Empty(position);
                        sr = i;
                        sc = j;
                    }
                    else if (grid[i][j] == '.') map[i][j] = new Empty(position);
                    else if (grid[i][j] == '^') map[i][j] = new Obstacle(position);
                }
            }
        }
    }

}

class Player {
    Position position;
    int hp, atk, def, maxHp;
    int buffed;
    int level;
    int exp;
    Weapon weapon;
    Armor armor;
    Set<Accessory> accessories;
    String killedBy;

    public Player(Position position) {
        this.maxHp = 20;
        this.hp = maxHp;
        this.atk = 2;
        this.def = 2;
        this.buffed = 0;
        this.level = 1;
        this.exp = 0;
        this.position = position;
        accessories = new HashSet<>();
    }
    private int getMaxExp() {
        return level * 5;
    }

    public void gainExp(int amount, float g) {
        int maxExp = getMaxExp();
        this.exp += (int) (amount * g);
        if (this.exp >= maxExp) {
            levelUp();
            this.exp = 0;
        }
    }
    private void levelUp() {
        this.level += 1;
        this.maxHp += 5;
        this.atk += 2;
        this.def += 2;
        this.hp = maxHp;
    }
    public void move(Position nextPos) {
        this.position = nextPos;
    }
    public void obtain(Item item) {
        if (item instanceof Weapon) {
            this.weapon = (Weapon) item;
        } else if (item instanceof Armor) {
            this.armor = (Armor) item;
        } else if (item instanceof Accessory) {
            if (accessories.size() >= 4) return;
            Accessory accessory = (Accessory) item;
            for (Accessory acc: accessories) {
                if (acc.type.equals(((Accessory) item).type)) return;
            }

            accessories.add(accessory);

        }
    }

    public void damage(int amount) {
        Accessory dx = getAccessaryWithEffect("DX");
        if (dx != null) amount = 1;
        hp = Math.max(hp-amount, 0);
        if (hp == 0) {
            killedBy = "SPIKE TRAP";
            Accessory re = getAccessaryWithEffect("RE");
            if (re != null) {
                re.effect.apply(this);
                accessories.remove(re);
                Game.isOver = false;
                killedBy = null;
            }
        }
    }
    private int getTotalAtk() {
        int totalAtk = this.atk;
        if (weapon != null) totalAtk += weapon.atk;

        return totalAtk;
    }
    private int getTotalDef() {
        int totalDef = this.def;
        if (armor != null) totalDef += armor.def;
        return totalDef;
    }
    public void battle(Enemy enemy) {
        int totalAtk = getTotalAtk();
        int totalDef = getTotalDef();
        Accessory hu = getAccessaryWithEffect("HU");
        if (enemy instanceof Boss && hu != null) hu.effect.apply(this);

        // first turn
        Accessory co = getAccessaryWithEffect("CO");
        Accessory dx = getAccessaryWithEffect("DX");
        if (co != null && dx != null) {
            co.effect.apply(this);
            dx.effect.apply(this);
        } else if (co != null) {
            co.effect.apply(this);
        }

        int firstDmg = Math.max(totalAtk + buffed - enemy.def, 1);
        int enemyDmg = Math.max(enemy.atk - totalDef, 1);

        enemy.hp = Math.max(enemy.hp-firstDmg, 0);
        if (enemy.hp > 0 && !(enemy instanceof Boss && hu != null)) {
            this.hp = Math.max(this.hp-enemyDmg, 0);
        }

        // after
        int playerDmg = Math.max(totalAtk - enemy.def, 1);
        int playerTurn = (enemy.hp + playerDmg - 1) / playerDmg;
        int enemyTurn = (this.hp + enemyDmg - 1) / enemyDmg;

        // lose
        if (playerTurn >= enemyTurn + 1) {
            this.hp = 0;
            Game.isOver = true;
            Accessory re = getAccessaryWithEffect("RE");
            killedBy = enemy.name;
            if (re != null) {
                re.effect.apply(this);
                accessories.remove(re);
                Game.isOver = false;
                killedBy = null;
                enemy.hp = enemy.maxHp;
            }
        } else {
            this.hp -= Math.max((playerTurn-1) * enemyDmg, 0);
            Accessory ex = getAccessaryWithEffect("EX");
            Accessory hr = getAccessaryWithEffect("HR");
            if (ex != null) {
                ExperienceEffect eff = (ExperienceEffect) ex.effect;
                eff.apply(this, enemy);
            }
            else gainExp(enemy.exp, 1);

            if (hr != null) {
                hr.effect.apply(this);
            }

            if (enemy instanceof Boss) {
                Game.isOver = true;
                Game.BOSS_SLAINED = true;
            }
        }

        for (Accessory accessory: accessories) {
            accessory.effect.remove(this);
        }

//        System.out.println("battle result: ");
//        printPlayerStats();
    }

    public void printPlayerStats() {
        System.out.println("LV : "+level);
        System.out.println("HP : "+hp+"/"+maxHp);
        if (weapon != null) System.out.println("ATT : "+atk+"+"+weapon.atk);
        else System.out.println("ATT : "+atk+"+"+0);
        if (armor != null) System.out.println("DEF : "+def+"+"+armor.def);
        else System.out.println("DEF : "+def+"+"+0);
        System.out.println("EXP : "+exp+"/"+getMaxExp());
    }
    public Accessory getAccessaryWithEffect(String type) {
        for (Accessory accessory: accessories) {
            if (accessory.type.equals(type)) {
                return accessory;
            }
        }
        return null;
    }

}
interface Passible {

}
class Position {
    int r, c;
    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }
    @Override
    public String toString() {
        return String.format("(%d, %d)", r, c);
    }
}
class Empty implements Passible{
    Position position;
    public Empty(Position position) {
        this.position = position;
    }
    @Override
    public String toString() {
        return ".";
    }
}
class Wall {
    Position position;
    public Wall(Position position) {
        this.position = position;
    }
    @Override
    public String toString() {
        return "#";
    }
}
interface Interactable extends Passible {
    void interact(Player player);
}
interface Removable {}

abstract class Item implements Interactable, Removable {
    Position position;
    public Item(Position position) {
        this.position = position;
    }
    @Override
    public void interact(Player player) {
        player.obtain(this);
    }


}

class Weapon extends Item {
    int atk;
    public Weapon(Position position) {
        super(position);
    }
    public Weapon(Position position, int atk) {
        super(position);
        this.atk = atk;
    }
}

class Armor extends Item {
    int def;
    public Armor(Position position) {
        super(position);
    }
    public Armor(Position position, int def) {
        super(position);
        this.def = def;
    }
}

class Accessory extends Item {
    Effect effect;
    String type;
    static final Set<String> TRIGGER_WIN = Set.of("HR", "EX");
    static final Set<String> TRIGGER_DEAD = Set.of("RE");
    static final Set<String> TRIGGER_BATTLE_FIRST_TURN = Set.of("CO");
    static final Set<String> TRIGGER_BOSS = Set.of("HU");
    static final Set<String> TRIGGER_SPIKE = Set.of("DX");
    public Accessory(Position position) {
        super(position);
    }
    public Accessory(Position position, String type) {
        super(position);
        this.type = type;
    }
    public void setEffect(Effect effect) {
        this.effect = effect;
    }
    @Override
    public String toString() {
        return type;
    }
}

interface Effect {
    void apply(Player player);
    void remove(Player player);
}

class HPRegenerationEffect implements Effect {

    public HPRegenerationEffect() {

    }
    @Override
    public void apply(Player player) {
        player.hp = Math.min(player.hp+3, player.maxHp);
    }
    @Override
    public void remove(Player player) {

    }
}
class ReincarnationEffect implements Effect {
    public ReincarnationEffect() {}

    @Override
    public void apply(Player player) {
        player.hp = player.maxHp;
        player.position.r = Game.sr;
        player.position.c = Game.sc;
    }
    @Override
    public void remove(Player player) {

    }
}
class CourageEffect implements Effect {
    public CourageEffect() {}

    @Override
    public void apply(Player player) {
        int buffed = player.weapon != null ?
                player.atk + player.weapon.atk :
                player.atk;
        player.buffed += buffed;
    }

    @Override
    public void remove(Player player) {
        player.buffed = 0;
    }
}
class ExperienceEffect implements Effect {
    public ExperienceEffect() {}

    @Override
    public void apply(Player player) {

    }
    public void apply(Player player, Enemy enemy) {
        player.gainExp(enemy.exp, 1.2f);
    }

    @Override
    public void remove(Player player) {
        player.buffed = 0;
    }
}
class DexterityEffect implements Effect {
    public DexterityEffect() {}

    @Override
    public void apply(Player player) {
        int buffed = player.weapon != null ?
                player.atk + player.weapon.atk :
                player.atk;
        player.buffed += buffed;
    }

    @Override
    public void remove(Player player) {
        player.buffed = 0;
    }
}
class HunterEffect implements Effect {
    public HunterEffect() {}

    @Override
    public void apply(Player player) {
        player.hp = player.maxHp;
    }

    @Override
    public void remove(Player player) {

    }
}
class CursedEffect implements Effect {
    public CursedEffect() {}

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {

    }
}

class Obstacle implements Interactable {
    Position position;
    static final int dmg = 5;
    public Obstacle(Position position) {
        this.position = position;
    }

    @Override
    public void interact(Player player) {
        player.damage(5);
    }
    @Override
    public String toString() {
        return "^";
    }
}

abstract class Enemy implements Interactable, Removable {
    Position position;
    int atk, def, hp, maxHp;
    int exp;
    String name;
    public Enemy(Position position) {
        this.position = position;
    }
    public Enemy(Position position, int atk, int def, int hp, int exp, String name) {
        this.position = position;
        this.atk = atk;
        this.def = def;
        this.maxHp = hp;
        this.hp = maxHp;
        this.exp = exp;
        this.name = name;
    }
    @Override
    public void interact(Player player) {
        player.battle(this);
    }
    @Override
    public String toString() {
        return name;
    }
}
class Monster extends Enemy {
    public Monster(Position position) {
        super(position);
    }
    public Monster(Position position, int atk, int def, int hp, int exp, String name) {
        super(position, atk, def, hp, exp, name);
    }
}
class Boss extends Enemy {
    public Boss(Position position) {
        super(position);
    }
    public Boss(Position position, int atk, int def, int hp, int exp, String name) {
        super(position, atk, def, hp, exp, name);
    }
}



