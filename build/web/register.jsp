<%-- 
    Document   : signup
    Created on : Dec 18, 2021, 9:57:42 PM
    Author     : JClaude
--%>
<%@page import="DAO.GenericDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Registration Page</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition register-page" style="background-image: url('images/signup.jpg'); background-size: cover;">
        <div class="register-box">
            <div class="register-logo">
                <a href="index.jsp" style="color: black; font-family: cursive;">Inventory Management System</a>
            </div>

            <div class="register-box-body">
                <div id="showResult"></div>
                <p class="login-box-msg" style="color: black; font-family: cursive;">Register a new membership</p>

                <form id="registration">
                    <div class="form-group has-feedback">
                        <input type="text" name="username" class="form-control" placeholder="User Name">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="email" name="email" class="form-control" placeholder="Email">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="password" class="form-control" placeholder="Password">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="retypepassword" class="form-control" placeholder="Retype password">
                        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                    </div>
                    <div class="row">

                        <!-- /.col -->
                        <div class="col-xs-12">
                            <%
                                    List<Account> user = new ArrayList<Account>();
                                    GenericDao<Account> dao = new GenericDao<Account>(Account.class);
                                    user = dao.findAll();
                                    int size = user.size();
                            %>
                            <button type="submit" data-size="<%= size %>" class="btn btn-primary btn-block btn-flat size" style="font-family: cursive;">Register</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

                <center><a href="index.jsp" class="text-center" style="font-family: cursive;">I already have a membership</a></center>
            </div>
            <!-- /.form-box -->
        </div>
        <!-- /.register-box -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.min.js"></script>
        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' /* optional */
                });
            });
        </script>
        <script>
            $("#registration").on('submit', (function (e) {
                e.preventDefault();
                $.ajax({
                    url: "SignUp",
                    type: "POST",
                    data: $('#registration').serialize(),
                    processData: false,
                    success: function (data) {
                        window.location.replace("index.jsp");
                    },
                    error: function (err) {
                        $('#showResult').addClass('alert alert-warning');
                        alert(err);
                    }
                })
            }));
        </script> 

        <script>
            $(document).ready(function () {

                var btn = $('.size');
                var size = btn.attr("data-size");

                if (size > 0) {
                    btn.prop('enabled', true);
                }
            });

        </script> 
    </body>
</html>
