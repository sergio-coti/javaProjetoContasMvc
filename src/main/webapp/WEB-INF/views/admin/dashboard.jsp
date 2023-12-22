<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard principal</title>
	
	<!-- Folha de estilos do Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
        
    <!-- Folha de estilos do Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>
    
</head>
<body>

	<jsp:include page="/WEB-INF/views/admin/components/navbar.jsp"></jsp:include>

	<div class="container mt-3">
		<h4>Dashboard principal</h4>
		<p>Seja bem vindo ao sistema de controle financeiro.</p>
		
		<div class="row mt-2">
			<div class="col">
			
				<form action="filtrardados" method="post">
					<div class="row">
						<div class="col">
							<input type="date" name="dataMin" class="form-control" value="${dtMin}"/>
						</div>
						<div class="col">
							<input type="date" name="dataMax" class="form-control" value="${dtMax}"/>
						</div>
						<div class="col">
							<button type="submit" class="btn btn-primary">
								<i class="bi bi-search"></i> Filtrar dados
							</button>
						</div>
					</div>
				</form>
				
				<div class="mt-3">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>Total de Contas a receber:</td>
								<td>
									<strong>
										<fmt:formatNumber value="${totalReceber}" type="currency"/>
									</strong>
								</td>
							</tr>
							<tr>
								<td>Total de Contas a pagar:</td>
								<td>
									<strong>
										<fmt:formatNumber value="${totalPagar}" type="currency"/>
									</strong>
								</td>
							</tr>
							<tr>
								<td>Saldo:</td>
								<td>
									<strong>
										<fmt:formatNumber value="${totalReceber - totalPagar}" type="currency"/>
									</strong>
								</td>
							</tr>
							<tr>
								<td>Situação:</td>
								<td>
									<c:choose>
										<c:when test="${totalReceber > totalPagar}">
											<strong class="text-success">Saldo positivo</strong>
										</c:when>
										<c:when test="${totalReceber == totalPagar}">
											<strong class="text-warning">Saldo nulo</strong>
										</c:when>
										<c:when test="${totalReceber < totalPagar}">
											<strong class="text-danger">Saldo devedor</strong>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
			<div class="col">
				<div id="graficoContas"></div>
			</div>
		</div>
		
	</div>
	
	<!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Highcharts JS -->
    <script src="https://code.highcharts.com/highcharts.js"></script>
    
    <script>
    
    	var dados = [
    	  ['Contas a Pagar', ${totalPagar}], 
    	  ['Contas a Receber', ${totalReceber}] 
    	];
    	
    	Highcharts.chart('graficoContas', {
    		chart: {
    			type: 'pie',
    		    backgroundColor: null, 
    		},
    		title: {
    			text: 'Comparativo de Contas a Pagar<br/>e Contas a Receber'
    		},
    		subtitle: {
    			text: 'Demonstrativo de contas do período.'
    		},
    		tooltip: {
    			pointFormat: '{point.name}: <b>{point.y}</b>',
    		},
    		plotOptions: {
    			pie: {
    		    	innerSize: '60%', 
    		      	dataLabels: {
    		        	enabled: false,
    		      	}
    		    }
    		},
    		series: [{
    			name: 'Valor',
    		    data: dados
    		}],
    		credits: false
    	});
    	
    </script>
    
</body>
</html>




