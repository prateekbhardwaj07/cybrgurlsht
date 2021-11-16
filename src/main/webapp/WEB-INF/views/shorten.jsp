<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shortened Url</title>
<!-- JQuery Imports-->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
<body>
<div>
<h1>URl Shortened Service</h1>
<h2>Given is the Shortened Url :- ${shortpath}</h2>
</div>

<div>
<button name="urlshtbtn" class="urlsht_btn" onclick="redirect()">Redirect Button</button>
</div>

<script>
		function redirect(){			
			console.log('${shortpath}');
			$.ajax({
				type:"POST",
				url:"http://localhost:8080/cybrgurlsht/redirect?shortpath=${shortpath}",
				success:function(data){
					var fullpath = data;
					if(fullpath != null){
						window.open(fullpath);	
					}
					else{
						console.log('path is null')
					}
				},
				error:function(error){
					console.log('error in ajax ${error}');
				}
			})
			}
	</script>

</body>
</html>