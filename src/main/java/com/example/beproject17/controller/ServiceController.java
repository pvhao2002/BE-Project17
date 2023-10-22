package com.example.beproject17.controller;


import com.example.beproject17.model.ResponseModel;
import com.example.beproject17.model.Service;
import com.example.beproject17.service.ServicesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/services")
public class ServiceController {
    private final ServicesService servicesService = new ServicesService();
    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("/getAll")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAll() {
        try {
            var result = servicesService.getAll();
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
    public Object add(Service item) {
        try {
            var result = servicesService.add(item);
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
    public Object update(Service item) {
        try {
            var result = servicesService.update(item);
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@PathParam("id") Integer id) {
        try {
            var result = servicesService.delete(id);
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/getById/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getById(@PathParam("id") Integer id) {
        try {
            var result = servicesService.getById(id);
            ResponseModel model = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(model);
        } catch (Exception e) {
            return null;
        }
    }

}
