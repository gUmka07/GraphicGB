package ru.gb.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server {
    static ServerSocket serverSocket;

    static void readingMessage(int port) {
        Runnable socketRunnable = () -> {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started on port " + port);

                while (true) {
                    Socket socket = serverSocket.accept();
                    // Обработка соединения с клиентом в отдельном потоке
                    Thread clientThread = new Thread(() -> {
                        // TODO: обработка запроса от клиента
                        // socket.getInputStream(), socket.getOutputStream() можно использовать для чтения и записи данных
                    });
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        // Создание и запуск потока для обработки сокета
        Thread socketThread = new Thread(socketRunnable);
        socketThread.start();
    }


    public static void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
