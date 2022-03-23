/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se1610.model.News;
import com.se1610.service.INewService;
import com.se1610.utils.Utility;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PHAM KHAC VINH
 */
@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

    /**
     * insert
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Inject
    private INewService newService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //get data from request
        News newsModel = Utility.Of(request.getReader()).toModel(News.class);

        //save data to database
        newsModel = newService.save(newsModel);

        objectMapper.writeValue(response.getOutputStream(), newsModel);

    }
    
    
    /**
     * update
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response); //To change body of generated methods, choose Tools | Templates.
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //News updateNews = Utility.Of(request.getReader()).toModel(News.class);
        News updateNews = Utility.Of(request.getReader()).toModel(News.class);

        updateNews = newService.update(updateNews);

        mapper.writeValue(response.getOutputStream(), updateNews);

    }
    

    /**
     * delete
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
