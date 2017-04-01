<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A00977249_Products</title>
<LINK REL=STYLESHEET HREF="mainstyle.css" TYPE="text/css">

<SCRIPT language="javascript" type="text/javascript">

		regexcode = /^\d{5}$/
	    regexdouble = /^(0*[1-9][0-9]*(\.[0-9]+)?|0+\.[0-9]*[1-9][0-9]*)$/
				
		function submitIt(myForm)
		{
			errMsg = ""
			if(myForm.Name.value == "") {
				errMsg = "Please fill in product Name\n"
			}
			if(!regexdouble.test(myForm.Price.value)) {
				errMsg = errMsg + "The Price has been incorrectly formatted (##.#)\n"
			}
			if(!regexdouble.test(myForm.Weight.value)) {
				errMsg = errMsg + "The Weight has been incorrectly formatted (##.#)\n"
			}
			if(!regexcode.test(myForm.Code.value)) {
				errMsg = errMsg + "The Code has been incorrectly formatted (#####)\n"
			}
			if(myForm.Code.value == "00000" && myForm.Code.value != "") {
				errMsg = errMsg + "The Code must contain at least one non-zero digit (#####)\n"
			}
			
			if(errMsg != ""){
				alert(errMsg)
				myForm.focus()
				return false
			}
			return true
		}
	 </SCRIPT>
</head>

<!-- JSP init() -->
<%! private String tableName;

	public void jspInit() {
		tableName = getServletContext().getInitParameter("tableName");
}%>

<!-- body section of document -->
	
<body>
<div>

   <div class="header">
    <h1>COMP 3613 Assignment01</h1>
    <h2>Siamak Pourian, A00977249</h2>
   </div>
   
   <div><p class="midfont">You are able to review the '<%=this.tableName%>' table because our server received a request from 
   you. Below, your information in addition to some server side and table meta data info has been
   provided for you to review.</p>
   <UL>
    <LI><B>User-Agent Header:</B> ${header["User-Agent"]}
    <LI><B>Server:</B> ${pageContext.servletContext.serverInfo}
    <LI><B>Protocol:</B> ${requestScope.protocol}
    <LI><B>DB URL:</B> ${requestScope.metadata[0]}
    <LI><B>DB Product Name:</B> ${requestScope.metadata[1]}
    <LI><B>DB Product Version:</B> ${requestScope.metadata[2]}
    <LI><B>JDBC Driver Name:</B> ${requestScope.metadata[3]}
    <LI><B>JDBC Driver Version:</B> ${requestScope.metadata[4]}
   </UL>
   </div>
 
<div class="mainDoc">
<table>
<tr>
  <th>Id</th>
  <th>Name</th>
  <th>Price(CAD)</th>
  <th>Weight(lbs)</th>
  <th>Code</th>
  <th>Manufacturer</th>
  <th>Made In</th>
  <th>Description</th>
  <th colspan="2">Action</th>
</tr>

${requestScope.products}


<form action="assignment01" method="post" onSubmit="return submitIt(this)">    
<input type="hidden" name="insert" value="insert">
<tr>
  <td>Autofill</td>
  <td><input type="text" name="Name"></td>
  <td><input type="text" name="Price" size="12"></td>
  <td><input type="text" name="Weight" size="12"></td>
  <td><input type="text" name="Code" size="12"></td>
  <td><input type="text" name="Manufacturer" size="12"></td>
  <td><input type="text" name="MadeIn" size="12"></td>
  <td><input type="text" name="Description" size="40"></td>
  <td><input type="submit" value="Insert" class="button buttonTb"></td>
  <td><input type="reset" class="button buttonTb"></td>  
</tr>
</form>
</table> 
</div>

</div>

<div class="footer" style="margin-top: 145px">
<img src="Images/flag.png" alt="Flag">
Copyright © Siamak Pourian
</div>

</body>
</html>