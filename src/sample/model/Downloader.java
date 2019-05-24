package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Downloader {


    public static ArrayList<FileDownload> files = new ArrayList<>();
    public static ArrayList<FileDownload> downloadedFiles = new ArrayList<>();
    public static ArrayList<FileDownload> downloadingFiles = new ArrayList<>();
    public static ArrayList<FileDownload> failFiles = new ArrayList<>();


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


    public void addFile(FileDownload fileDownload) {

        files.add(fileDownload);


    }


    public String getTime() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return time.format(formatter);
    }

    public static int search(String url)  {

        int d = -1;


        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getName().equals(url)) {
                d = i;
            }
        }

        return d;
    }

    public ObservableList getDetail() {
        ArrayList<FileDownload> d = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {

            d.add(files.get(i));
        }
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(d);

        return detail;
    }

    public int searchDownloading(String name) {
        int index = 0;
        for (int i = 0; i < downloadingFiles.size(); i++) {
            if (downloadingFiles.get(i).getName().equals(name)) {
                return i;
            }
        }


        return index;

    }


    /* ta places ragham ashar namyesh mide */

    public static double rond(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    public ObservableList downloadedFileDetail() {

        ArrayList<FileDownload> downloaded = new ArrayList<>();
        for (int i = 0; i < downloadingFiles.size(); i++) {

            downloaded.add(downloadedFiles.get(i));
        }
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(downloaded);

        return detail;
    }


    public ObservableList downloadingFileDetail() {

        ArrayList<FileDownload> downloading = new ArrayList<>();
        downloading.addAll(downloadingFiles);
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(downloading);

        return detail;
    }


    public ObservableList failFileDetail() {
        ArrayList<FileDownload> fail = new ArrayList<>();
        for (int i = 0; i < failFiles.size(); i++) {

            fail.add(failFiles.get(i));
        }
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(fail);

        return detail;
    }


//    public void writeFile() throws IOException {
//        String systemUser = System.getProperty("user.name");
//
//        FileWriter saveDetail = new FileWriter("/home/" + systemUser + "/Downloads/downloadManager/text.txt");
//        for(int i= 0 ; i < files.size();i++){
//            saveDetail.write(files.get(i));
//        }
//    }

}






