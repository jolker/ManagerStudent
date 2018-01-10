var _current_action = "";
var _action_url = "/listStudent"
var _action_url_upload = "/uploadImage"
var _action_url_import = "/importFile"
var _add = "add";
var _update = "update";
var _delete = "delete";
var _import = "import";
var _title_add_user = "Add a new Student";
var _title_update_user = "Update Student";
var _message_not_empty = "Not empty";
var _message_must_number = "Must number";

$(document).ready(function() {
  $('li.listStudent').addClass('active');
});

// show popup import excel file
function showImportModal() {}

function showAddModal() { // reset modal with id: modal-id-add-update
  _current_action = _add;
  // $('#id_modal_game_id').css('display', 'none');
  $(".modal-title").html("<strong>" + _title_add_user + "</strong>");
  $(".btn-add-update-game-key").text('Save');

  $("#modal_student_code").prop('disabled', false);

  $("#modal_student_code").val("");
  $("#modal_student_class").val("");
  $("#modal_start_course").val("");
  $("#modal_number_course").val("");
  $("#modal_last_name").val("");
  $("#modal_first_name").val("");
  $("#modal_sex").val("");
  $("#modal_phone").val("");
  $("#modal_birthday").val("");
  $("#modal_company").val("");
  $("#modal_email").val("");
  $("#modal_position_class").val("");
  $("#modal_presenter").val("");
  $("#modal_position_presenter").val("");
  $("#modal_department").val("");
  $("#modal_address").val("");
  $("#modal_description").val("");
  $("#modal_area").val("");

  $("#modal_test").prop('checked', false);
  $("#modal_icon").val("");
  // $(".upload").prop('disabled', true);

  $('.modal_items_message').each(function() {
    $(this).html("");
  });

  $('.modal_items_icon').each(function() {
    $(this).removeClass("glyphicon glyphicon-remove form-control-feedback");
  });

  $('.modal_items').each(function() {
    $(this).removeClass('has-error has-feedback');
  });
};

function showUpdateModal(id) { // show content user who was clicked to update

  _current_action = _update;
  $('#id_modal_student_code').css('display', 'block');
  $(".modal-title").html("<strong>" + _title_update_user + "</strong>");
  $("#modal_student_code").prop('disabled', true);
  $(".upload").prop('disabled', true);

  var student_code = $.trim($('#student_code_' + id).val());
  var student_class = $.trim($('#student_class_' + id).val());
  var start_course = $.trim($('#start_course_' + id).val());
  var number_course = $.trim($('#number_course_' + id).val());
  var last_name = $.trim($('#last_name_' + id).val());
  var first_name = $.trim($('#first_name_' + id).val());
  var sex = $.trim($('#sex_' + id).val());
  var phone = $.trim($('#phone_' + id).val());
  var birthday = $.trim($('#birthday_' + id).val());
  var company = $.trim($('#company_' + id).val());
  var email = $.trim($('#email_' + id).val());
  var position_class = $.trim($('#position_class_' + id).val());
  var presenter = $.trim($('#presenter_' + id).val());
  var position_presenter = $.trim($('#position_presenter_' + id).val());
  var department = $.trim($('#department_' + id).val());
  var address = $.trim($('#address_' + id).val());
  var description = $.trim($('#description_' + id).val());
  var area = $.trim($('#area_' + id).val());

  var icon = $.trim($("#image_profile_" + id).attr('src'));

  $("#modal_student_code").val(student_code);
  $("#modal_student_class").val(student_class);
  $("#modal_start_course").val(start_course);
  $("#modal_number_course").val(number_course);
  $("#modal_last_name").val(last_name);
  $("#modal_first_name").val(first_name);

  $('#sexForm input').removeAttr('checked');
  $("input[name=modal_sex][value=" + sex + "]", '#sexForm').prop('checked', true);

  $("#modal_phone").val(phone);
  $("#modal_birthday").val(birthday);
  $("#modal_company").val(company);
  $("#modal_email").val(email);
  $("#modal_position_class").val(position_class);
  $("#modal_presenter").val(presenter);
  $("#modal_position_presenter").val(position_presenter);
  $("#modal_department").val(department);
  $("#modal_address").val(address);
  $("#modal_description").val(description);
  $("#modal_area").val(area);

  $("#modal_icon").val(icon);

  $('.btn-add-update-game-key').attr('gameId', id);
};

