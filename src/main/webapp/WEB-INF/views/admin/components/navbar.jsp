<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"><strong>Controle de
				Finanças</strong></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link text-white"
					aria-current="page" href="/javaProjetoContasMvc/admin/dashboard">
						Dashboard Principal </a></li>
				<li class="nav-item"><a class="nav-link text-white"
					aria-current="page"
					href="/javaProjetoContasMvc/admin/cadastro-contas"> Cadastrar
						Contas </a></li>
				<li class="nav-item"><a class="nav-link text-white"
					aria-current="page"
					href="/javaProjetoContasMvc/admin/consulta-contas"> Consultar
						Contas </a></li>
				<li class="nav-item"><a class="nav-link text-white"
					aria-current="page" href="#"> <strong>${usuario.nome}</strong>
						${usuario.email} |
				</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					aria-current="page" href="/javaProjetoContasMvc/logout"> Sair
						do Sistema </a></li>
			</ul>
		</div>
	</div>
</nav>