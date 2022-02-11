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

	/**
	 * Start from adding Data, then check subsequent database behaviours,
	 * but remember to drop tables and adding data again before each.
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		//addData(main);
		//deletingLikeDoNotImpactOtherElements(main);
		//deletingPhotoWillDeleteItsLikes(main);
		//deletingAlbumWillDeletePhotos(main);
		//deletingUserWillDeleteAlbumsAndPhotos(main);
		//dropTables(main);
		main.close();
	}

	private static void deletingAlbumWillDeletePhotos(Main main) {
		main.session.getSessionFactory().openSession();
		Transaction transaction = main.session.beginTransaction();
		Album album = main.session.get(Album.class,1);
		main.session.delete(album);
		transaction.commit();
		main.session.close();
	}

	private static void dropTables(Main main) {
		main.session.getSessionFactory().openSession();
		Transaction transaction = main.session.beginTransaction();
		main.session.createSQLQuery("drop table if exists Albums;").executeUpdate();
		main.session.createSQLQuery("drop table if exists Photos;").executeUpdate();
		main.session.createSQLQuery("drop table if exists Users;").executeUpdate();
		main.session.createSQLQuery("drop table if exists UsersLikes;").executeUpdate();
		transaction.commit();
		main.session.close();
	}

	private static void deletingUserWillDeleteAlbumsAndPhotos(Main main) {
		main.session.getSessionFactory().openSession();
		Transaction transaction = main.session.beginTransaction();
		User user = main.session.get(User.class,1);
		main.session.delete(user);
		transaction.commit();
		main.session.close();
	}


	private static void deletingLikeDoNotImpactOtherElements(Main main) {
		main.session.getSessionFactory().openSession();
		Transaction transaction = main.session.beginTransaction();
		User user = main.session.get(User.class, 2);
		Photo photo = main.session.get(Photo.class,1);
		user.unlikePhoto(photo);
		main.session.save(user);
		transaction.commit();
		main.session.close();
	}

	private static void deletingPhotoWillDeleteItsLikes(Main main) {
		main.session.getSessionFactory().openSession();
		Transaction transaction = main.session.beginTransaction();
		Photo photo = main.session.get(Photo.class,1);
		main.session.delete(photo);
		transaction.commit();
		main.session.close();
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
