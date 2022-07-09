package pl.coderslab.users;

import pl.coderslab.User;
import pl.coderslab.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDAO userDao = new UserDAO();
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("id", read);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String userId = request.getParameter("id");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        if(
                userName == null || userName.isBlank() ||
                userEmail == null || userEmail.isBlank() ||
                userPassword == null || userPassword.isBlank()
        ){
            response.sendRedirect(request.getContextPath() + "/user/edit");

        }else{
            user.setId(Integer.parseInt(userId));
            user.setUserName(userName);
            user.setEmail(userEmail);
            user.setPassword(userPassword);
            UserDAO userDao = new UserDAO();
            userDao.update(user);
            response.sendRedirect(request.getContextPath() + "/user/list");
        }



    }
}
