package edu.escuelaing.arem;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Reto1Http {


    public static void main(String[] args) throws IOException {
        while (true) {

            ServerSocket serverSocket = null;

            try {

                serverSocket = new ServerSocket(getPort());

            } catch (Exception e) {
                System.err.println("Could not listen on port:"+getPort());
                System.exit(1);
            }

            Socket clientSocket = null;

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


            String path = "src/main/resources/public/";
            String url = "";
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.startsWith("GET ")) {
                    url = inputLine.substring(4).split(" ")[0];
                }
                System.out.println("Received: " + inputLine);

                if (!in.ready()) {
                    break;
                }


            }

            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Conten-Type: text/html\r\n"
                    + "\r\n";


            if(url.contains(".jpg")) {
                path = path + "img" + url;
                BufferedImage imagen = ImageIO.read(new File(path));
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                ImageIO.write(imagen, "jpg", buffer);
                dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
                dataOutputStream.writeBytes("Conten-Type: text/html\n");
                dataOutputStream.writeBytes("\r\n");
                dataOutputStream.write(buffer.toByteArray());

            }else{
                path = path+url;
                String cadena = "";
                System.out.println(path);
                BufferedReader leer;
                if(path.equals("src/main/resources/public/") || path.equals("src/main/resources/public//") ){
                    leer = new BufferedReader(new FileReader(path+"index.html"));
                }else {
                    leer = new BufferedReader(new FileReader(path));
                }
                while ((cadena = leer.readLine()) != null) {
                    outputLine += cadena+"\n";

                }

                System.out.println(outputLine);
                out.println(outputLine);
            }


            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();


        }
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 3478;
    }

}



