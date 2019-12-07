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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Compte;
import models.Partenaire;
import models.Profil;
import models.User;

/**
 *
 * @author SENE
 */
public class CompteDao implements ISystem<Compte> {
private DaoMysql dao;
 private final String SQL_INSERT="INSERT INTO `compte` (`id_compte`, `id_partenaire`, `numero`, `solde`) VALUES (NULL, ?, ?, ?)";
 private final String SQL_INSERT_COMPTE="INSERT INTO `compte` (`id_compte`, `numero`, `solde`) VALUES (NULL, ?, ?) where id_partenaire=?";
 private final String SQL_SELECT_COMPTE_PARTENAIRE="select * from COMPTE where id_partenaire=?";
    @Override
    public int create(Compte obj) {
         int result=0;
      
        try {
            dao.initPS(SQL_INSERT);
            dao.getPstm().setInt(1,obj.getPartenaire().getId() );
            dao.getPstm().setString(2, obj.getNumero());
            dao.getPstm().setDouble(3, obj.getSolde());
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
    }

    @Override
    public int update(Compte obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Compte> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    public int ajouterCompte(Compte obj) {
         int result=0;
      
        try {
            dao.initPS(SQL_INSERT_COMPTE);
            dao.getPstm().setString(1, obj.getNumero());
            dao.getPstm().setDouble(2, obj.getSolde());
            dao.getPstm().setInt(3,obj.getPartenaire().getId() );
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
        
    }
    
   public List<Compte> findByPartenaire() {
        List<Compte> result=null;

            try {
                result=new ArrayList<>();
                
                
                dao.initPS(SQL_SELECT_COMPTE_PARTENAIRE);
                  ResultSet rs=dao.executeSelect();
                  while(rs.next()){
                      Compte p=new Compte();
                      p.setId(rs.getInt("id_partenaire"));
                      p.setNumero(rs.getString("numero"));
                      p.setSolde(rs.getDouble("solde"));
                      result.add(p);
                  }
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }
  
  
    
}
