function like(btn, entityType, entityId) {
    $.post(
        CONTEXT_PATH + "/like",
        {"entityType": entityType, "entityId": entityId},//send data to the server
        //return data
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 0){
                $(btn).children("i").text(data.likeCount);
                $(btn).children("b").text(data.likeStatus==1?'Liked':'Like');
            }else{
                alert(data.msg);
            }
        }
    )
}
