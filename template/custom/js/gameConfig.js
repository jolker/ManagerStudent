    var _action_url = "/gameConfig"
    var _action_next_or_previous = "nextOrPreviousPage"
    var _action_filter = "filter";
    var _message_input_empty = "Trans Id do not empty!";
    var _indexPage = 0;
    var _total_players = $("#total").val();
    var _title_add_game_config = "Add Game Config";
    var _title_update_game_config = "Update Game Config"
    var _add = "add";
    var _add_multiple = "add_multiple";
    var _update = "update";
    var _delete = "delete";
    var _message_no_data = "No data";
    var _message_not_empty = "Not empty";
    var _message_must_number = "Must number";

    $(document).ready(function() {
      $('li.gameConfig').addClass('active');
    });

    function addRow(i, gameConfig) {
      var retval = "<tr id='row_"+gameConfig.id+"'>";
          retval+="    <td>"+gameConfig.id+"</td>";
          retval+="    <td id='game_id_"+gameConfig.id+"'>"+gameConfig.gameId+"</td>";
          retval+="    <td id='amount_"+gameConfig.id+"'>"+gameConfig.amount+"</td>"; 
          if(gameConfig.value === undefined) {
            retval += "<td id='value_"+gameConfig.id+"'>-</td>";
             
          } else {
            retval+="    <td id='value_"+gameConfig.id+"'>"+gameConfig.value+"</td>";
          }
          if(gameConfig.typePay === undefined) {
            retval += "<td id='typePay_"+gameConfig.id+"'>-</td>";
          } else {
            retval+="    <td id='typePay_"+gameConfig.id+"'>"+gameConfig.typePay+"</td>"; 
          }
          if(gameConfig.productId === undefined) {
            retval += "<td id='product_id_"+gameConfig.id+"'>-</td>";
          } else {
            retval+="    <td id='product_id_"+gameConfig.id+"'>"+gameConfig.productId+"</td>"; 
          }
          retval+="    <td><button class='btn btn-success' onclick='showUpdateModal("+gameConfig.id+")' data-toggle='modal' href='#modal-id-add-update'>Update</button></td>"; 
          retval+="    <td><button class='btn btn-warning' onclick='showDeleteModal("+gameConfig.id+")' data-toggle='modal' href='#modal-id-delete'>Delete</button></td>"; 
          retval+="  </tr>";
      return retval;
    }

    function checkUndefined(variable) {
      return (typeof variable !== undefined);
    }

    function filter() {
      var gameId = parseInt($.trim($("#select-filter").val()));
      if(gameId == 0) {
        alert("Select Game ID");
        return;
      }
      var datas = {
        "gameId" : gameId,
        "action" : _action_filter
      }

      ajaxChangeContentTable(datas, _action_url);
    }

    function ajaxChangeContentTable(datas, action) {
      $.ajax({
        url: action,
        data: datas,
        type: 'get',
        success: function(result){

          var gameConfigs = checkUndefined(result.gameConfigs)? JSON.parse(result.gameConfigs) : null;

          if(gameConfigs.length > 0) {
            $("#game-config-body").html("");
            for(var i=0; i<gameConfigs.length; i++) {
              $("#game-config-body").append(addRow((i+1), gameConfigs[i]));
            }
          } else {
              $("#game-config-body").html("<tr> <td colspan='9' align='center'>"+_message_no_data+"</td></tr>");
          }
          
        },
        error: function(e, status, error) {
            alert('error: ');
        },
      });
    }

  function showAddModal() { // reset modal with id: modal-id-add-update
    _current_action = _add;
      $(".modal-title").html("<strong>"+_title_add_game_config+"</strong>");
      $(".btn-add-update-game-key").text('Save');
      $("#modal_amount").val("");
      $("#modal_value").val("");
      $("#modal_type_pay").val("");
      $("#modal_product_id").val("");
      $("#modal_game_id").val("");


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

  $(".btn-add-update-game-config").click(function(){
      var game_id = $("#modal_game_id").val();
      var amount = $("#modal_amount").val();
      var value = $("#modal_value").val();
      var type_pay = $("#modal_type_pay").val();
      var product_id = $("#modal_product_id").val();
      var id = $(this).attr('gameConfigId');
      var validateInt = 0;

      // validate input
      validateInt += validateInput("container_amount", !$.isNumeric(amount), _message_must_number);
      validateInt += validateInput("container_value", !$.isNumeric(value), _message_must_number);
      validateInt += validateInput("container_type_pay", !$.isNumeric(type_pay), _message_must_number);
      
      if(validateInt > 0) { // one of the input are empty, this action will be return
        return;
      }
      
      var action = _current_action;
      var datas = {
        'id' : id,
        'gameId' : game_id,
        'amount' : amount,
        'value' : value,
        'typePay' : type_pay,
        'productId' : product_id,
        'action' : _current_action
      };
      $.ajax({
        url: _action_url,
        data: datas,
        success: function(result){
          var message = checkUndefined(result.success)? result.success : null;

          if(message==true) {
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

  function showUpdateModal(id) { // show content user who was clicked to update
    _current_action = _update;
    $(".modal-title").html("<strong>"+_title_update_game_config+"</strong>");
    
    var game_id = $.trim($('#game_id_'+id).html());
    var amount = $.trim($('#amount_'+id).html());
    var value = $.trim($('#value_'+id).html());
    var typePay = $.trim($('#typePay_'+id).html());
    var productId = $.trim($('#product_id_'+id).html());
    $("#modal_game_id").val(game_id);
    $("#modal_amount").val(amount);
    $("#modal_value").val(value);
    $("#modal_type_pay").val(typePay);
    if(productId == '-') {
      productId = '';
    }
    $("#modal_product_id").val(productId);


    $('.btn-add-update-game-config').attr('gameConfigId', id);
  };

  function showDeleteModal(id) {
    $("#modal-game-config-id").html("");
    $('.btn-delete-game-config').attr('gameConfigId', id);
    $("#modal-game-config-id").html(" ID : "+id);
  }

  $(".btn-delete-game-config").click(function(){
    
    var gameConfigId = $(this).attr('gameConfigId');
    var datas = {
      'gameConfigId' : gameConfigId,
      'action' : _delete
    }

    $.ajax({
      url: _action_url,
      data: datas,
      success: function(result){
        var message = checkUndefined(result.success)? result.success : null;
        if(message == true) {
          alert("success");
          $("#row_"+gameConfigId).remove();
        } else {
          alert(message);
        }
      },
      error: function(e, x, y) {
        alert('Error');
      },
    });

  });


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

  function add() {
    var gameId = $("#select-game-id").val();
    if(gameId == 0) {
      alert('Select Game ID');
      return;
    }
    var check = false;
    $(".typePayId").each(function(){
      if(!$.isNumeric($(this).val())) {
        $(this).css('border-color', 'red');
        check = true;
      } else {
        $(this).css('border', '1px solid #CCCCCC');
      }
    });

    $(".amount").each(function(){
      if($(this).val().length == 0) {
        $(this).css('border-color', 'rgba(255, 0, 0, 0.18)');
      }else if(!$.isNumeric($(this).val())) {
        $(this).css('border-color', 'red');
        // check = true;
      }else {
        $(this).css('border', '1px solid #CCCCCC');
      }
    });

    $(".value").each(function(){
      if($(this).val().length == 0) {
        $(this).css('border-color', 'rgba(255, 0, 0, 0.18)');
      }else if(!$.isNumeric($(this).val())) {
        $(this).css('border-color', 'red');
        // check = true;
      }else {
        $(this).css('border', '1px solid #CCCCCC');
      }
    });

    if(check) {
      return;
    }

    var typePayId = $("input[name='typePayId[]']").map(function(){return $(this).val();}).get();
    var amount = $("input[name='amount[]']").map(function(){return $(this).val();}).get();
    var values = $("input[name='values[]']").map(function(){return $(this).val();}).get();
    var productId = $("input[name='productId[]']").map(function(){return $(this).val();}).get();
    var datas = {
      "typePayId" : typePayId,
      "amount" : amount,
      "values" : values,
      "gameId" : gameId,
      "productId" : productId,
      "action" : _add_multiple
    }
    $.ajax({
        url: _action_url,
        data: datas,
        type: 'POST',
        success: function(result){
          var message = checkUndefined(result.success)? result.success : null;
          if(message == true) {
            alert("success");
            // window.location.href = _action_url;
          } else {
            alert(message);
          }
          
          
        },
        error: function(e, status, error) {
            alert('error: ');
        },
      });
  }

  $("#input-product-id").keypress(function(e) {
    var package_name = $.trim($(this).val());
    var productId = package_name;
    if(e.which == 13) {
        $(".add_package_name").each(function(){
          productId += "." + $(this).attr('data-amount');
          $(this).val(productId);
          productId = package_name;
        });
    }
});