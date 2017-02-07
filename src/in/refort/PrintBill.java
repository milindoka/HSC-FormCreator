package in.refort;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.text.JTextComponent;

public class PrintBill 
{
	////Basic 2 Functions For MVC Design---------------------------------------------------------------
	private static PrintBill instance = null;  /* ===== singleton details ===== */
	protected PrintBill() 
	{/* nothing needed, but this prevents a public no-arg  constructor from being created automatically */}
	public static PrintBill getInstance()
	{ if (instance==null)	{ instance = new PrintBill();} 	return instance;}
      //------------------------------------------------------------------------------------------------
    int StudCount;
	private String CollegeName;
    private String CollegeIndex;
    private JTextComponent Subject;
    String name,add1,add2,add3,add4,POP;    
    
    public void setNameAddress(String name, String add1, String add2,String add3, String add4,String POP) 
    {	this.name=name;this.add1=add1;this.add2=add2;this.add3=add3;this.add4=add4; this.POP=POP;
    }

    
    public void SetStudCount(int Count)
    { StudCount=Count; }
	public void LinkComponents(String CollegeName,String CollegeIndex,JTextComponent Subject)
	{this.CollegeName=CollegeName; this.CollegeIndex=CollegeIndex;  this.Subject=Subject;
 	}
	
		
public void Print()
{
	 try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
	      pjob.setJobName("Graphics Demo Printout");
	      pjob.setCopies(1);
	      pjob.setPrintable(new Printable() {
	        public int print(Graphics pg, PageFormat pf, int pageNum) {
	          if (pageNum > 0) // we only print one page
	            return Printable.NO_SUCH_PAGE; // ie., end of job
	          Font newFont;
	          newFont = new Font("Times New Roman", Font.BOLD, 12);
	          int x = 60;   // Left margin indent.
	          int y = 565;  // Right margin indent.
	          pg.drawString("C-2/P2-13", 505, 40);
	          pg.drawRect(503, 28, 60, 15);
	          pg.drawRect(90, 584, 65, 65);  //  coordinates of left top corner and width and height
	         	          
	          pg.setFont(newFont);
	          pg.drawString("MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION", x, 60);
	          pg.drawString("MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI  400703", 140, 77);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 13);
	          pg.setFont(newFont);
	          pg.drawString("H.S.C. PRACTICAL EXAMINATION FEBRUARY/OCTOBER  2015", 125, 95);
	          pg.drawString("BILL OF REMUNERATION OF INTERNAL EXAMINER", 158, 114);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 12);
	          pg.setFont(newFont);
	      
	          String address[] = {"The Divisional Secretary,","Maharashtra State Board of Secondary","& Higher Secondary Education",
	        		              "Mumbai Divisional Board,", "Vashi, Navi Mumbai  400703"};
	          int k = 140;
	          for( int i = 0; i < address.length; i++)
	          {
	            pg.drawString( address[i], x, k);
	            k = k+15;
	          }
	          pg.drawString("Name Shri/Smt/Kum", 60, 220);
	          pg.drawString(name, 170, 220);
	          pg.drawLine(170, 222, y, 222); 
	         
	          pg.drawString("Subject", 275, 245);
	          pg.drawString(Subject.getText(),325,245);
	          pg.drawLine(320, 247, y, 247); 
	          
	          pg.drawString("Practical Examination February/October,  2015", x, 265);
	          pg.drawString("at the", x, 285);
	          pg.drawLine(95, 287, 320, 287);
	          pg.drawString("Index No. of Jr. College ", 325, 285);
	          pg.drawLine(450, 287, y, 287);
	          
	          newFont = new Font("Times New Roman", Font.BOLD, 12);
	          pg.setFont(newFont);
	          pg.drawString(CollegeIndex, 452, 285);
	          pg.drawString(CollegeIndex, 182, 700);
	          pg.drawString(CollegeName, 100, 285);
	          pg.drawString(CollegeName, 252, 680);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 10);
	          pg.setFont(newFont);
	          pg.drawString("(In capital letters)", 72, 230);
	          pg.drawString("(Place ofexamination)", 130, 300);
	          pg.drawString("(Signature)", 420, 555);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 12);
	          pg.setFont(newFont);
	          pg.drawString("Received Payment", 80, 580);
	          pg.drawString("Full Postal", 230, 580);
	          pg.drawString("Residential Address", 210, 590);
	          pg.drawString("Signature of payee", 80, 660);
	          pg.drawString("( Name of Jr. College where teaching )", x, 680);
	          pg.drawString("Index No. ofJr. College", x, 700);
	          
	          pg.drawLine(x, 310, y, 310);        //  Horizontal Line of calculation table   
	          pg.drawLine(x, 330, y, 330);        //  Horizontal Line of calculation table
	          pg.drawLine(x, 465, y, 465);        //  Horizontal Line of calculation table   
	          pg.drawLine(x, 485, y, 485);        //  Horizontal Line of calculation table
	          pg.drawLine(325, 540, y, 540);      //  Horizontal Line above signature
	          pg.drawLine(400, 310, 400, 485);    //  Vertical Line of calculation table
	          
	          pg.drawString(add1, 325+5,582-5);
	          pg.drawLine(325, 582, y, 582);      //  Horizontal Line of Address line 1
	          pg.drawString(add2, 325+5,602-5);
	          pg.drawLine(325, 602, y, 602);      //  Horizontal Line of Address line 2
	          pg.drawString(add3, 325+5,622-5);
	          pg.drawLine(325, 622, y, 622);      //  Horizontal Line of Address line 3
	          pg.drawString(add4, 325+5,642-5);
	          pg.drawLine(325, 642, y, 642);      //  Horizontal Line of Address line 4
	          pg.drawLine(250, 683, 430, 683);      //  Horizontal Line of after Address line 3
	          pg.drawLine(180, 701, 250, 701);    //  Horizontal Line last line
	          
