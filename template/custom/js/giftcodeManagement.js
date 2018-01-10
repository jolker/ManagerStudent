var _url_action = "/giftcodeManagement";
var _action_show = "show";
var _action_update = "update";
var _action_add = "add";
var _action_import_gift_code = "import_gift_code";
var _message = "Select game";

$(document).ready(function(){
	$('li.giftcode').addClass('active');
});

function show() {
	$("#contaner-result").hide();
	var gameId = $("#select-game option:selected").val();
	if(gameId == 0) {
		return;
	}
	var datas = {
		"gameId" : gameId,
		"action" : _action_show
	}

	$.ajax({
        url: _url_action,
        data: datas,
        success: function(result){
            if(result.message == true) {
            	var infor = checkUndefined(result.infor)? JSON.parse(result.infor) : null;
            	if(infor != null) {
            		$("#contaner-result").show();
            		$("#table-giftcode-management").html(addRow(infor));
            	} else {
            		alert("Infor: Null");
            	}
            } else {
                alert(result.message);
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

function update(id) {
	var gameId = $("#select-game option:selected").val();
	if(gameId == 0) {
		return;
	}
	var value_button_gift_code = $("#button_gift_code").is(":checked")? 1 : 0;
	var datas = {
		"id" : id,
		"gameId" : gameId,
		"value" : value_button_gift_code,
		"action" : _action_update
	}

	$.ajax({
        url: _url_action,
        data: datas,
        success: function(result){
            if(result.message == true) {
            	alert("success");
            } else {
                alert(result.message);
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

function add() {
	var gameId = $("#select-game option:selected").val();
	if(gameId == 0) {
		return;
	}

	var value = $("#modal_show").is(":checked") ? 1 : 0;

	var datas = {
		"gameId" : gameId,
		"value" : value,
		"action" : _action_add
	}

	$.ajax({
        url: _url_action,
        data: datas,
        success: function(result){
            if(result.message == true) {
            	alert("success");
            } else {
                alert(result.message);
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

$(".btn-add").click(function(){
	var gameId = $("#select-game option:selected").val();
	if(gameId == 0) {
		$("#message").html("<span style='color: red'>"+_message+"</span>");
	} else {
		$("#message").html("");
		$('#modal-add').modal('show'); 
	}
});

function addRow(infor) {
	var retval = "";
	retval += "<tr>";
    retval += "<td>"+infor.id+"</td>";
    retval += "<td>"+infor.name+"</td>";
    retval += "<td>";
    retval += "<div class='checkbox'>";
    retval += "<label>";
    if(infor.value === "1") {
    	retval += "<input type='checkbox' checked='true' id='button_gift_code'>";
    } else {
    	retval += "<input type='checkbox' id='button_gift_code' >";
    }
    retval += "Show";
    retval += "</label>";
    retval += "</div>";
    retval += "</td>";
    retval += "<td>";
    retval += "<button class='btn btn-success' onclick='update("+infor.id+")'>Update</button>";
    retval += "</td>";
    retval += "</tr>";

    return retval;
}

function checkUndefined(variable) {
   	return (typeof variable !== undefined);
}

$("#importGiftCode").click(function(e){
    e.preventDefault();

    var gameId = $("#select-game-id-import option:selected").val();
    if(gameId == 0) {
        alert("Select game");
        return;
    }
    var data1 = {
        "gameId" : gameId,
        "action" : _action_import_gift_code
    }

    var fileName = $("#import_file").val();
    if(!validationDataType(fileName)) {
        return;
    }
    var datas = new FormData();
    jQuery.each(jQuery('#import_file')[0].files, function(i, file) {
        datas.append('file-'+i, file);
    });

    datas.append('gameId', gameId);
    datas.append('action', _action_import_gift_code);
    
    $.ajax({
        url: _url_action,
        data: datas,
        type: "post",
        async: false,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function(result){
            
        },
        error: function(e) {
            alert('error: ');
        },
    });
});

function validationDataType(name) {
    switch(name.substring(name.lastIndexOf('.') + 1).toLowerCase()){
        case 'xlsx':
            return true;
        default:
            alert("Must type *.xlxs");
            return false;
    }
}