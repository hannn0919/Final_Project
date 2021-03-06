package House.house;

import Stock.main.StockWindow;
import java.util.HashMap;
import java.util.Map;

public class House {
    private String role;
    private static int level;
    private static int exp;
    private static int holdMoney;
    private Map<String,Integer> map = new HashMap<String, Integer>();
    private float[] stock;
    private float[] stockPrs;
    private int[] stockTicket;
    private double[][][] data;
    private int cardTotalMoney;
    private int cardTotalExp;
    private int cardTotalPair;
    private int cardTotalPlay;
    private int hamsterTotalMoney;
    private int hamsterTotalExp;
    private int hamsterTotalMistake;
    private int hamsterTotalPlay;
    private int froggerTotalMoney;
    private int froggerTotalExp;
    private int froggerTotalDied;
    private int froggerTotalPlay;
    private int dinosaurTotalMoney;
    private int dinosaurTotalExp;
    private int dinosaurTotalDied;
    private int dinosaurTotalPlay;

    public House()
    {
        this.holdMoney = 1000;
        this.level = 1;
        this.role = "鮮嫩小心肝";
        this.exp = 0;
        this.stock = new float[4];
        this.stockTicket = new int[4];
        this.stockPrs = new float[4];
        this.data = new double[4][1][10];
        for (int i = 0;i<4;i++) {
            stock[i] = (float) (10 * (i+1) );
            stockTicket[i] = 0;
            data[i][0][0]= stock[i];
            for(int j = 1;j<9;j++){
                data[i][0][j] = data[i][0][j-1] * (1F + StockWindow.getNextPercent() );
            }
            stockPrs[i] = StockWindow.getNextPercent();
            data[i][0][9] = data[i][0][8] * ( 1F + stockPrs[i]);
            stock[i] = (float) data[i][0][9];
        }
        map.put("經驗加倍券",0);
        map.put("金錢加倍券",0);
        map.put("電蚊拍",0);
        map.put("增時卡",0);
        map.put("地下道鑰匙",0);
        map.put("警察卡",0);
        map.put("老師卡",0);
        map.put("加倍卡",0);
        map.put("再看一次",0);

    }

    public Map<String,Integer> getObject(){
        map.put("level", getLevel());
        map.put("exp", getExp());
        map.put("holdMoney", getHoldMoney());
        map.put("經驗加倍券", getItem("經驗加倍券"));
        map.put("金錢加倍券", getItem("金錢加倍券"));
        map.put("電蚊拍", getItem("電蚊拍"));
        map.put("增時卡", getItem("增時卡"));
        map.put("地下道鑰匙", getItem("地下道鑰匙"));
        map.put("警察卡", getItem("警察卡"));
        map.put("老師卡", getItem("老師卡"));
        map.put("加倍卡", getItem("加倍卡"));
        map.put("再看一次", getItem("再看一次"));
        map.put("cardTotalMoney", getTotalMoney(1));
        map.put("hamsterTotalMoney", getTotalMoney(2));
        map.put("froggerTotalMoney", getTotalMoney(3));
        map.put("dinosaurTotalMoney", getTotalMoney(4));

        map.put("cardTotalExp", getTotalExp(1));
        map.put("hamsterTotalExp", getTotalExp(2));
        map.put("froggerTotalExp", getTotalExp(3));
        map.put("dinosaurTotalExp", getTotalExp(4));

        map.put("cardTotalPair", getTotalMistake(1));
        map.put("hamsterTotalMistake", getTotalMistake(2));
        map.put("froggerTotalDied", getTotalMistake(3));
        map.put("dinosaurTotalDied", getTotalMistake(4));

        map.put("cardTotalPlay", getTotalPlay(1));
        map.put("hamsterTotalPlay", getTotalPlay(2));
        map.put("froggerTotalPlay", getTotalPlay(3));
        map.put("dinosaurTotalPlay", getTotalPlay(4));

        map.put("竹蜻蜓", getEquipment("竹蜻蜓"));
        map.put("透視眼鏡", getEquipment("透視眼鏡"));
        map.put("彈簧鞋", getEquipment("彈簧鞋"));
        map.put("翅膀", getEquipment("翅膀"));
        return map;
    }

    //set the role
    public void setRole(int num){
        if (num == 1) role = "鮮嫩小心肝";
        else if(num == 2) role="尚未硬化的肝";
        else if(num == 3) role="已經快不行了的肝";
        else if(num == 4) role="小心肝硬化";
    }

    //set the number of the level
    public void setLevel(int num)
    {
        level = num;
    }

    //set the number of the exp
    public void setExp(int num)
    {
        exp=num;
        if(1000<=num&&num<3000){
            level=2;
        } else if(3000<=num&&num<6000){
            level=3;
        }else if(num>=6000&&num<10000){
            level=4;
        }else if(num>=10000) {
            level=4;
            exp=10000;
        }
        setLevel(level);
        setRole(level);
    }

    //set the number of the money
    public void setHoldMoney(int num)
    {
        holdMoney = num;
    }

    //set the Items
    public void setItem(String element,int x)
    {
        map.put(element, x);
    }

    //set the Equipment
    public void setEquipment(String element)
    {
        map.put(element, 2);
    }

