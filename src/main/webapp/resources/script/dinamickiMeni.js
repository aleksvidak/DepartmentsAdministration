$(document).ready(function(){
	    $("ul li a").click(function(e){ 
	        e.preventDefault();
	        var url = $(this).attr('href'); //get the link you want to load data from
	        //alert(url);
	        $.ajax({ 
	         type: 'GET',
	         url: url,
	         success: function(data) {         	
	            $("#content").html(data); 
	        } 
	       }); 
	     });
	 
});