package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import messages.AlertMessage;
import model.Disease;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditDiseaseController implements Initializable {


    @FXML
    private TextField edit_name;

    @FXML
    private TextArea edit_symptoms;

    @FXML
    private TextArea edit_treatment;

    @FXML
    private Button edit_updateBtn;

    @FXML
    private AnchorPane main_form;
    private Disease disease = new Disease();

    public void setFields(){
        edit_name.setText(disease.getName());
        edit_treatment.setText(disease.getTreatment());
        edit_symptoms.setText(disease.getSymptoms());
    }

    public void updateBtn() throws IOException {
        disease.setName(edit_name.getText());
        disease.setSymptoms(edit_symptoms.getText());
        disease.setTreatment(edit_treatment.getText());

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(disease));
        requestModel.setRequestType(RequestType.EDIT_DISEASE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        AlertMessage alertMessage = new AlertMessage();
        alertMessage.successMessage("Данные были успешно обновлены");
    }

    public EditDiseaseController(Disease disease){
        this.disease = disease;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFields();
    }
}
