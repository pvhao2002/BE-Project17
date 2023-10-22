package com.example.beproject17.controller;

import com.example.beproject17.model.Appointment;
import com.example.beproject17.model.ResponseModel;
import com.example.beproject17.service.AppointmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService = new AppointmentService();
    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("/getAllMyAppointment/{userId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAllMyAppointment(@PathParam("userId") Integer userId) {
        try {
            var result = appointmentService.getAllMyAppointment(userId);
            return gson.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/getAll")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAll() {
        try {
            var result = appointmentService.getAll();
            return gson.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object add(Appointment item) {
        try {
            var result = appointmentService.add(item);
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }


    @Path("cancel")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object cancel(Appointment item) {
        try {
            var result = appointmentService.update(item.getStatus(), item.getId());
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object update(Appointment item) {
        try {
            var result = appointmentService.update(item.getStatus(), item.getId());
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }
}
