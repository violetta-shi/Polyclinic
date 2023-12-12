package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import enums.ResponseStatus;
import model.*;
import services.*;
import tcp.*;

import javax.print.Doc;


public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;

    private UserService userService = new UserService();
    private PersonService personService = new PersonService();
    private AdminService adminService = new AdminService();
    private AddressService addressService = new AddressService();
    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();
    private DiseaseService diseaseService = new DiseaseService();
    private VisitService visitService  = new VisitService();

    public ClientThread(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();

                request = gson.fromJson(message, Request.class);
                switch (request.getRequestType()) {
                    case REGISTER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().noneMatch(x -> x.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))) {
                            personService.saveEntity(user.getPerson());
                            userService.saveEntity(user);
                            userService.findAllEntities();
                            User returnUser;
                            returnUser = userService.findEntity(user.getUserId());
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(returnUser));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такой пользователь уже существует!", "");
                        }
                        break;
                    }
                    case LOGIN: {
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())) && userService.findAllEntities().stream().anyMatch(x -> x.getPassword().equals(requestUser.getPassword()))) {
                            User user = userService.findAllEntities().stream().filter(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())).findFirst().get();
                            user = userService.findEntity(user.getUserId());
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(user));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    }
                    case GET_ADMIN:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        Admin admin = adminService.findEntityByUserId(requestUser);
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(admin));
                        break;
                    }
                    case GET_DOCTOR_BY_USER:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        Doctor doctor = doctorService.findEntityByUserId(requestUser);
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(doctor));
                        break;
                    }
                    case GET_PATIENT:{
                        Patient requestPatient = gson.fromJson(request.getRequestMessage(), Patient.class);
                        Patient patient = patientService.findEntity(requestPatient.getPatientId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(patient));
                        break;
                    }
                    case GET_PATIENT_BY_USER:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        Patient patient = patientService.findEntityByUserId(requestUser);
                        try{
                            gson.toJson(patient);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(patient));
                        break;
                    }
                    case GET_DOCTOR:{
                        Doctor requestDoctor = gson.fromJson(request.getRequestMessage(), Doctor.class);
                        Doctor doctor = doctorService.findEntity(requestDoctor.getDoctorId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(doctor));
                        break;
                    }
                    case GETALL_DOCTORS:{
                        List<Doctor> doctors = doctorService.findAllEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(doctors));
                        break;
                    }
                    case GETALL_PATIENTS:{
                        List<Patient> patients = patientService.findAllEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(patients));
                        break;
                    }
                    case GETALL_DISEASES:{
                        List<Disease> diseases = diseaseService.findAllEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(diseases));
                        break;
                    }
                    case GETALL_VISITS:{
                        List<Visit> visits = visitService.findAllEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(visits));
                        break;
                    }
                    case ADD_VISIT:{
                        break;
                    }
                    case ADD_PATIENT:{
                        Patient patient = gson.fromJson(request.getRequestMessage(), Patient.class);
                        personService.saveEntity(patient.getUser().getPerson());
                        userService.saveEntity(patient.getUser());
                        addressService.saveEntity(patient.getAddress());
                        patientService.saveEntity(patient);
                        response = new Response(ResponseStatus.OK, "Готово!", "Пациент успешно добавлен!");
                        break;
                    }
                    case ADD_DOCTOR:{
                        Doctor doctor =  gson.fromJson(request.getRequestMessage(), Doctor.class);
                        personService.saveEntity(doctor.getUser().getPerson());
                        userService.saveEntity(doctor.getUser());
                        doctorService.saveEntity(doctor);
                        response = new Response(ResponseStatus.OK, "Готово!", "Пациент успешно добавлен!");
                        break;
                    }
                    case EDIT_PATIENT:{
                        Patient patient =  gson.fromJson(request.getRequestMessage(), Patient.class);
                        personService.updateEntity(patient.getUser().getPerson());
                        addressService.updateEntity(patient.getAddress());
                        patientService.updateEntity(patient);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    }
                    case DELETE_PATIENT:{
                        Patient patient =  gson.fromJson(request.getRequestMessage(), Patient.class);
                        patientService.deleteEntity(patient);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    }
                    case DELETE_DISEASE:{
                        Disease disease = gson.fromJson(request.getRequestMessage(), Disease.class);
                        diseaseService.deleteEntity(disease);
                        response = new Response(ResponseStatus.OK, "Готово!", "Данные успешно изменены!");
                        break;
                    }
                    case EDIT_DOCTOR:{
                        Doctor doctor = gson.fromJson(request.getRequestMessage(), Doctor.class);
                        personService.updateEntity(doctor.getUser().getPerson());
                        userService.updateEntity(doctor.getUser());
                        doctorService.updateEntity(doctor);
                        response = new Response(ResponseStatus.OK, "Готово!", "Данные успешно изменены");
                        break;
                    }
                    case EDIT_USER:{
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        userService.updateEntity(user);
                        response = new Response(ResponseStatus.OK, "Готово!", "Данные успешно изменены!");
                        break;
                    }
                    case EDIT_DISEASE:{
                        Disease disease = gson.fromJson(request.getRequestMessage(), Disease.class);
                        diseaseService.updateEntity(disease);
                        response = new Response(ResponseStatus.OK, "Готово!", "Данные успешно изменены!");
                        break;
                    }
                }
                out.println(gson.toJson(response));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " закрыл соединение.");
            try {

                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
