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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SignUp() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        Account account = new Account();
        GenericDao<Account> gdao = new GenericDao<Account>(Account.class);

        String uname = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("retypepassword");

        if (uname == "" || email == "" || password == "" || repassword == "") {
            pw.println("All Fields Required!!");
        } else {
            if (password.equals(repassword)) {
                try {
                    account.setName(uname);
                    account.setEmail(email);
                    account.setPassword(password);

                    gdao.save(account);
                    pw.println("User" + uname + "Saved Successfully!!");
                } catch (Exception ex) {
                    pw.println(ex);
                }
            } else {
                pw.println("Password not Matched");
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
