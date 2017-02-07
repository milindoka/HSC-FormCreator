package in.refort;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

public class PrintABreport 
{ 	////Basic 2 Functions For MVC Design---------------------------------------------------------------
	private static PrintABreport instance = null;  /* ===== singleton details ===== */
	protected PrintABreport() 
	{/* nothing needed, but this prevents apublic no-arg  constructor from being created automatically */}
	public static PrintABreport getInstance()
	{ if (instance==null)	{ instance = new PrintABreport();} 	return instance;}
      //------------------------------------------------------------------------------------------------

	public void LinkComponents(JTextComponent StudentsPerBatch,JTextComponent Subject,JTable tbl)
	  { this.StudentsPerBatch=StudentsPerBatch;
	    this.Subject=Subject;
	    this.table = tbl; 
	   
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

	
	
	public void SetPrinter(String printername)
	{
		PrinterName=printername;
	}
	public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}
	private JTable table;
      
	private JTextComponent Subject = null; ///maps to AppFrame
	private JTextComponent StudentsPerBatch=null;
	private int BatchStrength=24;	
	int TotalBatches;
	int LinesPerPage=25;
    int totalpages;	
    String PrinterName;
    
	public Object GetData(JTable table, int row_index, int col_index) {  return table.getModel().getValueAt(row_index, col_index); }
	
	
	private void Centre(String s, int width, int XPos, int YPos,Graphics g2d)
	  { int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
		int start = width/2 - stringLen/2; g2d.drawString(s, start + XPos, YPos);  
	   }

	  void CalculateTotalBatches()   ////and keep in TotalBatches
	  { int TotalStudents=table.getRowCount();
	    String spb=StudentsPerBatch.getText();
	    BatchStrength=Integer.parseInt(spb);
	    int remainder=TotalStudents%BatchStrength;
	    if (remainder>0) remainder=1; //even one student will create last batch
	    TotalBatches=TotalStudents/BatchStrength+remainder;  ////calculate Total Pages = Total Batches
		  
	  }

