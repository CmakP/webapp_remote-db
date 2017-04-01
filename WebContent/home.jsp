<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>COMP 3613 Assignment01</title>
<LINK REL=STYLESHEET HREF="mainstyle.css" TYPE="text/css">
</head>

<!-- JSP init() -->
<%! private String instruction;
	private String greeting;

	public void jspInit() {
		
		ServletConfig config = getServletConfig();
		this.greeting = config.getInitParameter("greeting");
		this.instruction = config.getInitParameter("instruction");
}%>

<body>
<div>

 <div class="header">
  <h1>COMP 3613 Assignment01</h1>
  <h2>Siamak Pourian, A00977249</h2>
  <h3>Welcome user aka Paul!</h3>
 </div>

<div class="center">
<form action="assignment01" method="get">
 <div>
 <p class="smallline">The current time is:<br><%=new java.util.Date()%></p><br>
 <h2><%=this.greeting%><br><%=this.instruction%></h2>
 <input type="submit" value="Submit" class="button">
 </div>
</form>
</div>

<div class="footer">
<img src="Images/flag.png" alt="Flag">
Copyright © Siamak Pourian
</div>

</div> 	
</body>

</html>