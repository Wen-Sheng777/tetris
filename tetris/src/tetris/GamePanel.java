package tetris;
 
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
 
public class GamePanel extends JPanel implements KeyListener{
 private int mapRow = 21;
 private int mapCol = 12;
 private int mapGame[][] = new int[mapRow][mapCol];//�}�P�@�ӤG���}�C�Ŷ��A�ΨӦs��ڭ̪��a�ϸ�T
 
 private Timer timer;
 private int score = 0;//�O�����Z
 Random random = new Random();
 private int curShapeType = -1;
 private int curShapeState = -1;//�]�w��e���Ϊ����O�M��e���Ϊ����A
 private int nextShapeType = -1;
 private int nextShapeState = -1;//�]�w�U�@���X�{������ժ����O�M���A
 
 private int posx = 0;
 private int posy = 0;
 
 private final int shapes[][][] = new int[][][]{
  //T�r�Ϋ��f�ɰw�������x�s
  {
  {0,1,0,0, 
   1,1,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0, 
   1,1,0,0, 
   0,1,0,0,
   0,0,0,0},
  {1,1,1,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0,
   0,1,1,0, 
   0,1,0,0,
   0,0,0,0}
  },
  //I�r�Ϋ��f�ɰw�������x�s
  {
  {0,0,0,0, 
   1,1,1,1, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0, 
   0,1,0,0, 
   0,1,0,0, 
   0,1,0,0},
  {0,0,0,0, 
   1,1,1,1, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0, 
   0,1,0,0, 
   0,1,0,0, 
   0,1,0,0}
  },
  //��Z�Ϋ��f�ɰw�������x�s
  {
  {0,1,1,0, 
   1,1,0,0,
   0,0,0,0, 
   0,0,0,0},
  {1,0,0,0, 
   1,1,0,0, 
   0,1,0,0, 
   0,0,0,0},
  {0,1,1,0, 
   1,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,0,0,0, 
   1,1,0,0, 
   0,1,0,0, 
   0,0,0,0}
  },
  //Z�Ϋ��f�ɰw�������x�s
  {
  {1,1,0,0, 
   0,1,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0, 
   1,1,0,0,
   1,0,0,0, 
   0,0,0,0},
  {1,1,0,0, 
   0,1,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0, 
   1,1,0,0, 
   1,0,0,0, 
   0,0,0,0}
  },
  //J�r�Ϋ��f�ɰw�������x�s
  {
  {0,1,0,0, 
   0,1,0,0,
   1,1,0,0, 
   0,0,0,0},
  {1,1,1,0,
   0,0,1,0, 
   0,0,0,0,
   0,0,0,0},
  {1,1,0,0, 
   1,0,0,0, 
   1,0,0,0,
   0,0,0,0},
  {1,0,0,0, 
   1,1,1,0,
   0,0,0,0,
   0,0,0,0}
  },
  //L�r�Ϋ��f�ɰw�������x�s
  {
  {1,0,0,0, 
   1,0,0,0, 
   1,1,0,0, 
   0,0,0,0},
  {0,0,1,0, 
   1,1,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   0,1,0,0, 
   0,1,0,0, 
   0,0,0,0},
  {1,1,1,0, 
   1,0,0,0,
   0,0,0,0, 
   0,0,0,0}
  },
  //�Цr�Ϋ��f�ɰw�������x�s
  {
  {1,1,0,0, 
   1,1,0,0, 
   0,0,0,0,
   0,0,0,0},
  {1,1,0,0, 
   1,1,0,0,
   0,0,0,0,
   0,0,0,0},
  {1,1,0,0, 
   1,1,0,0,
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   1,1,0,0,
   0,0,0,0,
   0,0,0,0}
  },
  //�Q�Ϋ��f�ɰw�������x�s?
  {
  {0,1,0,0,
   1,1,1,0, 
   0,1,0,0, 
   0,0,0,0},
  {0,1,0,0,
   1,1,1,0, 
   0,1,0,0, 
   0,0,0,0},
  {0,1,0,0,
   1,1,1,0, 
   0,1,0,0, 
   0,0,0,0},
  {0,1,0,0,
   1,1,1,0, 
   0,1,0,0, 
   0,0,0,0},
   },
  //o�Ϋ��f�ɰw�������x�s?
  {
  {0,0,0,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,0,0,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,0,0,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,0,0,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
   },
  //�W�Ϋ��f�ɰw�������x�s
  {
  {1,0,1,0,
   1,1,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   0,1,0,0, 
   1,1,0,0, 
   0,0,0,0},
  {1,1,1,0,
   1,0,1,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   1,0,0,0, 
   1,1,0,0, 
   0,0,0,0},
   },
  //���Ϋ��f�ɰw�������x�s
  {
  {1,0,0,0,
   1,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {0,1,0,0,
   1,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   0,1,0,0, 
   0,0,0,0, 
   0,0,0,0},
  {1,1,0,0,
   1,0,0,0, 
   0,0,0,0, 
   0,0,0,0},
   }
 };
 private int rowRect = 4;
 private int colRect = 4;//�o�̧ڭ̧��x�s���v�H�ݦ��O�@��4*4���G���}�C�A���M�b�W���ڭ̱ĥΤ@���}�C���x�s�A������٬O�n�ݦ��G���}�C�ӹ�{
 private int RectWidth = 10;
 
 public GamePanel()//�غc�禡----�إߦn�a��
 {
 CreateRect();
 initMap();//��l�Ƴo�Ӧa��
 SetWall();//�]�w��
// CreateRect();
 timer = new Timer(500,new TimerListener());
 timer.start();
 }
 
 class TimerListener implements ActionListener{
 public void actionPerformed(ActionEvent e)
 {
  MoveDown();
 }
 }
 
 public void SetWall()//��0�C�M��11�C���O��A��20��]�O��
 {
 for(int i = 0; i < mapRow; i++)//���e�C
 {
  mapGame[i][0] = 2;
  mapGame[i][11] = 2;
 }
 for(int j = 1; j < mapCol-1; j++)//�e�̫�@��
 {
  mapGame[20][j] = 2;
 }
 }
 
 public void initMap()//��l�Ƴo�Ӧa�ϡA��ID�O2�A�Ů檺ID�O0�A�����ID�O1
 {
 for(int i = 0; i < mapRow; i++)
 {
  for(int j = 0; j < mapCol; j++)
  {
  mapGame[i][j] = 0;
  }
 }
 }
 
 public void CreateRect()//�إߤ��---�p�G��e��������O�M���A���s�b�N�]�w�U�@�����A�p�G���s�b�N�]�w��e���åB�]�w�U�@�������A�M���O
 {
 if(curShapeType == -1 && curShapeState == -1)//��e��������A����1�A��ܹC���~�}�l
 {
  curShapeType = random.nextInt(shapes.length);
  curShapeState = random.nextInt(shapes[0].length);
 }
 else
 {
  curShapeType = nextShapeType;
  curShapeState = nextShapeState;
 }
 nextShapeType = random.nextInt(shapes.length);
 nextShapeState = random.nextInt(shapes[0].length);
 posx = 0;
 posy = 1;//�𪺥��W���إߤ��
 if(GameOver(posx,posy,curShapeType,curShapeState))
 {
  JOptionPane.showConfirmDialog(null, "�C�������I", "����", JOptionPane.OK_OPTION);
  System.exit(0);
 }
 }
 
 
 public boolean GameOver(int x, int y, int ShapeType, int ShapeState)//�P�_�C���O�_����
 {
 if(IsOrNoMove(x,y,ShapeType,ShapeState))
 {
  return false;
 }
 return true;
 }
 
 public boolean IsOrNoMove(int x, int y, int ShapeType, int ShapeState)//�P�_��e���o�ӹϧάO�_�i�H����,�o�̭��I�j��x,y���y�ЬO��4*4���G���}�C�]�y�z�ϧΪ����Ӱ}�C�^�����W���ؼ�
 {
 for(int i = 0; i < rowRect ; i++)
 {
  for(int j = 0; j < colRect; j++)
  {
  if(shapes[ShapeType][ShapeState][i*colRect+j] == 1 && mapGame[x+i][y+j] == 1
  || shapes[ShapeType][ShapeState][i*colRect+j] == 1 && mapGame[x+i][y+j] == 2)
  {
   return false;
  }
  }
 }
 return true;
 }
 
 
 public void Turn()//����
 {
 int temp = curShapeState;
 curShapeState = (curShapeState+1) % shapes[0].length;
 if(IsOrNoMove(posx,posy,curShapeType,curShapeState))
 {
 }
 else
 {
  curShapeState = temp;
 }
 repaint();
 }
 
 public void MoveDown()//�V�U����
 {
 if(IsOrNoMove(posx+1,posy,curShapeType,curShapeState))
 {
  posx++;
 }
 else
 {
  AddToMap();//�N����T�w�b�a�Ϥ�
  CheckLine();
  CreateRect();//���s�إߤ@�ӷs�����
 }
 repaint();
 }
 
 public void MoveLeft()//�V������
 {
 if(IsOrNoMove(posx,posy-1,curShapeType,curShapeState))
 {
  posy--;
 }
 repaint();
 }
 
 public void MoveRight()//�V�k����
 {
 if(IsOrNoMove(posx,posy+1,curShapeType,curShapeState))
 {
  posy++;
 }
 repaint();
 }
 
 public void AddToMap()//�T�w���U�Ӫ��o�@�v�H��a�Ϥ�
 {
 for(int i = 0; i < rowRect; i++)
 {
  for(int j = 0; j < colRect; j++)
  {
  if(shapes[curShapeType][curShapeState][i*colRect+j] == 1)
  {
   mapGame[posx+i][posy+j] = shapes[curShapeType][curShapeState][i*colRect+j];
  }
  }
 }
 }
 
 public void CheckLine()//�ˬd�@�U�o�Ǧ椤�O�_�����檺
 {
 int count = 0;
 for(int i = mapRow-2; i >= 0; i--)
 {
  count = 0;
  for(int j = 1; j < mapCol-1; j++)
  {
  if(mapGame[i][j] == 1)
  {
   count++;
  }
  else
   break;
  }
  if(count >= mapCol-2)
  {
  for(int k = i; k > 0; k--)
  {
   for(int p = 1; p < mapCol-1; p++)
   {
   mapGame[k][p] = mapGame[k-1][p];
   }
  }
  score += 10;
  i++;
  }
 }
 }
 
 public void paint(Graphics g)//���sø�s����
 {
 super.paint(g);
 for(int i = 0; i < rowRect; i++)//ø�s���b�U�������
 {
  for(int j = 0; j < colRect; j++)
  {
  if(shapes[curShapeType][curShapeState][i*colRect+j] == 1)
  {
   g.fillRect((posy+j+1)*RectWidth, (posx+i+1)*RectWidth, RectWidth, RectWidth);
  }
  }
 }
 for(int i = 0; i < mapRow; i++)//ø�s�a�ϤW���w�g�T�w�n�������T
 {
  for(int j = 0; j < mapCol; j++)
  {
  if(mapGame[i][j] == 2)//�e��
  {
   g.drawRect((j+1)*RectWidth, (i+1)*RectWidth, RectWidth, RectWidth);
  }
  if(mapGame[i][j] == 1)//�e�p���
  {
   g.fillRect((j+1)*RectWidth, (i+1)*RectWidth, RectWidth, RectWidth);
  }
  }
 }
 g.drawString("score = "+ score, 225, 15);
 g.drawString("�U�@�Ӥ���G", 255, 50);
 for(int i = 0; i < rowRect; i++)
 {
  for(int j = 0; j < colRect; j++)
  {
  if(shapes[nextShapeType][nextShapeState][i*colRect+j] == 1)
  {
   g.fillRect(225+(j*RectWidth), 100+(i*RectWidth), RectWidth, RectWidth);
  }
  }
 }
 }
 
 public void NewGame()//�C�����s�}�l
 {
 score = 0;
 initMap();
 SetWall();
 CreateRect();
 repaint();
 }
 
 public void StopGame()//�C���Ȱ�
 {
 timer.stop();
 }
 
 public void ContinueGame()
 {
 timer.start();
 }
 
 @Override
 public void keyTyped(KeyEvent e) {
 
 }
 
 @Override
 public void keyPressed(KeyEvent e) {
 switch(e.getKeyCode())
 {
 case KeyEvent.VK_UP://�W----����
  Turn();
  break;
 case KeyEvent.VK_DOWN://�U----�V�U����
  MoveDown();
  break;
 case KeyEvent.VK_LEFT://��----�V������
  MoveLeft();
  break;
 case KeyEvent.VK_RIGHT://�k----�V�k����
  MoveRight();
  break;
 }
 
 }
 
 @Override
 public void keyReleased(KeyEvent e) {
 // TODO Auto-generated method stub
 
 }
 
}