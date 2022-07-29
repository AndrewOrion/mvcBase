package controladores;

import java.io.IOException;
import java.util.List;

import dao.EditorialDAO;
import dao.EditorialDAOJDBC;
import dao.LibroDAO;
import dao.LibroDAOJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Editorial;
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
		} else if (opcion.equals("nuevo")) {
			mostrarFormulario(request,response);
		} else if (opcion.equals("eliminar")) {
			eliminar(request,response);
		}else if (opcion.equals("editar")) {
			editar(request,response);
		}
		
		}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn"); 	
		LibroDAO dao = new LibroDAOJDBC();
		Libro l = dao.getLibro(isbn);
		request.setAttribute("libro",l);
		EditorialDAO dao2 = new EditorialDAOJDBC();
		List<Editorial> lista = dao2.getListaEditoriales();
		request.setAttribute("listaEditoriales", lista);
		
		
		request.getRequestDispatcher("/libros/editar.jsp").forward(request, response);
		mostrarListado(request,response); //muestro la lista de nuevo	
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn"); 	
		LibroDAO dao = new LibroDAOJDBC();
		dao.eliminarLibro(isbn);
		mostrarListado(request,response); //muestro la lista de nuevo
		
	}

	private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditorialDAO dao = new EditorialDAOJDBC();
		List<Editorial> lista = dao.getListaEditoriales();
		request.setAttribute("listaEditoriales", lista);

		request.getRequestDispatcher("/libros/nuevo.jsp").forward(request,response);
		
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
		String opcion = request.getParameter("opcion");
		if (opcion!=null || opcion.equals("insertar")) {
			insertarLibro(request,response);
		} 
	}

	private void insertarLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn"); //recogiendo datos del formulario y metiendolos en variables
		String titulo = request.getParameter("titulo");
		int codEditorial = Integer.parseInt(request.getParameter("codEditorial"));
		int año = Integer.parseInt(request.getParameter("anio"));
		int numPags = Integer.parseInt(request.getParameter("numPags"));
		float precio = Float.parseFloat(request.getParameter("precio"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		int precioCD = Integer.parseInt(request.getParameter("precioCD"));
	
		Libro l = new Libro(isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);//instancio libro
		LibroDAO dao = new LibroDAOJDBC();//instancio el controlador para hacer conexion
		//System.out.println("Libro:"+l);
		dao.insertarLibro(l); //llamo a la funcion insertar pasandole el libro que he introducido
		
		mostrarListado(request,response); //muestro la lista de nuevo
	}

}
