package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import messages.AlertMessage;
import model.Patient;
import javafx.event.ActionEvent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainPatientController implements Initializable {
    @FXML
    private Button appointmentBookBtn;

    @FXML
    private Label appointment_ad_address;

    @FXML
    private Label appointment_ad_description;

    @FXML
    private Label appointment_ad_doctorName;

    @FXML
    private Label appointment_ad_gender;

    @FXML
    private Label appointment_ad_mobileNumber;

    @FXML
    private Label appointment_ad_name;

    @FXML
    private Label appointment_ad_schedule;

    @FXML
    private Label appointment_ad_specialization;

    @FXML
    private Button appointment_d_clearBtn;

    @FXML
    private Button appointment_d_confirmBtn;

    @FXML
    private TextArea appointment_d_description;

    @FXML
    private ComboBox<?> appointment_d_doctor;

    @FXML
    private DatePicker appointment_d_schedule;

    @FXML
    private Button appointments_btn;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Label current_form;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Label date_time;

    @FXML
    private Button doctors_btn;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private GridPane doctors_gridPane;

    @FXML
    private ScrollPane doctors_scrollPane;

    @FXML
    private TableColumn<?, ?> home_appointment_col_appointmenID;

    @FXML
    private TableColumn<?, ?> home_appointment_col_description;

    @FXML
    private TableColumn<?, ?> home_appointment_col_diagnosis;

    @FXML
    private TableColumn<?, ?> home_appointment_col_doctor;

    @FXML
    private TableColumn<?, ?> home_appointment_col_schedule;

    @FXML
    private TableColumn<?, ?> home_appointment_col_treatment;

    @FXML
    private TableView<?> home_appointment_tableView;

    @FXML
    private Circle home_doctor_circle;

    @FXML
    private Label home_doctor_email;

    @FXML
    private Label home_doctor_mobileNumber;

    @FXML
    private Label home_doctor_name;

    @FXML
    private Label home_doctor_specialization;

    @FXML
    private AnchorPane home_form;

    @FXML
    private TableColumn<?, ?> home_patient_col_dateIn;

    @FXML
    private TableColumn<?, ?> home_patient_col_description;

    @FXML
    private TableColumn<?, ?> home_patient_col_diagnosis;

    @FXML
    private TableColumn<?, ?> home_patient_col_treatment;

    @FXML
    private TableView<?> home_patient_tableView;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private TextField new_password;

    @FXML
    private TextField old_password;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle profile_circle;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_mobileNumber;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_patientID;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private TextField repeate_password;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    private Patient patient;

    private AlertMessage alert;
    public MainPatientController(Patient patient){
        this.patient = patient;
    }


    @FXML
    void logoutBtn() {
        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            home_form.setVisible(true);
            doctors_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == doctors_btn) {
            home_form.setVisible(false);
            doctors_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            home_form.setVisible(false);
            doctors_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            home_form.setVisible(false);
            doctors_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }

    }
    public void runTime() {

        new Thread() {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }

    public void displayPatientID(){
        nav_adminID.setText(String.valueOf(patient.getPatientId()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
