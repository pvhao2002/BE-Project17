package com.example.beproject17.controller;


import com.example.beproject17.model.ResponseModel;
import com.example.beproject17.model.User;
import com.example.beproject17.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("user")
public class UserController {
    private final UserService userService = new UserService();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(User user) {
        var result = userService.login(user.getUsername(), user.getPassword());
        if (result != null) {
            return gson.toJson(result);
        } else {
            // return error
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.INCORRECT_USERNAME_OR_PASSWORD, null));
        }
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object register(User user) {
        var result = userService.getByUsername(user.getUsername());
        String status = "";
        if (result == null) {
            var rs = userService.register(user);
            status = rs.toString();
        } else {
            if (result.getIsDeleted() == Boolean.TRUE) {
                user.setIsDeleted(false);
                var rs = userService.update(user);
                status = rs.toString();
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.USERNAME_ALREADY_EXISTS, null);
                return gson.toJson(errorModel);
            }
        }
        return getResponse(status);
    }

    @Path("/get/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getById(@PathParam("id") Integer id) {
        var result = userService.getById(id);
        if (result != null) {
            return gson.toJson(result);
        } else {
            ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
            return gson.toJson(errorModel);
        }
    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object update(User user) {
        var result = userService.getById(user.getId());
        String status = "";
        if (result != null) {
            user.setIsDeleted(false);
            var rs = userService.update(user);
            status = rs.toString();
        } else {
            ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.USERNAME_NOT_FOUND, null);
            return gson.toJson(errorModel);
        }
        return getResponse(status);
    }

    private Object getResponse(String status) {
        String STATUS_FAILED = "Error";
        if (status.contains(STATUS_FAILED)) {
            ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null);
            return gson.toJson(errorModel);
        }
        ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, null);
        return gson.toJson(responseModel);
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@PathParam("id") Integer id) {
        try {
            var result = userService.delete(id);
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAll() {
        var result = userService.getAll();
        ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
        return gson.toJson(responseModel);
    }
}
