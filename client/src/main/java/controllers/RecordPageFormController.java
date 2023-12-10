package controllers;

import com.google.gson.Gson;
import enums.RequestType;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import messages.AlertMessage;
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
    private TableColumn<Patient, String> recordpage_col_action;

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
                String name = patient.getUser().getPerson().getName();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getLastName();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_patronymic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getPatronymic();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getGender();
                return new SimpleStringProperty(name);
            }
        });
        recordpage_col_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
                Patient patient = param.getValue();
                String name = patient.getUser().getPerson().getPhone();
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

    public void actionButtons() throws IOException {
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

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {
                                Patient patient = recordpage_tableView.getSelectionModel().getSelectedItem();
                                int num = recordpage_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alertMessage.errorMessage("Please select item");
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
                                Patient patient = recordpage_tableView.getSelectionModel().getSelectedItem();
                                int num = recordpage_tableView.getSelectionModel().getSelectedIndex();
                                    if ((num - 1) < -1) {
                                        alertMessage.errorMessage("Please select item");
                                        return;
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

            return cell;
        };
        recordpage_col_action.setCellFactory(cellFactory);
        recordpage_tableView.setItems(patients);
    }

    AlertMessage alertMessage = new AlertMessage();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            patientsShowData();
            actionButtons();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
