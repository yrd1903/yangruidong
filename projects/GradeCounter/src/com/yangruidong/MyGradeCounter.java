package com.yangruidong;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class MyGradeCounter extends JFrame implements MouseListener,ActionListener
{
	TheClass classChosed,class1,class2,class3;//定义班级
	private String courseName,analysisWay;//定义分析的课程，分析方式
	
	JPanel p1,p2,p3,p4;
	JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5,jrb6,jrb7,jrb8;
	ButtonGroup bg1,bg2,bg3;
	JLabel jl1,jl2,jl3;
	JButton jb,jb1;
	GradeResults gr;//分析成绩窗口界面
	
		
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MyGradeCounter mygradecounter=new MyGradeCounter
				("学生成绩管理系统");
		mygradecounter.setResizable(false);//设置主窗口不可调整大小
	}
	
	public MyGradeCounter(String title)
	{
		super(title);
		this.setLayout(new GridLayout(4,1));
		this.setLocation(400, 200);	
		
		class1=new TheClass("1班");
		class2=new TheClass("2班");
		class3=new TheClass("3班");
		
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		
		p1.setLayout(new GridLayout(1,4));
		p2.setLayout(new GridLayout(1,4));
		p3.setLayout(new GridLayout(1,3));
		
		jl1=new JLabel("请选择班级：");
		jl2=new JLabel("请选择科目：");
		jl3=new JLabel("请选择绘制方式：");
		
		jrb1=new JRadioButton("  1班  ");
		jrb2=new JRadioButton("  2班  ");
		jrb3=new JRadioButton("  3班  ");
		jrb4=new JRadioButton("  数学  ");
		jrb5=new JRadioButton("  英语  ");
		jrb6=new JRadioButton("  Java  ");
		jrb7=new JRadioButton("  直方图  ");
		jrb8=new JRadioButton("  扇形图  ");
		
		jrb1.addActionListener(this);
		jrb2.addActionListener(this);
		jrb3.addActionListener(this);
		jrb4.addActionListener(this);
		jrb5.addActionListener(this);
		jrb6.addActionListener(this);
		jrb7.addActionListener(this);
		jrb8.addActionListener(this);
		
		bg1=new ButtonGroup();
		bg2=new ButtonGroup();
		bg3=new ButtonGroup();
		
		jb=new JButton("开始分析");
		jb1=new JButton("数据输入");
		jb.addMouseListener(this);//注册监听
		jb1.addMouseListener(this);
		
		bg1.add(jrb1);
		bg1.add(jrb2);
		bg1.add(jrb3);
		
		bg2.add(jrb4);
		bg2.add(jrb5);
		bg2.add(jrb6);
		
		bg3.add(jrb7);
		bg3.add(jrb8);
		
		p1.add(jl1);
		p1.add(jrb1);
		p1.add(jrb2);
		p1.add(jrb3);
		
		p2.add(jl2);
		p2.add(jrb4);
		p2.add(jrb5);
		p2.add(jrb6);
		
		p3.add(jl3);
		p3.add(jrb7);
		p3.add(jrb8);
		
		p4.add(jb);
		p4.add(jb1);
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		
		
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JButton)e.getSource()).equals(jb))//如果按下“开始分析”
		{
			if((jrb1.isSelected()||jrb2.isSelected()||jrb3.isSelected())&&(jrb4.isSelected()||jrb5.isSelected()||jrb6.isSelected())&&(jrb7.isSelected()||jrb8.isSelected()))
			{//只有三个选项都做出选择后，才能打开成绩分析结果界面
				gr=new GradeResults(classChosed,courseName,analysisWay);
				//创建成绩分析结果的窗口，传入的参数为通过单选框事件得到的所选班级，课程名，分析方式
				gr.setResizable(false);//设置成绩分析结果窗口不可调整大小
			}
			else 
				JOptionPane.showMessageDialog(null, "请先选择班级、科目以及绘制方式！","操作错误！",JOptionPane.PLAIN_MESSAGE);
		}	
		
		if(((JButton)e.getSource()).equals(jb1))//如果按下了数据输入
		{
			//这里覆盖数据输入代码
			WriteGrade dataGet=new WriteGrade();
			dataGet.setResizable(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jrb1.isSelected())
		{
			classChosed=class1;
		}
		if(jrb2.isSelected())
		{
			classChosed=class2;
		}
		if(jrb3.isSelected())
		{
			classChosed=class3;
		}
		if(jrb4.isSelected())
		{
			courseName="数学";
		}
		if(jrb5.isSelected())
		{
			courseName="英语";
		}
		if(jrb6.isSelected())
		{
			courseName="Java";
		}
		if(jrb7.isSelected())
		{
			analysisWay="直方图";
		}
		if(jrb8.isSelected())
		{
			analysisWay="扇形图";
		}
	}

}

