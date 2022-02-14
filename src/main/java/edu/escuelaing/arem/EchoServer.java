package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] arg) throws IOException {


        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(35000);

        }catch (Exception e){
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        }catch (Exception e){
            System.err.println("Accept failed.");
            System.exit(1);

        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null){
            System.out.println("Mensaje: " + inputLine);
            outputLine = "Respuesta: " + inputLine ;
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye."))
                break;
        }

        clientSocket.close();
        out.close();
        in.close();
        serverSocket.close();
    }


}
