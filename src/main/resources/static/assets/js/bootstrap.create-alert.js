// bootstrap alert 생성 함수
let resultCount = 0;
let basketResultMessageBox = document.querySelector('.basket_result_message_box');
let enrollmentResultMessageBox = document.querySelector('.enrollment_result_message_box');
function createResultAlert(target, result, message) {
    resultCount += 1;
    let specificClass = 'alert-' + resultCount;
    let resultMessageBox = target;
    let resultMessage = document.createElement('div');
    resultMessage.classList.add('alert', result, 'fade', 'show', specificClass);
    resultMessage.innerText = message;

    let resultMessageCloseBtnBox = document.createElement('button');
    resultMessageCloseBtnBox.setAttribute('type', 'button');
    resultMessageCloseBtnBox.setAttribute('data-dismiss', 'alert');
    resultMessageCloseBtnBox.setAttribute('aria-label', 'Close');
    resultMessageCloseBtnBox.classList.add('close')

    let resultMessageCloseBtn = document.createElement('span');
    resultMessageCloseBtn.setAttribute('aria-hidden', 'true')
    resultMessageCloseBtn.innerText = '×'

    resultMessageBox.append(resultMessage);
    resultMessage.append(resultMessageCloseBtnBox);
    resultMessageCloseBtnBox.appendChild(resultMessageCloseBtn);

    setTimeout(() => {
        $('.' + specificClass).alert('close');
        resultCount = 0
    }, 5000)

}