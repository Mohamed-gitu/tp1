package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzee.tp.beans.*;
import org.joda.time.DateTime;
import org.joda.time.format.*;

/**
 * Servlet implementation class creationCommande
 */
@WebServlet(description = "Validation commande", urlPatterns = { "/creationCommande" })
public class creationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String message;
		String url = "/afficherCommande.jsp";
		DateTime dt = new DateTime();
		String pattern = "dd/MM/yyyy HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		String date = dt.toString(formatter);
		String tempMontant ;
		
		
		String nom = (request.getParameter("nomClient") == null? "" : request.getParameter("nomClient")) ;
		String prenom = (request.getParameter("prenomClient") == null? "": request.getParameter("prenomClient"));
		String telephone = (request.getParameter("telephoneClient") == null? "": request.getParameter("telephoneClient"));
		String adresse = (request.getParameter("adresseClient") == null? "": request.getParameter("adresseClient") );
		String email = (request.getParameter("emailClient") == null ? "": request.getParameter("emailClient")  );
		//Double montant =  Double.parseDouble("-1") ;
		tempMontant  = request.getParameter("montantCommande") ;
		Double montant = Double.parseDouble( ( tempMontant == null || tempMontant.trim().isEmpty()  ? "-1": tempMontant  ));
		String modePaiement = (request.getParameter("modePaiementCommande") == null ? "": request.getParameter("modePaiementCommande")  );
		String statutPaiement = (request.getParameter("statutPaiementCommande") == null ? "": request.getParameter("statutPaiementCommande")  );
		String modeLivraison = (request.getParameter("modeLivraisonCommande") == null ? "": request.getParameter("modeLivraisonCommande")  );
		String statutLivraison = (request.getParameter("statutLivraisonCommande") == null ? "": request.getParameter("statutLivraisonCommande")  );
		
		
		
		
		if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty()  
				|| montant == -1 || modePaiement.trim().isEmpty() || modeLivraison.trim().isEmpty()	) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'une commande.";
        } else {
            message = "Commande créée avec succès !";
        }
		
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setAdresse(adresse);
		client.setEmail(email);
		
		Commande commande = new Commande();
		commande.setClient(client);
		commande.setMontant(montant);
		commande.setModePaiement(modePaiement);
		commande.setModeLivraison(modeLivraison);
		commande.setStatutPaiement(statutPaiement);
		commande.setStatutLivraison(statutLivraison);
		commande.setDate(date);
		
		
		 /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( "commande", commande );
        request.setAttribute( "message", message );
        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( url ).forward( request, response );
        
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
