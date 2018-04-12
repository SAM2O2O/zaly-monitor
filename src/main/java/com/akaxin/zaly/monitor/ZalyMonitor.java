package com.akaxin.zaly.monitor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public abstract class ZalyMonitor {

	public static final long INTERVAL_TIME = 1 * 1000;

	private long intervalCount = 0;// 间隔输出次数

	private List<String> headers;

	private Map<String, String> monitorData = new ConcurrentHashMap<String, String>();

	abstract public List<String> buidHeader();

	abstract public void buildBody(Map<String, String> bodyMap);

	abstract public long getIntervalTime();

	abstract public Logger getMonitorLogger();

	abstract public void clear();

	public List<String> getHeader() {
		if (headers != null && headers.size() > 0) {
			return headers;
		}
		headers = buidHeader();
		return headers;
	}

	public Map<String, String> getBody() {
		buildBody(monitorData);
		return monitorData;
	}

	public void output(List<ZalyMonitor> monitors, Map<String, String> monitorData) {
		Logger logger = getMonitorLogger();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// header
		StringBuffer sb = new StringBuffer();
		List<String> monitorHeaders = this.getHeader();
		if (monitorHeaders != null) {
			for (String header : this.getHeader()) {
				sb.append(header).append("\t");
			}
		}
		sb.append("time");
		if (intervalCount % 15 == 0) {
			String host = "localhost";
			logger.info(host + ":" + sdf.format(System.currentTimeMillis()));
			logger.info(sb.toString());
			intervalCount = 0;
		}

		// body
		StringBuffer stat = new StringBuffer();
		for (String header : this.getHeader()) {
			String body = monitorData.get(header);
			int tabCount = (header.length() >>> 3) + 1;
			stat.append(body);
			for (int i = 0; i < tabCount; i++) {
				stat.append("\t");
			}
		}

		stat.append(sdf.format(System.currentTimeMillis()));
		logger.info(stat.toString());
		intervalCount++;
	}

	protected ZalyCounter getCounter() {
		return new ZalyCounter();
	}

	protected ZalyCounter getCounter(int defaultValue) {
		return new ZalyCounter(defaultValue);
	}

}
