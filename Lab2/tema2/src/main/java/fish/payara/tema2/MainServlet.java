/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.tema2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    private DictionaryService dictionaryService = new DictionaryService();

    
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String language = request.getParameter("language");
        String word = request.getParameter("word");
        String definition = request.getParameter("definition");
        String captcha = request.getParameter("captcha");
        ErrorBean errorBean = new ErrorBean();
        
        int captcha_res = Integer.parseInt(captcha);
        Boolean captcha_ok = false;
        
        Cookie cks[]=request.getCookies();  
        for(int i=0;i<cks.length;i++){  
         if (cks[i].getName().equals("captcha_r")) {
             if (Integer.parseInt(cks[i].getValue()) == captcha_res)
                 captcha_ok = true;
         }
        }
        
        if (captcha_ok==false) {
            errorBean.setErrorMessage("Captcha verification not ok. Please try again and learn some math");
        }
        if (dictionaryService.verifyParameters(language, word, definition) == false) {
            errorBean.setErrorMessage("Parameters not ok. Must have at least length > 3");
        }
        if (dictionaryService.verifyEntry(language, word) == false) {
            errorBean.setErrorMessage("Word already exists");
        }
        if (errorBean.getIsSet()) {
            request.setAttribute("errorbean",errorBean);
            RequestDispatcher rd=request.getRequestDispatcher("error.jsp");  
            rd.forward(request, response); 
        } else {
            DictionaryBean dictionaryBean = dictionaryService.insertEntry(language, word, definition);
            
            Cookie ck = new Cookie("language", language);
            ck.setMaxAge(3600);
            response.addCookie(ck);
            
            request.setAttribute("dictionarybean",dictionaryBean);
            RequestDispatcher rd=request.getRequestDispatcher("result.jsp");  
            rd.forward(request, response); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String presetLanguage = "";
        
        Cookie ck[]=request.getCookies();  
        for(int i=0;i<ck.length;i++){  
         if (ck[i].getName().equals("language")) {
             presetLanguage = ck[i].getValue();
         }
        } 
        
        int nr1 = getRandomNumber(0, 20);
        int nr2 = getRandomNumber(0, nr1);
        int nr3 = getRandomNumber(0,1);
        String operation = "";
        int result = 0;
        if (nr3==0) {
            result = nr1+nr2;
            operation = String.valueOf(nr1) + "+" + String.valueOf(nr2);
        }
        else {
            result = nr1-nr2;
            operation = String.valueOf(nr1) + "-" + String.valueOf(nr2);
        }
        
        Cookie ck_captcha = new Cookie("captcha_r", String.valueOf(result));
        ck_captcha.setMaxAge(3600);
        response.addCookie(ck_captcha);
        
        request.setAttribute("operationcaptcha",operation);
        request.setAttribute("presetlanguage",presetLanguage);
        RequestDispatcher rd=request.getRequestDispatcher("input.jsp");  
        rd.forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
