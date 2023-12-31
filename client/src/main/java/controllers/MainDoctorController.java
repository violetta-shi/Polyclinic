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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import messages.AlertMessage;
import model.*;
import org.jetbrains.annotations.NotNull;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainDoctorController implements Initializable {
    @FXML
    private TableColumn<Visit, Integer> visits_visitId;
    @FXML
    private TableColumn<Visit, String> visits_doctorId;
    @FXML
    private TableColumn<Visit, String> visits_patientId;
    @FXML
    private TableColumn<Visit, String> visits_comment;
    @FXML
    private TableColumn<Visit, String> visits_date;
    @FXML
    private TableColumn<Visit, String> visits_disease;
    @FXML
    private TableColumn<Visit, String> visits_status;
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
    private TableView<Visit> appoitments_tableView;

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
    private TableColumn<Doctor, Integer> dashboard_col_doctorID;

    @FXML
    private TableColumn<Doctor, String> dashboard_col_lastname;

    @FXML
    private TableColumn<Doctor, String> dashboard_col_phone;

    @FXML
    private TableColumn<Doctor, String> dashboard_col_qualification;

    @FXML
    private TableColumn<Doctor, String> dashboard_col_room;

    @FXML
    private TableColumn<Doctor, String> dashboard_col_specialization;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<Doctor> dashboard_tableView;

    @FXML
    private Label date_time;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_usermane;

    @FXML
    private TableColumn<Patient, String> patient_col_address;

    @FXML
    private TableColumn<Patient, String> patient_col_date;

    @FXML
    private TableColumn<Patient, String> patient_col_gender;

    @FXML
    private TableColumn<Patient, String> patient_col_lastname;

    @FXML
    private TableColumn<Patient, String> patient_col_name;

    @FXML
    private TableColumn<Patient, String> patient_col_pasportID;

    @FXML
    private TableColumn<Patient, Integer> patient_col_patientID;

    @FXML
    private TableColumn<Patient, String> patient_col_patronymic;

    @FXML
    private TableColumn<Patient, String> patient_col_phone;

    @FXML
    private TableColumn<Patient, String> patient_col_status;

    @FXML
    private AnchorPane patient_form;
    @FXML
    private AnchorPane singlePatinet_form;
    @FXML
    private Button patinet_btn;

    @FXML
    private TableView<Patient> patient_tableView;

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
    @FXML
    private AnchorPane profile_settings_form;
    @FXML
    private Button ps_importBtn;
    @FXML
    private Label ps_DoctorID;
    @FXML
    private Label ps_Phone;
    @FXML
    private Label ps_WorkPhone;
    @FXML
    private Label ps_Room;
    @FXML
    private TextField ps_oldPassword;
    @FXML
    private TextField ps_newPassword;
    @FXML
    private TextField ps_oldPasswordRepeate;
    @FXML
    private TextField ps_newLogin;
    @FXML
    private Button ps_changepasswordBtn;
    @FXML
    private Button ps_changeLoginBtn;

    @FXML
    private ComboBox<Disease> disease_combo;


    /*@FXML
    private ComboBox<String> patinet_patientGender;*/
    private int doctorID;
    private String doctorLogin;
    private AlertMessage alertMessage = new AlertMessage();

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

    public void dashboardAD() throws IOException {
        ObservableList<Doctor> doctors = getDoctors();
        dashboard_AD.setText(String.valueOf(doctors.size()));
    }

    public void dashboardTA() throws IOException {
        ObservableList<Visit> visits = getVisits();
        dashboard_TA.setText(String.valueOf(visits.size()));
    }

    public void dashboardTP() throws IOException {
        ObservableList<Patient> patients = getPatients();
        dashboard_TP.setText(String.valueOf(patients.size()));
    }

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
            profile_settings_form.setVisible(false);
        }
        else if(event.getSource() == appoitments_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            appoitment_form.setVisible(true);
            singlePatinet_form.setVisible(false);
            profile_settings_form.setVisible(false);
        }
        else if(event.getSource() == patinets_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(true);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(false);
            profile_settings_form.setVisible(false);
        }
        else if(event.getSource() == patinet_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(true);
            profile_settings_form.setVisible(false);
        }
        else if(event.getSource() == profile_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            appoitment_form.setVisible(false);
            singlePatinet_form.setVisible(false);
            profile_settings_form.setVisible(true);
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
        User user = new User(patient_userName.getText(), patinet_patientPassword.getText(),
                "User", "---------", person);
        Patient patient = new Patient(patinet_patientBDate.getText(), patinet_patientPassportId.getText(),
                user, address);
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(patient));
        requestModel.setRequestType(RequestType.ADD_PATIENT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.successMessage(responseModel.getResponseMessage());
        patientClearFields();
    }

    public void patientRecordBtn(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/recordPageForm.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.setTitle("Hospital Management System | Record of Patients");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   public Doctor getDoctor() throws IOException {
       Doctor doctor = new Doctor();
       doctor.setDoctorId(this.doctorID);
       Request requestModel = new Request();
       requestModel.setRequestMessage(new Gson().toJson(doctor));
       requestModel.setRequestType(RequestType.GET_DOCTOR);
       ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
       ClientSocket.getInstance().getOut().flush();
       String answer = ClientSocket.getInstance().getInStream().readLine();
       Response responseModel = new Gson().fromJson(answer, Response.class);
       String data = responseModel.getResponseData();
       doctor = new Gson().fromJson(data, Doctor.class);
       return doctor;
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

    public ObservableList<Visit> getVisits() throws IOException {
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson("Найти докторов"));
        requestModel.setRequestType(RequestType.GETALL_VISITS);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Visit[] doctorArray = new Gson().fromJson(responseModel.getResponseData(), Visit[].class);
        ObservableList<Visit> doctors = FXCollections.observableArrayList(doctorArray);
        ObservableList<Visit> visits = FXCollections.observableArrayList();
        for(Visit visit : doctors){
            if(visit.getDoctor().getDoctorId() == this.doctorID){
                visits.add(visit);
            }
        }
        return visits;
    }

    public void changePassword() throws IOException {
        Doctor doctor = getDoctor();
        if (!ps_newPassword.getText().equals(ps_oldPasswordRepeate.getText())){
            alertMessage.errorMessage("Пароли не совпадают!");
            return;
        }
        if(!ps_oldPassword.getText().equals(doctor.getUser().getPassword())){
            alertMessage.errorMessage("Старый пароль введен неверно!");
            return;
        }
        doctor.getUser().setPassword(ps_newPassword.getText());
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(doctor.getUser()));
        requestModel.setRequestType(RequestType.EDIT_USER);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        alertMessage.successMessage(responseModel.getResponseMessage());
    }

    public void showPS() throws IOException {
        Doctor doctor = getDoctor();
        ps_DoctorID.setText(String.valueOf(doctorID));
        ps_Phone.setText(doctor.getUser().getPerson().getPhone());
        ps_WorkPhone.setText(doctor.getUser().getWorkPhone());
        ps_Room.setText(doctor.getRoom());
    }

    public void changeLogin() throws IOException {
        Doctor doctor = getDoctor();
        doctor.getUser().setLogin(ps_newLogin.getText());
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(doctor.getUser()));
        requestModel.setRequestType(RequestType.EDIT_USER);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        alertMessage.successMessage(responseModel.getResponseMessage());
    }

    public void patientClearFields(){
        patinet_patientName.clear();
        patinet_patientSurname.clear();
        patinet_patientPatronymic.clear();
        patinet_patientGender.getSelectionModel().clearSelection();
        patinet_patientPassportId.clear();
        patinet_patientNumber.clear();
        patinet_patientBDate.clear();
        patient_userName.clear();
        patinet_patientPassword.clear();
        patinet_patientSity1.clear();
        patinet_patientDistrict1.clear();
        patinet_patientStreet1.clear();
        patinet_patientHouse1.clear();
        patinet_patientApartment1.clear();

        patinet_PA_ID.setText("....................");
        patinet_PA_Username.setText("....................");
        patinet_PA_password.setText("....................");


        patinet_PI_name.setText("....................");
        patinet_PI_lastname.setText("....................");
        patinet_PI_patronymic.setText("....................");
        patinet_PI_gender.setText("....................");
        patinet_PI_number.setText("....................");
        patinet_PI_address.setText("....................");
    }

    public void patientGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.gender){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        patinet_patientGender.setItems(list);
        }

    public void visitsShowData() throws IOException {
        ObservableList<Visit> visits = null;
        try {
            visits = getVisits();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        visits_visitId.setCellValueFactory(new PropertyValueFactory<>("visitId"));
        visits_doctorId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                String doctorId = String.valueOf(visit.getDoctor().getDoctorId());
                return new SimpleStringProperty(doctorId);
            }
        });
        visits_patientId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                String patientId = String.valueOf(visit.getPatient().getPatientId());
                return new SimpleStringProperty(patientId);
            }
        });
        visits_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        visits_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        visits_disease.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                Visit visit = param.getValue();
                Set<Disease> diseaseSet = visit.getPatient().getDiseases();
                String disease = "";
                for(Disease disease1:diseaseSet){
                    disease += disease1.getName();
                    disease += " ";
                }
                return new SimpleStringProperty(disease);
            }
        });

        appoitments_tableView.setItems(visits);
    }
    public void patientsShowData() throws IOException {
        ObservableList<Patient> patients = getPatients();

        patient_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patient_col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getName();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getLastName();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_patronymic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getPatronymic();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getGender();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getPhone();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getBirthDate();
                return new SimpleStringProperty(name);
            }
        });
        patient_col_pasportID.setCellValueFactory(new PropertyValueFactory<>("passportId"));
        patient_col_address.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                Address address = patient.getAddress();
                return new SimpleStringProperty(address.toString());
            }
        });
        patient_tableView.setItems(patients);

    }

    public ObservableList<Disease> getDisease() throws IOException {
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson("Диагнозы"));
        requestModel.setRequestType(RequestType.GETALL_DISEASES);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Disease[] diseasesArray = new Gson().fromJson(responseModel.getResponseData(), Disease[].class);
        ObservableList<Disease> diseases = FXCollections.observableArrayList(diseasesArray);
        disease_combo.setItems(diseases);
        return diseases;
    }

    public void setDiseaseCombo() throws IOException {
        ObservableList<Disease> diseases = getDisease();
        disease_combo.setItems(diseases);
    }

    public void setDisease() throws IOException {
        if(appoitments_tableView.getSelectionModel().getSelectedItem() == null){
            alertMessage.errorMessage("Выберите пациента");
            return;
        }
        Visit visit = appoitments_tableView.getSelectionModel().getSelectedItem();
        Patient patient = visit.getPatient();
        Set<Disease> diseases = patient.getDiseases();
        diseases.add(disease_combo.getValue());
        patient.setDiseases(diseases);
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(patient));
        requestModel.setRequestType(RequestType.EDIT_PATIENT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
    }

    public void givePrescription() throws IOException {
        Visit visit = appoitments_tableView.getSelectionModel().getSelectedItem();
        Patient patient = visit.getPatient();
        Doctor doctor = visit.getDoctor();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/prescriptionForm.fxml"));
        GivePrescriptionController controller = new GivePrescriptionController(doctor, patient);
        loader.setController(controller);
        Parent root = loader.load();;
        Stage stage = new Stage();
        stage.setTitle("Admin Portal");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void analyses(){
        if(appoitments_tableView.getSelectionModel().getSelectedItem() == null){
            alertMessage.errorMessage("Выберите пациента");
            return;
        }
        Visit visit = appoitments_tableView.getSelectionModel().getSelectedItem();
        Patient patient = visit.getPatient();
        Doctor doctor = visit.getDoctor();
        File file = new File("D://" + patient.getUser().getPerson().getLastName() + "Analyses.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file, true);
            try
            {
                AlertMessage alert = new AlertMessage();
                out.write("Направление на анализы"+ "\n");
                out.write("Выдано пациенту " + patient.getUser().getPerson().getLastName() + " ");
                out.write("Врачом " + doctor.getUser().getPerson().getLastName() + "\n");
                out.write("Уровень гемоглобина " + "\n");
                out.write("Уровень эритроцитов" + "\n");
                out.write("Уровень лейкоцитов" + "\n");
                out.write("Уровень трамбоцитов" + "\n");
                out.write("Уровень гематокрита" + "\n");
                out.write(String.valueOf(new Date()));
                alert.successMessage("Направление успешно сохранен!");

            }finally {
                out.close();
            }
        }catch (IOException e1){
            throw new RuntimeException();
        }
    }

    @FXML
    private Button logout_btn;
    public void logOut() throws IOException {
        AlertMessage alert = new AlertMessage();
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

    public void dashboardGetDoctorDisplayData() throws IOException {
        ObservableList<Doctor> doctors = getDoctors();

        dashboard_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        dashboard_col_lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String lastName = doctor.getUser().getPerson().getLastName();
                return new SimpleStringProperty(lastName);
            }
        });
        dashboard_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        dashboard_col_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Doctor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Doctor, String> param) {
                Doctor doctor = param.getValue();
                String lastName = doctor.getUser().getPerson().getPhone();
                return new SimpleStringProperty(lastName);
            }
        });
        dashboard_col_qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        dashboard_col_room.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        dashboard_tableView.setItems(doctors);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayDoctorIDAndUsername();
        appoitmentStatus();
        patientGenderList();
        try {
            showPS();
            patientsShowData();
            visitsShowData();
            setDiseaseCombo();
            dashboardAD();
            dashboardTA();
            dashboardTP();
            dashboardGetDoctorDisplayData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
