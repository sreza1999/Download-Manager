package sample.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import sample.model.Download;
import sample.model.Downloader;
import sample.model.FileDownload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static sample.model.Downloader.*;


public class Controller {

    public Downloader downloader = new Downloader();

    @FXML
    private TextField textfiled = new TextField();

    @FXML
    private Label showMassage = new Label();
    @FXML
    private Label file1Progress = new Label();
    @FXML
    private Label file2Progress = new Label();
    @FXML
    private Label file3Progress = new Label();
    @FXML
    private Label file4Progress = new Label();

    @FXML
    private TableView downloadView = new TableView<>();

    @FXML
    private TableColumn nameColumn = new TableColumn();
    @FXML
    private TableColumn sizeColumn = new TableColumn();
    @FXML
    private TableColumn statusColumn = new TableColumn();
    @FXML
    private TableColumn categoryColumn = new TableColumn();
    @FXML
    private TableColumn downloadedColumn = new TableColumn();
    @FXML
    private ProgressBar progress1 = new ProgressBar();
    @FXML
    private ProgressBar progress2 = new ProgressBar();
    @FXML
    private ProgressBar progress3 = new ProgressBar();
    @FXML
    private ProgressBar progress4 = new ProgressBar();


    public void Test() throws MalformedURLException {

    }


    public void makeFile() {
        String url = textfiled.getText();
        try {
            URL u1 = new URL(url);
            String name = url.substring(url.lastIndexOf("/") + 1);
            String time = downloader.getTime();
            double size = downloader.getFileSize(url)/(1024*1024);
            size=round(size,2);
            String status = "Downloading";

            FileDownload fileDownload = new FileDownload(name, size, time, u1, status, url.substring(url.lastIndexOf(".") + 1));
            downloader.addFile(fileDownload);
        } catch (IOException e) {
            setTextLable("Not valid!!!");
        }


    }


    public void startDownloading() throws MalformedURLException {
        String url = textfiled.getText();
        makeFile();
        setColumn();
        textfiled.setText("");
        Download d1 = new Download(url);
        int temp=search(textfiled.getText());

        d1.start();

        new Thread(() -> {
            while (files.get(0).getDownloaded() < files.get(temp).getSize()) {
                double size = files.get(temp).getDownloaded();
                size= (long) round(size,2);
                if(temp==0){
//                    progress1.setProgress(0.22);
                    progress1.setProgress(size / (int) files.get(temp).getSize());
                }
                else if(temp==1){
                    file2Progress.setText(files.get(temp).getName());
                    file2Progress.setTextFill(Color.GREEN);
                    progress2.setProgress(size / (int) files.get(temp).getSize());


                }
                else if(temp==2){
                    file3Progress.setText(files.get(temp).getName());
                    file3Progress.setTextFill(Color.GREEN);
                    progress3.setProgress(size / (int) files.get(temp).getSize());

                }
                else if(temp == 3){
                    file4Progress.setText(files.get(temp).getName());
                    file4Progress.setTextFill(Color.GREEN);
                    progress4.setProgress(size / (int) files.get(temp).getSize());

                }


            }

        }).start();


    }

    public void setTextLable(String massage) {
        showMassage.setText(massage);
        showMassage.setTextFill(Color.RED);


    }

    public void setColumn() {


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        downloadedColumn.setCellValueFactory(new PropertyValueFactory<>("downloaded"));
        ObservableList d = downloader.getDetail();

        downloadView.setItems(d);
    }


//    public void setColumn(FileDownload d) {
//
//
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
//        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
//        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
////        downloadedColumn.setCellValueFactory(new PropertyValueFactory<>("downloaded"));
//        progress1.setProgress(22*100);
//        ObservableList  <FileDownload> detail = FXCollections.observableArrayList(d);
//        downloadView.setItems(detail);
//    }


}

