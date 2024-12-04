document.addEventListener("DOMContentLoaded", function() {
    const itemPriceElements = document.querySelectorAll('.itemPriceText');
    const totalPriceElements = document.querySelectorAll('.totalPrice');
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    itemPriceElements.forEach(formatElement);
    totalPriceElements.forEach(formatElement);
    // updateValue 함수 정의
    function updateValue(inputElement) {
        const cartId = inputElement.getAttribute("data-id");
        const newValue = inputElement.value;

        fetch(`/cart/updateNumber/${cartId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader] : csrfToken
            },
            body: JSON.stringify({number: newValue})
        })
            .then(response => {
                if (response.ok) {
                    console.log("DB 업데이트 성공");
                } else {
                    console.error("DB 업데이트 실패");
                }
            })
            .catch(error => console.error("요청 실패:", error));
    }

    // updatePrices 함수 정의
    function updatePrices(inputElement) {
        const tr = inputElement.closest('tr');
        const pricePerItem = unformat(tr.querySelector(".itemPriceText").textContent); // 가격 가져오기
        const totalPriceDisplay = tr.querySelector(".totalPrice"); // 총 가격 표시 요소


        // 수량 변화 시 총 가격 업데이트
        const quantity = parseInt(inputElement.value) || 0; // 'this'는 변경된 input 요소
        const totalPrice = quantity * pricePerItem; // 수량 * 가격
        totalPriceDisplay.textContent = format(totalPrice); // 총 가격을 화면에 표시
    }

    // 각 input[type="number"] 요소에 대해 이벤트 리스너를 추가
    const quantityInputs = document.querySelectorAll('input[type="number"]');
    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            updateValue(this);  // 값 변경 시 updateValue 함수 호출
            updatePrices(this);  // 값 변경 시 updatePrices 함수 호출
        });
    });

    function format(value) {
        // 숫자만 포맷팅, 공백 처리
        console.log(value);
        let number = value.toString().replace(/[^0-9]/g, '');  // 숫자 이외의 문자 제거
        if (number === '') return ''; // 빈 값 처리
        console.log(number);
        return parseInt(number).toLocaleString() + '원'; // 쉼표를 포함한 숫자 형식으로 반환하고 '원' 추가
    }

    function formatElement(element) {
        if(element===null)return;
        element.textContent = format(element.textContent);
    }

    function unformat(value) {
         // 쉼표와 '원' 제거 후 숫자로 변환
        return parseInt(value.replace(/,/g, '').replace('원', '')) || 0;
    }
});