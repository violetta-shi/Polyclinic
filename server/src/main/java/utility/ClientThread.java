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
                    case GET_DOCTOR:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        Doctor doctor = doctorService.findEntityByUserId(requestUser);
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
                    case ADD_VISIT:{
                        break;
                    }
                    case ADD_PATIENT:{
                        Patient patient = gson.fromJson(request.getRequestMessage(), Patient.class);
                        personService.saveEntity(patient.getPerson());
                        addressService.saveEntity(patient.getAddress());
                        patientService.saveEntity(patient);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    }
                    /*case ADD_FLIGHT:gson.fromJson(request.getRequestMessage(), Flight.class);
                        Flight flight = gson.fromJson(request.getRequestMessage(), Flight.class);
                        routeService.saveEntity(flight.getRoute());
                        aircraftService.saveEntity(flight.getAircraft());
                        flightService.saveEntity(flight);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    case DELETE_FLIGHT:
                        flight = gson.fromJson(request.getRequestMessage(), Flight.class);
                        flightService.deleteEntity(flight);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    case GET_FLIGHT:
                        flight = gson.fromJson(request.getRequestMessage(), Flight.class);
                        flight = flightService.findEntity(flight.getId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(flight));
                        break;
                    case GETALL_FLIGHT:
                        List<Flight> flights = new ArrayList<>();
                        List<ResultMark> result = calcCondorcet();
                        for (ResultMark resultMark :
                                result) {
                            flights.add(resultMark.getFlight());
                        }
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(flights));
                        break;
                    case UPDATE_FLIGHT:
                        flight = gson.fromJson(request.getRequestMessage(), Flight.class);
                        routeService.updateEntity(flight.getRoute());
                        aircraftService.updateEntity(flight.getAircraft());
                        flightService.updateEntity(flight);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    case UPDATE_MARK:
                        UserMark mark = gson.fromJson(request.getRequestMessage(), UserMark.class);
                        userMarkService.updateEntity(mark);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    case UPDATE_PASSENGER:
                        Passenger passenger = gson.fromJson(request.getRequestMessage(), Passenger.class);
                        List<Passenger> passengers = passengerService.findAllEntities();
                        if (passengers.stream().anyMatch(x -> x.getPlaceNumber() == passenger.getPlaceNumber() && x.getId() != passenger.getId() && x.getFlight().getId()== passenger.getFlight().getId())) {
                            response = new Response(ResponseStatus.ERROR, "Это место уже занято!", "");
                            break;
                        }
                        passengerService.updateEntity(passenger);
                        response = new Response(ResponseStatus.OK, "Готово!", "");
                        break;
                    case CONDORCET:
                        result = calcCondorcet();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(result));
                        break;*/
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
