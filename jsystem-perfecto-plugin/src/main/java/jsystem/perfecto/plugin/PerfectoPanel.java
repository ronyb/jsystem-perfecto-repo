/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsystem.perfecto.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PerfectoPanel extends javax.swing.JPanel {

	private static final String perfectoMobileBrowserJarLocation = "perfecto/perfecto-mobile-browser-1.0.jar";
	
	public PerfectoPanel() {
		initComponents();
		loadPerfectoProperties();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel4 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		perfectoLabButton = new javax.swing.JButton();
		perfectoDashboardButton = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		passwordField = new javax.swing.JPasswordField();
		cloudTextField = new javax.swing.JTextField();
		usernameTextField = new javax.swing.JTextField();

		jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(0, 0, 255));
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PerfectoLogo.png"))); // NOI18N

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				);
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				);

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		perfectoLabButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		perfectoLabButton.setText("Open Perfecto Lab");
		perfectoLabButton.setFocusable(false);
		perfectoLabButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				perfectoLabButtonActionPerformed(evt);
			}
		});

		perfectoDashboardButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		perfectoDashboardButton.setText("Open Perfecto Dashboard");
		perfectoDashboardButton.setFocusable(false);
		perfectoDashboardButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				perfectoDashboardButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(perfectoLabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(perfectoDashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(perfectoLabButton)
								.addComponent(perfectoDashboardButton))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		jLabel2.setText("Cloud:");

		jLabel3.setText("Username:");

		jLabel4.setText("Password:");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel4)
								.addComponent(jLabel3)
								.addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
								.addComponent(cloudTextField)
								.addComponent(usernameTextField))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(cloudTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3)
								.addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4)
								.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(14, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
				);
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(44, Short.MAX_VALUE))
				);
	}// </editor-fold>//GEN-END:initComponents

	private void perfectoLabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfectoLabButtonActionPerformed
		launchPerfectoLabJar();
	}//GEN-LAST:event_perfectoLabButtonActionPerformed

	private void perfectoDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfectoDashboardButtonActionPerformed
		launchPerfectoDashboardJar();
	}//GEN-LAST:event_perfectoDashboardButtonActionPerformed

	private void launchPerfectoLabJar() {

		savePerfectoProperties();
		
		if (validatePerfectoProperties()) {
			
			try {
				// expecting an exception to be thrown here if Lab is still not running
				PerfectoLabClient.getCloud();
				
				// showing the warning message because the exception wasn't thrown
				showWarningDialog("Perfecto Lab is already running", "Can't open more than one instance of Perfecto Lab");
			}
			catch (Exception e) {
				
				// caught expected exception - because the Lab is still not running - will start it now
				if (new File(perfectoMobileBrowserJarLocation).exists()) {
					try {
						Runtime.getRuntime().exec("java -jar " + perfectoMobileBrowserJarLocation + " lab");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					showErrorDialog("Perfecto Lab can't be launched!", perfectoMobileBrowserJarLocation + " wasn't found");
				}
			}
		}
	}

	private void launchPerfectoDashboardJar() {
		
		savePerfectoProperties();
		
		if (validatePerfectoProperties()) {
			
			try {
				// expecting an exception to be thrown here if Dashboard is still not running
				PerfectoDashboardClient.getCloud();
				
				// showing the warning message because the exception wasn't thrown
				showWarningDialog("Perfecto Dashboard is already running", "Can't open more than one instance of Perfecto Dashboard");
			}
			catch (Exception e) {
				
				// caught expected exception - because the Dashboard is still not running - will start it now
				if (new File(perfectoMobileBrowserJarLocation).exists()) {
					try {
						Runtime.getRuntime().exec("java -jar " + perfectoMobileBrowserJarLocation + " dashboard");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					showErrorDialog("Perfecto Dashboard can't be launched!", perfectoMobileBrowserJarLocation + " wasn't found");
				}
			}
		}
	}

	private void loadPerfectoProperties() {

		if (new File("perfecto/perfecto.properties").exists()) {
			
			Properties prop = new Properties();
			
			try {
				prop.load(new FileInputStream("perfecto/perfecto.properties"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			cloudTextField.setText(prop.getProperty("perfectoCloud"));
			usernameTextField.setText(prop.getProperty("perfectoUsername"));
			passwordField.setText(prop.getProperty("perfectoPassword"));
		}
	}

	@SuppressWarnings("deprecation")
	private boolean validatePerfectoProperties() {
		
		if (cloudTextField.getText().trim().equals("")) {
			showWarningDialog("Can't launch Perfecto Lab/Dashboard", "Cloud field can't be empty");
			return false;
		}
		else if (usernameTextField.getText().trim().equals("")) {
			showWarningDialog("Can't launch Perfecto Lab/Dashboard", "Username field can't be empty");
			return false;
		}
		else if (passwordField.getText().trim().equals("")) {
			showWarningDialog("Can't launch Perfecto Lab/Dashboard", "Password field can't be empty");
			return false;
		}
		
		return true;
	}
	
	private void showWarningDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	private void showErrorDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	@SuppressWarnings("deprecation")
	private void savePerfectoProperties() {

		Properties prop = new Properties();

		try {
			OutputStream outputStream = new FileOutputStream("perfecto/perfecto.properties");
			prop.setProperty("perfectoCloud", cloudTextField.getText());
			prop.setProperty("perfectoUsername", usernameTextField.getText());
			prop.setProperty("perfectoPassword", passwordField.getText());
			prop.store(outputStream, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTextField cloudTextField;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JButton perfectoDashboardButton;
	private javax.swing.JButton perfectoLabButton;
	private javax.swing.JTextField usernameTextField;
	// End of variables declaration//GEN-END:variables
}
