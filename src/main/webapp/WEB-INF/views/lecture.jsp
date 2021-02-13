<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="layout/header.jsp" %>
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
        <c:forEach var="lecture" items="${lectures}">
            <tr>
                <td>${lecture.id}</td>
                <td>${lecture.major.name}</td>
                <td>${lecture.name}</td>
                <td>${lecture.lecturer}</td>
                <td>${lecture.credit}</td>
                <td>${lecture.time}</td>
                <td><button type="button" class="btn btn-primary" onclick="basket.saveLecture(${lecture.id})">담기</button> </td>
                <td><button type="button" class="btn btn-primary" onclick="enrollment.saveLecture(${lecture.id})">신청</button> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<script src="/assets/js/basket.js"></script>
<script src="/assets/js/enrollment.js"></script>
<script>
    const dropdown_toggle = document.querySelector('.dropdown-toggle');
    const dropdown_item = document.querySelectorAll(".dropdown-item");

    dropdown_item.forEach((item) => {
        item.addEventListener('click', () => {
            dropdown_toggle.innerHTML = item.innerHTML;
        })
    });
</script>

<%@ include file="layout/footer.jsp"%>