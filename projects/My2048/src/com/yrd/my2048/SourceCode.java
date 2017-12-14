/*

Block.java


import javax.swing.*;
import java.awt.*;
public class Block extends JLabel 
{
  private int value;
  public Block() 
  {
    value = 0;//初始化值为0
    setFont(new Font("font", Font.PLAIN, 40));//设定字体
    setBackground(Color.gray);//设定初始颜色为灰色
  }
  
  public int getValue()//获取值
  {
    return value;
  }
  
  public void setValue(int value)
  {
    this.value = value;
    String text = String.valueOf(value);
    if (value != 0)
      setText(text);
    else
      setText("");//如果值为0则不显示
    setColor();
  }
  
  public void setColor() //根据值的不同设定不同的背景颜色、label字体
  {
    switch (value) 
      {
    case 0:
      setBackground(Color.gray);
      break;
    case 2:
      setBackground(new Color(238, 228, 218));
      break;
    case 4:
      setBackground(new Color(238, 224, 198));
      break;
    case 8:
      setBackground(new Color(243, 177, 116));
      break;
    case 16:
      setBackground(new Color(243, 177, 116));
      break;
    case 32:
      setBackground(new Color(248, 149, 90));
      break;
    case 64:
      setBackground(new Color(249, 94, 50));
      break;
    case 128:
      setBackground(new Color(239, 207, 108));
      break;
    case 256:
      setBackground(new Color(239, 207, 99));
      break;
    case 512:
      setBackground(new Color(239, 203, 82));
      break;
    case 1024:
      setBackground(new Color(239, 199, 57));
      break;
    case 2048:
      setBackground(new Color(239, 195, 41));
      break;
    case 4096:
      setBackground(new Color(255, 60, 57));
      break;
      }
  }
}
 

My2048.java


 
import java.awt.*;
import javax.swing.*;
public class My2048 extends JFrame 
{ 
  public My2048()//构造函数 
  {
    setTitle("2048");//设置标题
    setSize(400, 400);//设定窗口大小
    setLocation(500, 200);//设定窗口起始位置
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(4, 4, 5, 5));//设定布局方式为GridLayout型
    new Operation(this);
    this.setVisible(true);//设为可视
  }
  
  public static void main(String args[]) //程序入口点
  {
    try
    {
      UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel");//设定UI
    } //接受抛出的异常
    catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e)
    {
      e.printStackTrace();
    }
    JFrame.setDefaultLookAndFeelDecorated(true);//设定Frame的缺省外观
    new My2048();
  }
  
}
 

Operation.java


import java.awt.event.*;
import javax.swing.*;
public class Operation implements KeyListener
{
  Block[] block;//用于储存16个数据
  JPanel panel;
  public boolean up,down,left,right;
  int moveFlag;//用于累计移动的次数
  boolean numFlag;//用于判断是否还能加入新的数字
  public Operation(JFrame frame) 
  {
    this.panel = (JPanel)frame.getContentPane();//构造出panel
    block = new Block[16];//构造出长度为16的数组
    numFlag = true;//初始化
    moveFlag = 0;
    up=true;down=true;left=true;right=true;
    addBlock();
    for (int i = 0; i < 2; i++)
      appearBlock();
    frame.addKeyListener(this);
  }
  
  private void addBlock() 
  {
    for (int i = 0; i < 16; i++) //往panel里加入block
    {
      block[i] = new Block();
      block[i].setHorizontalAlignment(JLabel.CENTER);// 不透明的标签
      block[i].setOpaque(true);
      panel.add(block[i]);  
    }
  } 
  public void appearBlock() 
  {
    while (numFlag) //当还能加入随机的一个新的值得时候
    {
      int index = (int) (Math.random() * 16);//取一个0到15的随机整数，这个数作为随机加入盘中的2或4的位置
      if (block[index].getValue() == 0)//如果这个数所在的block数组中值为0，即在为空的时候，加入一个2或4的数字
      {
        if (Math.random() < 0.5)
        {
          block[index].setValue(2);
        }
        else
        {
          block[index].setValue(4);
        }
        break;//跳出while
      }
    }
  }
  
  public void judgeAppear() //统计block数组中是否含有值为0的元素，若没有，则numFlag变为false
  {
    int sum = 0;
    for (int i = 0; i < 16; i++) 
    {
      if (block[i].getValue() != 0)
      {
        sum++;
      }
    }
    if (sum == 16)
      numFlag = false;
  
  }
  
  public int Find(int i,int j,int a,int b)
  {
    while(i<b&&i>=a)
    {
       if(block[i].getValue()!=0)
       {
        return i;
       }
       i=i+j;
    }
    return -1;
  }
  public void upBlock()
  {
    int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
    for(i=0;i<4;i++)
    {
      index=i;
      for(j=i+4;j<16;j+=4)
      {  
        valueJ=0; valueI=0;
        if(block[index].getValue()==0)
        {
          t=Find(index,4,0,16);
          if(t!=-1)
          {
            block[index].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueI=block[index].getValue();
        if(block[j].getValue()==0)
        {
          t=Find(j,4,0,16);
          if(t!=-1)
          {
            block[j].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueJ=block[j].getValue();
        if(valueI==valueJ&&valueI!=0&&valueJ!=0)
        {
          block[index].setValue(valueI+valueJ);
          block[j].setValue(0);
          numFlag = true;
        }
        index=j;
      }
        
    }
  }
  public void downBlock() {
  
    int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
    for(i=12;i<16;i++)
    {
      index=i;
      for(j=i-4;j>=0;j-=4)
      {  
        valueJ=0; valueI=0;
        if(block[index].getValue()==0)
        {
          t=Find(index,-4,0,16);
          if(t!=-1)
          {
            block[index].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueI=block[index].getValue();
        if(block[j].getValue()==0)
        {
          t=Find(j,-4,0,16);
          if(t!=-1)
          {
            block[j].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueJ=block[j].getValue();
        if(valueI==valueJ&&valueI!=0&&valueJ!=0)
        {
          block[index].setValue(valueI+valueJ);
          block[j].setValue(0);
          numFlag = true;
        }
        index=j;
      }
        
    }
  }
  public void rightBlock() 
  {
    int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
    for(i=3;i<16;i+=4)
    {
      index=i;
      for(j=i-1;j>i-4;j--)
      {  
        valueJ=0; valueI=0;
        if(block[index].getValue()==0)
        {
          t=Find(index,-1,i-3,index+1);
          if(t!=-1)
          {
            block[index].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueI=block[index].getValue();
        if(block[j].getValue()==0)
        {
          t=Find(j,-1,i-3,j+1);
          if(t!=-1)
          {
            block[j].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueJ=block[j].getValue();
        if(valueI==valueJ&&valueI!=0&&valueJ!=0)
        {
          block[index].setValue(valueI+valueJ);
          block[j].setValue(0);
          numFlag = true;
        }
        index=j;
      }
        
    }
  }
  public void leftBlock() 
  {
    int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
    for(i=0;i<16;i+=4)
    {
      index=i;
      for(j=i+1;j<i+4;j++)
      {  
        valueJ=0; valueI=0;
        if(block[index].getValue()==0)
        {
          t=Find(index,1,index,i+4);
          if(t!=-1)
          {
            block[index].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueI=block[index].getValue();
        if(block[j].getValue()==0)
        {
          t=Find(j,1,j,i+4);
          if(t!=-1)
          {
            block[j].setValue(block[t].getValue());
            block[t].setValue(0);
          }
          else
          {
            break;
          }
        }
        valueJ=block[j].getValue();
        if(valueI==valueJ&&valueI!=0&&valueJ!=0)
        {
          block[index].setValue(valueI+valueJ);
          block[j].setValue(0);
          numFlag = true;
        }
        index=j;
      }
        
    }
  }
  public void over() 
  {
    if (numFlag ==false&& up==false&&down==false&&left==false&&right==false) //当不能添加元素，并且不可移动的步数超过36就输了，输了的时候在盘中央显示GAMEOVER
    {
      block[4].setText("G");
      block[5].setText("A");
      block[6].setText("M");
      block[7].setText("E");
      block[8].setText("O");
      block[9].setText("V");
      block[10].setText("E");
      block[11].setText("R"); 
      block[11].addMouseListener(new MouseAdapter() {public void mousePressed(MouseEvent e){reStart();}});
    }
  }
    
  public void win() //同OVER
  { 
    block[0].setText("Y");
    block[1].setText("O");
    block[2].setText("U");
    block[13].setText("W");
    block[14].setText("I");
    block[15].setText("N");
    block[15].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        reStart();
      }
    });
  }
  public void reStart()//重启游戏，和构造函数类似，不在累述
  {
    numFlag=true;
    moveFlag=0;
    up=true;down=true;left=true;right=true;
    for(int i=0;i<16;i++)
      block[i].setValue(0);
    for (int i = 0; i < 2; i++)
      appearBlock();
  }
  public void keyPressed(KeyEvent e) //判断按的上下左右键，并依次调用移动函数、判断函数、添加函数、判断是否输掉的函数
  {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
      if(up){
      upBlock();}
      judgeAppear();
      appearBlock();
      over();
        
      if(numFlag==false)
      {
        up=false;
      }
      else
      {
        up=true;down=true;left=true;right=true;
      }
      break;
    case KeyEvent.VK_DOWN:
      if(down){
      downBlock();}
      judgeAppear();
      appearBlock();
      over();
      if(numFlag==false)
      {
        down=false;
      }
      else
      {
        up=true;down=true;left=true;right=true;
      }
      break;
    case KeyEvent.VK_LEFT:
      if(left){
      leftBlock();}
      judgeAppear();
      appearBlock();
      over();
        
      if(numFlag==false)
      {
        left=false;
      }
      else
      {
        up=true;down=true;left=true;right=true;
      }
      break;
    case KeyEvent.VK_RIGHT:
      if(right){
      rightBlock();}
      judgeAppear();
      appearBlock();
      over();
        
      if(numFlag==false)
      {
        right=false;
      }
      else
      {
        up=true;down=true;left=true;right=true;
      }
      break;
    }
  
  }
  public void keyTyped(KeyEvent e) {
  
  }
  public void keyReleased(KeyEvent e) {
  
  }
  
}
 


*/