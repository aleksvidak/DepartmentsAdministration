$(document).ready(function () {
	var azuriranje=0;
	$.ajaxSetup({ cache: false });
    $.ajax({
        url: 'laboratorijaListaKatedri.json',
        type: 'GET',
        success: function (data) {
        	
            var html='<select id=selKatedre name=selKatedre><option value="">--Odaberite nesto--</option>';
            for(var i=0;i<data.length;i++){
            	html+='<option value="'+ data[i].id_Katedre + '">' 
            	+ data[i].nazivKatedre + '</option>';
            }
           html+="</select>";
           $("#katedraLab").html(html);
          
      

        },
        error: function(data) {
        	alert("Greska AJAX");
			
		}
    }); // kraj ajax
    
    
    $("#frmLabs").validate({
        rules: {
        	nazivLab: { required: true },
        	selKatedre: { required: true }

        },
        messages: {
        	nazivLab: "Unesite naziv",
        	selKatedre: "Odaberite katedru"
     
        }
    });
	
	
	var anSelected ;
    var oTable = $("#tbLab").dataTable({
        "bJQueryUI": true,
      //  "bFilter": false,
         "bStateSave": true,
        "aoColumnDefs": [
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
               { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [2] }
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

   $("#tbLab tbody tr").live('click', function (e) {       
        oTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        
        $("#btnChange").attr('disabled', false);
        $("#btnDel").attr('disabled', false);
        
     
    });   
   function otvoriPopUp(){
	   $("#divLabPOPUP").dialog({
           title: "Dodavanje laboratorije",
           width: 670,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });
	   
   }
   
   $('#btnNew').click(function () {
	   azuriranje=0;
	   $("#nazivLab").attr('value', '');
       $("#selKatedre").attr('value', '');
       $("#sajtLab").attr('value', '');
       otvoriPopUp();


   });
   function Proc_Insert(){
	   if ($("#frmLabs").validate().form()) {	  
		   
	        $.ajax({
	            url: 'dodajLab.html',
	            type: 'POST',
	            data: {
	            	nazivLab: $("#nazivLab").val(),
	            	selKatedre: $("#selKatedre").val(),
	            	sajt:$("#sajtLab").val()
	              
	            },
	            success: function (data) {
	            	  oTable.fnAddData([
	                                   data,
	                                   $("#nazivLab").val(),
	                                   $("#selKatedre").val(),
	                                   $("#selKatedre option:selected").text(),//naziv katedre pokupi iz selektovanog polja
	                                   $("#sajtLab").val()
	                                   ]);
	            	  $("#nazivLab").attr('value', '');
	                  $("#selKatedre").attr('value', '');
	                  $("#sajtLab").attr('value', '');
	                  showNotification({
                         message: 'Sacuvano',
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
	   if ($("#frmLabs").validate().form()) {	  
		   var anSelected = fnGetSelected(oTable);
	        $.ajax({
	            url: 'izmeniLab.html',
	            type: 'POST',
	            data: {
	            	id_Lab:oTable.fnGetData(anSelected[0])[0].toString(),
	            	nazivLab: $("#nazivLab").val(),
	            	selKatedre: $("#selKatedre").val(),
	            	sajt:$("#sajtLab").val(),
	              
	            },
	            success: function (data) {	   
	            //	alert(fnGetData(anSelected[0])[0].toString());
	            	  oTable.fnUpdate([
	            	                  oTable.fnGetData(anSelected[0])[0].toString(),
	                                   $("#nazivLab").val(),
	                                   $("#selKatedre").val(),
	                                   $("#selKatedre option:selected").text(),
	                                   $("#sajtLab").val()
	                                   ],anSelected[0]);
	            	
	            	  $("#divLabPOPUP").dialog('close');
	                 
	                  showNotification({
                        message: data,
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
   
   $('#btnSacuvaj').click(function () {
	   if(azuriranje==0){
		   Proc_Insert();
	   }
	   else{
		   Proc_Update();
	   }

   });
   $('#btnPonisti').click(function () {
	   $("#nazivLab").attr('value', '');
       $("#selKatedre").attr('value', '');
       $("#sajtLab").attr('value', '');

   });
   
   $('#btnChange').click(function () {
	   azuriranje=1;
	   var anSelected = fnGetSelected(oTable);
	   $("#nazivLab").attr('value', oTable.fnGetData(anSelected[0])[1].toString());
       $("#selKatedre").attr('value',oTable.fnGetData(anSelected[0])[2].toString());
       $("#sajtLab").attr('value', oTable.fnGetData(anSelected[0])[4].toString());
       otvoriPopUp();
       

   });
   
   $('#btnDel').click(function () {
	   var anSelected = fnGetSelected(oTable);
       $.ajax({
           url: 'obrisiLab.html',
           type: 'POST',
           data: {
           	id_Lab:oTable.fnGetData(anSelected[0])[0].toString()             
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
   
   $('#btnOdustani').click(function () {
	   
       $("#divLabPOPUP").dialog('close');
   });
   
   

});//doc ready