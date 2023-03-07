/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 *
 * @author SMART
 */
public interface SkillDaoInter
{
    
    public List<Skill> getAllSkill();
    public boolean insertSkill( Skill name);
    
}
