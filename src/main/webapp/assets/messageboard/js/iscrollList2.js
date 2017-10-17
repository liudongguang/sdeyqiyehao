$(document).ready(function() {
	var myScroll;
	// 下拉 上拉
	var pullDownFlag, pullUpFlag;
	var pullDown = $("#pullDown"), pullUp = $("#pullUp");
	var finished=0;
	myScroll = new IScroll('#wrapper', {
		probeType : 3,
		mouseWheel : true,
		scrollbars : true,
		preventDefault: false,
		preventDefaultException: { tagName: /^(INPUT|TEXTAREA|BUTTON|SELECT|A)$/ }
	});
	myScroll.on('scroll', scrollRun);
	myScroll.on('scrollEnd', scrollEndRun);

	// ////////
	// ///滚动条滚动的时候
	function scrollRun() {
		if (this.y > 40) { // 判断下拉 下拉了40像素
			//pullDown.show();
			//pullDownFlag = 1;
		} else if (this.y < (this.maxScrollY - 40)) { // 判断上拉
			if(finished==1){
				return false;
			}
			pullUp.show();
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
		if (pullDownFlag == 1) {
			pullDownFlag = 0;
			pullDownAction();
		} else if (pullUpFlag == 1) {
			pullUpFlag = 0;
			pullUpAction();
			
		}
	}
	function pullDownAction() {
		console.log('下拉.....');
		pullDown.hide(500);
	}

	function pullUpAction() {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		var jq_scrollUL = $("#scroller ul");
		var pageNum = jq_scrollUL.attr("pageNum");
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
			myScroll.refresh();
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
			pullUp.hide();
			finished = 1;
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
