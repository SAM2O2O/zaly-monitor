package com.akaxin.zaly.test.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.akaxin.zaly.logs.LogCreater;
import com.akaxin.zaly.monitor.ZalyCounter;
import com.akaxin.zaly.monitor.ZalyMonitor;

public class PushMonitor extends ZalyMonitor {
	private static Map<String, ZalyCounter> data = new HashMap<String, ZalyCounter>();

	public static ZalyCounter COUNTER_A = new ZalyCounter(1);
	public static ZalyCounter COUNTER_B = new ZalyCounter(2);
	public static ZalyCounter COUNTER_C = new ZalyCounter(3);
	public static ZalyCounter COUNTER_D = new ZalyCounter(4);
	public static ZalyCounter COUNTER_E = new ZalyCounter(5);

	static {
		data.put("A", COUNTER_A);
		data.put("B", COUNTER_B);
		data.put("C", COUNTER_C);
		data.put("D", COUNTER_D);
		data.put("E", COUNTER_E);
	}

	@Override
	public List<String> buidHeader() {
		List<String> headers = new ArrayList<String>();
		headers.add("A");
		headers.add("B");
		headers.add("C");
		headers.add("D");
		headers.add("E");
		return headers;
	}

	@Override
	public void buildBody(Map<String, String> monitorData) {
		for (String header : getHeader()) {
			monitorData.put(header, data.get(header).getCountString());
		}
	}

	@Override
	public long getIntervalTime() {
		return 1000;
	}

	@Override
	public void clear() {
		for (ZalyCounter c : data.values()) {
			c.clear();
		}
	}

	@Override
	public Logger getMonitorLogger() {
		return LogCreater.createLogger("push");
	}

}
