<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="static/simditor-2.3.13/styles/simditor.css" />
    <script type="text/javascript" src="static/simditor-2.3.13/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="static/simditor-2.3.13/scripts/module.js"></script>
    <script type="text/javascript" src="static/simditor-2.3.13/scripts/hotkeys.js"></script>
    <script type="text/javascript" src="static/simditor-2.3.13/scripts/uploader.js"></script>
    <script type="text/javascript" src="static/simditor-2.3.13/scripts/simditor.js"></script>
</head>
<body>
<h2>Hello UploadFile!</h2>
springmvc上传文件至ftp服务器
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springmvc上传文件">
</form>

富文本图片上传文件至远程服务器目录
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="富文本图片上传文件">
</form>

<div class="row cl">
    <label class="form-label col-xs-4 col-sm-2">文章内容：&nbsp;
    </label>
    <div class="formControls col-xs-8 col-sm-9">
        <form action="*" method="post">
            <textarea id="editor" type="text/plain" name="content" style="width:1000px;height:400px; margin-left: auto;">
            </textarea>
            <input type="submit" value="提交">
        </form>
    </div>
</div>


<script type="text/javascript">
    $(function(){
        Simditor.locale = 'zh-CN';//设置中文
        var editor = new Simditor({
            textarea: $('#editor'),  //textarea的id
            placeholder: '这里输入文字...',
            toolbar:  ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'], //工具条都包含哪些内容
            pasteImage: true,//允许粘贴图片
            //defaultImage: '/res/simditor/images/image.png',//编辑器插入的默认图片，此处可以删除
            upload : {
                url : '/manage/product/richtext_img_upload.do', //文件上传的接口地址
                params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                fileKey: 'upload_file', //服务器端获取文件数据的参数名
                connectionCount: 1,
                leaveConfirm: '正在上传文件'
            }
        });
    });
    //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
    //        {
    //            "success": true/false,
    //                "msg": "error message", # optional
    //            "file_path": "[real file path]"
    //        }
</script>

</body>
</html>
