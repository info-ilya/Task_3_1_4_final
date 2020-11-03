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

                $('.myModal #roles').empty();
                $.each(user.roles, function (i, role) {
                    // $('.myModal #roles').append('<option value="' + i + '">' + role.name + '</option>');
                    $('.myModal #roles').append('<option selected value="' + role.name + '">' + role.name + '</option>');
                });

                // $('#getResultDiv ul').empty();
                // $.each(user.roles, function(i, role){
                ////var role = "- Customer with Id = " + i + ", firstname = " + role.name;
                //     $('#getResultDiv .list-group').append(role.name).append(" ")
                // });
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
            $('.deleteModalNew #roles1').empty();
            $.each(user.roles, function (i, role) {
                $('.deleteModalNew #roles1').append('<option value="' + role.name + '">' + role.name + '</option>');
            });
        })
        //$('.deleteModalNew #delRef').attr('href', href);
        $('#deleteModal').modal();
    });
});