var user = {
	initialize : function() {
		// 用户地址完成按钮
		$("#addressBtn").unbind().click(function() {
			$("#addressForm").submit();
		});
	},

}
