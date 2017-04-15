function send_request(userId) {
    $.ajax({
        type: 'POST',
        url: 'request/send?friendId=' + userId
    })
}
