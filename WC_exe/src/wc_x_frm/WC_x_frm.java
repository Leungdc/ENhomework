package wc_x_frm;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class WC_x_frm extends JFrame {
	int xiforBigStr=0;
    int xjforBigSB=0;
    private String xBigStr[] = new String[100];
    private StringBuilder BigSB = new StringBuilder(""); 
    private JTextArea xtextArea;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WC_x_frm window = new WC_x_frm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WC_x_frm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("\u53EF\u7528\u6587\u4EF6");
		frame.setBounds(100, 100, 454, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnc = new JButton("\u626B\u63CF\u7CFB\u7EDF\u4E2D\u6240\u6709\u7684.c\u6587\u4EF6");
		btnc.setBounds(34, 228, 229, 23);
		btnc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//确定事件
				Readfile(e);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 48, 364, 170);
		frame.getContentPane().add(scrollPane);
		
		this.xtextArea =  new JTextArea();
		xtextArea.setText("\u9009\u62E9\u4E00\u6761.c\u6587\u4EF6\u7684\u8DEF\u5F84\u7136\u540E\r\n\u70B9\u51FB\u5F00\u59CB\u6309\u94AE\uFF0C\u5219\u663E\u793A\u6587\u4EF6\u7684\u5B57\u7B26\u6570\u3001\u884C\u6570\u7B49\u5168\u90E8\u7EDF\u8BA1\u4FE1\u606F\u3002\r\n\u6B64\u8FC7\u7A0B\u9700\u82B1\u8D39\u7EA6\u4E00\u5206\u949F"
				  );
		xtextArea.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(xtextArea);
		
		JButton button = new JButton("\u5F00\u59CB");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getdirectory(e);
			}
		});
		button.setBounds(305, 228, 93, 23);
		frame.getContentPane().add(button);
	}
	/**
	 * 确定事件
	 * @param e
	 */
	private void getdirectory(ActionEvent e){
		String xdirectory = this.xtextArea.getSelectedText();
		File xfile = new File(xdirectory);
		cActionPerformed(e, xfile);
		wActionPerformed(e, xfile);
		lActionPerformed(e, xfile);
		aActionPerformed(e, xfile);
		xtextArea.setText(BigSB.toString()+"\n");
		BigSB.delete(0, BigSB.length());
	}
	/**
	 * 找文件方法
	 * @param e
	 */
	private void  Readfile(ActionEvent e){
		StringBuilder xbuilder = new StringBuilder("");
		int j=0;
		 String xfileName[] = new String [4];
		 xfileName[0] = "C:"+"\\";
		 xfileName[1] = "D:"+"\\";
		 xfileName[2] = "E:"+"\\";
		 xfileName[3] = "F:"+"\\";		 		
         int i=0;
         for(String filestr : xfileName){
 		     File f = new File(filestr);
        	 xprint(f);
         }
        /* File f = new File(xfileName[1]);
    	 xprint(f);*/
    	 for(;j<this.xBigStr.length;j++){	
				if(this.xBigStr[j]!=null){
					xbuilder.append(this.xBigStr[j]);
					xbuilder.append("\n");
				}
				else break;
    	 }
    	 xtextArea.setText("可用文件有："+"\n"+xbuilder+"\n");
         this.xiforBigStr = 0;
	}
	/**
	 * 找文件方法
	 * @param f
	 */
	private  void xprint(File f){
		//test:   wc.exe   -s   -c  
	if(f!=null){
	    if(f.isDirectory()){
	        File[] fileArray=f.listFiles();
	        if(fileArray!=null){
	            for (int i = 0; i < fileArray.length; i++) {
	                //递归调用            	
	                xprint(fileArray[i]);
	            }
	        }
	    }
	    else{
	        if (f.getName().endsWith(".c")){

	        System.out.println(f);        
	       this.xBigStr[this.xiforBigStr++]=f.getAbsolutePath();
	        }
	        else ;	
	       
	    }
	}


		}
	/**
	 * -l指令操作
	 */
	private void  lActionPerformed(ActionEvent e, File file){
		//this.txtSss.setText("this is l action");
		//test:      -l  D:\forWCexeUsage\scardsrv_i.c
		try{		  
			int num;
		    xtextArea.setText(readToString(file));
			num = xtextArea.getLineCount();
			  this.BigSB.append("行数："+Integer.toString(num)+"\n" );
			// this.textArea.setText("行数："+Integer.toString(num) );//这里把这句移到这里,并输出words	 
	    }catch(Exception ex){
	 	// TODO Auto-generated catch block
	 		ex.printStackTrace();
	   }
	}
	/**
	 * -a指令操作
	 */
	private void  aActionPerformed(ActionEvent e, File file){
		//test:   wc.exe   -a   D:\WCtest.txt  
		try{		 
			//test:   -a  D:\forWCexeUsage\scardsrv_i.c
			
			    int len, emptyline=0, codeline=0, noteline=0, Enum= 0, Cnum=0, Nnum=0;
			    xtextArea.setText(readToString(file));
			    String str =xtextArea.getText();
				String newstr2,newstr1,Eregs,Cregs,Nregs ;
				Eregs = "\\p{Graph}?";
				Nregs = "\\p{Graph}?//";
				Cregs = "\\p{Graph}{2,}";  //注意此处注释行是会匹配代码行格式成功的，将在下面的if语句中做进一步限制
				newstr1=str.replace(" ", "");
				newstr2=newstr1.replace("\t", "");
			    String[] xx=newstr2.split("\r\n");	
			    for(String a : xx){
			    	if (a.matches(Eregs))
			    	  emptyline++;
			    	
			    	else if (a.matches(Cregs)){
			    		 if (a.matches(Nregs))
					    	  noteline++;
			    		 else 
			    	          codeline++;
			    	}
			    	else codeline++;
			    }
			   Enum = emptyline;
			   Cnum = codeline;
			   Nnum = noteline;
			   this.BigSB.append("空行数："  + Integer.toString(Enum)  +"\n"
					   +         "注释行数：" + Integer.toString(Nnum)  +"\n"
					   +          "代码行数：" + Integer.toString(Cnum) + "\n" );
			/*   this.textArea.setText("空行数："  + Integer.toString(Enum)  +"\n"
			   +                     "注释行数：" + Integer.toString(Nnum)   +"\n"
			   +          "代码行数：" + Integer.toString(Cnum)          ) ;	 */
			  }catch(Exception ex){
				// TODO Auto-generated catch block
					ex.printStackTrace();
			  }
	}

	/**
	 * -w指令操作
	 */
	private void  wActionPerformed(ActionEvent e, File file){
		//this.txtSss.setText("this is w action");
		//test:    -w  D:\forWCexeUsage\scardsrv_i.c
		  try{		  
			 //  Scanner sc=new Scanner(file);
			/*    FileReader in = new FileReader(file);
				char byt[] = new char[(int)file.length()];
				int len,num = 0;
				len = in.read(byt);
				in.close();*/
			    int num = 0;
				String str,newstr ;
			    xtextArea.setText(readToString(file));
				str =xtextArea.getText();
			    String[] xx=str.split("[a-zA-Z]+");	
			    for(String a : xx){
			    	num+=1;
			    }
			    if (num == 0)  ;
			    else
			    	num-=1;
			    this.BigSB.append("单词数："+Integer.toString(num)+"\n");
			 //  this.textArea.setText("单词数："+Integer.toString(num));//这里把这句移到这里,并输出words	 
			  }catch(Exception ex){
				// TODO Auto-generated catch block
					ex.printStackTrace();
					
			  }
	}
	/**
	 * -c指令操作
	 */
	private void  cActionPerformed(ActionEvent e,File file) {
		//   -c   D:\forWCexeUsage\scardsrv_i.c
		try {
			int length,num,len;
				xtextArea.setText(readToString(file));
				String str,newstr ;
				str = xtextArea.getText();
				newstr=str.replaceAll("\\s", "");				 
	            length=newstr.length();
	            num=length;
	           
	          //  this.textArea.setText(file.getName()+"中的字符数为："+Integer.toString(num)+"\n");
		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
	
	}
	
	private void refresh(){
		this.xiforBigStr = 0;
	}
	/**
	 * 
	 * 读文件方法
	 */
	private  String readToString(File f) {
        String encoding = "UTF-8";
        File file = f;
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}

