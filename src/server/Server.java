package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList<Socket> sockets = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(9178);
            System.out.println("Сервер запущен");
            while (true){
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                System.out.println("Клиент подключился");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream is = new DataInputStream(socket.getInputStream());
                            while (true){
                                String request = is.readUTF();
                                System.out.println(request);
                                for (Socket socket1 : sockets){
                                    DataOutputStream out = new DataOutputStream(socket1.getOutputStream());
                                    out.writeUTF(request);
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
