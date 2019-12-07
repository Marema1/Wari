/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Partenaire;
import models.Profil;

/**
 *
 * @author hp
 */
public class PartenaireDao implements ISystem<Partenaire>{
   private DaoMysql dao;
   private final String SQL_INSERT="INSERT INTO `partenaire` (`id_partenaire`, `nom`,`prenom`,`login`,`pwd`,`email`, `adresse`, `telephone`) VALUES (NULL,?,?,?,?,?,?,?)";
   private final String SQL_SELECT_ALL="select * from partenaire";
   @Override
    public int create(Partenaire obj) {
         int result=0;
        
       
            try {
                  dao.initPS(SQL_INSERT);
                  //Injecter les Valeurs dans la requete
                  //ORM Objet vers Table
                  dao.getPstm().setString(1, obj.getNom());
                  dao.getPstm().setString(2, obj.getPrenom());
                  dao.getPstm().setString(3, obj.getLogin());
                  dao.getPstm().setString(4, obj.getPwd());
                  dao.getPstm().setString(5, obj.getEmail());
                  dao.getPstm().setString(6, obj.getAdresse());
                  dao.getPstm().setString(7, obj.getTelephone());
                 result= dao.executeMaj();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }

    @Override
    public int update(Partenaire obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Partenaire findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Partenaire> findAll() {
        List<Partenaire> result=null;

            try {
                result=new ArrayList<>();
                
                
                dao.initPS(SQL_SELECT_ALL);
                
                  ResultSet rs=dao.executeSelect();
                  while(rs.next()){
                      Partenaire p=new Partenaire();
                      p.setId(rs.getInt("id_partenaire"));
                      p.setNom(rs.getString("nom"));
                      p.setPrenom(rs.getString("prenom"));
                      p.setLogin(rs.getString("id_login"));
                      p.setEmail(rs.getString("email"));
                      p.setAdresse(rs.getString("adresse"));
                      p.setTelephone(rs.getString("telephone"));
                      result.add(p);
                  }
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }
    
}
