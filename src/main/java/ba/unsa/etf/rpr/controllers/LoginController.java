package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

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
        PatientsDaoImpl pt = new PatientsDaoImpl();
        MainFX m = new MainFX();
        String user = username.getText();

        if(doc.searchByUsername(username.getText()) != null
                && doc.searchByUsername(username.getText()).getPassword().equals(password.getText())){
            if(choice.getValue().equals("Doctor")){
                System.out.println("doctor");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/afterdoctorlogin.fxml"));
            AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(username.getText());
            fxmlLoader.setController(afterDoctorLoginController);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
            stage.show();
            Stage s = (Stage)username.getScene().getWindow();
            s.close();
        }

        else if(pt.searchByUsername(username.getText()) != null
                && pt.searchByUsername(username.getText()).getPassword().equals(password.getText())){
            if(choice.getValue().equals("Patient")){
                System.out.println("patient");
                //m.switchScenes("/fxml/afterpatientlogin.fxml");
            }
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wronglogin.setText("Please enter your data");
        }

        else{
            wronglogin.setText("Wrong username or password");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choice.getItems().addAll("Doctor", "Patient");
    }


}