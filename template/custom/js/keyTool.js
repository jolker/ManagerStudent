var _action_url = "/keyTool"

$(document).ready(function(){
  $('li.keyTool').addClass('active');
});

$("#upload_apk_file").click(function(){
	$(".command-message").html("");
    $("#loading-image").show();
	var fileName = $("#fileName").val();
	if(!validationDataType(fileName)) {
		alert("Must apk file!");
        return;
    }
	var datas = new FormData();
    jQuery.each(jQuery('#fileName')[0].files, function(i, file) {
        datas.append('file-'+i, file);
    });

	$.ajax({
        url: _action_url,
        data: datas,
        type: "post",
        async: false,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        beforeSend: function() {
	        $('#loading-image').show();
	    },
        success: function(result){
        	$(".command-message").show();
        	$(".command-message").html("<span>"+result.command_message+"</span>");
        },
        error: function(e) {
            alert('error: ');
        },
    });
    $("#loading-image").hide();
});

function validationDataType(name) {
    return name.substring(name.lastIndexOf('.') + 1).toLowerCase() == "apk";
}