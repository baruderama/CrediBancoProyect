
$('document').ready(function(){

	$('table #editButton').on('click',function(event){
		event.preventDefault();
		
		var href = $(this).attr('href');
		$.get(href, function(autor,status){
		$('#idEdit').val(autor.id);
		$('#nombreEdit').val(autor.nombre);
		$('#fechaNacimientoEdit').val(autor.fechaNacimiento);
		$('#ciudadEdit').val(autor.ciudad);
		$('#correoEdit').val(autor.correo);
		
		});
		$('#editModal').modal();
	});
});