//成绩分析结果窗口界面
class GradeResults extends JFrame
{
	
	CourseAnalysis courseAnalysis;//创建一个成绩分析的对象引用
	JPanel p1,p11,p12,p2;
	PaintPanelZhi ppzhi;
	PaintPanelShan ppshan;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl_a,jl_b;
	public GradeResults(TheClass theClass,String courseName,String analysisWay)
	{//这里构造函数接收了主面板所选择的班级，课程，分析方式
		
		courseAnalysis=new CourseAnalysis(theClass,courseName); 
		//这里创建成绩分析对象，并且把上面接收的班级，课程传入到成绩分析对象，
		//分析方式只是在显示时区分，不需要传到成绩分析对象
		ppzhi=new PaintPanelZhi(courseAnalysis.getNumberbelow60(),courseAnalysis.getNumber6070(),courseAnalysis.getNumber7080(),courseAnalysis.getNumber8090(),courseAnalysis.getNumberover90(),courseAnalysis.getHighGrade(),courseAnalysis.getLowGrade(),courseAnalysis.getLowGrade());
		ppshan=new PaintPanelShan(courseAnalysis.getNumberbelow60(),courseAnalysis.getNumber6070(),courseAnalysis.getNumber7080(),courseAnalysis.getNumber8090(),courseAnalysis.getNumberover90(),courseAnalysis.getHighGrade(),courseAnalysis.getLowGrade(),courseAnalysis.getLowGrade(),courseAnalysis.getStudentNumber());
		p1=new JPanel();
		p2=new JPanel();
		p11=new JPanel();
		p12=new JPanel();
		
		p1.setLayout(new GridLayout(2,1));
		p2.setLayout(new FlowLayout());
		p11.setLayout(new FlowLayout());
		p12.setLayout(new FlowLayout());
		
		jl1=new JLabel("课程分析结果");
		jl2=new JLabel("所选班级：");
		jl3=new JLabel("                          课程名称：");
		jl4=new JLabel("最高分：");
		jl5=new JLabel("                最低分：");
		jl6=new JLabel("                平均分：");
		jl7=new JLabel(theClass.getClassName());
		jl8=new JLabel(courseName);
		jl9=new JLabel(""+courseAnalysis.getHighGrade());
		jl10=new JLabel(""+courseAnalysis.getLowGrade());
		jl11=new JLabel(""+courseAnalysis.getAvarageGrade());
		jl_a=new JLabel("                          课程人数：");
		jl_b=new JLabel(""+courseAnalysis.getStudentNumber());
		
		p11.add(jl1);
		p12.add(jl2);
		p12.add(jl7);
		p12.add(jl3);
		p12.add(jl8);
		p12.add(jl_a);
		p12.add(jl_b);
		
		p2.add(jl4);
		p2.add(jl9);
		p2.add(jl5);
		p2.add(jl10);
		p2.add(jl6);
		p2.add(jl11);
		
		p1.add(p11);
		p1.add(p12);
		if(analysisWay=="直方图")
		{
			this.add(ppzhi,BorderLayout.CENTER);
		}
		if(analysisWay=="扇形图")
		{
			this.add(ppshan,BorderLayout.CENTER);
		}
		//System.out.println(analysisWay);
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.SOUTH);
		
