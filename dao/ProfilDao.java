/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Profil;

/**
 *
 * @author hp
 */
public class ProfilDao implements ISystem<Profil> {
    
    private DaoMysql dao;
   private final String SQL_INSERT="INSERT INTO `profil` (`id_profil`, `libelle`) VALUES (NULL, ?)";
   private final  String SQL_UPDATE="UPDATE `profil` SET `libelle` = ? WHERE `profil`.`id_profil` =?";         
   private final String SQL_SELECT_ONE="select * from profil where id_profil=?";
   private final String SQL_SELECT_ALL="select * from profil";
   
    private final String SQL_SELECT_LIBELLE="select * from profil where libelle=?";
   public ProfilDao() {
        dao=  DaoMysql.getInstance();
    }

    @Override
    public int create(Profil obj) {
        int result=0;
        
       
            try {
                  dao.initPS(SQL_INSERT);
                  //Injecter les Valeurs dans la requete
                  //ORM Objet vers Table
                  dao.getPstm().setString(1, obj.getLibelle());
                 result= dao.executeMaj();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }

    @Override
    public int update(Profil obj) {
       int result=0;
            try {
                
               
             
                /* Execution requete */
                  //PreparedStatement ps=connexion.prepareStatement(sql);
                  dao.initPS(SQL_UPDATE);
                  //ps.setString(1, obj.getLibelle());
                  dao.getPstm().setString(1, obj.getLibelle());
                  dao.getPstm().setInt(2, obj.getId());
                  result=dao.executeMaj();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result; 
    }

    @Override
    public Profil findById(int id) {
       Profil result=null;
            try {
                result=new Profil();
                
                dao.initPS(SQL_SELECT_ONE);
                /* Execution requete */
                dao.getPstm().setInt(1, id);
                 //ORM table vers Objet
                  ResultSet rs=dao.executeSelect();
                  if(rs.first()){  
                     result.setId(rs.getInt("id_profil"));
                     result.setLibelle(rs.getString("libelle"));
                      
                  }
                  
                  dao.CloseConnection();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }

    @Override
    public List<Profil> findAll() {
       List<Profil> result=null;

            try {
                result=new ArrayList<>();
                
                
                dao.initPS(SQL_SELECT_ALL);
                
                  ResultSet rs=dao.executeSelect();
                  while(rs.next()){
                      Profil p=new Profil();
                      p.setId(rs.getInt("id_profil"));
                      p.setLibelle(rs.getString("libelle"));
                      result.add(p);
                  }
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }
    public Profil findByLibelle(String libelle) {
       Profil result=null;
       
       
            try {
                result=new Profil();
                
                dao.initPS(SQL_SELECT_LIBELLE);
                /* Execution requete */
                dao.getPstm().setString(1, libelle);

                  ResultSet rs=dao.executeSelect();
                  if(rs.next()){
                      
                     result.setId(rs.getInt("id_profil"));
                     result.setLibelle(rs.getString("libelle"));
                      
                  }
                  
                  dao.CloseConnection();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }
}
