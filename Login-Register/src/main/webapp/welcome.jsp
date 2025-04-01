<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
<link rel="stylesheet" type="text/css" href="welcome.css">
</head>
<body>

  <%
        // Retrieve the session object
        HttpSession session1 = request.getSession(false);

        // Check if the session is valid and the username exists
        if (session1 != null && session1.getAttribute("username") != null) {
            String username = (String) session1.getAttribute("username");
  %>

    <div class="container">
        <h1>Welcome, <%= username %>!</h1>
        <p>We're delighted to have you on our platform. 🌟</p>
        <h3>Explore, learn, and connect with our vibrant community! 🚀</h3>
        <p>Feel free to stay as long as you like, and when you're ready, you can logout securely.</p>

        <!-- Logout Button -->
        <form action="logout.jsp" method="post">
           <button class="logout-btn" type="submit">Logout</button>

        </form>
    </div>

  <%
        } else {
            // Redirect to login page if session is invalid
            response.sendRedirect("login.jsp");
        }
  %>






</body>
</html>
