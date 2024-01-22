package com.model.web_market;

//import javax.jms.Session;
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
import java.util.ArrayList;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private ArrayList<Person> base;
    public  void init()
    {
        base = new ArrayList<>();
        String s;
        String []str;
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\users.txt"));
            while ((s = in.readLine()) !=null) {
                s.trim();
                str = s.split(" ");
                base.add(new Person(str[0],str[1], str[2]));
            }
            in.close();
        }
        catch (Exception e){}

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = false;
        boolean stat = false;
        for (int i = 0; i < base.size();i++)
        {
            if ((name.equals(base.get(i).getName())) && (password.equals(base.get(i).getPassword())))
                flag = true;
                if (name.equals(base.get(i).getName()) && (password.equals(base.get(i).getPassword())) && (base.get(i).getStatus().equals("admin"))){
                    stat = true;
                }

        }
        out.println("<html><body>");
        if (flag) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);

            if (stat){
                session.setAttribute("stat", "admin");
            }else{
                session.setAttribute("stat", "user");
            }

            out.println(
                    "<meta http-equiv='refresh' content='0; URL=http://localhost:8080/Web_market_war_exploded/MainPage'>" +
                    "</body></html>");
        }
        else {
            out.println("Error!!!!");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.println("</body></html>");
        out.close();

    }


}