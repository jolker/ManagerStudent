<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>XGame | Users Admin Tool</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, charset=UTF-8, name=viewport">
  <link rel="stylesheet" href="${root_url}bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${root_url}dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${root_url}dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${root_url}custom/css/userAdminTool.css">
  <link rel="stylesheet" href="${root_url}custom/css/style.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    <!-- Header Navbar: style can be found in header.less -->
    <#include "header.ftl">
      <#include "leftSidebar.ftl">
        <!-- Left side column. contains the logo and sidebar xxx -->
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
          <!-- Content Header (Page header) -->
          <section class="content-header">
            <h4>
                <a href="/home"><span class="glyphicon glyphicon-home"></span> Home</a> >  User Admin Tool
            </h4>

          </section>

          <!-- Main content -->
          <section class="content">
            <div class="container-fluid" style="margin-top: 35px;">
              <button type="button" class="btn btn-primary" onclick="showAddModal()" data-toggle="modal" href='#modal-id-add-update'>Add User &nbsp;&nbsp; <span class="glyphicon glyphicon-plus"></span></button>
              <div class="container-body table-group-user-admin">

                <div class="table-responsive">
                  <table class="table table-hover table-user-admin">
                    <thead>
                      <tr>
                        <th>S.NO</th>
                        <th>Username</th>
                        <th>Active</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody id="table-body-admin">
                      <#assign count=1>
                        <#list users as user>
                          <tr>
                            <td>${count}</td>
                            <td id='username-${count}'>
                              ${user.username}
                            </td>

                            <td>
                              <#if user.active==true>
                                <input id='active-${count}' type="checkbox" checked disabled="">
                                <#else>
                                  <input id='active-${count}' type="checkbox" disabled="">
                              </#if>
                            </td>
                            <td>
                              <button class="btn btn-success" onclick="showUpdateModal('${count}')" data-toggle="modal" href='#modal-id-add-update'>Update</button>&nbsp;&nbsp;
                              <button class="btn btn-warning" onclick="showDeleteModal('${count}')" data-toggle="modal" href='#modal-id-delete'>Delete</button>
                            </td>
                            <#assign count=count+1>
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
                    <li><a href="/userAdminTool?page=${i}">${i}</a></li>
                  </#list>
                </ul>
              </div>
            </div>
          </section>
          <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <!--Modal add or update xxx-->
        <div class="modal fade" id="modal-id-add-update">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"></h4>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="left-modal">Username:</label>
                  <div class="right-modal modal_items container_username">
                    <input type="text" name="modal_username" id="modal_username" class="form-control" value="">
                    <span class="container_username_icon modal_items_icon"></span>
                  </div>
                  <div class="container_username_message modal_items_message"></div>
                </div>
                <div class="form-group">
                  <label class="left-modal">Password: </label>
                  <div class="right-modal modal_items container_password">
                    <input type="password" name="modal_password" id="modal_password" class="form-control" value="">
                    <span class="container_password_icon modal_items_icon"></span>
                  </div>
                  <div class="container_password_message modal_items_message"></div>
                </div>
                <!--select active-->
                <div class='form-group'>
                  <div class="right-modal">
                    <div class="checkbox">
                      <label>
                        <input id='modal_active' class='chk_active' type='checkbox'>
                        <strong>  Active</strong>
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary btn-add-update-user-admin-tool">Save changes</button>
              </div>
            </div>
          </div>
        </div>
  </div>
  </div>



  <!--Modal add or update xxx-->
  <div class="modal fade" id="modal-id-delete">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Delete User</h4>
        </div>
        <div class="modal-body">
          Are your sure delete this user? <strong id='username-delete'></strong>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary btn-delete">Delete</button>
        </div>
      </div>
    </div>
  </div>
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
<script src="${root_url}plugins/fastclick/fastclick.js"></script>
<script src="${root_url}dist/js/app.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${root_url}plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${root_url}dist/js/demo.js"></script>
<script src="${root_url}custom/js/userAdminTool.js"></script>
<script src="${root_url}plugins/datepicker/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="${root_url}plugins/datepicker/datepicker3.css" />
<script src="${root_url}custom/js/function.js"></script>
</body>
</html>
