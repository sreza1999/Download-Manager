package sample.controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import sample.model.Download;
import sample.model.Downloader;
import sample.model.File;


import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class Controller {

    public Downloader downloader = new Downloader();
    @FXML
    private Button AddUrl;
    @FXML
    private TextField textfiled = new TextField();

    @FXML
    private Label showMassage = new Label();

    @FXML
    private TableView <String> downloadView = new TableView<>();

    @FXML
    private TableColumn <String,String> nameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Object, Object> sizeColumn = new TableColumn<>("size");
    @FXML
    private TableColumn<Object, Object> statusColumn = new TableColumn<>("status");

//    private TableColumn<Object, Object> nameColumn = new TableColumn<>("Name");



    public void makeFile() throws IOException {
        String url = textfiled.getText();
        try {
            URL u1 = new URL(url);
            String name = url.substring(url.lastIndexOf("/") + 1);
            String time = downloader.getTime();
            BigInteger size = downloader.getFileSize(url);
            String status = "1";
            File file = new File(name, size, time, u1, status, url.substring(url.lastIndexOf(".") + 1));
            downloader.addFile(file);
        } catch (IOException e) {
            setTextLable("Not valid!!!");
        }

    }


    public void startDownloading() throws IOException {
        String url = textfiled.getText();
        makeFile();
        setColumn();
        textfiled.setText("");
        Download d1 = new Download(url);
        d1.start();


    }

    public void setTextLable(String massage) {
        showMassage.setText(massage);
        showMassage.setTextFill(Color.RED);


    }




    public void setColumn() throws MalformedURLException {
        ObservableList details = downloader.getNameDetail();
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        downloadView.setItems(details);


//         details = downloader.getNameDetail();
//        sizeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
//        downloadView.setItems(details);



    }


}

