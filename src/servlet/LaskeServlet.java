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

@WebServlet("/laske")
public class LaskeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// merkistön tarkistus
		req.setCharacterEncoding("UTF-8");

		// stringit doubleiksi ja desimaalierotin pisteeksi
		double korkeuss = Puupalikka.muotoileDouble(req.getParameter("korkeus"));
		double leveyss = Puupalikka.muotoileDouble(req.getParameter("leveys"));
		double painos = Puupalikka.muotoileDouble(req.getParameter("paino"));
		double pituuss = Puupalikka.muotoileDouble(req.getParameter("pituus"));
		
		// grainin stringi
		String grain = req.getParameter("grain");

		// tiheyden lasku ja formatointi kahdelle merkitsevälle desimaalille
		DecimalFormat kaksDesimaalia = new DecimalFormat("#.##");
		kaksDesimaalia.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		double tiheys = Double.parseDouble(
				kaksDesimaalia.format((painos / 1000) / ((korkeuss / 1000) * (leveyss / 1000) * (pituuss / 1000))));
		String jenkki = kaksDesimaalia.format(tiheys * 0.0624279606);
		req.setAttribute("tiheystaytto", tiheys+" kg/m<sup>3</sup>");
		req.setAttribute("korkeustaytto", korkeuss);
		req.setAttribute("leveystaytto", leveyss);
		req.setAttribute("painotaytto", painos);
		req.setAttribute("pituustaytto", pituuss);
		req.setAttribute("jenkki", "(= "+jenkki+" lb/ft<sup>3</sup>)");
		// palataan sivulle niin että inputeissa olleet tiedot säilyy
		req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
	}

}
