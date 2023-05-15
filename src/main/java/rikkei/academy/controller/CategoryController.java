package rikkei.academy.controller;

import rikkei.academy.model.Category;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/category"})
public class CategoryController extends HttpServlet {
    private ICategoryService categoryService = new CategoryServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println("action " + action);
        String page = request.getParameter("page");
        if(page==null){
            page = String.valueOf(0);
        }
        if (action == null ) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreat(request, response);
                break;
            case "page_grid":
                pageGridCategory(request,response);
            default:
                showFormCategory(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                actionCreateCategory(request, response);
                break;
        }
    }

    private void showFormCreat(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/content/category/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void actionCreateCategory(HttpServletRequest request, HttpServletResponse response) {
        String avatar = request.getParameter("avatar");
        String name = request.getParameter("name");
        System.out.println("avatar --->" + avatar);
        if (name.trim().equals("")) {
            request.setAttribute("validate", "The name is required!");
        } else if (avatar.trim().equals("")) {
            request.setAttribute("validate", "The avatar is required!");
            request.setAttribute("name", name);
        } else {
            request.setAttribute("success", "Create category success");
            categoryService.save(new Category(name, avatar), request);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/content/category/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormCategory(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = 1;
        if(request.getParameter("page")!=null){
             pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("pageNumber --->"+pageNumber);
        int elementOfPage = 2;
        int start = (pageNumber-1)*elementOfPage;
        List<Category> categoryList = categoryService.findAll(start,elementOfPage);
        int totalElement = categoryService.getNoOfRecords();
        int sumOfPage = (int) Math.ceil(totalElement / elementOfPage);
        request.setAttribute("listCategory", categoryList);
        request.setAttribute("sumOfPage", sumOfPage);
        request.setAttribute("pageNumber", pageNumber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/content/category/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void pageGridCategory(HttpServletRequest request, HttpServletResponse response){
        int pageNumber = 1;
        if(request.getParameter("page")!=null){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("pageNumber --->"+pageNumber);
        int elementOfPage = 3;
        int start = (pageNumber-1)*elementOfPage;
        List<Category> categoryList = categoryService.findAll(start,elementOfPage);
        int totalElement = categoryService.getNoOfRecords();
        int sumOfPage = (int) Math.ceil(totalElement / elementOfPage);
        request.setAttribute("listCategory", categoryList);
        request.setAttribute("sumOfPage", sumOfPage);
        request.setAttribute("pageNumber", pageNumber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/content/category/page.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
