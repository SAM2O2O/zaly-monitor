package com.akaxin.zaly.test.main;

import com.akaxin.zaly.monitor.JstatMonitor;
import com.akaxin.zaly.monitor.ZalyMonitorController;

public class MonitorTest {

	public static void main(String[] args) {
		ZalyMonitorController mm = new ZalyMonitorController();
		mm.addMonitor(new JstatMonitor());
		mm.addMonitor(new PushMonitor());
		mm.start();
	}

}
