package ua.hypson.hibernatelab;

import ua.hypson.hibernatelab.dao.impl.HibernateRoleDaoImpl;
import ua.hypson.hibernatelab.dao.impl.HibernateUserDaoImpl;
import ua.hypson.hibernatelab.dao.interfaces.RoleDao;
import ua.hypson.hibernatelab.dao.interfaces.UserDao;
import ua.hypson.hibernatelab.entity.Role;
import ua.hypson.hibernatelab.entity.User;
import ua.hypson.hibernatelab.service.DateUtils;

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
@WebServlet(name = "EditorServ", description = "servlet for editing users", urlPatterns = {"/EditorServ"})
public class EditorServlet extends HttpServlet {
    private static final long serialVersionUID = 15648974312L;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DateUtils dateUtils;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorServlet() {
        super();
        userDao = new HibernateUserDaoImpl();
        roleDao = new HibernateRoleDaoImpl();
        dateUtils = new DateUtils();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User editingUser = (User) session.getAttribute("editingUser");
        Role role = editingUser.getRole();
        String roleName = request.getParameter("role");
        if (!role.getName().equals(roleName)) {
            role = roleDao.findByName(roleName);
        }
        User updatingUser = User.createNewUser(editingUser.getLogin(),
                request.getParameter("password"), request.getParameter("email"), request.getParameter("firstName"),
                request.getParameter("lastName"), dateUtils.parseDate(request.getParameter("birthday")), role);
        updatingUser.setId(editingUser.getId());//hibernate makes update basing on entity's id.
        userDao.update(updatingUser);
        request.getRequestDispatcher("LoginServ").forward(request, response);
    }
}
