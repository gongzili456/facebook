/**
 * 发表动态消息
 */
function submitNewsFeed() {
	$('#newsfeedForm').submit(function(e) {
		var $content_inpt = $(this).find('textarea[name="content"]');
		var content = $content_inpt.val();
		var c = {
			1 : content
		};
		var params = {
			content : JSON.stringify(c)
		};
		if (content) {
			$.ajax({
				data : JSON.stringify(params),
				url : 'feeds',
				type : 'POST',
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.ok) {
						$content_inpt.val('');
						$('.friendNewsFeedsList').html('');
						Feed.page = 1;
						Feed.getNewsFeeds();
					} else {
						alert(data.error);
					}
				}
			});
		}

		console.log('content : ', content);
		console.log('submit...');
		return false;
	});

}

var Feed = {
	page : 1,
	size : 20,
	getNewsFeeds : function() {
		$.ajax({
			url : 'feeds',
			data : {
				page : this.page,
				size : this.size
			},
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				console.log(data);
				console.log(!data.length);
				if (!data.length) {
					$(".friendNewsFeedsList").html(
							'<h4>你还没有动态，快关注些朋友吧，告诉他们你在做什么！</h4>');
					return false;
				}

				$.each(data, function(i, f) {
					if (typeof f.content === 'string') {
						f.content = $.parseJSON(f.content);
					}
				});
				$(".friendNewsFeedsList").append("#newsTmpl", data);
			}
		});
	},
};

$(function() {
	submitNewsFeed();
	Feed.getNewsFeeds();
});