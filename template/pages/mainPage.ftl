<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ISM - Mindset-based Transformation | XCT</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${root_url}bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${root_url}dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${root_url}dist/css/skins/_all-skins.min.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <#include "header.ftl">
  <#include "leftSidebar.ftl">

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h3>
        Welcome to ISM - Mindset-based Transformation Page

      </h3>

    </section>

    <!-- Main content -->
    <section class="content">
    <div class="container-fluid">
      <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10">
           <div id="myCarousel" class="col-md-12 carousel slide" data-ride="carousel" style="border: 5px solid #fff;">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
              <li data-target="#myCarousel" data-slide-to="1"></li>
              <li data-target="#myCarousel" data-slide-to="2"></li>
              <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img src="${root_url}img/n1.png" alt="Chania">
              </div>

              <div class="item">
                <img src="${root_url}img/n2.png" alt="Chania">
              </div>

              <div class="item">
                <img src="${root_url}img/n3.png" alt="Flower">
              </div>

              <div class="item">
                <img src="${root_url}img/n4.png" alt="Flower">
              </div>

            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
          </div>
        <div class="col-md-1"></div>
      </div>
    </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">

    <strong>Copyright &copy; 2014-2015 </strong> All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->

<script src="${root_url}plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${root_url}bootstrap/js/bootstrap.min.js"></script>
<script src="${root_url}plugins/fastclick/fastclick.js"></script>
<script src="${root_url}dist/js/app.min.js"></script>
<script src="${root_url}plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${root_url}plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${root_url}plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${root_url}plugins/chartjs/Chart.min.js"></script>
<script src="${root_url}dist/js/demo.js"></script>
</body>
</html>
