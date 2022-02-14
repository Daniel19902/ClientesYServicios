package edu.escuelaing.arem;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ejercicio2 {

    public static void main(String[] arg) throws IOException {

        URL url = new URL("https://medlineplus.gov/spanish/genetica/entender/basica/celula/");


        URLConnection urlConnection = url.openConnection();

        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        for (Map.Entry<String, List<String>> entry: entrySet){
            String headerName = entry.getKey();

            if(headerName != null){
                System.out.println(headerName+":");
            }List<String> headerValues = entry.getValue();
            for (String value: headerValues){
                System.out.println(value);
            }
            System.out.println("");
        }
        System.out.println("-------------message-body--------------");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputStream = null;
        String html = "";
        FileOutputStream fos = new FileOutputStream("resultado.html");
        ObjectOutputStream oss = new ObjectOutputStream(fos);
        while ((inputStream = bufferedReader.readLine()) != null){
            html += inputStream;
            System.out.println(inputStream);
        }
        oss.writeUTF(html);
        fos.close();
    }


}
