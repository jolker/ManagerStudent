var _current_action = "";
var _action_url = "/gameKey";
var _action_gen_game_key = "gen_game_key";
var _add = "add";
var _update = "update";
var _delete = "delete";
var _export = "export";
var _title_add_user = "Add a new Game Key";
var _title_update_user = "Update Game Key";
var _message_not_empty = "Not empty";
var _message_must_number = "Must number";

$(document).ready(function(){
  $('li.gameKey').addClass('active');
});



function showAddModal() { // reset modal with id: modal-id-add-update
  _current_action = _add;
    $(".modal-title").html("<strong>"+_title_add_user+"</strong>");
    $(".btn-add-update-game-key").text('Save');
    $("#modal_app_key").prop('disabled', false);
    $("#modal_app_id").prop('disabled', false);
    $("#modal_app_secret").prop('disabled', false);

    
    $("#modal_game_id").val("");
    $("#modal_store_id").val("");
    $("#modal_app_key").val("");
    $("#modal_app_id").val("");
    $("#modal_app_secret").val("");
    $("#modal_android_payment").val("");
    $("#modal_ios_payment").val("");
    $("#modal_test").prop('checked', false);

    // $("#modal_dev_key").val("");
    $("#modal_app_store_id").val("");
    $("#modal_act_conversion_id").val("");
    $("#modal_act_conversion_label").val("");
    $("#modal_act_conversion_value").val("");
    $("#modal_gg_analytics_id").val("");
    $("#modal_gg_signIn_clien_id").val("");
    $("#modal_fb_id").val("");
    $("#modal_fb_secret").val("");
    $("#modal_fb_display_name").val("");
    $("#modal_enable_trans").val("");

    $('.modal_items_message').each(function(){
      $(this).html("");
    });

    $('.modal_items_icon').each(function() {
      $(this).removeClass("glyphicon glyphicon-remove form-control-feedback");
    });

    $('.modal_items').each(function(){
      $(this).removeClass('has-error has-feedback');
    });
};

function showUpdateModal(id) { // show content user who was clicked to update
  
  _current_action = _update;
    $(".modal-title").html("<strong>"+_title_update_user+"</strong>");
    
    var game_id = $.trim($('#game_id_'+id).html());
    var store_id = $.trim($('#store_id_'+id).html());
    var app_key = $.trim($('#app_key_'+id).val());
    var app_id = $.trim($('#app_id_'+id).val());
    var app_secret = $.trim($('#app_secret_'+id).val());
    var android_payment = $.trim($('#android_payment_'+id).val());
    var ios_payment = $.trim($('#ios_payment_'+id).val());
    var test = $('#is_test_'+id).is(':checked');
    var devKey = $("#dev_key_"+id).val();
    var appStoreId = $("#app_store_id_"+id).val();
    var actConversionId = $("#act_conversion_id_"+id).val();
    var actConversionLabel = $("#act_conversion_label_"+id).val();
    var actConversionValue = $("#act_conversion_value_"+id).val();
    var ggAnalyticsId = $("#gg_analytics_id_"+id).val();
    var ggSignInClienId = $("#gg_signIn_clien_id_"+id).val();
    var fbAppId = $("#fb_id_"+id).val();
    var fbSecret = $("#fb_secret_"+id).val();
    var fbDisplayName = $("#fb_display_name_"+id).val();
    var enableTrans = $("#enable_trans_"+id).val();

    $("#modal_app_key").prop('disabled', true);
    $("#modal_app_id").prop('disabled', true);
    $("#modal_app_secret").prop('disabled', true);

    
    $("#modal_game_id").val(game_id);
    $("#modal_store_id").val(store_id);
    $("#modal_app_key").val(app_key);
    $("#modal_app_id").val(app_id);
    $("#modal_app_secret").val(app_secret);
    $("#modal_android_payment").val(android_payment);
    $("#modal_ios_payment").val(ios_payment);
    $("#modal_test").prop('checked', test);

    $("#modal_dev_key").val(devKey);
    $("#modal_app_store_id").val(appStoreId);
    $("#modal_act_conversion_id").val(actConversionId);
    $("#modal_act_conversion_label").val(actConversionLabel);
    $("#modal_act_conversion_value").val(actConversionValue);
    $("#modal_gg_analytics_id").val(ggAnalyticsId);
    $("#modal_gg_signIn_clien_id").val(ggSignInClienId);
    $("#modal_fb_id").val(fbAppId);
    $("#modal_fb_secret").val(fbSecret);
    $("#modal_fb_display_name").val(fbDisplayName);
    $("#modal_enable_trans").val(enableTrans);

    $('.btn-add-update-game-key').attr('gameKeyId', id);
  };

