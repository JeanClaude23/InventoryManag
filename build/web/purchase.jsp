<%-- 
    Document   : purchase
    Created on : Jan 7, 2022, 7:41:38 AM
    Author     : JClaude
--%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Purchase"%>
<%@page import="java.util.List"%>
<%@page import="DAO.GenericDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Purchase Form</title>
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
                        <li class="url"><a href="sale.jsp"><i class="fa fa-shopping-cart"></i> <span>Sold</span></a></li>
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
                        Items Purchased
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content container-fluid">

                    <div class="box">
                        <div class="box-header">
                            <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#modal-default">add</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Date Product Purchased</th>
                                        <th>Total Cost</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%
                                            List<Purchase> purchases = new ArrayList<Purchase>();
                                            GenericDao<Purchase> dao = new GenericDao<Purchase>(Purchase.class);
                	
                                            purchases  = dao.findAll();
                	
                                            for (Iterator iterator = purchases.iterator(); iterator.hasNext();) {
                                                                    Purchase purchase = (Purchase) iterator.next();
						
                                                                    Date myDateObj = purchase.getDate();
						
                                                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						
                                                                    ZoneId defaultZoneId = ZoneId.systemDefault();
						
                                                                    Instant instant = myDateObj.toInstant();
						
                                                                    LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
						
                                                                    String formattedDate = localDate.format(myFormatObj);
                                          DecimalFormat dfor = new DecimalFormat("###,###,###");
                                    %>

                                    <tr>
                                        <td><%= purchase.getId() %></td>
                                        <td><%= formattedDate %></td>
                                        <td><%= dfor.format(purchase.getTotal_cost()) %> Rwf</td>
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
                            <h4 class="modal-title">Purchase</h4>
                        </div>
                        <div class="modal-body">
                            <form id="regPurchase" role="form">
                                <div class="box-body ">

                                    <div class="form-group col-sm-3">
                                        <label for="categoryName" class="col-sm-2 control-label">Categories</label>
                                        <select class="form-control select2" id="categoryName" name="category" style="width: 100%;">

                                        </select>
                                    </div>

                                    <div class="form-group col-sm-3">
                                        <label for="productName" class="col-sm-2 control-label">Products</label>
                                        <select class="form-control select2" id="productName" name="product" style="width: 100%;">

                                        </select>
                                    </div>

                                    <div class="form-group col-sm-3">
                                        <label for="purchaseQuantity">Quantity</label>
                                        <input type="text" name="quantity" class="form-control" id="purchaseQuantity" placeholder="Quantity">
                                    </div>

                                    <div class="form-group col-sm-3">
                                        <label for="purchaseUnitCost">Unit Cost</label>
                                        <input type="text" name="cost" class="form-control" id="purchaseUnitCost" placeholder="Unit Cost">
                                    </div>

                                    <button class="btn btn-primary" id="add">Add</button>

                                </div>
                                <!-- /.box-body -->

                                <div class="box">
                                    <div class="box-header">
                                        <h3 class="box-title">Added Products</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <table id="myTable" class="table table-striped table-hover">
                                            <tr>
                                                <th>Product</th>
                                                <th>Quantity</th>
                                                <th>Unit Cost</th>
                                                <th>remove</th>

                                            </tr>

                                        </table>
                                    </div>
                                    <!-- /.box-body -->
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


                $("#categoryName").on('change', (function (e) {

                    var selectedCategory = $(this).children("option:selected").val();

                    $.ajax({
                        type: 'GET',
                        url: 'Products',
                        data: {categoryId: selectedCategory},
                        datatype: 'json',
                        success: function (result) {

                            var products = $.parseJSON(result);


                            let dropdown = $('#productName');

                            dropdown.empty();

                            dropdown.append('<option selected="true" disabled>Product</option>');

                            dropdown.prop('selectedIndex', 0);

                            // Populate dropdown with list of provinces

                            $.each(products, function (key, entry) {
                                dropdown.append($('<option></option>').attr('value', entry.id).text(entry.name));
                            })

                        }});

                }));



            });

        </script>

        <script>

            $(document).ready(function () {

                let purchase = [];

                var counter = 0;

                $('#add').click(function (e) {



                    e.preventDefault();

                    var product = $('#productName').val();
                    var quantity = $('#purchaseQuantity').val();
                    var cost = $('#purchaseUnitCost').val();
                    if (product == '' || quantity == '' || cost == '')
                    {
                        $('#showResult').html('all fields is required').addClass('alert alert-warning');
                    } else
                    {
                        let record = {

                            id: product,
                            quantity: quantity,
                            unit_cost: cost
                        }

                        purchase.push(record);

                        $('#regPurchase')[0].reset();


                        for (var i = counter; i < purchase.length; i++) {

                            var row = $('<tr><td>' + purchase[i].id + '</td><td>' + purchase[i].quantity + '</td><td>' + purchase[i].unit_cost + '</td><td><button class="btn btn-danger delete-row" data-id= "' + i + '"><i class="fa fa-trash"></i></button></tr>');


                            $('#myTable').append(row);


                        }
                        counter++;


                    }


                });

                $('.delete-row').click(function (e) {

                    e.preventDefault();

                    var pid = $(this).attr("data-id");

                    purchase.splice(pid, 1);

                });


                $("#regPurchase").on('submit', (function (e) {
                    e.preventDefault();


                    var purchaseData = JSON.stringify(purchase);


                    $.ajax({
                        type: 'GET',
                        url: 'AddPurchase',
                        data: {purchaseData: purchaseData},
                        datatype: 'json',
                        success: function (result) {

                            $('#showResult').addClass('alert alert-success');
                            $('#showResult').html(result);
                            setTimeout(() => window.location.reload(), 3000);

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
    </body>
</html>
