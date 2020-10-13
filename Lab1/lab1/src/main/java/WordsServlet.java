/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.security.AccessController.getContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author x
 */
public class WordsServlet extends HttpServlet {
    
    private ArrayList<String> words;
    
    
    @Override
    public void init() throws ServletException {
        ServletContext context = this.getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/words.txt");
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text;
            
            words = new ArrayList<String>();

            try {
                // We read the file line by line and later will be displayed on the
                // browser page.
                while ((text = reader.readLine()) != null) {
                    words.add(text);
                }
            } catch (IOException ex) {
                Logger.getLogger(WordsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        
        logInfo(request);
                    
        String letters = request.getParameter("letters");
        System.out.println("Got letters:");
        System.out.println(letters);
        ArrayList<String> possibleWords = new ArrayList<String>();             
        for (int i=0; i<words.size(); i++) {
            int ok = 1;
            for (int j=0; j<words.get(i).length(); j++) {
                if (letters.indexOf(words.get(i).charAt(j)) <0 ) {
                    ok=0;
                    break;
                }
            }
            if (ok==1) {
                possibleWords.add(words.get(i));
            }
        }         
           
        if (request.getHeader("Accept").contains("text/html")) { //browser request
            respondHtml(response, possibleWords);
        } else {
            respondPlain(response, possibleWords);
        }
    }
    
    private void respondHtml(HttpServletResponse response, ArrayList<String> possibleWords) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WordsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            for (int i=0; i<possibleWords.size(); i++) {
                out.println(possibleWords.get(i)+"<br />");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    } 
    
    private void respondPlain(HttpServletResponse response, ArrayList<String> possibleWords) throws IOException {
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            for (int i=0; i<possibleWords.size(); i++) {
                out.println(possibleWords.get(i));
            }
        }
    } 
    
    private void logInfo(HttpServletRequest req) {
        getServletContext().log("Method:" + req.getMethod());
        getServletContext().log("IP:" + req.getRemoteAddr());
        getServletContext().log("User-Agent:" + req.getHeader("User-Agent"));
        getServletContext().log("Language:" + req.getHeader("Accept-Language"));
        Enumeration<String> enumeration = req.getParameterNames();
        while(enumeration.hasMoreElements()) {
            String parametername = enumeration.nextElement();
            getServletContext().log(parametername + " : " +req.getParameter(parametername));
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
        processRequest(request, response);
        
        //response.setContentType("text/html");
        //response.getWriter().println("words.html");
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
