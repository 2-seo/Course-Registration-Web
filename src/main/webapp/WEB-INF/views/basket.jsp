<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="utf-8" %>

<%@ include file="layout/header.jsp" %>
<main class="container">
    <h2>장바구니</h2>
<%--    <input type="text" value="${basket_lectures}">--%>
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
                <td><button type="button" class="btn btn-primary">삭제</button></td>
                <td><button type="button" class="btn btn-primary">신청</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
                <td><button type="button" class="btn btn-primary">삭제</button> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<script>
    const dropdown_toggle = document.querySelector('.dropdown-toggle');
    const dropdown_item = document.querySelectorAll(".dropdown-item");

    dropdown_item.forEach((item) => {
        item.addEventListener('click', () => {
            dropdown_toggle.innerHTML = item.innerHTML;
        })
    })

</script>

<%@ include file="layout/footer.jsp"%>