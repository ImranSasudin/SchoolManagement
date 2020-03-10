<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>School Management</title>

	<!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="css/datatables-select.min.css">
  	<link rel="stylesheet" href="css/datatables.min.css">

</head>

<body class="animsition">
	<div class="page-wrapper">
		<!-- HEADER DESKTOP-->
		<jsp:include page="../headerdesktop.jsp" />
		<!-- END HEADER DESKTOP-->

		<!-- HEADER MOBILE-->
		<jsp:include page="../headermobile.jsp" />
		<!-- END HEADER MOBILE -->

		<!-- PAGE CONTENT-->
		<div class="page-content--bgf7">
			<!-- BREADCRUMB-->
			<section class="au-breadcrumb2">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="au-breadcrumb-content">
								<div class="au-breadcrumb-left">
									<span class="au-breadcrumb-span">You are here:</span>
									<ul class="list-unstyled list-inline au-breadcrumb__list">
										<li class="list-inline-item active">Examination
										</li>
										<li class="list-inline-item seprate"><span>/</span></li>
										<li class="list-inline-item">Student List</li>
									</ul>
								</div>
								<!--<form class="au-form-icon--sm" action="" method="post">
									<input class="au-input--w300 au-input--style2" type="text"
										placeholder="Search for datas &amp; reports...">
									<button class="au-btn--submit2" type="submit">
										<i class="zmdi zmdi-search"></i>
									</button>
								</form>-->
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- END BREADCRUMB-->
			
			<!-- WELCOME-->
			<section class="welcome p-t-10">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="title-4">
								${examination.examinationName}, ${examination.examinationDateText}
							</h1>
							<ul class="nav nav-tabs">
								<li class="active"><a class="btn btn-primary" data-toggle="tab" href="#home">PT3 </a></li>
								<li><a class="btn btn-primary" data-toggle="tab" href="#menu1"> SPM </a></li>
							</ul>
							<hr class="line-seprate">
						</div>
					</div>
				</div>
			</section>
			<!-- END WELCOME-->

			<!-- WELCOME-->
			<section class="welcome p-t-10">
				<div class="container">
					<div class="row">
						<div class="col-lg-9 mx-auto">
						
							<div class="tab-content">
								<div id="home" class="tab-pane fade in active">
									<table id="dtBasicExample" style="table-layout: fixed"
										class="table table-striped table-bordered">
										<thead style="">
											<th class="th-sm">Student Name</th>
											<th class="th-sm">Class</th>
											<th class="th-sm">CGPA</th>
											<th class="th-sm"></th>
										</thead>
										<tbody style="">
											<c:forEach items="${students1}" var="s1">
												<c:forEach items="${students1cgpa }" var="s1C">
													<c:choose>
														<c:when test="${s1.id == s1C.id}">
															<tr>
																<td><c:out value="${s1.name}" /></td>
																<td><c:out value="${s1.className}" /></td>
																<td><c:out value="${s1C.cgpa}" /></td>
																<td class="text-center"><a
																	href="StudentController?action=UpdateStudent&id=<c:out value="${s.id}"/>"
																	class="btn btn-warning">View Grade</a></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td><c:out value="${s1.name}" /></td>
																<td><c:out value="${s1.className}" /></td>
																<td>No CGPA yet</td>
																<td class="text-center"><a
																	href="StudentController?action=UpdateStudent&id=<c:out value="${s.id}"/>"
																	class="btn btn-primary">Add Grade</a></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:forEach>
										</tbody>
									</table>
								</div>

								<div id="menu1" class="tab-pane fade">
									<table id="dtBasicExample2" style="table-layout: fixed"
										class="table table-striped table-bordered">
										<thead style="">
											<th class="th-sm">Student Name</th>
											<th class="th-sm">Class</th>
											<th class="th-sm">CGPA</th>
											<th class="th-sm"></th>
										</thead>
										<tbody style="">
											<c:forEach items="${students2}" var="s2">
												<c:forEach items="${students2cgpa}" var="s2C">
													<c:choose>
														<c:when test="${s2.id == s2C.id}">
															<tr>
																<td><c:out value="${s2.name}" /></td>
																<td><c:out value="${s2.className}" /></td>
																<td><c:out value="${s2C.cgpa}" /></td>
																<td class="text-center"><a
																	href="StudentController?action=UpdateStudent&id=<c:out value="${s.id}"/>"
																	class="btn btn-warning">View Grade</a></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td><c:out value="${s2.name}" /></td>
																<td><c:out value="${s2.className}" /></td>
																<td>No CGPA yet</td>
																<td class="text-center"><a
																	href="StudentController?action=UpdateStudent&id=<c:out value="${s.id}"/>"
																	class="btn btn-primary">Add Grade</a></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</section>
			<!-- END WELCOME-->

			<!-- CONTENT -->
			<div class="container">
			
			</div>

			<!-- COPYRIGHT-->
			<section class="p-t-60 p-b-20">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="copyright">
								<p>Copyright © 2020 School Management System</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- END COPYRIGHT-->
		</div>

	</div>

	<!-- Jquery JS-->
    <script src="vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="vendor/slick/slick.min.js">
    </script>
    <script src="vendor/wow/wow.min.js"></script>
    <script src="vendor/animsition/animsition.min.js"></script>
    <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
    </script>
    <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="vendor/counter-up/jquery.counterup.min.js">
    </script>
    <script src="vendor/circle-progress/circle-progress.min.js"></script>
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="vendor/select2/select2.min.js">
    </script>

    <!-- Main JS-->
    <script src="js/main.js"></script>
    <script src="js/datatables.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#dtBasicExample').DataTable({
				'columnDefs' : [ {

					'targets' : [ 3 ], // column or columns numbers

					'orderable' : false, // set orderable for selected columns

				} ],
				"scrollX" : true,
				responsive : true,
				"bAutoWidth" : false
			});
			$('#dtBasicExample2').DataTable({
				'columnDefs' : [ {

					'targets' : [ 3 ], // column or columns numbers

					'orderable' : false, // set orderable for selected columns

				} ],
				"scrollX" : true,
				responsive : true,
				"bAutoWidth" : false
			});
			$('.dataTables_length').addClass('bs-select');
		});
	</script>

</body>

</html>
<!-- end document-->
