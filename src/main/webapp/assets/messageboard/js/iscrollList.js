$(document).ready(function() {
	var myScroll;
	// 下拉 上拉
	var pullDownFlag, pullUpFlag;
	var pullDown = $("#pullDown"), pullUp = $("#pullUp");
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
		if (this.y > 40) { // 判断下拉 下拉了40像素
			pullDown.show();
			pullDownFlag = 1;
		} else if (this.y < (this.maxScrollY - 40)) { // 判断上拉
			pullUp.show();
			pullUpFlag = 1;
		}
	}
	// ///滚动结束时
	function scrollEndRun() {
		action();
	}
	// 滚动结束调用
	function action() {
		if (pullDownFlag == 1) {
			pullDownAction();
			pullDownFlag = 0;
		} else if (pullUpFlag == 1) {
			pullUpAction();
			pullUpFlag = 0;
		}
		myScroll.refresh();
	}
	function pullDownAction() {
		console.log('下拉.....');
		pullDown.hide(500);
	}

	function pullUpAction() {
		console.log('上拉.....');
		pullUp.hide();
	}
	// ///
	document.addEventListener('touchmove', function(e) {
		e.preventDefault();
	}, false);
});
