package in.refort;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.Preferences;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

public class AppPrint 
{ 
	////Basic 2 Function For MVC Design -----------------------------------------------------------------------
  private static AppPrint instance = null;  /* ===== singleton details ===== */  
  protected AppPrint() {/* Prevents constructor from being created automatically.*/}
  public static AppPrint getInstance() { if (instance==null)	{ instance = new AppPrint();} 	return instance;}
	////--------------------------------------------------------------------------------------------------------
  
  public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}
  
  
  //public enum FORM { PRACT, ORAL ,PROJE	} ;
  
  //int FORMTYPE; form;
	
	String PrinterName="Not Set"; 	
  int PractGridWidths[]={40,100,100,208};                                 /// total cols 4 = colwidArray.length
  String PractGridTitles[]={"Sr. No.","Seat No.","Session","Student's Signature"};
  
  int OralGridWidths[]={50,100,210,100};
  String OralGridTitles[]={"Sr. No.","Seat No.","Name of the Student","Student's Signature"};
  
  //int CollegeIndexNumberColwidArray[]={15,15,15,15,15,15,15,15,15,15}; /// total cols 10 = CollegeIndexNumberColwidArray.length
  
  private JTable tablePri;
  private JTable table2;
    
  private JTextComponent Subject = null; ///maps to AppFrame
  private JTextComponent StudentsPerBatch=null;
  private int BatchStrength=24;	
//  String[] BatchwiseABarray = new String[60];   ///??
  int TotalBatches;
  int totalpages;
  int LinesPerPage=32;
  Font Times12 = new Font("Liberation Serif", Font.PLAIN,10);  
  Font Times14 = new Font("Liberation Serif", Font.PLAIN,10);
///----------------------------common header strings-------------------------------------
	private String FormNo="C-2/BR.P.2-21";
	private String SheetType= "ATTENDANCE SHEET";
	private String BoardName1="Maharashtra State Board of Secondary & Higher Secondary Education,";
	private String BoardName2="Mumbai Divisional Board, Vashi, Navi Mumbai - 400703";
	private String Standard  = "HSC ";	
    private String CollegeCentre="SIWS College, Wadala";
    private String ExamType  = "Practical Exam";
    private String MonthYear = "FEB/MAR 2016";
    String IndexNumber="J-31-04-005";
    
