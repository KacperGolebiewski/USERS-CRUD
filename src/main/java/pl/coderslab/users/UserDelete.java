package pl.coderslab.users;

import pl.coderslab.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDelete ", value = "/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDao = new UserDAO();
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);
        userDao.delete(id);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
