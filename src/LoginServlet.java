
import management.UserManagementSystem;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserManagementSystem userms = new UserManagementSystem();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (userms.isLoginValid(username, password)) {
			
			 HttpSession session=request.getSession();  
		        session.setAttribute("username",username);  
			//request.login(username, password);
			response.sendRedirect("index.html");
		}
		
		else {
			response.sendRedirect("login.html");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
