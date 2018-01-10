<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ISM - Mindset-based Transformation | Game Key</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${root_url}bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${root_url}dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${root_url}dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${root_url}custom/css/gameKey.css">
  <link rel="stylesheet" href="${root_url}custom/css/style.css">
  <style type="text/css">
    .txt-input {
      border: transparent;
      background: #fff;
    }
  </style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    <!-- Header Navbar: style can be found in header.less -->
    <#include "header.ftl">

      <!-- Left side column. contains the logo and sidebar xxx -->
      <#include "leftSidebar.ftl">
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
          <!-- Content Header (Page header) -->
          <section class="content-header">
            <h4>
      <a href="/home"><span class="glyphicon glyphicon-home"></span> Home</a> > List Student

      </h4>

          </section>

          <!-- Main content -->
          <section class="content">
            <div class="container-fluid" style="margin-top: 35px;">
              <button type="button" class="btn btn-primary" onclick="showAddModal()" data-toggle="modal" href='#modal-id-add-update'>Add Student &nbsp;&nbsp; <span class="glyphicon glyphicon-plus"></span></button>
              <button type="button" class="btn btn-primary" onclick="showImportModal()" data-toggle="modal" href='#modal-id-import'>Import Excel File &nbsp;&nbsp; <span class="glyphicon glyphicon-plus"></span></button>
              <button type="button" class="btn btn-primary" data-toggle="modal" href='#modal-id-search'>Search &nbsp;&nbsp; <span class="glyphicon glyphicon-plus"></span></button>
              <div class="container-body">
                <div class="table-responsive">
                  <table class="table table-hover table-game-key">
                    <thead>
                      <tr>
                        <th>Lớp</th>
                        <th>MSThẻ</th>
                        <th>Hình profile</th>
                        <th>Thời gian diễn ra khóa học</th>
                        <th>Thứ tự khóa học</th>
                        <th>Họ</th>
                        <th>Tên</th>
                        <th>Giới tính</th>
                        <th>Di động</th>
                        <th>Ngày tháng năm sinh</th>
                        <th>Ngày sinh</th>
                        <th>Tháng sinh</th>
                        <th>Công ty</th>
                        <th>Email</th>
                        <th>Chức vụ MT</th>
                        <th>Người giới thiệu</th>
                        <th>Chức vụ</th>
                        <th>Phòng ban</th>
                        <th>Địa chỉ nhà</th>
                        <th>Ghi chú</th>
                        <th>Khu vực</th>
                        <th></th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody id="game-key-body">
                      <#list students as student>
                        <tr id='${student.id}'>
                          <td>
                            <input type="text" id='student_class_${student.id}' disabled class="txt-input" value="${student.student_class}" size="20">
                          </td>
                          <td>
                            <input type="text" id='student_code_${student.id}' disabled class="txt-input" value="${student.student_code}" size="20">
                          </td>
                          <td>
                            <img src="${student.image_profile}" alt="" id='image_profile_${student.id}' width="40px" height="40px">
                          </td>
                          <td>
                            <input type="text" id='start_course_${student.id}' disabled class="txt-input" value="${student.start_course}" size="20">
                          </td>
                          <td>
                            <input type="text" id='number_course_${student.id}' disabled class="txt-input" value="${student.number_course}" size="20">
                          </td>
                          <td>
                            <input type="text" id='last_name_${student.id}' disabled class="txt-input" value="${student.last_name}" size="20">
                          </td>
                          <td>
                            <input type="text" id='first_name_${student.id}' disabled class="txt-input" value="${student.first_name}" size="20">
                          </td>
                          <td>
                            <input type="text" id='sex_${student.id}' disabled class="txt-input" value="${student.sex}" size="20">
                          </td>
                          <td>
                            <input type="text" id='phone_${student.id}' disabled class="txt-input" value="${student.phone}" size="20">
                          </td>
                          <td>
                            <input type="text" id='birthday_${student.id}' disabled class="txt-input" value="${student.birthday}" size="20">
                          </td>
                          <td>
                            <input type="text" id='day_birth_${student.id}' disabled class="txt-input" value="${student.day_birth}" size="20">
                          </td>
                          <td>
                            <input type="text" id='month_birth_${student.id}' disabled class="txt-input" value="${student.month_birth}" size="20">
                          </td>
                          <td>
                            <input type="text" id='company_${student.id}' disabled class="txt-input" value="${student.company}" size="20">
                          </td>
                          <td>
                            <input type="text" id='email_${student.id}' disabled class="txt-input" value="${student.email}" size="20">
                          </td>
                          <td>
                            <input type="text" id='position_class_${student.id}' disabled class="txt-input" value="${student.position_class}" size="20">
                          </td>
                          <td>
                            <input type="text" id='presenter_${student.id}' disabled class="txt-input" value="${student.presenter}" size="20">
                          </td>
                          <td>
                            <input type="text" id='position_presenter_${student.id}' disabled class="txt-input" value="${student.position_presenter}" size="20">
                          </td>
                          <td>
                            <input type="text" id='department_${student.id}' disabled class="txt-input" value="${student.department}" size="20">
                          </td>
                          <td>
                            <input type="text" id='address_${student.id}' disabled class="txt-input" value="${student.address}" size="20">
                          </td>
                          <td>
                            <input type="text" id='description_${student.id}' disabled class="txt-input" value="${student.description}" size="20">
                          </td>
                          <td>
                            <input type="text" id='area_${student.id}' disabled class="txt-input" value="${student.area}" size="20">
                          </td>
                          <td><button class="btn btn-success" onclick="showUpdateModal(${student.id})" data-toggle="modal" href='#modal-id-add-update'>Update</button></td>
                          <td><button class="btn btn-warning" onclick="showDeleteModal(${student.id})" data-toggle="modal" href='#modal-id-delete'>Delete</button></td>
                        </tr>
                      </#list>
                    </tbody>
                  </table>
                </div>
              </div>

              <div class="container">
                <ul class="pager">
                  <#assign x = total_page>
                  <#list 1..x as i>
                    <li><a href="/listStudent?page=${i}">${i}</a></li>
                  </#list>
                </ul>
              </div>
            </div>

            <!--Modal delete-->
            <div class='modal fade' id='modal-id-delete'>
              <div class='modal-dialog'>
                <div class='modal-content'>
                  <div class='modal-header'>
                    <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>
                    <h4 class='modal-title'>Delete Game</h4>
                  </div>
                  <div class='modal-body'>
                    Are you sure delete? <strong id='modal-game-name'></strong>
                    <br> Note: we can not be restored after this game is deleted.

                  </div>
                  <div class='modal-footer'>
                    <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
                    <button type='button' class='btn btn-primary btn-delete-game-key' gameId='' data-dismiss='modal'>Delete</button>
                  </div>
                </div>
              </div>
            </div>

            <!--Modal add or update xxx-->
            <div class="modal fade" id="modal-id-add-update">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Update Student</h4>
                  </div>
                  <div class="modal-body">
                    <div class="form-group" id='id_modal_student_code'>
                      <label class="left-modal">MS Thẻ:</label>
                      <div class="right-modal modal_items container_student_code">
                        <input type="text" name="modal_student_code" id="modal_student_code" class="form-control" value="">
                        <span class="container_student_code_icon modal_items_icon"></span>
                      </div>
                      <div class="container_student_code_message modal_items_message"></div>
                    </div>
                    <div class="form-group" id='id_modal_icon'>
                      <label class="left-modal">Image Profile:</label>
                      <div class="right-modal modal_items container_icon">
                        <input type="text" name="modal_icon" id="modal_icon" class="form-control" value="">
                        <input type="file" id="fileName" name="fileName"><br>
                        <div class="message-upload-image"></div>
                        <span id='alert_text'>Upload ảnh có tâm, làm nhỏ dung lượng trước khi upload nhé</span>
                        <div class="group-slide">
                          <button type="submit" class="btn btn-success upload" disabled>Upload &nbsp; <span class="glyphicon glyphicon-upload"></span></button>
                        </div>
                        <span class="container_icon_icon modal_items_icon"></span>
                      </div>
                      <div class="container_icon_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Lớp: </label>
                      <div class="right-modal modal_items container_student_class">
                        <input type="text" name="modal_student_class" id="modal_student_class" class="form-control" value="">
                        <span class="container_student_class_icon modal_items_icon"></span>
                      </div>
                      <div class="container_student_class_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Thời gian diễn ra khóa học: </label>
                      <div class="right-modal modal_items container_start_course">
                        <input type="text" name="modal_start_course" id="modal_start_course" class="form-control" value="">
                        <span class="container_start_course_icon modal_items_icon"></span>
                      </div>
                      <div class="container_start_course_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Thứ tự khóa học: </label>
                      <div class="right-modal modal_items container_number_course">
                        <input type="number" name="modal_number_course" id="modal_number_course" class="form-control" value="">
                        <span class="container_number_course_icon modal_items_icon"></span>
                      </div>
                      <div class="container_number_course_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Họ: </label>
                      <div class="right-modal modal_items container_last_name">
                        <input type="text" name="modal_last_name" id="modal_last_name" class="form-control" value="">
                        <span class="container_last_name_icon modal_items_icon"></span>
                      </div>
                      <div class="container_last_name_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Tên: </label>
                      <div class="right-modal modal_items container_first_name">
                        <input type="text" name="modal_first_name" id="modal_first_name" class="form-control" value="">
                        <span class="container_first_name_icon modal_items_icon"></span>
                      </div>
                      <div class="container_first_name_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Giới tính: </label>
                      <div class="right-modal modal_items">
                        <form id="sexForm">
                          <label class="radio-inline">
                              <input type="radio" name="modal_sex" value="Nam" checked="checked">Nam
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="modal_sex" value="Nữ">Nữ
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="modal_sex" value="None">None
                            </label>
                        </form>
                      </div>
                      <div class="container_sex_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Di động: </label>
                      <div class="right-modal modal_items container_phone">
                        <input type="text" name="modal_phone" id="modal_phone" class="form-control" value="">
                        <span class="container_phone_icon modal_items_icon"></span>
                      </div>
                      <div class="container_phone_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Ngày tháng năm sinh: </label>
                      <div class="right-modal modal_items container_birthday">
                        <input type="date" name="modal_birthday" id="modal_birthday" class="form-control" value="">
                        <span class="container_birthday_icon modal_items_icon"></span>
                      </div>
                      <div class="container_birthday_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Công ty: </label>
                      <div class="right-modal modal_items container_company">
                        <input type="text" name="modal_company" id="modal_company" class="form-control" value="">
                        <span class="container_company_icon modal_items_icon"></span>
                      </div>
                      <div class="container_company_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Email: </label>
                      <div class="right-modal modal_items container_email">
                        <input type="email" name="modal_email" id="modal_email" class="form-control" value="">
                        <span class="container_email_icon modal_items_icon"></span>
                      </div>
                      <div class="container_email_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Chức vụ MT: </label>
                      <div class="right-modal modal_items container_position_class">
                        <input type="text" name="modal_position_class" id="modal_position_class" class="form-control" value="">
                        <span class="container_position_class_icon modal_items_icon"></span>
                      </div>
                      <div class="container_position_class_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Người giới thiệu: </label>
                      <div class="right-modal modal_items container_presenter">
                        <input type="text" name="modal_presenter" id="modal_presenter" class="form-control" value="">
                        <span class="container_presenter_icon modal_items_icon"></span>
                      </div>
                      <div class="container_presenter_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Chức vụ: </label>
                      <div class="right-modal modal_items container_position_presenter">
                        <input type="text" name="modal_position_presenter" id="modal_position_presenter" class="form-control" value="">
                        <span class="container_position_presenter_icon modal_items_icon"></span>
                      </div>
                      <div class="container_position_presenter_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Phòng ban: </label>
                      <div class="right-modal modal_items container_department">
                        <input type="text" name="modal_department" id="modal_department" class="form-control" value="">
                        <span class="container_department_icon modal_items_icon"></span>
                      </div>
                      <div class="container_department_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Địa chỉ nhà: </label>
                      <div class="right-modal modal_items container_address">
                        <input type="text" name="modal_address" id="modal_address" class="form-control" value="">
                        <span class="container_address_icon modal_items_icon"></span>
                      </div>
                      <div class="container_address_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Ghi chú: </label>
                      <div class="right-modal modal_items container_description">
                        <input type="text" name="modal_description" id="modal_description" class="form-control" value="">
                        <span class="container_description_icon modal_items_icon"></span>
                      </div>
                      <div class="container_description_message modal_items_message"></div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Khu vực: </label>
                      <div class="right-modal modal_items container_area">
                        <input type="text" name="modal_area" id="modal_area" class="form-control" value="">
                        <span class="container_area_icon modal_items_icon"></span>
                      </div>
                      <div class="container_area_message modal_items_message"></div>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary btn-add-update-game-key">Save changes</button>
                  </div>
                </div>
              </div>
            </div>

            <!--Modal search-->
            <div class="modal fade" id="modal-id-search">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Search Student</h4>
                  </div>
                  <div class="modal-body">
                    <div class="form-group">
                      <label class="left-modal">Chọn trường bạn muốn search: </label>
                      <div class="right-modal modal_items">
                        <form id="caseForm">
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_student_codes" checked="checked">MSThẻ
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_student_class">Lớp
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_first_name">Tên
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_phone">Số điện thoại
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_presenter">Người giới thiệu
                            </label>
                          <label class="radio-inline">
                              <input type="radio" name="optradio" value="case_area">Khu vực
                            </label>
                        </form>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="left-modal">Nhập từ khóa: </label>
                      <div class="right-modal modal_items container_search_student_code">
                        <input type="text" name="modal_search_student_code" id="modal_search_student_code" class="form-control" value="">
                        <span class="container_search_student_code_icon modal_items_icon"></span>
                      </div>
                      <div class="container_search_student_code_message modal_items_message"></div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button type="button" class="btn btn-primary btn-search">Search</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Import file -->
            <div class="modal fade" id="modal-id-import">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Import User From Excel File</h4>
                  </div>
                  <div class="modal-body">
                    <div class="form-group" id='id_modal_icon'>
                      <label class="left-modal">File Student:</label>
                      <div class="right-modal modal_items container_icon">
                        <input type="file" id="fileName_excel" name="fileName_excel"><br>
                        <div class="message-upload-file"></div>
                        <div class="group-slide">
                          <button type="submit" class="btn btn-success upload-file">Upload file &nbsp; <span class="glyphicon glyphicon-upload"></span></button>
                        </div>
                        <span class="container_icon_icon modal_items_icon"></span>
                      </div>
                      <div class="container_icon_message modal_items_message"></div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button type="button" class="btn btn-primary btn-import-file-excel">Import</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </section>
          <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <div id="scrolltop" style="display: none;">
          <a href="#"></a>
        </div>

        <footer class="main-footer">

          <strong>Copyright &copy; 2014-2015 All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->

<script src="${root_url}plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${root_url}bootstrap/js/bootstrap.min.js"></script>
<script src="${root_url}custom/js/game.js"></script>
<script src="${root_url}plugins/fastclick/fastclick.js"></script>
<script src="${root_url}dist/js/app.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${root_url}plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${root_url}dist/js/demo.js"></script>
<script src="${root_url}custom/js/function.js"></script>
</body>
<style type="text/css">
  .message-upload-image {
    color: red;
  }
</style>
</html>
