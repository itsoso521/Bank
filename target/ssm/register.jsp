<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition register-page">
    <div class="register-box">
        <div class="register-logo">


        </div>

        <div class="register-box-body">
            <p class="login-box-msg">新用户注册</p>

            <form action="all-admin-index.html" method="post" id="registerForm">
                <div class="form-group has-feedback">
                    <input type="text" class="form-control" name="username" id="username" placeholder="全名">
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" class="form-control" name="pd" id="pd" placeholder="确认密码">
                    <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="col-xs-8">
                        <div class="checkbox icheck">
                            <label>

            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>



            <a href="login.jsp" class="text-center">我有账号，现在就去登录</a>
        </div>
        <!-- /.form-box -->
    </div>
    <!-- /.register-box -->

    <!-- jQuery 2.2.3 -->
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });
    </script>
 <script>

 function checkPassword(){
      var password=$("#password").val();
      var pd=$("#pd").val();
      var msg="";
      var flag=true;
      if(password==null||password==""||password==undefined||pd==null||pd==""||pd==undefined){
         alert("密码不能为空！");
         return false;
      }
      if(password!=pd){
         msg="两次输入的密码不一致！";
         flag=false;
      }
      if(!flag){
        alert(msg);
      }
      return flag;
    }
  function checkEmail(){
    var email=$("#email").val();
    var f=true;
    if(email==null||email==""||email==undefined){
         msg="邮箱不能为空！";
          f=false;
    }
    if(!f){
            alert(msg);
          }
          return f;

  }
   function checkUsername(){
      var email=$("#username").val();
      var f=true;
      if(email==null||email==""||email==undefined){
           msg="用户名不能为空！";
            f=false;
      }
      if(!f){
              alert(msg);
            }
            return f;

    }
    // 全局变量，指明是否能提交
      var uf=false;
      $("#username").blur(function (){
          uf=false;
          var trans=$("#username").val();
          if(trans == "" || trans== null || trans == undefined){
               alert("转账用户没有输入，请重新输入！");
              uf=false;
          }
          else{
           $.get("${pageContext.request.contextPath}/user/user.do",{trans:trans},function(data){
                   if(data.status==1){
                      alert("用户已经存在，请重新输入！");
                      uf=false;

                   }else{
                      uf=true;
                   }
                 },"json")

          }

      });
 $(function () {
                   //当表单提交时，调用所有的校验方法
   				$("#registerForm").submit(function(){
   					//1.发送数据到服务器
   					if(uf&&checkEmail()&&checkPassword()){
   					    //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
                         var username=$("#username").val();
                         var password=$("#password").val();
                         var email=$("#email").val();
   						$.post("${pageContext.request.contextPath}/user/add2.do",{username:username,password:password,email:email},function(data){
                                location.href="${pageContext.request.contextPath}/login.jsp"
   						});

   					}
   					//2.不让页面跳转
                       return false;
                       //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
   				});
   				});

 </script>
</body>

</html>