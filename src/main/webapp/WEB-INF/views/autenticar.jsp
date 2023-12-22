<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Autenticação de usuários</title>

  	<!-- Folha de estilos do Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
        
    <!-- Folha de estilos do Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>
        
    <!-- Folha de estilos CSS do projeto -->
    <link rel="stylesheet" href="resources/style.css"/>

</head>
<body class="bg-secondary">

	<div class="row mt-5">
		<div class="col-md-4 offset-md-4">
			<div class="card">
				<div class="card-body">
					
					<div class="text-center">
					
						<h4>Sistema de controle de contas</h4>
						<p><i class="bi bi-people-fill"></i> Acessar Sistema!</p>
						
						<h5 class="text-danger">${mensagem_erro}</h5>
						
					</div>
					
					<form id="formAutenticar" action="autenticar-usuario" method="post">
						
						<div class="mb-2">
							<label><i class="bi bi-envelope"></i> Email de acesso:</label>
							<input type="text" class="form-control" name="email"
								placeholder="Digite seu email aqui."/>
						</div>
						
						<div class="mb-2">
							<label><i class="bi bi-lock"></i> Senha de acesso:</label>
							<input type="password" class="form-control" name="senha"
								placeholder="Digite sua senha aqui."/>
						</div>
						
						<div class="mb-2 d-grid">
							<button type="submit" class="btn btn-primary">
								<i class="bi bi-arrow-right-circle-fill"></i> Entrar
							</button>
						</div>
						
						<div class="mb-2 d-grid">
							<a href="/javaProjetoContasMvc/criar-usuario"
								class="btn btn-light">
								Não possui conta? <strong>Cadastre-se aqui!</strong>
							</a>
						</div>
						
					</form>
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        
    <!-- JQuery JS -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        
    <!-- JQuery Validation JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>

	<script>
	
		//execute quando a página abrir..
		$(document).ready(function(){
			
			//aplicando as validações no formulário..
			$("#formAutenticar").validate({
				rules: {
					"email": {
						required: true,
						email: true
					},
					"senha": {
						required: true,
						pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
					}
				},
				messages: {
					"email": {
						required: "Por favor, informe seu email de acesso.",
						email: "Por favor, informe um endereço de email válido."
					},
					"senha": {
						required: "Por favor, informe sua senha de acesso.",
						pattern: "A senha deve conter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número, um caractere especial (por exemplo, !, @, #, $, etc.)."
					}
				}
			});
			
		});
	
	</script>

</body>
</html>


