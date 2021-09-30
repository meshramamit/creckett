package com.creckett.messaging.application.rtmp.red5;

import java.util.ArrayList;
import java.util.List;

public class RTMPConnectionStatsSummary {
	
	private List<RTMPConnectionStat> stats = new ArrayList<RTMPConnectionStatsSummary.RTMPConnectionStat>();
	
	private long totalIncomingTraffic;
	
	private long totalOutgoingTraffic;
	
	

	public List<RTMPConnectionStat> getStats() {
		return stats;
	}

	public long getTotalIncomingTraffic() {
		return totalIncomingTraffic;
	}

	public long getTotalOutgoingTraffic() {
		return totalOutgoingTraffic;
	}

	public class RTMPConnectionStat {
		private String remoteAddress;
		
		private long readBytes;
		
		private long writtenBytes;

		public RTMPConnectionStat(String remoteAddress, long readBytes,
				long writtenBytes) {
			super();
			this.remoteAddress = remoteAddress;
			this.readBytes = readBytes;
			this.writtenBytes = writtenBytes;
		}

		public String getRemoteAddress() {
			return remoteAddress;
		}

		public long getReadBytes() {
			return readBytes;
		}

		public long getWrittenBytes() {
			return writtenBytes;
		}
		
		
		
	}

	public void addStat(String remoteAddress, long readBytes, long writtenBytes) {
		stats.add(new RTMPConnectionStat(remoteAddress, readBytes, writtenBytes));
		totalIncomingTraffic+=readBytes;
		totalOutgoingTraffic+=writtenBytes;
		
	}
}