		this.setTitle("成绩分析");
		this.setLocation(400, 150);
		this.setSize(600, 450);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}

//绘制直方图成绩分析结果图形的Panel
class PaintPanelZhi extends JPanel 
{
	private int numberbelow60,number6070,
				number7080,number8090,numberover90; 
	private double highGrade,lowGrade,avarageGrade;
	
	public PaintPanelZhi(int numberbelow60,int number6070,int number7080,int number8090,int numberover90,double highGrade,double lowGrade,double avarageGrade)
	{
		this.numberbelow60=numberbelow60;
		this.number6070=number6070;
		this.number7080=number7080;
		this.number8090=number8090;
		this.numberover90=numberover90;
		this.highGrade=highGrade;
		this.lowGrade=lowGrade;
		this.avarageGrade=avarageGrade;
		this.setSize(580,350);
	}

	public void paint(Graphics g)
	{
		g.drawRect(20, 15, 540, 300);
		g.drawString("考试成绩统计直方图", 240,40);
		g.drawString("人数", 80,50);
		g.drawRect(90,60,420,200);
		g.drawString("<60分", 145, 275);
		g.drawString("60-70分", 215, 275);
		g.drawString("70-80分", 285, 275);
		g.drawString("80-90分", 355, 275);
		g.drawString(">90分", 425, 275);
		
		g.drawString("0", 75, 265);
		g.drawString("5", 75, 225);
		g.drawString("10", 70, 185);
		g.drawString("15", 70, 145);
		g.drawString("20", 70, 105);
		g.drawString("25", 70, 65);
		
		g.setColor(Color.BLUE);
		g.fill3DRect(140, 260-8*numberbelow60, 40, 8*numberbelow60,false);
		g.fill3DRect(210, 260-8*number6070, 40, 8*number6070,false);
		g.fill3DRect(280, 260-8*number7080, 40, 8*number7080,false);
		g.fill3DRect(350, 260-8*number8090, 40, 8*number8090,false);
		g.fill3DRect(420, 260-8*numberover90, 40, 8*numberover90,false);
	}

}
//绘制扇形图成绩结果的Panel
class PaintPanelShan extends JPanel 
{
	private int numberbelow60,number6070,
				number7080,number8090,numberover90; 
	private double highGrade,lowGrade,avarageGrade;
	private int studentNumber;//定义班级学生人数，因为画扇形需要知道总人数算百分比
	
	public PaintPanelShan(int numberbelow60,int number6070,int number7080,int number8090,int numberover90,double highGrade,double lowGrade,double avarageGrade,int studentNumber)
	{
		this.numberbelow60=numberbelow60;
		this.number6070=number6070;
		this.number7080=number7080;
		this.number8090=number8090;
		this.numberover90=numberover90;
		this.highGrade=highGrade;
		this.lowGrade=lowGrade;
		this.avarageGrade=avarageGrade;
		this.studentNumber=studentNumber;
		this.setSize(580,350);
	}

