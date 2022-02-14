package edu.escuelaing.arem;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Reto1Http {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(35000);

        }catch (Exception e){
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            try {

                System.out.println("Listo para recibir...");
                clientSocket = serverSocket.accept();

            } catch (Exception e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;


            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\edward.porras.LABINFO\\Downloads\\ClientesYServicios\\resultado.html"));

            outputLine = "HTTP/1.1 OK\r\n"
                    + "Conten-Type: text/html\r\n"
                    + "\r\n";
            while (leer.readLine() != null) {
                outputLine += leer.readLine();
            }
            System.out.println(outputLine);
        }

    }

}



