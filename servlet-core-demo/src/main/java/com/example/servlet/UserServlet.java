package com.example.servlet;

import java.io.IOException;
import java.util.List;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if(idParam == null) {
            // get all
            List<User> users = userService.getAllUsers();
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().write(usersToJSON(users));
            return;
        }

        Integer id = Integer.parseInt(idParam);

        User userResp = userService.getUserById(id);

        if(userResp == null) {
            resp.setStatus(404);
            resp.setContentType("application/json");
            resp.getWriter().write(
                "{\r\n" + //
                                        "    \"message\": \"Not Found\"\r\n" + //
                                        "}"
            );
        }

        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().write(userToJSON(userResp));

    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPatch(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        if (id == null || email == null ||
                name == null || mobile == null) {
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Some fields are missing\"\n" +
                            "}"
            );
        }

        User user = new User(id, name, email, mobile);

        User createdUser = userService.createUser(user);

        response.setStatus(201);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\n" +
                        "    \"message\" : \"User Added successfully\"\n" +
                        "}"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    private String userToJSON(User userResp) {
        return "{\r\n" + //
                                "    \"id\": " + userResp.getId() + ",\r\n" + //
                                "    \"name\": " + userResp.getName() + " \",\r\n" + //
                                "    \"email\": " + userResp.getEmail() + " ,\r\n" + //
                                "    \"mobile\": " + userResp.getMobile() + " \r\n" + //
                                "}";
    }

    private String usersToJSON(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for(int i = 0; i<users.size(); i++) {
            stringBuilder.append(userToJSON(users.get(i))); 

            if(i < users.size() - 1) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
    
}