// ajax Add or Update game key
$(".btn-add-update-game-key").click(function() {
  var student_code = $.trim($('#modal_student_code').val());
  var student_class = $.trim($('#modal_student_class').val());
  var start_course = $.trim($('#modal_start_course').val());
  var number_course = $.trim($('#modal_number_course').val());
  var last_name = $.trim($('#modal_last_name').val());
  var first_name = $.trim($('#modal_first_name').val());
  var sex = $('input[name=modal_sex]:checked', '#sexForm').val();
  var phone = $.trim($('#modal_phone').val());
  var birthday = $.trim($('#modal_birthday').val());
  var day_birth = birthday.split("-")[2];
  var month_birth = birthday.split("-")[1];
  var company = $.trim($('#modal_company').val());
  var email = $.trim($('#modal_email').val());
  var position_class = $.trim($('#modal_position_class').val());
  var presenter = $.trim($('#modal_presenter').val());
  var position_presenter = $.trim($('#modal_position_presenter').val());
  var department = $.trim($('#modal_department').val());
  var address = $.trim($('#modal_address').val());
  var description = $.trim($('#modal_description').val());
  var area = $.trim($('#modal_area').val());

  var icon = $.trim($("#modal_icon").val());
  var validateInt = 0;

  // // validate input
  // if (_current_action === _update) {
  //   var id = $(this).attr('gameId');
  //   validateInt += validateInput("container_game_id", !$.isNumeric(game_id), _message_must_number);
  //
  // } else {
  //   var id = game_id;
  // }
  // validateInt += validateInput("container_name", name.length == 0, _message_not_empty);

  if (validateInt > 0) { // one of the input are empty, this action will be return
    return;
  }

  var action = _current_action;
  var datas = {
    student_code: student_code,
    student_class: student_class,
    start_course: start_course,
    last_name: last_name,
    first_name: first_name,
    sex: sex,
    phone: phone,
    birthday: birthday,
    day_birth: day_birth,
    month_birth: month_birth,
    company: company,
    email: email,
    position_class: position_class,
    presenter: presenter,
    position_presenter: position_presenter,
    department: department,
    address: address,
    description: description,
    area: area,
    image_profile: icon
  };
  $.ajax({
    url: _action_url,
    data: {
      action: _current_action,
      student_json: JSON.stringify(datas) // look here!
    },
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    success: function(result) {
      var message = checkUndefined(result.success) ? result.success : null;

      if (message == true) {
        // addOrUpdateRow(gameKey, action);
        alert("success");
        $('#modal-id-add-update').modal('toggle'); // hide modal if adding successful
        window.location.href = _action_url;
      } else {
        alert(message);
      }
    },
    error: function(e) {
      alert('Error');
    },
  });
});

// Delete game key

function showDeleteModal(id) {
  $('#modal-game-name').html("");
  var name = $('#first_name_' + id).val();
  $('.btn-delete-game-key').attr('gameId', id);
  $('#modal-game-name').html(name);
}
// ajax delete game key
$(".btn-delete-game-key").click(function() {
  var id = $(this).attr('gameId');
  console.log(id);
  var datas = {
    'id': id,
    'action': _delete
  }

  $.ajax({
    url: _action_url,
    data: datas,
    success: function(result) {
      var message = checkUndefined(result.success) ? result.success : null;
      if (message == true) {
        alert("success");
        window.location.href = _action_url;
      } else {
        alert(message);
      }
    },
    error: function(e, x, y) {
      alert('Error');
    },
  });

});

function checkUndefined(variable) {
  return (typeof variable !== "undefined");
}

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

