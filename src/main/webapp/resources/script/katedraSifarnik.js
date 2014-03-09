$(document).ready(function () {
	var azuriranje=0;
	$.ajaxSetup({ cache: false });
	
    $("#frmKatSif").validate({
        rules: {
        	nazivKat: { required: true },
        	rukovodilac: { required: true },
        	sekretar: { required: true }

        },
        messages: {
        	nazivKat: "Unesite naziv",
        	rukovodilac: "Odaberite rukovodioca",
        	sekretar:"Odaberite sekretara"
     
        }
    });
	
	
	var anSelected ;
	function  crtajTabelu(){
    var oTable = $("#tbKatSif").dataTable({
        "bJQueryUI": true,
      //  "bFilter": false,
         "bStateSave": true,
        "aoColumnDefs": [
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [2] },
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [4] }
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
} 
    var ex = document.getElementById('tbKatSif');
    if (!$.fn.DataTable.fnIsDataTable(ex)) {
        crtajTabelu();
    }
    else {
        oTable.fnDraw();
    }

   function fnGetSelected(oTableLocal) { return oTableLocal.$('tr.row_selected'); }

   $("#tbKatSif tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
        $("#btnChange").attr('disabled', false);
        $("#btnDel").attr('disabled', false);
        
     
    });   
   function otvoriPopUp(){
	   $("#divKatSifPOPUP").dialog({
           title: "Dodavanje katedre",
           width: 670,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });
	   
   }
   
   function ocistiPopUp(){
	   $("#nazivKat").attr('value', '');
       $("#rukovodilac").attr('value', '');
       $("#sekretar").attr('value', '');
       
   }
//   
   $('#btnNew').click(function () {
	   azuriranje=0;
	   ocistiPopUp();
       otvoriPopUp();
   });
   
   function Proc_Insert(){
	   if ($("#frmKatSif").validate().form()) {	
	        $.ajax({
	            url: 'dodajKatedru.html',
	            type: 'POST',
	            data: {
	            	nazivKat: $("#nazivKat").val(),
	            	rukovodilac: $("#rukovodilac").val(),
	            	sekretar:$("#sekretar").val()
	              
	            },
	            success: function (data) {	            	  
	            	  if($.isNumeric(data)){	            		
		            	  oTable.fnAddData([
			                                   data,
			                                   $("#nazivKat").val(),			                                  
			                                   $("#rukovodilac").val(),
			                                   $("#rukovodilac option:selected").text(),
			                                   $("#sekretar").val(),
			                                   $("#sekretar option:selected").text()
			                                   ]);
		            	  ocistiPopUp();
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
	                          duration: 5,
	                          type: "warning"
	                      }); 
	            	  }
	                  

	            },
	        error: function(){
	        	alert("GRESKA");
	        }

	        }); // kraj ajax
	   }
	   
   }
   function  Proc_Update(){
	   if ($("#frmKatSif").validate().form()) {	  
		   var anSelected = fnGetSelected(oTable);
	        $.ajax({
	            url: 'izmeniKatedru.html',
	            type: 'POST',
	            data: {
	            	idKatedre:oTable.fnGetData(anSelected[0])[0].toString(),
	            	nazivKat: $("#nazivKat").val(),
	            	rukovodilac: $("#rukovodilac").val(),
	            	sekretar:$("#sekretar").val()
	              
	            },
	            success: function (data) {
	           
	            	  if(data=="IZMENJENO"){
	            	 	  oTable.fnUpdate([
	            	 	                   oTable.fnGetData(anSelected[0])[0].toString(),
	 	                                   $("#nazivKat").val(),
	 	                                   $("#rukovodilac").val(),
	 	                                   $("#rukovodilac option:selected").text(),
	 	                                   $("#sekretar").val(),
	 	                                   $("#sekretar option:selected").text()
	 	                                   ],anSelected[0]);
	                	  $("#divKatSifPOPUP").dialog('close');
	 	                 
		                  showNotification({
	                        message: data,
	                       autoClose: true,
	                       duration: 5
	                    });
	            		  
	            	  }
	            	  else{
	            	        showNotification({
		                        message: data,
		                        type:"warning",
		                       autoClose: true,
		                       duration: 5
		                    });
	            	  }
	            	
	        

	            },
	        error: function(){
	        	alert("GRESKA");
	        }

	        }); // kraj ajax
	   }
	   
   }
//   
   $('#btnSacuvaj').click(function () {
	   if(azuriranje==0){
		   Proc_Insert();
	   }
	   else{
		   Proc_Update();
	   }

   });
   $('#btnPonisti').click(function () {
	  ocistiPopUp();
   });
//   
   $('#btnChange').click(function () {
	   azuriranje=1;
	   var anSelected = fnGetSelected(oTable);
	   $("#nazivKat").attr('value', oTable.fnGetData(anSelected[0])[1].toString());
       $("#rukovodilac").attr('value',oTable.fnGetData(anSelected[0])[2].toString());
       $("#sekretar").attr('value', oTable.fnGetData(anSelected[0])[4].toString());
       otvoriPopUp();
       

   });
//   
   $('#btnDel').click(function () {
	   var anSelected = fnGetSelected(oTable);
       $.ajax({
           url: 'obrisiKatedru.html',
           type: 'POST',
           data: {
           	idKatedre:oTable.fnGetData(anSelected[0])[0].toString()             
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
                        message: "Nije moguce obrisati zbog povezanosti sa drugim tabelama",
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
	   
       $("#divKatSifPOPUP").dialog('close');
   });
//   
   

});//doc ready