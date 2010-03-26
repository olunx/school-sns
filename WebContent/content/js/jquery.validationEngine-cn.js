

(function($) {
	$.fn.validationEngineLanguage = function() {};
	$.validationEngineLanguage = {
		newLang: function() {
			$.validationEngineLanguage.allRules = 	{"required":{    			// Add your regex rules here, you can take telephone as an example
						"regex":"none",
						"alertText":"* 不能为空",
						"alertTextCheckboxMultiple":"* 请选择一个项目",
						"alertTextCheckboxe":"* 必须选中一个"},
					"length":{
						"regex":"none",
						"alertText":"*必须在 ",
						"alertText2":" 个和 ",
						"alertText3": " 个字符之间"},
					"maxCheckbox":{
						"regex":"none",
						"alertText":"* 超出允许选择个数"},	
					"minCheckbox":{
						"regex":"none",
						"alertText":"* 请选择 ",
						"alertText2":" 项"},	
					"confirm":{
						"regex":"none",
						"alertText":"* 两次输入的不一样"},		
					"telephone":{
						"regex":"/^[0-9\-\(\)\ ]+$/",
						"alertText":"* 不正确的手机号码"},	
					"email":{
						"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
						"alertText":"* 错误的邮箱地址"},	
					"date":{
                         "regex":"/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                         "alertText":"* 错误的日期格式, 必须为 YYYY-MM-DD "},
					"onlyNumber":{
						"regex":"/^[0-9\ ]+$/",
						"alertText":"* 只能输入数字"},	
					"noSpecialCaracters":{
						"regex":"/^[0-9a-zA-Z]+$/",
						"alertText":"* 只能输入数字和字母"},	
					"ajaxUser":{
						"file":"validateUser.php",
						"extraData":"name=eric",
						"alertTextOk":"* 当前用户可用",	
						"alertTextLoad":"* 正在加载数据",
						"alertText":"* 这个用户已经被注册了"},	
					"ajaxName":{
						"file":"validateUser.php",
						"alertText":"* 这个名称已经被使用了",
						"alertTextOk":"* 这个名称可以使用",	
						"alertTextLoad":"* 正在加载数据"},		
					"onlyLetter":{
						"regex":"/^[a-zA-Z\ \']+$/",
						"alertText":"* 只能输入字母"}
					}	
		}
	}
})(jQuery);

$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});