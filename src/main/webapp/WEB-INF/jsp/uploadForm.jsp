<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="icon" type="image/png" href="images/favicon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/phNumAnalyzer.css">

<div class="container">
	<div class="row">
		<div class="twelve column" style="margin-top: 15%">
			<h1>Phone Number Analyzer</h1>
			
			<form:form method="post" action="upload.htm" modelattribute="uploadForm" enctype="multipart/form-data">
				<p>Select file to upload</p>
				<table id="fileTable">
					<tbody>
						<tr>
							<td><input name="file" type="file"></td>
						</tr>
					</tbody>
				</table>
				<br>
				<input type="submit" value="Upload">
			</form:form>
		</div>
	</div>
</div>