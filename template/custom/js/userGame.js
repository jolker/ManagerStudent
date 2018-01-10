var _action = "/user"
    var _action_search = "username";
    var _action_next_or_previous = "nextOrPreviousPage"
    var _message_input_empty = "Either Id or Username do not empty!";
    var _indexPage = 0;
    var _total_players = $("#total").val();

    var _gameId = $.trim($("#gameId").val());
    var _message_no_data = "No data";

    $(document).ready(function() {
      var gameId = $.trim($("#gameId").val());
      $('li.userGame').addClass('active');

      $("#close-table-search").click(function(){ // close table search
        $(".table-player-search").hide();
      });

      $("#select-limit").change(function(){ // load the content of the table when choosing the other limit
          var limit = parseInt($.trim($("#select-limit").val()));
          _indexPage = 0;
          var datas = {
              "index" : _indexPage,
              "limit" : limit,
              "gameId" : _gameId,
              "action" : _action_next_or_previous
            }
          ajaxChangeContentTable(datas, _action);
          disabledNextOrPrevious(limit);
          
      });

      var limit = parseInt($.trim($("#select-limit").val()));
      disabledNextOrPrevious(limit);
    });

    function search() {
      var username = $.trim($("#txt-username").val());
      var id = $.trim($("#txt-userId").val());
      var phone = $.trim($("#txt-phone").val());
      var email = $.trim($("#txt-email").val());

      var allEmpty = 0;
      if(username.length == 0) {
        allEmpty++;
      }

      if(id.length == 0) {
        allEmpty++;
      }

      if(phone.length == 0) {
        allEmpty++;
      }

      if(email.length == 0) {
        allEmpty++;
      }

      if(allEmpty == 4) {
        $(".message-user-id-page").html("<span style='color: red'>"+_message_input_empty+"</span>");
        return;
      } else {
        $(".message-user-id-page").html("");
      }

      $("#table-body-player-search").html("");// reset content on table
      $(".table-player-search").show();

      var datas = {
        "id" : id,
        "username" : username,
        "phone" : phone,
        "email" : email,
        "action" : _action_search
      }

      $.ajax({
        url: _action,
        data: datas,
        success: function(result){
          var users = checkUndefined(result.users)? JSON.parse(result.users) : null;
          if(users.length > 0) {
            for(var i=0; i<users.length; i++) {
              $("#table-body-player-search").append(addRow((i+1), users[i]));
            }
          } else {
              $("#table-body-player-search").append("<tr> <td colspan='11' align='center'>"+_message_no_data+"</td></tr>");
          }
          
        },
        error: function(e, status, error) {
            alert('error: ');
        },
      });
    }


    function addRow(i, user) {
      var retval = "<tr>";
          retval+="    <td>"+user.id+"</td>";
          retval+="    <td title='"+user.fullname+"'>"+user.username+"</td>"; 
          if(user.email === undefined || user.email.length == 0) {
            retval += "<td>-</td>";
             
          } else {
            retval+="    <td>"+user.email+"</td>";
          }
          if(user.keyId === undefined) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.keyId+"</td>"; 
          }
          if(user.openId === undefined || user.openId == 0) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.openId+"</td>"; 
          }
          if(user.phone === undefined || user.phone.length == 0) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.phone+"</td>"; 
          }
          
          if(user.levelUpdate === undefined) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.levelUpdate+"</td>"; 
          }
          if(user.dateCreate === undefined) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.dateCreate+"</td>"; 
          }
          if(user.osName === undefined || user.osName == 0) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.osName+"</td>"; 
          }
          if(user.osVersion === undefined) {
            retval += "<td>-</td>";
          } else {
            retval+="    <td>"+user.osVersion+"</td>"; 
          }
          
          retval+="  </tr>";
      return retval;
    }

    function checkUndefined(variable) {
      return (typeof variable !== undefined);
    }

    function nextOrPrevious(nextOrPrevious) { // xxx ok
      var limit = parseInt($.trim($("#select-limit").val()));
        if(nextOrPrevious === "next") {
            _indexPage = _indexPage+limit;
            
          } else {
            _indexPage = _indexPage-limit;
          }
          
        var xxx = disabledNextOrPrevious(limit);
        if(!xxx) {
          return;
        }
      // alert("total : "+_total_players + " | _indexPage "+_indexPage);
      var datas = {
        "index" : _indexPage,
        "limit" : limit,
        "gameId" : _gameId,
        "action" : _action_next_or_previous
      }
      ajaxChangeContentTable(datas, _action);
    }

    function ajaxChangeContentTable(datas, action) {
      $.ajax({
        url: action,
        data: datas,
        type: 'get',
        success: function(result){

          var users = checkUndefined(result.users)? JSON.parse(result.users) : null;

          if(users.length > 0) {
            $("#table-body-player").html("");
            for(var i=0; i<users.length; i++) {
              $("#table-body-player").append(addRow((i+1), users[i]));
            }
          } else {
              $("#table-body-player").append("<tr> <td colspan='9' align='center'>"+_message_no_data+"</td></tr>");
          }
          
        },
        error: function(e, status, error) {
            alert('error: ');
        },
      });
    }

    function disabledNextOrPrevious(limit) {
      if(_total_players == 0) {
        $("span#to").html(0); // set value of to
        $("span#from").html(0); // set value of from
        $(".next").attr('disabled', true);
        $(".previous").attr('disabled', true);
        return false;
      }

      if(_indexPage >= 0 && _indexPage<=_total_players) {
        var from = _indexPage + 1;
        var to = _indexPage+limit;
        to = to> parseInt(_total_players) ? _total_players : to;
        from = parseInt(from)< 1 ? 1 : from;
        $("span#to").html(to); // set value of to
        $("span#from").html(from); // set value of from

        if(to >= parseInt(_total_players)) {
          $(".next").attr('disabled', true);
        } else {
          $(".next").attr('disabled', false);
        }
        if(from <= 1) {
          $(".previous").attr('disabled', true);
        } else {
          $(".previous").attr('disabled', false);
        }
      return true;
    } else {
      return false;
    }
  }
