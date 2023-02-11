/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.dao.inter.UserSkillDaoInter;
import com.mycompany.entity.Skill;

/**
 * @author Umman Hasan
 */
public class Main
{

    public static void main(String[] args) throws Exception {
// thightly coupling
//      UserDaoInter userDao = new UserDaoImpl();

// loosely coupling
        UserDaoInter userDao = Context.instanceUserDao();
    
        System.out.println(userDao.removeUser(7));
//
//        System.out.println("--------------------------------------------");
//
//        UserSkillDaoInter userSkillDao = Context.instanceUserSkillDao();
//        System.out.println(userSkillDao.getAllSkillByUserId(1));
//
//        System.out.println("--------------------------------------------");
//
//        EmploymentHistoryDaoInter employmentHistoryDao = Context.instanceEmploymentHistoryDao();
//        System.out.println(employmentHistoryDao.getAllEmploymentHistoryById(1));
//
//        System.out.println("--------------------------------------------");
//
//        SkillDaoInter skillDao = Context.instanceSkillDao();
//        System.out.println(skillDao.getAllSkill());
//
//        System.out.println("--------------------------------------------");
//
//        CountryDaoInter countryDao = Context.instanceCountryDao();
//        System.out.println(countryDao.getAllCountry());

    }
}


/*

JDBC (Java Database Connectivity) provides several interfaces for connecting to
and interacting with databases. These include:

DriverManager: This is a class that manages a list of database drivers.
Applications can use the DriverManager class to load a specific JDBC driver and
establish a connection to a database.

Connection: This interface represents a connection to a database. It provides 
methods for creating and executing SQL statements, as well as managing transactions.

Statement: This interface is used to execute SQL statements. It has several 
sub-interfaces, including PreparedStatement and CallableStatement, which are 
used for executing pre-compiled and stored procedures, respectively.

ResultSet: This interface represents a result set returned by a database query.
It provides methods for accessing and manipulating the data returned by a query.

SQLException: This class represents an exception that occurs during a database
operation. It provides detailed information about the error, including the error 
code and SQL state.


 */
