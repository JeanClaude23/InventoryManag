/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        int i = 0;
        List<Account> list = new ArrayList<Account>();
        GenericDao<Account> gdao = new GenericDao(Account.class);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == "" || password == "") {
            pw.println("All fields required");
        } else {
            list = gdao.findAll();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Account acc = (Account) iterator.next();
                if (email.equals(acc.getEmail()) && password.equals(acc.getPassword())) {

                    HttpSession session = request.getSession();
                    session.setAttribute("user", acc.getName());

                    i++;
                    pw.println("login");
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