	public void paint(Graphics g)
	{
		Color c=new Color(51,51,51);
		g.drawRect(20, 15, 540, 300);//大方框
		g.drawString("考试成绩统计扇形图", 240,40);
		g.drawRect(80,60,430,220);//小方框
		//画出标示信息
		//System.out.println(g.getColor());
		g.drawRect(390, 130, 100, 130);
		g.setColor(Color.gray);
		g.fillRect(400, 145, 10, 10);
		g.setColor(Color.green);
		g.fillRect(400, 168, 10, 10);
		g.setColor(Color.blue);
		g.fillRect(400, 191, 10, 10);
		g.setColor(Color.YELLOW);
		g.fillRect(400, 214, 10, 10);
		g.setColor(Color.RED);
		g.fillRect(400, 237, 10, 10);
		g.setColor(c);
		g.drawString("<60分", 430, 155);
		g.drawString("60-70分", 430, 178);
		g.drawString("70-80分", 430, 201);
		g.drawString("80-90分", 430, 224);
		g.drawString(">90分", 430, 247);
		
		//g.drawRect(110, 80, 180,180);
		//开始画扇形
		g.setColor(Color.gray);//gray代表<60分
		g.fillArc(110, 80, 180,180, 0, (360*numberbelow60)/studentNumber);
		g.setColor(Color.green);
		g.fillArc(110, 80, 180,180, (360*numberbelow60)/studentNumber, (360*(numberbelow60+number6070))/studentNumber-(360*numberbelow60)/studentNumber);
		g.setColor(Color.blue);
		g.fillArc(110, 80, 180,180, (360*(numberbelow60+number6070))/studentNumber, (360*(numberbelow60+number6070+number7080))/studentNumber-(360*(numberbelow60+number6070))/studentNumber);
		g.setColor(Color.YELLOW);
		g.fillArc(110, 80, 180,180, (360*(numberbelow60+number6070+number7080))/studentNumber, (360*(numberbelow60+number6070+number7080+number8090))/studentNumber-(360*(numberbelow60+number6070+number7080))/studentNumber);
		g.setColor(Color.RED);
		g.fillArc(110, 80, 180,180, (360*(numberbelow60+number6070+number7080+number8090))/studentNumber, (360-(360*(numberbelow60+number6070+number7080+number8090))/studentNumber));
		//System.out.println(0);//为什么会执行多次？
	}
}

//定义成绩数据采集的类
class WriteGrade extends JFrame implements MouseListener,ActionListener
{
	JPanel p00,p01,p1,p2,p11,p12,p13,p21,p22,p23;
	JLabel jl1,jl2,jl11,jl12,jl13,jl14,jl15,jl16,jl21,jl22,jl23,jl24,jl25,jl26;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10;
	JComboBox jcb1,jcb2;
	JButton jb1,jb2,jb3;
	
