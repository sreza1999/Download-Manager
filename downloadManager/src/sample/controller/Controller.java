package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Downloader;


public class Controller {
    Downloader downloader =new Downloader();
    @FXML
    private Button AddUrl;
    @FXML
    private TextField textfiled=new TextField();




    public void getUrl(){

        downloader.download(textfiled.getText());

    }







}

