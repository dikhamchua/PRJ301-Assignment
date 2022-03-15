package com.se1610.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se1610.model.User;
import com.se1610.service.ICategoryService;
import com.se1610.service.INewService;
import com.se1610.dao.INewsDAO;
import com.se1610.model.News;

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
        String title = "Bài viết 4";
        String content = "Bài viết 4";
        int categoryID = 2;
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategoryID(categoryID);
        
        newsService.save(news);
        
        
        request.setAttribute("categories", categoryService.getAll());
        request.getRequestDispatcher("view/web/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
