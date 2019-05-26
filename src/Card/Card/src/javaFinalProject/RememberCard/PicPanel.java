package javaFinalProject.RememberCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * @author jqs
 * 
   *         �Ϥ����O�A�D�n��{�F�Ϥ�����ܻP�Ϥ��ۦP�P�_
 */
public class PicPanel extends JPanel {
	private static final long serialVersionUID = 2172162568449349737L;
    private String picPath;
    private JLabel lbl_Pic = new JLabel();
    private ImageIcon bgIcon = null;
    private boolean isShow = false;
    private RememberCard parent;
    private boolean finished = false;

    public PicPanel(RememberCard rememberCard, String picPath) {
        this.picPath = picPath;
        this.parent = rememberCard;
        this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 2)));
        this.setLayout(new BorderLayout());
        this.add(lbl_Pic, BorderLayout.CENTER);
        this.addMouseListener(mouseAdapter);
        initLabelImage();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
           * �Ϥ����O���ƹ��ƥ��ť�A�t��L�{�b������
     */
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        
        public void mouseClicked(MouseEvent e) {
        	Thread t1 = new Thread();
            new Thread() {
                public void run() {
                    if (!parent.isRunning() || finished) {
                        return;
                    }
                    isShow = !isShow;//true  �I�쪺����
                    if (isShow) {
                        if (bgIcon == null) {//�I��S½�}��
                            initLabelImage();
                        }
                        PicPanel curOne = (PicPanel) lbl_Pic.getParent();//��2��
                        PicPanel preOne = parent.getPreOne();//��1��
                        if (preOne == null) {
                            parent.setPreOne(curOne);
                        } 
                        else {
                            boolean right = checkRight(curOne, preOne);
                            if (right) {//�t�令�\
                                parent.setPreOne(null);
                                curOne.setFinished(true);
                                preOne.setFinished(true);
                                parent.addCount();
                            } 
                            else {
                                lbl_Pic.setIcon(bgIcon);
                                repaint();
                                try {
                                    Thread.sleep(500);//0.5s��\���d��
                                } 
                                catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                lbl_Pic.setIcon(null);
                                isShow = !isShow;
                                repaint();
                                preOne.getMouseListeners()[0].mouseClicked(null);
                                parent.setPreOne(null);
                                return;
                            }
                        }
                        lbl_Pic.setIcon(bgIcon);
                    }
                    else {
                        lbl_Pic.setIcon(null);
                    }
                    repaint();
                };
                
            }.start();
            //Thread.interrupt();
            
           
        }

        /**
                        * �ˬd��ӭ��O��ܪ��Ϥ��O�_�@�P�A�ھڹϤ������|�ӧP�_�A�P�ɭn�O�Ҩ�ӭ��O���O�P�@�ӭ��O
         * 
         * @param curOne
         * @param preOne
         * @return
         */
        private boolean checkRight(PicPanel curOne, PicPanel preOne) {
            return curOne.getPicPath().equals(preOne.getPicPath()) && !curOne.equals(preOne);
        }
        
       
    };

    /**
              * ��l��Label����image
     */
    private void initLabelImage() {
        try {
            Image image = ImageIO.read(new File(picPath));//��ܹϤ�
            if (image != null) {
                //int lblWidth = this.getWidth();
                //int lblHeight = this.getHeight();
            	int lblWidth = 280;//�Ϥ������e
            	int lblHeight = 188;
                //System.out.println("PicPanel initLabelImage " + lblWidth + " " + lblHeight);
                bgIcon = new ImageIcon(image.getScaledInstance(lblWidth, lblHeight, Image.SCALE_DEFAULT));
                lbl_Pic.setIcon(bgIcon);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
              * ����t�諸�Ϥ����O��]�m�������A��true�A�����I��Ϥ����O�w�g�L�ĤF�C
     * 
     * @param b
     */
    protected void setFinished(boolean b) {
        finished = b;
    }

	public void setLabelPicNull() {
		lbl_Pic.setIcon(null);
	}
}
