function deleteItem(buttonElement) {
    const cartId = buttonElement.getAttribute("data-id");

    fetch('/deleteItem', {
        method: 'POST',  // DELETE 대신 POST로 삭제 요청
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ cartId: cartId })
    })
        .then(response => {
            if (response.ok) {
                console.log("DB 삭제 성공");
                // 삭제 후 UI에서 해당 항목 제거
                buttonElement.closest('tr').remove();
            } else {
                console.error("DB 삭제 실패");
            }
        })
        .catch(error => console.error("요청 실패:", error));
}

// 모든 .btn-delete-item 버튼 요소에 이벤트 리스너 추가
document.querySelectorAll('.btn-delete-item').forEach(button => {
    button.addEventListener('click', function() {
        deleteItem(this);  // 클릭 시 deleteItem 함수 호출
    });
});
