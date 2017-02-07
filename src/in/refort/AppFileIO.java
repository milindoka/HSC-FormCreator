package in.refort;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;


public class AppFileIO extends JFrame    ////extends JFrame is required to diplay Toast
{
private static final long serialVersionUID = 1L;

Toast toast;

private JTextComponent FrollField = null;       ///maps to AppFrame FrollField 
private JTextComponent LrollField = null;       ///maps to AppFrame LrollField
private JTextComponent StudentsPerBatch = null; ///maps to AppFrame StudentsPerBatch
private JTextComponent Subject = null;          ///maps to AppFrame Subject
private JTextComponent DateField = null;        ///maps to AppFrame DateField
private JTable table,table2,table3;    
private JLabel total;
String name,add1,add2,add3,add4,POP;


//////Standard 2 Methods For MVC Design-----------------------------------------------------------------------
private static AppFileIO instance = null;  /* ===== singleton details ===== */  
public static AppFileIO getInstance() { if (instance==null)	{ instance = new AppFileIO();} 	return instance;}
protected AppFileIO() { toast=Toast.getInstance();
	setLocationRelativeTo(null);/* Prevents constructor from being created automatically.*/
	}
//////---------------------------------------------------------------------------------------------------------


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

public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}

public Object GetData(JTable table, int row_index, int col_index){  return table.getModel().getValueAt(row_index, col_index);  	  }
public void SetData(Object obj, int row_index, int col_index)    {  table.getModel().setValueAt(obj,row_index,col_index);  }
public void SetData2(Object obj, int row_index, int col_index)    {  table2.getModel().setValueAt(obj,row_index,col_index);  }
public void SetData3(Object obj, int row_index, int col_index)    {  table3.getModel().setValueAt(obj,row_index,col_index);  }
//fileio.LinkComponents(FrollField,LrollField,StudentsPerBatch,Subject,DateField,table,table2,total);
public void LinkComponents(JTextComponent frollfield,JTextComponent lrollfield,JTextComponent studentsperbatch,
        JTextComponent Subject,JTextComponent datefield,JTable tbl,JTable tbl2,JTable tbl3,JLabel lbl)
{this.FrollField = frollfield;	this.LrollField = lrollfield;	this.StudentsPerBatch=studentsperbatch; 
this.Subject=Subject;this.DateField=datefield;this.table = tbl; this.table2 = tbl2;table3=tbl3;this.total=lbl;

 	
}


public void setNameAddress(String name, String add1, String add2,String add3, String add4,String POP) 
{	this.name=name;this.add1=add1;this.add2=add2;this.add3=add3;this.add4=add4; this.POP=POP;
}

public void handleLoadAction(String fnem)
{	
	int rc=table3.getRowCount();
	for(int i=0;i<rc;i++) { SetData3(" ",i,0);	SetData3(" ",i,1); SetData3(" ",i,2);}
	
	BufferedReader reader=null;
	try { 	reader = new BufferedReader(new FileReader(fnem));
	} catch (FileNotFoundException e1) {
	
		e1.printStackTrace();
	}
	ArrayList<String> strArray = new ArrayList<String>();
	String line = null;
	try { while ((line = reader.readLine()) != null) { strArray.add(line); }	} 
	catch (IOException e) {	e.printStackTrace();   }



FrollField.setText(strArray.get(0));
LrollField.setText(strArray.get(1));
StudentsPerBatch.setText(strArray.get(2));
Subject.setText(strArray.get(3));	
DateField.setText(strArray.get(4));

int totalseatnumbers=Integer.parseInt(strArray.get(5));
int totaltimetablerows=Integer.parseInt(strArray.get(6));
int totalscheduleperdayrows=Integer.parseInt(strArray.get(7));

ResizeTable(table,totalseatnumbers);	
ResizeTable(table2,totaltimetablerows);
//ResizeTable(table3,totalscheduleperdayrows); ///not required, kept fixed at present
 
  for(int i=0;i<totalseatnumbers;i++) 
     { String temp[],stemp;
 	   stemp=strArray.get(i+8);
 	  temp=stemp.split("#");
 	  
	  //String tt=String.format("%d",1+i/StudentsPerBatch);
      //SetData(tt,i,0);
 	  SetData(temp[0],i,0);
      SetData(temp[1],i,1);
      Boolean bAB=(temp[2].contains("A")?true:false);
      SetData(bAB,i,2);
     }

  String tt=String.format(" Total : %d",totalseatnumbers);
  total.setText(tt);
 
  for(int i=0;i<totaltimetablerows;i++) 
  { String temp[],stemp;
	   stemp=strArray.get(i+totalseatnumbers+8);
	  temp=stemp.split("#");
	  Boolean holiday=(temp[0].contains("Y")?true:false);
      SetData2(holiday,i,0);
	for(int j=1;j<8;j++) 	
	  SetData2(temp[j],i,j);
  }
  

  for(int i=0;i<totalscheduleperdayrows;i++) 
  { String temp[],stemp;
	   stemp=strArray.get(i+totalseatnumbers+totaltimetablerows+8);
	  temp=stemp.split("#");
	  if (temp.length==0) continue;
	for(int j=0;j<temp.length;j++) 	
	  SetData3(temp[j],i,j);
  }
  
  
  {String temp[],stemp;
   int lastindex=strArray.size()-1;
  stemp=strArray.get(lastindex);
  temp=stemp.split("#");
  
  switch (temp.length) 
  {
    
   case 6 : add4=temp[5];
   case 5 : add3=temp[4];
   case 4 : add2=temp[3];
   case 3 : add1=temp[2];
   case 2 : name=temp[1];
   case 1 : POP=temp[0];
   case 0 : break;
  
  }
 
  
  	  
  }

toast.AutoCloseMsg("File Loaded");

}


