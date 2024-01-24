package com.control.web_market;

import com.control.web_market.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class removeProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
        request.getRequestDispatcher("link_log.html").include(request,response);

        HttpSession session = request.getSession(false);
        String name = request.getParameter("name");
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
                ads.add(str_prod[0], str_prod[1], str_prod[2], str_prod[3]);
            }



            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\Bag.txt"));

            String s;
            String [] str;
            StringBuilder result = new StringBuilder();
            while((s = in.readLine()) != null){
                str = s.split(";");

                for(int i = 0; i < str.length; i++){
                    if (i == 0){
                        result.append(str[0]);
                        result.append(";");
                        continue;
                    }

                    String [] text;
                    text = str[i].split("//");

                    if(i == 1 && str[0].equals(sess_name)){
                        if(text[0].equals(name)){
                            continue;
                        }
                        result.append(text[0] + "//" + text[1]);
                        result.append(";");
                        continue;
                    }
                    if(text[0].equals(name)){
                        continue;
                    }
                    result.append(text[0] + "//" + text[1]);
                    if(i != str.length - 1){
                        result.append(";");
                    }
                }
                result.append("\n");
            }

            PrintWriter writer = new PrintWriter("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\Bag.txt");
            writer.print("");
            writer.print(result);
            writer.close();



            StringBuilder st = new StringBuilder();
            for(int i = 0; i < ads.size(); i++){
                if (ads.getAd(i).getName().equals(name)){
                    continue;
                }
                st.append(ads.getAd(i).getName() + ";");
                st.append(ads.getAd(i).getQuantity() + ";");
                st.append(ads.getAd(i).getDescription() + ";");
                st.append(ads.getAd(i).getCost() + "\n");
            }
            String text = st.toString();
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