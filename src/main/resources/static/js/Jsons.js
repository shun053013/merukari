/**
 * 
 */
$(function(){
	

$('#bigCategory').change(function(){
	$('.middle').remove();
	var id = $('#bigCategory').val();
	$.ajax({
		type:'POST',
		url:'/middle',
		data: {id : id},
		dataType :'json'
	}).done(function(data){
		console.log(data);
		for(var i = 0;i< data.length;i++){
			$('#middle').append(' <option class = "middle" value= '+data[i].id+' >'+ data[i].name + '</option>');
			console.log(data[i]);
		}
		
	})
});

$('#middle').change(function(){
	$('.mini').remove();
	var id = $('#middle').val();
	$.ajax({
		type:'POST',
		url:'/mini'	,
		data:{id :id},
		dataType :'json'
			
	}).done(function(data){
		console.log(data);
		for(var i =0 ;i<data.length;i++){
			$('#mini').append('<option class ="mini" value= '+data[i].id+ '>' +data[i].name+'</option>');
			console.log(data[i]);
		}
		
		
	});
});  



});