	          newFont = new Font("Times New Roman", Font.BOLD, 14);
	          pg.setFont(newFont);
	          pg.drawString("Particulars", 190, 325);
	          pg.drawString("Amount", 450, 325);
	          pg.drawString("Total Amount", 180, 480);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 12);
	          pg.setFont(newFont);
	          pg.drawString("Amount due to me as an INTERNAL examiner at the", x, 345);
	          pg.drawString("Examination Centre Date/s of Examination", x, 365);  
	          pg.drawLine(275, 367, 400, 367);
	          pg.drawString("Actual Total No. of  Candidates examined", x, 385);
	          pg.drawString("by me excluding absentees", 130, 405);
	          

	          String tt=String.format("Rs %.2f",StudCount*3.5);
	          String nn=String.format("%d x 3.5",StudCount);
	         
	          pg.drawString(nn, 280,402);
	          pg.drawString(tt, 455,350);
	          pg.drawLine(275, 407, 400, 407);
	     //   pg.drawLine(565, 50, 565, 400);   // Right margin testing vertical line
	          pg.drawString("Rs.3.5/- per candidate", x, 425);
	          pg.drawString("( Minimum of Rs. 50/- irrespective of the number of candidates )", x, 445);
	          pg.drawString("I hereby undertake to refund any amount paid to me in excess of the amount due", x, 500);
	          pg.drawString("certified that the Examiner has actually examined the No. of candidates mentioned above", x, 715);
	          pg.drawString("counter signature of the Head of the institution with stamp", x, 730);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 8);
	          pg.setFont(newFont);
	          String revenue[] = {"On revenue stamp","where the amount","exceeds Rs.5000/-"};
	          int p = 605;
	          for( int i = 0; i < revenue.length; i++)
	          {
	            pg.drawString(revenue[i], x+33, p);
	            p = p+15;
	          }
	          return Printable.PAGE_EXISTS;
	        }
	      });

	      if (pjob.printDialog() == false) // choose printer
	        return; 
	      pjob.print(); 
	    } 
	      catch (PrinterException pe) {
	      pe.printStackTrace();
	    }
	  }	  

public void Print1()
{
	 try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
	      pjob.setJobName("Graphics Demo Printout");
	      pjob.setCopies(1);
	      pjob.setPrintable(new Printable() {
	        public int print(Graphics pg, PageFormat pf, int pageNum) {
	          if (pageNum > 0) // we only print one page
	            return Printable.NO_SUCH_PAGE; // ie., end of job
	          Font newFont;
	          newFont = new Font("Times New Roman", Font.BOLD, 13);
	          int x = 60;   // Left margin indent.
	          int y = 550;  // Right margin indent.
	     //     pg.drawLine(550, 50, 550, 400);   // Right margin testing vertical line
	          pg.drawString("( FOR OFFICE USE ONLY )", 230, 60);
	          
	          newFont = new Font("Times New Roman", Font.BOLD, 12);
	          pg.setFont(newFont);
	          pg.drawString("For Practical Branch", x, 80); 
	          pg.drawLine(x, 82, 168, 82);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 12);
	          pg.setFont(newFont);
	          pg.drawString("Certified that the afore said details have been verified from the office records & found correct, the bill", x, 100);
	          pg.drawString("is released for payment.", x, 120);
	          pg.drawString("1) Signature of the dealing clerk", x+20, 140);  pg.drawString("Date", x+350, 140);
	          pg.drawString("2) Head of the H.S.C. Brabch", x+20, 160);       pg.drawString("Date", x+350, 160);
	          pg.drawLine(240, 142, 400, 142);        //  Horizontal Line - Signature of clerk   
	          pg.drawLine(440, 142, 550, 142);        //  Horizontal Line in front of date
	          pg.drawLine(240, 162, 400, 162);        //  Horizontal Line - Head ofHSC Branch   
	          pg.drawLine(440, 162, 550, 162);        //  Horizontal Line
	     	          
	          newFont = new Font("Times New Roman", Font.BOLD, 12);
	          pg.setFont(newFont);
	          pg.drawString("For Accounts Branch", x, 190); 
	          pg.drawLine(x, 192, 170, 192);
	          
	          newFont = new Font("Times New Roman", Font.PLAIN, 12);  
	          pg.setFont(newFont);
	          pg.drawString("Passed for payement of Rs.", x, 210);  pg.drawString("Rupees ", x+210, 210);
	          pg.drawLine(195, 212, 265, 212);        //  Horizontal Line - Rs.   
	          pg.drawLine(310, 212, y, 212);        //  Horizontal Line - Rupees
	          pg.drawString("Signature of dealing clerk", x, 240);
	          pg.drawLine(195, 242, 300, 242); 
	          pg.drawString("Accountant", x, 270);
	          pg.drawLine(120, 272, 300, 272); 
	          pg.drawString("For Divisional Secretary,", 430, 300);
	          pg.drawString("Maharashtra State Board of Secondary", 365, 320);
	          pg.drawString("& Higher Secondary Education", 400, 340);
	          pg.drawString("Mumbai Divisional Board,", 422, 360);
	          pg.drawString("Vashi, Navi Mumbai  400703", 408, 380);
	          pg.drawLine(410, 381, y, 381); 
	          
	          return Printable.PAGE_EXISTS;
	        }
	      });

	      if (pjob.printDialog() == false) // choose printer
	        return; 
	      pjob.print(); 
	    }
	      catch (PrinterException pe) {
	      pe.printStackTrace();
	    }
	  }	



}


