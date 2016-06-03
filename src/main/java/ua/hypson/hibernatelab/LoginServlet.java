package ua.hypson.hibernatelab;

import ua.hypson.hibernatelab.dao.impl.HibernateRoleDaoImpl;
import ua.hypson.hibernatelab.dao.impl.HibernateUserDaoImpl;
import ua.hypson.hibernatelab.dao.interfaces.RoleDao;
import ua.hypson.hibernatelab.dao.interfaces.UserDao;
import ua.hypson.hibernatelab.entity.Role;
import ua.hypson.hibernatelab.entity.User;
import ua.hypson.hibernatelab.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServ", description = "login servlet", urlPatterns = {"/LoginServ"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 789456123L;
    private static final String ADMINHOME_JSP = "adminhome.jsp";
    private static final String SUCCESS_JSP = "success.jsp";
    private static final String USERS = "users";
    private static final String USER = "user";
    private LoginService loginService;
    private Role adminRole;

    @Override
    @SuppressWarnings("deprecation")
    public void init() throws ServletException {

        RoleDao roleDao = new HibernateRoleDaoImpl();
        UserDao userDao = new HibernateUserDaoImpl();

        adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("admin");

        Role userRole = new Role();
        userRole.setId(2L);
        userRole.setName("regular");
        roleDao.create(adminRole);
        roleDao.create(userRole);

        User admin = User.createNewUser("admin", "admin", "admin@my.app", "Vasiliy", "Zhar", new Date(87, 7, 23),
                adminRole);
        User vika = User.createNewUser("vika", "vika", "vika@my.app", "Vika", "Zhar", new Date(97, 7, 23), userRole);
        User masha = User.createNewUser("masha", "masha", "masha@my.app", "Masha", "Zhar", new Date(85, 7, 23), userRole);
        User inna = User.createNewUser("inna", "inna", "inna@my.app", "Inna", "Zhar", new Date(77, 7, 23), userRole);
        userDao.create(admin);
        userDao.create(inna);
        userDao.create(vika);
        userDao.create(masha);

        loginService = new LoginService();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "no logged user for this session");

        }
        User user = (User) session.getAttribute(USER);

        dispatchOnRole(request, response, user);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        if (session.getAttribute(USER) == null) {
            user = loginService.login(request.getParameter("login"), request.getParameter("password"));
            session.setAttribute(USER, user);
        } else {
            user = (User) session.getAttribute(USER);
        }
        dispatchOnRole(request, response, user);
    }

    private void dispatchOnRole(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if (user.getRole().equals(adminRole)) {
            List<User> users = loginService.getAllUsers();
            request.setAttribute(USERS, users);
            request.getRequestDispatcher(ADMINHOME_JSP).forward(request, response);
        } else {
            request.getRequestDispatcher(SUCCESS_JSP).forward(request, response);
        }
    }
}
