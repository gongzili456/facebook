function initBirthdayElement() {

	function options(start, end) {
		var op = [];

		if (start > end) {
			for (var i = start; i >= end; i--) {
				op.push('<option value="' + i + '">' + i + '</option>');
			}
		} else {
			for (var i = start; i <= end; i++) {
				op.push('<option value="' + i + '">' + i + '</option>');
			}
		}

		return op.join('');
	}

	var $year = $('#birthday_year');
	var $month = $('#birthday_month');
	var $day = $('#birthday_day');

	$year.html('').append('<option value="0" selected="1">年</option>').append(
			options(2014, 1905));
	$month.html('').append('<option value="0" selected="1">月</option>').append(
			options(1, 12));
	$day.html('').append('<option value="0" selected="1">日</option>').append(
			options(1, 31));
}

function sexRadioEvent() {
	$('.sex_span label').click(function(e) {
		$(this).siblings('input').click();
	}).hover(function() {
		$(this).css({
			'cursor' : 'pointer'
		});
	});
}
var form_valide = true;
var email_unuse = false;
jQuery.fn
		.extend({
			myValidator : function(options) {
				// {
				// fields : {
				// 'email': 'required;email;'
				// }
				// }

				$self = $(this);

				var fields = options.fields;

				$.each(fields, function(k, v) {
					check(k, v);
				});

				function check(selector, rules) {
					var $s = $self.find('input[name="' + selector + '"]');
					var rs = rules.split(';');// ["required", "email", ""]

					$s.blur(function(e) {
						form_valide = checkRule($s, rs, this, selector);
						return form_valide;
					});
				}

				function checkRule($s, rs, se, selector) {
					var isValide = true;
					var value = $(se).val();
					if (rs[0] === 'required') {
						if (value.length < 1) {
							isValide = showMess($(se), selector, 'empty');
						} else {
							// 验证合法性
							if (rs[1] !== undefined && rs[1].length > 0) {
								switch (rs[1]) {
								case 'email':
									var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
									if (!reg.test(value)) {
										isValide = showMess($(se), selector,
												'invalid');
									}
									// ajax验证邮箱是否存在
									$.ajax({
										url : 'checkEmail',
										data : {
											email : value
										},
										type : 'GET',
										dataType : 'json',
										contentType : 'application/json',
										success : function(data) {
											console.log(data);
											if (!data.ok) {
												isValide = showMess($(se),
														selector, 'unuse');
											} else {
												email_unuse = true;
											}
										}
									});
									break;
								case 'email2':
									var e = $self.find('input[name="email"]')
											.val();
									var e2 = $(se).val();
									if (e !== e2) {
										isValide = showMess($(se), selector,
												'invalid');
									}
									break;
								case 'password':
									var v = $(se).val();
									if (v.length < 6) {
										isValide = showMess($(se), selector,
												'invalid');
									}
									break;
								default:
									break;
								}
							}

						}
					}
					return isValide;
				}

				function showMess($selector, name, opt) {
					var mess = {
						name : {
							empty : '你应该有个名字',
							invalid : ''
						},
						email : {
							empty : '你不会连个邮箱都 没有吧？',
							invalid : '你的邮箱好怪异~',
							unuse : '你生的晚了，邮箱被别人用了！'
						},
						email2 : {
							empty : '必须要再写一次',
							invalid : '做人要专一，请保持一致！'
						},
						password : {
							empty : '有点安全意识吧！',
							invalid : '起码要大于6个字吧，这是常识好不好？'
						}
					};
					$selector.popover({
						content : mess[name][opt]
					}).popover('show').focus(function(e) {
						$(this).popover('destroy');
					});

					isValide = false;
					return isValide;
				}
			}
		});

function valide(active) {

	var options = {
		fields : {
			name : 'required;',
			email : 'required;email;',
			email2 : 'required;email2;',
			password : 'required;password;',
		}
	};

	if (active) {
		// 主动激活验证
		var ac = true;
		$.each(options.fields, function(k, v) {
			if (form_valide) {
				$('form.form_register input[name="' + k + '"]').blur();
			} else {
				ac = false;
				return false;
			}
		});
		form_valide = true;
		return ac;
	}

	$('form.form_register').myValidator(options);
}

function register() {
	$('form.form_register').submit(function(e) {
		if (valide(true)) {
			var isSubmit = true;
			console.log('可以提交。。。');
			var year = $('#birthday_year').val();
			var month = $('#birthday_month').val();
			var day = $('#birthday_day').val();
			data_str = year + '-' + month + '-' + day;
			var millisec = Date.parse(data_str);
			var date = new Date();
			date.setTime(millisec);
			if (date.getDate() != parseInt(day)) {
				// 日期选择不合法
				isSubmit = false;
				$('#birthday_day').popover({
					content : '如期选择有误'
				}).popover('show').change(function(e) {
					$(this).popover('destroy');
				});
			}
			// 验证性别选项
			var sex = 0;
			$(this).find('input[name="sex"]').each(function(i, o) {
				if (o.checked) {
					sex = o.value;
				}
			});
			if (!sex) {
				isSubmit = false;
				$('.sex_span:eq(1)').popover({
					content : '你总得有性别吧？'
				}).popover('show').change(function(e) {
					$(this).popover('destroy');
				});
			}

			// 开始提交表单
			console.log('email_unuse:',email_unuse);
			if (isSubmit && email_unuse) {
				var ser = $(this).serializeArray();
				console.log(ser);
				var params = {
					name : '',
					email : '',
					password : '',
					birthday : data_str,
					sex : ''
				};

				$.each(ser, function() {
					if (this.name === 'email2') {
						return;
					}
					if (params[this.name]) {
						if (!params[this.name].push) {
							params[this.name] = [ params[this.name] ];
						}
						params[this.name].push(this.value || '');
					} else {
						params[this.name] = this.value || '';
					}
				});

				console.log('params:', params);

				$.ajax({
					url : 'register',
					data : JSON.stringify(params),
					type : 'POST',
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						console.log('data back:', data);
						if (data.ok) {
							location.href = data.next;
						}
					},
					error : function() {

					}
				});
			}
		}
		return false;
	});
}

$(function() {
	sexRadioEvent();
	initBirthdayElement();

	valide();
	register();
});
