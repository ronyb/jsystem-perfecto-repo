package jsystem.perfecto.plugin;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * This class was created for debugging purposes.
 * Use this class to test the PerfectoPanel GUI functionality without needing to install the plugin in JSystem.
 * @author Rony Byalsky
 */
public class PerfectoPanelTest {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("JSystem Perfecto Plugin Test");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new PerfectoPanel(), BorderLayout.CENTER);
		
        frame.setVisible(true);
	}
}
