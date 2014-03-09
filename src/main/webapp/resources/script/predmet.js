$(document).ready(function () {
	var azuriranje=0;
	$.ajaxSetup({ cache: false });
	
    $("#frmPredmet").validate({
        rules: {
        	nazivPred: { required: true },
        	brBodova: { required: true, number: true, min: 0},
        	semestar: { required: true },
        	brCasovaPredavanja: { required: true, number: true, min: 0 },
        	brCasovaVezbi: { required: true, number: true, min: 0 },
        	brCasovaOstalo: { required: true, number: true, min: 0 }

        },
        messages: {
        	nazivPred: "Unesite predmeta",
        	brBodova:{
        		required:"Unesite broj bodova",
        		number:"Mora biti brojcana vrednost",
        		min:"Mora biti pozitivan broj"},
        	semestar: "Unesite naziv",
        	brCasovaPredavanja: {
        		required:"Unesite broj casova",
        		number:"Mora biti brojcana vrednost",
        		min:"Mora biti pozitivan broj"},
        	brCasovaVezbi: {
        		required:"Unesite broj casova",
        		number:"Mora biti brojcana vrednost",
        		min:"Mora biti pozitivan broj"},
        	brCasovaOstalo: {
        		required:"Unesite broj casova",
        		number:"Mora biti brojcana vrednost",
        		min:"Mora biti pozitivan broj"}
     
        }
    });
	
	
	var anSelected ;
    var oTable = $("#tbPredmet").dataTable({
        "bJQueryUI": true,
      //  "bFilter": false,
         "bStateSave": true,
//        "aoColumnDefs": [
//               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
//               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [2] }
//        ],
        "oLanguage": {
            "sProcessing": "Procesiranje u toku...",
            "sLengthMenu": "Prikazi _MENU_  ",

            "sInfo": "Prikaz _START_ do _END_ od ukupno _TOTAL_ elemenata",
            "sInfoEmpty": "Prikaz 0 od 0 do ukupno 0 elemenata",
            "sZeroRecords": "Nije pronadjen nijedan rezultat",
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

   $("#tbPredmet tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
        $("#btnChange").attr('disabled', false);
        $("#btnDel").attr('disabled', false);
        
     
    });   
   function otvoriPopUp(){
	   $("#divPredmetPOPUP").dialog({
           title: "Dodavanje predmeta",
           width: 750,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });
	   
   }
   function ocistiPopUp(){
	   $("#nazivPred").attr('value', '');
       $("#brBodova").attr('value', '');
       $("#semestar").attr('value', '');
       $("#brCasovaPredavanja").attr('value', '');
       $("#brCasovaVezbi").attr('value', '');
       $("#brCasovaOstalo").attr('value', '');
	   
   }
   
   $('#btnNew').click(function () {
	   azuriranje=0;
	   ocistiPopUp();
       otvoriPopUp();


   });
   function Proc_Insert(){
	   if ($("#frmPredmet").validate().form()) {
	        $.ajax({
	            url: 'dodajPredmet.html',
	            type: 'POST',
	            data: {
	            	nazivPred: $("#nazivPred").val(),
	            	brBodova: $("#brBodova").val(),
	            	semestar:$("#semestar").val(),
	            	brCasovaPredavanja:$("#brCasovaPredavanja").val(),
	            	brCasovaVezbi:$("#brCasovaVezbi").val(),
	            	brCasovaOstalo:$("#brCasovaOstalo").val()
	              
	            },
	            success: function (data) {
	            	
	            	  oTable.fnAddData([
	                                   data,
	                                   $("#nazivPred").val(),
	                                   $("#brBodova").val(),	                                   
	                                   $("#semestar").val(),
	                                   $("#brCasovaPredavanja").val(),
	                                   $("#brCasovaVezbi").val(),
	                                   $("#brCasovaOstalo").val()
	                                   ]);
	            	  
	            	  ocistiPopUp();
	             	  
	           		    showNotification({
	                           message: "SACUVANO",
	                           autoClose: true,
	                           duration: 5
	                       });
	           	   
	           	 

	            },
	        error: function(){
	        	alert("GRESKA");
	        }

	        }); // kraj ajax
	   }
	   
   }
   
   function  Proc_Update(){
	   if ($("#frmPredmet").validate().form()) {	  
		   var anSelected = fnGetSelected(oTable);
	        $.ajax({
	            url: 'izmeniPredmet.html',
	            type: 'POST',
	            data: {
	            	idPredmeta:oTable.fnGetData(anSelected[0])[0].toString(),
	            	nazivPred: $("#nazivPred").val(),
	            	brBodova: $("#brBodova").val(),
	            	semestar:$("#semestar").val(),
	            	brCasovaPredavanja:$("#brCasovaPredavanja").val(),
	            	brCasovaVezbi:$("#brCasovaVezbi").val(),
	            	brCasovaOstalo:$("#brCasovaOstalo").val()
	              
	            },
	            success: function (data) {	   
	            //	alert(fnGetData(anSelected[0])[0].toString());
	            	  oTable.fnUpdate([
	            	                  oTable.fnGetData(anSelected[0])[0].toString(),
	            	                   $("#nazivPred").val(),
	                                   $("#brBodova").val(),	                                   
	                                   $("#semestar").val(),
	                                   $("#brCasovaPredavanja").val(),
	                                   $("#brCasovaVezbi").val(),
	                                   $("#brCasovaOstalo").val()
	                                   ],anSelected[0]);
	            	
	            	  $("#divPredmetPOPUP").dialog('close');
	                 
	             	   if(data=="IZMENJENO"){
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
	   }
	   
   }
   
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
	   $("#nazivPred").attr('value', oTable.fnGetData(anSelected[0])[1].toString());
       $("#brBodova").attr('value',oTable.fnGetData(anSelected[0])[2].toString());
       $("#semestar").attr('value', oTable.fnGetData(anSelected[0])[3].toString());
	   $("#brCasovaPredavanja").attr('value', oTable.fnGetData(anSelected[0])[4].toString());
       $("#brCasovaVezbi").attr('value',oTable.fnGetData(anSelected[0])[5].toString());
       $("#brCasovaOstalo").attr('value', oTable.fnGetData(anSelected[0])[6].toString());
       otvoriPopUp();
       
   });
//   
   $('#btnDel').click(function () {
	   var anSelected = fnGetSelected(oTable);
       $.ajax({
           url: 'obrisiPredmet.html',
           type: 'POST',
           data: {
           	idPredmeta:oTable.fnGetData(anSelected[0])[0].toString()             
           },
           success: function (data) {	              
        	   oTable.fnDeleteRow(anSelected[0]);
        	   if(data=="OBRISANO"){
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
	   
       $("#divPredmetPOPUP").dialog('close');
   });
   
   

});//doc ready