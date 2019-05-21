package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Downloader {


    public ArrayList<File> files = new ArrayList<>();


    public BigInteger getFileSize(String url) throws IOException {

        URL url1 = new URL(url);

        HttpURLConnection conecction = (HttpURLConnection) url1.openConnection();
        conecction.getInputStream();
        BigInteger size = BigInteger.valueOf(conecction.getContentLength());
        return size;
    }


//    public void resumeDownload(String url) throws IOException {
//
//        int fileLength = getFileSize(url);
//        URLConnection connection=null;
//
//        URL url1 = new URL(url);
//        HttpURLConnection httpConnection = (HttpURLConnection) url1.openConnection();
////        httpConnection.setRequestMethod("HEAD");
////        long removeFileSize = httpConnection.getContentLengthLong();
//
//
//        long existingFileSize = url.substring(url.lastIndexOf("/")+1).length();
//        if (existingFileSize < fileLength) {
//
//            connection.setRequestProperty(
//                    "Range",
//                    "bytes=" + existingFileSize + "-" + fileLength);
//        }
//    }


    public void addFile(File file) {

        files.add(file);


    }


    public String getTime() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return time.format(formatter);
    }

    public int search(String url) throws MalformedURLException {

        URL url1 = new URL(url);

        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getUrl().equals(url1)) {
                return i;
            }
        }
        return -1;
    }

    public ObservableList getNameDetail(){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0;i<files.size();i++){
           names.add(files.get(i).getName());
        }
        ObservableList<String> detail = FXCollections.observableArrayList(names);

        return detail;
    }

    public ObservableList getsizeDetail(){
        ArrayList<BigInteger> size = new ArrayList<>();
        for(int i=0;i<files.size();i++){
            size.add(files.get(i).getSize());
        }
        ObservableList<BigInteger> detail = FXCollections.observableArrayList(size);

        return detail;
    }

}





