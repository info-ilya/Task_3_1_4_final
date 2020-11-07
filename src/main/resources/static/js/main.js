$(document).ready(function () {
    $('#myList a').on('click', function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    $(function () {
        $('#myList a:first-child').tab('show');
    });

    function editModal() {
        $('.table .eBtn').on('click', function (event) {
            event.preventDefault();
            let href = $(this).attr('href');
            let text = $(this).text();
            if (text === 'Edit') {
                $.get(href, function (user, status) {
                    $('.myModal #id').val(user.id);
                    $('.myModal #firstName').val(user.firstName);
                    $('.myModal #lastName').val(user.lastName);
                    $('.myModal #age').val(user.age);
                    $('.myModal #email').val(user.email);
                    $('.myModal #password').val(user.password);

                    let roleAdmin = null;
                    let roleUser = null;

                    $('.myModal #roles').empty();

                    $.each(user.roles, function (i, role) {
                        // $('.myModal #roles').append('<option value="' + i + '">' + role.name + '</option>');
                        //$('.myModal #roles').append('<option selected value="' + role.name + '">' + role.name + '</option>');
                        if (role.name === 'ADMIN') {
                            roleAdmin = "ADMIN";
                        } else {
                            roleUser = "USER";
                        }
                    });
                    if (roleUser === 'USER') {
                        $('.myModal #roles').append('<option selected value="USER">USER</option>').append('<option value="ADMIN">ADMIN</option>');
                    } else if (roleAdmin === 'ADMIN') {
                        $('.myModal #roles').append('<option selected value="ADMIN">ADMIN</option>').append('<option value="USER">USER</option>');
                    }

                })
                $('#exampleModal').modal();

            }
        });
    }

    function deleteModal() {
        $('.table .delBtn').on('click', function (event) {
            event.preventDefault();
            let href = $(this).attr('href');
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
            $('#deleteModal').modal();
        });
    }

    function createUser() {
        fetch('http://localhost:8080/api/newuser', {
            method: 'POST',
            body: JSON.stringify({
                firstname: window.formNewUser.firstName.value,
                lastName: window.formNewUser.lastName.value,
                age: window.formNewUser.age.value,
                email: window.formNewUser.email.value,
                password: window.formNewUser.password.value,
                roles: window.formNewUser.roles.value
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
            .then(response => {
                window.formNewUser.firstName.value = "";
                window.formNewUser.lastName.value = "";
                window.formNewUser.age.value = "";
                window.formNewUser.email.value = "";
                window.formNewUser.password.value = "";
                window.formNewUser.roles.value = "";

               // showAll();
                //$('#successful').modal();
            });
    }


    $.getJSON("http://localhost:8080/api/users",
        function (data) {

            let userTable = '';
            let arr = [];
            let editBtn =
                '<a href="/api/users/user_id" class="btn btn-info btn-sm eBtn">Edit</a>';
            let deleteBtn =
                '<a href="/api/users/user_id" class="btn btn-danger btn-sm delBtn">Delete</a>';

            $.each(data, function (key, user) {

                $.each(user.roles, function (i, role) {
                    arr = role.name;
                });

                userTable += '<tr>';
                userTable += '<td id="userID">' + user.id + '</td>';
                userTable += '<td id="userFirstName">' + user.firstName + '</td>';
                userTable += '<td id="userLastName">' + user.lastName + '</td>';
                userTable += '<td id="userEmail">' + user.email + '</td>';
                userTable += '<td id="userAge">' + user.age + '</td>';
                userTable += '<td id="userRoles">' + arr + '</td>';
                userTable += '<td id="userEditBtn">' + editBtn.replace('user_id', user.id) + '</td>';
                userTable += '<td id="userDeleteBtn">' + deleteBtn.replace('user_id', user.id) + '</td>';
                userTable += '</tr>';
            });
            $('#userstable').append(userTable);

            editModal();
            deleteModal();

        });


});
