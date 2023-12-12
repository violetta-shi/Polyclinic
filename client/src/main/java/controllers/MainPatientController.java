package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import messages.AlertMessage;
import model.Disease;
import model.Doctor;
import model.Patient;
import javafx.event.ActionEvent;
import model.Visit;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
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
    private Label appointment_ad_date;

    @FXML
    private Label appointment_ad_mobileNumber;

    @FXML
    private Label appointment_ad_name;

    @FXML
    private Label appointment_ad_schedule;

    @FXML
    private Label appointment_ad_specialization;
    @FXML
    private Label appointment_ad_room;

    @FXML
    private Button appointment_d_clearBtn;

    @FXML
    private Button appointment_d_confirmBtn;

    @FXML
    private TextArea appointment_d_description;

    @FXML
    private ComboBox<Doctor> appointment_d_doctor;

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
    private TableColumn<Visit, Integer> home_appointment_col_appointmenID;

    @FXML
    private TableColumn<Visit, String> home_appointment_col_description;

    @FXML
    private TableColumn<Visit, String> home_appointment_col_diagnosis;

    @FXML
    private TableColumn<Visit, String> home_appointment_col_doctor;

    @FXML
    private TableColumn<Visit, String> home_appointment_col_schedule;

    @FXML
    private TableColumn<Visit, String> home_appointment_col_treatment;

    @FXML
    private TableView<Visit> home_appointment_tableView;

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
    private TableColumn<Disease, String> home_patient_col_dateIn;

    @FXML
    private TableColumn<Disease, String> home_patient_col_description;

    @FXML
    private TableColumn<Disease, String> home_patient_col_diagnosis;

    @FXML
    private TableColumn<Disease, String> home_patient_col_treatment;

    @FXML
    private TableView<Disease> home_patient_tableView;

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

    private AlertMessage alert = new AlertMessage();
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
        top_username.setText(patient.getUser().getLogin());
    }

    public void appointmentClearBtn() {
        appointment_d_doctor.getSelectionModel().clearSelection();
        appointment_d_description.clear();
        appointment_d_schedule.setValue(null);

        appointment_ad_description.setText("");
        appointment_ad_doctorName.setText("");
        appointment_ad_specialization.setText("");
        appointment_ad_schedule.setText("");
    }

    public void showDiseases(){
        ObservableList<Disease> diseases =  FXCollections.observableArrayList(patient.getDiseases());
        home_patient_col_description.setCellValueFactory(new PropertyValueFactory<>("name"));
        home_patient_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        home_patient_col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        //home_patient_col_dateIn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        home_patient_tableView.setItems(diseases);
    }

    public ObservableList<Visit> getVisits() throws IOException {
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson("Найти визиты"));
        requestModel.setRequestType(RequestType.GETALL_VISITS);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Visit[] doctorArray = new Gson().fromJson(responseModel.getResponseData(), Visit[].class);
        ObservableList<Visit> doctors = FXCollections.observableArrayList(doctorArray);
        return doctors;
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

    public void showVisits() throws IOException {
        ObservableList<Visit> visits = getVisits();

        home_appointment_col_appointmenID.setCellValueFactory(new PropertyValueFactory<>("visitId"));
        home_appointment_col_description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                String specialization = visit.getDoctor().getSpecialization();
                return new SimpleStringProperty(specialization);
            }
        });
        home_appointment_col_diagnosis.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                String specialization = visit.getDoctor().getUser().getPerson().getLastName();
                return new SimpleStringProperty(specialization);
            }
        });
        home_appointment_col_treatment.setCellValueFactory(new PropertyValueFactory<>("date"));
        home_appointment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("time"));
        home_appointment_col_schedule.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                String specialization = visit.getDoctor().getRoom();
                return new SimpleStringProperty(specialization);
            }
        });

        home_appointment_tableView.setItems(visits);
    }

    public void confirmBtn() throws IOException {
        Visit visit = new Visit();
        visit.setComment(appointment_d_description.getText());
        Doctor doctor = new Doctor();
        visit.setDoctor(appointment_d_doctor.getSelectionModel().getSelectedItem());
        visit.setPatient(this.patient);
        visit.setDate(String.valueOf(appointment_d_schedule.getValue()));
        visit.setTime(appointment_d_doctor.getSelectionModel().getSelectedItem().getSchedule());

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(visit));
        requestModel.setRequestType(RequestType.ADD_VISIT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);


        appointment_ad_name.setText(patient.getUser().getPerson().getLastName() +
                " " + patient.getUser().getPerson().getName().substring(0, 1) +
                " " + patient.getUser().getPerson().getPatronymic().substring(0, 1));

        appointment_ad_gender.setText(patient.getUser().getPerson().getGender());
        appointment_ad_address.setText(String.valueOf(patient.getAddress()));

        appointment_ad_description.setText(visit.getComment());
        appointment_ad_doctorName.setText(visit.getDoctor().getUser().getPerson().getLastName() +
                " " + visit.getDoctor().getUser().getPerson().getName().substring(0, 1) +
                " " + visit.getDoctor().getUser().getPerson().getPatronymic().substring(0, 1));

        appointment_ad_specialization.setText(visit.getDoctor().getSpecialization());
        appointment_ad_schedule.setText(visit.getDoctor().getSchedule());
        appointment_ad_date.setText(visit.getDate());
        appointment_ad_room.setText(visit.getDoctor().getRoom());
    }

    public void printTicket(){
        File file = new File("D://" + patient.getUser().getPerson().getLastName() + "Ticket.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file, true);
            try
            {
                String patientFIO = appointment_ad_name.getText();
                out.write("Пациент - " + patientFIO + "\n");
                out.write("Пол - " + appointment_ad_gender.getText() + "\n");
                out.write("Описание - " + appointment_ad_description.getText() + "\n");
                out.write(appointment_ad_specialization.getText() + "\n");
                out.write("Доктор - " + appointment_ad_doctorName.getText() + "\n");
                out.write("Дата - " + appointment_ad_date.getText() + "\n");
                out.write("Смена" + appointment_ad_schedule.getText()+ "\n");
                out.write("Кабинет" + appointment_ad_room.getText()+ "\n");
                alert.successMessage("Талон успешно сохранен!");

            }finally {
                out.close();
            }
        }catch (IOException e1){
            throw new RuntimeException();
        }
    }



    public void appointmentDoctor() throws IOException {
            ObservableList<Doctor> listData = getDoctors();
            appointment_d_doctor.setItems(listData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayPatientID();
        showDiseases();
        try {
            appointmentDoctor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            showVisits();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
