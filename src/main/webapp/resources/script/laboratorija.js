$(document).ready(function () {
	
    $.ajax({
        url: 'laboratorijaListaKatedri.json',
        type: 'GET',
        success: function (data) {
            alert(data[0].nazivKatedre);

        },
        error: function(data) {
        	alert("Greska AJAX");
			
		}
    

    }); // kraj ajax
	
	
	var anSelected ;
    var oTable = $("#tbLab").dataTable({
        "bJQueryUI": true,
        "bFilter": false,
        // "bStateSave": true,
        // "aaSorting": [[9, "desc"]],
        //"aoColumnDefs": [
        //       { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [0] },
        //       { "bSearchable": false, "bSortable": false, "bVisible": false, "aTargets": [1] }
        //],
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
	   $("#divLabPOPUP").dialog({
           title: "Dodavanje laboratorije",
           width: 670,
           modal: true,
           open: function () {
               $('.error').html('');
               // $(this).parent().appendTo($('#podaci'));
           }
       });

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