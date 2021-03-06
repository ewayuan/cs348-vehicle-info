package org.Info.Vehicle.dao;

import java.util.List;

import org.Info.Vehicle.model.Make;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MakeDaoImpl implements MakeDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Make> getAllMake() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Make>  MakeList = session.createQuery("from Make").list();
		return MakeList;
	}

	public String getMake(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Make m = (Make) session.get(Make.class, id);
		return m.getMakee();
	}

	public Make addMake(Make Make) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Make);
		return Make;
	}

	public void updateMake(Make Make) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(Make);
	}

	public void deleteMake(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Make p = (Make) session.load(Make.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	} 
	
	public int getmid(String makename) {
		if (makename.equals("All")) {
			return 0;
		}
		Session session = this.sessionFactory.getCurrentSession();
		String searchQ = "from Make WHERE make_name = '" + makename +"'";
		List<Make> makeList = session.createQuery(searchQ).list();
		Make topMake;
		try {
			topMake = makeList.get(0);
		} catch(Exception e) {
			topMake = null;
		}
		return topMake.getMid();
	}
}
