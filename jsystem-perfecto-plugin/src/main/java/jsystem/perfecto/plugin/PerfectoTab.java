package jsystem.perfecto.plugin;

import java.util.logging.Logger;

import javax.swing.JPanel;

import jsystem.treeui.interfaces.JSystemTab;
import jsystem.treeui.teststable.TestsTableController;

public class PerfectoTab implements JSystemTab {

	private static Logger log = Logger.getLogger(PerfectoTab.class.getName());
	
	@Override
	public JPanel init() {

		PerfectoPanel perfectoPanel = null;
		
		try {
			perfectoPanel = new PerfectoPanel();
		}
		catch (Exception e) {
			log.warning("Failed to open the Perfecto Mobile Lab tab due to " + e.getMessage());
			e.printStackTrace();
		}
		return perfectoPanel;
	}

	@Override
	public String getTabName() {
		return "Perfecto Mobile";
	}

	@Override
	public void setTestsTableController(TestsTableController testsTableController) {
		// no need to implement this method
	}

}
