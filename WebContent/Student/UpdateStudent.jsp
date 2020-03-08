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
										<li class="list-inline-item active">Student</li>
										<li class="list-inline-item seprate"><span>/</span></li>
										<li class="list-inline-item">Update Student</li>
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
							<h1 class="title-4">Update Student</h1>
							<hr class="line-seprate">
						</div>
					</div>
				</div>
			</section>
			<!-- END WELCOME-->

			<!-- CONTENT -->
			<div class="container">
				<div class="row">
					<div class="col-6 mx-auto">
					<form action="StudentController" method="post">
						<br>
						<div class="card h-100">
							<h4 class="card-header">Student Details</h4>
							
							<div class="card-body">
								<div class="form-group">
									<br />
									<!-- <h4 class="text-left ">Student ID* :</h4>
									<input type="text" name="id"
										class="form-control border border-info"
										placeholder="" required>
									<br> -->
									<h4 class="text-left ">Student ID :</h4>
									<input type="text" name="id" value="<c:out value="${student.id}"/>"
										class="form-control border border-info" 
										placeholder="without dash '-'" readonly>
									<br>
									<h4 class="text-left ">IC Number :</h4>
									<input type="text" name="ic" value="<c:out value="${student.ic}"/>"
										class="form-control border border-info" 
										placeholder="without dash '-'" readonly>
									<br>
									<h4 class="text-left ">Name* :</h4>
									<input type="text" name="name"
										class="form-control border border-info" value="<c:out value="${student.name}"/>"
										placeholder="" required>
									<br>
									<div class="row">
										<div class="col-4">
									<h4 class="text-left ">Age* :</h4>
									<input type="number" name="age"
										class="form-control border border-info" value="<c:out value="${student.age}"/>"
										placeholder="" required>
									<br>
									</div>
									<div class="col-8">
									<h4 class="text-left ">Address* :</h4>
									<textarea type="text" name="address"
										class="form-control border border-info" 
										placeholder="" required><c:out value="${student.address}"/></textarea>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col">
											<h4 class="text-left">Class Name* :</h4>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-4">
											<select name="form" class="form-control border border-info" required>
												<option selected hidden value="<c:out value="${form}"/>"><c:out value="${form}"/></option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
										</div>
										<div class="col-8">
											<select name="class" class="form-control border border-info" required>
												<option selected hidden value="<c:out value="${formClass}"/>"><c:out value="${formClass}"/></option>
												<option value="ARIF">ARIF</option>
												<option value="BESTARI">BESTARI</option>
												<option value="CENDIKIAWAN">CENDIKIAWAN</option>
												<option value="DAMAI">DAMAI</option>
												<option value="EFISIEN">EFISIEN</option>
											</select>
										</div>
									</div>
									<br>
									<h4 class="text-left ">Guardian Name* :</h4>
									<input type="text" name="guardianName"
										class="form-control border border-info" value="<c:out value="${student.guardianName}"/>"
										placeholder="" required>
									<br>
									<h4 class="text-left ">Guardian Job* :</h4>
									<input type="text" name="job"
										class="form-control border border-info" value="<c:out value="${student.guardianJob}"/>"
										placeholder="" required>
									<br>
								</div>
							</div>
							
							<div class="card-footer text-center ">
								<button type="submit" name="action" value="UpdateStudent" class="btn btn-primary ">Update</button>
							</div>
							
						</div>
						</form>
					</div>
				</div>
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

</body>

</html>
<!-- end document-->
