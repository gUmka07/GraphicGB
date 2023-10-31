package ru.gb.server;

import javax.annotation.processing.Messager;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class ServerGUI extends JFrame implements Message {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnExit;
    JToggleButton btnStart;
    JTextArea textArea;


    public ServerGUI() throws HeadlessException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Сервер"); //заголовок
        setResizable(false); //не изменяемое окно



        btnStart = new JToggleButton("Влючить");
        btnStart.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (btnStart.isSelected()) {
                    btnStart.setText("Отключить");
                } else {
                    btnStart.setText("Включить");
                }
            }
        });
        btnExit = new JButton("Выход");
        textArea = new JTextArea(); //мнгострочное текстовое поле
        JScrollPane scrollPane = new JScrollPane(textArea);
        btnStart.addActionListener(new ActionListener() { //добавить слушателя действия
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnStart.isSelected()) {
                    readingMessage(" Сервер включен");
                    //todo когда сервер включен. создать поток. соккет.
                    Server.readingMessage(8080);
                } else {
                    readingMessage(" Сервер выключен");
                    Server.stop();
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(scrollPane);
        textArea.setEditable(false);

        JPanel panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(btnStart);
        panBotton.add(btnExit);
        add(panBotton, BorderLayout.SOUTH);

        setVisible(true);
    }


    @Override
    public void readingMessage(String text) {
        appendLog(text);
    }

    private void appendLog(String text) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String message = currentDateTime.toString();
        textArea.append("[" + message + "]" + text + "\n");
    }

}
