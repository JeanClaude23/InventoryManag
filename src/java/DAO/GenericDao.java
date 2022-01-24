/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Category;
import Util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author JClaude
 */
public class GenericDao<K> {
    Session session =null;
    private final Class<K> type;

    public GenericDao(Class<K> type) {
        this.type = type;
    }
    public Session createSession()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
    public void closeSession(){
        session.getTransaction().commit();
        session.close();
    }
    
    
    public void save(K obj){
        createSession().saveOrUpdate(obj);
        closeSession();
    }
    
    public void update(K obj){
        createSession().update(obj);
        closeSession();
    }
    public void delete(K obj){
        createSession().delete(obj);
        closeSession();
    }
    
    public K  findById(Serializable id){
        K obj = (K) createSession().get(type, id);
        closeSession();
        return obj;
    }

    public List<K>  findAll(){
        List<K> obj = createSession().createCriteria(type.getName()).list();
        closeSession();
        return obj;
    }
    public List<Category> retrieveAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categorylist = new ArrayList();
        categorylist = session.createQuery("from Category").list();
        session.close();
        return categorylist;
    }
    
    public static Long countUsers(String username){
        String hql = "SELECT COUNT(name) FROM Account WHERE name='"+username+"'";
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
}
