package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.mwo.hibernate.models.Album;
import pl.edu.agh.mwo.hibernate.models.Photo;
import pl.edu.agh.mwo.hibernate.models.User;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		addData(main);
		main.close();
	}

	private static void addData(Main main) {
		main.session.getSessionFactory().openSession();
		User u1 = new User("Stefano", Date.valueOf(LocalDate.now()));
		User u2 = new User("Sophia", Date.valueOf(LocalDate.now()));
		Photo p1 = new Photo("Selfie",Date.valueOf(LocalDate.now()));
		Album a1 = new Album("Summer of '69", "I got my first real six string.");

		a1.addPhoto(p1);
		u1.addAlbum(a1);
		u1.likePhoto(p1);
		u2.likePhoto(p1);
		Transaction transaction = main.session.beginTransaction();
		main.session.save(u1);
		main.session.save(u2);
		main.session.save(p1);
		main.session.save(a1);
		transaction.commit();

		main.session.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
