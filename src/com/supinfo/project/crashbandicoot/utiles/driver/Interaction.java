package com.supinfo.project.crashbandicoot.utiles.driver;

import com.supinfo.project.crashbandicoot.entities.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Interaction {

    // Cette classe permet de communiquer avec le driver prenant en charge les manettes de jeux.
    // Les instructions reçu sont ensuite converti en donnée compréhensible par le jeu.

    static Socket socket;
    static BufferedReader bufferedReader;

    static Thread t;

    static boolean isConnected = false;

    // connexion au driver
    public static void connect (int port) {
        try {
            socket = new Socket("localhost", port);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("[GP] Connected !");

            isConnected = true;

            listening();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String req = null;
    // écoute des données envoyés par le driver
    public static void listening () {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isConnected) {
                    try {
                        req = bufferedReader.readLine();

                        if (req != null) {
                            switch(req) {
                                case "RIGHT":
                                    Player.moveRight = true;
                                    Player.moveLeft = false;
                                    System.out.println("[GP] RIGHT");
                                    break;
                                case "LEFT":
                                    Player.moveLeft = true;
                                    Player.moveRight = false;
                                    System.out.println("[GP] LEFT");
                                    break;
                                case "STOP":
                                    Player.moveRight = false;
                                    Player.moveLeft = false;
                                    System.out.println("[GP] STOP");
                                    break;
                                case "JUMP":
                                    Player.moveJump = true;
                                    System.out.println("[GP] JUMP");
                                    break;
                                case "TORNADO":
                                    System.out.println("[GP] TORNADO");
                                    break;
                                case "B":
                                    Player.gamepadB = true;
                                    System.out.println("[GP] B");
                                    break;
                                default:
                                    break;
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    // déconnexion du driver (fermer les flux)
    public static void disconnect () {
        if(isConnected == true) {
            try {
                isConnected = false;

                bufferedReader.close();
                socket.close();
                t.interrupt();
                t.currentThread().interrupt();
                t.stop();

                System.out.println("[GP] Disconnected !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
