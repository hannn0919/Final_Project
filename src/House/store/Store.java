package House.store;
import House.house.House;
import Main.Main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Store extends JPanel {
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton e1, e2, e3, e4;
    private JButton item, equip;
    private BufferedImage backGround;
    private boolean IsBackGround=true;
    private Main mainFrame;
    private House house;
    private ImageIcon iconB1,iconB2,iconB3,iconB4,iconB5,iconB6,iconB7,iconB8,iconB9;
    private ImageIcon iconE1,iconE2,iconE3,iconE4;
    ButtonHandler handler;
    MouseHandler handler2;

    public Store(Main mainFrame,House house) {
        this.mainFrame=mainFrame;
        this.house=house;
        this.setBackground(Color.WHITE);
        setLayout(null);
        setPreferredSize(new Dimension(728,598));
        item = new JButton();
        equip = new JButton();

        item.setContentAreaFilled(false);
        equip.setContentAreaFilled(false);

        item.setBorderPainted(false);
        equip.setBorderPainted(false);

        item.setBounds(10,90,108,79);
        equip.setBounds(10,13,108,75);

        ButtonHandler handler = new ButtonHandler();
        MouseHandler handler2 = new MouseHandler();
        equip.addActionListener(handler);
        item.addActionListener(handler);

        add(equip);
        add(item);
        b1 = new JButton("經驗加倍券",iconB1);
        b2 = new JButton("金錢加倍券",iconB2);
        b3 = new JButton("電蚊拍",iconB3);
        b4 = new JButton("增時卡",iconB4);
        b5 = new JButton("地下室鑰匙",iconB5);
        b6 = new JButton("警察卡",iconB6);
        b7 = new JButton("老師卡",iconB7);
        b8 = new JButton("加倍卡",iconB8);
        b9 = new JButton("再看一次",iconB9);
        e1 = new JButton("竹蜻蜓",iconE1);
        e2 = new JButton("透視眼鏡",iconE2);
        e3 = new JButton("彈簧鞋",iconE3);
        e4 = new JButton("翅膀",iconE4);

        setButtonB(b1);
        setButtonB(b2);
        setButtonB(b3);
        setButtonB(b4);
        setButtonB(b5);
        setButtonB(b6);
        setButtonB(b7);
        setButtonB(b8);
        setButtonB(b9);
        setButtonE(e1);
        setButtonE(e2);
        setButtonE(e3);
        setButtonE(e4);

        b1.addMouseListener(handler2);
        b2.addMouseListener(handler2);
        b3.addMouseListener(handler2);
        b4.addMouseListener(handler2);
        b5.addMouseListener(handler2);
        b6.addMouseListener(handler2);
        b7.addMouseListener(handler2);
        b8.addMouseListener(handler2);
        b9.addMouseListener(handler2);
        if(house.getEquipment("竹蜻蜓")==0)
            e1.addMouseListener(handler2);
        if(house.getEquipment("透視眼鏡")==0)
            e2.addMouseListener(handler2);
        if(house.getEquipment("彈簧鞋")==0)
            e3.addMouseListener(handler2);
        if(house.getEquipment("翅膀")==0)
            e4.addMouseListener(handler2);

        b1.setBounds(142,32,172,174);
        b2.setBounds(314,32,200,174);
        b3.setBounds(514,32,172,174);
        b4.setBounds(142,206,172,174);
        b5.setBounds(314,206,200,174);
        b6.setBounds(514,206,172,174);
        b7.setBounds(142,387,172,174);
        b8.setBounds(314,387,200,174);
        b9.setBounds(514,387,172,174);
        e1.setBounds(144, 31, 275, 271);
        e2.setBounds(419, 31, 275, 271);
        e3.setBounds(144, 302, 275, 271);
        e4.setBounds(419, 302, 275, 271);

        add(e1);
        add(e2);
        add(e3);
        add(e4);
    }

    //設計道具按鈕
    private void setButtonB(JButton j){
        j.setFont(new Font("微軟正黑體",Font.BOLD,20));
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        j.setVerticalTextPosition(AbstractButton.BOTTOM);
        j.setHorizontalTextPosition(AbstractButton.CENTER);
        j.setForeground(new Color(0,0,128));
    }

    //設計裝備按鈕
    private void setButtonE(JButton j){
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        j.setVerticalTextPosition(AbstractButton.BOTTOM);
        j.setHorizontalTextPosition(AbstractButton.CENTER);
        j.setForeground(Color.PINK);
    }

    //當點擊裝備或道具時的反應
    private class MouseHandler implements MouseListener {
        public void mouseClicked(MouseEvent event) {
        }//用不到

        public void mouseReleased(MouseEvent event) {
        }//用不到

        public void mouseEntered(MouseEvent event) {
            if(event.getSource()==b1){
                b1.setText("售價為1,500");
            } else if (event.getSource() == b2) {
                b2.setText("售價為1,500");
            } else if (event.getSource() == b3) {
                b3.setText("售價為1,000");
            } else if (event.getSource() == b4) {
                b4.setText("售價為1,000");
            } else if (event.getSource() == b5) {
                b5.setText("售價為1,000");
            } else if (event.getSource() == b6) {
                b6.setText("售價為1,000");
            } else if (event.getSource() == b7) {
                b7.setText("售價為1,000");
            } else if (event.getSource() == b8) {
                b8.setText("售價為1,000");
            } else if (event.getSource() == b9) {
                b9.setText("售價為1,000");
            } else if (event.getSource() == e1&&house.getEquipment("竹蜻蜓")==0) {
                e1.setText("售價為3,000");
            } else if (event.getSource() == e2&&house.getEquipment("透視眼鏡")==0) {
                e2.setText("售價為3,000");
            } else if (event.getSource() == e3&&house.getEquipment("彈簧鞋")==0) {
                e3.setText("售價為3,000");
            } else if (event.getSource() == e4&&house.getEquipment("翅膀")==0) {
                e4.setText("售價為3,000");
            }
        }

        public void mouseExited(MouseEvent event) {
            if(event.getSource()==b1){
                b1.setText("經驗加倍券");
            } else if (event.getSource() == b2) {
                b2.setText("金錢加倍券");
            } else if (event.getSource() == b3) {
                b3.setText("電蚊拍");
            } else if (event.getSource() == b4) {
                b4.setText("增時卡");
            } else if (event.getSource() == b5) {
                b5.setText("地下道鑰匙");
            } else if (event.getSource() == b6) {
                b6.setText("警察卡");
            } else if (event.getSource() == b7) {
                b7.setText("老師卡");
            } else if (event.getSource() == b8) {
                b8.setText("加倍卡");
            } else if (event.getSource() == b9) {
                b9.setText("再看一次");
            } else if (event.getSource() == e1) {
                e1.setText("竹蜻蜓");
            } else if (event.getSource() == e2) {
                e2.setText("透視眼鏡");
            } else if (event.getSource() == e3) {
                e3.setText("彈簧鞋");
            } else if (event.getSource() == e4) {
                e4.setText("翅膀");
            }
        }

        //當遊玩者點下道具或裝備時的反應
        public void mousePressed(MouseEvent event) {//當滑鼠按下物件時
            if (event.getSource() == b1) {
                int opt= JOptionPane.showConfirmDialog(null, "遊戲結算時，獲得兩倍經驗\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                if (opt == 0) {
                    if (house.getHoldMoney() - 1500 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1500);
                        house.setItem("經驗加倍券", house.getItem("經驗加倍券") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b2) {
                int opt= JOptionPane.showConfirmDialog(null, "遊戲結算時，獲得兩倍金錢\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1500 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1500);
                        house.setItem("金錢加倍券", house.getItem("金錢加倍券") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b3) {
                int opt= JOptionPane.showConfirmDialog(null, "變身無敵狀態，效果維持十秒\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("電蚊拍", house.getItem("電蚊拍") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b4) {
                int opt= JOptionPane.showConfirmDialog(null, "增加遊戲時間10秒\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("增時卡", house.getItem("增時卡") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b5) {
                int opt= JOptionPane.showConfirmDialog(null, "起點向前移至分隔島\n-------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("地下道鑰匙", house.getItem("地下道鑰匙") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b6) {
                int opt= JOptionPane.showConfirmDialog(null, "被警察抓到時不需處罰\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("警察卡", house.getItem("警察卡") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b7) {
                int opt= JOptionPane.showConfirmDialog(null, "只會出現老師，效果維持10秒\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("老師卡", house.getItem("老師卡") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b8) {
                int opt= JOptionPane.showConfirmDialog(null, "分數增為兩倍，效果維持10秒\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 1000 >= 0) {
                        house.setHoldMoney(house.getHoldMoney() - 1000);
                        house.setItem("加倍卡", house.getItem("加倍卡") + 1);
                    } else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == b9) {
                int opt= JOptionPane.showConfirmDialog(null, "將所有牌翻至正面看10秒\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if(house.getHoldMoney()-1000>=0) {
                        house.setHoldMoney(house.getHoldMoney()-1000);
                        house.setItem("再看一次",house.getItem("再看一次")+1);
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == e1&&house.getEquipment("竹蜻蜓")==0) {
                int opt = JOptionPane.showConfirmDialog(null, "起點向前移動100公尺\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                if(opt == 0){
                    if(house.getHoldMoney()-3000>=0){
                        if(house.getEquipment("竹蜻蜓") !=0)
                            JOptionPane.showConfirmDialog(null, "已擁有此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else {
                            house.setHoldMoney(house.getHoldMoney()-3000);
                            house.setEquipment("竹蜻蜓");
                            e1.removeMouseListener(handler2);
                        }
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
                // 0=yes, 1=no
            } else if (event.getSource() == e2&&house.getEquipment("透視眼鏡")==0) {
                int opt = JOptionPane.showConfirmDialog(null, "可看到影子下的真實面貌\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 3000 >= 0) {
                        if (house.getEquipment("透視眼鏡") != 0)
                            JOptionPane.showConfirmDialog(null, "已擁有此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else {
                            house.setHoldMoney(house.getHoldMoney() - 3000);
                            house.setEquipment("透視眼鏡");
                            e2.removeMouseListener(handler2);
                        }
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == e3&&house.getEquipment("彈簧鞋")==0) {
                int opt= JOptionPane.showConfirmDialog(null, "可以一次前進兩格\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 3000 >= 0) {
                        if (house.getEquipment("彈簧鞋") != 0)
                            JOptionPane.showConfirmDialog(null, "已擁有此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else {
                            house.setHoldMoney(house.getHoldMoney() - 3000);
                            house.setEquipment("彈簧鞋");
                            e3.removeMouseListener(handler2);
                        }
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            } else if (event.getSource() == e4&&house.getEquipment("翅膀")==0) {
                int opt = JOptionPane.showConfirmDialog(null, "系統自動翻出一對配對組合\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if (opt == 0) {
                    if (house.getHoldMoney() - 3000 >= 0) {
                        if (house.getEquipment("翅膀") != 0)
                            JOptionPane.showConfirmDialog(null, "已擁有此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else {
                            house.setHoldMoney(house.getHoldMoney() - 3000);
                            house.setEquipment("翅膀");
                            e4.removeMouseListener(handler2);
                        }
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "擁有金額不足!!!", "", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
    }

    //判斷遊玩者切換為裝備或是道具
    private class ButtonHandler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == item) {
                repaint();
                IsBackGround=false;
                remove(e1);
                remove(e2);
                remove(e3);
                remove(e4);
                add(b1);
                add(b2);
                add(b3);
                add(b4);
                add(b5);
                add(b6);
                add(b7);
                add(b8);
                add(b9);
                /*bagTable.revalidate();*/
            }
            else if (event.getSource() == equip) {
                repaint();
                IsBackGround=true;
                add(e1);
                add(e2);
                add(e3);
                add(e4);
                remove(b1);
                remove(b2);
                remove(b3);
                remove(b4);
                remove(b5);
                remove(b6);
                remove(b7);
                remove(b8);
                remove(b9);

            }
        }
    }

    @Override//抓入圖檔 (ok)
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clears drawing area
        try {
            if(IsBackGround) {
                backGround = ImageIO.read(new File("data//Bag&Store/背包-裝備.png"));
                if(house.getEquipment("竹蜻蜓")==0)
                    iconE1 = new ImageIcon("data//Bag&Store/竹蜻蜓.png");
                else
                    iconE1 = new ImageIcon("data//Bag&Store/竹蜻蜓out.png");
                if(house.getEquipment("透視眼鏡")==0)
                    iconE2 = new ImageIcon("data//Bag&Store/透視眼鏡.png");
                else
                    iconE2 = new ImageIcon("data//Bag&Store/透視眼鏡out.png");
                if(house.getEquipment("彈簧鞋")==0)
                    iconE3 = new ImageIcon("data//Bag&Store/彈簧鞋.png");
                else
                    iconE3 = new ImageIcon("data//Bag&Store/彈簧鞋out.png");
                if(house.getEquipment("翅膀")==0)
                    iconE4 = new ImageIcon("data//Bag&Store/翅膀.png");
                else
                    iconE4 = new ImageIcon("data//Bag&Store/翅膀out.png");
                e1.setIcon(iconE1);
                e2.setIcon(iconE2);
                e3.setIcon(iconE3);
                e4.setIcon(iconE4);

            }
            else {
                backGround = ImageIO.read(new File("data//Bag&Store/背包-道具.png"));
                iconB1 = new ImageIcon("data//Bag&Store/exp.png");
                iconB2 = new ImageIcon("data//Bag&Store/錢加倍.jpg");
                iconB3 = new ImageIcon("data//Bag&Store/電蚊拍.png");
                iconB4 = new ImageIcon("data//Bag&Store/增時卡.png");
                iconB5 = new ImageIcon("data//Bag&Store/地下道鑰匙.png");
                iconB6 = new ImageIcon("data//Bag&Store/警察卡.png");
                iconB7 = new ImageIcon("data//Bag&Store/老師卡.png");
                iconB8 = new ImageIcon("data//Bag&Store/加倍卡.png");
                iconB9 = new ImageIcon("data//Bag&Store/再看一次.png");
                b1.setIcon(iconB1);
                b2.setIcon(iconB2);
                b3.setIcon(iconB3);
                b4.setIcon(iconB4);
                b5.setIcon(iconB5);
                b6.setIcon(iconB6);
                b7.setIcon(iconB7);
                b8.setIcon(iconB8);
                b9.setIcon(iconB9);

            }
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }
        g.drawImage(backGround, 0, 0, null);
    }
}