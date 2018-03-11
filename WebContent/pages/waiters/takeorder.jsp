<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>${ORDER_SYS_NAME}-餐厅管理员</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/dashboard.css">


    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="bootstrap/js/jquery-1.11.1.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>


    <script src="jquery/syq-pagination1.0.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/ajax.js"></script>
    <script type="text/javascript">
        /*function begin() {
            getDishesList(1);
            txtAjaxRequest("getrtdishes.order?messageTitle=rtdishes&time="
                + Math.random(), "get", true, null, dishesCallback, null, null);
            txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
                + Math.random(), "get", true, null, bordCallback, null, null);
        }
*/
        /*function bordCallback(responseTxt, obj) {
            if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
                var msg = document.getElementById("msg");

                msg.innerHTML = responseTxt;

            }
            txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
                + Math.random(), "get", true, null, bordCallback, null, null);

        }
        function dishesCallback(responseTxt, obj) {
            if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
                var msg = document.getElementById("msg");
                msg.innerHTML = responseTxt;

            }
            txtAjaxRequest("getrtdishes.order?messageTitle=rtdishes&time="
                + Math.random(), "get", true, null, dishesCallback, null, null);


        }



        function getDishesList(page) {

            xmlAjaxRequest("getdishesbypage.order?page=" + page + "&time="
                + Math.random(), "get", true, null, showList, null, null);
        }*/

        /*function showList(responseXml, obj) {

            var maxPage = responseXml.getElementsByTagName("maxPage");
            maxPage = maxPage[0].childNodes;
            //alert(maxPage[0].nodeValue);
            var link = document.getElementById("last");
            link.href = "javascript:getDishesList(" + maxPage[0].nodeValue + ")";

            var page = responseXml.getElementsByTagName("page");
            page = page[0].childNodes;
            link = document.getElementById("next");
            link.href = "javascript:getDishesList("
                + (parseInt(page[0].nodeValue) + 1) + ")";
            link = document.getElementById("pre");
            link.href = "javascript:getDishesList("
                + (parseInt(page[0].nodeValue) - 1) + ")";
            var table = document.getElementById("dishesbord");
            table.innerHTML = "";
            var disheses = responseXml.getElementsByTagName("dishes");
            for (var i = 0; i < disheses.length; i++) {
                var dishes = disheses[i];
                var attrs = dishes.childNodes;
                var dishesId;
                var dishesName;
                var dishesDiscript;
                var dishesImg;
                var dishesTxt;
                var recommend;
                var dishesPrice;
                for (var j = 0; j < attrs.length; j++) {
                    var attr = attrs[j];
                    if (attr.nodeName == "dishesId") {
                        dishesId = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "dishesName") {
                        dishesName = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "dishesDiscript") {
                        dishesDiscript = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "dishesImg") {
                        dishesImg = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "dishesTxt") {
                        dishesTxt = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "recommend") {
                        recommend = attr.childNodes[0].nodeValue;
                    }
                    if (attr.nodeName == "dishesPrice") {
                        dishesPrice = attr.childNodes[0].nodeValue;
                    }
                }

                var subDiscript = dishesDiscript.length > 8 ? dishesDiscript
                        .substring(0, 7)
                    + "..." : dishesDiscript;
                var subRecommend = recommend == 1 ? "<span style='color: red;font-size: 12px'><i class='icon-heart '></i></span>&nbsp;"
                    : "";
                var newLine = "<div class='col-xs-6 col-sm-3 placeholder'>";
                newLine += "<a href=javascript:onclick=detail(\""
                    + dishesName
                    + "\",\""
                    + dishesDiscript
                    + "\",\""
                    + dishesTxt
                    + "\","
                    + dishesPrice
                    + ","
                    + recommend
                    + ",\""
                    + dishesImg
                    + "\")> <img class='img-thumbnail'	style='border-radius:20px;width:95%;height:200px' alt='Generic placeholder thumbnail' src='img/dishes/"
                    + dishesImg + "'></a>";
                newLine += "<h4>" + subRecommend + dishesName + "</h4>";
                newLine += "<span class='text-muted'>" + subDiscript + "</span>";
                newLine += "<div class='form-group'>";
                newLine += "<form action='addcart.order' target='formtarget' onsubmit='alert(\"添加成功！\")' method='post'>";
                newLine += "<div style='width:120px;margin: 0px auto'>";
                newLine += "<div class='input-group'>";
                newLine += "<span class='input-group-btn'>";
                newLine += "<button class='btn btn-default' type='button'	onclick='subtract(this)'>-</button></span> ";
                newLine += "<input type='text' class='form-control' value='1'  readonly='readonly' name='num' style='text-align: center;padding: 0px;cursor: text;'>";
                newLine += "<input type='hidden' name='dishes' value='" + dishesId + "' /> <span class='input-group-btn'>";
                newLine += "<button class='btn btn-default' type='button' onclick='add(this)'>+</button> </span> </div>";
                newLine += "</div>";
                newLine += "<p> <input type='submit' class='btn btn-danger' style='width:120px;margin-top: 5px' value='加入点餐车' /> </p> </form> </div> </div>";

                table.innerHTML += newLine;

            }

        }*/


        function detail(name, disc, txt, price, commend, img) {

            var dishesName = document.getElementById("dishesName");
            var dishesDiscript = document.getElementById("dishesDiscript");
            var dishesTxt = document.getElementById("dishesTxt");
            txt = txt.replace(/ordersysspace/g, "&nbsp;");
            txt = txt.replace(/ordersysbreak/g, "<br>");

            var dishesPrice = document.getElementById("dishesPrice");
            var recommend = document.getElementById("recommend");
            var dishesImg = document.getElementById("dishesImg");
            dishesImg.src = "img/dishes/" + img;
            dishesName.innerHTML = name;
            dishesDiscript.innerHTML = disc;
            dishesTxt.innerHTML = txt;
            dishesPrice.innerHTML = price;
            if (commend == 1) {
                recommend.innerHTML = "<i class='icon-heart icon-large'></i>&nbsp;推荐菜品";
            } else {
                recommend.innerHTML = "";
            }
            $("#myModal").modal("show");
        }

        function subtract(obj) {
            var form = obj.parentNode.parentNode.parentNode.parentNode;
            if (parseInt(form.num.value) >= 1) {
                form.num.value = parseInt(form.num.value) - 1;
            }
        }

        function add(obj) {
            var form = obj.parentNode.parentNode.parentNode.parentNode;
            form.num.value = parseInt(form.num.value) + 1;

            //var child=form.
            //txt[0].value+=1;
        }


        function setTableId() {
            var id = document.getElementById("tableId").value;
            alert("桌号设置成功");
            var result = document.getElementById("tableresult");
            result.innerHTML = id;
            usersAjax(0,5);


        }
        function usersAjax(pageIndex,everyPageDataCount){//初始化数据
            var id = $("#tableresult").html();
            $.ajax({
                type: "POST",
                url: "SetTableIdServlet",
                data: {
                    pageIndex: pageIndex,//当前第几页（0序列）
                    everyPageDataCount:everyPageDataCount,//每一页多少条数据
                    tableid: id,
                },
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //window.location.href = "/paging/PagingServlet";
                },
                success: function (json) {
                    if(json.myData!=""){
                        var listHtml="<thead> " +
                            "<tr> " +
                            "<th>桌号</th> " +
                            "<th>菜品</th> " +
                            "<th>数量</th> " +
                            "</tr> " +
                            "</thead> " +
                            "<tbody id='orderTable'>";
                        for(var i=0;i<json.myData.length;i++){
                            /*listHtml+="<span>"+json.myData[i].proposer+"</span><br>"	;*/
                            listHtml+="<tr>"+

                                "<td>"+ id +"</td>"+
                                "<td>"+json.myData2[i].dishesName+"</td>"+
                                "<td>"+json.myData[i].num+"</td>"+

                                +"</tr>";
                        }
                        listHtml+="</tbody>";
                        $("#listDivId").html(listHtml);
                        $("#orderdetail").show();
                        pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex)
                    }else{
                        var listHtml="<thead> " +
                            "<tr> " +
                            "<th>桌号</th> " +
                            "<th>菜品</th> " +
                            "<th>数量</th> " +
                            "</tr> " +
                            "</thead> " +
                            "<tbody id='orderTable'>";
                        listHtml+="</tbody>";
                        $("#listDivId").html(listHtml);
                        $("#orderdetail").hide();

                    }



                }
            });
        }
        function pagingAjax(dataCount,everyPageDataCount,nowPage){
            $("#pagingDivId").zcPage({
                allDataCount: dataCount,//一共有多少条数据
                everyPageDataCount: everyPageDataCount,//每一页显示多少条数据
                nowPageCataCount:nowPage,//当前是第几页
                success: function (nowPageCataCount/*当前是第几页*/) {
                    usersAjax(nowPageCataCount,everyPageDataCount)

                },
            });
        }

        function commitOrder() {
            var id = $("#tableresult").html();
            var dishesid=new Array();
            var dishes = $(".dishesid");
            for(var i=0;i<dishes.size();i++)
                dishesid[i] = dishes[i].value;

            var nums=new Array();
            var num = $(".num");
            for(var j=0;j<num.size();j++)
                nums[j] = num[j].value;
            if(id!="") {
                $.ajax({
                    type: "POST",
                    url: "CommitCartServlet",
                    traditional: true,
                    data: {
                        tableid: id,
                        dishes: dishesid,
                        num: nums,

                    },
                    dataType: "json",
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                    },
                    success: function (json) {
                        if (json.strMessage == "") {
                            usersAjax(0,5);


                        } else {

                            //登录不成功显示消息


                        }
                    }
                });

                alert("订单提交成功，用餐愉快！");
            }
            else
                alert("请输入桌号");
        }

       $(document).ready(function () {
            $("#orderdetail").hide();
           var dishes = $(".dishesid").val();
           if(dishes==""||dishes==null){
               window.location.href="ToWaiterMainServlet";
           }
           /* usersAjax2(0,4);//初始化页面数据（当前是第1页，每一页显示5条数据）*/
        });
        /*
        function usersAjax2(pageIndex,everyPageDataCount){//初始化数据

            $.ajax({
                type: "POST",
                url: "ToWaiterMainServlet",
                data: {
                    pageIndex: pageIndex,//当前第几页（0序列）
                    everyPageDataCount:everyPageDataCount,//每一页多少条数据

                },
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //window.location.href = "/paging/PagingServlet";
                },
                success: function (json) {
                    if(json.dish!=""){
                        var listHtml="";
                        for(var i=0;i<json.dish.length;i++){
                            /!*listHtml+="<span>"+json.myData[i].proposer+"</span><br>"	;*!/
                            listHtml+=" <div class='col-xs-6 col-sm-3 placeholder' >"+
                           " <a  onclick='detail(\""
                                + json.dish[i].dishesName
                                + "\",\""
                                + json.dish[i].dishesDiscript
                                + "\",\""
                                + json.dish[i].dishesTxt
                                + "\","
                                + json.dish[i].dishesPrice
                                + ","
                                + json.dish[i].recommend
                                + ",\""
                                + json.dish[i].dishesImg + "\")'>"+

                                "<input type='hidden' class='dishesid' value='"+json.dish[i].dishesId+"'>"+
                                " <img class='img-thumbnail'"+
                                " style='border-radius:20px;width:95%;height:300px' alt='Generic placeholder thumbnail'"+
                                " src='img/dishes/"+json.dish[i].dishesImg+"'></a>"+
                                "<h4>"+json.dish[i].dishesName+"</h4>"+
                                "<span class='text-muted'>"+json.dish[i].dishesDiscript+"</span>"+
                                "<div class='form-group'>"+
                                "<form>"+
                                "<div style='width:120px;margin: 0px auto'>"+


                                "<div class='input-group'>"+

                                "<span class='input-group-btn'>"+
                                "<button class='btn btn-default' type='button'"+
                            " onclick='subtract(this)'>-</button>"+
                                "</span> <input type='text'  class='form-control num' value='0'"+
                            " disabled='disabled' name='num' "+
                            " style='text-align: center;padding: 0px;cursor: text;'><input "+
                            " type='hidden' name='dishes' value='6'/> <span"+
                        " class='input-group-btn'>"+
                                "<button class='btn btn-default' type='button'"+
                            " onclick='add(this)'>+</button>"+
                                "</span>"+
                                "</div>"+

                                "</div>"+

                                "</form>"+
                                "</div>"+
                                "</div>";
                        }

                        $("#orderTable2").html(listHtml);
                        pagingAjax2(json.dataCount2,everyPageDataCount,json.pageIndex2)
                    }else{
                        var listHtml="";
                        $("#orderTable2").html(listHtml);

                    }



                }
            });
        }
        function pagingAjax2(dataCount,everyPageDataCount,nowPage){
            $("#pagingDivId2").zcPage({
                allDataCount: dataCount,//一共有多少条数据
                everyPageDataCount: everyPageDataCount,//每一页显示多少条数据
                nowPageCataCount:nowPage,//当前是第几页
                success: function (nowPageCataCount/!*当前是第几页*!/) {
                    usersAjax2(nowPageCataCount,everyPageDataCount)

                },
            });
        }*/
        function detail(name, disc, txt, price, commend, img) {
            var dishesName = document.getElementById("dishesName");
            var dishesDiscript = document.getElementById("dishesDiscript");
            var dishesTxt = document.getElementById("dishesTxt");
            txt=txt.replace(/ordersysspace/g, "&nbsp;");
            txt=txt.replace(/ordersysbreak/g, "<br>");

            var dishesPrice = document.getElementById("dishesPrice");
            var recommend = document.getElementById("recommend");
            var dishesImg = document.getElementById("dishesImg");
            dishesImg.src = "img/dishes/" + img;
            dishesName.innerHTML = name;
            dishesDiscript.innerHTML = disc;
            dishesTxt.innerHTML = txt;
            dishesPrice.innerHTML = price;
            if (commend == 1) {
                recommend.innerHTML = "<i class='icon-heart icon-large'></i>&nbsp;推荐菜品";
            } else {
                recommend.innerHTML = "";
            }
            $("#myModal").modal("show");
        }

    </script>


