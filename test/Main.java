/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import models.Compte;
import models.Partenaire;
import models.Profil;
import models.User;
import services.SystemService;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SystemService metier =new SystemService();
       //Creer Profil
       Profil p=new Profil(3,"Partenaires");
      // Compte c=new Compte(1,"A1254",124584);
         User u =metier.seConnecter("login2", "pwd");
         if(u!=null && u.getEtat().compareTo("Actif")==0){
            System.out.println("User Connecter"); 
         }else{
            System.out.println("User bloqué"); 
         }
       /*if(metier.creerCaissier(u)){
           System.out.println("User Creer");
       }else{
            System.out.println("Erreur");
       }*/
       metier.listerCaissier().forEach(
               (user) -> System.out.println(user.getNom() + ""+ user.getProfil().getLibelle())
       );
    }
    
}
