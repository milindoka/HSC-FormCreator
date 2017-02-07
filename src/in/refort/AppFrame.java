package in.refort;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AppFrame extends JFrame 
{  private static final long serialVersionUID = 1L;
   
   AppController controller;
   AppPrint printer;
   AppFileIO fileio;
   PrintBill Bill;
   PrintABreport AB;
   FillBillerDlg FBdlg;
   
   private JTextField FrollField;
   private JTextField LrollField;
   private JTextField StudentsPerBatch;
   private JTextField Subject;
   private JTextField DateField;
  // private JCheckBox cb;
   Icon pressedIcon;   
   private JButton setPrinterButton;
   private JButton billdlgButton;
   private JButton createButton;
   private JButton saveButton;
   private JButton loadButton;
   private JButton insertAboveButton;
   private JButton insertBelowButton;
   private JButton printAttendanceButton;
   private JButton printTimeTableButton;
   private JButton deleteButton;
   private JButton UpdateTimeTableButton;
   private JButton PrintABreportButton;
   private JButton PrintBillButton;
   private JLabel total;
   private JLabel printerLabel;
   private String CollegeName="S.I.W.S. College, Wadala";
   private String CollegeIndex="J 31.04.005";
   
   private JButton insertTimeTableRowAboveButton;
   private JButton insertTimeTableRowBelowButton;
   private JButton deleteTimeTableRowButton;
   
   JPanel GridPanel = new JPanel(new GridLayout(7,3,2,2));
   JPanel GridPanel2 = new JPanel(new GridLayout(7,3,2,2));
  
   

   
   JPanel topPanel = new JPanel(new BorderLayout());
   private JTable table;
   private JTable table2;
   private RXTable table3;
   
   String name="",add1="",add2="",add3="",add4="",POP="A";
   
   private JScrollPane scrollPane;
   private JScrollPane scrollPane2;
   private JScrollPane scrollPane3;
   private DefaultTableModel model;
   private DefaultTableModel model2;
   private DefaultTableModel model3;
   
    JRadioButton PRACTICALattendance = new JRadioButton("Practical");
    JRadioButton ORALattendance = new JRadioButton("Oral");
	JRadioButton PROJECTattendance = new JRadioButton("Project");
	ButtonGroup group = new ButtonGroup();
	
   public static void show(String msg) {JOptionPane.showMessageDialog(null, msg);}
	public void show(int msg) {JOptionPane.showMessageDialog(null, msg);}
	public void SetData3(Object obj, int row_index, int col_index)    {  table3.getModel().setValueAt(obj,row_index,col_index);  }   
	public Object GetData(JTable table, int row_index, int col_index) {  return table.getModel().getValueAt(row_index, col_index); }   
   
   public AppFrame() 
    {   UIManager.put( "CheckBox.icon", new CrossBoxIcon() );
	   
   
    // Set the LookAndFeel
    try {
        try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } catch(UnsupportedLookAndFeelException e) {
        e.printStackTrace();
    }
    // Setup font size based on screen size
    //   UIManager.put("Label.font", new Font("Dialog", Font.BOLD, 8)); //Change the 8 to what ever you want
    UIManager.put("Button.font", new Font("Dialog", Font.BOLD, 8)); 
    //  UIManager.put("RadioButton.font", new Font("Dialog", Font.BOLD, 8)); 
    // UIManager.put("CheckBox.font", new Font("Dialog", Font.BOLD, 8)); 
    //  UIManager.put("ColorChooser.font", new Font("Dialog", Font.BOLD, 8)); 
    // UIManager.put("ComboBox.font", new Font("Dialog", Font.BOLD, 8)); 
    // Other default setting you want to change..
 
    // Previous code to create your Frame and components
    
    
    initializeVariables();  ///Allocate Memory;
	
	   
       InitializeButtonsWithActionListeners(); //
 	   layoutComponents();  ///Arrange on Screen
 	   setTitle("HSC Form Creator");
       //setSize(800, 800);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);   
       setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
       
       controller = AppController.getInstance();
       printer = AppPrint.getInstance();
       fileio = AppFileIO.getInstance();
       Bill = PrintBill.getInstance();
       AB=PrintABreport.getInstance();
      
       controller.LinkComponents(FrollField,LrollField,StudentsPerBatch,DateField,table,table2,table3,total);
       printer.LinkComponents(StudentsPerBatch,Subject,table,table2);
       fileio.LinkComponents(FrollField,LrollField,StudentsPerBatch,Subject,DateField,table,table2,table3,total);
       AB.LinkComponents(StudentsPerBatch, Subject, table);
             
       controller.handleCreateAction();
       	
       Bill.LinkComponents(CollegeName,CollegeIndex,Subject);
       printer.LoadPreferences();
       printerLabel.setHorizontalAlignment(SwingConstants.CENTER);
       printerLabel.setText(printer.PrinterName);
       AB.SetPrinter(printer.PrinterName);
       
    }
   
   private void initializeVariables()
   {	FBdlg = new FillBillerDlg();
        FBdlg.setNameAddress(name,add1,add2,add3,add4);
	    FrollField = new JTextField(8);
        LrollField = new JTextField(8);
        StudentsPerBatch = new JTextField(8);
        Subject = new JTextField(8);
        DateField = new JTextField(8);
   
        
    //    cb = new JCheckBox("Check me!");
        pressedIcon = new ImageIcon("pres-icon.gif");
        //cb.setPressedIcon(pressedIcon);
   //     cb.setSelectedIcon(pressedIcon); 
        group.add(PRACTICALattendance);
    	group.add(ORALattendance);
    	group.add(PROJECTattendance);
        
   total=new JLabel(" Total");
   printerLabel=new JLabel("Printer Not Set");
   FrollField.setText("M0121000");
   LrollField.setText("M0121200");
   //LrollField.setText("M0122990");
   
   StudentsPerBatch.setText("24");
   Subject.setText("Mathematics");
	
   String det = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
   
   DateField.setText(det);
   
   Object[][] data = 	{  {" ", " ", new Boolean(false)} }; /////define data types    
   
   String[] col = {"Batch","Seat No", "Absent ?"};
   	
    
  model = new DefaultTableModel(data, col)
   { private static final long serialVersionUID = 2L;

   @Override
   public Class<?> getColumnClass(int columnIndex) {
       if (columnIndex == 2)
           return Boolean.class;
       return super.getColumnClass(columnIndex);
   }
   
       @Override
       public boolean isCellEditable(int row, int column)
       {
          //all cells false
          return true;
       }
   };
   
   table = new JTable(model);
   scrollPane = new JScrollPane(table);
   //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   table.setRowHeight(30);
   
   table.setIntercellSpacing(new Dimension(8,8));
   table.getTableHeader().setResizingAllowed(false);
   table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
   table.getColumnModel().getColumn(1).setPreferredWidth(150);
   //table.getColumnModel().getColumn(0).setHeaderValue("Batch");
   //table.getColumnModel().getColumn(1).setHeaderValue("Seat Number");
   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
   centerRenderer.setHorizontalAlignment( JLabel.CENTER );
   table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

   table.addKeyListener(new KeyAdapter()
   {
       public void keyPressed(KeyEvent e)
       {  if   (e.getKeyCode()==KeyEvent.VK_DELETE)  	controller.handleDeleteAction();
       } 
   });

 Object[][] data2 = 	{  {new Boolean(false)," ", " "," "," "," "," "," " } }; /////define data types    
   
   //String[] col = {"Batch","Seat No", "Absent ?"};

 //  final String[][] data2 = new String[60][8];
   String[] col2= new String[8];	
   model2 = new DefaultTableModel(data2, col2)
   { private static final long serialVersionUID = 2L;


   @Override
   public Class<?> getColumnClass(int columnIndex) {
       if (columnIndex == 0)
           return Boolean.class;
       return super.getColumnClass(columnIndex);
   }
  
   
       @Override
       public boolean isCellEditable(int row, int column)
       {
          //all cells false
          return true;
       }
   };
   
   table2 = new JTable(model2);
   scrollPane2 = new JScrollPane(table2);
   table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   table2.setRowHeight(30);
   table2.setIntercellSpacing(new Dimension(8,8));
   table2.getTableHeader().setResizingAllowed(false);
   table2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
   table2.getColumnModel().getColumn(0).setMinWidth(50);
   table2.getColumnModel().getColumn(1).setMinWidth(100);
   table2.getColumnModel().getColumn(2).setMinWidth(80);
   table2.getColumnModel().getColumn(3).setMinWidth(180);
   table2.getColumnModel().getColumn(4).setMinWidth(50);
   table2.getColumnModel().getColumn(5).setMinWidth(250);
   table2.getColumnModel().getColumn(6).setMinWidth(100);
   
   table2.getColumnModel().getColumn(1).setHeaderValue("Date");
   table2.getColumnModel().getColumn(2).setHeaderValue("Day");
   table2.getColumnModel().getColumn(3).setHeaderValue("Time");
   table2.getColumnModel().getColumn(4).setHeaderValue("Batch");
   table2.getColumnModel().getColumn(5).setHeaderValue("Seat Numbers");
   table2.getColumnModel().getColumn(6).setHeaderValue("Room No");
   table2.getColumnModel().getColumn(7).setHeaderValue("Session");

   final String[][] data3 = new String[20][3];
   String[] col3= new String[3];	
   model3 = new DefaultTableModel(data3, col3)
   { private static final long serialVersionUID = 1L;
       @Override
       public boolean isCellEditable(int row, int column)
       {
          //all cells false
          return true;
       }
   };
  
   table3 = new RXTable(model3);
   table3.setSelectAllForEdit(true);
   //table3 = new JTable(model3);
   scrollPane3 = new JScrollPane(table3);
   table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   table3.setRowHeight(30);
   table3.setIntercellSpacing(new Dimension(8,8));
   table3.getTableHeader().setResizingAllowed(false);
   table3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
   table3.getColumnModel().getColumn(0).setMinWidth(150);
   table3.getColumnModel().getColumn(1).setMinWidth(150);
   table3.getColumnModel().getColumn(2).setMinWidth(150);
   
   table3.getColumnModel().getColumn(0).setHeaderValue("Time");
   table3.getColumnModel().getColumn(1).setHeaderValue("Room No.");
   table3.getColumnModel().getColumn(2).setHeaderValue("Session");
   
   PRACTICALattendance.setSelected(true);
   
   for(int i=0;i<20;i++) 
       for(int j=0;j<3;j++)
          SetData3("",i,j);

   for(int i=0;i<8;i++)  SetData3("Afternoon",i,2);
   
   SetData3("1:00 PM TO 2:00 PM",0,0);
   SetData3("1:00 PM TO 2:00 PM",1,0);
   SetData3("1:00 PM TO 2:00 PM",2,0);
   SetData3("1:00 PM TO 2:00 PM",3,0);
   SetData3("2:30 PM TO 3:30 PM",4,0);
   SetData3("2:30 PM TO 3:30 PM",5,0);
   SetData3("2:30 PM TO 3:30 PM",6,0);
   SetData3("2:30 PM TO 3:30 PM",7,0);
   
   SetData3("401",0,1);
   SetData3("401",1,1);
   SetData3("406",2,1);
   SetData3("406",3,1);
   SetData3("401",4,1);
   SetData3("401",5,1);
   SetData3("406",6,1);
   SetData3("406",7,1);
  
   ListSelectionModel selModel = table3.getSelectionModel();
   selModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   
   
   table3.addKeyListener(new KeyAdapter()
   {
       public void keyPressed(KeyEvent e)
       {  if   (e.getKeyCode()==KeyEvent.VK_DELETE)  	controller.handleDeleteSelected();
       } 
   });
   
   
   
   }
  

   
   public boolean vaidCheck(int row, int col)
   {    if(table.getCellEditor()!=null)
         { table.getCellEditor().stopCellEditing();
         }

   String om=table.getValueAt(row,col).toString();

   

   if(om.trim().length()==0)  return false;
 ///else
   return true;

   }
   
   
   private void InitializeButtonsWithActionListeners() 
    {  
	   
	   billdlgButton = new JButton("Bill Details");
	 //  billdlgButton.setMaximumSize(Dimension(10,10));
	     billdlgButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent e)
			{FBdlg.setNameAddress(name,add1,add2,add3,add4);
			 FBdlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			 FBdlg.setVisible(true);
			}
		   } );
	   
	   
	     setPrinterButton = new JButton("Set Printer");
		 //  billdlgButton.setMaximumSize(Dimension(10,10));
		     setPrinterButton.addActionListener(new ActionListener()
			   {
				public void actionPerformed(ActionEvent e)
				{
					printer.SelectPrinter();
					printerLabel.setText(printer.PrinterName);
				}
			   } );

	     
	   createButton = new JButton("Create List");
     createButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){controller.handleCreateAction();}
	   } );
     
     saveButton = new JButton("Save List");
     saveButton.addActionListener(new ActionListener()
	   { 
	   
	   
		public void actionPerformed(ActionEvent e){
		
			String fyle="";
			
			JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "HSC Pract-Oral-Project Files", "hsc");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("D:\\HSC"));
           
            int option = chooser.showSaveDialog(AppFrame.this);
           
            
            if (option == JFileChooser.APPROVE_OPTION)
             {
                File[] sf = chooser.getSelectedFiles();
                if (sf.length > 0) {
				}
                for (int i = 1; i < sf.length; i++) 
                {
                 }
              fyle=sf[0].getPath();
             
              
              //bSaveDirect[CurLis]=true;
             if (!fyle.endsWith(".hsc")) fyle+= ".hsc";
            name=FBdlg.getName();add1=FBdlg.getAdd1(); add2=FBdlg.getAdd2();add3=FBdlg.getAdd3();add4=FBdlg.getAdd4();
            if (PRACTICALattendance.isSelected()) POP="A";
            if (ORALattendance.isSelected()) POP="B";
            if (PROJECTattendance.isSelected()) POP="C";
            fileio.setNameAddress(name, add1, add2,add3,add4,POP); 
			fileio.handleSaveAction(fyle);
             }
			}
            
	   } );
     
     
     loadButton = new JButton("Load List");
     loadButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e)
		{  String fyle="";
		
		JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "HSC Pract-Oral-Project Files", "hsc");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("D:\\HSC"));
       
        int option = chooser.showOpenDialog(AppFrame.this);
       
        
        if (option == JFileChooser.APPROVE_OPTION)
         {
            File[] sf = chooser.getSelectedFiles();
            if (sf.length > 0) {
			}
            for (int i = 1; i < sf.length; i++) 
            {
             }
          fyle=sf[0].getPath();
         
          
          //bSaveDirect[CurLis]=true;
         if (!fyle.endsWith(".hsc")) fyle+= ".hsc";
         	
		fileio.handleLoadAction(fyle);
		name=fileio.getName();add1=fileio.getAdd1(); add2=fileio.getAdd2();add3=fileio.getAdd3();add4=fileio.getAdd4();
         POP=fileio.getPOP();
         //show(POP);
           if (POP.contains("A")) PRACTICALattendance.setSelected(true);
           if (POP.contains("B")) ORALattendance.setSelected(true);
           if (POP.contains("C")) PROJECTattendance.setSelected(true);
         
         }
			
		
		controller.UpdateBatch();
		}
	   } );
     insertAboveButton = new JButton("Ins Above");
     insertAboveButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){controller.handleInsAboveAction();}
	   } );
     
     insertBelowButton = new JButton("Ins Below");
     insertBelowButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){controller.handleInsBelowAction();}
	   } );
     printAttendanceButton = new JButton("Print Attendance");
     printAttendanceButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e)
		{   if(PRACTICALattendance.isSelected()) printer.PrintAttendance(0);
		    if(ORALattendance.isSelected()) printer.PrintAttendance(1);
		    if(PROJECTattendance.isSelected()) printer.PrintAttendance(2);
			
		}
	   } );

     printTimeTableButton = new JButton("Print Time Table");
     printTimeTableButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){printer.Print2();}
	   } );
     deleteButton = new JButton("Delete");
     deleteButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ controller.handleDeleteAction();}
	   } );
     //frame.add(datePicker);
 
     
     UpdateTimeTableButton = new JButton("Update Time Table");
     UpdateTimeTableButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ controller.UpdateBatch();}
	   } );
     
    
    
    PrintABreportButton = new JButton("Print AB Report");
    PrintABreportButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ AB.PrintAbsenteeReport();
		                                            
		                                           }
	   } );

    PrintBillButton = new JButton("Print Bill");
    PrintBillButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e)
		{  int rc=table.getRowCount();
	       int ABcount=0;	
		  for(int i=0;i<rc;i++)
		  {
		  Boolean temp =(Boolean) GetData(table,i,2);
		  if (temp==true) ABcount++;
		  }		
		
			Bill.SetStudCount(rc-ABcount);
			Bill.setNameAddress(name, add1,add2,add3, add4, POP);
			Bill.Print();
		
			int result=JOptionPane.showOptionDialog(null, 
			        "Do you like this answer?", 
			        "Feedback", 
			        JOptionPane.OK_CANCEL_OPTION, 
			        JOptionPane.INFORMATION_MESSAGE, 
			        null, 
			        new String[]{"Yes I do", "No I don't"}, // this is the array
			        "default");
			if(result==JOptionPane.OK_OPTION) 	Bill.Print1();
			        
			        
			
		 }
	   } );
    
    insertTimeTableRowAboveButton = new JButton("Insert Time Table Row (Above)");
    insertTimeTableRowAboveButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ controller.onb1();}
	   } );
    
    insertTimeTableRowBelowButton = new JButton("Insert Time Table Row (Below)");
    insertTimeTableRowBelowButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ controller.onb1();}
	   } );
    
    deleteTimeTableRowButton = new JButton("Delete Time Table Row");
    deleteTimeTableRowButton.addActionListener(new ActionListener()
	   {
		public void actionPerformed(ActionEvent e){ controller.onb1();}
	   } );
    
   }
    
    private void layoutComponents()
    {
    GridPanel.setBorder(new EmptyBorder( 8, 8, 8, 8 ));
    GridPanel2.setBorder(new EmptyBorder( 8, 8, 8, 8 ));
    
    //GridPanel2.setMaximumSize(new Dimension(50,50));
    GridPanel.add(new JLabel("First Roll"));
    GridPanel.add(FrollField);
    GridPanel.add(createButton);
    
    GridPanel.add(new JLabel("Last Roll"));
    GridPanel.add(LrollField);
    GridPanel.add(saveButton);
    
   
    GridPanel.add(new JLabel("Students Per Batch :"));
    GridPanel.add(StudentsPerBatch);
    GridPanel.add(loadButton);
    
    GridPanel.add(new JLabel("Subject : "));
    GridPanel.add(Subject);
    GridPanel.add(printAttendanceButton);
     
    GridPanel.add(new JLabel("Start Date : "));
    GridPanel.add(DateField);
    GridPanel.add(UpdateTimeTableButton);
    
    GridPanel.add(insertAboveButton);
    GridPanel.add(insertBelowButton);
    GridPanel.add(printTimeTableButton);
    
    GridPanel.add(deleteButton);
    GridPanel.add(PrintBillButton);
    GridPanel.add(PrintABreportButton);
 
    
    GridPanel2.add(total);GridPanel2.add(setPrinterButton); GridPanel2.add(new JLabel(" "));
    GridPanel2.add(PRACTICALattendance);GridPanel2.add(new JLabel(" ")); GridPanel2.add(new JLabel(" "));
    GridPanel2.add(ORALattendance);GridPanel2.add(printerLabel); GridPanel2.add(new JLabel(" "));
    GridPanel2.add(PROJECTattendance);GridPanel2.add(new JLabel(" ")); GridPanel2.add(new JLabel(" "));
    GridPanel2.add(billdlgButton);GridPanel2.add(new JLabel(" ")); GridPanel2.add(new JLabel(" "));
    GridPanel2.add(new JLabel(" ")); GridPanel2.add(new JLabel(" "));GridPanel2.add(new JLabel(" "));
    
    GridPanel.add(PrintBillButton);
    GridPanel.add(PrintABreportButton);
 
    
    topPanel.setPreferredSize(new Dimension(350,250));
    topPanel.add(GridPanel,BorderLayout.WEST);
    topPanel.add(GridPanel2,FlowLayout.CENTER);
    topPanel.add(scrollPane3,BorderLayout.EAST);
    //topPanel.add(total,BorderLayout.CENTER);
    //topPanel.add(,BorderLayout.CENTER);    
    
    getContentPane().add(topPanel, BorderLayout.NORTH);
    getContentPane().add(scrollPane, BorderLayout.WEST);
    getContentPane().add(scrollPane2, FlowLayout.RIGHT);
    
    table3.setRowSelectionAllowed(true);
    table3.setColumnSelectionAllowed(true);
    @SuppressWarnings("unused")
	ExcelAdapter myAd = new ExcelAdapter(table3);
   
    
    pack();
    }
}


