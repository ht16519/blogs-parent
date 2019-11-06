/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/

define(function(require, exports, module) {

	require('ueditor.config');

    var ueditor;

    var initEditor = function (callback) {
        require.async('ueditor', function () {
            ueditor = UE.getEditor('content', {
                fastUpload: app.base + "/api/ueditor/upload.json",
                fastFileName: 'upfile',
                fastUrlPrefix: app.base,
                imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */

                wordCount: true,
                maximumWords: 100000,
                initialFrameWidth: '100%',
                initialFrameHeight: 300
            });

            callback.call(this);
        });

    }

	exports.init = function (callback) {
        initEditor(callback);
    }
});