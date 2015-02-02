<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="icon" type="image/png" href="images/favicon.png">
<script type="text/javascript" src="<c:url value="js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="js/jquery.dataTables-1.10.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/dataTables.jqueryui.js"/>"></script>

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/phNumAnalyzer.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/dataTables.jqueryui.css">

<div class="container">
	<div class="row">
		<div class="twelve column" style="margin-top: 5%">
			<h4>Phone Number Analyzer</h4>
			<table id="example" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Phone Number</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="phone" items="${phoneList}" varStatus="rowCounter">
						<tr>
							<td>${phone.phoneNumber}</td>
							<c:choose>
								<c:when test="${phone.status == 'Success'}">
									<c:set var="color" value="success" />
								</c:when>
								<c:when test="${phone.status == 'Failed'}">
									<c:set var="color" value="failed" />
								</c:when>
								<c:when test="${phone.status == 'Invalid'}">
									<c:set var="color" value="invalid" />
								</c:when>
								<c:when test="${phone.status == 'Already Blacklisted'}">
									<c:set var="color" value="repeated" />
								</c:when>
								<c:otherwise>
									<c:set var="color" value="" />
								</c:otherwise>
							</c:choose>
							<td class="${color}">${phone.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#example').dataTable({

		});
	});
</script>