$(".upload").click(function() {
  var fileName = $("#fileName").val();
  if (!validationDataType(fileName)) {
    return;
  }
  var datas = new FormData();
  jQuery.each(jQuery('#fileName')[0].files, function(i, file) {
    datas.append('file-' + i, file);
  });
  $("#modal_icon").val("");
  $(".message").html("");

  $.ajax({
    url: _action_url_upload,
    data: datas,
    type: "post",
    async: false,
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    success: function(result) {
      var url = JSON.parse(result.url_image);
      if (url.length > 0) {
        $("#modal_icon").css('display', 'block');
        $("#modal_icon").val(url);
      } else {
        $(".message").html("error");
      }
      // $(".upload").prop('disabled', true);
    },
    error: function(e) {
      alert('error: ');
    },
  });
});
$("#fileName").change(function() {
  var fileName = $(this).val();
  validationDataType(fileName);
});

function validationDataType(name) {
  switch (name.substring(name.lastIndexOf('.') + 1).toLowerCase()) {
    case 'gif':
    case 'jpg':
    case 'png':
      $(".upload").prop('disabled', false);
      $(".message-upload-image").html("");
      return true;
    default:
      $(".upload").prop('disabled', true);
      $(".message-upload-image").html("This file is not image (*.jpg, *.png or *.gif)");
      return false;
  }
}

// import file excel
$(".upload-file").click(function() {

  var fileName = $("#fileName_excel").val();
  if (!validationDataType(fileName)) {
    return;
  }
  var datas = new FormData();
  jQuery.each(jQuery('#fileName_excel')[0].files, function(i, file) {
    datas.append('file-' + i, file);
  });
  $("#modal_file").val("");
  $(".message").html("");

  $.ajax({
    url: _action_url_import,
    data: datas,
    type: "post",
    async: false,
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    success: function(result) {
      var url = JSON.parse(result.url_file);
      if (url.length > 0) {
        // $("#modal_file").css('display', 'block');
        // $("#modal_file").val(url);
        alert('upload success');
      } else {
        $(".message").html("error");
      }
    },
    error: function(e) {
      alert('error: ');
    },
  });
});
$("#fileName_excel").change(function() {
  var fileName = $(this).val();
  validationDataType(fileName);
});

function validationDataType(name) {
  switch (name.substring(name.lastIndexOf('.') + 1).toLowerCase()) {
    case 'gif':
    case 'jpg':
    case 'png':
    case 'xlsx':
      $(".upload").prop('disabled', false);
      $(".message-upload-image").html("");
      $(".message-upload-file").html("");
      return true;
    default:
      $(".upload").prop('disabled', true);
      $(".message-upload-image").html("This file is not image (*.jpg, *.png or *.gif)");
      $(".message-upload-file").html("Must type *.xlxs");
      return false;
  }
}

$(".btn-import-file-excel").click(function() {
  var datas = {
    'action': _import
  }

  $.ajax({
    url: _action_url,
    data: datas,
    success: function(result) {
      var message = checkUndefined(result.success) ? result.success : null;
      if (message == true) {
        alert("success");
        window.location.href = _action_url;
      } else {
        alert(message);
      }
    },
    error: function(e, x, y) {
      alert('Error');
    },
  });

});

// search by student code

$(".btn-search").click(function() {
  var case_search = $('input[name=optradio]:checked', '#caseForm').val();
  var value_search = $('#modal_search_student_code').val();
  window.location.href = "/search?search_value=" + value_search + "&search_case=" + case_search + "&action=search"
});

// export file
$(".btn-export-file").click(function() {
  var pathname = window.location.href;
  var a = pathname.split("?")[1];
  var b = a.split("&")[0];
  var c = a.split("&")[1];
  var value_search = b.split("=")[1];
  var case_search = c.split("=")[1];

  var datas = {
    'action': 'export',
    'search_case': case_search,
    'search_value': decodeURI(value_search)
  }

  $.ajax({
    url: "/search",
    data: datas,
    success: function(result) {
      window.location.href = "/downloadFile";
      alert("success");
    },
    error: function(e, x, y) {
      alert('Error');
    },
  });

});
