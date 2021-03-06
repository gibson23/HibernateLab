package ua.hypson.hibernatelab;

import ua.hypson.hibernatelab.dao.impl.HibernateUserDaoImpl;
import ua.hypson.hibernatelab.dao.interfaces.UserDao;
import ua.hypson.hibernatelab.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "EditDeleteServ", description = "login servlet", urlPatterns = {"/EditDeleteServ"})
public class EditDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 15648974312L;
    private final UserDao userDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteServlet() {
        super();
        userDao = new HibernateUserDaoImpl();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        String editingUserLogin = request.getParameter("edit");
        String deletingUserLogin = request.getParameter("delete");
        if (editingUserLogin != null) {
            User editingUser = userDao.findByLogin(editingUserLogin);
            session.setAttribute("editingUser", editingUser);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } else if (deletingUserLogin != null) {
            User deletingUser = userDao.findByLogin(deletingUserLogin);
            userDao.remove(deletingUser);
            request.getRequestDispatcher("LoginServ").forward(request, response);
        }
    }
}
