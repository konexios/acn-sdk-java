package com.arrow.acn.client.cloud.aws.defender;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDefenderReport {
	private Header header = new Header();
	private Metrics metrics = new Metrics();

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public static class Metrics {
		@JsonProperty("listening_tcp_ports")
		private Ports listeningTcpPorts = new Ports();
		@JsonProperty("listening_udp_ports")
		private Ports listeningUdpPorts = new Ports();
		@JsonProperty("tcp_connections")
		private TcpConnections tcpConnections = new TcpConnections();
		@JsonProperty("network_stats")
		private NetworkStats networkStats = new NetworkStats();

		public Ports getListeningTcpPorts() {
			return listeningTcpPorts;
		}

		public void setListeningTcpPorts(Ports listeningTcpPorts) {
			this.listeningTcpPorts = listeningTcpPorts;
		}

		public Ports getListeningUdpPorts() {
			return listeningUdpPorts;
		}

		public void setListeningUdpPorts(Ports listeningUdpPorts) {
			this.listeningUdpPorts = listeningUdpPorts;
		}

		public TcpConnections getTcpConnections() {
			return tcpConnections;
		}

		public void setTcpConnections(TcpConnections tcpConnections) {
			this.tcpConnections = tcpConnections;
		}

		public NetworkStats getNetworkStats() {
			return networkStats;
		}

		public void setNetworkStats(NetworkStats networkStats) {
			this.networkStats = networkStats;
		}
	}

	public static class Ports {
		private List<Port> ports = new ArrayList<>();
		private int total = 0;

		public List<Port> getPorts() {
			return ports;
		}

		public void setPorts(List<Port> ports) {
			this.ports = ports;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}

	public static class Header {
		@JsonProperty("report_id")
		private long reportId = Instant.now().getEpochSecond();
		private String version = "1.0";

		public long getReportId() {
			return reportId;
		}

		public void setReportId(long reportId) {
			this.reportId = reportId;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
	}

	public static class Port {
		@JsonProperty("interface")
		private String tcpInterface;
		private int port;

		public Port() {
		}

		public Port(String tcpInterface, int port) {
			this.tcpInterface = tcpInterface;
			this.port = port;
		}

		public String getTcpInterface() {
			return tcpInterface;
		}

		public void setTcpInterface(String tcpInterface) {
			this.tcpInterface = tcpInterface;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

	public static class TcpConnections {
		@JsonProperty("established_connections")
		private Connections establishedConnections = new Connections();

		public Connections getEstablishedConnections() {
			return establishedConnections;
		}

		public void setEstablishedConnections(Connections establishedConnections) {
			this.establishedConnections = establishedConnections;
		}
	}

	public static class Connections {
		private List<Connection> connections = new ArrayList<>();
		private int total = 0;

		public List<Connection> getConnections() {
			return connections;
		}

		public void setConnections(List<Connection> connections) {
			this.connections = connections;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}

	public static class Connection {
		@JsonProperty("local_interface")
		private String localInterface;
		@JsonProperty("local_port")
		private int localPort;
		@JsonProperty("remote_addr")
		private String remoteAddr;

		public Connection() {
		}

		public Connection(String localInterface, int localPort, String remoteAddr) {
			super();
			this.localInterface = localInterface;
			this.localPort = localPort;
			this.remoteAddr = remoteAddr;
		}

		public String getLocalInterface() {
			return localInterface;
		}

		public void setLocalInterface(String localInterface) {
			this.localInterface = localInterface;
		}

		public int getLocalPort() {
			return localPort;
		}

		public void setLocalPort(int localPort) {
			this.localPort = localPort;
		}

		public String getRemoteAddr() {
			return remoteAddr;
		}

		public void setRemoteAddr(String remoteAddr) {
			this.remoteAddr = remoteAddr;
		}
	}

	public static class NetworkStats {
		@JsonProperty("bytes_in")
		private long bytesIn = 0;
		@JsonProperty("bytes_out")
		private long bytesOut = 0;
		@JsonProperty("packets_in")
		private long packetsIn = 0;
		@JsonProperty("packets_out")
		private long packetsOut = 0;

		public long getBytesIn() {
			return bytesIn;
		}

		public void setBytesIn(long bytesIn) {
			this.bytesIn = bytesIn;
		}

		public long getBytesOut() {
			return bytesOut;
		}

		public void setBytesOut(long bytesOut) {
			this.bytesOut = bytesOut;
		}

		public long getPacketsIn() {
			return packetsIn;
		}

		public void setPacketsIn(long packetsIn) {
			this.packetsIn = packetsIn;
		}

		public long getPacketsOut() {
			return packetsOut;
		}

		public void setPacketsOut(long packetsOut) {
			this.packetsOut = packetsOut;
		}
	}
}
