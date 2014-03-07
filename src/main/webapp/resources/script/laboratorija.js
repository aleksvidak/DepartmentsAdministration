$(document).ready(function () {
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
        "bFilter": false,
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
   
   $('#btnNew').click(function () {
	   $("#nazivLab").attr('value', '');
       $("#selKatedre").attr('value', '');
       $("#sajtLab").attr('value', '');
       
	   $("#divLabPOPUP").dialog({
           title: "Dodavanje laboratorije",
           width: 670,
           modal: true,
           open: function () {
               $('.error').html('');
               //$(this).parent().appendTo($('#frmLabs'));
           }
       });

   });
   
   $('#btnSacuvaj').click(function () {
	   if ($("#frmLabs").validate().form()) {	  
		   
	        $.ajax({
	            url: 'dodajLab.html',
	            type: 'POST',
	            data: {
	            	nazivLab: $("#nazivLab").val(),
	            	selKatedre: $("#selKatedre").val(),
	            	sajt:$("#sajtLab").val(),
	              
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
   });
   $('#btnPonisti').click(function () {
	   $("#nazivLab").attr('value', '');
       $("#selKatedre").attr('value', '');
       $("#sajtLab").attr('value', '');

   });
   
   $('#btnChange').click(function () {
       alert("Otvori popup za menjanje");

   });
   
   $('#btnDel').click(function () {
       alert("Obrisi slog");

   });
   
   $('#btnOdustani').click(function () {
	   
       $("#divLabPOPUP").dialog('close');
   });
   
   

});//doc ready