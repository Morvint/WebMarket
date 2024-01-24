package com.control.web_market;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
//        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
//        request.getRequestDispatcher("link.html").include(request, response);

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String qauantity = request.getParameter("qauantity");
        String cost = request.getParameter("cost");

        StringBuilder str = new StringBuilder();
        str.append(name + ";");
        str.append(qauantity + ";");
        str.append(description + ";");
        str.append(cost + "\n");

        String text = str.toString();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\product.txt", true));
            writer.write(text);
            writer.close();

        } catch (Exception e) {
        }

        out.println("<html>" +
                "<head>" +
                "    <meta http-equiv='refresh' content='0; URL=http://localhost:8080/Web_market_war_exploded/MainPage'>" +
                "</head>" +
                "</html>");

        out.close();
    }
}