// ajax Add or Update game key
  $(".btn-add-update-game-key").click(function(){
      var game_id = $.trim($("#modal_game_id").val());
      var store_id = $.trim($("#modal_store_id").val());
      var app_key = $.trim($("#modal_app_key").val());
      var app_id = $.trim($("#modal_app_id").val());
      var app_secret = $.trim($("#modal_app_secret").val());
      var android_payment = $.trim($("#modal_android_payment").val());
      var ios_payment = $.trim($("#modal_ios_payment").val());
      var test = $("#modal_test").is(':checked');

      var devKey = $.trim($("#modal_dev_key").val());
      var appStoreId = $.trim($("#modal_app_store_id").val());
      var actConversionId = $.trim($("#modal_act_conversion_id").val());
      var actConversionLabel = $.trim($("#modal_act_conversion_label").val());
      var actConversionValue = $.trim($("#modal_act_conversion_value").val());
      var ggAnalyticsId = $.trim($("#modal_gg_analytics_id").val());
      var ggSignInClienId = $.trim($("#modal_gg_signIn_clien_id").val());
      var fbAppId = $.trim($("#modal_fb_id").val());
      var fbSecret = $.trim($("#modal_fb_secret").val());
      var fbDisplayName = $.trim($("#modal_fb_display_name").val());
      var enableTrans = $.trim($("#modal_enable_trans").val());
      var id = $(this).attr('gameKeyId');
      var validateInt = 0;

      // validate input
      validateInt += validateInput("container_game_id", !$.isNumeric(game_id), _message_must_number);
      validateInt += validateInput("container_store_id", !$.isNumeric(store_id), _message_must_number);
      validateInt += validateInput("container_app_key", app_key.length == 0, _message_not_empty);
      validateInt += validateInput("container_app_id", app_id.length == 0, _message_not_empty);
      validateInt += validateInput("container_app_secret", app_secret.length == 0, _message_not_empty);
      validateInt += validateInput("container_android_payment", android_payment.length == 0, _message_not_empty);
      validateInt += validateInput("container_ios_payment", ios_payment.length == 0, _message_not_empty);
      if(validateInt > 0) { // one of the input are empty, this action will be return
        return;
      }
      
      var action = _current_action;
      var datas = {
        'id' : id,
        'gameId' : game_id,
        'storeId' : store_id,
        'appKey' : app_key,
        'appId' : app_id,
        'appSecret' : app_secret,
        'android_payment' : android_payment,
        'ios_payment' : ios_payment,
        'test' : test,
        'devKey' : devKey,
        'appStoreId' : appStoreId,
        'actConversionId' : actConversionId,
        'actConversionLabel' : actConversionLabel,
        'actConversionValue' : actConversionValue,
        'ggAnalyticsId' : ggAnalyticsId,
        'ggSignInClienId' : ggSignInClienId,
        'fbId' : fbAppId,
        'fbSecret' : fbSecret,
        'fbDisplayName' : fbDisplayName,
        'enableTrans' : enableTrans,
        'action' : _current_action
      };
      $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          var message = checkUndefined(result.success)? result.success : null;
            if(message == true) {
              alert("success");
              $('#modal-id-add-update').modal('toggle'); // hide modal if adding successful
              window.location.href = _action_url;
            } else {
              alert(message);
            }
        },
        error: function(e) {
          alert('Error from server');
        },
      });
  });

  // Delete game key

function showDeleteModal(id) {
  $("#modal-game-key-delete").html("");
  $('.btn-delete-game-key').attr('gameKeyId', id);
  $("#modal-game-key-delete").html("ID: "+id);
}
// ajax delete game key
$(".btn-delete-game-key").click(function(){
    
    var gameKeyId = $(this).attr('gameKeyId');
    var datas = {
      'gameKeyId' : gameKeyId,
      'action' : _delete
    }

    $.ajax({
      url: _action_url,
      data: datas,
      success: function(result){
        var message = checkUndefined(result.success)? result.success : null;
        if(message == true) {
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
    if(right == true) {
      $("."+name_class).addClass('has-error has-feedback');
      $("."+name_class+"_icon").addClass("glyphicon glyphicon-remove form-control-feedback");
      $("."+name_class+"_message").html("<span style='color: red;'> "+message+"</span>");
      emptyInt = 1;
    } else {
      $("."+name_class).removeClass('has-error has-feedback');
      $("."+name_class+"_icon").removeClass("glyphicon glyphicon-remove form-control-feedback");
      $("."+name_class+"_message").html("");
    }

    return emptyInt;
  }

  function exportGameKey(type, gameKeyId) {
    $(location).attr('href', _action_url+"?action=export&&type="+type+"&&gameKeyId="+gameKeyId);
  }

  function generate() {
    var gameId = $.trim($("#modal_game_id").val());
    var storeId = $.trim($("#modal_store_id").val());
    if(gameId.length == 0 || storeId.length ==0) {
      alert("Either Game Id or Store Id is empty!");
      return;
    }
    var datas = {
      "gameId" : gameId,
      "storeId" : storeId,
      "action" : _action_gen_game_key
    }
    $.ajax({
      url: _action_url,
      data: datas,
      success: function(result){
        var message = checkUndefined(result.success)? result.success : null;
        var gen = checkUndefined(result.gameKeyGen) ? JSON.parse(result.gameKeyGen) : null;
        if(message == true) {
          $("#modal_app_id").val(gen.appId);
          $("#modal_app_secret").val(gen.appSecret);
          $("#modal_app_key").val(gen.appKey);
        } else {
          alert(message);
        }
      },
      error: function(e, x, y) {
        alert('Error');
      },
    });

  }