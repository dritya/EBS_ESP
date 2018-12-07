<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body id="bdy">
<div class="container">
<h2 class="title"> Admin Dashboard</h2>
<div class="set">
<form method="GET" action="/adminaddnewactivity">

<p><b> Enter muscle</b>
<input type="text" id="muscle" name="muscle" required>
</p>

<p><b>Enter exercise</b>
<input type="text" id="exercise" name="exercise" required>
</p>


<p><b>Enter time</b>
<input type="text" id="time" name="time" required>
</p>

<p><b>Enter calories</b>
<input type="text" id="calories" name="calories" required>
</p>

<p>
<input type="submit" name="Submit" value="Submit">
</p>
</form>
</div>
${message}
</div>
</body>
</html>

<style>

#bdy {
    /* The image used */
    background-image: url("https://images.unsplash.com/photo-1544021601-3e5723f9d333?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
	
    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    
}
	.container {
		color: red;
		width: 33%;
	    padding-top: 30px;
	    margin: auto;
	}
	
  .set {
	    padding-bottom: 10px;
    		border-bottom: 1px solid white;
    		font-size: 20px;
	}
	.title {
		text-align: center;
    		font-size: 60px;
	}
	</style>