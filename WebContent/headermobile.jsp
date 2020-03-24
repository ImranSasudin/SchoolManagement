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

<header class="header-mobile header-mobile-2 d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="index.html">
                            <img src="/SchoolManagement/images/icon/apple-touch-icon2.png" alt="CoolAdmin" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                <% if(role.equalsIgnoreCase("Teacher")){ %>
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-graduation-cap"></i>Student</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="/SchoolManagement/Student/RegisterStudent.jsp">Add New Student</a>
                                </li>
                                <li>
                                    <a href="/SchoolManagement/StudentController?action=ListStudents">List All Students</a>
                                </li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-copy"></i>Examination</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="/SchoolManagement/Examination/AddExamination.jsp">Add New Examination</a>
                                </li>
                                <li>
                                    <a href="/SchoolManagement/ExaminationController?action=ListExaminations">List All Examinations</a>
                                </li>
                            </ul>
                        </li>

                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="far fa-calendar-alt"></i>Event</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="/SchoolManagement/Event/AddEvent.jsp">Add New Event</a>
                                </li>
                                <li>
                                    <a href="/SchoolManagement/EventController?action=ListEvent">List All Events</a>
                                </li>
                            </ul>
                        </li>
                        <!-- <li><a href="/SchoolManagement/StudentController?action=PerformanceByBatch"> <i class="fas fa-trophy"></i><span
							class="bot-line"></span>Performance by Batch
						</a>
						</li> -->
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-briefcase"></i>Teacher</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="/SchoolManagement/Teacher/RegisterTeacher.jsp">Register New Teacher</a>
                                </li>
                                <li>
                                    <a href="/SchoolManagement/TeacherController?action=ListTeachers">List All Teachers</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <% } %>
                </div>
            </nav>
        </header>
        <div class="sub-header-mobile-2 d-block d-lg-none">
            <div class="header__tool">
                <div class="account-wrap">
                    <div class="account-item account-item--style2 clearfix js-item-menu">
                        <div class="image">
                            <img src="/SchoolManagement/images/icon/profile.png" alt="John Doe" />
                        </div>
                        <div class="content">
                            <a class="js-acc-btn" href="#"><%=userName %></a>
                        </div>
                        <div class="account-dropdown js-dropdown">
                            <div class="info clearfix">
                                <div class="image">
                                    <a href="#">
                                        <img src="/SchoolManagement/images/icon/profile.png" alt="John Doe" />
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
                                    <a href="/SchoolManagement/LoginController?action=Account&ID=<%=userID%>">
                                        <i class="zmdi zmdi-account"></i>Account</a>
                                </div>
                                <%
									} else if (role.equalsIgnoreCase("Student")) {
								%>
								<div class="account-dropdown__item">
                                    <a href="/SchoolManagement/LoginController?action=Account&ID=<%=userIDStudent%>">
                                        <i class="zmdi zmdi-account"></i>Account</a>
                                </div>
                                <% } %>
                            </div>
                            <div class="account-dropdown__footer">
                                <a href="/SchoolManagement/LoginController?action=Logout" onclick="return confirm('are you sure?')">
                                    <i class="zmdi zmdi-power"></i>Logout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>