$(document).ready(function () {
	var azuriranje=0;
	$.ajaxSetup({ cache: false });
	

    
    $("#frmNastavnik").validate({
        rules: {
        	ime: { required: true },
        	prezime: { required: true },
        	vrsta: { required: true },
        	zvanje: { required: true },
        	kabinet: { required: true },
        	email:{email:true }

        },
        messages: {
        	ime: "Unesite naziv",
        	prezime: "Odaberite katedru",
        	email:"Uneseite validan e-mail",
        	vrsta:"Izaberite vrstu",
        	zvanje:"Izaberite zvanje",
        	kabinet:"Izaberite kabinet"
        }
    });
	
	
	var anSelected ;
    var oTable = $("#tbNastavnik").dataTable({
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

   $("#tbNastavnik tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
        $("#btnChange").attr('disabled', false);
        $("#btnDel").attr('disabled', false);
        
     
    });   
   function otvoriPopUp(){
	   $("#divNastavnikPOPUP").dialog({
           title: "Nastavnik",
           width: 670,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });
	   
   }
//   
   function ocistiPopUp(){
	   $("#ime").attr('value', '');
       $("#prezime").attr('value', '');
       $("#email").attr('value', '');
       $("#telefon").attr('value', '');
       $("#vrsta").attr('value', '');
       $("#kabinet").attr('value', '');
       $("#licPrez").attr('value', '');
       $("#zvanje").attr('value', '');
	   
   }
   $('#btnNew').click(function () {
	   azuriranje=0;
	   ocistiPopUp();
       otvoriPopUp();


   });
   function Proc_Insert(){
	   if ($("#frmNastavnik").validate().form()) {	  
		   
	        $.ajax({
	            url: 'dodajNastavnika.html',
	            type: 'POST',
	            data: {
	            	ime: $("#ime").val(),
	            	prezime: $("#prezime").val(),
	            	email:$("#email").val(),
	            	telefon: $("#telefon").val(),
	            	vrsta: $("#vrsta").val(),
	            	kabinet:$("#kabinet").val(),
	            	licPrez:$("#licPrez").val(),
	            	zvanje:$("#zvanje").val()
	              
	            },
	            success: function (data) {
	            	 if($.isNumeric(data)){	
	            	  oTable.fnAddData([
	                                   data,
	                                   $("#ime").val()+" "+$("#prezime").val(),
	                                   $("#email").val(),
	                                   $("#vrsta").val(),
	                                   $("#vrsta option:selected").text(),
	                                   $("#kabinet").val(),
	                                   $("#kabinet option:selected").text(),
	                                   $("#zvanje").val(),
	                                   $("#zvanje option:selected").text(),
	                                   "",
	                                   ""
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
//   $('#btnChange').click(function () {
//	   azuriranje=1;
//	   var anSelected = fnGetSelected(oTable);
//	   $("#nazivLab").attr('value', oTable.fnGetData(anSelected[0])[1].toString());
//       $("#selKatedre").attr('value',oTable.fnGetData(anSelected[0])[2].toString());
//       $("#sajtLab").attr('value', oTable.fnGetData(anSelected[0])[4].toString());
//       otvoriPopUp();
//       
//
//   });
//   
   $('#btnDel').click(function () {
	   var anSelected = fnGetSelected(oTable);
       $.ajax({
           url: 'obrisiNastavnika.html',
           type: 'POST',
           data: {
        	   idNastanivk:oTable.fnGetData(anSelected[0])[0].toString()             
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
	   
       $("#divNastavnikPOPUP").dialog('close');
   });
   
   

});//doc ready