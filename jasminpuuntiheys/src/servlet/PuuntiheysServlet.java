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

		// tarkistetaan että sessioniin talletettu salasana täsmää
		if (session.getAttribute("salasana") == "31") {

			// puupalikoiden haku tietokannasta ja talletus attribuuttiin
			JDBCPuuntiheysDao dao = new JDBCPuuntiheysDao();
			List<Puupalikka> tuotteet = dao.getAllItems();
			req.setAttribute("tuotteet", tuotteet);

			// inputtien valmistäytöt, jos niissä ei ole ollut mitään
			String korkeustaytto = "";
			String leveystaytto = "";
			String pituustaytto = "";
			String painotaytto = "";
			String graintaytto = "";
			String tiheystaytto = "(Syötä arvot ja paina laske)";

			// inputeihin täytöt, jos asetettu
			if (req.getParameter("korkeus") != null && req.getParameter("korkeus") != "") {
				korkeustaytto = " value=\"" + req.getParameter("korkeus") + "\"";
			}
			if (req.getParameter("leveys") != null && req.getParameter("leveys") != "") {
				leveystaytto = " value=\"" + req.getParameter("leveys") + "\"";
			}
			if (req.getParameter("pituus") != null && req.getParameter("pituus") != "") {
				pituustaytto = " value=\"" + req.getParameter("pituus") + "\"";
			}
			if (req.getParameter("paino") != null && req.getParameter("paino") != "") {
				painotaytto = " value=\"" + req.getParameter("paino") + "\"";
			}
			if (req.getParameter("grain") != null && req.getParameter("grain") != "") {
				graintaytto = " value=\"" + req.getParameter("grain") + "\"";
			}
			if (req.getParameter("tiheys") != null && req.getParameter("tiheys") != "") {
				tiheystaytto = req.getParameter("tiheys") + " kg/m<sup>3</sup>";
			}

			// lähetetään attribuutit eteenpäin
			req.setAttribute("leveystaytto", leveystaytto);
			req.setAttribute("korkeustaytto", korkeustaytto);
			req.setAttribute("pituustaytto", pituustaytto);
			req.setAttribute("painotaytto", painotaytto);
			req.setAttribute("graintaytto", graintaytto);
			req.setAttribute("tiheystaytto", tiheystaytto);
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

		// ohjaus samalle sivulle niin että inputtien tiedot säilyy
		resp.sendRedirect("/jasminpuuntiheys/puuntiheys?tiheys=" + tiheys + "&korkeus=" + korkeus + "&leveys=" + leveys + "&paino="
				+ paino + "&pituus=" + pituus + "&grain=" + grain);

	}

}
