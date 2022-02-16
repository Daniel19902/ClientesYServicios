package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio1 {


    public static void main(String[] arg) throws Exception {

        //URL url = new URL("https://es.symbolab.com:80/solver/step-by-step/5%2B5?or=input#symbolab");
        URL url = new URL("http://localhost:35000/");

        System.out.println("Protocolo: "+url.getProtocol());
        System.out.println("Authority: "+url.getAuthority());
        System.out.println("Host: "+url.getHost());
        System.out.println("Port: "+url.getPort());
        System.out.println("Path: "+url.getPath());
        System.out.println("Query: "+url.getQuery());
        System.out.println("File: "+url.getFile());
        System.out.println("Ref: "+url.getRef());



    }



}
