package com.example.beproject17.controller;


import com.example.beproject17.model.ResponseModel;
import com.example.beproject17.service.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/")
public class HomeController {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private final DashboardService dashboardService = new DashboardService();

    @Path("/dashboard")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Object index() {
        try {
            var result = dashboardService.getDashboard();
            return gson.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }
}
