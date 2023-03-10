/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.main;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.EmploymentHistoryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

/**
 * @author Umman Hasan
 */
public class Context
{

    public static UserDaoInter instanceUserDao() {

        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {

        return new UserSkillDaoImpl();
    }
    
    public static SkillDaoInter instanceSkillDao() {

        return new SkillDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {

        return new CountryDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

}
