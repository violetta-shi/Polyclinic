package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import messages.AlertMessage;
import model.Address;
import model.Data;
import model.Patient;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditPatientFormController implements Initializable {
    @FXML
    private TextField edit_contactNumber;

    @FXML
    private TextField edit_district;

    @FXML
    private TextField edit_flatNum;

    @FXML
    private ComboBox<?> edit_gender;

    @FXML
    private TextField edit_houseNum;

    @FXML
    private TextField edit_lastName;

    @FXML
    private TextField edit_name;

    @FXML
    private TextField edit_passportID;

    @FXML
    private TextField edit_sity;

    @FXML
    private TextField edit_street;

    @FXML
    private Button edit_updateBtn;

    @FXML
    private AnchorPane main_form;
    private Patient patient;

    public EditPatientFormController(Patient patient){
        this.patient = patient;
    }

    public void patientGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.gender){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        edit_gender.setItems(list);
    }

    public void setField() {
        edit_name.setText(patient.getUser().getPerson().getName());
        edit_lastName.setText(patient.getUser().getPerson().getLastName());
        edit_contactNumber.setText(patient.getUser().getPerson().getPhone());
        edit_passportID.setText(patient.getPassportId());
        edit_sity.setText(patient.getAddress().getCity());
        edit_district.setText(patient.getAddress().getDistrict());
        edit_street.setText(patient.getAddress().getStreet());
        edit_houseNum.setText(patient.getAddress().getHouseNumber());
        edit_flatNum.setText(String.valueOf(patient.getAddress().getApartmentNumber()));
    }

    public void updateBtn() throws IOException {
        patient.getUser().getPerson().setName(edit_name.getText());
        patient.getUser().getPerson().setLastName(edit_lastName.getText());
        patient.getUser().getPerson().setGender(((String) edit_gender.getSelectionModel().getSelectedItem()).substring(0, 1));
        patient.getUser().getPerson().setPhone(edit_contactNumber.getText());
        patient.setPassportId(edit_passportID.getText());
        patient.getAddress().setCity(edit_sity.getText());
        patient.getAddress().setDistrict(edit_district.getText());
        patient.getAddress().setStreet(edit_street.getText());
        patient.getAddress().setHouseNumber(edit_houseNum.getText());
        patient.getAddress().setApartmentNumber(Integer.parseInt(edit_flatNum.getText()));
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(patient));
        requestModel.setRequestType(RequestType.EDIT_PATIENT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.successMessage("Данные были успешно обновлены");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        patientGenderList();
    }
}
