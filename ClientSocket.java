package com.gmail.s12348.evgen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket implements Runnable {
	private Socket socket;
	private String info;
	private Thread th;

	public ClientSocket(Socket socket, String info) {
		super();
		this.socket = socket;
		this.info = info;
		th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		try (InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os)) {
			byte[] rec = new byte[is.available()];
			is.read(rec);
			String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type:text/html\r\n"
					+ "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
			pw.print(response + info);
			pw.flush();
		} catch (IOException e) {
			System.out.println(e.toString());
		}

	}

}
