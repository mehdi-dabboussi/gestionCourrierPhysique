<html>
<head>
<TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>
 
<style type="text/css">
body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function crunchifyAjax() {
        $.ajax({
            url : 'ajaxtest.html',
            success : function(data) {
                $('#result').html(data);
            }
        });
    }
</script>
</head>
 
<body>
    <div align="center">
        
        <form name="maintain_catalog_frm" id="maintain_catalog_frm" >
		    <input type="submit" name="update" value="Inactive" onClick="crunchifyAjax();" >
		</form>
    </div>
</body>
</html>