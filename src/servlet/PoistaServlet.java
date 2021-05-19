package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCPuuntiheysDao;
import model.Puupalikka;

@WebServlet("/poistapalikka")
public class PoistaServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// parsea id parametrinä saadusta stringistä
		long id = Long.parseLong(req.getParameter("id"));

		// luodaan tietokantayhteys ja feikkiolio id:lle
		JDBCPuuntiheysDao dao = new JDBCPuuntiheysDao();
		Puupalikka tuote = new Puupalikka(id, 0, 0, 0, 0, 0, "feikki");

		// poistetaan feikkiolion id:tä vastaava puupalikka tietokannasta
		dao.removeItem(tuote);
	}

}