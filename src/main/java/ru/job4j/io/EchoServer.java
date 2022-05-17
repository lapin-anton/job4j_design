package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        String msgPrefix = "/?msg=";
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    if (str != null && str.startsWith("GET")) {
                        String param = str.split("\\s")[1];
                        int index = param.indexOf(msgPrefix);
                        String msg = param.substring(index + msgPrefix.length());
                        if (msg.equals("Exit")) {
                            server.close();
                        } else {
                            out.write(msg.getBytes());
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
