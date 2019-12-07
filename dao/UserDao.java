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
import models.Profil;
import models.User;

/**
 *
 * @author hp
 */
public class UserDao implements ISystem<User>{
 private DaoMysql dao;
 private final String SQL_INSERT="INSERT INTO `user` (`id_user`, `id_profil`, `id_partenaire`, `nom`, `prenom`, `login`, `password`, `etat`) VALUES (NULL, ?, ?, ?, ?, ?, ?, 'Actif')";
 private final String SQL_UPDATE="Update user set etat=? where id_user=? ";  
 private final String SQL_SELECT_ONE="select * from user where id_user=?";
 private final String SQL_SELECT_LOGIN_PWD="select * from user where login=? and password=?";
   public UserDao() {
        dao=  DaoMysql.getInstance();
    }
 
 
    @Override
    public int create(User obj) {
        int result=0;
      
        try {
            dao.initPS(SQL_INSERT);
            dao.getPstm().setInt(1,obj.getProfil().getId() );
            dao.getPstm().setInt(2,0);
            dao.getPstm().setString(3, obj.getNom());
            dao.getPstm().setString(4, obj.getPrenom());
            dao.getPstm().setString(5, obj.getLogin());
            dao.getPstm().setString(6, obj.getPwd());
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
    }

    @Override
    public int update(User obj) {
        int result=0;
      
        try {
            dao.initPS(SQL_UPDATE);
            dao.getPstm().setString(1, obj.getEtat());
            dao.getPstm().setInt(2, obj.getId());
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
    }

    @Override
    public User findById(int id) {
        User result=null;
     try {
         dao.initPS(SQL_SELECT_ONE);
         dao.getPstm().setInt(1, id);
          ResultSet rs=dao.executeSelect();
          if(rs.first()){
            result=new User();
            result.setId(rs.getInt("id_user"));
            result.setNom(rs.getString("nom"));
            result.setLogin(rs.getString("login"));
            result.setPwd(rs.getString("password"));
            result.setPrenom(rs.getString("prenom"));
            result.setEtat(rs.getString("etat"));
            int id_profil=rs.getInt("id_profil");
             Profil profil=new ProfilDao().findById(id_profil);
             result.setProfil(profil);
             
          }  
          
     } catch (SQLException ex) {
         Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
     }
       
       return result; 
    } 

    @Override
    public List<User> findAll() {
        List<User> result=null;
        ProfilDao profildao=new ProfilDao();
        String sql="select * from user";
     try {
         result=new ArrayList<>();
         dao.initPS(sql);
        ResultSet rs= dao.executeSelect();
        while(rs.next()){
            User u =new User();
            u.setId(rs.getInt("id_user"));
            u.setLogin(rs.getString("login"));
            u.setPwd(rs.getString("password"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEtat(rs.getString("etat"));
            Profil p=profildao.findById(rs.getInt("id_profil"));
            u.setProfil(p);
            result.add(u);
        }
     } catch (SQLException ex) {
         Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
     }
        return result;
    }
  public User findByLoginPassword(User user) {
        User result=null;
     try {
         dao.initPS(SQL_SELECT_LOGIN_PWD);
         dao.getPstm().setString(1, user.getLogin());
         dao.getPstm().setString(2, user.getPwd());
          ResultSet rs=dao.executeSelect();
          if(rs.first()){
            result=new User();
            result.setId(rs.getInt("id_user"));
            result.setNom(rs.getString("nom"));
            result.setLogin(rs.getString("login"));
            result.setPwd(rs.getString("password"));
            result.setPrenom(rs.getString("prenom"));
            result.setEtat(rs.getString("etat"));
            int id_profil=rs.getInt("id_profil");
             Profil profil=new ProfilDao().findById(id_profil);
             result.setProfil(profil);
             
          }  
          
     } catch (SQLException ex) {
         Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
     }
       
       return result; 
    }   
}