	private String className,studentName,studentID,mathGrade,englishGrade,javaGrade;
	//定义学生的六个参数，用来接收键盘输入的信息
	ReadGrade rg;
	File f=null;
	FileOutputStream fos=null;
	private String gradeData="";//先从文件中取出数据，然后再把输入的数据加到文件字符串末尾
	public WriteGrade()
	{
		
		jl1=new JLabel("请输入学生信息");
		jl2=new JLabel("修改学生信息");
		jl11=new JLabel("            班      级  ");
		jl12=new JLabel("            姓      名  ");
		jl13=new JLabel("            学      号  ");
		jl14=new JLabel("            数学成绩  ");
		jl15=new JLabel("            英语成绩  ");
		jl16=new JLabel("            Java成绩");
		jl21=new JLabel("            班      级  ");
		jl22=new JLabel("            姓      名  ");
		jl23=new JLabel("            学      号  ");
		jl24=new JLabel("            数学成绩  ");
		jl25=new JLabel("            英语成绩  ");
		jl26=new JLabel("            Java成绩");
		
		jb1=new JButton("保存");
		jb1.addMouseListener(this);
		jb2=new JButton("确认修改");
		jb2.addMouseListener(this);
		jb3=new JButton("显示当前成绩信息");
		jb3.addMouseListener(this);
		
		jcb1=new JComboBox();
		jcb2=new JComboBox();
		
		jcb1.addItem("1班");
		jcb1.addItem("2班");
		jcb1.addItem("3班");
		jcb2.addItem("1班");
		jcb2.addItem("2班");
		jcb2.addItem("3班");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		jtf7=new JTextField();
		jtf8=new JTextField();
		jtf9=new JTextField();
		jtf10=new JTextField();
		
		p1=new JPanel();
		p2=new JPanel();
		p11=new JPanel();
		p12=new JPanel();
		p13=new JPanel();
		p21=new JPanel();
		p22=new JPanel();
		p23=new JPanel();
		p00=new JPanel();
		p01=new JPanel();
		
		p11.add(jl1);
		p12.add(jl11);
		p12.add(jcb1);
		p12.add(jl12);
		p12.add(jtf1);
		p12.add(jl13);
		p12.add(jtf2);
		p12.add(jl14);
		p12.add(jtf3);
		p12.add(jl15);
		p12.add(jtf4);
		p12.add(jl16);
		p12.add(jtf5);
		p13.add(jb1);
		
		p21.add(jl2);
		p22.add(jl21);
		p22.add(jcb2);
		p22.add(jl22);
		p22.add(jtf6);
		p22.add(jl23);
		p22.add(jtf7);
		p22.add(jl24);
		p22.add(jtf8);
		p22.add(jl25);
		p22.add(jtf9);
		p22.add(jl26);
		p22.add(jtf10);
		p23.add(jb2);
		
		p1.setLayout(new BorderLayout());//JPanel默认是FlowLayout布局，首先要设置BorderLayout布局
		p2.setLayout(new BorderLayout());
		p1.add(p11,BorderLayout.NORTH);
		p1.add(p12,BorderLayout.CENTER);
		p1.add(p13,BorderLayout.SOUTH);
		p2.add(p21,BorderLayout.NORTH);
		p2.add(p22,BorderLayout.CENTER);
		p2.add(p23,BorderLayout.SOUTH);
		
		p01.add(p1);
		p01.add(p2);
		p00.add(jb3);
		
		p01.setLayout(new GridLayout(1,2,50,20));
		p12.setLayout(new GridLayout(6,2,1,30));
		p22.setLayout(new GridLayout(6,2,1,30));
		
		this.add(p01,BorderLayout.CENTER);
		this.add(p00,BorderLayout.SOUTH);
		
		this.setTitle("成绩数据录入");
		this.setLocation(400, 150);
		this.setSize(600, 450);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(((JButton)e.getSource()).equals(jb1))//按下”保存“按钮
		{
			boolean studentExist=false;//定义是否存在相同学号的学生的判断变量，默认为假，即不存在相同学号的学生
			
			if(!(this.jtf1.getText().isEmpty())&!(this.jtf2.getText().isEmpty())&!(this.jtf3.getText().isEmpty())&!(this.jtf4.getText().isEmpty())&!(this.jtf5.getText().isEmpty()))
			{//如果五个文本框都不为空，则执行保存数据的操作
				className=this.jcb1.getSelectedItem().toString();
				//System.out.println(className);
				studentName=this.jtf1.getText();
				studentID=this.jtf2.getText();
				mathGrade=this.jtf3.getText();
				englishGrade=this.jtf4.getText();
				javaGrade=this.jtf5.getText();
				rg=new ReadGrade(className);
				for(int i=1;i<rg.ressplited.length;i=i+5)
				{
					if(studentID.equals(rg.ressplited[i]))
					{
						studentExist=true;
						JOptionPane.showMessageDialog(null, "已经有相同学号的学生存在！","输入错误",JOptionPane.PLAIN_MESSAGE);
					}
				}
				if(studentExist==false)
				{
					gradeData=rg.res;
					gradeData=gradeData+"\r\n"+studentName+"|"+studentID+"|"+mathGrade+"|"+englishGrade+"|"+javaGrade+"|";
					//  \r\n
					this.writeGrade();
					JOptionPane.showMessageDialog(null, "成绩数据保存成功！","操作提示",JOptionPane.PLAIN_MESSAGE);
				}	
			}
			else//只要不满足五个文本框都有数据，则进行提示
			{
				JOptionPane.showMessageDialog(null, "请输入完整成绩信息！","输入错误",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(((JButton)e.getSource()).equals(jb2))//按下”确认修改“按钮
		{
			if(!(this.jtf6.getText().isEmpty())&!(this.jtf7.getText().isEmpty())&!(this.jtf8.getText().isEmpty())&!(this.jtf9.getText().isEmpty())&!(this.jtf10.getText().isEmpty()))
			{
				String gradeChange="";
				boolean studentExist=false;//定义修改学生是否存在的变量，默认是不存在
				className=this.jcb2.getSelectedItem().toString();
				studentName=this.jtf6.getText();
				studentID=this.jtf7.getText();
				mathGrade=this.jtf8.getText();
				englishGrade=this.jtf9.getText();
				javaGrade=this.jtf10.getText();
				rg=new ReadGrade(className);
				
				for(int i=1;i<rg.ressplited.length;i=i+5)
				{
					if(studentID.equals(rg.ressplited[i]))
					{
						studentExist=true;
						rg.ressplited[i-1]="\r\n"+studentName;//studentName和"\r\n"是组合在一起构成数组的一个元素
						rg.ressplited[i+1]=mathGrade;
						rg.ressplited[i+2]=englishGrade;
						rg.ressplited[i+3]=javaGrade;
					}
				}
				if(studentExist==true)
				{
					for(int i=0;i<rg.ressplited.length;i++)
					{
						gradeChange=gradeChange+rg.ressplited[i]+"|";
					}
					gradeData=gradeChange;
					this.writeGrade();
					JOptionPane.showMessageDialog(null, "修改成绩信息成功！","操作提示",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "输入修改学生信息不存在！","操作错误",JOptionPane.PLAIN_MESSAGE);
				}
			}
			else//只要不满足五个文本框都有数据，则进行提示
			{
				JOptionPane.showMessageDialog(null, "请输入完整成绩信息！","输入错误",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(((JButton)e.getSource()).equals(jb3))//按下”显示当前成绩“按钮
		{
			className=this.jcb1.getSelectedItem().toString();//根据左侧的下拉菜单决定所要显示成绩的班级
			rg=new ReadGrade(className);
			String outputArea="";
			outputArea=className+"的成绩信息如下：\r\n姓名|学号|数学|英语|Java\r\n"+rg.res;
			JOptionPane.showMessageDialog(null, outputArea,"当前成绩信息",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void writeGrade()
	{
		if(className.equals("1班"))
		{
			f=new File("D:\\GradeCounter\\1班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(className.equals("2班"))
		{
			f=new File("D:\\GradeCounter\\2班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(className.equals("3班"))
		{
			f=new File("D:\\GradeCounter\\3班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
			try {
				fos=new FileOutputStream(f);
				fos.write(gradeData.getBytes());//把成绩字符串重新写到文件中
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	

}

//班级类
class TheClass
{
	private String className;
	//private int number=20;
	public TheClass(String className)
	{		
		this.className=className;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}

////学生类
//class Student
//{
//	private String name,id;//定义学生的学号，名字
//	private int mathGrade,englishGrade,javaGrade;//定义学生数学英语Java成绩
//	public Student()
//	{
//		
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public int getMathGrade() {
//		return mathGrade;
//	}
//	public void setMathGrade(int mathGrade) {
//		this.mathGrade = mathGrade;
//	}
//	public int getEnglishGrade() {
//		return englishGrade;
//	}
//	public void setEnglishGrade(int englishGrade) {
//		this.englishGrade = englishGrade;
//	}
//	public int getJavaGrade() {
//		return javaGrade;
//	}
//	public void setJavaGrade(int javaGrade) {
//		this.javaGrade = javaGrade;
//	}
//	
//}

//课程分析类
class CourseAnalysis
{
	private TheClass theClass;//要进行分析的班级
	private String courseName;//要进行分析的课程
	//private String analysisWay;//要进行绘制的方式
	private double highGrade,lowGrade=100,avarageGrade;//最高分，最低分，平均分
	private int numberbelow60,number6070,
				number7080,number8090,numberover90;//各个分数段的人数
	private int studentNumber;
	ReadGrade readGrade;
	
	//传递所要分析的班级对象名，要分析的课程名到构造函数
	public CourseAnalysis(TheClass theClass,String courseName)
	{
		this.theClass=theClass;
		this.courseName=courseName;
		readGrade=new ReadGrade(theClass);
		studentNumber=readGrade.mathGrade.length;
		this.calculate();
	}
	
	public void calculate()
	{
		String course=this.courseName;
		double sum=0;
		DecimalFormat df=new DecimalFormat("0.00");
		switch (course)
		{	
		case "数学":
			for(int i=0;i<readGrade.getMathGrade().length;i++)
			{

				if(readGrade.mathGrade[i]<60)
					numberbelow60++;
				else if(readGrade.mathGrade[i]<70)
					number6070++;
				else if(readGrade.mathGrade[i]<80)
					number7080++;
				else if(readGrade.mathGrade[i]<90)
					number8090++;
				else if(readGrade.mathGrade[i]<100)
					numberover90++;
				//求解数学的最高分，最低分，平均分
				if(readGrade.mathGrade[i]>this.highGrade)
				{
					this.highGrade=readGrade.mathGrade[i];
				}
				if(readGrade.mathGrade[i]<this.lowGrade)
				{
					this.lowGrade=readGrade.mathGrade[i];
				}
				sum=sum+readGrade.mathGrade[i];
			}
			//这里需要先把平均值sum/readGrade.getMathGrade().length限制为两位小数，然后其返回为字符串，再把它转换为Double
			this.avarageGrade=Double.parseDouble(df.format(sum/readGrade.getMathGrade().length));
				
			break;
		case "英语":
			for(int i=0;i<readGrade.getEnglishGrade().length;i++)
			{
				if(readGrade.englishGrade[i]<60)
					numberbelow60++;
				else if(readGrade.englishGrade[i]<70)
					number6070++;
				else if(readGrade.englishGrade[i]<80)
					number7080++;
				else if(readGrade.englishGrade[i]<90)
					number8090++;
				else if(readGrade.englishGrade[i]<100)
					numberover90++;
				//求解英语的最高分，最低分，平均分
				if(readGrade.englishGrade[i]>this.highGrade)
				{
					this.highGrade=readGrade.englishGrade[i];
				}
				if(readGrade.englishGrade[i]<this.lowGrade)
				{
					this.lowGrade=readGrade.englishGrade[i];
				}
				sum=sum+readGrade.englishGrade[i];
			}
			this.avarageGrade=Double.parseDouble(df.format(sum/readGrade.getEnglishGrade().length));
			break;
			
		case "Java":
			for(int i=0;i<readGrade.javaGrade.length;i++)
			{
				if(readGrade.javaGrade[i]<60)
					numberbelow60++;
				else if(readGrade.javaGrade[i]<70)
					number6070++;
				else if(readGrade.javaGrade[i]<80)
					number7080++;
				else if(readGrade.javaGrade[i]<90)
					number8090++;
				else if(readGrade.javaGrade[i]<100)
					numberover90++;
				//求解Java的最高分，最低分，平均分
				if(readGrade.javaGrade[i]>this.highGrade)
				{
					this.highGrade=readGrade.javaGrade[i];
				}
				if(readGrade.javaGrade[i]<this.lowGrade)
				{
					this.lowGrade=readGrade.javaGrade[i];
				}
				sum=sum+readGrade.javaGrade[i];
			}
			this.avarageGrade=Double.parseDouble(df.format(sum/readGrade.getJavaGrade().length));
			break;
		}
		//System.out.println(numberover90);
		
	}
	
	public int getNumberbelow60() {
		return numberbelow60;
	}
	public void setNumberbelow60(int numberbelow60) {
		this.numberbelow60 = numberbelow60;
	}
	public int getNumber6070() {
		return number6070;
	}
	public void setNumber6070(int number6070) {
		this.number6070 = number6070;
	}
	public int getNumber7080() {
		return number7080;
	}
	public void setNumber7080(int number7080) {
		this.number7080 = number7080;
	}
	public int getNumber8090() {
		return number8090;
	}
	public void setNumber8090(int number8090) {
		this.number8090 = number8090;
	}
	public int getNumberover90() {
		return numberover90;
	}
	public void setNumberover90(int numberover90) {
		this.numberover90 = numberover90;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getHighGrade() {
		return highGrade;
	}
	public void setHighGrade(double highGrade) {
		this.highGrade = highGrade;
	}
	public double getLowGrade() {
		return lowGrade;
	}
	public void setLowGrade(double lowGrade) {
		this.lowGrade = lowGrade;
	}
	public double getAvarageGrade() {
		return avarageGrade;
	}
	public void setAvarageGrade(double avarageGrade) {
		this.avarageGrade = avarageGrade;
	}
	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
}

//定义读取成绩的类
class ReadGrade
{
	File f=null;
//	File f1=new File("d:\\1班.txt");
//	File f2=new File("d:\\2班.txt");
//	File f3=new File("d:\\3班.txt");
	FileInputStream fis=null;
	String res="";
	String ressplited[];

	Double mathGrade[],englishGrade[],javaGrade[];
	
	public ReadGrade(TheClass theClass)//第一个构造函数
	{
		if((theClass.getClassName())=="1班")
		{
			f=new File("D:\\GradeCounter\\1班.txt");
		}
		if((theClass.getClassName())=="2班")
		{
			f=new File("D:\\GradeCounter\\2班.txt");;
		}
		if((theClass.getClassName())=="3班")
		{
			f=new File("D:\\GradeCounter\\3班.txt");
		}
		this.readFile();
		//System.out.println("readFile执行了");
	}
	
	public ReadGrade(String className)//第二个构造函数
	{
		if(className.equals("1班"))
		{
			f=new File("D:\\GradeCounter\\1班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(className.equals("2班"))
		{
			f=new File("D:\\GradeCounter\\2班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(className.equals("3班"))
		{
			f=new File("D:\\GradeCounter\\3班.txt");
			if(!f.exists())
			{	try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.readFile();
	}
	public void readFile()
	{
		try 
		{
			fis=new FileInputStream(f);
			//定义一个字节数组
			byte []bytes=new byte[1024];
			int n=0;
			//循环读取
			while((n=fis.read(bytes))!=-1)
			{
				String s=new String(bytes,0,n);
				//System.out.println(s);
				res=res+s;
			}
			//System.out.println(res);
			ressplited=res.split("\\|");			
			//System.out.println(ressplited.length);
			//定义三科成绩数组,并且数组的长度根据读取后分割后长度设置
			//为(ressplited.length)/5，即读取到的人数
			mathGrade=new Double[(ressplited.length)/5];
			englishGrade=new Double[(ressplited.length)/5];
			javaGrade=new Double[(ressplited.length)/5];
			//对成绩数组进行初始化
			for(int i=0;i<(ressplited.length)/5;i++)
			{
				mathGrade[i]=Double.parseDouble(ressplited[5*i+2]);
				englishGrade[i]=Double.parseDouble(ressplited[5*i+3]);
				javaGrade[i]=Double.parseDouble(ressplited[5*i+4]);
			}
			//System.out.println(javaGrade[18]);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			//当在指定路径找不到目标数据文件时，弹出消息框进行提示
			JOptionPane.showMessageDialog(null, "文件路径错误！请将所选班级数据文件放在正确位置！","数据错误！",JOptionPane.PLAIN_MESSAGE);
		}
		finally
		{
			//关闭文件流
			try 
			{
				fis.close();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public Double[] getMathGrade() {
		return mathGrade;
	}

	public void setMathGrade(Double[] mathGrade) {
		this.mathGrade = mathGrade;
	}

	public Double[] getEnglishGrade() {
		return englishGrade;
	}

	public void setEnglishGrade(Double[] englishGrade) {
		this.englishGrade = englishGrade;
	}

	public Double[] getJavaGrade() {
		return javaGrade;
	}

	public void setJavaGrade(Double[] javaGrade) {
		this.javaGrade = javaGrade;
	}
	public String[] getRessplited() {
		return ressplited;
	}

	public void setRessplited(String[] ressplited) {
		this.ressplited = ressplited;
	}
}
