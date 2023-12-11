package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Doctor;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDoctorFormController implements Initializable {
    @FXML
    private Button editDoctor_cancelBtn;

    @FXML
    private TextField editDoctor_lastname;

    @FXML
    private TextField editDoctor_name;

    @FXML
    private TextField editDoctor_patronimyc;

    @FXML
    private TextField editDoctor_phone;

    @FXML
    private ComboBox<?> editDoctor_qualification;

    @FXML
    private TextField editDoctor_room;

    @FXML
    private ComboBox<?> editDoctor_shedule;

    @FXML
    private ComboBox<?> editDoctor_specialization;

    @FXML
    private Button editDoctor_updateBtn;

    @FXML
    private TextField editDoctor_workPhone;

    private Doctor doctor;

    public EditDoctorFormController(Doctor doctor){
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
