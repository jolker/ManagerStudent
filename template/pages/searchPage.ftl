<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ISM - Mindset-based Transformation | Search</title>
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
      <a href="/home"><span class="glyphicon glyphicon-home"></span> Home</a> >  Search

      </h4>

          </section>

          <!-- Main content -->
          <section class="content">
            <div class="container-fluid" style="margin-top: 35px;">
              <button type="button" class="btn btn-primary btn-export-file" data-toggle="modal">Export &nbsp;&nbsp; <span class="glyphicon glyphicon-plus"></span></button>
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
                      <#if student??>
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
                        </tr>
                      <#elseif students??>
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
                          </tr>
                        </#list>
                      <#else>
                        <tr>
                          <td><p>No result</p></td>
                        </tr>
                      </#if>
                    </tbody>
                  </table>
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
