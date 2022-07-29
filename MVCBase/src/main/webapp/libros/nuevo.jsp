<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Editorial" %>
<%@ page import="java.util.List" %>    
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>
	
	<div>
		<h2>Inserta nuevo libro</h2>
	</div>	
		
<%
	List<Editorial> listaEditoriales = (List<Editorial>) request.getAttribute("listaEditoriales");
%>
		
	<div>
	<form action="libros" method="post">
			<input type="hidden" name="opcion" value="insertar"/>
	<div>
			<label for="titulo">Título:</label>
    		<input type="text" name="titulo" id="titulo" placeholder="Título del libro..." required>
    </div><br>
     <div>
            <label for="isbn">ISBN:</label>
            <input type="text" name="isbn" id="isbn" pattern="[0-9]{13}" placeholder="Introduzca los 13 dígitos..." required>
        </div><br>
        <div>
            <label for="codEditorial">Editorial:</label>
            <select name="codEditorial" id="codEditorial" required>
                <%for (Editorial e: listaEditoriales) { %>
                <option value="<%=e.getCodEditorial()%>"><%=e.getNombre() %></option>
               <%} %>
            </select>
        </div><br>
        <div>
            <label for="año">Año:</label>
            <input type="number" size="2" name="anio" id="año" min="1900" value="2022">
        </div><br>
        <div>
            <label for="numPags">Número de páginas:</label>
            <input type="text" size="2" name="numPags" id="numPags">
        </div><br>
        <div>
            <label for="precio">Precio:</label>
            <input type="text" size="2" name="precio" id="precio">
        </div><br>
        <div>
            <label for="cantidad">Cantidad:</label>
            <input type="number" name="cantidad" id="cantidad" value="1" min="1">
        </div><br>
        <div>
            <label for="precioCD">Precio CD:</label>
            <input type="text" size="2" name="precioCD" id="precioCD">
        </div><br>
        <div>
            <input class="boton" type="submit" value="Insertar">
        </div>
	</form>
	</div>
	
<jsp:include page="../plantillas/pie.jsp"></jsp:include>