<header class="main-header">
    <a href="/home" class="logo">
      <span class="logo-lg"><b>ISM - Mindset-based Transformation</b></span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${root_url}bootstrap/img/user1.png" class="user-image" alt="User Image">
              <span class="hidden-xs">${user.username}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${root_url}bootstrap/img/user1.png" class="img-circle" alt="User Image">

                <p>
                  ${user.username}
                  <small>${user.username}</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <form action="logout" method="GET" class="pull-right">
                  <button type="submit" class="btn btn-default btn-flat">Sign out</button>
                </form>
              </li>
            </ul>
          </li>

        </ul>
      </div>

    </nav>
  </header>
