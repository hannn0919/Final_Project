package Frogger.main;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.border.EmptyBorder;


import Frogger.util.*;
import House.house.House;
import Main.*;


public class FroggerPanel extends JPanel
{
    private Timer timer;
    public int time = 6000;
    private ArrayList<Car> cars;
    private Frog frog;
    private Car[] CarsRoadOne = new Car[5];
    private Car[] CarsRoadTwo = new Car[3];
    private Car[] CarsRoadThree = new Car[4];
    private Car[] CarsRoadFour = new Car[6];
    private Car police;
    public int hitByPolice = 0;
    public int died = 0;
    private int policeShow;
    private UnderWay[] underWays = new UnderWay[3];
    private int end;
    private Main mainFrame ;
    private House house;
    private int stepX ;
    private int stepY ;
    private final int oneStep = 80;
    private int policeTime;
    private Random random;
    public boolean expCard = false;
    public boolean moneyCard = false;
    public boolean noPolice = false;
    public boolean underKey = false;
    public int endTime = 0;

    public FroggerPanel(Main mainFrame, House house)
    {
        this.random = new Random();
        this.house = house;
        this.mainFrame = mainFrame;
        this.setFocusable(true);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        Init();
        repaint();
        Keylisten listener = new Keylisten();
        this.addKeyListener(listener);
    }

    public class Keylisten extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key1 = e.getKeyCode();
            char key2 = e.getKeyChar();

            switch (key1) {
                case KeyEvent.VK_UP:
                    if(frog.getX() == 225 && frog.getY() == underWays[0].getY() && underKey){
                        underKey = false;
                        setStep(3);
                    }else if(frog.getX() == 225 && frog.getY() == underWays[1].getY() && underKey){
                        underKey = false;
                        setStep(3);
                    }
                    frog.move(0, -stepY);
                    if(house.getEquipment("彈簧鞋")==1){
                        setStep(2);
                    }else setStep(1);
                    break;
                case KeyEvent.VK_LEFT:
                    frog.move(-stepX, 0);
                    if(house.getEquipment("彈簧鞋")==1){
                        setStep(2);
                    }else setStep(1);
                    break;
                case KeyEvent.VK_DOWN:
                    frog.move(0, stepY);
                    if(house.getEquipment("彈簧鞋")==1){
                        setStep(2);
                    }else setStep(1);
                    break;
                case KeyEvent.VK_RIGHT:
                    frog.move(stepX, 0);
                    if(house.getEquipment("彈簧鞋")==1){
                        setStep(2);
                    }else setStep(1);
                    break;
                default:
                    System.out.println(key2);
            }
        }
    }

    private void Init()
    {
        String character = "data/role/"+"LV"+house.getLevel()+"/肝";
        if(house.getEquipment("透視眼鏡")==1){
            character += "+眼鏡";
        }

        if(house.getEquipment("竹蜻蜓")==1){
            character += "+竹蜻蜓";
        }

        if(house.getEquipment("翅膀")==1){
            character += "+翅膀";
        }

        if(house.getEquipment("彈簧鞋")==1){
            character += "+彈簧鞋";
            setStep(2);
        }else setStep(1);

        this.end = 1;
        died = 0;
        hitByPolice = 0;
        cars = new ArrayList<>();
        frog = new Frog(Frog.startX, Frog.startY, 70, 70, character + ".png");
        policeTime = 6000 - random.nextInt(2000);
        police = new Car( 900, 300, 120 -10, 80-  10, 0, "police170r.png");
        policeShow = 0;
        for(int i =0;i<5;i++){
            CarsRoadOne[i] = new Car(10 + i * 280, 460, 120-10, 80-10, 1, "orangecarr.png"); // 5
            cars.add(CarsRoadOne[i]);
        }
        for(int i =0;i<3;i++){
            CarsRoadTwo[i] = new Car( 20 + i * 466 , 380, 200-10, 80-10, 4, "truck200r.png"); // 3
            cars.add(CarsRoadTwo[i]);
        }
        for(int i =0;i<4;i++){
            CarsRoadThree[i] = new Car( 40 + i * 350, 220, 170-10, 80-10, -3, "truck170l.png"); // 4
            cars.add(CarsRoadThree[i]);
        }
        for(int i =0;i<6;i++){
            CarsRoadFour[i] = new Car( 15 + i * 240 , 140, 120-10 , 80-10, -2, "bluecarl.png"); // 6
            cars.add(CarsRoadFour[i]);
        }
        for(int i = 0; i< 3;i++){
            underWays[i] = new UnderWay(225, 545 - i * 240, 80, 80, "underWay.png");
        }
        /*
        if(noPolice){
            house.gameSettlementItem("警察卡");
        }
        if(expCard){
            house.gameSettlementItem("經驗加倍券");
        }
        if(moneyCard){
            house.gameSettlementItem("金錢加倍券");
        }
        if(underKey){
            house.gameSettlementItem("地下道鑰匙");
        }
*/
    }

    public void gameStart()
    {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(time == 0){
                    timer.cancel();
                }
                time--;
                if(time == 99){
                    endTime = time;
                    time = 0;
                    timer.cancel();
                }
                if(time == policeTime){
                    police.setSpeed(-20);
                    policeShow = 1;
                }
                if(police.getX() < 0){
                    police.setSpeed(0);
                    policeShow = 0;
                    policeTime = random.nextInt(policeTime) + 200;
                }
                if( time % 2000 == 0){
                    policeTime = time - random.nextInt(2000) ;
                    police.setX(900);
                }
                if(end == 0){
                    timer.cancel();
                }
                for( Car c : cars){
                    if(c.intersect(frog)){
                        frog.setX(Frog.startX);
                        frog.setY(Frog.startY);
                        died++;
                    }
                    c.update();
                }
                if(police.getSpeed() != 0){
                    if(police.intersect(frog) ){
                        frog.setX(Frog.startX);
                        frog.setY(Frog.startY);
                        died++;
                        if(!noPolice)
                            hitByPolice++;
                    }
                    police.update();
                }
                if(frog.win()){
                    endTime = time;
                    time = 0;
                    timer.cancel();
                }
                repaint();
            }
        }, 0, 10);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // clears drawing area

        try {
            Image backGroundImage = ImageIO.read(new File("data/Frogger/image/froggerBackGround2.png"));
            g.drawImage(backGroundImage, 0, 0, null);
            Image t= ImageIO.read(new File(frog.getImageName()));
            Image frogImage = t.getScaledInstance(frog.getW(), frog.getW(), Image.SCALE_SMOOTH);
            g.drawImage(frogImage, frog.getX(), frog.getY(), null);
            for (Car c : cars) {
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + c.getImageName()));
                g.drawImage(tempImage, c.getX() - 5, c.getY() - 5, null);
            }
            if(policeShow == 1) {
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + police.getImageName()));
                g.drawImage(tempImage, police.getX() - 5, police.getY() - 5, null);
            }
        }
        catch (Exception ex) {
                System.out.println("No example.jpg!!");
        }

    }

    public void setStep(int k) {
        stepY = k * oneStep;
        stepX = oneStep / 2;
    }
} // end class PaintPanel