/////----------------------------------------------------------------------------------------------
  
    public void BoxedString(String str,int x,int y,Graphics gr)
	  {FontMetrics fontMetrics = gr.getFontMetrics();
    int width = fontMetrics.stringWidth(str);
    int fheight = fontMetrics.getHeight();
    gr.drawString(str, x, y);
    gr.drawRect(x - 2, y - fheight + 3, width + 4, fheight+2);
	  }
	
  
  public void LinkComponents(JTextComponent StudentsPerBatch,JTextComponent Subject,JTable tbl,JTable tbl2)
  { 
    this.Subject=Subject;
    this.tablePri = tbl; 
    this.table2=tbl2;
    this.StudentsPerBatch=StudentsPerBatch;
    
   
    String MM = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()); //Jan 01, Feb 02 ..
    String YYYY = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
    if (MM.contains("01") || MM.contains("02") || MM.contains("03")) MonthYear="Feb/Mar-"+YYYY; else MonthYear="Oct-"+YYYY;
    
   }
  
  public Object GetData(JTable table, int row_index, int col_index) {  return table.getModel().getValueAt(row_index, col_index); }
  //public Object GetData2(JTable table, int row_index, int col_index) {  return table.getModel().getValueAt(row_index, col_index); }
	
  
  void CalculateTotalBatches()
  { int TotalStudents=tablePri.getRowCount();
    String spb=StudentsPerBatch.getText();
    BatchStrength=Integer.parseInt(spb);
    int remainder=TotalStudents%BatchStrength;
    if (remainder>0) remainder=1; //even one student will create last batch
    TotalBatches=TotalStudents/BatchStrength+remainder;  ////calculate Total Pages = Total Batches
	  
  }
  
  private void Centre(String s, int width, int XPos, int YPos,Graphics g2d)
  { int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
	int start = width/2 - stringLen/2; g2d.drawString(s, start + XPos, YPos);  
   } 
  
  public void PrintTimeTable(Graphics GR,int pgno)
  {GR.setFont(Times14);

  int topleftx=74,toplefty=10;
  int linespacing = 20;
  
  Centre("SIWS COLLEGE, WADALA",460,topleftx,toplefty,GR);
  toplefty+=linespacing;
  Centre("HSC PRACTICAL EXAMINATION - FEB- 2015 - SCIENCE",460,topleftx,toplefty,GR);
  toplefty+=linespacing;
  Centre("BATCHWISE TIME TABLE",460,topleftx,toplefty,GR);
  
   int TableWidth[]={58,38,122,42,152,32};
   int TotalColumns=TableWidth.length;
   int shifty=15;
   String tempData="";
  
   int x=74, y=toplefty+linespacing,height=20;
   
   String TableTitle[]={"Date","Day","Time","Batch","Seat Numbers","Room"};
  
  
   for(int i=0;i<TotalColumns;i++)
          { GR.drawRect(x,y,TableWidth[i],height);
           Centre(TableTitle[i],TableWidth[i],x,y+shifty,GR);
            x=x+TableWidth[i];
          }
 
 // CalculateTotalBatches(); 
 x=74;y=y+23;height=20;
 int y1=y;
 for(int i=0;i<TotalColumns;i++)
  {y=y1;
	 for(int j=0;j<LinesPerPage;j++) 
       {  GR.drawRect(x,y,TableWidth[i],height);
          if(j+LinesPerPage*pgno<TotalBatches) tempData=(String) GetData(table2,j+LinesPerPage*pgno,i+1);
          else break;
         
         Centre(tempData,TableWidth[i],x,y+shifty,GR);
          y=y+height;
       }
    x=x+TableWidth[i]; 
  }
  
  GR.drawString("Maths Dept",74,y+50);   GR.drawString("Principal/Vice Principal",370, y+50); 
  }
  public void Print2()
  {   CalculateTotalBatches();
      PrintService ps = findPrintService(PrinterName);
      totalpages=TotalBatches/LinesPerPage;
      if(TotalBatches%LinesPerPage!=0) totalpages++;
	  String spb=StudentsPerBatch.getText();
	  BatchStrength=Integer.parseInt(spb);
		try {
            PrinterJob pjob = PrinterJob.getPrinterJob();
            pjob.setPrintService(ps);
            
            pjob.setJobName("HSC Practical Time Table");
            pjob.setCopies(1);
            pjob.setPrintable
            (   new Printable() 
                   {  public int print(Graphics gr, PageFormat pf, int pageNum) 
                      { if (pageNum < totalpages) 
        	              {  PrintTimeTable(gr,pageNum);   return PAGE_EXISTS; } 
        	              else { return NO_SUCH_PAGE; }      
                      }
                    }
            );

            HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
            pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
            // 210 x 297  A4 size paper
            pjob.print(pattribs);
            
           
           // if (pjob.printDialog() == false)  return;   // choose printer
           // pjob.print(); 
        } 
  	    catch (PrinterException pe) 
  	    { pe.printStackTrace();
          }
  }
  
  public void PrintFormHeader(Graphics gr,int pageno)
  { 
	  
  int rc=tablePri.getRowCount();
  int topSeatIndex=pageno*BatchStrength;   
  String StartSeatNo,EndSeatNo;
  StartSeatNo = (String) GetData(tablePri,topSeatIndex,1);
  if(topSeatIndex+BatchStrength-1<rc) 
	  EndSeatNo = (String) GetData(tablePri,topSeatIndex+BatchStrength-1,1);
  else   ///This is the last batch
	  EndSeatNo = (String) GetData(tablePri,rc-1,1);
   String PAGENO=String.format("%02d", pageno+1);
   int topleftx=74,toplefty=10;
   int linespacing = 16;
   gr.setFont(Times14);
   //BoxedString(FormNo,470,24,gr);
   Centre(BoardName1,460,topleftx,toplefty,gr);
   toplefty+=linespacing;
   Centre(BoardName2,460,topleftx,toplefty,gr);
   toplefty+=linespacing;
   Centre(Standard+ ExamType+" - "+MonthYear,460,topleftx,toplefty,gr);
   toplefty+=linespacing;
   Centre(SheetType,460,topleftx,toplefty,gr);
   toplefty+=linespacing;
  linespacing=12;
   gr.setFont(Times12); // linespacing=15;
   gr.drawString("School/College/Centre : "+CollegeCentre,topleftx,toplefty);
  gr.drawString("Batch No :  "+PAGENO,topleftx+340,toplefty);
  toplefty+=linespacing;
  gr.drawString("Subject : "+Subject.getText(),topleftx,toplefty);
  String Det=(String) GetData(table2,pageno,1);
  gr.drawString("Date :  "+Det,topleftx+340,toplefty);
  toplefty+=linespacing;
  gr.drawString("Seat No.s : From  "+StartSeatNo+"  TO  "+EndSeatNo,topleftx,toplefty);
  String timing=(String) GetData(table2,pageno,3);
  gr.drawString("Time :  "+timing,topleftx+340,toplefty);
  toplefty+=linespacing;
  gr.drawString("Extra Seat No.s :",topleftx,toplefty);
  }
  
  public void Grid(int px, int py,int nRows,int WIDTH[], int height, Graphics GR,int pageno,String titles[],int AttendanceType)
  {GR.setFont(Times12);
   int j,shifty=12; String tempSeatNo;
   int topSeatIndex=pageno*BatchStrength; 
   int rc=tablePri.getRowCount();
   int cols=WIDTH.length;
   PrintFormHeader(GR,pageno);
   
  int x=px;
   for(j=0;j<cols;j++)
    { GR.drawRect(x,py-height,WIDTH[j],height);
      if(j==0) Centre(titles[j],WIDTH[j],x,py-height+shifty,GR);
      if(j==1) Centre(titles[j],WIDTH[j],x,py-height+shifty,GR);
      if(j==2) Centre(titles[j],WIDTH[j],x,py-height+shifty,GR);
      if(j==3) Centre(titles[j],WIDTH[j],x,py-height+shifty,GR);
      x=x+WIDTH[j];
    }
   
   for(j=0;j<cols;j++)
   {   int y=py;
       for (int i=0;i<nRows;i++)
         {GR.drawRect(px,y,WIDTH[j],height);
          switch(j)
          { case 0 :   if(i<BatchStrength && topSeatIndex+i<rc) 
	                    {   String tt=String.format("%d",i+1);
	                       Centre(tt,WIDTH[j],px,y+shifty,GR);           
                         } break;
            case 1 :
            	      if(i<BatchStrength && topSeatIndex+i<rc) 
            	      { tempSeatNo = (String) GetData(tablePri,topSeatIndex+i,1);
                      Centre(tempSeatNo,WIDTH[j],px,y+shifty,GR);           
                      } break;
                      
            case 2 : if (AttendanceType>0) break;    ////// There is no session in Oral or Proje
            	     if(i<BatchStrength && topSeatIndex+i<rc)
            	       {String temp=(String) GetData(table2,pageno,7);
            	      //  show(temp);
            	        Centre(temp,WIDTH[j],px,y+shifty,GR);
            	       
            	       }
                       break;  
                      
             default : ;
          }
                    
         y=y+height; 
         }
         
          px=px+WIDTH[j];
     }
   
   PrintAttendanceFooter(GR,AttendanceType);
  }
  
  
  public void SavePreferences()
		{Preferences prefs = Preferences.userNodeForPackage(in.refort.AppFrame.class);
		// Preference key name
		final String PREF_NAME = "HFCPref";
		// Set the value of the preference
		prefs.put(PREF_NAME, PrinterName);
			
		}


		public  void LoadPreferences()
		{Preferences prefs = Preferences.userNodeForPackage(in.refort.AppFrame.class);

		// Preference key name
		final String PREF_NAME = "HFCPref";
		PrinterName= prefs.get(PREF_NAME,PrinterName); // "a string"
				
		}

  
  
  
  
  public void SelectPrinter()
  {   LoadPreferences();
  	//show(PrinterName);
  	ButtonGroup group = new ButtonGroup();
      ArrayList<String> PrinterNames = new ArrayList<String>(); 
  	
  	for (PrintService service : PrinterJob.lookupPrintServices())
      {
          PrinterNames.add(service.getName());
             
      }	    	
  	JRadioButton buttons[] = new JRadioButton[PrinterNames.size()];
  	
  	for (int i = 0; i < buttons.length; ++i)
  	{
  		buttons[i] = new JRadioButton(PrinterNames.get(i));
  	 //   btn.addActionListener(this);
  	    group.add(buttons[i]);
  	    //buttons[i] = btn;
  	}
  	
      int res = JOptionPane.showConfirmDialog(null, buttons, "Select Default Printer", 
              JOptionPane.OK_CANCEL_OPTION);

      if (res == JOptionPane.OK_OPTION)
            { for(int i=0;i<PrinterNames.size();i++) 
          	  if(buttons[i].isSelected()) { PrinterName=PrinterNames.get(i);
          	                                SavePreferences();
          	                                
          	                                }
            }

      
  }
  
  public void PrintAttendanceFooter(Graphics gr,int AttendanceType)
  {int xlocation=74,ylocation=800,spacing=16;
  if(AttendanceType==0) gr.drawString("Internal Examiner",xlocation,ylocation);
  else
	  gr.drawString("Supervisor/Teacher's Name",xlocation,ylocation);
  gr.drawString("Signature of Head of the Jr College",370, ylocation);
  gr.drawString("Name & Signature",xlocation,ylocation+spacing-5);  
  gr.drawString("with Rubber Stamp",400,ylocation+spacing-5);
  gr.drawString("Note :",xlocation,ylocation+3*spacing-20);   
  gr.drawString("1. Submit to Board with Practical Marksheet.   2. Write ABSENT with Red Ink   3. Write Extra No.s if any.",105,ylocation+3*spacing-20);
 // gr.drawString("2. For Absent students, write ABSENT in signature column with RED INK.",100, 777);
 // gr.drawString("3. Please write Additional/Extra seat numbers, if any.",100, 789);
	  
  }
  
  
  
  public PrintService findPrintService(String printerName)
  {
      for (PrintService service : PrinterJob.lookupPrintServices())
      {
          if (service.getName().equalsIgnoreCase(printerName))
              return service;
      }

      return null;
  }

  static private PageFormat getMinimumMarginPageFormat(PrinterJob printJob) {
	    PageFormat pf0 = printJob.defaultPage();
	    PageFormat pf1 = (PageFormat) pf0.clone();
	    Paper p = pf0.getPaper();
	    p.setImageableArea(0, 0,pf0.getWidth(), pf0.getHeight());
	    pf1.setPaper(p);
	    PageFormat pf2 = printJob.validatePage(pf1);
	    return pf2;     
	}


  
  
  
  //////////
  public void PrintAttendance(final int AttendanceType)
    {   String spb=StudentsPerBatch.getText();
	    BatchStrength=Integer.parseInt(spb);
    	  
	    PrintService ps = findPrintService(PrinterName);                                    
        //create a printerJob
        //PrinterJob job = PrinterJob.getPrinterJob();            
        //set the printService found (should be tested)
         
	    
	    
    	CalculateTotalBatches();
    	
    	try {
              PrinterJob pjob = PrinterJob.getPrinterJob();
              
              
             PageFormat pf= getMinimumMarginPageFormat(pjob);
              
              try {
      			pjob.setPrintService(ps);
      		} catch (PrinterException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}    
              
           //   show(AttendanceType);
              switch(AttendanceType)
              {case 0 : ExamType="Practical Exam";break;
               case 1 : ExamType="Oral Exam";break;
               case 2 : ExamType="Project Exam"; break;
              }
              
              
             // show(ExamType);
              pjob.setJobName("HSC Practical Attendance Sheets");
              
              pjob.setCopies(1);
              pjob.setPrintable
              (   new Printable() 
                     {  public int print(Graphics gr, PageFormat pf, int pageNum) 
                        { if (pageNum < TotalBatches) 
          	              {  
                        	
                        	switch(AttendanceType)
                        	{ case 0: Grid(74,140,32,PractGridWidths,19,gr,pageNum,PractGridTitles,AttendanceType); break;
                        	  case 1:
                        	  case 2: Grid(74,140,30,OralGridWidths,20,gr,pageNum,OralGridTitles,AttendanceType); break;
                        	 
                        	} 
                        	return PAGE_EXISTS;
          	              }
                        	
                        	else { return NO_SUCH_PAGE; }      
                        }
                      }
              );

              HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
              pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
              // 210 x 297  A4 size paper
              pjob.print(pattribs);
              
              
             // if (pjob.printDialog() == false)  return;   // choose printer
             // pjob.print(); 
          } 
    	    catch (PrinterException pe) 
    	    { pe.printStackTrace();
            }
     } ///end of Print Attendance   



}    ////End of Class AppPrint



