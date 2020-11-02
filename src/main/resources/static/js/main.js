$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {

            $('.myModal #id').val(user.id);
            $('.myModal #firstName').val(user.firstName);
            $('.myModal #lastName').val(user.lastName);
            $('.myModal #age').val(user.age);
            $('.myModal #email').val(user.email);
            $('.myModal #password').val(user.password);
            // $.each(user.roles, function(key, value) {
            //     $('.myModal #roles').append('<option value="' + key + '">' + value + '</option>');
            // });
            $('.myModal #roles').val(JSON.stringify(user.roles));
        })

        $('.myModal #exampleModal').modal();
    });
});