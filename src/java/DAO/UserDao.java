/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Account;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author JClaude
 */
public class UserDao {
    public static void register(Account account)
    {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        sess.save(account);
        sess.getTransaction().commit();
        sess.close();
    }
    public static Long countUsers(String name){
        String hql = "SELECT COUNT(name) FROM Account WHERE name='"+name+"'";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery(hql);
        Long users = (Long) query.uniqueResult();
        sess.getTransaction().commit();
        sess.close();
        
        return users;
    }
    public static Long countEmails(String email){
        String hql = "SELECT COUNT(email) FROM Account WHERE email='"+email+"'";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery(hql);
        Long emails = (Long) query.uniqueResult();
        sess.getTransaction().commit();
        sess.close();
        
        return emails;
    }
    
    public static Account loginByUsernameAndPassword(String name, String password){
        String hql = "FROM Account WHERE name='"+name+"' AND password='"+password+"'";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery(hql);
        Account singleUser = (Account) query.uniqueResult();
        sess.getTransaction().commit();
        sess.close();
        
        return singleUser;
    }
}
