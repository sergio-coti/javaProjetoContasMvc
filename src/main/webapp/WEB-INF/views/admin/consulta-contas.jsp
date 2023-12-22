<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Consulta de Contas</title>
	
	<!-- Folha de estilos do Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
        
    <!-- Folha de estilos do Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>
    
   	<!-- Folha de estilos CSS do projeto -->
    <link rel="stylesheet" href="../resources/style.css"/>
    
    <!-- Folha de estilos CSS do JQuery DataTables -->
    <link rel="stylesheet" href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css"/>
    
</head>
<body>

	<jsp:include page="/WEB-INF/views/admin/components/navbar.jsp"></jsp:include>

	<div class="container mt-3">
		
		<h4>Consulta de Contas</h4>
		<p>Informe o período desejado para consultar suas contas.</p>
		<hr/>
		
		<form id="formConsulta" action="consultar-contas" method="post">
		
			<div class="row mb-2">
				<div class="col-md-2">
					<label><i class="bi bi-calendar-date"></i> Data de início:</label>
					<input type="date" name="dataMin" class="form-control" value="${dtMin}"/>
				</div>
				<div class="col-md-2">
					<label><i class="bi bi-calendar-date"></i> Data de fim:</label>
					<input type="date" name="dataMax" class="form-control" value="${dtMax}"/>
				</div>
				<div class="col-md-8">
					<button type="submit" class="btn btn-success mt-4">
						<i class="bi bi-search"></i> Realizar Pesquisa
					</button>
				</div>
			</div>
		
		</form>
		
		<div class="mt-2 mb-2">
			<p class="text-success">${mensagem_sucesso}</p>
        	<p class="text-danger">${mensagem_erro}</p>
		</div>
		
		<c:choose>
			<c:when test="${contas.size() > 0}">
				
				<div class="table-responsive">
					<table id="tabelaContas" class="table table-sm">
						<thead>
							<tr>
								<th>Data</th>
								<th>Valor</th>
								<th>Tipo</th>
								<th>Nome da conta</th>
								<th>Descrição</th>
								<th>Operações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contas}" var="conta">
								<tr>
									<td><fmt:formatDate value="${conta.data}" pattern="dd/MM/yyyy" /></td>
									<td><fmt:formatNumber value="${conta.valor}" type="currency" /></td>
									<td>
										<c:choose>
											<c:when test="${conta.tipo == 1}">
												<strong class="text-success">Receber</strong>
											</c:when>
											<c:otherwise>
												<strong class="text-danger">Pagar</strong>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${conta.nome}</td>
									<td>${conta.descricao}</td>
									<td>
										<a href="/javaProjetoContasMvc/admin/edicao-contas?id=${conta.idConta}"
											class="btn btn-sm btn-primary">
											<i class="bi bi-pencil-square"></i>
										</a>
										<a href="/javaProjetoContasMvc/admin/exclusao-contas?id=${conta.idConta}&dtMin=${dtMin}&dtMax=${dtMax}" 
											onclick="return confirm('Deseja realmente excluir a conta selecionada?');"    
											class="btn btn-sm btn-danger ms-2">
											<i class="bi bi-trash3"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</c:when>
			<c:when test="${contas.size() == 0}">
				<div class="mt-2">
					Nenhuma conta foi encontrada para o período de datas selecionado.
				</div>
			</c:when>
		</c:choose>
		
	</div>
	
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
     <!-- JQuery JS -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        
    <!-- JQuery Validation JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
    
    <!-- JQuery DataTables -->
    <script src="//cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    
    <script>
    	
    	//quando a página abrir, execute...
    	$(document).ready(function(){
    		
    		//validação dos campos do formulário
    		$("#formConsulta").validate({
    			rules: {
    				"dataMin": {
    					required: true
    				},
    				"dataMax": {
    					required: true
    				}
    			},
    			messages: {
    				"dataMin": {
    					required: "Por favor, informe a data de início."
    				},
    				"dataMax": {
    					required: "Por favor, informe a data de término."
    				}
    			}
    		});
    		
    		//formatação do datatable
    		$("#tabelaContas").DataTable({
    			language: {
    				url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
    			}
    		});
    		
    	});
    
    </script>
    
</body>
</html>