    public void gameSettlementSomething(int game,int money,int EXP){
        if(game==1){
            cardTotalMoney+=money;
            cardTotalExp+=EXP;
        }else if(game==2){
            hamsterTotalMoney+=money;
            hamsterTotalExp+=EXP;
        }else if(game==3){
            froggerTotalMoney+=money;
            froggerTotalExp+=EXP;
        }else if(game==4){
            dinosaurTotalMoney+=money;
            dinosaurTotalExp+=EXP;
        }
    }

    public void gameSettlementmistake(int game,int time){
        if(game==1){
            cardTotalPair+=time;
            cardTotalPlay++;
        }else if(game==2){
            hamsterTotalMistake+=time;
            hamsterTotalPlay++;
        }else if(game==3){
            froggerTotalDied+=time;
            froggerTotalPlay++;
        }else if(game==4){
            dinosaurTotalDied+=time;
            dinosaurTotalPlay++;
        }
    }

    public int getTotalMoney(int game){
        if(game==1){
            return cardTotalMoney;
        }
        else if(game==2){
            return hamsterTotalMoney;
        }
        else if(game==3){
            return froggerTotalMoney;
        }
        else if(game==4){
            return dinosaurTotalMoney;
        }
        return 0;
    }

    public int getTotalExp(int game){
        if(game==1){
            return cardTotalExp;
        }
        else if (game==2){
            return hamsterTotalExp;
        }
        else if(game==3){
            return  froggerTotalExp;
        }
        else if(game==4){
            return  dinosaurTotalExp;
        }
        return 0;
    }

    public int getTotalMistake(int game){
        if(game==1){
            return cardTotalPair;
        }
        else if(game==2){
            return hamsterTotalMistake;
        }
        else if(game==3){
            return  froggerTotalDied;
        }
        else if(game==4){
            return  dinosaurTotalDied;
        }
        return  0;
    }

    public int getTotalPlay(int game){
        if(game==1){
            return cardTotalPlay;
        }
        else if(game==2){
            return  hamsterTotalPlay;
        }
        else if(game==3){
            return  froggerTotalPlay;
        }
        else if(game==4){
            return  dinosaurTotalPlay;

        }
        return 0;
    }

    //get the holding money
    public int getHoldMoney()
    {
        return holdMoney;
    }

    //get the level
    public int getLevel()
    {
        return level;
    }

    //get the exp
    public int getExp()
    {
        return exp;
    }

    //get the role
    public String getRole(){
        return role;
    }

    //get the item
    public int getItem(String element){
        if(map.containsKey(element))
        {
            return map.get(element);
        }
        else return 0;
    }

    //get the Equipment
    public int getEquipment(String element){
        if(map.containsKey(element))
        {
            return map.get(element);
        }
        return 0;
    }

    //use the Equipment
    public void useEquipment(String element){
            map.put(element,1);
    }

    public void sEquipment(String element, int t){
            map.put(element,t);
    }

    //get stock
    public float[] getStock()
    {
        return stock;
    }

    //set stock
    public void setStock(int i, float f)
    {
        stock[i] = f;
    }
    //get ticket
    public int[] getStockTicket(){
        return stockTicket;
    }
    // set ticket
    public void setStockTicket( int i, int n){
        stockTicket[i] = n;
    }

    //set stock
    public void setStockPrs(int i, float f)
    {
        stockPrs[i] = f;
    }

    public void setData(int i, int j, int k, double num){
        data[i][j][k] = num;
    }

    //get stock
    public float[] getStockPrs()
    {
        return stockPrs;
    }

    public double[][][] getData(){
        return data;
    }

    public void updataData(){
        for(int i = 0;i<4;i++){
            for(int j = 0;j<9;j++){
                data[i][0][j] = data[i][0][j+1];
            }
            data[i][0][9] = getStock()[i];
        }
    }

    public void setCardTotalMoney(int t){
        cardTotalMoney = t;
    }public void setCardTotalExp(int t){
        cardTotalExp = t;
    }public void setCardTotalPair(int t){
        cardTotalPair = t;
    }public void setCardTotalPlay(int t){
        cardTotalPlay = t;
    }

    public void setHamsterTotalMoney(int t){
        hamsterTotalMoney = t;
    }public void setHamsterTotalExp(int t){
        hamsterTotalExp = t;
    }public void setHamsterTotalMistake(int t){
        hamsterTotalMistake = t;
    }public void setHamsterTotalPlay(int t){
        hamsterTotalPlay = t;
    }

    public void setFroggerTotalMoney(int t){
        froggerTotalMoney = t;
    }public void setFroggerTotalExp(int t){
        froggerTotalExp = t;
    }public void setFroggerTotalDied(int t){
        froggerTotalDied = t;
    }public void setFroggerTotalPlay(int t){
        froggerTotalPlay = t;
    }

    public void setDinosaurTotalMoney(int t){
        dinosaurTotalMoney = t;
    }public void setDinosaurTotalExp(int t){
        dinosaurTotalExp = t;
    }public void setDinosaurTotalDied(int t){
        dinosaurTotalDied = t;
    }public void setDinosaurTotalPlay(int t){
        dinosaurTotalPlay = t;
    }
}