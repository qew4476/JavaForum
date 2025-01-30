$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	// Get the title and content of the post
	let title = $("#recipient-name").val();
	let content = $("#message-text").val();
	//ajax
	$.post(
		CONTEXT_PATH + "/discuss/add",
		{"title": title, "content": content},
		function(data){
			data = $.parseJSON(data);
			// Set the hint message
			$("#hintBody").text(data.msg);
			// Show the hint message
			$("#hintModal").modal("show");
			// Disappear after 2 seconds
			setTimeout(function(){
				$("#hintModal").modal("hide");
				// Refresh the page after 2 seconds
				if(data.code == 0){
					window.location.reload();
				}
			}, 2000);
		}
	)

	$("#hintModal").modal("show");
	setTimeout(function(){
		$("#hintModal").modal("hide");
	}, 2000);
}