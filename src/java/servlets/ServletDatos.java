/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.Salario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Amelia
 */
public class ServletDatos extends HttpServlet {

    private static DecimalFormat df = new DecimalFormat("#.##");
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ejemplo Servlets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Pruebas de Servlet </h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);        

        Salario objetoDatos = new Salario();
        if (request.getParameter("base") != null) {
            objetoDatos.setSalarioBase(Float.parseFloat(request.getParameter("base")));
        }

        if (request.getParameter("bonus") != null) {
            objetoDatos.setBonificacion(Float.parseFloat(request.getParameter("bonus")));
        }

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Cálculo para empleados";
        String docType
                = "<!doctype html public \"-//w3c//dtd html 4.0 "
                + "transitional//en\">\n";
        Double isr = objetoDatos.getSalarioBase() * 0.05;
        Double igss = objetoDatos.getSalarioBase() * 0.0483;
        Double total = (objetoDatos.getSalarioBase() + objetoDatos.getBonificacion()) - igss - isr;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        out.println(docType
                + "<html>\n"
                + "<head><title>" + title + "</title>"
                + "<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\"></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n"
                + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css\">\n"
                + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js\"></script>\n"
                + "<div class='row'>\n"
                + "<div class='col s12 m12'>\n"
                + "<div class='card white hoverable'>\n"
                + "<div class='card-content black-text'>\n"
                + "<span class='card-title'>Descuentos a salario:</span>\n"
                + "<div class='row'>\n"
                + "<div class='input-field col s6'>\n"
                + "<i class='material-icons prefix'>attach_money</i>\n"
                + "<input id='base' type='text'  name='base' value=" + String.format("%.2f",objetoDatos.getSalarioBase()) + " readonly>\n"
                + "<label for='icon_prefix'>Salario base</label>\n"
                + "</div>\n"
                + "<div class='input-field col s6'>\n"
                + "<i class='material-icons prefix'>card_giftcard</i>\n"
                + "<input id='bonus' type='text'  name='bonus' value=" + String.format("%.2f", objetoDatos.getBonificacion()) + " readonly>\n"
                + "<label for='icon_telephone'>Bonificación</label>\n"
                + "</div>\n"
                + "<div class='input-field col s6'>\n"
                + "<i class='material-icons prefix'>request_quote</i>\n"
                + "<input id='bonus' type='text'  value=" + String.format("%.2f",isr) + " name='isr' readonly>\n"
                + "<label for='icon_telephone'>ISR</label>\n"
                + "</div>\n"
                + "<div class='input-field col s6'>\n"
                + " <i class='material-icons prefix'>healing</i>\n"
                + "<input id='igss' type='text' value=" + String.format("%.2f",igss) + "  name='igss' readonly>\n"
                + "<label for='igss'>IGSS</label>\n"
                + "</div>\n"
                + "<div class='input-field col s6'>\n"
                + " <i class='material-icons prefix'>money</i>\n"
                + "<input id='montofinal' type='text' value=" + String.format("%.2f", total) + "  name='total' readonly>\n"
                + "<label for='montofinal'>Monto a pagar:</label>\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</body></html>");
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
