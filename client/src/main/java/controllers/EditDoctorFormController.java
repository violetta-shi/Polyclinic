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
import messages.AlertMessage;
import model.Data;
import model.Doctor;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditDoctorFormController implements Initializable {
    @FXML
    private Button editDoctor_cancelBtn;

    @FXML
    private TextField editDoctor_lastname;

    @FXML
    private TextField editDoctor_name;

    @FXML
    private TextField editDoctor_patronimyc;

    @FXML
    private TextField editDoctor_phone;

    @FXML
    private ComboBox<?> editDoctor_qualification;

    @FXML
    private TextField editDoctor_room;

    @FXML
    private ComboBox<?> editDoctor_shedule;

    @FXML
    private ComboBox<?> editDoctor_specialization;

    @FXML
    private Button editDoctor_updateBtn;

    @FXML
    private TextField editDoctor_workPhone;

    private Doctor doctor;

    public EditDoctorFormController(Doctor doctor){
        this.doctor = doctor;
    }

    public void setFields(){
        editDoctor_name.setText(doctor.getUser().getPerson().getName());
        editDoctor_lastname.setText(doctor.getUser().getPerson().getLastName());
        editDoctor_patronimyc.setText(doctor.getUser().getPerson().getPatronymic());
        editDoctor_phone.setText(doctor.getUser().getPerson().getPhone());
        editDoctor_room.setText(doctor.getRoom());
        editDoctor_workPhone.setText(doctor.getUser().getWorkPhone());
        editDoctor_qualification.setPromptText(doctor.getQualification());
        editDoctor_specialization.setPromptText(doctor.getSpecialization());
        editDoctor_shedule.setPromptText(doctor.getSchedule());
    }

    public void doctorSpecialization(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.spezialisation){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        editDoctor_specialization.setItems(list);
    }

    public void doctorQualification(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.qualification){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        editDoctor_qualification.setItems(list);
    }

    public void doctorShedule(){
        List<String> listG = new ArrayList<>();

        for(String data : Data.shedule){
            listG.add(data);
        }
        ObservableList list = FXCollections.observableList(listG);
        editDoctor_shedule.setItems(list);
    }

    public void updateBtn() throws IOException {
        doctor.getUser().getPerson().setName(editDoctor_name.getText());
        doctor.getUser().getPerson().setLastName(editDoctor_lastname.getText());
        doctor.getUser().getPerson().setPatronymic(editDoctor_patronimyc.getText());
        doctor.getUser().getPerson().setPhone(editDoctor_phone.getText());
        doctor.getUser().setWorkPhone(editDoctor_workPhone.getText());
        doctor.setQualification((String) editDoctor_qualification.getSelectionModel().getSelectedItem());
        doctor.setSpecialization((String) editDoctor_specialization.getSelectionModel().getSelectedItem());
        doctor.setSchedule((String) editDoctor_shedule.getSelectionModel().getSelectedItem());

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(doctor));
        requestModel.setRequestType(RequestType.EDIT_DOCTOR);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.successMessage("Данные были успешно обновлены");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields();
        doctorShedule();
        doctorQualification();
        doctorSpecialization();
    }
}
