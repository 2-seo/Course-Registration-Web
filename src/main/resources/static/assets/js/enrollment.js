import {createResultAlert, basketResultMessageBox} from '/assets/js/bootstrap.create-alert.js';

let enrollment = {

    saveLecture: function (lectureID) {
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

    deleteLecture: function (lectureID) {
        $.ajax({
            type: "DELETE",
            url: 'api/enrollment/' + lectureID,
        }).done(function (response) {
            if (response.statusCode === 200) {
                createResultAlert(enrollmentResultMessageBox, 'alert-success', response.message)
            }
            console.log(response);
            // location.href = '/basket';
        }).fail(function (error) {
            console.log(error);
        });
    }
}