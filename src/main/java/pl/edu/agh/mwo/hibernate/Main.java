package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();



		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
