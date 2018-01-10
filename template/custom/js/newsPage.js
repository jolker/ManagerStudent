var _empty_message = "Not empty!";
var _select_type = "Select type!";
var _select_name = "Select name!";
var _select_priority = "Select priority!";
var _action_url_news_tool = "/newsAdminTool";
var _action_url_upload = "/uploadImage"
var _action_add = "add";
var _action_update = "update";
var _action_delete = "delete";
var _action_show = "show";
var _action_upload = "upload";
var _action_get_news = "get_news";
var _action_get_all_slide = "all_slide";
var _action_add_slide = "add_slide";
var _action_delete_slide = "delete_slide";
var _action_update_slide = "update_slide";
var _action = "";
var _message_no_data = "No data";
var _modal_upload = "upload";
var _modal_slide = "slide";
var _modal_update = "update";
var _modal = "";

$(document).ready(function(){
    $('li.newsAdminTool').addClass('active');
    
    $('#date_date').datepicker({
        format: "yyyy-mm-dd",
        ignoreReadonly: false,
        todayHighlight: true
    }).on('changeDate', function(e){
        $(this).datepicker('hide');
    });

    tinymce.init({
        selector: 'textarea#news-editor',
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

// show and reset modal
$(".btn-upload-image").click(function(){ 
    resetModal();
    _modal = _modal_upload;
    $(".modal-title").html("Upload image"); // set title
});

$(".btn-add-slide-modal").click(function(){
    resetModal();
    _modal = _modal_slide;
    _action = _action_add_slide;
    $(".group-upload").hide();
    $(".group-slide").show();
    $(".modal-title").html("Add slide"); // set title
});

function update_slide(id) {
    resetModal();
    _modal = _modal_update;
    _action = _action_update_slide;
    $(".group-upload").hide();
    $(".group-slide").show();
    var image = $("#slide_image_"+id).val();
    var url = $("#slide_url_"+id).val();
    var position = $("#slide_position_"+id).val();
    $("#url_image").val(image);
    $("#url_href").val(url);
    $("#position").val(position);

    $(".modal-title").html("Update slide"); // set title
    $(".btn-add-update-slide").attr('data-slide-id', id);
}

// end show and reset modal

function resetModal() { // reset modal function
    $(".group-slide").hide();
    $(".group-upload").show();
    $(".upload").prop('disabled', false);
    // $("#url_image").hide();
    $("#url_image").val("");
    $("#fileName").val("");
    $(".message").html("");
    $("#url_href").val("");

    $("#url_href").css('border-color', '#d2d6de');
    $("#url_image").css('border-color', '#d2d6de');
    $("#position").css('border-color', '#d2d6de');
}

$("#fileName").change(function() {
    var fileName = $(this).val();
    validationDataType(fileName);
});

function validationDataType(name) {
    switch(name.substring(name.lastIndexOf('.') + 1).toLowerCase()){
        case 'gif': case 'jpg': case 'png':
            $(".upload").prop('disabled', false);
            $(".message").html("");
            return true;
        default:
            $(".upload").prop('disabled', true);
            $(".message").html("This file is not image (*.jpg, *.png or *.gif)");
            return false;
    }
}

function showNewsList() {
    var typeId = $("#typeId").val();
    var gameId = $("#gameId").val();
    var priority = $("#priority").val();
    var date_date = $("#date_date").val();
    var time_time = $("#time_time").val();
    date_date = date_date + " "+time_time;

    var valid = validInput(typeId, gameId, priority);

    if(valid > 0) {
        return;
    }
    $("#container-editor").css('display', 'none');
    $("#table-news").css('display', 'block');
    $("#current_game_id").attr('data-game-id', gameId); // store game id
    var datas = {
        "typeId" : typeId,
        "gameId" : gameId,
        "priority" : priority,
        "date" : date_date,
        "action" : _action_show
    }
    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "get",
        success: function(result){
            $("#body-table-news").html("");
            $(".news-content").html("");
            var newsObjects = checkUndefined(result.news)? JSON.parse(result.news) : null;
            if(newsObjects.length > 0) {
                for(var i=0; i<newsObjects.length; i++) {
                    $("#body-table-news").append(addRow((i+1), newsObjects[i]));
                }
            } else {
                $("#body-table-news").append("<tr> <td colspan='4' align='center'>"+_message_no_data+"</td></tr>");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

function showEditorAdd() {
    var typeId = $("#typeId").val();
    var gameId = $("#gameId").val();
    var priority = $("#priority").val();
    var date_date = $("#date_date").val();
    var time_time = $("#time_time").val();
    date_date = date_date + " " +time_time;

    var valid = validInput(typeId, gameId, priority);
    
    if(valid > 0) {
        return;
    }
    tinyMCE.get('news-editor').setContent("");
    $("#title").val("");

    $("#table-news").css('display', 'none');
    $("#container-editor").css('display', 'block');

    _action = _action_add;
}

$("#btn-cancel").click(function(){
    $("#table-news").css('display', 'none');
    $("#container-editor").css('display', 'none');
});

function showEditorUpdate(id) {
    $("#table-news").css('display', 'none');
    $("#container-editor").css('display', 'block');
    var gameId = $("#gameId").val();
    var datas = {
        "action" : _action_get_news,
        "id" : id,
        "gameId" : gameId
    }
    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "get",
        success: function(result){
            var newsObjects = checkUndefined(result.news)? JSON.parse(result.news) : null;
            if(newsObjects != null) {
                tinyMCE.get('news-editor').setContent(newsObjects.content);
                $("#title").val(newsObjects.title);
                $('#date_date').datepicker("setDate", newsObjects.date);
                $("#time_time").val(newsObjects.time);
                $("#select-priority").val(newsObjects.typeId);
            } else {
                alert("Get news error");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });


    $("#btn-finish").attr('data-newsId', id);
    _action = _action_update;
}

function finish() { // add or update
    var valid = 0;
    var title = $.trim($("#title").val());
    var date = $.trim($("#date_date").val());
    var time = $.trim($("#time_time").val());
    date = date + " " + time;

    var content = tinyMCE.get('news-editor').getContent();
    content = content.replace(/<img/g,"<img id='img_from_editor' ");
    
    var priority = $("#select-priority").val();
    if(title.length == 0) {
        valid++;
        $("#title-message").html(_empty_message);
    } else {
        $("#title-message").html("");
    }
   
    if(date.length == 0) {
        valid++;
        $("#date_date-message").html(_empty_message);
    } else {
        $("#date_date-message").html("");
    }

    if(content.length == 0) {
        valid++;
        $("#content-message").html(_empty_message);
    } else {
        $("#content-message").html("");
    }
    if(priority == 0) {
        valid++;
        $("#priority-message").html(_select_priority);
    } else {
        $("#priority-message").html("");
    }

    if(valid > 0) {
        return;
    }
    
    var typeId = $.trim($("#typeId").val());
    var gameId = $.trim($("#gameId").val());
    var id = $.trim($("#btn-finish").attr('data-newsId'));
    var action = _action;
    var datas = {
        "id" : id,
        "date" : date,
        "title" : title,
        "content" : content,
        "gameId" : gameId,
        "typeId" : typeId,
        "priority" : priority,
        "action" : action
    }

    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "post",
        success: function(result){
            if(result.message == true) {
                alert("success");
            } else {
                alert("error");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

$(".upload").click(function(){
    var fileName = $("#fileName").val();
    if(!validationDataType(fileName)) {
        return;
    }
    var datas = new FormData();
    jQuery.each(jQuery('#fileName')[0].files, function(i, file) {
        datas.append('file-'+i, file);
    });
    $("#url_image").val("");
    $(".message").html("");
    
    $.ajax({
        url: _action_url_upload,
        data: datas,
        type: "post",
        async: false,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function(result){
            var url = JSON.parse(result.url_image);
            if(url.length > 0) {
                $("#url_image").css('display', 'block');
                $("#url_image").val(url);
            } else {
                $(".message").html("error");
            }
            $(".upload").prop('disabled', true);
        },
        error: function(e) {
            alert('error: ');
        },
    });
});

function addRow(sno, newsObject) {
    // $(".news-content").append("<div id='news-detail-"+newsObject.id+"'>"+newsObject.content+"</div>");
    var retval = "";
    retval +="<tr id='tr-"+newsObject.id+"'>";
    retval +="<td>"+sno+"</td>";
    retval +="<td id='title-"+newsObject.id+"'>"+newsObject.title+"</td>";
    retval +="<td id='date-"+newsObject.id+"'>"+newsObject.date+"</td>";
    retval +="<td id='time-"+newsObject.id+"'>"+newsObject.time+"</td>";
    retval +="<td id='id-"+newsObject.id+"'>"+newsObject.id+"</td>";
    retval +="<td>";
    retval +="<button type='button' class='btn btn-success' onclick='showEditorUpdate("+newsObject.id+")'>Update&nbsp;&nbsp;<span class='glyphicon glyphicon-pencil'></span></button>&nbsp;&nbsp;";
    retval +="<button type='button' data-toggle='modal' onclick='showDeleteModal("+newsObject.id+")' href='#modal-delete' class='btn btn-warning'>Delete&nbsp;&nbsp;<span class='glyphicon glyphicon-trash'></span></button>";
    retval +="</td>";
    retval +="</tr>";
    return retval;
}

function checkUndefined(variable) {
    return (typeof variable !== undefined);
}

function showDeleteModal(id) {
    $("#news-name").html("");
    var name = $("td#title-"+id).html();
    $("#news-name").html(name);
    $(".btn-delete-modal").attr('data-newsId', id);
}

$(".btn-delete-modal").click(function() {
    var id = $(this).attr('data-newsId');
    var gameId = $("#current_game_id").attr('data-game-id');
    var datas = {
        "id" : id,
        "gameId" : gameId,
        "action" : _action_delete
    }
    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "post",
        success: function(result){
            if(result.message === true) {
                alert("success");
                $("tr#tr-"+id).remove();
                $('#modal-delete').modal('toggle');
            } else {
                alert("failed");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
});

function validInput(typeId, gameId, priority) {
    var error = 0;
    if(typeId == 0) {
        error++;
        $("#typeId-message").html(_select_type);
    } else {
        $("#typeId-message").html("");
    }
    if(gameId == 0) {
        error++;
        $("#gameId-message").html(_select_name);
    } else {
        $("#gameId-message").html("");
    }
    if(priority == 0) {
        error++;
        $("#priority-message").html(_select_priority);
    } else {
        $("#priority-message").html("");
    }
    
    return error;
}


function showSlideList() {
    var gameId = $("#gameId-slide").val();
    var datas = {
        "gameId" : gameId,
        "action" : _action_get_all_slide
    }
    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "post",
        success: function(result){
            $("#slide_list").html("");
            $(".container-slide-list").css('display', 'block');
            if(result.message === true) {
                var slides = checkUndefined(result.slides)? JSON.parse(result.slides) : null;
                for(var i=0; i<slides.length; i++) {
                    $("#slide_list").append(addRowSlide(slides[i], (i+1)));
                }
            } else {
                alert("failed");
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

function addRowSlide(slide, position) {
    var retval = "<tr id='slide_"+slide.id+"'>";
    retval += "<td>";
    retval += position;
    retval += "</td>";

    retval += "<td>";
    retval += "<img width='300px' height='200px' src='"+slide.image+"'/> <br><br>";
    retval += "<input type='text' class='form-control' id='slide_image_"+slide.id+"' value='"+slide.image+"' disabled ";
    retval += "</td>";

    retval += "<td>";
    retval += "<input type='text' class='form-control' id='slide_url_"+slide.id+"' value='"+slide.url+"' disabled ";
    retval += "</td>";

    retval += "<td>";
    retval += "<input type='text' class='form-control' size='4' id='slide_position_"+slide.id+"' value='"+slide.position+"' disabled ";
    retval += "</td>";

    retval += "<td>";
    retval += "<button class='btn btn-warning' onclick=delete_slide("+slide.id+")>Delete</button>";
    retval += "</td>";

    retval += "<td>";
    retval += "<button class='btn btn-success btn-update-slide-modal' data-toggle='modal' href='#modal-upload' onclick=update_slide("+slide.id+")>Update</button>";
    retval += "</td>";

    retval += "</tr>";
    return retval;
}

function checkUndefined(variable) {
    return (typeof variable !== "undefined");
}

$(".btn-add-update-slide").click(function(){
    var url = $.trim($("#url_href").val());
    var image = $.trim($("#url_image").val());
    var position = $.trim($("#position").val());
    var gameId = $("#gameId-slide option:selected").val();
    var id = $(this).attr('data-slide-id');
    var valid = 0;
    if(url.length == 0) {
        $("#url_href").css('border-color', 'red');
        valid++;
    } else {
        $("#url_href").css('border-color', '#d2d6de');
    }
    if(image.length == 0) {
        $("#url_image").css('border-color', 'red');
        valid++;
    } else {
        $("#url_image").css('border-color', '#d2d6de');
    }
    if(position.length == 0) {
        $("#position").css('border-color', 'red');
        valid++;
    } else {
        $("#position").css('border-color', '#d2d6de');
    }
    if(valid > 0) {
        return;
    }
    var datas = {
        "url" : url,
        "image" : image,
        "position" : position,
        "gameId" : gameId,
        "id" : id,
        "action" : _action
    }

    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "post",
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
});

function delete_slide(id) {
    var con = confirm("Are you sure delete it?");
    if(!con) {
        return;
    }
    var gameId = $("#gameId-slide option:selected").val();
    var datas = {
        "id" : id,
        "gameId" : gameId,
        "action" : _action_delete_slide
    }

    $.ajax({
        url: _action_url_news_tool,
        data: datas,
        type: "post",
        success: function(result){
            if(result.message == true) {
                alert("success");
                $("#slide_"+id).remove();
            } else {
                alert(result.message);
            }
        },
        error: function(e) {
            alert('error: ');
        },
    });
}

$("#gameId-slide").change(function(){
    var gameId = $(this).val();
    if(gameId == 0) {
        $(".group-slide-button").hide();
    } else {
        $(".group-slide-button").show();
    }
});