// $(document).ready(function () {
//     $('.table .eBtn').on('click', function (event) {
//         event.preventDefault();
//         var href = $(this).attr('href');
//         $.get(href, function (user, status) {
//             $('.myModal #id').val(user.id);
//             $('.myModal #firstName').val(user.firstName);
//             $('.myModal #lastName').val(user.lastName);
//             $('.myModal #age').val(user.age);
//             $('.myModal #email').val(user.email);
//             $('.myModal #password').val(user.password);
//             // $.each(user.roles, function(key, value) {
//             //     $('.myModal #roles').append('<option value="' + key + '">' + value + '</option>');
//             // });
//             $('.myModal #roles').val(JSON.stringify(user.roles));
//         })
//
//         $('.myModal #exampleModal').modal();
//     });
// });
$('#myList a').on('click', function (e) {
    e.preventDefault();
    $(this).tab('show');
});
$(function () {
    $('#myList a:first-child').tab('show');
});

$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if (text == 'Edit') {
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
            $('#exampleModal').modal();
        }
    });

    $(' .table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.deleteModalNew #id1').val(user.id);
            $('.deleteModalNew #firstName1').val(user.firstName);
            $('.deleteModalNew #lastName1').val(user.lastName);
            $('.deleteModalNew #age1').val(user.age);
            $('.deleteModalNew #email1').val(user.email);
            // $.each(user.roles, function(key, value) {
            //     $('.myModal #roles').append('<option value="' + key + '">' + value + '</option>');
            // });
            $('.deleteModalNew #roles1').val(JSON.stringify(user.roles));
        })
        //$('.deleteModalNew #delRef').attr('href', href);
        $('#deleteModal').modal();
    });
});