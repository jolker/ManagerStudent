
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Login ISM - Mindset-based Transformation Admin</title>

    <link rel="stylesheet" type="text/css" href="${root_url}bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${root_url}custom/css/login.css" />
</head>

<body>
    <div class="container">
        <div class="login-header">
            <div class="login-title">
                <div>ISM - Mindset-based Transformation</div>
                <!-- <em style="font-weight: 300; font-size: 20px;">Tháo ơi đừng chạy</em> -->
            </div>
        </div>
        <form action="login" method = "POST" class="form-horizontal" role="form">

            <#if errorMessage??>
               <div class="error-message">
                <div class="alert alert-danger fade in">
                   ${errorMessage}
                </div>
              </div>
            </#if>
            <div class="form-group" style="margin-bottom: 20px;">
                <div class="col-md-12">
                    <input class="form-control input-lg" id="username" name="username" placeholder="Username" autofocus/>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 25px;">
                <div class="col-md-12">
                    <input type="password" class="form-control input-lg" id="password" name="password" placeholder="Password" />
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 10px;">
                <div class="col-md-12">

                    <button class="btn btn-lg btn-primary" type="submit" style="float: right;">Login</button>
                </div>
            </div>

        </form>
    </div>
</body>

</html>
