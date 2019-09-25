package wc_exe_view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;





import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import wc_x_frm.WC_x_frm;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;

public class WCview {
	int iforBigStr=0;
    private File BigFile[];
    private String BigStr[] = new String[100];
	private JFrame frmWcexe;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WCview window = new WCview();
					window.frmWcexe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WCview() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWcexe = new JFrame();
		frmWcexe.setTitle("WC.exe");
		frmWcexe.setBounds(100, 100, 583, 402);
		frmWcexe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWcexe.getContentPane().setLayout(null);
		
		JButton button = new JButton("\u5F00\u59CB");
		button.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				//在此处编辑按钮的单击事件
				Readfile(e);
				
			}
		});
		button.setBounds(105, 330, 93, 23);
		frmWcexe.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u5728\u4E0B\u65B9\u6DFB\u52A0\u6587\u4EF6\u8DEF\u5F84\uFF08\u591A\u6761\u8DEF\u5F84\u8BF7\u6362\u884C\u5206\u9694\u5F00\uFF09");
		lblNewLabel.setBounds(36, 28, 300, 39);
		frmWcexe.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(WCview.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		
		textField = new JTextField();
		textField.setBounds(36, 299, 490, 21);
		frmWcexe.getContentPane().add(textField);
		textField.setForeground(Color.BLACK);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//在此处编辑Textfiled的事件
			}
		});
		textField.setToolTipText("");
		textField.setBackground(Color.ORANGE);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 73, 489, 216);
		frmWcexe.getContentPane().add(scrollPane);
		
		this.textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.PINK);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chongzhi();
			}
		});
		button_1.setBounds(352, 330, 93, 23);
		frmWcexe.getContentPane().add(button_1);
	}

	/**
	 * FileReader
	 */
	private void  Readfile(ActionEvent e){
		//testdirectory:   wc.exe   -c   D:\WCtest.txt
		int maohao = 0;
		String the_s_x;	
		String text=this.textArea.getText();
		the_s_x = text;
		the_s_x = the_s_x.replaceAll("\\s", "");
		File empty  = null;
		//StringBuilder sf = new StringBuilder("");
		if(text.length()<2) { invaildoperation(e); }//报错
		else if (the_s_x.equals("-x"))
	          xActionPerformed(e);	
		else if (the_s_x.equals("-s"))
			  sActionPerformed(e, empty, -1);	
     	else{  	
				String textT,textT2,textTR,command,Scommand,directory=null;
				maohao = text.indexOf(":", 0);
				textT=text.trim();
				textT2=textT.substring(maohao);
				textT=textT.substring(0, maohao);				
				textT=textT.replaceAll("\\s", "");
				textTR=textT+textT2;
				//txtSss.setText(textTR);							
									command=textTR.substring(0, 2);
									Scommand=textTR.substring(2, 4);
									directory=textTR.substring(2);								
									File file= new File(directory);
									switch(command){
									case "-c":
										 cActionPerformed(e,file);
									     break;
									case "-w":
									     wActionPerformed(e,file);
									     break;
									case "-l":
									     lActionPerformed(e,file);
									     break;	 
									case "-a":
									     aActionPerformed(e, file);
									     break;					
									case "-s":
										directory=textTR.substring(4);								
										File newfile= new File(directory);
										switch(Scommand){
										case "-c":
											 sActionPerformed(e,newfile, 1);
										     break;
										case "-w":
											 sActionPerformed(e,newfile, 2);
										     break;
										case "-l":
										     sActionPerformed(e,newfile, 3);
										     break;	 
										case "-a":
										     sActionPerformed(e, newfile, 4);
										     break;	
										default:	
											
											sActionPerformed(e, file, 0);
											break;
										}									
										 
										 break;
									default:
										invaildoperation(e);
										break;//报错
									
									}
								
				
								
				
			     }
				 

				}
		
	/**
	 * -c指令操作
	 */
	private void  cActionPerformed(ActionEvent e,File file) {
		//   -c   D:\forWCexeUsage\scardsrv_i.c
		try {
			int length,num,len;
			if(readToString(file)==null)   filenotfound();
			else{
				textArea.setText(readToString(file));
				String str,newstr ;
				str = textArea.getText();
				newstr=str.replaceAll("\\s", "");				 
	            length=newstr.length();
	            num=length;
	            this.textArea.setText(file.getName()+"中的字符数为："+Integer.toString(num)+"\n");
		}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
	
	}
	/**
	 * -w指令操作
	 */
	private void  wActionPerformed(ActionEvent e, File file){
		//test:    -w  D:\forWCexeUsage\scardsrv_i.c
		  try{		  
			    int num = 0;
				String str,newstr ;
			    textArea.setText(readToString(file));
				str =textArea.getText();
			    String[] xx=str.split("[a-zA-Z]+");	
			    for(String a : xx){
			    	num+=1;
			    }
			    if (num == 0)  ;
			    else
			   	   num-=1;	  
			   this.textArea.setText("单词数："+Integer.toString(num));//这里把这句移到这里,并输出words	 
			  }catch(Exception ex){
				// TODO Auto-generated catch block
					ex.printStackTrace();
					filenotfound();
			  }
	}
	/**
	 * -l指令操作
	 */
	private void  lActionPerformed(ActionEvent e, File file){
		//test:      -l  D:\forWCexeUsage\scardsrv_i.c
		try{
			int num=0;
		    textArea.setText(readToString(file));
		    if (!(file.getName().endsWith(".c")))
		    	num = 0;
		    else
		     	num = textArea.getLineCount();
			 this.textArea.setText("行数："+Integer.toString(num) );//这里把这句移到这里,并输出words	 
	    }catch(Exception ex){
	 	// TODO Auto-generated catch block
	 		ex.printStackTrace();
	   }
	}
	/**
	 * -s指令操作
	 */
	
	private void  sActionPerformed(ActionEvent e, File f, int Perfomance){
		//test:    -s D:\英雄时刻		
		this.textField.setText("执行中......");
		StringBuilder builder = new StringBuilder("");
		File fileforc = null,fileforw = null,fileforl = null ,filefora = null;
		int Cnum=0,Wnum=0,Lnum=0,AEnum=0,ACnum=0, ANnum=0;
		int j=0;
		if(Perfomance == -1){
			 String xfileName[] = new String [4];
			 xfileName[0] = "C:"+"\\";
			 xfileName[1] = "D:"+"\\";
			 xfileName[2] = "E:"+"\\";
			 xfileName[3] = "F:"+"\\";		 		
	         int i=0;
	         for(String filestr : xfileName){
	 		     File file_s = new File(filestr);
	        	 print(file_s);
	         }
		}
		else print(f);//全局变量BigStr中获得符合条件的文件的绝对路径
		if(this.BigStr[0]==null)
			 textArea.setText("没有符合条件的文件!"+"\n");
		else
		{
			for(;j<this.BigStr.length;j++){	
				if(BigStr[j]!=null){
					builder.append(this.BigStr[j]);
					builder.append("\n");
				}
				else break;
			}
			if(Perfomance==-1)	{
				this.textField.setText("this is -s action");
				textArea.setText("所有目录中符合条件的文件有："+"\n"+builder.toString()+"\n"+"查找结束");
				refresh();
				}
			if(Perfomance==0)	{
				this.textField.setText("this is -s-c action");
				textArea.setText("所选目录中符合条件的文件有："+"\n"+builder.toString()+"\n"+"查找结束");
				refresh();
				}
		}
		
		if(Perfomance==1)
		 {
			//test:  -s-c D:\英雄时刻	 ||    -s-c  D:\forWCexeUsage
			this.textField.setText("执行中......");
			int k=0;
			for(;k<this.BigStr.length;k++){	
				if(this.BigStr[k]!=null){
					fileforc = new File(BigStr[k]);
					textArea.setText(readToString(fileforc));
					String str,newstr ;
					str = textArea.getText();
					newstr=str.replaceAll("\\s", "");				 
		            Cnum+=newstr.length();
				}
			}
			if(this.BigStr[0]==null){
				 textArea.setText("没有符合条件的文件!"+"\n");
				 this.textField.setText("this is -s-c action");
				 refresh();
			}
			else{
			     textArea.setText("当前目录及子目录中所有*.c 文件的字符数为："+Integer.toString(Cnum));
			     this.textField.setText("this is -s-c action");
			     refresh();
			}
		}
		
		if(Perfomance==2){
			//test:  -s-w D:\英雄时刻	 ||    -s-w D:\forWCexeUsage
			int m = 0;
			String str;
			for(;m<this.BigStr.length;m++){	
				if(this.BigStr[m]!=null){
					fileforw = new File(BigStr[m]);
				    textArea.setText(readToString(fileforw));
					str =textArea.getText();
				    String[] xx=str.split("[a-zA-Z]+");	
				    for(String a : xx){
				    	Wnum+=1;
				    }
				    Wnum-=1;	  
				}
			}
			if(this.BigStr[0]==null){
				 textArea.setText("没有符合条件的文件!"+"\n");
				 this.textField.setText("this is -s-w action");
				 refresh();
			}
			else{
				 this.textArea.setText("单词数："+Integer.toString(Wnum));//这里把这句移到这里,并输出words
				 this.textField.setText("this is -s-w action");
				 refresh();
			}
		}
		
		if(Perfomance==3){
			//test:  -s-l D:\英雄时刻	 ||    -s-l D:\forWCexeUsage
			int ii=0;
		for(;ii<this.BigStr.length;ii++){	
			if(this.BigStr[ii]!=null){
				fileforl = new File(BigStr[ii]);
			    textArea.setText(readToString(fileforl));
				Lnum += textArea.getLineCount();		        
			}
		}
		if(this.BigStr[0]==null){
			 textArea.setText("没有符合条件的文件!"+"\n");
			 this.textField.setText("this is -s-l action");
			 refresh();
		}
		else{
		     this.textArea.setText("当前目录及子目录中所有*.c 文件的行数："+Integer.toString(Lnum) );
		     this.textField.setText("this is -s-l action");
		     refresh();
		}
		}
		   
		if(Perfomance==4){	
			//test:  -s-a D:\英雄时刻	 ||    -s-a D:\forWCexeUsage
			int jj = 0;		 
			for(;jj<this.BigStr.length;jj++){	
				if(this.BigStr[jj]!=null){
					filefora = new File(BigStr[jj]);
					textArea.setText(readToString(filefora));
				    String str =textArea.getText();
					String newstr2,newstr1,Eregs,Cregs,Nregs ;
					Eregs = "\\p{Graph}?";
					Nregs = "\\p{Graph}?//";
					Cregs = "\\p{Graph}{2,}";  //注意此处注释行是会匹配代码行格式成功的，将在下面的if语句中做进一步限制
					newstr1=str.replace(" ", "");
					newstr2=newstr1.replace("\t", "");
				    String[] xx=newstr2.split("\r\n");	
				    for(String a : xx){
				    	if (a.matches(Eregs))
				    	  AEnum++;			    	
				    	else if (a.matches(Cregs)){
					    		 if (a.matches(Nregs))
							    	  ANnum++;
					    		 else 
					    	          ACnum++;
				    	}
				    	else ;
				    }
				}
			}  
			if(this.BigStr[0]==null){
				 textArea.setText("没有符合条件的文件!"+"\n");
				 this.textField.setText("this is -s-a action");
				 refresh();
			}
			else{
			     this.textArea.setText("当前目录及子目录中所有*.c 文件的空行数："  + Integer.toString(AEnum)  +"\n"
					   +               "当前目录及子目录中所有*.c 文件的注释行数：" + Integer.toString(ANnum)   +"\n"
					   +               "当前目录及子目录中所有*.c 文件的代码行数：" + Integer.toString(ACnum)          ) ;
			     this.textField.setText("this is -s-a action");
			     refresh();
			}
			}
		
	}

	/**
	 * -a指令操作
	 */
	private void  aActionPerformed(ActionEvent e, File file){
		//test:   wc.exe   -a   D:\WCtest.txt  
		this.textField.setText("this is c action");
		try{		 
			//test:   -a  D:\forWCexeUsage\scardsrv_i.c
			
			    int len, emptyline=0, codeline=0, noteline=0, Enum= 0, Cnum=0, Nnum=0;
			    textArea.setText(readToString(file));
			    String str =textArea.getText();
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
			   this.textArea.setText("空行数："  + Integer.toString(Enum)  +"\n"
			   +                     "注释行数：" + Integer.toString(Nnum)   +"\n"
			   +          "代码行数：" + Integer.toString(Cnum)          ) ;	 
			  }catch(Exception ex){
				// TODO Auto-generated catch block
					ex.printStackTrace();
			  }
	}
	/**
	 * -x指令操作
	 */
	private void  xActionPerformed(ActionEvent e){
		//test:   wc.exe   -a   D:\WCtest.txt  
		    new WC_x_frm().setVisible(true);
		//lActionPerformed(e);
		
	}
	/**
	 * 报错
	 */
	private void  invaildoperation(ActionEvent e){
		textField.setText("this is a invaild input");
	}
	private void  filenotfound(){
		textField.setText("系统找不到指定的.c类型文件。");

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
            filenotfound();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            filenotfound();
            return null;
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            filenotfound();
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 找文件
	 */
	private  void print(File f){
		//test:   wc.exe   -s   -c  
	if(f!=null){
	    if(f.isDirectory()){
	        File[] fileArray=f.listFiles();
	        if(fileArray!=null){
	            for (int i = 0; i < fileArray.length; i++) {
	                //递归调用            	
	                print(fileArray[i]);
	            }
	        }
	    }
	    else{
	        if (f.getName().endsWith(".c")){

	        System.out.println(f);        
	       this.BigStr[this.iforBigStr++]=f.getAbsolutePath();
	        }
	        else ;	
	       
	    }
	}


		}
	/**
	 * 
	 * 全局变量的刷新
	 */
	private void refresh(){
		this.iforBigStr = 0;
	}
	/**
	 * 
	 * 重置时间
	 */
	private void chongzhi(){
		textArea.setText("");
	}
}
