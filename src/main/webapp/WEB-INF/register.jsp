<%--
  Created by IntelliJ IDEA.
  User: carol
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="includes/css/styles.css" type="text/css" media="screen">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container-fluid">
    <div class="modal-content">
        <div class="modal-body">
            <div class="form-title text-center">
                <h4>Sign up for DevDebate</h4>
            </div>
            <div class="d-flex flex-column text-center">
                <form name="register" action="ServletController" method="post" novalidate>
                    <input type="hidden" name="cmd" value="user">
                    <input type="hidden" name="usercmd" value="register">
                    <div class="form-group">
                        <input type="text" class="form-control" name="r_username"
                               placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="r_email"
                               placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="r_password"
                               placeholder="Password" id="r_password" required>
                        <div class="password-meter-wrap">
                            <div class="password-meter-bar"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="r_confirm_password"
                               placeholder="Repeat password" id="r_confirm_password" required>
                        <div class="password-meter-wrap">
                            <div class="password-meter-bar"></div>
                        </div>
                    </div>
                    <div class="g-recaptcha" data-sitekey="${siteKey}"></div>
                    <p style="color:red"><c:out value="${errorMsg}"/></p>
                    <button type="submit" class="btn btn-info btn-block btn-round" id="r_submit">Register</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="includes/js/zxcvbn.js"></script>
<script src="includes/js/password-meter.js"></script>
<script>
    $(document).ready(function(){
        var $submitBtn = $("#r_submit");
        var $passwordBox = $("#r_password");
        var $confirmBox = $("#r_confirm_password");
        var $errorMsg =  $('<span style="color:red" id="error_msg">Passwords do not match.</span>');

        $submitBtn.removeAttr("disabled");

        function checkMatchingPasswords(){
            if($confirmBox.val() !== "" || $passwordBox.val() !== ""){
                if( $confirmBox.val() !== $passwordBox.val() ){
                    $submitBtn.attr("disabled", "disabled");
                    $errorMsg.insertAfter($confirmBox);
                }
            }
        }

        function isCorrectPattern(str) {
            var regex = /^(?=.*\d)(?=.*[<>!"#¤%&/()=?`,.*^ }{€$£@])(?=.*[a-zæøå])(?=.*[A-ZÆØÅ]).{10,64}$/;
            return regex.test(str);
        }

        function resetPasswordError(){
            $submitBtn.removeAttr("disabled");
            var $errorCont = $("#error_msg");
            if($errorCont.length > 0){    $errorCont.remove();
            }
        }

        function resetPatternError(){
            $submitBtn.removeAttr("disabled");
            var $errorCont = $("#pattern_error");
            if($errorCont.length > 0){    $errorCont.remove();
            }
        }

        $("#r_confirm_password, #r_password")
            .on("blur", function(){
                if (!isCorrectPattern($passwordBox.val())) {
                    var $patternError =  $('<span style="color:red" id="pattern_error">' +
                        'Your password must be at least 10 characters long and should contain at least 1 number, ' +
                        '1 special symbol, 1 uppercase and 1 lowercase letter. ' +
                        "The password can't be longer than 64 characters! </span>");
                    $submitBtn.attr("disabled", "disabled");
                    $patternError.insertAfter($confirmBox);
                }
                checkMatchingPasswords();
            })
            .on("focus", function(){
                if (isCorrectPattern($passwordBox.val())) {
                    resetPatternError();
                }
                resetPasswordError();
            })
    });
</script>
</body>
</html>
