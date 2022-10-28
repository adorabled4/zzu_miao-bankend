const images_upload_handler = (blobInfo, progress) => new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    baseURL = "http://localhost:8080/";
    xhr.open('POST', baseURL + "test/file");
    xhr.upload.onprogress = (e) => {
        progress(e.loaded / e.total * 100);
    };
    xhr.onload = () => {
        var json;
        if (xhr.status != 200) {
            reject('HTTP Error: ' + xhr.status);
            return;
        }
        console.log(xhr.responseText);
        json = JSON.parse(xhr.responseText);
        //        console.log(json.url);
        //        json.location = json.url;
        if (!json || typeof json.location != 'string') {
            reject('Invalid JSON: ' + xhr.responseText);
            return;
        }
        resolve(json.location);
    };
    xhr.onerror = () => {
        reject('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
    };
    const formData = new FormData();
    formData.append('file', blobInfo.blob(), blobInfo.filename());
    xhr.send(formData);
});

tinymce.init({
    selector: 'textarea#editable',  // 前面是标签 , 后面是 id
    language: 'zh_CN', //设置语言
    plugins: 'codesample | advlist | link |  image | lists |uploadimage | |searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking save table contextmenu directionality emoticons template paste textcolor colorpicker textpattern imagetools codesample toc help uploadimage',
    /* without images_upload_url set, Upload tab won't show up*/
    images_upload_url: '/test/file',//图片上传地址
    images_upload_credentials: true,
    image_dimensions: false,
    paste_word_valid_elements: '*[*]',        // word需要它
    paste_data_images: true,                  // 粘贴的同时能把内容里的图片自动上传
    paste_convert_word_fake_lists: false,     // 插入word文档需要该属性
    automatic_uploads: true,
    file_picker_types: 'image',
    image_class_list: [
        { title: '无', value: '' },
        { title: '预览', value: 'preview' },
    ],
    toolbar: 'undo redo | image | code',
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
    },
    //第一行菜单
    toolbar1: 'index releaseIcon undo redo insert styleselect bold italic alignleft aligncenter alignright alignjustify  bullist numlist outdent indent',
    toolbar2: 'forecolor backcolor | codesample link image',//第二行菜单
    image_advtab: true,
    menubar: false,
    codesample_languages: [
        { text: 'HTML/XML', value: 'markup' },
        { text: 'JavaScript', value: 'javascript' },
        { text: 'Python', value: 'python' },
        { text: 'Java', value: 'java' },
        { text: 'C', value: 'c' },
        { text: 'C++', value: 'cpp' }
    ],
    /* we override default upload handler to simulate successful upload*/
    images_upload_handler: images_upload_handler,
    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:20px }',
});
const submitTopic= function (cm, icon, cursor, selection) {
    //表单提交
    mdEditorForm.method = "post";
    mdEditorForm.action = "/test/addTopic";
    //提交至服务 器的路径
    mdEditorForm.submit();
}
function  updateValue(editId)
{
    var textValue = document.getElementById(editId);
    textValue.value = tinyMCE.getInstanceById(editId).getBody().innerHTML;
}
