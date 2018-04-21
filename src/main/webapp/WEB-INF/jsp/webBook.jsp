<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>书籍列表</title>
    <%@include file="common/head.jsp"%>
    <%@include file="common/tag.jsp"%>
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h1>书籍列表</h1>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>转换状态(完结/转换中)</th>
                    <th>替换文字</th>
                    <th>章节展示页</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${webBookList == null || webBookList.size()==0}">
                        <tr style="text-align: center;">
                            <td colspan="6">
                                暂无拉取的数据
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${webBookList}" var="wb">
                            <tr>
                                <td>${wb.getId()}</td>
                                <td>${wb.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${wb.state == 1}">
                                            完结
                                        </c:when>
                                        <c:otherwise>
                                            转换中
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${wb.replaceStr}</td>
                                <td>${wb.showUrl}</td>
                                <td>
                                    <fmt:formatDate value="${wb.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    <button type="button" class="btn btn-primary" onclick="webBook.show()">新增书籍</button>
</div>

<div class="modal fade" id="editWebBookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增书籍</h4>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form" role="form">
                    <div class="input-group-lg">
                        <input type="text" class="form-control" id="name" placeholder="书名">
                    </div>
                    <br>
                    <div class="input-group-lg">
                        <input type="text" class="form-control" id="showUrl" placeholder="章节url">
                    </div>
                    <br>
                    <div class="input-group-lg">
                        <input type="text" class="form-control" id="replaceStr" placeholder="章节中需要替换成空的广告语">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="webBook.save()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resource/script/webBook.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){

    });
</script>
</html>
