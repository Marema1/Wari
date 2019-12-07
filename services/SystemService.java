/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CompteDao;
import dao.PartenaireDao;
import dao.ProfilDao;
import dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;
import models.Compte;
import models.Partenaire;
import models.Profil;
import models.User;

/**
 *
 * @author hp
 */
public class SystemService {

    ProfilDao daoprofil =new ProfilDao();
    UserDao daouser =new UserDao();
    PartenaireDao daopartenaire =new PartenaireDao();
    CompteDao daocompte =new CompteDao();
    public boolean creerProfil(Profil profil){
        
        return daoprofil.create(profil)==0?false:true;
    }
    
    public List<Profil> listerProfil(){
        return daoprofil.findAll();
    }
    
    public boolean modifierProfil(Profil p){
        return daoprofil.update(p)==0?false:true;
    }
    public boolean creerCaissier(User u){
        Profil p=daoprofil.findByLibelle("Caissier");
        u.setProfil(p);
          return daouser.create(u)==0?false:true;
    }
    
    public List<User> listerCaissier(){

     return  daouser.findAll().stream()
                        .filter(user-> user.getProfil().getLibelle().compareToIgnoreCase("Caissier")==0)
                        .collect(Collectors.toList()); 
    }
   
    public User seConnecter(String login,String pwd){
        User user=new User(login,pwd);
       return daouser.findByLoginPassword(user);
    }
    public boolean bloquerUser(int id,String etat){
        User obj=new User(id, etat);
        return daouser.update(obj)==0;
        
    }
    
     public boolean creerCompte(Compte compte){
        
        return daocompte.create(compte)==0?false:true;
    }
     
    public boolean addCompte(Compte compte){
        
        return daocompte.ajouterCompte(compte)==0?false:true;
    }
    
     public List<Partenaire> listerPartenaire(){
        return daopartenaire.findAll();
    }
     
    public List<Compte> listerComptePartenaire(){
        return daocompte.findByPartenaire();
    }
    
    
}
