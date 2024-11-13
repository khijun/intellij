const priceElements = document.querySelectorAll('.price');
const itemPriceElements = document.querySelectorAll('.itemPriceText');
const totalPriceElements = document.querySelectorAll('.totalPrice');
const orderPrice = document.querySelector("#orderPrice");
const deliveryCost = document.querySelector("#deliveryCost");
const finalTotalPrice = document.querySelector("#finalTotalPrice");

function format(element){
    let number = parseInt(element.textContent.replace(/,/g, ''), 10);

    // 숫자 포맷팅 (쉼표 추가)
    if (!isNaN(number)) {
        element.textContent = number.toLocaleString() + '원';
    }
}
itemPriceElements.forEach(format);
totalPriceElements.forEach(format);
format(orderPrice);
format(deliveryCost);
format(finalTotalPrice);

priceElements.forEach(element => {
    format(element);
});