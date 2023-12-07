package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Address;
import model.Patient;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordPageFormController implements Initializable {
    @FXML
    private TableColumn<Patient, String> recordpage_col_address;

    @FXML
    private TableColumn<Patient, String> recordpage_col_date;

    @FXML
    private TableColumn<Patient, String> recordpage_col_gender;

    @FXML
    private TableColumn<Patient, String> recordpage_col_lastname;

    @FXML
    private TableColumn<Patient, String> recordpage_col_name;

    @FXML
    private TableColumn<Patient, String> recordpage_col_passportID;

    @FXML
    private TableColumn<Patient, Integer> recordpage_col_patientID;

    @FXML
    private TableColumn<Patient, String> recordpage_col_patronymic;

    @FXML
    private TableColumn<Patient, String> recordpage_col_phone;

    @FXML
    private AnchorPane recordpage_mainForm;

    @FXML
    private TextField recordpage_search;

    @FXML
    private TableView<Patient> recordpage_tableView;

    public ObservableList<Patient> getPatients() throws IOException {
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

        recordpage_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        recordpage_col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getPerson().getName();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getPerson().getLastName();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_patronymic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getPerson().getPatronymic();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getPerson().getGender();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getPerson().getPhone();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getBirthDate();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_passportID.setCellValueFactory(new PropertyValueFactory<>("passportId"));
        recordpage_col_address.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                Address address = patient.getAddress();
                return new SimpleStringProperty(address.toString());
            }
        });


        recordpage_tableView.setItems(patients);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            patientsShowData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
