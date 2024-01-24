package com.model.web_market;

import com.control.web_market.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Bag", value = "/Bag")
public class Bag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<head><meta charset=\"UTF-8\" />" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />" +
                "<title>Document</title>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" +
                "</head>");

        HttpSession session = req.getSession(false);
        if(session != null)
        {
            req.getRequestDispatcher("link_log.html").include(req,resp);
            String name = (String)session.getAttribute("name");
            out.println("<center><h2>Это ваша корзина," + name + "</h2>");
            out.println("<body>");

            getProduct(out, name);
        }
        else
        {
            req.getRequestDispatcher("link_nolog.html").include(req,resp);
            out.println("<center><h2>Добро пожаловать на Skamshop</h2>");
            out.println("<h2>Я не понимаю как вы здесь оказались</h2></center>");
            out.println("<body>");
        }


        out.println("</body>");
        out.close();
    }

    private void getProduct(PrintWriter out, String name_sess)throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\Bag.txt"));

        String s;
        String [] str;
        StringBuilder result = new StringBuilder();

        while((s = in.readLine()) != null){
            str = s.split(";");
            if(str[0].equals(name_sess)){
                result.append(s);
            }
        }
        String text = result.toString();
        printAllNoLog(out, text);
    }
    private void printAllNoLog(PrintWriter out, String bag_product)throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\product.txt"));

        String s;
        String [] str;
        Product ads = new Product();
        while((s = in.readLine()) != null){
            str = s.split(";");
            ads.add(str[0], str[1], str[2], str[3]);
        }

        out.println("<div class=\"flex-container-2\">");
        out.println("<div><div>");
        out.println("Наименование товара");
        out.println("</div></div><div><div>");
        out.println("Описание товара");
        out.println("</div></div><div><div>");
        out.println("Количество товара");
        out.println("</div></div><div><div>");
        out.println("Цена");
        out.println("</div></div><div><div>");
        out.println("Удалить товар");
        out.println("</div></div>");
        out.println("</div>");

        String [] st;
        st = bag_product.split(";");

        Integer Finalcost = 0;

        for(int i = 0; i < ads.size(); i++){
            for (int j = 1; j < st.length;j++){
                String [] text;
                text = st[j].split("//");
                if(text[0].equals(ads.getAd(i).getName())){
                    out.println("<div class=\"flex-container-2\">");
                    out.println("<div><div>");
                    out.println(ads.getAd(i).getName());
                    out.println("</div></div>");

                    out.println("<div><div>");
                    out.println(ads.getAd(i).getDescription());
                    out.println("</div></div>");

                    out.println("<div><div>");
                    out.println(text[1]);
                    out.println("</div></div>");

                    out.println("<div><div>");
                    out.println(ads.getAd(i).getCost() + "р");
                    out.println("</div></div>");

                    out.println("<div><div>");
                    out.println("<form action=\"removeBag\" method=\"post\">");
                    out.println("<input type=\"image\" src=\"Trash_bag.png\" width=\"32px\" height=\"32px\"/>");
                    out.println("<input type=\"hidden\" name=\"name\" value=\"" + ads.getAd(i).getName() + "\">");
                    out.println("</form></div></div>");

                    out.println("</div>");
                    Finalcost = Finalcost + (Integer.parseInt(ads.getAd(i).getCost()) * Integer.parseInt(text[1]));
                }

            }
        }

        out.println("<div class=\"flex-container-2\">");
        out.println("<div>");
        System.out.println(Finalcost);
        out.println("Итоговая стоимость товаров в корзине " + Integer.toString(Finalcost) + "р");
        out.println("</div></div>");

        in.close();
        out.close();
    }
}
