package controladores;

import java.io.IOException;
import java.util.List;

import dao.LibroDAO;
import dao.LibroDAOJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Libro;

/**
 * Servlet implementation class LibrosServlet
 */
public class LibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrosServlet() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//envío parametro para listado o nuevo o eliminar...
		String opcion = request.getParameter("opcion");
		if (opcion==null || opcion.equals("listado")) {
			mostrarListado(request,response);//necesito mandarle el request y el response
		}
		}

	private void mostrarListado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroDAO dao = new LibroDAOJDBC();//instancia el librodao para acceder a bd
		List <Libro> lista = dao.getListaLibros();//obtengo listado
		request.setAttribute("listaLibros", lista);
		request.getRequestDispatcher("/libros/listado.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
