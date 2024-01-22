package com.control.web_market;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class ReProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
        request.getRequestDispatcher("link_log.html").include(request,response);

        HttpSession session = request.getSession(false);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String qauantity = request.getParameter("qauantity");
        String sess_name = (String)session.getAttribute("name");
        out.println("<p>" + name + "</p>");
        out.println("<p>" + sess_name + "</p>");

        try {
            BufferedReader in_prod = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\product.txt"));

            String s_prod;
            String [] str_prod;
            Product ads = new Product();
            while((s_prod = in_prod.readLine()) != null){
                str_prod = s_prod.split(";");
                ads.add(str_prod[0], str_prod[1], str_prod[2]);
            }

            StringBuilder result = new StringBuilder();
            for(int i = 0; i < ads.size(); i++){
                if(name.equals(ads.getAd(i).getName())){
                    result.append(ads.getAd(i).getName() + ";");
                    result.append(qauantity + ";");
                    result.append(description + "\n");
                    continue;
                }
                result.append(ads.getAd(i).getName() + ";");
                result.append(ads.getAd(i).getQuantity() + ";");
                result.append(ads.getAd(i).getDescription() + "\n");
            }

            String text = result.toString();
            PrintWriter writer_prod = new PrintWriter("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\product.txt");
            writer_prod.print("");
            writer_prod.print(text);
            writer_prod.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        out.println("<meta http-equiv='refresh' content='0; URL=http://localhost:8080/Web_market_war_exploded/MainPage'>" +
                "</body></html>");

        out.println();
        out.println("</body></html>");
        out.close();
    }
}