</head>

<body style="font-family: 微软雅黑" <%--onload="begin()"--%>>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <img src="img/logo.png" class="navbar-brand"/> <span
                class="navbar-brand">${ORDER_SYS_NAME}</span>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><span class="navbar-brand">点单界面：当前桌号【<span id="tableresult"></span>】</span></li>

                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown" role="button" aria-haspopup="true"
                                        aria-expanded="false"><img src="img/faces/${USER_INFO.faceimg }"
                                                                   width="24" height="24" class="img-circle"
                                                                   style="border:1px solid #FFF"/>&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount}】,身份为服务员
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li style="text-align: center;padding-top: 15px"><img
                                src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
                                class="img-circle"
                                style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px"/>
                        </li>
                        <li><a href="pages/users/modifyuser.jsp">修改我的密码</a></li>
                        <li role="separator" class="divider"></li>


                    </ul>
                </li>
                <li><a href="LoginOutServlet">退出登录</a></li>
            </ul>


            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="设定桌号" id="tableId"><input
                         class="btn btn-default" type="button" value="确定" onclick="setTableId()"/>
            </form>

        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="nav-header"
                    style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
                        class="icon-user icon-large"></i>&nbsp;服务员点餐功能
                </li><li class="active"><a href="pages/waiters/takeorder.jsp">
                <i class="icon-glass icon-large"></i>&nbsp;服务点餐界面 <span class="sr-only">(current)</span></a></li>
                <li><a href="pages/waiters/paylist.jsp"><i
                        class="icon-credit-card icon-large"></i>&nbsp; 餐桌结账 <span
                        class="sr-only">(current)</span></a></li>


            </ul>

        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <ol class="breadcrumb">
                <li><a href="pages/waiters/takeorder.jsp">首页</a></li>
                <li>服务员</li>
                <li class="active">服务员点餐界面</li>
            </ol>


            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h2 class="panel-title">最新公告消息</h2>
                </div>
                <div class="panel-body" style="padding-bottom: 10px">

                    <h4>
                        <i class=" icon-envelope icon-large" style="color:orange;"></i>&nbsp;<span
                            id="msg"></span>
                    </h4>

                </div>
            </div>

            <div class="panel panel-danger" id="orderdetail">
                <div class="panel-heading">
                    <h2 class="panel-title">餐桌订单</h2>
                </div>
                <div class="panel-body" style="padding-bottom: 10px">

                    <div class="table-responsive">
                        <table class="table table-striped" id="listDivId">
                            <thead>
                            <tr>
                                <th>桌号</th>
                                <th>菜品</th>
                                <th>数量</th>

                            </tr>


                            </thead>
                            <tbody id="orderTable">

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>

                            </tr>


                            </tbody>
                        </table>


                        <div id="pagingDivId" class="pager"></div>
                        <%--<nav>
                            <ul class="pager" >
                                <li><a href="javascript:getUserList(1)" id="firstpage">&larr;首页</a></li>
                                <li><a href="#" id="pre">上一页</a></li>
                                <li><a href="#" id="next">下一页</a></li>
                                <li><a href="#" id="last">末页&rarr;</a></li>
                            </ul>
                        </nav>--%>


                    </div>

                </div>
            </div>


            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h2 class="panel-title">菜品清单</h2>
                </div>
                <div class="panel-body" style="padding-bottom: 0px">
                    <div class="row placeholders" id="dishesbord" >
                        <div id="orderTable2" align="center">







                        <c:forEach items="${dishes}" var="dish">
                        <div class="col-xs-6 col-sm-3 placeholder" >
                            <a onclick=detail('${dish.dishesName}','${dish.dishesDiscript}','${dish.dishesTxt}',${dish.dishesPrice},${dish.recommend},'${dish.dishesImg}')>
                                <input type="hidden" class="dishesid" value="${dish.dishesId}">
                                <img class="img-thumbnail"
                                     style='border-radius:20px;width:95%;height:300px' alt="Generic placeholder thumbnail"
                                              src="img/dishes/${dish.dishesImg}"></a>
                            <h4>${dish.dishesName}</h4>
                            <span class="text-muted">${dish.dishesDiscript}</span>
                            <div class="form-group">
                                <form>
                                    <div style="width:120px;margin: 0px auto">


                                        <div class="input-group">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button"
                                                            onclick="subtract(this)">-</button>
												</span> <input type="text"  class="form-control num" value="0"
                                                               disabled="disabled" name="num"
                                                               style="text-align: center;padding: 0px;cursor: text;"><input
                                                type="hidden" name="dishes" value="6"/> <span
                                                class="input-group-btn">
													<button class="btn btn-default" type="button"
                                                            onclick="add(this)">+</button>
												</span>
                                        </div>


                                        <!-- /input-group -->
                                    </div>

                                </form>
                            </div>
                        </div>



                        </c:forEach>







                        </div>






                    </div>
                    <div id="pagingDivId2" class="pager"></div>
                    <%--<nav>
                        <ul class="pager">
                            <li><a href="javascript:getDishesList(1)">&larr;首页</a></li>
                            <li><a href="#" id="pre">上一页</a></li>
                            <li><a href="#" id="next">下一页</a></li>
                            <li><a href="#" id="last">末页&rarr;</a></li>
                        </ul>
                    </nav>--%>

                    <div style="text-align: center;">
                        <a href="javascript:commitOrder()" class="btn btn-danger"
                           style="width: 40%;margin-bottom: 15px;font-size: 18;font-weight: bold;">确认订单</a>
                    </div>
                </div>
            </div>


            <div
                    style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
            <footer>
                <p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
            </footer>

        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">详细信息</h4>
            </div>
            <div class="modal-body"
                 style="background-image: url('img/body-bg.png')">


                <div class="panel panel-danger" style="margin-top: 10px">
                    <div class="panel-heading">
                        <h3 class="panel-title">菜品详情</h3>
                    </div>
                    <div class="panel-body">

                        <div style="text-align: center;">
                            <img src="img/faces/default.jpg" id="dishesImg" width="200px"
                                 height="200px" class="img-circle"
                                 style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);"/>
                        </div>
                        <p>
                        <h2 style="text-align: center;">
                            菜品名称： <span id="dishesName"></span>
                        </h2>
                        <h3 style="text-align: center;">
                            <span style="color: red;font-weight: bold;" id="recommend"></span>
                        </h3>

                        <hr/>

                        <p>
                        <h3>菜品简介：</h3>
                        <p>
                            <span id="dishesDiscript"></span>
                        </p>

                        <p>
                        <h3>菜品描述：</h3>

                        <p>
                            <span id="dishesTxt"></span>
                        </p>
                        <h3>
                            菜品价格：<span id="dishesPrice"> </span> (元)
                        </h3>


                    </div>


                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>

            </div>

            <iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
        </div>
    </div>
</div>


</body>
</html>
