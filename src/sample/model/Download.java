package sample.model;

import sample.controller.Controller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import static sample.model.Downloader.*;




public  class Download extends Thread {
     Controller c1 = new Controller();

    public Download(String name) {
        this.setName(name);
    }

    private String name;
    private FileDownload d;


    public  void checkConection() {

        try {
            URL url1 = new URL(name);

            URLConnection connection = url1.openConnection();
            connection.connect();

        } catch (Exception e) {

            c1.setTextLable("Connection fail or your link is not valid!!! ");


        }
    }


    @Override
    public void run() {
        int temp=0;
        String dir = creatDirectory() + getName().substring(getName().lastIndexOf("/") + 1);
        File file = new File(dir);
        try {
            temp=search(name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new URL(getName()).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(dir)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                double size = file.length();

                size=size/(1024*1024);
                size=round(size,2);
                files.get(temp).setDownloaded(size);
                System.out.println(size);
//                sleep(2000);


            }

        } catch (Exception e) {

            c1.setTextLable("your link is not valid!!! ");

        }


    }


    public  String creatDirectory() {
        checkConection();
        String type = files.get(files.size() - 1).getType();
        String os = System.getProperty("os.name");
        String dir = "";
        if (os.equals("Linux")) {


            String userName = System.getProperty("user.name");
            dir = "/home/" + userName + "/Downloads/downloadManager/";


            if (type.equals("mp3")) {

                File theDir = new File(dir + "/Music/");

                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                dir = dir + "/Music/";
            } else if (type.equals("mkv") || type.equals("mp4")) {
                File theDir = new File(dir + "/Movie/");

                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                dir = dir + "/Movie/";
            } else if (type.equals("apk")) {

                File theDir = new File(dir + "Apk/");

                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                dir = dir + "/Apk/";
            } else if (type.equals("rar")) {

                File theDir = new File(dir + "/rar/");

                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                dir = dir + "/rar/";
            } else {
                File theDir = new File(dir + "/else/");

                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                dir = dir + "/else/";
            }

            return dir;

        }
        return dir;
    }

    /* for windows path
     * C:\Users\TOFIGHHED\Downloads*/
//        else if (os.equals("Windows 10") || os.equals("Windows 8") || os.equals("Windows 7")) {
//
//
//            String userName = System.getProperty("user.name");
//            dir = "/home/" + userName + "/Downloads/downloadManager/";
//
//
//            if (type.equals("mp3")) {
//
//                File theDir = new File(dir + "/Music/");
//
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                }
//                dir = dir + "/Music/";
//            } else if (type.equals("mkv") || type.equals("mp4")) {
//                File theDir = new File(dir + "/Movie/");
//
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                }
//                dir = dir + "/Movie/";
//            } else if (type.equals("apk")) {
//
//                File theDir = new File(dir + "Apk/");
//
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                }
//                dir = dir + "/Apk/";
//            } else if (type.equals("rar")) {
//
//                File theDir = new File(dir + "/rar/");
//
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                }
//                dir = dir + "/rar/";
//            } else {
//                File theDir = new File(dir + "/else/");
//
//                if (!theDir.exists()) {
//                    theDir.mkdirs();
//                }
//                dir = dir + "/else/";
//            }
//            return dir;
//
//        }
//        return dir;
//    }


}

