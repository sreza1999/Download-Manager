package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Downloader {
    
    public  static ArrayList<String> search_result = new ArrayList<>();
    public  static ArrayList<FileDownload> files = new ArrayList<>();
    
    public ArrayList<String> search(String name)
    {
        for(int loop=0;loop<files.size();loop++)
        {
            if(files.get(loop).getName().equals(name)) 
            {
                search_result.add(files.get(loop).getName());
            }
            if(files.get(loop).getStatus().equals(name)) 
            {
                search_result.add(files.get(loop).getStatus());
            }
            if(files.get(loop).getPath().equals(name)) 
            {
                search_result.add(files.get(loop).getpath());
            }
            if(files.get(loop).getDate().equals(name)) 
            {
                search_result.add(files.get(loop).getDate());
            }
            if(files.get(loop).getType().equals(name)) 
            {
                search_result.add(files.get(loop).getType());
            }
            if(files.get(loop).getUrl().equals(name)) 
            {
                search_result.add(files.get(loop).getUrl());
            }
        }    
        return search_result;
        
    }

    


    public double getFileSize(String url1) throws MalformedURLException {

        URL url = new URL(url1);
        URLConnection connection = null;
        try {
            connection = url.openConnection();
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).setRequestMethod("HEAD");
            }
            connection.getInputStream();
            return connection.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);


        } finally {
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
        }
    }

//    public void resumeDownload(String url1) throws IOException {
//
//
//        URL url = new URL(url1);
//        FileDownload d ;
//        d=search(url1);
//        File file =new File(d.getPath());
//        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//        httpConnection.setRequestMethod("HEAD");
//        long removeFileSize = httpConnection.getContentLengthLong();
//        long existingFileSize = file.length();
//        if (existingFileSize < d.getSize()) {
//            URLConnection httpFileConnection = null;
//            httpFileConnection.setRequestProperty(
//                    "Range",
//                    "bytes=" + existingFileSize + "-" + d.getSize());
//        }
//
//
//    }


    public void addFile(FileDownload fileDownload) {

        files.add(fileDownload);


    }


    public String getTime() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return time.format(formatter);
    }

   public static   int search(String url) throws MalformedURLException {

        int d =0;


        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getUrl().equals(url)) {
                 d=i;
            }
        }

        return d ;
    }

    public ObservableList getDetail() {
        ArrayList<FileDownload> d = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {

            d.add(files.get(i));
        }
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(d);

        return detail;
    }



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}






