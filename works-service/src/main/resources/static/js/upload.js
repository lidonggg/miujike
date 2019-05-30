function upload() {

    /**
     * 上传
     * @param btnId  上传按钮id
     * @param inputId 上传输入框
     * @param preVideo 预览
     */
    this.initUpload = function(btnId,inputId,preVideo) {
        Qiniu.uploader({
            runtimes: 'html5,flash,html4', // 上传模式，依次退化
            browse_button: btnId, // 上传选择的点选按钮，必需
            uptoken_url: "/api/v1/upload/getToken", // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
            get_new_uptoken: false, // 设置上传文件的时候是否每次都重新获取新的uptoken(没有特殊需求一般为false)
            domain: 'http://ps8xnh0n1.bkt.clouddn.com/', // bucket域名，下载资源时用到，必需(找后端拿)
            max_file_size: '2048mb', // 最大文件体积限制(可以调大)
            unique_names: true,
            flash_swf_url: '/static/lib/qiniu/Moxie.swf', //引入flash，相对路径(如果没用到flash上传的话可以不设置,一般支持html5上传的浏览器都不会用到它)
            max_retries: 3, // 上传失败最大重试次数(自动帮你续传分片)
            dragdrop: true, // 开启可拖曳上传(如果不实现拖拽上传可以不设置)
            chunk_size: '8mb', // 分块上传时，每块的体积
            auto_start: true, // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
            init: {
                'FilesAdded': function (up, files) {
                    plupload.each(files, function (file) {
                        let s = file.name.split(".");
                        if (s[s.length - 1] !== "mp4") {
                            alert("请上传mp4格式的文件");
                        }
                    });
                },
                'BeforeUpload': function (up, file) {
                    // 每个文件上传前，处理相关的事情
                    // (上传文件前做一些处理)
                },
                'UploadProgress': function (up, file) {
                    let s = file.name.split(".");
                    if (s[s.length - 1] === "mp4") {
                        let size = file['size'];

                        let loaded = file['loaded'];
                        // console.log("loaded:"+loaded/size);
                        $("#" + inputId).val("上传中...(" + Math.floor((loaded / size) * 1000) / 1000 + ")");
                    }
                },
                'FileUploaded': function (up, file, info) {
                    let s = file.name.split(".");
                    if (s[s.length - 1] === "mp4") {
                        console.log(file);
                        // 查看简单反馈
                        let domain = up.getOption('domain');
                        let resp = JSON.parse(info.response);
                        console.log(info);
                        let sourceLink = domain + "/" + resp.key; //获取上传成功后的文件的Url
                        $("#"+inputId).prop('value',sourceLink);
                        $('#' + inputId).val(sourceLink);
                        if(typeof (preVideo) !== 'undefined'){
                            $(preVideo).prop("src", sourceLink);
                        }
                    }
                },
                'Error': function (up, err, errTip) {
                    $("#"+inputId).attr("value", "上传失败");
                },
                'UploadComplete': function () {
                    //队列文件处理完毕后，处理相关的事情
                }
            }
        })
    }
}