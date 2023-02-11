/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Umman Hasan
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Date birthdate = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");

        Country nationality = new Country(nationalityId, nationalityStr, null);
        Country birthplace = new Country(birthplaceId, null, birthplaceStr);

        return (new User(id, name, surname, phone, email, birthdate, birthplace, nationality));
    }
    
    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Date birthdate = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
     

        User user =  new User(id, name, surname, phone, email , birthdate, null, null);
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "SELECT "
                    + "u. * , "
                    + "c.`name` AS birthplace, "
                    + "n.nationality  "
                    + "FROM  user u  "
                    + "LEFT JOIN country n ON u.nationality_id = n.id "
                    + "LEFT JOIN country c ON u.birthplace_id = c.id WHERE 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += " AND u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                sql += " AND u.surname=? ";
            }
            if (nationalityId != null) {
                sql += " AND u.nationality_id=? ";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
// name, surname ve nationalityId ucun indexlesdiririk
            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }
            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByUserEmailAndPassword(String email, String password) {

        User result = null;        
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM user WHERE email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               result = getUserSimple(rs);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public User findByUserEmail(String email) {

        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM user WHERE email=?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();
//            return stmt.execute("UPDATE user SET name='Eldar' WHERE id=2");
            // for security and bypass inject => character encoding
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {

        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("DELETE FROM user WHERE id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT "
                    + "       u. * , "
                    + "       c.`name` AS birthplace, "
                    + "       n.nationality  "
                    + "FROM  user u  "
                    + "LEFT JOIN country n ON u.nationality_id = n.id "
                    + "LEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    private BCrypt.Hasher crypt = BCrypt.withDefaults();
    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("INSERT INTO user (name, surname, phone, email, password, profile_description) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDesc());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
