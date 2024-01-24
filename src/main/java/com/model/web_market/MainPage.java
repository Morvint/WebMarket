package com.model.web_market;

import com.control.web_market.Product;
import jakarta.servlet.ServletException;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "MainPage", value = "/MainPage")
public class MainPage extends HttpServlet {

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
            String name = (String)session.getAttribute("name");
            String stat = (String)session.getAttribute("stat");
            if (stat.equals("admin")){
                req.getRequestDispatcher("link_admin.html").include(req,resp);
                out.println("<center><h2>Добро пожаловать на Skamshop, " + name + "</h2>");
                out.println("<body>");

                printAllLog(out, stat);
            }

            if(stat.equals("user")){
                req.getRequestDispatcher("link_log.html").include(req,resp);
                out.println("<center><h2>Добро пожаловать на Skamshop, " + name + "</h2>");
                out.println("<body>");

                printAllLog(out, stat);
            }

        }
        else
        {
            req.getRequestDispatcher("link_nolog.html").include(req,resp);
            out.println("<center><h2>Добро пожаловать на Skamshop</h2>");
            out.println("<h2>Вы являетесь незарегистрированным пользователем</h2>");
            out.println("<h2>Для совершения покупок необходимо зарегистрироваться</h2></center>");
            out.println("<body>");

            printAllNolog(out);
        }

        out.println("</body>");
        out.close();
    }

    protected void printAllNolog(PrintWriter out)throws IOException
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
        out.println("</div></div>");
        out.println("</div>");

        for(int i = 0; i < ads.size(); i++){
            if(Integer.parseInt(ads.getAd(i).getQuantity()) == 0){
                continue;
            }

            out.println("<div class=\"flex-container-2\">");
            out.println("<div><div>");
            out.println(ads.getAd(i).getName());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getDescription());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getQuantity());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getCost() + "р");
            out.println("</div></div>");
            out.println("</div>");
        }
        in.close();
        out.close();
    }

    protected void printAllLog(PrintWriter out, String stat)throws IOException
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
        out.println("Название");
        out.println("</div></div><div><div>");
        out.println("Описание товара");
        out.println("</div></div><div><div>");
        out.println("Количество товара");
        out.println("</div></div><div><div>");
        out.println("Цена товара");
        out.println("</div></div><div><div>");
        out.println("Положить в корзину/удалить из каталога");
        out.println("</div></div>");
        out.println("</div>");

        for(int i = 0; i < ads.size(); i++){
            if(Integer.parseInt(ads.getAd(i).getQuantity()) == 0 && !(stat.equals("admin"))){
                continue;
            }
            out.println("<div class=\"flex-container-2\">");
            out.println("<div><div>");
            out.println(ads.getAd(i).getName());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getDescription());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getQuantity());
            out.println("</div></div>");

            out.println("<div><div>");
            out.println(ads.getAd(i).getCost() + "р");
            out.println("</div></div>");

            out.println("<div><div>");
            out.println("<div style=\"float: left;\"><form action=\"AddBag\" method=\"post\">");
            out.println("<input type=\"image\" src=\"shopping-cart-empty-2.png\" width=\"32px\" height=\"32px\"/>");
            out.println("<input type=\"hidden\" name=\"name\" value=\"" + ads.getAd(i).getName() + "\">");
            out.println("</form></div>");

            if (stat.equals("admin")){
                out.println("<div style=\"float: center;\"><form action=\"removeProduct\" method=\"post\">");
                out.println("<input type=\"image\" src=\"Trash_bag.png\" width=\"32px\" height=\"32px\"/>");
                out.println("<input type=\"hidden\" name=\"name\" value=\"" + ads.getAd(i).getName() + "\">");
                out.println("</form></div>");
                out.println("</div></div>");
            }else{
                out.println("</div></div>");
            }

            out.println("</div>");
        }

        in.close();
        out.close();
    }
}