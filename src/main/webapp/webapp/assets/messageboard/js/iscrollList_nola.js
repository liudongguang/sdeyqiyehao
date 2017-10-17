$(document).ready(function() {
	var myScroll;
	// 上拉
	var pullUpFlag;
	var finished=0;
	myScroll = new IScroll('#wrapper', {
		probeType : 3,
		mouseWheel : true,
		scrollbars : true
	});
	myScroll.on('scroll', scrollRun);
	myScroll.on('scrollEnd', scrollEndRun);

	// ////////
	// ///滚动条滚动的时候
	function scrollRun() {
		if (this.y == this.maxScrollY) { // 判断到底
			pullUpFlag = 1;
		}
	}
	// ///滚动结束时
	function scrollEndRun() {
		if(finished==1){
			return false;
		}
		action();
	}
	// 滚动结束调用
	function action() {
		if (pullUpFlag == 1) {
			pullUpAction();
			pullUpFlag = 0;
		}
		myScroll.refresh();
	}
	function pullUpAction() {
		var jq_scrollUL = $("#scroller ul");
		var pageNum = jq_scrollUL.attr("pageNum");
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		var nextPage = parseInt(pageNum) + 1;
		var formdata = $("#subForm").serialize();
		var ajaxurl = basePath + 'messageboard/liuYanListForMore';
		if (formdata) {
			ajaxurl = ajaxurl + "?" + formdata;
		}
		$("#morenumID").remove();
		$.post(ajaxurl, {
			pageNum : nextPage
		}, function(data) {
			jq_scrollUL.attr("pageNum", nextPage);// 赋值
			jq_scrollUL.append(data);
			isdisplayMore();
			layer.close(ii);
		});
	}
	// 显示更多文字
	function isdisplayMore() {
		var morepages = $("#morepages").val(); // 总页数
		var morepageNum = $("#morepageNum").val(); // 当前页数
		var jq_noMore = $("#noMore");
		var jq_yesMore = $("#moreLiuYan");
		if (morepages == morepageNum) { // 没有更多
			jq_noMore.removeClass().addClass('show');
			jq_yesMore.removeClass().addClass('hide');
			finished=1;
		} else {
			jq_noMore.removeClass().addClass('hide');
			jq_yesMore.removeClass().addClass('show');
		}
	}
	// ///
	document.addEventListener('touchmove', function(e) {
		e.preventDefault();
	}, false);
});
