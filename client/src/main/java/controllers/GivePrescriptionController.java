package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import messages.AlertMessage;
import model.Doctor;
import model.Patient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GivePrescriptionController implements Initializable {

    @FXML
    private TextField dose;

    @FXML
    private Button give;

    @FXML
    private TextArea instraction;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField name;
    private Doctor doctor;
    private Patient patient;
    public GivePrescriptionController(Doctor doctor, Patient patient){
        this.doctor = doctor;
        this.patient = patient;
    }

    public void create(){
        File file = new File("D://" + patient.getUser().getPerson().getLastName() + "Prescription.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file, true);
            try
            {
                AlertMessage alert = new AlertMessage();
                out.write("Рецепт"+ "\n");
                out.write("Выдан пациенту " + patient.getUser().getPerson().getLastName() + " ");
                out.write("Врачом " + doctor.getUser().getPerson().getLastName() + "\n");
                out.write("Лекарственное средство " + name.getText() + " " + dose.getText()+ "\n");
                out.write("Инструкция по применению:" + instraction.getText() + "\n");
                out.write(String.valueOf(new Date()));
                alert.successMessage("Талон успешно сохранен!");

            }finally {
                out.close();
            }
        }catch (IOException e1){
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
