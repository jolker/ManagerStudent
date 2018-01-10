var _title_add = "Add User Admin Tool";
var _action_url = "/userAdminTool";
var _action_add = "add";
var _action_update = "update";
var _action_delete = "delete";
var _message = "Not empty!";
var _action = "";
$(document).ready(function() {
  $('li.userAdminTool').addClass('active');
});

function showAddModal() {
  $(".modal-title").html("<strong>" + _title_add + "</strong>");
  $("#modal_username").val("");
  $("#modal_password").val("");
  $("#modal_username").prop('disabled', false);
  $("#modal_active").prop('checked', true);
  $(".modal_items").removeClass("has-error has-feedback");
  $(".modal_items_icon").removeClass("glyphicon glyphicon-remove form-control-feedback");
  $(".modal_items_message").html("");

  _action = _action_add;
}

$('.btn-add-update-user-admin-tool').click(function() {
  var username = $.trim($("#modal_username").val());
  var password = $.trim($("#modal_password").val());
  var active = $("#modal_active").is(':checked');

  var validateInt = 0;

  // validate input
  validateInt += validateInput("container_username", username.length == 0, _message);
  if (_action === _action_add) {
    validateInt += validateInput("container_password", password.length == 0, _message);
  }

  if (validateInt > 0) {
    return
  }

  var datas = {
    "username": username,
    "password": password,
    "active": active,
    "action": _action
  }

  $.ajax({
    url: _action_url,
    type: "post",
    data: datas,
    success: function(result) {
      if (result.duplicate != "") {
        $(".container_username").addClass('has-error has-feedback');
        $(".container_username_icon").addClass("glyphicon glyphicon-remove form-control-feedback");
        $(".container_username_message").html("<span style='color: red;'> " + result.duplicate + "</span>");
      } else if (result.result === "success") {
        alert("Success");
        window.location.href = _action_url;
      } else {
        alert(result.result);
      }

    },
    error: function(e, status, error) {
      alert('Error');
    },
  });
});

// function showAddModal(id) {
//   $(".modal-title").html("Add User");
//   $("#modal_username").val("");
//   $("#modal_password").val("");
//   $("#modal_fullname").val("");
//   $("#modal_active").prop('checked', active);
//   _action = _action_add;
// }

function showUpdateModal(id) {
  $(".modal-title").html("Update User");
  var username = $.trim($("#username-" + id).html());
  var password = $.trim($("#password-" + id).html());
  var active = $("#active-" + id).is(":checked");

  $(".active_game").each(function() { // reset
    $(this).prop("checked", false);
  });

  $("#modal_username").prop('disabled', true);
  $("#modal_username").val(username);
  $("#modal_password").val(password);
  $("#modal_active").prop('checked', active);

  _action = _action_update;
}

function showDeleteModal(id) {
  var username = $.trim($("#username-" + id).html());
  $("#username-delete").html("");
  $("#username-delete").html("Username: " + username);
  $('.btn-delete').attr('username', username);
}

$(".btn-delete").click(function() {
  var username = $(this).attr('username');
  var datas = {
    "username": username,
    "action": _action_delete
  }

  $.ajax({
    url: _action_url,
    type: "post",
    data: datas,
    success: function(result) {
      if (result.result == true) {
        alert("success");
        window.location.href = _action_url;
      } else {
        alert(result.result);
      }

    },
    error: function(e, status, error) {
      alert('Error');
    },
  });
});

function validateInput(name_class, right, message) {
  var emptyInt = 0;
  if (right == true) {
    $("." + name_class).addClass('has-error has-feedback');
    $("." + name_class + "_icon").addClass("glyphicon glyphicon-remove form-control-feedback");
    $("." + name_class + "_message").html("<span style='color: red;'> " + message + "</span>");
    emptyInt = 1;
  } else {
    $("." + name_class).removeClass('has-error has-feedback');
    $("." + name_class + "_icon").removeClass("glyphicon glyphicon-remove form-control-feedback");
    $("." + name_class + "_message").html("");
  }

  return emptyInt;
}
