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
import java.net.URL;


public class Controller  {

    public Downloader downloader = new Downloader();

    @FXML
    private TextField textfiled = new TextField();

    @FXML
    private Label showMassage = new Label();

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



    public void makeFile() {
        String url = textfiled.getText();
        try {
            URL u1 = new URL(url);
            String name = url.substring(url.lastIndexOf("/") + 1);
            String time = downloader.getTime();
            int size = downloader.getFileSize(url);
            String status = "Downloading";
            FileDownload fileDownload = new FileDownload(name, size, time, u1, status, url.substring(url.lastIndexOf(".") + 1));
            downloader.addFile(fileDownload);
        } catch (IOException e) {
            setTextLable("Not valid!!!");
        }

    }


    public void startDownloading()  {
        String url = textfiled.getText();
        makeFile();
        setColumn();

        textfiled.setText("");
        Download d1 = new Download(url);
        d1.start();
        setColumn();

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

        ObservableList d=downloader.getDetail();
        downloadView.setItems(d);
    }




}

