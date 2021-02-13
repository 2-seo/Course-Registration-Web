<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="layout/header.jsp" %>
<main class="container mt-4">
    <%--  장바구니 테이블  --%>
    <h2>장바구니</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">전공</th>
            <th scope="col">강의명</th>
            <th scope="col">교수명</th>
            <th scope="col">학점</th>
            <th scope="col">시간</th>
            <th scope="col">장바구니</th>
            <th scope="col">수강신청</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lecture" items="${basket_lectures}">
            <tr>
                <td>${lecture.id}</td>
                <td>${lecture.major.name}</td>
                <td>${lecture.name}</td>
                <td>${lecture.lecturer}</td>
                <td>${lecture.credit}</td>
                <td>${lecture.time}</td>
                <td>
                    <button type="button" class="btn_basket_delete btn btn-danger">
                        삭제
                        <input type="hidden" value="${lecture.id}">
                    </button>
                </td>
                <td>
                    <button type="button" class="btn_enrollment_save btn btn-primary">
                        신청
                        <input type="hidden" value="${lecture.id}">
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 결과 메시지 -->
    <div class="basket_result_message_box"></div>
    <%--  수강신청 테이블  --%>
    <h2>수강신청</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">전공</th>
            <th scope="col">강의명</th>
            <th scope="col">교수명</th>
            <th scope="col">학점</th>
            <th scope="col">시간</th>
            <th scope="col">수강신청</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lecture" items="${enrollment_lectures}">
            <tr>
                <td>${lecture.id}</td>
                <td>${lecture.major.name}</td>
                <td>${lecture.name}</td>
                <td>${lecture.lecturer}</td>
                <td>${lecture.credit}</td>
                <td>${lecture.time}</td>
                <td>
                    <button type="button" class="btn_enrollment_delete btn btn-danger">
                        삭제
                        <input type="hidden" value="${lecture.id}">
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 결과 메시지 -->
    <div class="enrollment_result_message_box"></div>
</main>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                modal body
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" id="btn_modal_ok" class="btn btn-primary">확인</button>
            </div>
        </div>
    </div>
</div>

<%-- javascript --%>
<script src="/assets/js/logic.js" defer></script>

<script>
    ///////////////////////////////////////////////////
    // 모달 생성
    let lectureID
    let modalTitle = document.querySelector('.modal-title');
    let modalBody = document.querySelector('.modal-body');
    let modalLogicType = {
        BASKET: {
            'delete': {name: "장바구니 삭제"}
        },
        ENROLLMENT: {
            'save': {name: "수강신청 저장"},
            'delete': {name: "수강신청 삭제"}
        }
    };
    let modalLogic;
    let deleteTargetNode;
    ///////////////////////////////////////////////////
    // 수강신청 버튼 클릭 시 모달 발생 이벤트
    let btn_enrollment_save = document.querySelectorAll('.btn_enrollment_save');

    btn_enrollment_save.forEach((item) => {
        item.addEventListener('click', function(event) {
            createModal("수강신청", "해당 과목을 수강신청 하시겠습니까?", event, modalLogicType.ENROLLMENT.save)

        })
    });
    ///////////////////////////////////////////////////
    // 수강신청 삭제 버튼 클릭 시 모달 발생 이벤트
    let btn_enrollment_delete = document.querySelectorAll('.btn_enrollment_delete');

    btn_enrollment_delete.forEach((item) => {
        item.addEventListener('click', function(event) {
            createModal("수강신청", "해당 과목을 수강 취소하시겠습니까?", event, modalLogicType.ENROLLMENT.delete)
        })
    });
    ///////////////////////////////////////////////////
    // 장바구니 삭제 버튼 클릭 시 모달 발생 이벤트
    let btn_basket_delete = document.querySelectorAll('.btn_basket_delete');

    btn_basket_delete.forEach((item) => {
        item.addEventListener('click', function(event) {
            createModal("장바구니", "해당 과목을 장바구니에서 삭제하시겠습니까?", event, modalLogicType.BASKET.delete);
        })
    });
    ///////////////////////////////////////////////////
    // 모달 확인 버튼 클릭 후 발생하는 이벤트
    let btn_modal_ok = document.querySelector('#btn_modal_ok');
    btn_modal_ok.addEventListener('click', () => {
        $('#myModal').modal('hide');
        if (modalLogic === modalLogicType.ENROLLMENT.save) {
            console.log(1)
            enrollment.save(lectureID);
        } else if (modalLogic === modalLogicType.BASKET.delete) {
            console.log(2)
            basket.delete(deleteTargetNode, lectureID);
        } else if (modalLogic === modalLogicType.ENROLLMENT.delete) {
            console.log(3)
            enrollment.delete(deleteTargetNode, lectureID);
        }
    })

    function createModal(title, content, event, logic) {
        modalTitle.innerText = title;
        modalBody.innerText = content
        lectureID = event.currentTarget.childNodes[1].value;
        deleteTargetNode = event.currentTarget;
        modalLogic = logic
        console.log(title, content, logic);
        $('#myModal').modal('show');
    }


</script>

<%--임시--%>
<script>
    let basket = {

        save: function (lectureID) {
            $.ajax({
                type: "POST",
                url: 'api/basket/' + lectureID,
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
                url: 'api/basket/' + lectureID,
            }).done(function (response) {
                if (response.statusCode === 200) {
                    createResultAlert(basketResultMessageBox, 'alert-success', response.message);
                    // 삭제할 노드 구한 후 부모노드를 구해서 삭제.
                    let removeTargetNode = event.parentNode.parentNode;
                    event.parentNode.parentNode.parentNode.removeChild(removeTargetNode);
                }
            }).fail(function (error) {
                console.log(error);
            });
        }

    }
</script>

<%@ include file="layout/footer.jsp"%>