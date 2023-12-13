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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import messages.AlertMessage;
import model.*;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    private TableColumn<Doctor, String>doctors_col_action;

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
    private TableColumn<Visit, String> visits_visitId;

    @FXML
    private TableColumn<Visit, String> visits_patientId;
    @FXML
    private TableColumn<Visit, String> visits_doctorId;
    @FXML
    private TableColumn<Visit, String> visits_comment;
    @FXML
    private TableColumn<Visit, String> visits_date;
    @FXML
    private TableColumn<Visit, String> visits_time;
    @FXML
    private TableColumn<Visit, String> visits_action;
    @FXML
    private TableView<Visit> visit_tableView;

    @FXML
    private TableColumn<Patient, String> patient_col_address;

    @FXML
    private TableColumn<Patient, String> patient_col_date;

    @FXML
    private TableColumn<Patient, String> patient_col_gender;
    @FXML
    private TableColumn<Patient, String>patient_col_action;

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
    private TableView<Patient> patient_tableView;

    @FXML
    private Button patients_btn;
    @FXML
    private AreaChart<String, Number> dashboad_chart_PD;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle top_profile;
    @FXML
    private Button logout_btn;

    private AlertMessage alert = new AlertMessage();

    @FXML
    private Label top_username;

    @FXML
    private TableColumn<Disease, Integer> disease_diseaseID;

    @FXML
    private TableColumn<Disease, String> disease_name;

    @FXML
    private TableColumn<Disease, String> disease_symptoms;
    @FXML
    private TableColumn<Disease, String> disease_treatment;
    @FXML
    private TableColumn<Disease, String> disease_action;

    @FXML
    private TableView<Disease> disease_tableView;

    @FXML
    private AnchorPane disease_form;

    @FXML
    private AnchorPane visits_form;

    @FXML
    private Button disease_btn;
    private int adminId;
    private String adminLogin;
    public void dashboardAD() throws IOException {
        ObservableList<Doctor> doctors = getDoctors();
        dashboard_AD.setText(String.valueOf(doctors.size()));
    }

    public void dashboardTP() throws IOException {
            ObservableList<Patient> patients = getPatients();
            dashboard_TP.setText(String.valueOf(patients.size()));
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

    public MainAdminController(int adminId, String adminLogin){
        this.adminId = adminId;
        this.adminLogin = adminLogin;
    }
    public void displayAdminIDAndUsername(){
        nav_usermane.setText(adminLogin);
        nav_adminID.setText(Integer.toString(adminId));
        top_username.setText(adminLogin);
    }

    public void dashboardPatientDataChart() throws IOException {
        /*Map<Integer, Integer> yearCountMap = new HashMap<>();

        ObservableList<Patient> patients = getPatients();
        for (Patient patient : patients) {
            int birthYear = Integer.parseInt(patient.getBirthDate().substring(0, 4));
            if (yearCountMap.containsKey(birthYear)) {
                yearCountMap.put(birthYear, yearCountMap.get(birthYear) + 1);
            } else {
                yearCountMap.put(birthYear, 1);
            }
        }
        XYChart.Series chart = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : yearCountMap.entrySet()) {
            String year = entry.getKey().toString();
            int count = entry.getValue();
            chart.getData().add(new XYChart.Data<>(year, count));
        }
        chart.getData().add(new XYChart.Data<>("2004", 100));
        chart.getData().add(new XYChart.Data<>("2003", 50));
        dashboad_chart_PD.getData().add(chart);*/

        //dashboad_chart_PD.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        chart.getData().add(new XYChart.Data<>("2004", 10));
        chart.getData().add(new XYChart.Data<>("2003", 20));
            dashboad_chart_PD.getData().add(chart);
    }

    public void switchForm(ActionEvent event) throws IOException {
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            patient_form.setVisible(false);
            doctors_form.setVisible(false);
            disease_form.setVisible(false);
            visits_form.setVisible(false);
        }else if(event.getSource() == doctors_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            doctors_form.setVisible(true);
            disease_form.setVisible(false);
            visits_form.setVisible(false);
        }else if(event.getSource() == patients_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(true);
            doctors_form.setVisible(false);
            disease_form.setVisible(false);
            visits_form.setVisible(false);
        }
        else if(event.getSource() == disease_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            doctors_form.setVisible(false);
            disease_form.setVisible(true);
            visits_form.setVisible(false);
        }
        else if(event.getSource() == appoitments_btn){
            dashboard_form.setVisible(false);
            patient_form.setVisible(false);
            doctors_form.setVisible(false);
            disease_form.setVisible(false);
            visits_form.setVisible(true);
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
        return diseases;
    }

    public void diseasesShowData() throws IOException {
        ObservableList<Disease> diseases = getDisease();
        disease_diseaseID.setCellValueFactory(new PropertyValueFactory<>("diseaseId"));
        disease_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        disease_symptoms.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        disease_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        disease_tableView.setItems(diseases);
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

    public void visitsShowData() throws IOException {
        ObservableList<Visit> visits = null;
        try {
            visits = getVisits();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        visits_visitId.setCellValueFactory(new PropertyValueFactory<>("visitId"));
            visits_patientId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                    Visit visit = param.getValue();
                    String doctorId = String.valueOf(visit.getDoctor().getDoctorId());
                    return new SimpleStringProperty(doctorId);
                }
            });
            visits_doctorId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Visit, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Visit, String> param) {
                    Visit visit = param.getValue();
                    String patientId = String.valueOf(visit.getPatient().getPatientId());
                    return new SimpleStringProperty(patientId);
                }
            });
            visits_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            visits_time.setCellValueFactory(new PropertyValueFactory<>("time"));
            visits_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));


            visit_tableView.setItems(visits);
    }

    public void doctorActionButton() throws IOException {

        ObservableList<Doctor> doctors = getDoctors();

        Callback<TableColumn<Doctor, String>, TableCell<Doctor, String>> cellFactory = (TableColumn<Doctor, String> param) -> {
            final TableCell<Doctor, String> cell = new TableCell<Doctor, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                Doctor pData = doctors_tableView.getSelectionModel().getSelectedItem();
                                int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editDoctorForm.fxml"));
                                EditDoctorFormController formController = new EditDoctorFormController(pData);
                                loader.setController(formController);
                                Parent root = loader.load();;
                                Stage stage = new Stage();
                                stage.setTitle("Edit Doctor");
                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            Doctor pData = doctors_tableView.getSelectionModel().getSelectedItem();
                            int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }
                            Request requestModel = new Request();
                            requestModel.setRequestMessage(new Gson().toJson(pData));
                            requestModel.setRequestType(RequestType.DELETE_DOCTOR);
                            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
                            ClientSocket.getInstance().getOut().flush();
                            String answer = null;
                            try {
                                answer = ClientSocket.getInstance().getInStream().readLine();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Response responseModel = new Gson().fromJson(answer, Response.class);

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorId() + "?")) {

                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            try {
                doctorsShowData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctors);

    }

    public void patientActionButton() throws IOException {
        ObservableList<Patient> patients = getPatients();
        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactory = (TableColumn<Patient, String> param) -> {
            final TableCell<Patient, String> cell = new TableCell<Patient, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {
                                Patient patient = patient_tableView.getSelectionModel().getSelectedItem();
                                int num = patient_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item");
                                    return;
                                }
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editPatientForm.fxml"));
                                EditPatientFormController formController = new EditPatientFormController(patient);
                                loader.setController(formController);
                                Parent root = loader.load();;
                                Stage stage = new Stage();
                                stage.setTitle("Edit Patient");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                        removeButton.setOnAction((ActionEvent event) -> {
                            try {
                                Patient patient = patient_tableView.getSelectionModel().getSelectedItem();
                                int num = patient_tableView.getSelectionModel().getSelectedIndex();
                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item");
                                    return;
                                }
                                Request requestModel = new Request();
                                requestModel.setRequestMessage(new Gson().toJson(patient));
                                requestModel.setRequestType(RequestType.DELETE_PATIENT);
                                ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
                                ClientSocket.getInstance().getOut().flush();
                                String answer = ClientSocket.getInstance().getInStream().readLine();
                                Response responseModel = new Gson().fromJson(answer, Response.class);
                                alert.successMessage("Данные успешно удалены");
                                patientsShowData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        patient_col_action.setCellFactory(cellFactory);
        patient_tableView.setItems(patients);
    }

    public void diseaseActionButton() throws IOException {
        ObservableList<Disease> diseases = getDisease();
        Callback<TableColumn<Disease, String>, TableCell<Disease, String>> cellFactory = (TableColumn<Disease, String> param) -> {
            final TableCell<Disease, String> cell = new TableCell<Disease, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {
                                Disease disease = disease_tableView.getSelectionModel().getSelectedItem();
                                int num = disease_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item");
                                    return;
                                }
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editDiseaseForm.fxml"));
                                EditDiseaseController formController = new EditDiseaseController(disease);
                                loader.setController(formController);
                                Parent root = loader.load();;
                                Stage stage = new Stage();
                                stage.setTitle("Edit Disease");
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                        removeButton.setOnAction((ActionEvent event) -> {
                            try {
                                Disease disease = disease_tableView.getSelectionModel().getSelectedItem();
                                int num = disease_tableView.getSelectionModel().getSelectedIndex();
                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item");
                                    return;
                                }
                                Request requestModel = new Request();
                                requestModel.setRequestMessage(new Gson().toJson(disease));
                                requestModel.setRequestType(RequestType.DELETE_DISEASE);
                                ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
                                ClientSocket.getInstance().getOut().flush();
                                String answer = ClientSocket.getInstance().getInStream().readLine();
                                Response responseModel = new Gson().fromJson(answer, Response.class);
                                alert.successMessage("Данные успешно удалены");
                                patientsShowData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        disease_action.setCellFactory(cellFactory);
        disease_tableView.setItems(diseases);
    }

    public void addDoctor(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/addDoctorForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hospital Management System | Add Doctor");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return doctors;
    }

    public void logOut() throws IOException {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayAdminIDAndUsername();
        try {
            doctorsShowData();
            patientsShowData();
            doctorActionButton();
            patientActionButton();
            diseaseActionButton();
            dashboardGetDoctorDisplayData();
            dashboardTP();
            dashboardAD();
            diseasesShowData();
            visitsShowData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
