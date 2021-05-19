
package servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.JDBCPuuntiheysDao;
import model.Puupalikka;

@WebServlet("/puuntiheys")
public class PuuntiheysServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println("PuuntiheysServlet get");
		// tarkistetaan että sessioniin talletettu salasana täsmää
		if (session.getAttribute("salasana") == "31") {

			// puupalikoiden haku tietokannasta ja talletus attribuuttiin
			JDBCPuuntiheysDao dao = new JDBCPuuntiheysDao();
			List<Puupalikka> tuotteet = dao.getAllItems();
			req.setAttribute("tuotteet", tuotteet);

			req.getRequestDispatcher("/WEB-INF/puuntiheys.jsp").forward(req, resp);

		} else {
			// jos salasana ei täsmää, palataan perusversioon
			resp.sendRedirect("/jasminpuuntiheys/puun-tiheys");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// tietokantayhteyden alustus ja ääkköstuki
		req.setCharacterEncoding("UTF-8");
		JDBCPuuntiheysDao dao = new JDBCPuuntiheysDao();

		// stringit doubleiksi ja desimaalierotin pisteeksi
		double korkeus = Puupalikka.muotoileDouble(req.getParameter("korkeus"));
		double leveys = Puupalikka.muotoileDouble(req.getParameter("leveys"));
		double paino = Puupalikka.muotoileDouble(req.getParameter("paino"));
		double pituus = Puupalikka.muotoileDouble(req.getParameter("pituus"));

		// tiheyden formatointi kahdelle merkitsevälle desimaalille
		DecimalFormat kaksDesimaalia = new DecimalFormat("#.##");
		kaksDesimaalia.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		double tiheys = Double.parseDouble(
				kaksDesimaalia.format((paino / 1000) / ((korkeus / 1000) * (leveys / 1000) * (pituus / 1000))));

		// grainin stringi
		String grain = req.getParameter("grain");

		// palikan lisäys tietokantaan
		Puupalikka tuote = new Puupalikka(tiheys, korkeus, leveys, paino, pituus, grain);
		dao.addItem(tuote);

		// haetaan taas tuotteet
		List<Puupalikka> tuotteet = dao.getAllItems();
		req.setAttribute("tuotteet", tuotteet);
		req.setAttribute("tiheys", tiheys);

		req.getRequestDispatcher("/WEB-INF/puuntiheys.jsp").forward(req, resp);

	}

}