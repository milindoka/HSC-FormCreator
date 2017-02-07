package in.refort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class AppController
{ 	
    //private static boolean bModified=false;	
	private JTextComponent FrollField = null; ///maps to AppFrame FrollField 
	private JTextComponent LrollField = null; ///maps to AppFrame LrollField
	private JTextComponent DateField = null; ///maps to AppFrame LrollField
    private JTable tableCon,table2,table3;    
    private JLabel total;
    
    private JTextComponent StudentsPerBatch;
       
    public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}
    
	
	////Basic 2 Functions For MVC Design---------------------------------------------------------------
	private static AppController instance = null;  /* ===== singleton details ===== */
	protected AppController() 
	{/* nothing needed, but this prevents a public no-arg  constructor from being created automatically */}
	public static AppController getInstance()
	{ if (instance==null)	{ instance = new AppController();} 	return instance;}
      //------------------------------------------------------------------------------------------------
	
	
	
	public void LinkComponents(JTextComponent frollfield,JTextComponent lrollfield,JTextComponent studentsperbatch,
			                   JTextComponent datefield,JTable table,JTable table2,JTable table3,JLabel total) 
	{this.FrollField = frollfield;	this.LrollField = lrollfield;	this.StudentsPerBatch=studentsperbatch; 
	 this.DateField=datefield;this.tableCon = table; this.table2 = table2;this.table3=table3;this.total=total;
	 }
	
	
	 public void SetData(Object obj, int row_index, int col_index)    {  tableCon.getModel().setValueAt(obj,row_index,col_index);  }
	 public void SetData2(Object obj, int row_index, int col_index)    {  table2.getModel().setValueAt(obj,row_index,col_index);  }
	 public void SetData3(Object obj, int row_index, int col_index)    {  table3.getModel().setValueAt(obj,row_index,col_index);  }
	 
	 public void handleDeleteAction()
	 {
	 int rc=tableCon.getRowCount();
     if (rc<9) return;
     int temprow=tableCon.getSelectedRow();
     int tempcol=tableCon.getSelectedColumn();
      if(temprow>=0) ((DefaultTableModel) tableCon.getModel()).removeRow(temprow);
     
      String temp=(String) GetData(tableCon,0,1);                    FrollField.setText(temp);
      temp=(String) GetData(tableCon,tableCon.getRowCount()-1,1);      LrollField.setText(temp);
      if(temprow==tableCon.getRowCount()) temprow--;
      tableCon.changeSelection(temprow, tempcol,false,false);
      UpdateBatch();
      tableCon.updateUI();
    //  String tt=String.format(" Total : %d",tableCon.getRowCount());
    //  total.setText(tt);
      tableCon.changeSelection(temprow, temprow, true, true);
	 }
	 
	 public void onb1()
	 {  /*
		 Boolean temp=false;
		for(int i=0;i<10;i++)
		   { 
			 temp=(Boolean) GetData(tableCon,i,2);
			 if(temp==true) show("true"); else show("false");
		   }
		   
		   */
		 
		 show("Not Assigned");
		 //ResizeTable(tableCon,10);
	 };
	 
	
	 public void handleCreateAction() 
	{ String froll=FrollField.getText();  
	  String lroll=LrollField.getText();
	  String spb=StudentsPerBatch.getText();
	  int BatchStrength=Integer.parseInt(spb);
	
	  String[] temp = new String[2000];
	  boolean found=false;
	  int i;
	  temp[0]=froll;
	  String buff=froll;
	    for(i=1;i<2000;i++)
	    	{ temp[i]=Increment(buff);
	    	  buff=temp[i];
	    	  if(buff.compareTo(lroll)>0){found=true; break;}
	    	}
	    int strength;
	    if(found) strength=i; else strength=2000;
	   // show(strength);
   
	    ResizeTable(tableCon,strength);
	    	
          for(i=0;i<strength;i++) 
		     {String tt=String.format("%d",1+i/BatchStrength);
		      SetData(tt,i,0);
		      SetData(temp[i],i,1);
		     }
         
          UpdateBatch();
	}
	
	 
	 public void ResizeTable2(JTable tablename,int numberofrows)
	 { DefaultTableModel model=(DefaultTableModel) tablename.getModel();
     int totalrows=tablename.getRowCount();
     int difference=numberofrows-totalrows;
     if(difference>0)
     {                               
        for(int i=0;i<difference;i++) model.addRow(new Object[]{false," ", " "," "," "," "," "," "});
      }  
     if(difference<0)
	   { difference=-difference;
        for(int i=0;i<difference;i++) model.removeRow(0);
	  }
		 
	 }
	 
	 
	 public void ResizeTable(JTable tablename,int numberofrows)
	 { DefaultTableModel model=(DefaultTableModel) tablename.getModel();
     int totalrows=tablename.getRowCount();
     int difference=numberofrows-totalrows;
     if(difference>0)
     {
        for(int i=0;i<difference;i++) model.addRow(new Object[]{" "," ",false});
      }  
     if(difference<0)
	   { difference=-difference;
        for(int i=0;i<difference;i++) model.removeRow(0);
	  }
		 
	 }
	 
	 public  String IncrementDate(String det) //Format dd/MM/yy
	 {
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			 Calendar c = Calendar.getInstance();
			 try {
				c.setTime(sdf.parse(det));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 c.add(Calendar.DATE, 1);  // number of days to add
			 det = sdf.format(c.getTime());  // dt is now the new date
			 return det;
	}
	 
	 public  String DayOfWeek(String det) //Format dd/MM/yy
	 { SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yy");
	   Date MyDate = null;
	try {
		MyDate = newDateFormat.parse(det);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   newDateFormat.applyPattern("EEE");
	 String dow = newDateFormat.format(MyDate);
	 return dow;
	 }
	 
	 
	 public void UpdateBatch()
	 {
		
	  String spb=StudentsPerBatch.getText();
	   int BatchStrength=Integer.parseInt(spb);
		 String TempFormatString;
		 int rc=tableCon.getRowCount(), rem=rc%BatchStrength;
	 //show(rc);
     if (rem>0) rem=1; 
 	 
     int CurrentRows=table2.getRowCount();
     int CrossCount=0;
       for(int i=0;i<CurrentRows;i++) { Boolean temp=(Boolean) GetData(table2,i,0); if(temp==true) CrossCount++;} 
     int LASTPAGE=rc/BatchStrength+rem+CrossCount;  ////calculate last 0-base pageindex)
     
 	ResizeTable2(table2,LASTPAGE);	
 	 
     for(int i=0;i<rc;i++) 
      {TempFormatString=String.format("%02d",1+i/BatchStrength);   SetData(TempFormatString,i,0);  	 
      }
          
     String StartDate=DateField.getText();
     
     
     int PerDaySlots=0;  ///Count Per Day Slots Specified by User in table
     for(int i=0;i<20;i++)
    	 {String temp=(String) GetData(table3,i,0); 
    	 if(temp.length()!=0)
    		 { if(Character.isDigit(temp.charAt(0))) PerDaySlots=i;
    		 } 
    	 else break;
    	 }
	 PerDaySlots++;
     //show(PerDaySlots); 
	int CurrentSlot=0;
	int index=0;
    ////Now go and fill time table 
     for(int i=0;i<LASTPAGE;i++)
       {
       
    	Boolean temp=(Boolean) GetData(table2,i,0);
       if(temp==true)  ///Holiday
                 {  SetData2(StartDate,i,1);
                    SetData2(DayOfWeek(StartDate),i,2);
    	            SetData2("--",i,3);SetData2("--",i,4);SetData2("--",i,5);
                   SetData2("--",i,6);SetData2("--",i,7);
                   StartDate=IncrementDate(StartDate);
                   continue;
                 }
       
       int topSeatIndex=index*BatchStrength;
       String StartSeatNo,EndSeatNo;
 	   StartSeatNo = (String) GetData(tableCon,topSeatIndex,1);
 	   if(topSeatIndex+BatchStrength-1<rc) 
		  EndSeatNo = (String) GetData(tableCon,topSeatIndex+BatchStrength-1,1);
	  else   ///This is the last batch
		  EndSeatNo = (String) GetData(tableCon,rc-1,1);
 	  
 	  TempFormatString=String.format("%02d",index+1);
 	     SetData2(TempFormatString,i,4);
 	   
 	   SetData2(StartSeatNo+" -- "+EndSeatNo,i,5);
          SetData2(StartDate,i,1);
         
        SetData2(DayOfWeek(StartDate),i,2);
       //  if(i%4==3) StartDate=IncrementDate(StartDate);
        String c1=(String) GetData(table3,CurrentSlot,0);
        String c2=(String) GetData(table3,CurrentSlot,1);
        String c3=(String) GetData(table3,CurrentSlot,2);
        SetData2(c1,i,3);
        SetData2(c2,i,6);
        SetData2(c3,i,7);
         CurrentSlot=(CurrentSlot+1)%PerDaySlots;
         if(CurrentSlot==0) StartDate=IncrementDate(StartDate);
         index++;
         //if(DayOfWeek(StartDate).contains("Sun"))  StartDate=IncrementDate(StartDate); ///SKIP SUNDAY
         
       }
     
     String tt=String.format(" Total : %d",rc);
     total.setText(tt);
     
	 }
		 
	public void handleInsAboveAction() 
	{ DefaultTableModel model=(DefaultTableModel) tableCon.getModel();
	  int row=tableCon.getSelectedRow();
	  int col=tableCon.getSelectedColumn();
if(row>=0)
	{  String temp=(String) GetData(tableCon,row,1);
	   temp=Decrement(temp);
	   model.insertRow(row,new Object [] {" ",temp,false," "," "," "," "," "," "," "," "});
	   tableCon.changeSelection(row, col,false,false);
	   UpdateBatch();
	 }
	
	}
	
	public void handleInsBelowAction() 
	{ DefaultTableModel model=(DefaultTableModel) tableCon.getModel();
	  int row=tableCon.getSelectedRow();
	  int col=tableCon.getSelectedColumn();
	  
if(row>=0)
	{  String temp=(String) GetData(tableCon,row,1);
	   temp=Increment(temp);
	   model.insertRow(row+1,new Object [] {" ",temp,false," "," "," "," "," "," "," "," "});
	   tableCon.changeSelection(row, col,false,false);
	   UpdateBatch();
	 }
	}
	
public Object GetData(JTable table, int row_index, int col_index){  return table.getModel().getValueAt(row_index, col_index);  	  }
public void handleExitAction() 	{System.exit(0);}

private static String Increment(String alphaNumericString)
	{  char[] an = alphaNumericString.toCharArray();
       int i = an.length - 1;
	    while (true)
	    {   if(an[i]<'0' || an[i]>'9') return new String(an); 
	    	if (i <= 0)
				try { throw new Exception("Maxed out number!!!"); }
	    	           catch (Exception e)
	    	        { e.printStackTrace(); }
 	     	an[i]++;
	    	if (an[i] - 1 == '9')
	    	{
	    		an[i] = '0';
	    		i--;
	    		continue;
	    	}
	    	return new String(an);
	    }
	}


private static String Decrement(String alphaNumericString)
{  char[] an = alphaNumericString.toCharArray();
   int i = an.length - 1;
    while (true)
    {   if(an[i]<'0' || an[i]>'9') return new String(an); 
    	if (i <= 0)
			try { throw new Exception("Maxed out number!!!"); }
    	           catch (Exception e)
    	        { e.printStackTrace(); }
	     	an[i]--;
    	if (an[i] == '0'-1)
    	{
    		an[i] = '9';
    		i--;
    		continue;
    	}
    	return new String(an);
    }
}
public void handleDeleteSelected() 
{ 

	int[] selectedrow=table3.getSelectedRows();
	int[] selectedcol=table3.getSelectedColumns();

int r=selectedrow.length;
int c=selectedcol.length;

for(int j=0;j<c;j++)
for(int i=0;i<r;i++)
    {SetData3(" ",selectedrow[i],selectedcol[j]);
    
    }
	// TODO Auto-generated method stub
	
}

}





