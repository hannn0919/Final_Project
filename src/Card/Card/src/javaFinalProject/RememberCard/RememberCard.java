package javaFinalProject.RememberCard;

import java.awt.BorderLayout;
import java.awt.Component;
//import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

//import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * @author jqs �D�n��{�O��½�P�\��
 */
public class RememberCard extends JFrame {
	
	/**
          * ��l�ƹC������C�ơA��C�Ʀ��Z����������
   */
    private static final int ROWS = 4;
    private static final int COLUMNS = 5;
    private static final long serialVersionUID = -8908268719780973221L;
    private JTextField txt_Time;
    private boolean isRunning = false;
    /**
           * �s��Ϥ����ؿ��A²��_���A�s��Ϥ����ؿ����Ϥ��ӼƬ���l�ƪ���C�ƭ��n���@�b
    */
    private String picDir = "C:\\Users\\user\\Desktop\\Card\\pics";
    private String[] picture;//�Ӥ�����
    protected boolean isStart;
    private PicPanel preOne = null;
    /**
           * �Ω�Хܤw��쪺���
    */
    private int count;
    private JPanel panel_Pic;

    public RememberCard() {
        setTitle("�ۦP�d�P");

        JPanel panel_Time = new JPanel();//�s�����O
        getContentPane().add(panel_Time, BorderLayout.NORTH);//��b�W�� panel time

        JLabel lbl_Time = new JLabel("�w�g�L");
        panel_Time.add(lbl_Time);//��b�W�� panel time

        txt_Time = new JTextField();
        txt_Time.setEditable(false);
        panel_Time.add(txt_Time);
        txt_Time.setColumns(10);////����خ�

        JLabel lbl_Unit = new JLabel("��");
        panel_Time.add(lbl_Unit);

        JButton btn_Start = new JButton("�}�l�C��");
        panel_Time.add(btn_Start);
        
        JButton btn_Item = new JButton("�D��");
        panel_Time.add(btn_Item);
        /////////////////////////////////////

        panel_Pic = new JPanel();
        getContentPane().add(panel_Pic, BorderLayout.CENTER);///�Ӥ�����m
        
        btn_Item.addActionListener(new ActionListener() {//�D�㪺���sclick
        	public void actionPerformed(ActionEvent e) {//���U�D���  �خت��ɶ��٦b�] �o��
        		openCard();//½�}�������d�T��
        	}
            
        });
        
        btn_Start.addActionListener(new ActionListener() {//�}�l�C�������sclick
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();
                
            }
            
        });
        //initPicPanels();//��l�ƹϤ����O
    }

    /**
           * ��l�ƹϤ����O
    */
    private void initPicPanels() {
        panel_Pic.setLayout(new GridLayout(ROWS, COLUMNS, 10, 10));//10�O����
        initPictureIndex();//��l�ƹϤ������ިý�ȨC�ӹϤ������|
        
        for (int i = 0; i < ROWS * COLUMNS; i++) {
        	PicPanel panel_1 = new PicPanel(this, picture[i]);
            panel_Pic.add(panel_1);
            //ImageIO.read(new File();
        }
        try {
			Thread.sleep(3000);//����T��\�P
		} 
        catch (InterruptedException e) {
			e.printStackTrace();
		}
        for (int i = 0; i < panel_Pic.getComponentCount(); i++) {
        	Component comp = panel_Pic.getComponent(i);
        	if(comp instanceof PicPanel){
        		PicPanel panel_1 = (PicPanel)comp;
        		panel_1.setLabelPicNull();
			}
        }
    }

    /**
          * �}�l�C��
    */
    //static int hi=0;
    protected void startGame() {
        new Thread() {//�����  �i��n�ﱼ  ���M��ť�W�|�ܺC
        	 ////@Override 
        	//Thread.sleep(500);
        	
        	public void run() {
        		initPicPanels();
            	//��ɶ����a��
            	long startTime = System.currentTimeMillis();//��^��e�ɶ�(�@��)�C��^�Ȫ��ɶ����O1�@��
                while ((System.currentTimeMillis() - startTime) / 1000 <= 30) {//30s�����Υ����䧹
                    txt_Time.setText(((System.currentTimeMillis() - startTime) / 1000)  + "");//��ƶ]���t��
                    if(count > ROWS * COLUMNS / 2) {
                    	break;
                    }
                }
                //////////
                JOptionPane.showMessageDialog(null, "�@��X" + count + "��");//�@��X�X��  �n�O�o��
                // �����᭫�s��l�Ƥ@�U���O�H�K��U�@��������
                
                count = 0;
                panel_Pic.removeAll();
                
                txt_Time.setText(null);
                panel_Pic.validate();
                isRunning = false;
                /////////////////��l�Ƨ���
            }
        }.start();
    }

    /**
           * ��l�ƹϤ������ިý�ȨC�ӹϤ������|
    */
    private void initPictureIndex() {
        picture = new String[ROWS * COLUMNS];////�̭���Ϥ������|

        // �o�̨S���˴��Ϥ��ؿ����ɮת����ĩʡA�ݭn�O�ҳ��O�Ϥ����O�C
        File file = new File(picDir);
        File[] pics = file.listFiles();

        // ��l�Ƥ@��ROWS*COLUMNS��int�}�C�A�̭��s��C�ӹϤ�������
        int[] indexs = getIndexs(picture.length, pics.length);
        for (int i = 0; i < indexs.length; i++) {
            picture[i] = pics[indexs[i]].getAbsolutePath();
            //Image image = ImageIO.read(new File(picPath));
        }
    }

    /**
              * �ھڴ��Ѫ��Ϥ��`�ƥء]���]�Ϥ����O�����ۦP���^�o��@�Ӫ��׬�sum���}�C�ΨӪ�ܨC�ӹϤ�������
     * 
     * @param sum
              *            �C������C�ƭ��n
     * @param picNums
              *            ���w�ؿ��U�Ϥ����`�ƥ�
     * @return
     */
    private int[] getIndexs(int sum, int picNums) {
        int half = sum / 2;

        int[] tmpResult = new int[sum];
        Random random = new Random(System.currentTimeMillis());
        int temp = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (list.size() != half) {
            temp = random.nextInt(picNums);
            if (!list.contains(temp)) {
                list.add(temp);
            }
            else if (picNums < half) {
                list.add(temp);
            }
        }

        for (int i = 0; i < tmpResult.length; i++) {
            tmpResult[i] = list.get(i >= half ? i % half : i);
        }
        // �N���ǥ��áA�_�h�|�X�{�e�b�����M��b�����O�������}�����p
        LinkedList<Integer> _result = new LinkedList<Integer>();
        while (_result.size() != sum) {
            temp = random.nextInt(sum);
            if (!_result.contains(temp)) {
                _result.add(temp);
            }
        }
        int[] result = new int[sum];
        for (int i = 0; i < result.length; i++) {
            result[i] = tmpResult[_result.get(i)];
        }
        return result;
    }
    
    private void openCard() {//�@�}�l��item
    	long startTime = System.currentTimeMillis();//��^��e�ɶ�(�@��)�C��^�Ȫ��ɶ����O1�@��
        while ((System.currentTimeMillis() - startTime) / 1000 <= 3) {//3s
        	//setTitle("����");
        	for (int i = 0; i < ROWS * COLUMNS; i++) {
                //picture[i] = pics[indexs[i]].getAbsolutePath();
            }
        
        }
    }
    
    public void setallPic() {
    	
    }
    public PicPanel getPreOne() {
        return preOne;
    }

    public void setPreOne(PicPanel preOne) {
        this.preOne = preOne;
    }

    public void addCount() {
        count++;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
