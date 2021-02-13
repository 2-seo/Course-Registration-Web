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

// 수강신청
let enrollment = {

    save: function (lectureID) {
        $.ajax({
            type: "POST",
            url: 'api/enrollment/' + lectureID,
        }).done(function (response) {
            if (response.statusCode === 200) {
                createResultAlert(basketResultMessageBox, 'alert-success', response.message);
            } else {
                createResultAlert(basketResultMessageBox, 'alert-danger', response.errorMessage);
            }
        }).fail(function (error) {
            console.log(error);
        });
    },

    delete: function (event, lectureID) {

        $.ajax({
            type: "DELETE",
            url: 'api/enrollment/' + lectureID,
        }).done(function (response) {
            if (response.statusCode === 200) {
                createResultAlert(enrollmentResultMessageBox, 'alert-success', response.message);
                // 삭제할 노드 구한 후 부모노드를 구해서 삭제.
                let removeTargetNode = event.parentNode.parentNode;
                event.parentNode.parentNode.parentNode.removeChild(removeTargetNode);
            }
            console.log(response);
            // location.href = '/basket';
        }).fail(function (error) {
            console.log(error);
        });
    }
}

// 장바구니
// let basket = {
//
//     saveLecture: function (lectureID) {
//         $.ajax({
//             type: "POST",
//             url: 'api/basket/' + lectureID,
//         }).done(function (response) {
//             if (response.statusCode === 200) {
//                 createResultAlert(basketResultMessageBox, 'alert-success', response.message);
//             } else {
//                 createResultAlert(basketResultMessageBox, 'alert-danger', response.errorMessage);
//             }
//         }).fail(function (error) {
//             console.log(error);
//         });
//     },
//
//     deleteLecture: function (lectureID) {
//         $.ajax({
//             type: "DELETE",
//             url: 'api/basket/' + lectureID,
//         }).done(function (response) {
//             if (response.statusCode === 200) {
//                 createResultAlert(basketResultMessageBox, 'alert-success', response.message);
//             }
//             // console.log(response);
//             // location.href = '/basket';
//         }).fail(function (error) {
//             console.log(error);
//         });
//     }
//
// }