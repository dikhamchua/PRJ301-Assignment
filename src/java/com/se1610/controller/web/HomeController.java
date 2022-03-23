package com.se1610.controller.web;

import com.se1610.model.News;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se1610.service.ICategoryService;
import com.se1610.service.INewService;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap"})
public class HomeController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;
    
    @Inject INewService newsService;
    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News updateNews = new News();
        updateNews.setId(5);
        updateNews.setTitle("bai viet test");
        updateNews.setCategoryID(4);
        News news = newsService.update(updateNews);
        System.out.println(news.getTitle());
        
        request.setAttribute("categories", categoryService.getAll());
        request.getRequestDispatcher("view/web/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
