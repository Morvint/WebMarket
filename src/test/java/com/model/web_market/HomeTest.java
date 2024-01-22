package com.model.web_market;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class HomeTest {
    @Mock
    private PrintWriter out;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void TestFiles() throws IOException {
        try{
        File f1 = new File("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\product.txt");
        assertEquals(f1.exists(),true);
        File f2 = new File("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\users.txt");
        assertEquals(f2.exists(),true);
        File f3 = new File("C:\\Users\\tv_20\\IdeaProjects\\Web_market\\Bag.txt");
        assertEquals(f3.exists(),true);
        MainPage a = new MainPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestPage() throws IOException, ServletException {
        try{
            MainPage servlet = new MainPage();
            PrintWriter out = new PrintWriter(System.out);

            servlet.getServletContext().setAttribute("name", "John Doe");
            servlet.getServletContext().setAttribute("stat", "user");

            servlet.printAllLog(out, "user");

            String expectedOutput = "<center><h2>Добро пожаловать на Skamshop, John Doe</h2></center>";
            String actualOutput = out.toString();

            assertTrue(actualOutput.contains(expectedOutput));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestprintAllNolog() throws IOException, ServletException {
        try{
            MainPage a = new MainPage();
            a.printAllNolog(out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void TestLogin() throws IOException, ServletException {
        try {
            Mockito.when(request.getParameter("name")).thenReturn("1");
            Mockito.when(request.getParameter("description")).thenReturn("1");
            Mockito.when(request.getParameter("qauantity")).thenReturn("1");
            AddProduct servlet = new AddProduct();

            servlet.doPost(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void TestAddProtected() throws IOException, ServletException {
        try {
            Mockito.when(request.getParameter("name")).thenReturn("1");
            Mockito.when(request.getParameter("password")).thenReturn("1");
            Login servlet = new Login();

            servlet.doPost(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

