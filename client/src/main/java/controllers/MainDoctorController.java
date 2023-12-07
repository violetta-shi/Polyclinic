package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainDoctorController implements Initializable {
    @FXML
    private TextField appoitment_appoitmentID;

    @FXML
    private Button appoitment_clearBtn;

    @FXML
    private TableColumn<?, ?> appoitment_col_AppoitmentID;

    @FXML
    private TableColumn<?, ?> appoitment_col_PassportID;

    @FXML
    private TableColumn<?, ?> appoitment_col_address;

    @FXML
    private TableColumn<?, ?> appoitment_col_contact;

    @FXML
    private TableColumn<?, ?> appoitment_col_date;

    @FXML
    private TableColumn<?, ?> appoitment_col_dateModify;

    @FXML
    private TableColumn<?, ?> appoitment_col_description;

    @FXML
    private TableColumn<?, ?> appoitment_col_gender;

    @FXML
    private TableColumn<?, ?> appoitment_col_lastname;

    @FXML
    private TableColumn<?, ?> appoitment_col_status;

    @FXML
    private Button appoitment_deleteBtn;

    @FXML
    private TextField appoitment_description;

    @FXML
    private TextField appoitment_diagnosist;

    @FXML
    private TextField appoitment_district;

    @FXML
    private TextField appoitment_flatNum;

    @FXML
    private ComboBox<?> appoitment_gender;

    @FXML
    private TextField appoitment_houseNum;

    @FXML
    private Button appoitment_insertBtn;

    @FXML
    private TextField appoitment_lastname;

    @FXML
    private TextField appoitment_sity;

    @FXML
    private TextField appoitment_street;

    @FXML
    private TextField appoitment_tratment;

    @FXML
    private Button appoitment_updateBtn;

    @FXML
    private Button appoitments_btn;

    @FXML
    private TableView<?> appoitments_tableView;

    @FXML
    private Label current_form;

    @FXML
    private Label current_form1;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;
    @FXML
    private AnchorPane appoitment_form;

    @FXML
    private BarChart<?, ?> dashboard_chat_DD;

    @FXML
    private AreaChart<?, ?> dashboard_chat_PD;

    @FXML
    private TableColumn<?, ?> dashboard_col_doctorID;

    @FXML
    private TableColumn<?, ?> dashboard_col_lastname;

    @FXML
    private TableColumn<?, ?> dashboard_col_phone;

    @FXML
    private TableColumn<?, ?> dashboard_col_qualification;

    @FXML
    private TableColumn<?, ?> dashboard_col_room;

    @FXML
    private TableColumn<?, ?> dashboard_col_specialization;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<?> dashboard_tableView;

    @FXML
    private Label date_time;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_usermane;

    @FXML
    private TableColumn<?, ?> patient_col_address;

    @FXML
    private TableColumn<?, ?> patient_col_date;

    @FXML
    private TableColumn<?, ?> patient_col_gender;

    @FXML
    private TableColumn<?, ?> patient_col_lastname;

    @FXML
    private TableColumn<?, ?> patient_col_name;

    @FXML
    private TableColumn<?, ?> patient_col_pasportID;

    @FXML
    private TableColumn<?, ?> patient_col_patientID;

    @FXML
    private TableColumn<?, ?> patient_col_patronymic;

    @FXML
    private TableColumn<?, ?> patient_col_phone;

    @FXML
    private TableColumn<?, ?> patient_col_status;

    @FXML
    private AnchorPane patient_form;
    @FXML
    private AnchorPane singlePatinet_form;
    @FXML
    private Button patinet_btn;

    @FXML
    private TableView<?> patient_tableView;

    @FXML
    private Label patinet_PA_ID;

    @FXML
    private Label patinet_PA_Username;

    @FXML
    private Label patinet_PA_password;

    @FXML
    private Label patinet_PI_address;

    @FXML
    private Label patinet_PI_gender;

    @FXML
    private Label patinet_PI_lastname;

    @FXML
    private Label patinet_PI_number;

    @FXML
    private Button patinet_addBtn;

    @FXML
    private Button patinet_confirmBtn;

    @FXML
    private TextField patinet_patientApartment1;

    @FXML
    private TextField patinet_patientDistrict1;

    @FXML
    private ComboBox<?> patinet_patientGender;

    @FXML
    private TextField patinet_patientHouse1;

    @FXML
    private TextField patinet_patientName;

    @FXML
    private TextField patinet_patientNumber;

    @FXML
    private TextField patinet_patientPassword;

    @FXML
    private TextField patinet_patientPatronymic;

    @FXML
    private TextField patinet_patientSity1;

    @FXML
    private TextField patinet_patientStreet1;

    @FXML
    private TextField patinet_patientSurname;

    @FXML
    private Button patinet_recordBtn;

    @FXML
    private Button patinets_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;
    private int doctorID;
    private String doctorLogin;

    public MainDoctorController(int doctorID, String doctorLogin){
        this.doctorID = doctorID;
        this.doctorLogin = doctorLogin;
    }

    @FXML
    void switchForm(ActionEvent event) {
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            patient_form.setVisible(false);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(false);
        }
        else if(event.getSource() == appoitments_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            appoitment_form.setVisible(true);
            singlePatinet_form.setVisible(false);
        }
        else if(event.getSource() == patinets_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(true);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(false);
        }else if(event.getSource() == patinet_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(true);
        }
    }
    public void displayDoctorIDAndUsername(){
        nav_usermane.setText(doctorLogin);
        nav_adminID.setText(Integer.toString(doctorID));
        top_username.setText(doctorLogin);
    }

    public void runTime(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Platform.runLater(() ->{
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayDoctorIDAndUsername();
    }
}
