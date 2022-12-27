package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label wronglogin;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ChoiceBox<String> choice;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        System.out.println("login");
        validateLogin();
    }

    public void validateLogin() throws IOException {

        DoctorsDaoImpl doc = new DoctorsDaoImpl();
        MainFX m = new MainFX();

        if(doc.searchByUsername(username.getText()) != null
                && doc.searchByUsername(username.getText()).getPassword().equals(password.getText())){
            if(choice.getValue().equals("Doctor")){
                System.out.println("doctor");
            }
            //System.out.println("Success");
            m.switchScenes("/fxml/afterdoctorlogin.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wronglogin.setText("Please enter your data");
        }
        else{
            wronglogin.setText("Wrong username or password");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice.getItems().addAll("Doctor", "Patient");
    }
}