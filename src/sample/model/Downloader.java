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


    static ArrayList<FileDownload> fileDownloads = new ArrayList<>();


    public int getFileSize(String url1) throws MalformedURLException {

        URL url = new URL(url1);
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);


        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }

//    public void resumeDownload(String url1) throws IOException {
//
//
//        int index = search(url1);
//        String fileName = fileDownloads.get(index).getName();
//
//        URL url = new URL(url1);
//        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//        httpConnection.setRequestMethod("HEAD");
//        long removeFileSize = httpConnection.getContentLengthLong();
//        long existingFileSize = fileName.length();
//        if (existingFileSize < fileLength) {
//            URLConnection httpFileConnection = null;
//            httpFileConnection.setRequestProperty(
//                    "Range",
//                    "bytes=" + existingFileSize + "-" + fileLength
//            );
//        }
//
//
//    }


    public void addFile(FileDownload fileDownload) {

        fileDownloads.add(fileDownload);


    }


    public String getTime() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return time.format(formatter);
    }

    static int search(String url) throws MalformedURLException {

        URL url1 = new URL(url);

        for (int i = 0; i < fileDownloads.size(); i++) {
            if (fileDownloads.get(i).getUrl().equals(url1)) {
                return i;
            }
        }
        return -1;
    }

    public ObservableList getDetail() {
        ArrayList<FileDownload> d = new ArrayList<>();
        for (int i = 0; i < fileDownloads.size(); i++) {

            d.add(fileDownloads.get(i));
        }
        ObservableList<FileDownload> detail = FXCollections.observableArrayList(d);

        return detail;
    }
}






