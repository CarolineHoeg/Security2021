<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Frontpage</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
    <h1>Frontpage</h1>

    <p style="color:red">${errorMsg}</p>

    <div class="container">
        <button type="button" class="btn btn-info btn-round" data-toggle="modal" data-target="#loginModal">
            Login
        </button>
    </div>

    <!-- Login modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header border-bottom-0">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-title text-center">
                        <h4>Login</h4>
                    </div>
                    <div class="d-flex flex-column text-center">
                        <form name="login" action="ServletController" method="post">
                            <input type="hidden" name="cmd" value="login">
                            <div class="form-group">
                                <input type="text" class="form-control" name="l_username" placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="l_password" placeholder="Password" required>
                            </div>
                            <button type="submit" class="btn btn-info btn-block btn-round">Login</button>
                        </form>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <div class="signup-section">Not a member yet?
                        <button type="button" class="sign-up-btn" data-toggle="modal" data-target="#registerModal"> Sign up.</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Registration modal -->
        <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header border-bottom-0">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-title text-center">
                            <h4>Register</h4>
                        </div>
                        <div class="d-flex flex-column text-center">
                            <form name="register" action="ServletController" method="post">
                                <input type="hidden" name="cmd" value="register">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="r_username" placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="r_password" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="r_control_password" placeholder="Repeat password" required>
                                </div>
                                <button type="submit" class="btn btn-info btn-block btn-round">Register</button>
                            </form>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center"></div>
                </div>
            </div>
        </div>
    </div>
    <script src='https://code.jquery.com/jquery-3.3.1.slim.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js'></script>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>
    <script  src="js/script.js"></script>
    </body>
</html>
