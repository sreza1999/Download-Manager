package sample.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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
    private ProgressBar progress1 = new ProgressBar();
    @FXML
    private ProgressBar progress2 = new ProgressBar();
    @FXML
    private ProgressBar progress3 = new ProgressBar();
    @FXML
    private ProgressBar progress4 = new ProgressBar();
    @FXML
    private TitledPane moviePane = new TitledPane();
    @FXML
    private TitledPane rarPane = new TitledPane();
    @FXML
    private TitledPane musicPane = new TitledPane();
    @FXML
    private TitledPane elsePane = new TitledPane();


    public void Test() throws MalformedURLException {


    }


    public void makeFile() {
        String url = textfiled.getText();
        try {
            URL u1 = new URL(url);
            String name = url.substring(url.lastIndexOf("/") + 1);
            String time = downloader.getTime();
            double size = downloader.getFileSize(url) / (1024 * 1024);
            size = rond(size, 2);
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
        int filesIndex = search(url.substring(url.lastIndexOf("/") + 1));
        d1.start();
        downloadingFiles.add(files.get(filesIndex));
        int downloadingIndex = downloader.searchDownloading(files.get(filesIndex).getName());
        setProgressLable(downloadingIndex);



        new Thread(() -> {
            while (files.get(downloadingIndex).getDownloaded() < files.get(downloadingIndex).getSize()) {

                double size = files.get(downloadingIndex).getDownloaded();
                size = (long) rond(size, 2);
//                System.out.println(size);
                if (downloadingIndex == 0) {


                    progress1.setProgress(size / (int) files.get(downloadingIndex).getSize());
                } else if (downloadingIndex == 1) {

                    progress2.setProgress(size / (int) files.get(downloadingIndex).getSize());


                } else if (downloadingIndex == 2) {

                    progress3.setProgress(size / (int) files.get(downloadingIndex).getSize());

                } else if (downloadingIndex == 3) {

                    progress4.setProgress(size / (int) files.get(downloadingIndex).getSize());

                }


            }

            resetProgress(downloadingIndex);


            if (files.get(filesIndex).getSize() == files.get(filesIndex).getDownloaded()) {
                downloadedFiles.add(files.get(filesIndex));
                downloadingFiles.remove(downloader.searchDownloading(files.get(filesIndex).getName()));
                files.get(filesIndex).setStatus("downloaded");
            } else {
                failFiles.add(files.get(filesIndex));
                downloadingFiles.remove(downloader.searchDownloading(files.get(filesIndex).getName()));
                files.get(filesIndex).setStatus("fail");
            }
        }).start();


    }

    public void setTextLable(String massage)  {
        showMassage.setText(massage);
        showMassage.setTextFill(Color.RED);


    }

    public void setColumn() {
        ObservableList d = downloader.getDetail();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
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

    public void resetProgress(int temp) {

        if (temp == 0) {
            progress1.setProgress(0);
        } else if (temp == 1) {
            progress2.setProgress(0);

        } else if (temp == 2) {
            progress3.setProgress(0);

        } else if (temp == 3) {
            progress4.setProgress(0);

        }
    }

    public void setProgressLable(int temp) {


        if (temp == 0) {

            file1Progress.setText(files.get(temp).getName());
            file1Progress.setTextFill(Color.GREEN);
        } else if (temp == 1) {
            file2Progress.setText(files.get(temp).getName());
            file2Progress.setTextFill(Color.GREEN);
        } else if (temp == 2) {
            file3Progress.setText(files.get(temp).getName());
            file3Progress.setTextFill(Color.GREEN);
        } else if (temp == 3) {
            file4Progress.setText(files.get(temp).getName());
            file4Progress.setTextFill(Color.GREEN);
        }
    }


    public void setDownloadingColumn() {


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList d = downloader.downloadingFileDetail();

        downloadView.setItems(d);

    }


    public void setDownloadedColumn() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList d = downloader.downloadedFileDetail();

        downloadView.setItems(d);

    }


    public void failColumn() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList d = downloader.failFileDetail();

        downloadView.setItems(d);
    }


    /* dar thread javab nemidahad!!!*/
    public void resetProgressLable(int temp) {


        if (temp == 0) {

            file1Progress.setText("File 1");
            file1Progress.setTextFill(Color.RED);
        } else if (temp == 1) {
            file2Progress.setText("File 2");
            file1Progress.setTextFill(Color.RED);
        } else if (temp == 2) {
            file3Progress.setText("File 3");
            file1Progress.setTextFill(Color.RED);
        } else if (temp == 3) {
            file4Progress.setText("File 4");
            file1Progress.setTextFill(Color.RED);
        }
    }


    public void setCategory() {

        VBox movie = new VBox();
        VBox rar = new VBox();
        VBox music = new VBox();
        VBox Else = new VBox();
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getType().equals("rar") || files.get(i).getType().equals("zip")) {
                rar.getChildren().add(new Label(files.get(i).getName()));
            }

        }

        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getType().equals("mp4") || files.get(i).getType().equals("mkv")) {
                movie.getChildren().add(new Label(files.get(i).getName()));
            }

        }

        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getType().equals("mp3")) {
                music.getChildren().add(new Label(files.get(i).getName()));
            }

        }




        for (int i = 0; i < files.size(); i++) {
            if (!files.get(i).getType().equals("mp4") && !files.get(i).getType().equals("mkv")
                    && !files.get(i).getType().equals("rar") && !files.get(i).getType().equals("zip") && !files.get(i).getType().equals("mp3")) {
                Else.getChildren().add(new Label(files.get(i).getName()));
            }

        }
        moviePane.setContent(movie);
        musicPane.setContent(music);
        rarPane.setContent(rar);
        elsePane.setContent(Else);

    }

}
