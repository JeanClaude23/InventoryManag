<%-- 
    Document   : product
    Created on : Dec 18, 2021, 9:57:00 PM
    Author     : JClaude
--%>
<%@page import="java.util.Iterator"%>
<%@page import="DAO.GenericDao"%>
<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>product</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="bower_components/select2/dist/css/select2.min.css">
        <!-- Select2 -->
        <script src="bower_components/select2/dist/js/select2.full.min.js"></script>
        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <%
                if(session.getAttribute("user") == null){
                        response.sendRedirect("index.jsp");
                }
        %>
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="category.jsp" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini font-monospace"><b>IMS</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg font-monospace">IMS</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="category.jsp" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu" style="margin: 4px;">


                        <!-- /.messages-menu -->

                        <span class="hidden-xs" style="margin-right: 3px; color: white;"><%= (String) session.getAttribute("user") %></span> 

                        <button class="btn btn-info logout">Log out</button>


                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <!-- Optionally, you can add icons to the links -->
                        <li class="url"><a href="category.jsp"><i class="fa fa-qrcode"></i> <span>Category</span></a></li>
                        <li class="url"><a href="product.jsp"><i class="fa fa-list-alt"></i> <span>Stock</span></a></li>
                        <li class="url"><a href="purchase.jsp"><i class="fa fa-cart-arrow-down"></i> <span>Purchased</span></a></li>
                        <li class="url"><a href="sale.jsp"><i class="fa fa-shopping-cart"></i> <span>Sold </span></a></li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->

                <section class="content-header">

                    <h1>
                        Stock Items
                    </h1> 
                </section>

                <!-- Main content -->
                <section class="content container-fluid">

                    <div class="box">
                        <div id="Result" style="font-family: monospace;"></div>
                        <div class="box-header">
                            <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#modal-default">add</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Category</th>
                                        <th>Quantity</th>
                                        <th>Unit Cost</th>
                                        <th>Unit Price</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                            List<Product> products = new ArrayList<Product>();
                                            GenericDao<Product> dao = new GenericDao<Product>(Product.class);
						
                                            products = dao.findAll();
						
                                            for (Iterator iterator = products.iterator(); iterator.hasNext();) {
                                                    Product product = (Product) iterator.next();
                                                    DecimalFormat dfor = new DecimalFormat("###,###,###");
					
                                    %>
                                    <tr>
                                        <td><%= product.getId() %></td>
                                        <td><%= product.getName() %></td>
                                        <td><%= product.getCategory().getName() %></td>
                                        <td><%= product.getQuantity() %></td>
                                        <td><%= dfor.format(product.getUnit_cost()) %> Rwf</td>
                                        <td><%= dfor.format(product.getUnit_price()) %> Rwf</td>
                                        <td>
                                            <button class="btn btn-primary edit-row" data-id="<%= product.getId() %>" style="margin-right: 2px;" data-toggle="modal" data-target="#modal-edit">Edit</button>
                                            <button class="btn btn-danger delete-row" data-id="<%= product.getId()  %>"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>

                                    <%
                                                                    }
                                    %>

                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>

                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <div class="modal fade" id="modal-default">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <div id="showResult" style="font-family: monospace;"></div>
                            <h4 class="modal-title">Products</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="addProduct">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="categoryName" class="col-sm-2 control-label">Category</label>

                                        <div class="col-sm-10">
                                            <select class="form-control select2" id="categoryName" name="category" style="width: 100%;">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="productName" class="col-sm-2 control-label">Name</label>

                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="productName"  name="name" placeholder="Name">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="product_unit_price" class="col-sm-2 control-label">Unit Price</label>

                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="price" id="product_unit_price"  placeholder="Price">
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

            <!-- Edit Modal -->
            <div class="modal fade" id="modal-edit">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <div id="resultMessage" style="font-family: monospace;"></div>
                            <h4 class="modal-title">Products</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="editProduct">
                                <div class="box-body">

                                    <div class="form-group">
                                        <label for="Name" class="col-sm-2 control-label">Name</label>

                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name" id="Name"  placeholder="Product Name">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="unit_price" class="col-sm-2 control-label">Unit Price</label>

                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="price" id="unit_price"  placeholder="Price">
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Modify</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

            <!-- Main Footer -->
            <footer class="main-footer text-center ">

                <!-- Default to the left -->
                <strong><i class="fa fa-plug"></i> Powered by Jean Claude </strong>
            </footer>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Create the tabs -->
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
            </aside>
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
            immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- DataTables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="bower_components/fastclick/lib/fastclick.js"></script>
        <!-- Select2 -->
        <script src="bower_components/select2/dist/js/select2.full.min.js"></script>
        <!-- AdminLTE App -->
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
        <!-- page script -->
        <script>
            $(function () {
                $('#example1').DataTable()
                $('#example2').DataTable({
                    'paging': true,
                    'lengthChange': false,
                    'searching': false,
                    'ordering': true,
                    'info': true,
                    'autoWidth': false
                })
            })
        </script>

        <script>
            $(function () {
                //Initialize Select2 Elements
                $('.select2').select2()

            })
        </script>

        <script>
            $(document).ready(function () {

                $.ajax({
                    type: 'GET',
                    url: 'Categories',
                    datatype: 'json',
                    success: function (result) {

                        var categories = $.parseJSON(result);


                        let dropdown = $('#categoryName');

                        dropdown.empty();

                        dropdown.append('<option selected="true" disabled>Category</option>');
                        dropdown.prop('selectedIndex', 0);

                        // Populate dropdown with list of provinces

                        $.each(categories, function (key, entry) {
                            dropdown.append($('<option></option>').attr('value', entry.id).text(entry.name));
                        })

                    }});

            });

        </script>

        <script>

            $("#addProduct").on('submit', (function (e) {
                e.preventDefault();
                $.ajax({
                    url: "AddProduct",
                    type: "POST",
                    data: $('#addProduct').serialize(),
                    processData: false,
                    success: function (data) {

                        $('#showResult').addClass('alert alert-success');
                        $('#showResult').html(data).fadeIn().delay(2000).fadeOut();
                        ;
                        $('#addProduct')[0].reset();
                    },
                    error: function (err) {
                        $('#showResult').addClass('alert alert-warning');
                        $('#showResult').html(data).fadeIn().delay(2000).fadeOut();
                        $('#addProduct')[0].reset();

                    }
                })
            }));

        </script> 

        <script>
            $('.delete-row').click(function (e) {

                e.preventDefault();

                var pid = $(this).attr("data-id");

                $.ajax({
                    type: 'GET',
                    url: 'DeleteProduct',
                    data: {productId: pid},
                    datatype: 'json',
                    success: function (result) {


                        $('#Result').addClass('alert alert-success');
                        $('#Result').html(result);
                        setTimeout(() => window.location.reload(), 2000);

                    }});


            });

        </script>

        <script>
            $(document).ready(function () {

                var id = '';

                $('.edit-row').click(function (e) {
                    e.preventDefault();

                    id = $(this).attr("data-id");


                    $.ajax({
                        type: 'GET',
                        url: 'ProductById',
                        data: {productId: id},
                        datatype: 'json',
                        success: function (result) {

                            var data = $.parseJSON(result);

                            $('#Name').val(data.name);
                            $('#unit_price').val(data.unit_price);


                        }});


                });

                $("#editProduct").on('submit', (function (e) {
                    e.preventDefault();

                    var productId = id;
                    var name = $('#Name').val();
                    var price = $('#unit_price').val();
                    $.ajax({
                        type: 'GET',
                        url: 'ProductEdit',
                        data: {productId: productId, name: name, price: price},
                        datatype: 'json',
                        success: function (result) {

                            $('#resultMessage').addClass('alert alert-success');
                            $('#resultMessage').html(result);
                            setTimeout(() => window.location.reload(), 2000);

                        }});

                }));

                $(".logout").on('click', (function () {

                    $.ajax({
                        type: 'GET',
                        url: 'LogOut',
                        success: function (result) {

                            window.location.replace("index.jsp");


                        }});

                }));

            });

        </script>

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->
    </body>
</html>