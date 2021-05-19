package servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Puupalikka;

@WebServlet("/puun-tiheys")
public class PerusServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jos ei olla painettu pro-version nappia eli salasana-parametri on tyhjä
		if (req.getParameter("salasana") == "" || req.getParameter("salasana") == null) {

			// oletustäytöt inputeille
			String korkeustaytto = "";
			String leveystaytto = "";
			String pituustaytto = "";
			String painotaytto = "";
			String tiheystaytto = "(Syötä arvot ja paina laske)";

			// asetetaan inputien täytöt, jos sellaiset on ollut requestissa
			if (req.getParameter("korkeus") != null && req.getParameter("korkeus") != "") {
				korkeustaytto = req.getParameter("korkeus");
			}
			if (req.getParameter("leveys") != null && req.getParameter("leveys") != "") {
				leveystaytto = req.getParameter("leveys");
			}
			if (req.getParameter("pituus") != null && req.getParameter("pituus") != "") {
				pituustaytto = req.getParameter("pituus");
			}
			if (req.getParameter("paino") != null && req.getParameter("paino") != "") {
				painotaytto = req.getParameter("paino");
			}
			if (req.getParameter("tiheys") != null && req.getParameter("tiheys") != "") {
				tiheystaytto = req.getParameter("tiheys") + " kg/m<sup>3</sup>";
			}

			// lähetetään täytöt eteenpäin
			req.setAttribute("leveystaytto", leveystaytto);
			req.setAttribute("korkeustaytto", korkeustaytto);
			req.setAttribute("pituustaytto", pituustaytto);
			req.setAttribute("painotaytto", painotaytto);
			req.setAttribute("tiheystaytto", tiheystaytto);
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// merkistön tarkistus
		req.setCharacterEncoding("UTF-8");

		// stringit doubleiksi ja desimaalierotin pisteeksi
		double korkeus = Puupalikka.muotoileDouble(req.getParameter("korkeus"));
		double leveys = Puupalikka.muotoileDouble(req.getParameter("leveys"));
		double paino = Puupalikka.muotoileDouble(req.getParameter("paino"));
		double pituus = Puupalikka.muotoileDouble(req.getParameter("pituus"));

		// tiheyden lasku ja formatointi kahdelle merkitsevälle desimaalille
		DecimalFormat kaksDesimaalia = new DecimalFormat("#.##");
		kaksDesimaalia.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		double tiheys = Double.parseDouble(
				kaksDesimaalia.format((paino / 1000) / ((korkeus / 1000) * (leveys / 1000) * (pituus / 1000))));

		// palataan sivulle niin että inputeissa olleet tiedot säilyy
		resp.sendRedirect("/jasminpuuntiheys/puun-tiheys?tiheys=" + tiheys + "&korkeus=" + korkeus + "&leveys=" + leveys + "&paino="
				+ paino + "&pituus=" + pituus);

	}

}