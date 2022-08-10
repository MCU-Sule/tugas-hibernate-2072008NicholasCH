package com.example.pt08_2072008.controller;

import com.example.pt08_2072008.dao.userDao;
import com.example.pt08_2072008.model.UserEntity;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class addController {
    Alert alert;


    public TextField txtUserName;
    public PasswordField txtPassword;

    public void submit(ActionEvent actionEvent) {
        int hasil;
        if((txtUserName.getText().isEmpty()) || (txtPassword.getText().isEmpty()) ){
            hasil = 0;
        } else {
            UserEntity user = new UserEntity();
            user.setIdUser(0);
            user.setUserName(txtUserName.getText());
            user.setUserPassword(txtPassword.getText());
            userDao uDao = new userDao();
            uDao.addData(user);
            hasil = 1;
            reset();
        }
        if (hasil > 0){
            alert = new Alert(Alert.AlertType.INFORMATION, "Data successfully added!", ButtonType.OK);
            alert.showAndWait();
            reset();
            txtPassword.getScene().getWindow().hide();
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error while adding new data!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void reset(){
        txtUserName.clear();
        txtPassword.clear();
    }
}
