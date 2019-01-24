/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author yrelique
 */
@WebServlet(name = "ShowClientForStates", urlPatterns = {"/ShowClientForStates"})
public class ShowClientForStates extends HttpServlet {

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
            out.println("<title>Servlet ShowClientForStates</title>");            
            out.println("</head>");
            out.println("<body>");
            try {	// Trouver la valeur du paramètre HTTP State
				String val = request.getParameter("state");
				if (val == null) {
					throw new Exception("La paramètre state n'a pas été transmis");
				}
				// on doit convertir cette valeur en entier (attention aux exceptions !)
				//int customerID = Integer.valueOf(val);

				DAO dao = new DAO(DataSourceFactory.getDataSource());
				List<CustomerEntity> customer = dao.customersInState(val);
				if (customer.isEmpty()) {
					throw new Exception("Etat inconnu");
				}
                                
                                out.println("<table border=2>\n");
                                out.println("<tr>\n");
                                out.println("<th>\n");
                                out.println("ID");
                                out.println("</th>\n");
                                out.println("<th>\n");
                                out.println("Nom");
                                out.println("</th>\n");
                                out.println("<th>\n");
                                out.println("Adresse");
                                out.println("</th>\n");
                                out.println("</tr>\n");
                                
                                for(int i=0;i<customer.size();i++){
                                    out.println("<tr>\n");
                                    out.println("<td>\n");
                                    out.println(customer.get(i).getCustomerId()+"\n");
                                    out.println("</td>\n");
                                    out.println("<td>\n");
                                    out.println(customer.get(i).getName()+"\n");
                                    out.println("</td>\n");
                                    out.println("<td>\n");
                                    out.println(customer.get(i).getAddressLine1()+"\n");
                                    out.println("</td>\n");
                                    out.println("</tr>\n");
                                }
                                
                                out.println("</table>");
				// Afficher les propriétés du client			
				
			} catch (Exception e) {
				out.printf("Erreur : %s", e.getMessage());
			}
			out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
