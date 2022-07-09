package pl.coderslab.users;

import pl.coderslab.User;
import pl.coderslab.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        User user = new User();
        UserDAO userDao = new UserDAO();

        if(
                userName == null || userName.isBlank() ||
                userEmail == null || userEmail.isBlank() ||
                userPassword == null || userPassword.isBlank() // jesli ktores z pol jest niewypelnione to forma nie powinna przejsc

        ) {
            response.sendRedirect(request.getContextPath() + "user/add"); // odsylam spowrotem do strony dodawania uzytkownika

        }else {
            user.setUserName(userName);
            user.setEmail(userEmail);
            user.setPassword(userPassword);
            userDao.create(user);
            response.sendRedirect(request.getContextPath() + "user/list"); // w przypadku wpisania wszystkich pol w formie odsylam do strony list (pametaj by bylo w els a nie za wasami bo bedzie error
        }
    }
}
