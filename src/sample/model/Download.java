package sample.model;

import sample.controller.Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.net.URL;
import java.net.URLConnection;

import static sample.model.Downloader.fileDownloads;

public class Download extends Thread  {
    Controller c1 = new Controller();

    public Download(String name) {

        this.setName(name);

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

        String dir =creatDirectory();
        try (BufferedInputStream in = new BufferedInputStream(new URL(getName()).openStream());
             FileOutputStream fileOutputStream= new FileOutputStream(dir+getName().substring(getName().lastIndexOf("/") + 1))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);


            }

        } catch (Exception e) {

            c1.setTextLable("your link is not valid!!! ");

        }


    }


    public String creatDirectory(){
        checkConection(getName());
        String type= fileDownloads.get(fileDownloads.size()-1).getType();

        String userName=System.getProperty("user.name");
        String dir = "/home/"+userName+"/Downloads/downloadManager/";


        if(type.equals("mp3")){

            File theDir = new File(dir+"/Music/");

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            dir=dir+"/Music/";
        }
        else if(type.equals("mkv") || type.equals("mp4")){
            File theDir = new File(dir+"/Movie/");

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            dir=dir+"/Movie/";
        }
        else if(type.equals("apk")){

            File theDir = new File(dir+"Apk/");

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            dir=dir+"/Apk/";
        }
        else if(type.equals("rar")){

            File theDir = new File(dir+"/rar/");

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            dir=dir+"/rar/";
        }
        else {
            File theDir = new File(dir+"/else/");

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            dir=dir+"/else/";
        }
    return dir;

    }






}

