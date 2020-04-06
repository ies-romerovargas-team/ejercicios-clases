package com.company;

public class Main {

    public static void main(String[] args) {
        ChatDirecto chat = new ChatDirecto();
        chat.escuchar();
        chat.conectar("127.0.0.1");
    }
}
