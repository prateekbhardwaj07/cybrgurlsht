<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cyborg Url Shortener</title>
</head>
<body>
    <div align="center">
        <h1>Cyborg Url Webapp</h1>
    </div>
    <div>
    <form id="urlform" action="/cybrgurlsht/shorten" method = "post">
			<label>Copy Url In Box</label>
			<input type="text" name="UrlBox" id="input_url" style="width:300px;height:25px"/>
			<input type="submit" name="submit_btn" value="Submit Data" id="submit_btn">
		</form>
    </div>
</body></html>
