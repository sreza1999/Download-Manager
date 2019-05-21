package sample.model;

import sample.controller.Controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;

import java.net.URL;
import java.net.URLConnection;

public class Download extends Thread {
    Controller c1 = new Controller();

    public Download(String name) {
        this.name = name;
    }

    private String name;


    public void checkConection(String url){

        try {
            URL url1 = new URL(url);

            URLConnection connection = url1.openConnection();
            connection.connect();

        } catch (Exception e) {

            c1.setTextLable("Connection fail or your link is not valid!!! ");


        }
    }







    @Override
    public void run() {
        checkConection(name);


        try (BufferedInputStream in = new BufferedInputStream(new URL(name).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(name.substring(name.lastIndexOf("/") + 1))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {

            c1.setTextLable("your link is not valid!!! ");

        }


    }


}

