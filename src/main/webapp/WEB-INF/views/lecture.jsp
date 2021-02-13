<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="layout/header.jsp" %>

<!-- toast -->
<div class="toast_area" style="position: relative;">
    <div class="toast_container"></div>
</div>
<main class="container">
    <div class="d-flex justify-content-center m-5">
        <%--    검생창    --%>
        <div class="d-flex box_search">
            <div class="dropdown">
                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                    전체
                </button>
                <div class="dropdown-menu">
                    <div class="dropdown-item">전체</div>
                    <div class="dropdown-item">교과목명</div>
                    <div class="dropdown-item">교수명</div>
                </div>
            </div>

            <div class="box_search_input">
                <input type="text" name="" id="" class="search_input" placeholder="검색어를 입력하세요.">
            </div>

            <div class="box_search_img">
                <i class="fas fa-search"></i>
            </div>
        </div>
    </div>
    <%--  조회 결과 테이블  --%>
    <table class="table table-hover">
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
        <c:forEach var="lecture" items="${lectures}">
            <tr>
                <td>${lecture.id}</td>
                <td>${lecture.major.name}</td>
                <td>${lecture.name}</td>
                <td>${lecture.lecturer}</td>
                <td>${lecture.credit}</td>
                <td>${lecture.time}</td>
                <td><button type="button" class="btn btn-primary" onclick="basket.save(${lecture.id})">담기</button> </td>
                <td><button type="button" class="btn btn-primary" onclick="enrollment.save(${lecture.id})">신청</button> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<script src="/assets/js/lecturepage-logic.js" defer></script>
<script>
    const dropdown_toggle = document.querySelector('.dropdown-toggle');
    const dropdown_item = document.querySelectorAll(".dropdown-item");

    dropdown_item.forEach((item) => {
        item.addEventListener('click', () => {
            dropdown_toggle.innerHTML = item.innerHTML;
        })
    });
</script>

<%--임시--%>
<script>
    // Toast 동적 생성
    let toastCount = 0;
    let TOASTTYPE = {
        'SUCCESS': 'text-primary',
        'FAIL': 'text-danger'
    }
    function createMyToast(type, title, content, time) {
        toastCount += 1;
        let specificClass = 'myToast' + toastCount;
        let toast_container = document.querySelector('.toast_container');

        let toast = document.createElement('div');
        toast.classList.add('toast', specificClass);
        toast.setAttribute('role', 'alert');
        toast.setAttribute('aria-live', 'assertive');
        toast.setAttribute('data-delay', time);
        toast.style.minWidth = '300px';

        let toast_header = document.createElement('div');
        toast_header.classList.add('toast-header');

        let toast_header__strong = document.createElement('storng');
        toast_header__strong.classList.add('mr-auto', type)
        toast_header__strong.innerText = title;

        let toast_header__small = document.createElement('small');
        toast_header__small.classList.add('text-muted');
        toast_header__small.innerText = 'just now';

        let toast_header__button = document.createElement('button');
        toast_header__button.setAttribute('type', 'button');
        toast_header__button.setAttribute('data-dismiss', 'toast');
        toast_header__button.classList.add('ml-2', 'mb-1', 'close');

        let toast_header__button__span = document.createElement('span');
        toast_header__button__span.setAttribute('aria-hidden', 'true');
        toast_header__button__span.innerText = "×"

        let toast_body = document.createElement('div');
        toast_body.classList.add('toast-body');
        toast_body.innerText = content;

        //  결합
        toast_header__button.append(toast_header__button__span);
        toast_header.append(toast_header__strong);
        toast_header.append(toast_header__small);
        toast_header.append(toast_header__button);
        toast.append(toast_header);
        toast.append(toast_body);
        toast_container.append(toast);

        $('.' + specificClass).toast('show');
        setTimeout(() => {
            toast_container.removeChild(toast);
        }, time + 2000);
    }

    // 수강신청
    let enrollment = {

        save: function (lectureID) {
            $.ajax({
                type: "POST",
                url: 'api/enrollment/' + lectureID,
            }).done(function (response) {
                if (response.statusCode === 200) {
                    createMyToast(TOASTTYPE.SUCCESS, '수강신청', response.message, 5000);

                } else {
                    createMyToast(TOASTTYPE.FAIL, '수강신청', response.errorMessage, 5000);

                }
            }).fail(function (error) {
                console.log(error);
            });
        },

    }

    // 장바구니
    let basket = {

        save: function (lectureID) {
            $.ajax({
                type: "POST",
                url: 'api/basket/' + lectureID,
            }).done(function (response) {
                if (response.statusCode === 200) {
                    createMyToast(TOASTTYPE.SUCCESS, '장바구니', response.message, 5000);

                } else {
                    createMyToast(TOASTTYPE.FAIL, '장바구니', response.errorMessage, 5000);

                }
            }).fail(function (error) {
                console.log(error);
            });
        },

    }
</script>

<%@ include file="layout/footer.jsp"%>