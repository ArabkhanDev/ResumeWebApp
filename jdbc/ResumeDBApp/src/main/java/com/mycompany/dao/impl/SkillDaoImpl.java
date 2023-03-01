/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Skill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.SkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Umman Hasan
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int skillId = rs.getInt("id");
        String skillName = rs.getString("name");

        return (new Skill(skillId, skillName));
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill s = getSkill(rs);
                result.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertSkill(Skill s) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO skill (name) VALUES(?)");
            stmt.setString(1, s.getName());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