	  public void BoxedString(String str,int x,int y,Graphics gr)
	  {FontMetrics fontMetrics = gr.getFontMetrics();
      int width = fontMetrics.stringWidth(str);
      int fheight = fontMetrics.getHeight();
      gr.drawString(str, x, y);
      gr.drawRect(x - 2, y - fheight + 3, width + 4, fheight+2);
	  }
	  
	  
	  
public void CreateAndPrintABreport(Graphics gr,String [] BWA,int pgNo)
{
	  BoxedString("C-2/BR.P2-11",470,22,gr);
      ///////report created in local string array
	  int topleftx=70,toplefty=50;
	  int linespacing = 20;
	  Font MyFont = new Font("Liberation Serif", Font.PLAIN,10);
		 gr.setFont(MyFont);
	  //Font Times12 = new Font("Times New Roman", Font.PLAIN, 9);
	  //gr.setFont(Times12);
	   Centre("Maharashtra State Board of Secondary & Higher Secondary Education",460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre("Mumbai Divisional Board, Vashi, Navi Mumbai - 400 703",460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre("HSC Practical Exam. Feb-Mar 2015",460,topleftx,toplefty,gr);
	   gr.drawString("Practical Examination in Science Subject : "+Subject.getText(), 70 , 110);
	   gr.drawString("Name of the Junior College : SIWS College, Wadala    Index No : J 31.04.005", 70 , 130);
	   
	   toplefty=150;
	   int colwidArray[]={60,320,110} ;         /// total cols 3 = colwidArray.length
	   int TotalColumns=colwidArray.length;
	   int height=40;
	   int shifty=12;
	   ////////////Draw rectanlges for headings
	   for(int i=0;i<TotalColumns;i++)
	   {
	      gr.drawRect(topleftx,toplefty,colwidArray[i],height);
	      
	      topleftx=topleftx+colwidArray[i];
	   }
	  topleftx=70;
	  
	  Centre("Candidates",colwidArray[0],topleftx,toplefty+shifty,gr);
	  Centre("Per",colwidArray[0],topleftx,toplefty+2*shifty,gr); ///
	  Centre("Batch",colwidArray[0],topleftx,toplefty+3*shifty,gr); ///
	  // Draw rectangles for empty body of the tablecontent
	   
	 
	  Centre("Seat No. of Candidates who are ABSENT in the batch",colwidArray[1],topleftx+colwidArray[0],toplefty+2*shifty,gr); ///
	  
	  Centre("(Remarks if any)",colwidArray[2],topleftx+colwidArray[0]+colwidArray[1],toplefty+shifty,gr);
	  Centre("Out of turn",colwidArray[2],topleftx+colwidArray[0]+colwidArray[1],toplefty+2*shifty,gr); ///
	  Centre("Candidates's Seat No.",colwidArray[2],topleftx+colwidArray[0]+colwidArray[1],toplefty+3*shifty,gr); ///
	  
	  
	   toplefty=toplefty+height;   ///draw table re
	   topleftx=70; height=450;    /// with new  topleft point and height
	   for(int i=0;i<TotalColumns;i++)
	   {
	      gr.drawRect(topleftx,toplefty,colwidArray[i],height);
	      topleftx=topleftx+colwidArray[i];
	   }

	  ////Fill Headings 
	 topleftx=70;  //toplefty=150;///reset left starting point 
	  
	 toplefty=toplefty+15;
	 linespacing=17;
	 String tt;
   	 for(int i=0;i<LinesPerPage;i++) 
         {
   		    if(i+pgNo*LinesPerPage>=TotalBatches) break;
   		 
   		 tt=String.format("%02d] "+StudentsPerBatch.getText(),i+1+pgNo*LinesPerPage);   		 
   		  
   		    Centre(tt,colwidArray[0],topleftx,toplefty,gr);
   		    if(BWA[i+pgNo*LinesPerPage].isEmpty())
   		    	gr.drawString("----",topleftx+colwidArray[0]+6,toplefty);
   		    else
   		    gr.drawString(BWA[i+pgNo*LinesPerPage],topleftx+colwidArray[0]+6,toplefty);
            toplefty+=linespacing;
         }    	  
        	 
   	topleftx=70;toplefty=780; 
   	String para="Seat No.s of the candidates appearing from other Jr. College if any, should be mentioned ";
   	para+="under the column giving his/her full details i.e Name of the institution, Seat No. (letter from  ";
	para+="the principal granting permission to appear as out of turn candidates is to be attached with the ";
	para+="mark list).";
   	PrintFormattedPara(para,topleftx,toplefty,490,MyFont,gr);
   	 
}



///// 0 - practical    1-oral    2-project 




public void PrintAbsenteeReport()
{  
	  CalculateTotalBatches();
	  
	  PrintService ps = findPrintService(PrinterName);
	  int rc=table.getRowCount();
	  final String[] BatchwiseABarray = new String[TotalBatches];
	  
	  for(int i=0;i<TotalBatches;i++) BatchwiseABarray[i]="";
	  
	  Boolean temp=false;
	  String tempseat;
		for(int i=0;i<rc;i++)
		   { 
			 temp=(Boolean) GetData(table,i,2);
			 if(temp==true) 
				 { tempseat = (String) GetData(table,i,1);
				   if(BatchwiseABarray[i/BatchStrength].isEmpty()) 
					   BatchwiseABarray[i/BatchStrength]=tempseat;
				   else
	             BatchwiseABarray[i/BatchStrength]+=", "+tempseat;
				 }
		   }
		totalpages=TotalBatches/LinesPerPage;
	    if(TotalBatches%LinesPerPage!=0) totalpages++;
	
	try {
	      PrinterJob pjob = PrinterJob.getPrinterJob();
	      pjob.setPrintService(ps);
	      pjob.setJobName("Graphics Demo Printout");
	      pjob.setCopies(1);
	      pjob.setPrintable(new Printable() {
	        public int print(Graphics pg, PageFormat pf, int pageNum)
	        {
	          if (pageNum<totalpages) // we only print one page
	          { CreateAndPrintABreport(pg, BatchwiseABarray,pageNum); 
	            return Printable.PAGE_EXISTS; // ie., end of job
	          }
	          
	          else
	          {
	           return Printable.NO_SUCH_PAGE;
	          }
	        }
	      });
	      HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
          pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
          // 210 x 297  A4 size paper
          pjob.print(pattribs);	      
	      
	    } catch (PrinterException pe) {
	      pe.printStackTrace();
	    }

 	
} ///end of Print Absentee Report   


public void PrintFormattedPara(String para,int topleftx,int toplefty,int width,Font font,Graphics gr)
{  	FontMetrics metrics = gr.getFontMetrics(font);
    int defaultspacing=3;
  	String[] result = para.split("\\s");
  	int totalwords=result.length;
  	//int NonSpaceExtent=0;
  	ArrayList<String> line = new ArrayList<String>();
 // 	mylist.add(mystring); //this adds an element to the list.
    int extent=0;
    String templine="";
  	for (int x=0; x<totalwords; x++)
  	    { extent = extent + metrics.stringWidth(result[x]);
  	      if(extent>width) { line.add(templine); templine=result[x] + ' '; extent=0; continue;}
  	      if((extent+defaultspacing)>width) { line.add(templine); templine=result[x]+' '; extent=0; continue;}
  	      templine=templine+result[x]+' ';
  	      extent=extent+defaultspacing;
  	    }
  	line.add(templine);
  	int LineTotal=line.size();
  	
  	int linespacing=15;
  	for(int i=0;i<LineTotal-1;i++)
  	    { String tempstr=line.get(i);
  	      tempstr.trim();
  	      PrintPara(tempstr,topleftx,toplefty,width,font,gr);
  	      toplefty=toplefty+linespacing;
  	    }
    gr.drawString(line.get(LineTotal-1),topleftx,toplefty);
  	
}  
	  
	  

public void PrintPara(String oneline,int topleftx,int toplefty,int width,Font font,Graphics gr)
{   
    //int defaultspacing=14;   ////default word spacing
	FontMetrics metrics = gr.getFontMetrics(font);
  	String[] result = oneline.split("\\s");
  	int totalwords=result.length;
  	int NonSpaceExtent=0;
   for (int x=0; x<totalwords; x++)
          NonSpaceExtent=NonSpaceExtent+ metrics.stringWidth(result[x]);
    int spaceleft=width-NonSpaceExtent;
    int averagegap=spaceleft/(totalwords-1);
    int remainingpixels=spaceleft%(totalwords-1);
    
    
    for (int x=0; x<totalwords; x++)
   	   { gr.drawString(result[x],topleftx,toplefty);
   	     topleftx=topleftx+metrics.stringWidth(result[x])+averagegap;
   	     if(remainingpixels>0) { topleftx++; remainingpixels--; }
   	   }
    

	
}
	  
	
}
