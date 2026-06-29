package com.unpam.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.unpam.view.MainForm;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})

public class LoginController extends HttpServlet {

    protected void processRequest(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        String userName =
        request.getParameter("userName");

        String password =
        request.getParameter("password");

        if ((userName != null) && (password != null)) {

            if (userName.equals("admin")
            && password.equals("admin")) {

                HttpSession session =
                request.getSession(true);

                session.setAttribute(
                "userName", userName);

                response.sendRedirect(
                "MahasiswaController");

                return;
            }
        }

        MainForm mainForm = new MainForm();

        String konten =

        "<h2>Login</h2>"

        + "<form method='post'>"

        + "<table>"

        + "<tr>"
        + "<td>User Name</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' name='userName'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Password</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='password' name='password'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td colspan='3' align='center'>"
        + "<br>"
        + "<input type='submit' value='Login'>"
        + "</td>"
        + "</tr>"

        + "</table>"

        + "</form>";

        mainForm.tampilkan(request, response, konten);
    }

    @Override
    protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        processRequest(request, response);
    }
}