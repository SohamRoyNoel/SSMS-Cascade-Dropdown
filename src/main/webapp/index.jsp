<%@page import="queryLibrary.Queries"%>
<%@page import="dto.States"%>
<%@page import="connectionFactory.Connections"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<%
   ResultSet resultset = null;
   %>
<HTML>
<HEAD>
<TITLE>Select element drop down box</TITLE>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script>
         $(document).ready(function() {        	
         
         $('#countries').change(function(event) {
         		// alert("dick");
                 var countries = $("select#countries").val();
                 // alert(countries);
                 $.get('JsonServlet', {
                         countryName : countries
                 }, function(response) {
         
                 var select = $('#states');
                 select.find('option').remove();
                   $.each(response, function(index, value) {
                   $('<option>').val(index).text(value).appendTo(select);
         //          alert(index);
               });
                 });
                 });
           
         // Get the ID of choosen state
         $('#states').change(function(event) {
         		// alert("dick");
         	    var states = $("select#states").val();
         	    // alert(countries);
         	    $.get('JsonServletRoads', {
         	            statesName : states
         	    }, function(response) {
         	
         	    var select = $('#roads');
         	    select.find('option').remove();
         	      $.each(response, function(index, value) {
         	      $('<option>').val(index).text(value).appendTo(select);
         	//      alert(index);
         	  });
         	    });
             });
         });
      </script>
</HEAD>
<BODY>
	<%
         try {
        	
         	Connection connection = Connections.getConnection();
         	 System.out.println("Connection : from index" + connection);
         	Statement statement = connection.createStatement();
         	resultset = statement.executeQuery(Queries.askCountries);
         %>
	<h1>Cascade Drop down box or select element</h1>
	Select Your Country
	<select id="countries">
		<option>Select Country</option>
		<%
            while (resultset.next()) {
            %>
		<option value="<%=resultset.getString(1)%>"><%=resultset.getString(2)%></option>
		<%
            }
            %>
	</select>
	<%
         } catch (Exception e) {
         	out.println("wrong entry" + e);
         }
         %>
	<br />
	<br /> Select Your States:
	<select id="states">
		<option>Select States</option>
	</select>
	<br />
	<br /> Select Your States:
	<select id="roads">
		<option>Select Roads</option>
	</select>
</BODY>
</HTML>