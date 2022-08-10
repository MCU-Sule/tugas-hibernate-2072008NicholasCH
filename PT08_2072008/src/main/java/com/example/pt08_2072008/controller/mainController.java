package com.example.pt08_2072008.controller;

import com.example.pt08_2072008.HelloApplication;
import com.example.pt08_2072008.dao.movieDao;
import com.example.pt08_2072008.dao.userDao;
import com.example.pt08_2072008.dao.watchlistDao;
import com.example.pt08_2072008.model.MovieEntity;
import com.example.pt08_2072008.model.UserEntity;
import com.example.pt08_2072008.model.WatchlistEntity;
import com.example.pt08_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class mainController {

    public ComboBox cmbGenre;
    public ListView lvUser;
    public TableView table1;
    public TableColumn colTitle;
    public TableColumn colGenre;
    public TableColumn colDur;
    public TableColumn colTitleML;
    public TableColumn colLastML;
    public TableColumn colFavML;
    public TableView table2;

    FXMLLoader fxmlLoader;
    Scene scene;
    Stage stage;

    ObservableList<String> filterData;
    ObservableList<MovieEntity> movieList;
    ObservableList<UserEntity> userList;
    ObservableList<WatchlistEntity> wList;

    UserEntity selectedItem;

    movieDao movDao = new movieDao();
    userDao userDao = new userDao();
    watchlistDao wlDao = new watchlistDao();

    public void initialize() throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        scene = new Scene(fxmlLoader.load(), 350, 170);
        stage = new Stage();
        stage.setTitle("Add New User");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        filterData = FXCollections.observableArrayList(
                "All",
                "Action",
                "Musical",
                "Comedy",
                "Animated",
                "Fantasy",
                "Drama",
                "Mystery",
                "Thriller",
                "Horror"
        );
        cmbGenre.setItems(filterData);
        cmbGenre.getSelectionModel().select(0);
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        tampilan();
    }
    public void tampilan(){
        movieList = FXCollections.observableArrayList(movDao.getData());
        userList = FXCollections.observableArrayList(userDao.getData());
        table1.setItems(movieList);
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDur.setCellValueFactory(new PropertyValueFactory<>("durasi"));
        lvUser.setItems(userList);
    }

    public void changeCombo() {
        movieList.clear();
        if (cmbGenre.getValue().equals("All")) {
            movieList = FXCollections.observableArrayList(movDao.getData());
            table1.setItems(movieList);
        }else {
            movieList = FXCollections.observableArrayList(movDao.filterData((String) cmbGenre.getValue()));
            table1.setItems(movieList);
        }
    }

    public void AddUserAction() {
        stage.showAndWait();
        tampilan();
    }

    public void DelUserAction() {
        int hasil = 0;
        selectedItem = (UserEntity) lvUser.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this selected data?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            hasil = userDao.delData(selectedItem);
        } else {
            hasil = 0;
        }
        if (hasil > 0){
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Success to Delete Data!", ButtonType.OK);
            alert2.showAndWait();
            tampilan();
        } else {
            Alert alert3 = new Alert(Alert.AlertType.ERROR, "Error While Delete Data!", ButtonType.OK);
            alert3.showAndWait();
        }
        
    }

    public void printReport() {
        JasperPrint jp;
        Map param = new HashMap();
        Connection conn = utilConnection.getConnection();
        try {
            jp = JasperFillManager.fillReport("report/Report_UTS_2072008.jasper", param, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Laporan Movies");
            jv.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void filteredData() {
        selectedItem = (UserEntity) lvUser.getSelectionModel().getSelectedItem();
        wList = FXCollections.observableArrayList(wlDao.filterData(selectedItem));
        table2.setItems(wList);
        colTitleML.setCellValueFactory(new PropertyValueFactory<>("movieByMovieIdMovie"));
        colLastML.setCellValueFactory(new PropertyValueFactory<>("DurasiLW"));
        colFavML.setCellValueFactory(new PropertyValueFactory<>("Favorit"));
    }
}
