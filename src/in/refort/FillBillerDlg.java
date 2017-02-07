package in.refort;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FillBillerDlg extends JDialog {
	private JTextField Name;
	private JTextField Address1;
	private JTextField Address2;
	private JTextField Address3;
	private JTextField Address4;
	String NameString,AddLine1,AddLine2,AddLine3,AddLine4;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FillBillerDlg dialog = new FillBillerDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FillBillerDlg() {
		setBounds(100, 100, 500, 380);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 147, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 33, 0, 0, 0, 0, 0, 0, 7, 41, 0, 21, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel label = new JLabel("   ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 3;
			gbc_label.gridy = 0;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel lblEnterBillerName = new JLabel("Enter Biller Name and Address");
			GridBagConstraints gbc_lblEnterBillerName = new GridBagConstraints();
			gbc_lblEnterBillerName.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterBillerName.gridx = 3;
			gbc_lblEnterBillerName.gridy = 1;
			getContentPane().add(lblEnterBillerName, gbc_lblEnterBillerName);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.insets = new Insets(0, 0, 5, 5);
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 1;
			gbc_buttonPane.gridy = 2;
			getContentPane().add(buttonPane, gbc_buttonPane);
		}
		{
			JLabel lblNewLabel = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 15);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 3;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			Name = new JTextField();
			GridBagConstraints gbc_Name = new GridBagConstraints();
			gbc_Name.insets = new Insets(0, 0, 5, 5);
			gbc_Name.fill = GridBagConstraints.HORIZONTAL;
			gbc_Name.gridx = 3;
			gbc_Name.gridy = 3;
			getContentPane().add(Name, gbc_Name);
			Name.setColumns(10);
		}
		{
			JLabel label = new JLabel("       ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 4;
			gbc_label.gridy = 3;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("    ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 3;
			gbc_label.gridy = 4;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Full Postal");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 5;
			getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			Address1 = new JTextField();
			GridBagConstraints gbc_Address1 = new GridBagConstraints();
			gbc_Address1.insets = new Insets(0, 0, 5, 5);
			gbc_Address1.fill = GridBagConstraints.HORIZONTAL;
			gbc_Address1.gridx = 3;
			gbc_Address1.gridy = 5;
			getContentPane().add(Address1, gbc_Address1);
			Address1.setColumns(10);
		}
		{
			JLabel label = new JLabel("   ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 6;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Residential Address");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 6;
			getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("  ");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.gridx = 2;
			gbc_lblNewLabel_3.gridy = 6;
			getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			Address2 = new JTextField();
			GridBagConstraints gbc_Address2 = new GridBagConstraints();
			gbc_Address2.insets = new Insets(0, 0, 5, 5);
			gbc_Address2.fill = GridBagConstraints.HORIZONTAL;
			gbc_Address2.gridx = 3;
			gbc_Address2.gridy = 6;
			getContentPane().add(Address2, gbc_Address2);
			Address2.setColumns(10);
		}
		{
			Address3 = new JTextField();
			GridBagConstraints gbc_Address3 = new GridBagConstraints();
			gbc_Address3.insets = new Insets(0, 0, 5, 5);
			gbc_Address3.fill = GridBagConstraints.HORIZONTAL;
			gbc_Address3.gridx = 3;
			gbc_Address3.gridy = 7;
			getContentPane().add(Address3, gbc_Address3);
			Address3.setColumns(10);
		}
		{
			Address4 = new JTextField();
			GridBagConstraints gbc_Address4 = new GridBagConstraints();
			gbc_Address4.insets = new Insets(0, 0, 5, 5);
			gbc_Address4.fill = GridBagConstraints.HORIZONTAL;
			gbc_Address4.gridx = 3;
			gbc_Address4.gridy = 8;
			getContentPane().add(Address4, gbc_Address4);
			Address4.setColumns(10);
		}
		{
			JLabel label = new JLabel("   ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 3;
			gbc_label.gridy = 9;
			getContentPane().add(label, gbc_label);
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 10;
			getContentPane().add(panel, gbc_panel);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{	dispose();
						NameString=Name.getText();
						AddLine1=Address1.getText();
						AddLine2=Address2.getText();
						AddLine3=Address3.getText();
						AddLine4=Address4.getText();
					}
				});
				panel.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JLabel label = new JLabel("         ");
				panel.add(label);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				panel.add(cancelButton);
				cancelButton.setActionCommand("Cancel");
			}
		}
		{
			JLabel label = new JLabel("   ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 12;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("   ");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 3;
			gbc_label.gridy = 12;
			getContentPane().add(label, gbc_label);
		}
	}

	public void setNameAddress(String name2, String add1, String add2,String add3, String add4) 
	{	
		Name.setText(name2); Address1.setText(add1); Address2.setText(add2);
	    Address3.setText(add3);Address4.setText(add4);
		NameString=name2;AddLine1=add1;AddLine2=add2;AddLine3=add3;AddLine4=add4;
	}
	
	public String getName() {  return NameString; }
	public String getAdd1() {  return AddLine1; }
	public String getAdd2() {  return AddLine2; }
	public String getAdd3() {  return AddLine3; }
	public String getAdd4() {  return AddLine4; }

}
