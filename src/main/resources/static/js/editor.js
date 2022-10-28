var testEditor;
$(function() {
    testEditor = editormd("test-editormd", {
        width   : "60%",
        height  : 640,
        syncScrolling : "single",
        path    : "../editormd/lib/",
        saveHTMLToTextarea: true, // 保存 HTML 到 Textarea
        emoji: true,
//        location: left,
        theme: "light",//工具栏主题
        previewTheme: "light",//预览主题
        editorTheme: "pastel-on-light",//编辑主题
        tex: true, // 开启科学公式TeX语言支持，默认关闭
        flowChart: true, // 开启流程图支持，默认关闭
        sequenceDiagram: true, // 开启时序/序列图支持，默认关闭,
        // 图片上传
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/test/file", //上传图片的请求接口
        onload: function () {
            console.log('onload', this);
        },
        /*指定需要显示的功能按钮*/
        toolbarIcons: function () {
            return ["undo", "redo", "|", "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|", "h1", "h2", "h3", "h4", "h5", "h6", "|", "list-ul", "list-ol", "hr", "|", "link", "reference-link", "image", "code", "preformatted- text", "code-block", "table", "datetime", "emoji", "html- entities", "pagebreak", "|", "goto- line", "watch", "preview", "fullscreen", "clear", "search", "|", "help", "info", "releaseIcon", "index"]
        },
        /*自定义功能按钮，下面我自定义了2个，一个是发布，一个是返回首页*/
        toolbarIconTexts: {
            releaseIcon: "<span bgcolor=\"gray\">发布</span>",
            index: "<span bgcolor=\"red\">返回首页</span>",
        },
        /*给自定义按钮指定回调函数*/
        toolbarHandlers: {
            releaseIcon: function (cm, icon, cursor, selection) {
                //表单提交
                mdEditorForm.method = "post";
                mdEditorForm.action = "/test/addTopic";
                //提交至服务 器的路径
                mdEditorForm.submit();
            },
            index: function () {
                window.location.href = '/';
            },
        }
    });
});
/*
// or
testEditor = editormd({
    id      : "test-editormd",
    width   : "90%",
    height  : 640,
    path    : "../lib/"
});
*/

