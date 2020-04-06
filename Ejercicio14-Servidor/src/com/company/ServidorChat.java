package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServidorChat {
    public static void main(String args[]) {
        final int time = 75;
        //boolean CHAT_SESSION_ALIVE = false; 
        int port = 9999;

        try {
            System.out.println("Starting chat server using the port : " + port);
            ServerSocket srvr = new ServerSocket(port);
            Socket skt = srvr.accept();
            System.out.println("Server has connected with client         " + skt.getInetAddress());
            //CHAT_SESSION_ALIVE = true;

            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (in.ready()) {
                                String msg = in.readLine();
                                System.out.println("receive message: '" + msg + "'");
                                Thread.sleep(time);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(time);
                            String msg = new Scanner(System.in).nextLine();
                            System.out.println("Sending message: '" + msg + "'");
                            out.print(msg + "\n");
                            out.flush();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }).start();

            //in.close();
            //out.close();
            //skt.close();
            //srvr.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}