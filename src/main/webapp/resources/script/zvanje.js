$(document).ready(function () {
	var azuriranje=0;
    
    $("#frmZvanje").validate({
        rules: {
        	zvanje: { required: true }
        	

        },
        messages: {
        	zvanje: "Unesite zvanje"
        
     
        }
    });
	
	
	var anSelected ;
    var oTable = $("#tbZvanje").dataTable({
        "bJQueryUI": true,
      //  "bFilter": false,
         "bStateSave": true,
        "aoColumnDefs": [
              // { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] }
        ],
        "oLanguage": {
            "sProcessing": "Procesiranje u toku...",
            "sLengthMenu": "Prikazi _MENU_  ",

            "sInfo": "Prikaz _START_ do _END_ od ukupno _TOTAL_ elemenata",
            "sInfoEmpty": "Prikaz 0 od 0 do ukupno 0 elemenata",

            "sInfoPostFix": "",
            "sSearch": "Pretraga:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Pocetna",
                "sPrevious": "Prethodna",
                "sNext": "Sledeca",
                "sLast": "Poslednja"
            }
        }
        
    });

   function fnGetSelected(oTableLocal) { return oTableLocal.$('tr.row_selected'); }

   $("#tbZvanje tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
       
        $("#btnDel").attr('disabled', false);
        
     
    });   
   function otvoriPopUp(){
	   $("#divZvanjePOPUP").dialog({
           title: "Zvanje",
           width: 550,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });
	   
   }
   
   $('#btnNew').click(function () {
	   azuriranje=0;
	   $("#zvanje").attr('value', '');
       otvoriPopUp();


   });
   
   function Proc_Insert(){
	   if ($("#frmZvanje").validate().form()) {	
	        $.ajax({
	            url: 'dodajZvanje.html',
	            type: 'POST',
	            data: {
	            	zvanje: $("#zvanje").val()
	              
	            },
	            success: function (data) {
	            	 if($.isNumeric(data)){
	            	  oTable.fnAddData([
	                                   data,
	                                   $("#zvanje").val(),
	                              
	                                   ]);
	            	  $("#zvanje").attr('value', '');
	          
	                  showNotification({
                         message: 'Sacuvano',
                         autoClose: true,
                         duration: 5
                     });
	            	 }
	            	 else{
	            	        showNotification({
	                            message: data,
	                            autoClose: true,
	                            type:"warning",
	                            duration: 5
	                        });
	            	 }
	            },
	        error: function(){
	        	alert("GRESKA");
	        }

	        }); // kraj ajax
	   }
//	   
   }
//   function  Proc_Update(){
//	   if ($("#frmLabs").validate().form()) {	  
//		   var anSelected = fnGetSelected(oTable);
//	        $.ajax({
//	            url: 'izmeniLab.html',
//	            type: 'POST',
//	            data: {
//	            	id_Lab:oTable.fnGetData(anSelected[0])[0].toString(),
//	            	nazivLab: $("#nazivLab").val(),
//	            	selKatedre: $("#selKatedre").val(),
//	            	sajt:$("#sajtLab").val(),
//	              
//	            },
//	            success: function (data) {	   
//	            //	alert(fnGetData(anSelected[0])[0].toString());
//	            	  oTable.fnUpdate([
//	            	                  oTable.fnGetData(anSelected[0])[0].toString(),
//	                                   $("#nazivLab").val(),
//	                                   $("#selKatedre").val(),
//	                                   $("#selKatedre option:selected").text(),
//	                                   $("#sajtLab").val()
//	                                   ],anSelected[0]);
//	            	
//	            	  $("#divLabPOPUP").dialog('close');
//	                 
//	                  showNotification({
//                        message: data,
//                       autoClose: true,
//                       duration: 5
//                    });
//
//	            },
//	        error: function(){
//	        	alert("GRESKA");
//	        }
//
//	        }); // kraj ajax
//	   }
//	   
//   }
//   
   $('#btnSacuvaj').click(function () {
	
		   Proc_Insert();
	

   });
 
   
  
   
   $('#btnDel').click(function () {
	   var anSelected = fnGetSelected(oTable);
       $.ajax({
           url: 'obrisiZvanje.html',
           type: 'POST',
           data: {
           	idZvanje:oTable.fnGetData(anSelected[0])[0].toString()             
           },
           success: function (data) {              
        	  
        	   if(data=="OBRISANO"){
        		   oTable.fnDeleteRow(anSelected[0]);
        		    showNotification({
                        message: data,
                       autoClose: true,
                       duration: 5
                    });
        	   }
        	   else{
        		    showNotification({
                        message: data,
                       autoClose: true,
                       type: "warning",
                       duration: 5
                    });
        	   }
             

           },
       error: function(){
       	alert("GRESKA");
       }

       }); // kraj ajax

   });
//   
   $('#btnOdustani').click(function () {
	   
       $("#divZvanjePOPUP").dialog('close');
   });
   
   

});//doc ready