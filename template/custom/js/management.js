var url = "http://api.nivi.vn/admin/reload?type=";
var _action_url = "/management";
var action = "reload";
var _action_query = "query";
var _action_update = "update"
var _action_update_user_info = "update_user_info";
var _action_get_password = "get_password";
var _action_reset_password = "reset_password";
var _action_update_card_provider = "update_card_provider";

$(document).ready(function(){
  $('li.management').addClass('active');
});

function reload() {
    var select_management = $.trim($("#select-management").val());
    // var call_url = url + select_management;
    // window.location.href = call_url;
    var datas = {
    	"action" : action,
    	"name" : select_management
    }
    $(".loading").css("display", "block");
    $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          $(".loading").css("display", "none");
          alert(result.result);
        },
        error: function(e, status, error) {
          $(".loading").css("display", "none");
          alert('error: ');
        },
      });
}

$(".btn-execute-query").click(function(){
  excute(_action_query);
});

$(".btn-execute-update").click(function(){
  excute(_action_update);
});

function excute(id) {
  var query = "";
  var config = "";
  var action = "";

  if(id === _action_query) {
    query = $("#excuteQuery").val();
    config = $("#config-query").val();
    action = _action_query;
  } else {
    query = $("#excuteUpdate").val();
    config = $("#config-update").val();
    action = _action_update;
  }

  var datas = {
    "query" : query,
    "config" : config,
    "action" : action
  }

  $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          var message = result.message;
          if(message == true) {
            alert("OK");
          } else {
            alert(message);
          }
        },
        error: function(e, status, error) {
          alert('error: ');
        },
      });
}

function updateUserInfo() {
  $("#message_user_info").html(""); // reset
  $("#message_user_info").html("<img src='img/loading.gif' width='50' alt=''>");
  var nct_id = $.trim($("#update_user_info_nct_id").val());
  var email = $.trim($("#update_user_info_email").val());
  var error_message = "";
  if(nct_id.length == 0) {
    error_message = "NCT ID not empty.";
  }
  if(email.length == 0) {
    error_message += " Email not empty"
  } else if(!isValidEmailAddress(email)) {
    error_message += " Wrong email format."
  }

  if(error_message.length > 0) {
    $("#message_user_info").html("<span style='color: red'>"+error_message+" </span>");
    return;
  } else {
    $("#message_user_info").html("");
  }

  var datas = {
    "nct_id" : nct_id,
    "email" : email,
    "action" : _action_update_user_info
  }
  $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          $("#message_user_info").html("");
          $("#message_user_info").html(result);
        },
        error: function(e, status, error) {
          alert('error: ');
        },
      });
}

function getPassword() {
  $("#message_get_password").html("");
  $("#message_get_password").html("<img src='img/loading.gif' width='50' alt=''>");
  var phone = $.trim($("#get_password_phone").val());
  var error_message = "";
  if(phone.length == 0) {
    error_message = "Phone not empty.";
  }

  if(error_message.length > 0) {
    $("#message_get_password").html("<span style='color: red'>"+error_message+" </span>");
    return;
  } else {
    $("#message_get_password").html("");
  }

  var datas = {
    "phone" : phone,
    "action" : _action_get_password
  }
  $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          $("#message_get_password").html("");
          $("#message_get_password").html(result);
        },
        error: function(e, status, error) {
          alert('error: ');
        },
      });
}

function resetPassword() {
  $("#message_reset_password").html("");
  $("#message_reset_password").html("<img src='img/loading.gif' width='50' alt=''>");
  var email = $.trim($("#reset_password_email").val());
  var error_message = "";
  if(email.length == 0) {
    error_message = "Email not empty.";
  }

  if(error_message.length > 0) {
    $("#message_reset_password").html("<span style='color: red'>"+error_message+" </span>");
  } else if(!isValidEmailAddress(email)) {
    error_message += " Wrong email format."
  }

  if(error_message.length > 0) {
    $("#message_reset_password").html("<span style='color: red'>"+error_message+" </span>");
    return;
  } else {
    $("#message_reset_password").html("");
  }

  var datas = {
    "email" : email,
    "action" : _action_reset_password
  }
  $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          $("#message_reset_password").html("");
          $("#message_reset_password").html(result);
        },
        error: function(e, status, error) {
          alert('error: ');
        },
      });
}

function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
};

function updateCardProvider() {
  var active_index = $('input[name=provider]:checked').val();
  var datas = {
    "active_index" : active_index,
    "action" : _action_update_card_provider
  }
  $.ajax({
        url: _action_url,
        type: "post",
        data: datas,
        success: function(result){
          var message = result.message;
          if(message == true) {
            alert("OK");
          } else {
            alert(message);
          }
        },
        error: function(e, status, error) {
          alert('error: ');
        },
      });
}