$(document).ready(function (){
    $('.table .eBtn').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {

            $('.myModal #id').val(user.id);
            $('.myModal #firstName').val(user.firstName);
            $('.myModal #lastName').val(user.lastName);
            $('.myModal #age').val(user.age);
            $('.myModal #email').val(user.email);
            $('.myModal #password').val(user.password);
            $('.myModal #roles').val(user.roles);

        })

        $('.myModal #exampleModal').modal();
    });
});