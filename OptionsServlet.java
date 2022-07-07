package forms;

import adapter.StockBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Automobile;

/** Servlet implementation class OptionsServlet */
@WebServlet("/options")
public class OptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Set the response message's MIME type
		response.setContentType("text/html; charset=UTF-8");

		// Post Parameters From The Request
		String choice = (String) request.getSession().getAttribute("choice");

		// sbean
		ServletContext sc = getServletContext();
		StockBean sbean = (StockBean) sc.getAttribute("sbean");

		// Allocate output writer to write response msg into the network socket
		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title> Select options </title></head>");
			out.println(
					"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<body BGCOLOR='#FDF5E6'>");

			Automobile aa = sbean.getInvmap().get(choice);
			String[] optionsets = aa.getAllOptionSetNames();

			out.println("<form method='GET' action='options'>");
			int optionSetCount = 0;
			for (String s : optionsets) {
				out.println("<fieldset>");
				out.println("<legend> " + s + " </legend>");
				out.println(
						"<br /> Select an option:  <select name='" + s + "'>");
				for (int i = 0; i < aa.getAllOptionNames(s).length; i++) {
					out.println("<option value='"
							+ aa.getOptionName(optionSetCount, i) + "'>");
					out.println(aa.getOptionName(optionSetCount, i));
					out.println("</option>");
				}
				out.println("</select>");
				out.println("</fieldset><br>");
				optionSetCount++;
			}
			out.println("<input type='submit' value='Submit' />");
			out.println("</form><br>");

			out.println("</body></html>");

			boolean cont = false;
			String[] choices = new String[optionsets.length];
			for (int i = 0; i < choices.length; i++)
				choices[i] = request.getParameter(optionsets[i]);
			// String choice1 = request.getParameter(optionsets[0]);
			// sbean.getInvmap().get(sbean.getCarChoice()).addOptionChoice(optionsets[0],
			// choice1);
			for (int i = 0; i < choices.length; i++) {
				// out.println("<h2> " + choices[i] + " </h2>");
				aa.addOption(optionsets[i], choices[i], optionsets[i]);
				if (!choices[i].equals(null))
					cont = true;
			}

			sbean.getInvmap().put(aa.getKey(), aa);

			// redirect
			RequestDispatcher rdObj = null;

			if (!cont) {
				// out.println("<span> choice.equals(null)=" + choice +
				// "</span>"); // debug
				rdObj = request.getRequestDispatcher("options");
				rdObj.include(request, response);
			} else {
				// out.println("<span> choices.toString()=" + choices.toString()
				// + "</span>"); // debug
				request.getSession().setAttribute("sbean", sbean);
				response.sendRedirect("AutoInventory.jsp");
			}

		} finally {
			out.close(); // Always close the output writer
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

