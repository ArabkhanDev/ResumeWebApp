/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Skill;
import com.mycompany.entity.User;
import com.mycompany.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Umman Hasan
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter
{
   
    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        
        return (new UserSkill(null, new User(userId), new Skill(skillId, skillName) ,power));
    }
    
    
    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*,  "
                    + "	us.skill_id, "
                    + "	s.`name` AS skill_name,  "
                    + "	us.power  "
                    + "FROM "
                    + "	user_skill us "
                    + "	LEFT JOIN `user` u ON us.user_id=u.id "
                    + "	LEFT JOIN skill s ON us.skill_id=s.id "
                    + "WHERE "
                    + " us.user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
