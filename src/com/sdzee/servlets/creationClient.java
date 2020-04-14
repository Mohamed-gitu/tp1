package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzee.tp.beans.*;




/**
 * Servlet implementation class creationClient
 */
@WebServlet(description = "Controleur du formulaire creerClient", urlPatterns = { "/creationClient" })
public class creationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		String message;
		String url = "/afficherClient.jsp";
		
		String ParamNom = (request.getParameter("nomClient") == null? "x": request.getParameter("nomClient")) ;
		String prenom = (request.getParameter("prenomClient") == null? "": request.getParameter("prenomClient"));
		String telephone = (request.getParameter("telephoneClient") == null? "": request.getParameter("telephoneClient"));
		String adresse = (request.getParameter("adresseClient") == null? "": request.getParameter("adresseClient") );
		String email = (request.getParameter("emailClient") == null ? "": request.getParameter("emailClient")  );
		
		

		if ( ParamNom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
        } else {
            message = "Client créé avec succès !";
        }
		
		Client client = new Client();		
		client.setNom(ParamNom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setAdresse(adresse);
		client.setEmail(email);
		
		request.setAttribute("message", message);
		request.setAttribute("client", client);
		
		/*
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");

		Enumeration<String> en=request.getParameterNames();

		while(en.hasMoreElements())
		{
			Object objOri=en.nextElement();

			String param=(String)objOri;

			String value=request.getParameter(param);

			pw.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");

		}		
			pw.close();	
	
		*/
			request.getServletContext().getRequestDispatcher(url).forward(request, response);
							
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
