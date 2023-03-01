/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Country;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Umman Hasan
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter
{

    private Country getAllCountry(ResultSet rs) throws Exception {
        int countryId = rs.getInt("id");
        String countryName = rs.getString("name");
        String nationality = rs.getString("nationality");
        
        return (new Country(countryId, nationality, countryName));
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country cntry = getAllCountry(rs);
                result.add(cntry);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
