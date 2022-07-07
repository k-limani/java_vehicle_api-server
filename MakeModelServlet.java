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

/** Servlet implementation class MakeModelServlet */
@WebServlet("/make-and-model")
public class MakeModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Set the response message's MIME type
		response.setContentType("text/html; charset=UTF-8");
		// Allocate a output writer to write response message into the network
		PrintWriter out = response.getWriter();

		// socket
		StockBean sbean = new StockBean();

		String[] carNamesArr;
		carNamesArr = sbean.getcarNamesArr();
		// Write the response message, in an HTML page
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title> Select auto </title></head>");
			out.println(
					"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<body BGCOLOR='#FDF5E6'>");

			// form
			out.println("<form method='GET' action='make-and-model'>");
			out.println("<fieldset>");
			out.println("<legend>Select an automobile: </legend>");
			out.println("<br /> Auto: <select name='auto'>");
			for (int i = 0; i < carNamesArr.length; i++) {
				out.println("<option value='" + carNamesArr[i] + "'>");
				out.println(carNamesArr[i]);
				out.println("</option>");
			}
			out.println("</select>");
			out.println("<input type='submit' value='Submit' />");
			out.println("</fieldset>");
			out.println("</form>");

			out.println("</body></html>");

			String choice = request.getParameter("auto");
			sbean.setCarChoice(choice);

			ServletContext sc = getServletContext();
			sc.setAttribute("sbean", sbean);

			// out.println("<span> choice=" + sbean.getCarChoice() + "</span>"
			// ); //debug

			RequestDispatcher rdObj = null;

			if (choice.equals(null)) {
				// out.println("<span> choice.equals(null)=" + choice +
				// "</span>" ); //debug
				rdObj = request.getRequestDispatcher("make-and-model");
				rdObj.include(request, response);
			} else {
				// out.println("<span> choice=" + choice + "</span>" ); //debug
				request.getSession().setAttribute("choice", choice);
				response.sendRedirect("options");
			}

		} finally {
			out.close(); // Always close the output writer
		}
	}

	// Redirect POST request to GET request.
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}

