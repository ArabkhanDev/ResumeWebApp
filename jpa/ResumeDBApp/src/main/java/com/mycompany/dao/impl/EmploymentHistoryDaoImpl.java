/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.entity.EmploymentHistory;
import com.mycompany.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Umman Hasan
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter
{

    private EmploymentHistory getAllEmploymentHistory(ResultSet rs) throws Exception {

        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");
        
        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;

    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryById(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM employment_history WHERE user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory eh = getAllEmploymentHistory(rs);
                result.add(eh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
