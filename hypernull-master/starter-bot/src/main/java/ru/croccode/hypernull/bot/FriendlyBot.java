package ru.croccode.hypernull.bot;

import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Point;
import ru.croccode.hypernull.geometry.Size;

import java.util.*;

public class FriendlyBot {
    private static final Random rnd = new Random(System.currentTimeMillis());
    private final Size mapSize;
    private char[][] map;
    private final int viewRadius;
    private Point myPosition;
    private final int id;
    private int moveCount = 0;
    private boolean countUp = false;

    public FriendlyBot(Size mapSize,char[][] map,int viewRadius, int id){
        this.mapSize = mapSize;
        this.map = map;
        this.viewRadius = viewRadius;
        this.id = id;
    }

    public Offset getMoveOffset(Set<Point> blocks, Map<Integer,Integer> botCoins, Map<Integer,Point> bots, Set<Point> coins){

        Point purpose = AnalyzeMap(blocks, botCoins, bots, coins);  // анализ карты, поиск цели
        return GeneratePurpose(purpose);
    }

    private Point AnalyzeMap(Set<Point> blocks, Map<Integer,Integer> botCoins, Map<Integer,Point> bots, Set<Point> coins){

        Point purpose = null;
        myPosition = bots.get(id);
        GenerateMap(blocks, botCoins, bots, coins);  // генерируем видимые объекты на карте

        if (coins != null) purpose = TheBestCoin(botCoins, bots, coins);  // поиск монеток как смысл жизни
        if (purpose == null) purpose = GenerateNextStep(); // поиск цели

        return purpose;
    }

    private void GenerateMap(Set<Point> blocks, Map<Integer,Integer> botCoins, Map<Integer,Point> bots, Set<Point> coins){

          if (blocks != null){
            for (Point block : blocks){
                map[block.x()][block.y()] = 'x';  // препятствие
            }
          }
        for (int i = -viewRadius; i < viewRadius; i++)
            for (int j = -viewRadius; j < viewRadius; j++){
                Point p = myPosition.apply(new Offset(i + myPosition.x(),j + myPosition.y()), mapSize);
                if ((map[p.x()][p.y()] != ' ') && (map[p.x()][p.y()] != 'x'))
                    map[p.x()][p.y()] = '.';
            }
        if (coins != null){
            for (Point coin : coins){
                map[coin.x()][coin.y()] = 'c';  // монетки
            }
        }
        if (bots != null){
            for (Point bot : bots.values()){
                map[bot.x()][bot.y()] = 'b';  // боты
            }
        }

    }
    private Point TheBestCoin(Map<Integer, Integer> botCoins, Map<Integer, Point> bots, Set<Point> coins){  // выбор лучшей монетки
        int min = 1000;
        Point retCoin = null;
        Set<Point> normCoins = new HashSet<>();
        for(Map.Entry<Integer,Point> bot : bots.entrySet()) {
            for (Point coin : coins){
                int distPos = myPosition.offsetTo(coin,mapSize).length2();
                int distBot = bot.getValue().offsetTo(coin, mapSize).length2();
                if ((distPos <= distBot) && (botCoins.get(bot.getKey()) <= botCoins.get(id)))  // расстояние от меня до бота + количество монеток?
                    normCoins.add(coin);
            }
        }

        for (Point coin : normCoins){
            if (min > myPosition.offsetTo(coin, mapSize).length2()){
                min = myPosition.offsetTo(coin, mapSize).length2();
                retCoin = coin;
            }
        }
        return retCoin;
    }

    private Point GenerateNextStep(){  // если цель не монетка, идем вправо
        moveCount ++;
        if (moveCount % 4 == 0) return myPosition.apply(new Offset(5,-5));
        return myPosition.apply(new Offset(5,0));
    }

    private Offset GeneratePurpose(Point purpose){
        Offset offset = AlgoritmFinder(purpose);  // алгоритм обхода препятствий и выбора пути
        if (offset == null){
            if (map[myPosition.apply(new Offset(1,1),mapSize).x()][myPosition.apply(new Offset(1,1),mapSize).y()] != 'x')
                return new Offset(1,1);
            else if (map[myPosition.apply(new Offset(1,0),mapSize).x()][myPosition.apply(new Offset(1,0),mapSize).y()] != 'x')
                return new Offset(1,0);
            else if (map[myPosition.apply(new Offset(1,-1),mapSize).x()][myPosition.apply(new Offset(1,-1),mapSize).y()] != 'x')
                return new Offset(0,1);
            else if (map[myPosition.apply(new Offset(0,1),mapSize).x()][myPosition.apply(new Offset(0,1),mapSize).y()] != 'x')
                return new Offset(1,-1);
            else if (map[myPosition.apply(new Offset(-1,1),mapSize).x()][myPosition.apply(new Offset(-1,1),mapSize).y()] != 'x')
                return new Offset(0,-1);
            else if (map[myPosition.apply(new Offset(0,-1),mapSize).x()][myPosition.apply(new Offset(0,-1),mapSize).y()] != 'x')
                return new Offset(-1,-1);
            else if (map[myPosition.apply(new Offset(-1,0),mapSize).x()][myPosition.apply(new Offset(-1,0),mapSize).y()] != 'x')
                return new Offset(-1,0);
            else if (map[myPosition.apply(new Offset(-1,0),mapSize).x()][myPosition.apply(new Offset(-1,0),mapSize).y()] != 'x')
                return new Offset(-1,1);
        }
        return offset;
    }

