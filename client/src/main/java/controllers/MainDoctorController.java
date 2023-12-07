package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import messages.AlertMessage;
import model.Address;
import model.Data;
import model.Patient;
import model.Person;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainDoctorController implements Initializable {
    @FXML
    private TableColumn<?, ?> appoitment_col_AppoitmentID;

    @FXML
    private TableColumn<?, ?> appoitment_col_date;

    @FXML
    private TableColumn<?, ?> appoitment_col_description;

    @FXML
    private TableColumn<?, ?> appoitment_col_disease;

    @FXML
    private TableColumn<?, ?> appoitment_col_doctorID;

    @FXML
    private TableColumn<?, ?> appoitment_col_patientID;

    @FXML
    private TableColumn<?, ?> appoitment_col_status;

    @FXML
    private TableColumn<?, ?> appoitment_col_treatment;

    @FXML
    private TextField appoitment_date;

    @FXML
    private Button appoitment_deleteBtn;

    @FXML
    private TextArea appoitment_description;

    @FXML
    private TextField appoitment_disease;

    @FXML
    private TextField appoitment_doctorID;

    @FXML
    private AnchorPane appoitment_form;

    @FXML
    private Button appoitment_insertBtn;

    @FXML
    private TextField appoitment_patientID;

    @FXML
    private ComboBox<?> appoitment_status;

    @FXML
    private TextField appoitment_tratment;

    @FXML
    private Button appoitment_updateBtn;

    @FXML
    private Button appoitments_btn;

    @FXML
    private TableView<?> appoitments_tableView;
    @FXML
    private TextField appoitment_appoitmentID;

    @FXML
    private Button appoitment_clearBtn;

    @FXML
    private TableColumn<?, ?> appoitment_col_PassportID;

    @FXML
    private TableColumn<?, ?> appoitment_col_address;

    @FXML
    private TableColumn<?, ?> appoitment_col_contact;


    @FXML
    private TableColumn<?, ?> appoitment_col_dateModify;

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
    private TextField appoitment_lastname;

    @FXML
    private TextField appoitment_sity;

    @FXML
    private TextField appoitment_street;
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
    private Label patinet_PI_name;
    @FXML
    private Label patinet_PI_patronymic;

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
    @FXML
    private TextField patient_userName;
    @FXML
    private TextField patinet_patientBDate;
    @FXML
    private TextField patinet_patientPassportId;

    /*@FXML
    private ComboBox<String> patinet_patientGender;*/
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

    public void appoitmentStatus(){
        List<String> listS = new ArrayList<>();

        for(String data: Data.status){
            listS.add(data);
        }

        ObservableList observableList = FXCollections.observableArrayList(listS);
        appoitment_status.setItems(observableList);

    }

    public void patientConfirnBtn(){
        if(patinet_patientName.getText().isEmpty()
        || patinet_patientSurname.getText().isEmpty()
        || patinet_patientPatronymic.getText().isEmpty()
        || patinet_patientGender.getSelectionModel().isEmpty()
        || patinet_patientNumber.getText().isEmpty()
        || patient_userName.getText().isEmpty()
        || patinet_patientNumber.getText().isEmpty()
        || patinet_patientSity1.getText().isEmpty()
        ||patinet_patientPassword.getText().isEmpty()
        ||patinet_patientDistrict1.getText().isEmpty()
        ||patinet_patientStreet1.getText().isEmpty()
        ||patinet_patientHouse1.getText().isEmpty()
        ||patinet_patientApartment1.getText().isEmpty()){
            AlertMessage alertMessage = new AlertMessage();
            alertMessage.errorMessage("Заполните все поля");
        }else{
            patinet_PA_ID.setText("2");
            patinet_PA_Username.setText(patient_userName.getText());
            patinet_PA_password.setText(patinet_patientPassword.getText());

            patinet_PI_name.setText(patinet_patientName.getText());
            patinet_PI_lastname.setText(patinet_patientSurname.getText());
            patinet_PI_patronymic.setText(patinet_patientPatronymic.getText());
            patinet_PI_gender.setText((String) patinet_patientGender.getSelectionModel().getSelectedItem());
            patinet_PI_number.setText(patinet_patientNumber.getText());
            String address = "г." + patinet_patientSity1.getText() + ", район " + patinet_patientDistrict1.getText()
            + "\n, ул. " + patinet_patientStreet1.getText() + ", д." + patinet_patientHouse1.getText()
                    + ", кв." + patinet_patientApartment1.getText();
            patinet_PI_address.setText(address);
        }
    }

    public void patientAddBrn() throws IOException {
        Person person = new Person(patinet_PI_name.getText(), patinet_PI_lastname.getText(),
                patinet_PI_patronymic.getText(), patinet_PI_number.getText(), patinet_PI_gender.getText().substring(0, 1));
        Address address = new Address(patinet_patientSity1.getText(), patinet_patientDistrict1.getText(),
                patinet_patientStreet1.getText(), patinet_patientHouse1.getText(),
                Integer.parseInt(patinet_patientApartment1.getText()));
        Patient patient = new Patient(patinet_patientBDate.getText(), patinet_patientPassportId.getText(),
                person, address);
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(patient));
        requestModel.setRequestType(RequestType.ADD_PATIENT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
    }

    public void patientGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.gender){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        patinet_patientGender.setItems(list);
        }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayDoctorIDAndUsername();
        appoitmentStatus();
        patientGenderList();
    }
}
