<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
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
        					<p><i class="bi bi-people-fill"></i> Crie sua conta de usuário!</p>
        					
        					<h5 class="text-success">${mensagem_sucesso}</h5>
        					<h5 class="text-danger">${mensagem_erro}</h5>
        					
    					</div>   
    					
    					<form id="formCadastro" method="post" action="cadastrar-usuario">
        
        					<div class="mb-2">
        						<label><i class="bi bi-person-circle"></i> Nome do usuário:</label>
        						<input type="text" name="nome" class="form-control"
        							placeholder="Digite seu nome aqui" />
        					</div>
        	
        					<div class="mb-2">
        						<label><i class="bi bi-envelope"></i> Email do usuário:</label>
        						<input type="text" name="email" class="form-control"
        							placeholder="Digite seu email aqui"/>
        					</div>
        	
        					<div class="mb-2">
        						<label><i class="bi bi-lock"></i> Senha do usuário:</label>
        						<input type="password" name="senha" id="senha" class="form-control"
        							placeholder="Digite sua senha aqui"/>
        					</div>
        					
        					<div class="mb-2">
        						<label><i class="bi bi-lock"></i> Confirme sua senha:</label>
        						<input type="password" name="senhaConfirmacao" class="form-control"
        							placeholder="Confirme sua senha aqui"/>
        					</div>
        	
        					<div class="mb-2 d-grid">
        						<button type="submit" class="btn btn-primary">
        							<i class="bi bi-arrow-right-circle-fill"></i> Realizar Cadastro
        						</button>
        					</div>
        					
        					<div class="mb-2 d-grid">
        						<a href="/javaProjetoContasMvc/" class="btn btn-light">
        							Voltar
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
        
        	//quando a página abrir, faça..
        	$(document).ready(function() {
        		
        		//validação do formulário
        		$("#formCadastro").validate({
        			rules: {
        				"nome": {
        					required: true,
        					minlength: 8,
        					maxlength: 100
        				},
        				"email": {
        					required: true,
        					email: true
        				},
        				"senha": {
        					required: true,
        					pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
        				},
        				'senhaConfirmacao': {
        					required: true,
        					equalTo: '#senha'
        				}
        			},
        			messages: {
        				"nome": {
        					required: 'Por favor, informe o seu nome.',
        					minlength: 'Informe pelo menos 8 caracteres.',
        					maxlength: 'Informe no máximo 100 caracteres.'
        				},
        				"email": {
        					required: 'Por favor, informe o seu email.',
        					email: 'Informe um endereço de email válido.'
        				},
        				"senha": {
        					required: 'Por favor, informe a sua senha.',
        					pattern: 'A senha deve conter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número, um caractere especial (por exemplo, !, @, #, $, etc.).'
        				},
        				"senhaConfirmacao": {
        					required: 'Por favor, confirme a sua senha.',
        					equalTo: 'Senhas não conferem, por favor verifique.'
        				}
        			}
        		});
        		
        	})
        
        </script>
        
    </body>
</html>




