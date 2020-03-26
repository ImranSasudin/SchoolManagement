<% String role = (String) session.getAttribute("userRoleSession");
String userIDStudent = null;
Integer userID = null;
if (role.equalsIgnoreCase("Student")){
	userIDStudent = (String) session.getAttribute("userIDSession");
}
else if (role.equalsIgnoreCase("Teacher")){
	userID = (Integer) session.getAttribute("userIDSession");
}
 String userName = (String) session.getAttribute("userNameSession"); %>


<header class="header-desktop3 d-none d-lg-block">
	<div class="section__content section__content--p35">
		<div class="header3-wrap">
			<div class="header__logo">
				<a href="#"> <img src="/SchoolManagement/images/icon/apple-touch-icon2.png"
					alt="CoolAdmin" />
				</a>
			</div>
			<div class="header__navbar">
			<% if(role.equalsIgnoreCase("Teacher")){ %>
				<ul class="list-unstyled">
					<li class="has-sub"><a href="#"> <i
							class="fas fa-graduation-cap"></i>Student <span class="bot-line"></span>
					</a>
						<ul class="header3-sub-list list-unstyled">
							<li><a href="/SchoolManagement/Student/RegisterStudent.jsp">Add New Student</a></li>
							<li><a href="/SchoolManagement/StudentController?action=ListStudents">List All Students</a></li>
						</ul></li>
					<li class="has-sub"><a href="#"> <i
							class="fas fa-copy"></i> <span class="bot-line"></span>Examination
					</a>
						<ul class="header3-sub-list list-unstyled">
							<li><a href="/SchoolManagement/Examination/AddExamination.jsp">Add New Examination</a></li>
							<li><a href="/SchoolManagement/ExaminationController?action=ListExaminations">List All Examinations</a></li>
						</ul></li>

					<li class="has-sub"><a href="#"> <i class="far fa-calendar-alt"></i>
							<span class="bot-line"></span>Event
					</a>
						<ul class="header3-sub-list list-unstyled">
							<li><a href="/SchoolManagement/Event/AddEvent.jsp">Add New Event</a></li>
							<li><a href="/SchoolManagement/EventController?action=ListEvent">List All Events</a></li>
						</ul>
					</li>
					<li><a href="/SchoolManagement/StudentController?action=PerformanceByBatch"> <i class="fas fa-trophy"></i> <span
							class="bot-line"></span>Performance by Batch
					</a>
					</li>
					<li class="has-sub"><a href="#"> <i class="fas fa-briefcase"></i>
							<span class="bot-line"></span>Teacher
					</a>
						<ul class="header3-sub-list list-unstyled">
							<li><a href="/SchoolManagement/Teacher/RegisterTeacher.jsp">Register New Teacher</a></li>
							<li><a href="/SchoolManagement/TeacherController?action=ListTeachers">List All Teachers</a></li>
						</ul>
					</li>
				</ul>
			<% } %>
			</div>
			<div class="header__tool">
				<div class="account-wrap">
					<div
						class="account-item account-item--style2 clearfix js-item-menu">
						<div class="image">
							<img src="/SchoolManagement/images/icon/profile.png" alt="Profile Picture" />
						</div>
						<div class="content">
							<a class="js-acc-btn" href="#"><%=userName %></a>
						</div>
						<div class="account-dropdown js-dropdown">
							<div class="info clearfix">
								<div class="image">
									<a href="#"> <img src="/SchoolManagement/images/icon/profile.png"
										alt="Profile Picture" />
									</a>
								</div>
								<div class="content">
									<h5 class="name">
										<a href="#"><%=userName %></a>
									</h5>
									<%
										if (role.equalsIgnoreCase("Teacher")) {
									%>
									<span class="email">ID : <%=userID%></span>
									<%
										} else if (role.equalsIgnoreCase("Student")) {
									%>
									<span class="email">ID : <%=userIDStudent%></span>
									<%
										}
									%>

								</div>
							</div>
							<div class="account-dropdown__body">
								<%
									if (role.equalsIgnoreCase("Teacher")) {
								%>
								<div class="account-dropdown__item">
									<a
										href="/SchoolManagement/LoginController?action=Account&ID=<%=userID%>">
										<i class="zmdi zmdi-account"></i>Account
									</a>
								</div>
								<%
									} else if (role.equalsIgnoreCase("Student")) {
								%>
								<div class="account-dropdown__item">
									<a
										href="/SchoolManagement/LoginController?action=Account&ID=<%=userIDStudent%>">
										<i class="zmdi zmdi-account"></i>Account
									</a>
								</div>
								<% } %>
							</div>
							<div class="account-dropdown__footer">
								<a href="/SchoolManagement/LoginController?action=Logout" onclick="return confirm('are you sure?')"> <i class="zmdi zmdi-power"></i>Logout
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>