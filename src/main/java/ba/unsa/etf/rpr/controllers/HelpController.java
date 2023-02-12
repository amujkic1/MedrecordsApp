package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private AnchorPane root;
    public HelpController(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       Text text = new Text("\nText Box -> enter the username of patient whose\n" + "record you want to see\n" +
               "\nSearch Button -> takes the username from text box\n" + "and looks for the medical record\n" +
               "\nAdd button -> press to open a new window for creating\n" + "a medical record\n" +
               "\nPatients list -> has all patients that have an appointment\n with the doctor who is logged in\n");

       root.getChildren().add(text);

    }
}
