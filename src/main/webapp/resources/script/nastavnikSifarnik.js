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
	var anSelectedTbKat ;
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
    
    var oTableTbKat;
    function crtajTbKat(){
    	  oTableTbKat = $("#tbKatedreZaNastavnika").dataTable({
    	        "bJQueryUI": true,
    	      //  "bFilter": false,
    	         "bStateSave": true,
//    	        "aoColumnDefs": [
//    	               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
//    	               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [2] }
//    	        ],
    	        "oLanguage": {
    	            "sProcessing": "Procesiranje u toku...",
    	            "sLengthMenu": "Prikazi _MENU_  ",
    	            "sZeroRecords": "Nema zapisa",
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
    	        
    	    });//kraj crtanja
    	  $('#btnDelKat').click(function () {
    			alert("OBRISI KATEDRU IZ TABELE PRIPADNOST NAS KATEDRI");

    		   });
    	  $('#btnNewKat').click(function () {
  			otvoriPopUpKatNas();

  		   });
    	  $('#btnSacuvajKat').click(function () {
    		  alert("SACUVAJ KATEDRU U TABELu PRIPADNOST NAS KATEDRI");

    		   });
    	  $('#btnOdustaniKat').click(function () {
    		  $("#divKatedraZaNastavnikPOPUP").dialog('close');
    		   });
    	   $("#tbKatedreZaNastavnika tbody tr").live('click', function (e) {       
    		   oTableTbKat.$('tr.row_selected').removeClass('row_selected');
    	        $(this).addClass('row_selected');    	        
    	        $("#btnDelKat").attr('disabled', false);
    	        
    	     
    	    }); 
    	
    }
    
    function otvoriPopUpKatNas(){
    	 var anSelected = fnGetSelected(oTable); 
 	   $("#divKatedraZaNastavnikPOPUP").dialog({ 		   
            title: "Nastavnik: "+oTable.fnGetData(anSelected[0])[1].toString(),
            width: 550,
            modal: true,
            open: function () {
                $('.error').html('');
                //$(this).parent().appendTo($('#frmLabs'));
            }
        });
 	   
    }
    
    function ajaxZaKatedre(){//popunjava tabelu za nastavnike kojoj katedri pripada
    	 var anSelected = fnGetSelected(oTable); 
    	$.ajax({
	            url: 'dajKatedreZaNastavnika.json',
	            type: 'GET',
	            data: {
	            	idNastavnika:oTable.fnGetData(anSelected[0])[0].toString()
	              
	            },
	            success: function (data) {
	            	
	            	//if(data.length>0){
	            		var tbHtml='<table id="tbKatedreZaNastavnika"><thead><tr><td>ID_Katedre</td><td>Katedra</td></tr></thead><tbody>';
	            		for(var i=0;i<data.length;i++){
	            			tbHtml+='<tr><td>'+data[i].id_Katedre +'</td><td>'+data[i].nazivKatedre +'</td></tr>';
	            		
	            		}
	            		tbHtml+='</tbody></table><div> <input type="button" value="Dodaj katedru" id="btnNewKat"  style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 150px;"/> <input type="button" value="Obrisi katedru" id="btnDelKat" disabled="disabled" style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 150px;"/> </div>'; 

	            //	}
	            //	else{
	            		//tbHtml="prazno";
	            //	}
	            
	            	$("#listaKatedriZaNastavnika").html(tbHtml);
	            	crtajTbKat();
	            	
	            },
	        error: function(){
	        	alert("GRESKA");
	        }

	        }); // kraj ajax
    }

   function fnGetSelected(oTableLocal) { return oTableLocal.$('tr.row_selected'); }

   $("#tbNastavnik tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
        ajaxZaKatedre();
        
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
	                                   "",
	                                   $("#licPrez").val(),
	                                   $("#telefon").val()
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
   function  Proc_Update(){
	   if ($("#frmNastavnik").validate().form()) {	  
		   var anSelected = fnGetSelected(oTable);
	        $.ajax({
	            url: 'izmeniNastavnika.html',
	            type: 'POST',
	            data: {
	            	idNastavnika:oTable.fnGetData(anSelected[0])[0].toString(),
	            	ime: $("#ime").val(),
	            	prezime: $("#prezime").val(),
	            	email:$("#email").val(),
	            	telefon: $("#telefon").val(),
	            	vrsta: $("#vrsta").val(),
	            	kabinet:$("#kabinet").val(),
	            	licPrez: $("#licPrez").val(),
	            	zvanje:$("#zvanje").val()
	              
	            },
	            success: function (data) {	   
	            	if(data=="IZMENJENO"){
	            //	alert(fnGetData(anSelected[0])[0].toString());
	            	  oTable.fnUpdate([
	            	                   oTable.fnGetData(anSelected[0])[0].toString(),
	                                   $("#ime").val()+" "+ $("#prezime").val(),
	                                   $("#email").val(),
	                                   $("#vrsta").val(),
	                                   $("#vrsta option:selected").text(),
	                                   $("#kabinet").val(),
	                                   $("#kabinet option:selected").text(),
	                                   $("#zvanje").val(),
	                                   $("#zvanje option:selected").text(),
	                                   "",
	                                   "",
	                                   $("#licPrez").val(),
	                                   $("#telefon").val()
	                                   ],anSelected[0]);
	            	
	            	  $("#divLabPOPUP").dialog('close');
	                 
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
	   var txt =  oTable.fnGetData(anSelected[0])[1].toString()
	   var arr = txt.split(' ');
	   $("#ime").attr('value',arr[0]);
       $("#prezime").attr('value',arr[1]);
       $("#email").attr('value', oTable.fnGetData(anSelected[0])[2].toString());
       $("#vrsta").attr('value', oTable.fnGetData(anSelected[0])[3].toString());
       $("#kabinet").attr('value',oTable.fnGetData(anSelected[0])[5].toString());
       $("#licPrez").attr('value', oTable.fnGetData(anSelected[0])[11].toString());
       $("#zvanje").attr('value', oTable.fnGetData(anSelected[0])[7].toString());
       $("#telefon").attr('value', oTable.fnGetData(anSelected[0])[12].toString());
      
       otvoriPopUp();
       

   });
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