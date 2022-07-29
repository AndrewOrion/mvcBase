<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Editorial" %>
<%@ page import="java.util.List" %>  
<%@page import="modelo.Libro"%>  
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>
	
	<div>
		<h2>Edita libro</h2>
	</div>	
		
<%
	List<Editorial> listaEditoriales = (List<Editorial>) request.getAttribute("listaEditoriales");

	Libro l = (Libro)request.getAttribute("libro");//le paso el libro desde el controlador
%>
		
	<div>
	<form action="libros" method="post">
			<input type="hidden" name="opcion" value="editar"/>
	<div>
			<label for="titulo">Título:</label>
    		<input type="text" name="titulo" id="titulo" placeholder="Título del libro..." value="<%=l.getTitulo() %>">
    </div><br>
     <div>
            <label for="isbn">ISBN:</label>
            <input type="text" name="isbn" id="isbn" pattern="[0-9]{13}" placeholder="Introduzca los 13 dígitos..." value="<%=l.getIsbn() %>" readonly>
        </div><br>
        <div>
            <label for="codEditorial">Editorial:</label>
            <select name="codEditorial" id="codEditorial">
                <%for (Editorial e : listaEditoriales) { %>
                <option value="<%=e.getCodEditorial()%>"
                <% if (e.getCodEditorial()==l.getCodEditorial()){
                	%>selected
                	<%}%>>
                <%=e.getNombre() %>
                </option>
               <%
               } %>
            </select>
        </div><br>
        <div>
            <label for="año">Año:</label>
            <input type="number" size="2" name="anio" id="año" min="1900" value="<%=l.getAño() %>">
        </div><br>
        <div>
            <label for="numPags">Número de páginas:</label>
            <input type="text" size="2" name="numPags" id="numPags" value="<%=l.getNumPags() %>">
        </div><br>
        <div>
            <label for="precio">Precio:</label>
            <input type="text" size="2" name="precio" id="precio" value="<%=l.getPrecio() %>">
        </div><br>
        <div>
            <label for="cantidad">Cantidad:</label>
            <input type="number" name="cantidad" id="cantidad" value="1" min="1" value="<%=l.getCantidad() %>">
        </div><br>
        <div>
            <label for="precioCD">Precio CD:</label>
            <input type="text" size="2" name="precioCD" id="precioCD" value="<%=l.getPrecioCD() %>">
        </div><br>
        <div>
            <input class="boton" type="submit" value="Editar">
        </div>
	</form>
	</div>
	
<jsp:include page="../plantillas/pie.jsp"></jsp:include>