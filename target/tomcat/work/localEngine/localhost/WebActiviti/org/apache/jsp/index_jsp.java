package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"static/simditor-2.3.13/styles/simditor.css\" />\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/simditor-2.3.13/scripts/jquery.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/simditor-2.3.13/scripts/module.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/simditor-2.3.13/scripts/hotkeys.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/simditor-2.3.13/scripts/uploader.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/simditor-2.3.13/scripts/simditor.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h2>Hello UploadFile!</h2>\r\n");
      out.write("springmvc上传文件至ftp服务器\r\n");
      out.write("<form name=\"form1\" action=\"/manage/product/upload.do\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("    <input type=\"file\" name=\"upload_file\">\r\n");
      out.write("    <input type=\"submit\" value=\"springmvc上传文件\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("富文本图片上传文件至远程服务器目录\r\n");
      out.write("<form name=\"form1\" action=\"/manage/product/richtext_img_upload.do\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("    <input type=\"file\" name=\"upload_file\">\r\n");
      out.write("    <input type=\"submit\" value=\"富文本图片上传文件\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<div class=\"row cl\">\r\n");
      out.write("    <label class=\"form-label col-xs-4 col-sm-2\">文章内容：&nbsp;\r\n");
      out.write("    </label>\r\n");
      out.write("    <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("        <form action=\"*\" method=\"post\">\r\n");
      out.write("            <textarea id=\"editor\" type=\"text/plain\" name=\"content\" style=\"width:1000px;height:400px; margin-left: auto;\">\r\n");
      out.write("            </textarea>\r\n");
      out.write("            <input type=\"submit\" value=\"提交\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    $(function(){\r\n");
      out.write("        Simditor.locale = 'zh-CN';//设置中文\r\n");
      out.write("        var editor = new Simditor({\r\n");
      out.write("            textarea: $('#editor'),  //textarea的id\r\n");
      out.write("            placeholder: '这里输入文字...',\r\n");
      out.write("            toolbar:  ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'], //工具条都包含哪些内容\r\n");
      out.write("            pasteImage: true,//允许粘贴图片\r\n");
      out.write("            //defaultImage: '/res/simditor/images/image.png',//编辑器插入的默认图片，此处可以删除\r\n");
      out.write("            upload : {\r\n");
      out.write("                url : '/manage/product/richtext_img_upload.do', //文件上传的接口地址\r\n");
      out.write("                params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交\r\n");
      out.write("                fileKey: 'upload_file', //服务器端获取文件数据的参数名\r\n");
      out.write("                connectionCount: 1,\r\n");
      out.write("                leaveConfirm: '正在上传文件'\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("    //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回\r\n");
      out.write("    //        {\r\n");
      out.write("    //            \"success\": true/false,\r\n");
      out.write("    //                \"msg\": \"error message\", # optional\r\n");
      out.write("    //            \"file_path\": \"[real file path]\"\r\n");
      out.write("    //        }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