    private Offset AlgoritmFinder(Point purpose){
        int x = 0;
        int y = 0;
        if (purpose == null) return null;
        if (purpose.x() - myPosition.x() > 0) x = 1;
        else if (purpose.x() - myPosition.x() == 0) x = 0;
        else if (purpose.x() - myPosition.x() < 0) x = -1;

        if (purpose.y() - myPosition.y() > 0) y = 1;
        else if (purpose.y() - myPosition.y() == 0) y = 0;
        else if (purpose.y() - myPosition.y() < 0) y = -1;
        if (map[myPosition.apply(new Offset(x,y),mapSize).x()][myPosition.apply(new Offset(x,y),mapSize).y()] != 'x')
            return new Offset(x,y);
        if ((map[myPosition.apply(new Offset(0,1),mapSize).x()][myPosition.apply(new Offset(0,1),mapSize).y()] == 'x') &&
                (map[myPosition.apply(new Offset(1,0),mapSize).x()][myPosition.apply(new Offset(1,0),mapSize).y()] == 'x')){
            countUp = true;
            return new Offset(0,-1);
        }

        if (x >= 0) {
            if (map[myPosition.apply(new Offset(1,1),mapSize).x()][myPosition.apply(new Offset(1,1),mapSize).y()] != 'x'){
                countUp = false;
                return new Offset(1,1);
            }
            if (map[myPosition.apply(new Offset(1,0),mapSize).x()][myPosition.apply(new Offset(1,0),mapSize).y()] != 'x'){
                countUp = false;
                return new Offset(1,0);}
            if ((map[myPosition.apply(new Offset(0,1),mapSize).x()][myPosition.apply(new Offset(0,1),mapSize).y()] != 'x') && (countUp != true))
                return new Offset(0,1);
            if (map[myPosition.apply(new Offset(0,-1),mapSize).x()][myPosition.apply(new Offset(0,-1),mapSize).y()] != 'x')
                return new Offset(0,-1);
            if (map[myPosition.apply(new Offset(1,-1),mapSize).x()][myPosition.apply(new Offset(1,-1),mapSize).y()] != 'x')
                return new Offset(1,-1);
            if (map[myPosition.apply(new Offset(-1,-1),mapSize).x()][myPosition.apply(new Offset(-1,-1),mapSize).y()] != 'x')
                return new Offset(-1,-1);
            if (map[myPosition.apply(new Offset(-1,0),mapSize).x()][myPosition.apply(new Offset(-1,0),mapSize).y()] != 'x')
                return new Offset(-1,0);
            if (map[myPosition.apply(new Offset(-1,1),mapSize).x()][myPosition.apply(new Offset(-1,1),mapSize).y()] != 'x')
                return new Offset(-1,1);
        }
        if (x < 0){
            if (map[myPosition.apply(new Offset(0,-1),mapSize).x()][myPosition.apply(new Offset(1,-1),mapSize).y()] != 'x')
                return new Offset(1,-1);
            if (map[myPosition.apply(new Offset(-1,-1),mapSize).x()][myPosition.apply(new Offset(-1,-1),mapSize).y()] != 'x')
                return new Offset(-1,-1);
            if (map[myPosition.apply(new Offset(-1,0),mapSize).x()][myPosition.apply(new Offset(-1,0),mapSize).y()] != 'x')
                return new Offset(-1,0);
            if (map[myPosition.apply(new Offset(-1,1),mapSize).x()][myPosition.apply(new Offset(-1,1),mapSize).y()] != 'x')
                return new Offset(-1,1);
            if (map[myPosition.apply(new Offset(1,1),mapSize).x()][myPosition.apply(new Offset(1,1),mapSize).y()] != 'x')
                return new Offset(1,1);
            if (map[myPosition.apply(new Offset(1,0),mapSize).x()][myPosition.apply(new Offset(1,0),mapSize).y()] != 'x')
                return new Offset(1,0);
            if (map[myPosition.apply(new Offset(0,1),mapSize).x()][myPosition.apply(new Offset(0,1),mapSize).y()] != 'x')
                return new Offset(0,1);
            if (map[myPosition.apply(new Offset(0,-1),mapSize).x()][myPosition.apply(new Offset(-1,0),mapSize).y()] != 'x')
                return new Offset(-1,0);
        }
        return null;
    }

}
