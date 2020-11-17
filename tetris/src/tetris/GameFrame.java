package tetris;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
 
public class GameFrame extends JFrame implements ActionListener{
 private int widthFrame = 450;
 private int heightFrame = 450;
 private JMenu menuone = new JMenu("�C��");//�إߤ@�ӿ��
 private JMenuItem newGame = menuone.add("���s�}�l");//�إߤ@�Ӥ��ؿ��ﶵ
 private JMenuItem exitGame = menuone.add("�C���h�X");
 private JMenuItem stopGame = menuone.add("�C���Ȱ�");
 private JMenuItem goOnGame = menuone.add("�C���~��");
 
 private JMenu menutwo = new JMenu("���U");//�إ߲ĤG�ӿ��
 private JMenuItem aboutGame = menutwo.add("����C��");
 GamePanel gamepanel = new GamePanel();
 
 public GameFrame()//�غc�禡
 {
 addKeyListener(gamepanel);
 newGame.addActionListener(this);
 exitGame.addActionListener(this);
 stopGame.addActionListener(this);
 goOnGame.addActionListener(this);
 aboutGame.addActionListener(this);
 
 this.add(gamepanel);
 
 JMenuBar menu = new JMenuBar();
 menu.add(menuone);
 menu.add(menutwo);
 this.setJMenuBar(menu);
 
 this.setTitle("Tetris");
 this.setBounds(50, 10, widthFrame, heightFrame);
 this.setVisible(true);
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 }
 
 public void actionPerformed(ActionEvent e)
 {
 if(e.getSource() == newGame)//�C�����s�}�l
 {
  gamepanel.NewGame();
 }
 if(e.getSource() == exitGame)//�C���h�X
 {
  System.exit(0);
 }
 if(e.getSource() == stopGame)//�C���Ȱ�
 {
  gamepanel.StopGame();
 }
 if(e.getSource() == goOnGame)//�C���~��
 {
  gamepanel.ContinueGame();
 }
 if(e.getSource() == aboutGame)//����C����T
 {
  JOptionPane.showMessageDialog(null, "���k�䲾�ʡA�V�W�����A�V�U��[�t����", "����", JOptionPane.OK_OPTION);
 }
 }
 
 
 public static void main(String[] args) {
 new GameFrame();
 }
}
