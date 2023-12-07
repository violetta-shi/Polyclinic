package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import model.Doctor;
import model.Patient;
import model.User;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainAdminController implements Initializable {
    @FXML
    private Button appoitments_btn;

    @FXML
    private Label current_form;

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
    private Button doctors_btn;

    @FXML
    private TableColumn<Doctor, Integer> doctors_col_doctorID;

    @FXML
    private TableColumn<Doctor, String> doctors_col_gender;

    @FXML
    private TableColumn<Doctor, String> doctors_col_lastname;

    @FXML
    private TableColumn<Doctor, String> doctors_col_name;

    @FXML
    private TableColumn<Doctor, String> doctors_col_patronymic;

    @FXML
    private TableColumn<Doctor, String> doctors_col_phone;

    @FXML
    private TableColumn<Doctor, String> doctors_col_qualification;

    @FXML
    private TableColumn<Doctor, String> doctors_col_room;

    @FXML
    private TableColumn<Doctor, String> doctors_col_specialization;

    @FXML
    private TableColumn<Doctor, String> doctors_col_workPone;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<Doctor> doctors_tableView;

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
    private TableView<?> patient_tableView;

    @FXML
    private Button patients_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;
    private int adminId;
    private String adminLogin;
    public MainAdminController(int adminId, String adminLogin){
        this.adminId = adminId;
        this.adminLogin = adminLogin;
    }
    public void displayAdminIDAndUsername(){
        nav_usermane.setText(adminLogin);
        nav_adminID.setText(Integer.toString(adminId));
        top_username.setText(adminLogin);
    }

    public void switchForm(ActionEvent event) throws IOException {
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            patient_form.setVisible(false);
            doctors_form.setVisible(false);
        }else if(event.getSource() == doctors_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            doctors_form.setVisible(true);
        }else if(event.getSource() == patients_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(true);
            doctors_form.setVisible(false);
        }
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


    public ObservableList<Doctor> getDoctors() throws IOException {
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson("Найти докторов"));
        requestModel.setRequestType(RequestType.GETALL_DOCTORS);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Doctor[] doctorArray = new Gson().fromJson(responseModel.getResponseData(), Doctor[].class);
        ObservableList<Doctor> doctors = FXCollections.observableArrayList(doctorArray);
        return doctors;
    }

    public ObservableList<Patient> getPatients() throws IOException{
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson("Найти пациентов"));
        requestModel.setRequestType(RequestType.GETALL_PATIENTS);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Patient[] patientArray = new Gson().fromJson(responseModel.getResponseData(), Patient[].class);
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientArray);
        return patients;
    }

    public void patientsShowData() throws IOException {
        ObservableList<Patient> patients = getPatients();
    }

    public void doctorsShowData() throws IOException {
        ObservableList<Doctor> doctors = getDoctors();
        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        doctors_col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String name = doctor.getUser().getPerson().getName();
                return new SimpleStringProperty(name);
            }
        });
        doctors_col_lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String lastName = doctor.getUser().getPerson().getLastName();
                return new SimpleStringProperty(lastName);
            }
        });
                //doctors_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        doctors_col_patronymic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String patronymic = doctor.getUser().getPerson().getPatronymic();
                return new SimpleStringProperty(patronymic);
            }
        });
        doctors_col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String gender = doctor.getUser().getPerson().getGender();
                return new SimpleStringProperty(gender);
            }
        });
        doctors_col_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String phone = doctor.getUser().getPerson().getPhone();
                return new SimpleStringProperty(phone);
            }
        });
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctors_col_qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        doctors_col_room.setCellValueFactory(new PropertyValueFactory<>("room"));
        doctors_col_workPone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String workPhone = doctor.getUser().getWorkPhone();
                return new SimpleStringProperty(workPhone);
            }
        });

        doctors_tableView.setItems(doctors);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayAdminIDAndUsername();
        try {
            doctorsShowData();
            patientsShowData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
