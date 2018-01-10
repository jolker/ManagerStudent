var _action_url = "/support";
var _action_update_handle = "update_handle";
var _action_get_with_cursor = "get_with_cursor";
var _action_delete =  "delete";
var _current_index = 0;
var _total_record = 0;
var _total_page = 0;

var _current_id = "";
var _input_normal_border_color = "#d2d6de";
var _input_error_border_color = "red";
var _message_no_date = "No data";

$(document).ready(function(){
  $('li.support').addClass('active');
  tinymce.init({
        selector: 'textarea#email-body',
        height: 500,
        relative_urls : true,
        remove_script_host : true,
        plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table contextmenu paste code'
        ],
        toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        content_css: '//www.tinymce.com/css/codepen.min.css'
    });

});
function handle(id) {
	_current_id = id;
	var contact = $.trim($("#contact-"+id).html());
	if(contact.includes("@")) {
		resetModalEmail();
		$("#email-send-to").val(contact);
		$('#modal-send-email').modal('toggle');
	} else {
		sendEmailOrUpdateState(false); // update state only
	}
}

function resetModalEmail() {
	$("#email-send-to").val("");
	$("#email-subject").val("");
	$("#email-send-to").css('border-color', _input_normal_border_color);
	$("#email-subject").css('border-color', _input_normal_border_color);
	tinyMCE.get('email-body').setContent("");
}
function sendEmailOrUpdateState(hasSend) {
	var email_send_to = $.trim($("#email-send-to").val());
	var email_send_subject = $.trim($("#email-subject").val());
	var email_send_body = tinyMCE.get('email-body').getContent();
	if(hasSend) { // only check when contact is an email format
		var valid = 0;
		if(email_send_to.length == 0) {
			$("#email-send-to").css('border-color', _input_error_border_color);
			valid++;
		}
		if(email_send_subject.length == 0) {
			$("#email-subject").css('border-color', _input_error_border_color);
			valid ++;
		}
		if(email_send_body.length == 0) {
			alert("The email body is not empty!");
			valid ++;
		}

		if(valid > 0) {
			return;
		}
	}

	var datas = {
		"id" : _current_id,
		"has_send" : hasSend,
		"send_to" : email_send_to,
		"subject" : email_send_subject,
		"body" : email_send_body,
		"action" : _action_update_handle
	}
	$.ajax({
        url: _action_url,
        data: datas,
        type: "post",
        success: function(result){
            if(result.message == true) {
                alert("success");
                $("#id-"+_current_id).remove();
            } else {
                alert("error");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}
// update handle
$(".btn-send-email").click(function(){
	// var email_send_to = $.trim($("#email-send-to").val());
	// var email_send_subject = $.trim($("#email-subject").val());
	// var email_send_body = tinyMCE.get('email-body').getContent();
	// var valid = 0;
	// if(email_send_to.length == 0) {
	// 	$("#email-send-to").css('border-color', _input_error_border_color);
	// 	valid++;
	// }
	// if(email_send_subject.length == 0) {
	// 	$("#email-subject").css('border-color', _input_error_border_color);
	// 	valid ++;
	// }
	// if(email_send_body.length == 0) {
	// 	alert("The email body is not empty!");
	// 	valid ++;
	// }

	// if(valid > 0) {
	// 	return;
	// }

	// var datas = {
	// 	"id" : _current_id,
	// 	"send_to" : email_send_to,
	// 	"subject" : email_send_subject,
	// 	"body" : email_send_body,
	// 	"action" : _action_update_handle
	// }
	// $.ajax({
 //        url: _action_url,
 //        data: datas,
 //        type: "post",
 //        success: function(result){
 //            if(result.message == true) {
 //                alert("success");
 //                $("#id-"+_current_id).remove();
 //            } else {
 //                alert("error");
 //            }
 //        },
 //        error: function(e) {
 //            alert('error: ');
 //        },
 //    });

 	sendEmailOrUpdateState(true); // send email and update state
});

$(".btn-getFeedback").click(function(){
 	_current_index = 0;
 	updateContent();
});

function next() {
	if(_current_index < _total_page) {
		++_current_index;
		updateContent();
	}
	disabledNextAndPrevious();
}

function previous() {
	if(_current_index>0) {
		--_current_index;
		updateContent();
	}
	disabledNextAndPrevious();
}

function updateContent() {
	var current_state = $("#select-state").val();
	if(current_state == undefined || current_state == "") {
		alert("Select state!");
		return;
	}
	var datas = {
		"index" : _current_index,
		"state" : current_state,
		"action": _action_get_with_cursor
	}
	$("#support-body").html(""); // reset table
	$.ajax({
        url: _action_url,
        data: datas,
        type: "post",
        success: function(result){
        	_total_record = result.total_record;
        	_total_page = result.total_page;
        	$("#txt-total-page").html("<span>"+(_current_index+1) + " of "+_total_page+" pages</span>");
        	disabledNextAndPrevious();
            var feedback = checkUndefined(result.feedback)? JSON.parse(result.feedback) : null;
            if(feedback!=null && feedback.length>0) {
                for(var i=0; i<feedback.length; i++) {
                	$("#support-body").append(addRow(feedback[i]));
                }
            } else {
                $("#support-body").append("<tr><td colspan='11' align='center'>"+_message_no_date+"</td></tr>");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

function disabledNextAndPrevious() {
	if(_current_index < _total_page-1) {
		$(".btn-next").attr('disabled', false);
	} else {
		$(".btn-next").attr('disabled', true);
	}

	if(_current_index > 0) {
		$(".btn-previous").attr('disabled', false);
	} else {
		$(".btn-previous").attr('disabled', true);
	}
}

function addRow(feedback) {
	var retval = "";
	retval += "<tr id='id-"+feedback.id+"'>";
	retval += "<td>"+feedback.id+"</td>";
	retval += "<td id='username-"+feedback.id+"'>"+feedback.user+"</td>";
	retval += "<td>"+feedback.type+"</td>";
	retval += "<td>"+feedback.gameName+"</td>";
	retval += "<td>"+feedback.timeError+"</td>";
	retval += "<td>";
	if(feedback.state==1) {
		retval += "<span class='glyphicon glyphicon-ok' id='status-finish' title='true'></span>";
		retval += "</td>";
		retval += "<td id='contact-"+feedback.id+"'>"+feedback.contact+"</td>";
		if(feedback.description==undefined || feedback.description=="") {
			retval += "<td>-</td>";
		} else {
			retval += "<td>"+feedback.description+"</td>";
		}
		retval += "<td>"+feedback.handledBy+"</td>";
		retval += "<td>"+feedback.timeHandle+"</td>";
		retval += "<td>";
		retval += "<button class='btn btn-warning' data-toggle='modal' href='#modal-delete' onclick='showDeleteModal("+feedback.id+")' data-id='"+feedback.id+"'>Delete</button>";
		retval += "</td>";
		retval += "<td></td>";
	} else {
		retval += "<span class='glyphicon glyphicon-refresh' id='status-waiting' title='false'></span>";
		retval += "</td>";

		retval += "<td id='contact-"+feedback.id+"'>"+feedback.contact+"</td>";
		if(feedback.description==undefined || feedback.description=="") {
			retval += "<td>-</td>";
		} else {
			retval += "<td>"+feedback.description+"</td>";
		}

		retval += "<td>-</td>";
		retval += "<td>-</td>";
		retval += "<td>";
		retval += "<button class='btn btn-warning' data-toggle='modal' href='#modal-delete' onclick='showDeleteModal("+feedback.id+")' data-id='"+feedback.id+"'>Delete</button>";
		retval += "</td>";
		if(feedback.contact.includes("@")) {
			retval += "<td>";
			retval += "<button class='btn btn-info btn-handle' data-id='"+feedback.id+"' data-send-email='true' onclick='handle("+feedback.id+")''>Handle &nbsp; <span class='glyphicon glyphicon-envelope'></span></button>";
			retval += "</td>";
		} else {
			retval += "<td>";
			retval += "<button class='btn btn-info btn-handle' data-id='"+feedback.id+"' onclick='handle("+feedback.id+")''>Done &nbsp; <span class='glyphicon glyphicon-earphone'></span></button>";
			retval += "</td>";
		}
	}
	
	retval += "</tr>";

	return retval;
}

function showDeleteModal(id) {
    $("#username").html("");
    var name = $.trim($("td#username-"+id).html());
    $("#username").html(name);
    $(".btn-delete-modal").attr('data-id', id);
}

$(".btn-delete-modal").click(function(){
	var id = $(this).attr('data-id');
	var datas = {
		"id" : id,
		"action" : _action_delete
	}
	$.ajax({
        url: _action_url,
        data: datas,
        type: "post",
        success: function(result){
            if(result.message==true) {
            	alert("Success");
            	$("#id-"+id).remove();
            	$('#modal-delete').modal('toggle');
            } else {
            	alert("Failed");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
});

function checkUndefined(variable) {
    return (typeof variable !== undefined);
}