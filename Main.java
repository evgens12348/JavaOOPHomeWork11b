package com.gmail.s12348.evgen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		int n = 1;
		try (ServerSocket ss = new ServerSocket(20000)) {
			for (;;) {
				Socket soc = ss.accept();
				ClientSocket cs = new ClientSocket(soc, message(n));
				n += 1;
			}
		} catch (IOException e) {
			System.out.println("Error to server Socket Open!");
		}

	}

	public static String message(int n) {
		String ansver = "";
		ansver += "<html><head><meta charset='utf-8'></head><body><p>System configuration: </p><br>";
		ansver += "<table border='2' cellpadding='5' ><tr><th>Parametr</th><th>Name</th></tr>";
		ansver += "<tr>";
		ansver += "<td>Operating system name</td>";
		ansver += "<td>" + System.getProperty("os.name") + "</td>";
		ansver += "</tr>";
		ansver += "<tr>";
		ansver += "<td>Operating system architecture</td>";
		ansver += "<td>" + System.getProperty("os.arch") + "</td>";
		ansver += "</tr>";
		ansver += "<tr>";
		ansver += "<td>Operating system version</td>";
		ansver += "<td>" + System.getProperty("os.version") + "</td>";
		ansver += "</tr>";
		ansver += "<tr>";
		ansver += "<td>Number of processors</td>";
		ansver += "<td>" + Runtime.getRuntime().availableProcessors() + "</td>";
		ansver += "</tr>";
		ansver += "<tr>";
		ansver += "<td>Free memory</td>";
		ansver += "<td>" + Runtime.getRuntime().totalMemory() + " byte" + "</td>";
		ansver += "</tr>";
		ansver += "</table>";
		ansver += "<p>Number of request: " + n + "</p></body></html>";
		return ansver;
	}

}
