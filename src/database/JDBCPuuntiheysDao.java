
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Puupalikka;

public class JDBCPuuntiheysDao implements PuuntiheysDao {

	@Override
	public List<Puupalikka> getAllItems() {

		List<Puupalikka> puuntiheyslista = new ArrayList<Puupalikka>();

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:/home/jasmin/puuntiheys.sqlite";
			Connection yhteys = DriverManager.getConnection(url);
			String sql = "SELECT * FROM Balsalevy";
			PreparedStatement kysely = yhteys.prepareStatement(sql);
			ResultSet tulokset = kysely.executeQuery();

			while (tulokset.next()) {
				long id = tulokset.getInt("id");
				double tiheys = tulokset.getDouble("tiheys");
				double korkeus = tulokset.getDouble("korkeus");
				double leveys = tulokset.getDouble("leveys");
				double paino = tulokset.getDouble("paino");
				double pituus = tulokset.getDouble("pituus");
				String grain = tulokset.getString("grain");

				Puupalikka puuOlio = new Puupalikka(tiheys, korkeus, leveys, paino, pituus, grain);
				puuOlio.setId(id);
				puuntiheyslista.add(puuOlio);
			}

			kysely.close();
			yhteys.close();
		} catch (Exception e) {
			System.out.println("Jokin meni vikaan, listaus epäonnistui.");
		}
		return puuntiheyslista;
	}

	@Override
	public Puupalikka getItem(long id) {
		return null;
	}

	@Override
	public boolean addItem(Puupalikka newItem) {
		double tiheys = newItem.getTiheys();
		double korkeus = newItem.getKorkeus();
		double leveys = newItem.getLeveys();
		double paino = newItem.getPaino();
		double pituus = newItem.getPituus();
		String grain = newItem.getGrain();

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:/home/jasmin/puuntiheys.sqlite";
			Connection yhteys = DriverManager.getConnection(url);
			String sql = "INSERT INTO Balsalevy (tiheys,korkeus,leveys,paino,pituus,grain) VALUES (?,?,?,?,?,?)";
			PreparedStatement insertKysely = yhteys.prepareStatement(sql);
			insertKysely.setDouble(1, tiheys);
			insertKysely.setDouble(2, korkeus);
			insertKysely.setDouble(3, leveys);
			insertKysely.setDouble(4, paino);
			insertKysely.setDouble(5, pituus);
			insertKysely.setString(6, grain);
			insertKysely.executeUpdate();

			insertKysely.close();
			yhteys.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean removeItem(Puupalikka item) {
		long poistettava = item.getId();
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:/home/jasmin/puuntiheys.sqlite";
			Connection yhteys = DriverManager.getConnection(url);
			String sql = "DELETE FROM Balsalevy WHERE id = (?)";
			PreparedStatement insertKysely = yhteys.prepareStatement(sql);
			insertKysely.setLong(1, poistettava);
			insertKysely.executeUpdate();

			insertKysely.close();
			yhteys.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}