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
	J = jQuery;
	require('plugins');
	require('jcrop');

	var jcrop_api;
	var jcrop_init = false;
	// var upload_url = app.base + '/post/upload?scale=true&size=300';
	var base_url = app.base;
	
	function getRandom() {
		var dim = jcrop_api.getBounds();
		return [
			Math.round(Math.random() * dim[0]),
			Math.round(Math.random() * dim[1]),
			Math.round(Math.random() * dim[0]),
			Math.round(Math.random() * dim[1])
		];
    };
	
	function showCoords(c) {
		$('#x').val(c.x);
		$('#y').val(c.y);
		$('#width').val(c.w);
		$('#height').val(c.h);
	};
  
	function initJcrop() {
		$('#target').Jcrop({
			boxWidth:300,
      		aspectRatio: 100 / 100,
			onChange:   showCoords,
      		onSelect:   showCoords,
			allowSelect: false
		},function(){
    		jcrop_api = this;
    		jcrop_api.animateTo([100,100,300,300]);
		});
	}

    $(document).ready(function() {
        $("#upload_btn").change(function() {
            var fil = this.files;
            if (fil.length > 0) {
                reads(fil[0]);
            }
        });
    });
    function reads(fil) {
        var reader = new FileReader();
        reader.readAsDataURL(fil);
        reader.onload = function() {
        	var path = reader.result;
            $("#target").attr('src', path);
            if (!jcrop_init) {
                initJcrop();
                jcrop_init = true;
            } else {
                jcrop_api.setImage(path, function () {
                    this.animateTo(getRandom());
                });
            }
        };
    }

	// $('#upload_btn').change(function(){
	// 	var fil = this.files;
	// 	if (fil.length > 0) {
     //        var reader = new FileReader();
     //        reader.readAsDataURL(fil[0]);
     //        var path;
     //        reader.onload = function() {
     //            path = reader.result;
     //        }
     //        $("#target").attr("src", path);
     //        if (!jcrop_init) {
     //            initJcrop();
     //            jcrop_init = true;
     //        } else {
     //            jcrop_api.setImage(path, function () {
     //                this.animateTo(getRandom());
     //            });
     //        }
	// 	}
	// });
	
});