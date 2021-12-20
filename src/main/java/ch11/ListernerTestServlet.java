package ch11;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListernerTestServlet
 */
@WebServlet("/ListernerTestServlet")
public class ListernerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletContext sc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListernerTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		sc = getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		sc.setAttribute("name", "홍길동");
		HttpSession s  = request.getSession();
		s.setAttribute("ssName", s.getId() + ":세션 속성 저장!");
	}

}
