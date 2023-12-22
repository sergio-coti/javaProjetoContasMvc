<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edição de Contas</title>
	
	<!-- Folha de estilos do Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
        
    <!-- Folha de estilos do Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>
    
    <!-- Folha de estilos CSS do projeto -->
    <link rel="stylesheet" href="../resources/style.css"/>
    
</head>
<body>

	<jsp:include page="/WEB-INF/views/admin/components/navbar.jsp"></jsp:include>

	<div class="container mt-3">
	
		<h4>Edição de Contas</h4>
		<p>Utilize o formulário para atualizar suas contas a pagar e a receber.</p>
		
		<hr/>
		
			<h5 class="text-success">${mensagem_sucesso}</h5>
        	<h5 class="text-danger">${mensagem_erro}</h5>
		
			<form id="formEdicao" action="editar-contas" method="post">
			
			<!-- campo oculto -->
			<input type="hidden" name="idConta" value="${conta.idConta}"/>
		
			<div class="row mb-2">
				<div class="col-md-6">
					<label><i class="bi bi-file-earmark"></i> Nome:</label>
					<input type="text" name="nome" class="form-control" 
						value="${conta.nome}"/>
				</div>
				<div class="col-md-3">
					<label><i class="bi bi-calendar-date"></i> Data:</label>
					<input type="date" name="data" class="form-control" 
						value="<fmt:formatDate value="${conta.data}" pattern="yyyy-MM-dd"/>"/>
				</div>
				<div class="col-md-3">
					<label><i class="bi bi-cash-coin"></i> Valor:</label>
					<input type="text" name="valor" class="form-control" 
						value="${conta.valor}"/>
				</div>
			</div>
			
			<div class="row mb-2">
				<div class="col-md-6">
					<label><i class="bi bi-body-text"></i> Descrição:</label>
					<textarea name="descricao" class="form-control">${conta.descricao}</textarea>
				</div>
				<div class="col-md-3">
					<Label><i class="bi bi-menu-app"></i> Tipo:</Label>
					<select name="tipo" class="form-select">
						<option value="">Selecione</option>
						<option value="1" ${conta.tipo == 1 ? "selected" : ""}>Conta a receber</option>
						<option value="2" ${conta.tipo == 2 ? "selected" : ""}>Conta a pagar</option>
					</select>
				</div>
			</div>
			
			<div class="row mb-2">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-arrow-right-circle-fill"></i> Salvar Alterações
					</button>
					<a href="/javaProjetoContasMvc/admin/consulta-contas" class="btn btn-light">
						Ir para a consulta
					</a>
				</div>
			</div>
		
		</form>
		
	</div>
	
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- JQuery JS -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        
    <!-- JQuery Validation JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
    
    <script>
    
    	//quando a página abrir, faça..
    	$(document).ready(function(){
    		//validar o formulário
    		$("#formEdicao").validate({
    			rules: {
    				"nome": {
    					required: true,
    					minlength: 8,
    					maxlength: 100
    				},
    				"data": {
    					required: true
    				},
    				"valor": {
    					required: true
    				},
    				"descricao": {
    					required: true
    				},
    				"tipo": {
    					required: true
    				}
    			},
    			messages: {
    				"nome": {
    					required: "Por favor, informe o nome da conta.",
    					minlength: "Por favor, informe pelo menos 8 caracteres.",
    					maxlength: "Por favor, informe no máximo 100 caracteres."
    				},
    				"data": {
    					required: "Por favor, informe a data da conta."
    				},
    				"valor": {
    					required: "Por favor, informe o valor da conta."
    				},
    				"descricao": {
    					required: "Por favor, informe a descrição da conta."
    				},
    				"tipo" : {
    					required: "Por favor, selecione o tipo da conta."
    				}
    			}
    		});
    	});
    
    </script>
    
</body>
</html>