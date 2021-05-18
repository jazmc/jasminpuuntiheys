package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/salainen")
public class ProServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// jos kenttään kirjoitettu salasana on oikein, aseta se sessioniin
		if (req.getParameter("salasana").equals("31") || req.getParameter("salasana") == "31") {
			session.setAttribute("salasana", "31");
			// uudelleenohjaus pro-versioon
			resp.sendRedirect("/jasminpuuntiheys/puuntiheys");

		} else {
			// jos salasana ei ollut oikein, palaa perusversioon
			resp.sendRedirect("/jasminpuuntiheys/puun-tiheys");
		}
	}
}