public String getName() {  return name; }
public String getAdd1() {  return add1; }
public String getAdd2() {  return add2; }
public String getAdd3() {  return add3; }
public String getAdd4() {  return add4; }
public String getPOP() {  return POP; }



public void handleSaveAction(String fnem) 
{   
	FileWriter f0=null;
	try {f0 = new FileWriter(fnem); }            catch (IOException e1){e1.printStackTrace();}
	 String newLine = System.getProperty("line.separator");
	// try { f0.write(newLine);	}                          catch (IOException e) {e.printStackTrace();}
	 try { f0.write(FrollField.getText()+ newLine);	}      catch (IOException e) {e.printStackTrace();}
	 try { f0.write(LrollField.getText()+ newLine);	}      catch (IOException e) {e.printStackTrace();}
	 try { f0.write(StudentsPerBatch.getText()+ newLine);} catch (IOException e) {e.printStackTrace();}
	 try { f0.write(Subject.getText()+ newLine);	}      catch (IOException e) {e.printStackTrace();}
	 try { f0.write(DateField.getText()+ newLine);	}      catch (IOException e) {e.printStackTrace();}
	 
	 
	 int rowcount=table.getRowCount();
	 String tempstr=String.format("%d",rowcount);
	 try {f0.write(tempstr + newLine);	}      catch (IOException e) {e.printStackTrace();}
	 
	 int rowcount2=table2.getRowCount();
	 tempstr=String.format("%d",rowcount2);
	 try {f0.write(tempstr + newLine);	}      catch (IOException e) {e.printStackTrace();}
	 
	 int rowcount3=table3.getRowCount();
	 tempstr=String.format("%d",rowcount3);
	 try {f0.write(tempstr + newLine);	}      catch (IOException e) {e.printStackTrace();}
	 
	 
	 for(int i = 0;i<rowcount;i++)
	 {   
	    String batch=(String) GetData(table,i,0);
	    String seatno=(String) GetData(table,i,1);
	    Boolean IsAbsent=(Boolean) GetData(table,i,2);
	    String AB=(IsAbsent ? "A" : "P");
	    try { f0.write(batch+"#"+seatno+"#"+AB+newLine);	} catch (IOException e) {e.printStackTrace();	}
	 }
	 
	 
	 
	 for(int i = 0;i<rowcount2;i++)
	 {  
		Boolean IsHday=(Boolean) GetData(table2,i,0);
		String holiday=(IsHday ? "Y" : "N");
	    String day=(String) GetData(table2,i,1);
	    String tyme=(String) GetData(table2,i,3);
	    String bach=(String) GetData(table2,i,4);
	    String seat=(String) GetData(table2,i,5);
	    String room=(String) GetData(table2,i,6);
	    String session=(String) GetData(table2,i,7);

	    try { f0.write(holiday+"#"+day+"#"+day+"#"+tyme+"#"+bach+"#"+seat+"#"+room+"#"+session+newLine);	} catch (IOException e) {e.printStackTrace();	}
	 }
	 
	 for(int i = 0;i<rowcount3;i++)
	 {   
	    String tyme=(String) GetData(table3,i,0);
	    String roomno=(String) GetData(table3,i,1);
	    String session=(String) GetData(table3,i,2);

	    try { f0.write(tyme+"#"+roomno+"#"+session+newLine);	} catch (IOException e) {e.printStackTrace();	}
	 }

	    try { f0.write(POP+"#"+name+"#"+add1+"#"+add2+"#"+add3+"#"+add4+newLine);	} catch (IOException e) {e.printStackTrace();	}
	 
	  
	 try {f0.close();} catch (IOException e) {e.printStackTrace();}
	 toast.AutoCloseMsg("File Saved");
	
}


}
