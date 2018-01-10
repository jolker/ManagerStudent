<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-title" content="" />

<link rel="stylesheet" href="${root_url}bootstrap/js/jquery.fileupload.js">
<link rel="stylesheet" href="${root_url}bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="${root_url}dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${root_url}dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${root_url}custom/css/style.css">
<link rel="stylesheet" href="${root_url}custom/css/support.css">
<title>Support</title>
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
            <a href="/home"><span class="glyphicon glyphicon-home"></span> Home</a> >  Support
        </h4>
        <input type="hidden" id='current_game_id' data-game-id='0'>
    </section>

    <!-- Main content -->
    <section class="content">
            <div id="wrapper_container container-editor" class="width_common wrapper_tuong">
                <div class="row">
                	<div class="col-md-3">
                		<select name="select-state" id="select-state" class="form-control">
                			<option value="">-- Select State --</option>
                			<#list states as s>
								<option value="${s.getState()}">${s.name()}</option>
                			</#list>
                			
                		</select>
                	</div>
                	<div class="col-md-2">
                		<button type="button" class="btn btn-primary btn-getFeedback">Get feedback</button>
                	</div>
                </div>
                <div class="row" style="margin-top: 30px;">
                	<div class="col-md-3">
                		<button class="btn btn-success btn-previous" onclick="previous()" disabled><span class="glyphicon glyphicon-chevron-left"></span> &nbsp;Previous</button>
                		<button class="btn btn-success btn-next" onclick="next()" disabled>Next &nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
                	</div>
                	<div class="col-md-3"></div>
                	<div class="col-md-6">
                		<div id="txt-total-page" class="pull-right" style="font-size: 15px; padding-right: 40px;"></div>
                	</div>
                </div>
				<div class="container-body">
			      	<div class="table-responsive">
			       <table class="table table-hover table-support">
			        <thead>
			          <tr>
			           <th>ID</th>
			           <th>User</th>
			           <th>Type</th>
			           <th>Game</th>
			           <th>Time Error</th>
			           <th>State</th>
			           <th>Contact</th>
			           <th>Description</th>
			           <th>Handled By</th>
			           <th>Time handle</th>
			           <th>Delete</th>
			           <th>Handle</th>
			         </tr>
			       </thead>
			       <tbody id="support-body">
			       		
			        	
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

<!-- Modal send email-->
<div class="modal fade" id="modal-send-email">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
			<div class="form-group">
				<label for="email-send-to">Send to</label>
				<input type="text" name="email-send-to" id="email-send-to" class="form-control" required="required" placeholder="Send to">
			</div>
			<div class="form-group">
				<label for="email-subject">Subject</label>
				<input type="text" id="email-subject" class="form-control" required="required" placeholder="Subject">
			</div>
				<textarea name="email-body" id="email-body"></textarea>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary btn-send-email">Send</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal send email-->

<!-- modal Delete -->
<div class="modal fade" id="modal-delete">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Delete</h4>
            </div>
            <div class="modal-body">
                Are you sure delete it? <strong id='username'></strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary btn-delete-modal" data-id = "">Delete</button>
            </div>
        </div>
    </div>
</div> <!-- end modal Delete -->

</div>

<script src="${root_url}plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${root_url}bootstrap/js/bootstrap.min.js"></script>
<script src="${root_url}plugins/fastclick/fastclick.js"></script>
<script src="${root_url}dist/js/app.min.js"></script>
<script type="text/javascript" src="${root_url}/editor/tinymce.min.js"></script>
<script type="text/javascript" src="${root_url}/editor/jquery.tinymce.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${root_url}plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${root_url}dist/js/demo.js"></script>
<script src="${root_url}custom/js/support.js"></script>
<script src="${root_url}custom/js/function.js"></script>
<script src="${root_url}plugins/datepicker/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="${root_url}plugins/datepicker/datepicker3.css" />
<!-- /page -->
</body>

</html>
