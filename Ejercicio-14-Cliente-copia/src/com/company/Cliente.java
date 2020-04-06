package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {

    public static void main(String args[]) {
        final int time = 75;
        //boolean CHAT_SESSION_ALIVE = false;
        int port = 9999;
        String hostIP = "127.0.0.1";

        try {
            Socket skt = new Socket(hostIP, port);
            System.out.println("Client has connected with server " + hostIP + ":" + port);
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
                            String msg = new Scanner(System.in).nextLine();
                            System.out.println("Sending message: '" + msg + "'");
                            out.print(msg + "\n");
                            out.flush();
                            Thread.sleep(time);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }).start();

            //in.close();
            //out.close();
            //skt.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
