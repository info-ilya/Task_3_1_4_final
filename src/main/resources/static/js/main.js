$(document).ready(function () {
        $('#myList a').on('click', function (e) {
            e.preventDefault();
            $(this).tab('show');
        });
        $(function () {
            $('#myList a:first-child').tab('show');
        });

        mainTable();
        newUser();
        allUsersSidebar();

        function editModal() {
            $('#userstable .eBtn').on('click', function (event) {
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

                    $('#editModal').modal();

                    const myForm = document.getElementById("editUserForm");
                    myForm.addEventListener('submit', function (e) {
                        e.preventDefault();

                        return fetch('http://localhost:8080/api/users/', {
                            method: 'PUT',
                            body: JSON.stringify({
                                id: window.editUserForm.id.value,
                                firstName: window.editUserForm.firstName.value,
                                lastName: window.editUserForm.lastName.value,
                                age: window.editUserForm.age.value,
                                email: window.editUserForm.email.value,
                                password: window.editUserForm.password.value,
                                roles: $(".myModal #roles").val()
                            }),
                            headers: {
                                'Content-Type': 'application/json',
                                'Accept': 'application/json'
                            }
                        }).then(response => {
                            $('#userstablebody').empty().append(mainTable())
                            $('#editModal').modal('hide')
                        })
                    })
                }
            });
        }

        function deleteModal() {
            $('#userstable .delBtn').on('click', function (event) {
                event.preventDefault();
                let href = $(this).attr('href');
                let userid;
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

                    userid = user.id;
                })

                let closestTr = $(this).closest("tr");

                $('#deleteModal').modal();

                const myForm = document.getElementById("deleteUserForm");
                myForm.addEventListener('submit', function (e) {
                    e.preventDefault();

                    return fetch('http://localhost:8080/api/users/' + userid, {
                        method: 'DELETE',
                    }).then(response => response.json())
                        .then($('#deleteModal').modal('hide'))
                        .then(closestTr.remove())
                })
            });
        }

        // function createUser() {
        //     fetch('http://localhost:8080/api/users', {
        //         method: 'POST',
        //         body: JSON.stringify({
        //             firstname: window.formNewUser.firstName.value,
        //             lastName: window.formNewUser.lastName.value,
        //             age: window.formNewUser.age.value,
        //             email: window.formNewUser.email.value,
        //             password: window.formNewUser.password.value,
        //             roles: window.formNewUser.roles.value
        //         }),
        //         headers: {"Content-type": "application/json; charset=UTF-8"}
        //     })
        //         .then(response => {
        //             window.formNewUser.firstName.value = "";
        //             window.formNewUser.lastName.value = "";
        //             window.formNewUser.age.value = "";
        //             window.formNewUser.email.value = "";
        //             window.formNewUser.password.value = "";
        //             window.formNewUser.roles.value = "";
        //
        //             $('.myModalNew .submitBtn').submit();
        //         });
        // }

        function newUser() {
            const myForm = document.getElementById("formNewUser");
            myForm.addEventListener('submit', function (e) {
                e.preventDefault();

                fetch('http://localhost:8080/api/users', {
                    method: 'POST',
                    body: JSON.stringify({
                        //_csrf: window.formNewUser._csrf.value,
                        firstName: window.formNewUser.firstName.value,
                        lastName: window.formNewUser.lastName.value,
                        age: window.formNewUser.age.value,
                        email: window.formNewUser.email.value,
                        password: window.formNewUser.password.value,
                        roles: $("#formNewUser #roles").val()
                    }),
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    }
                }).then(response => response.json())
                    .then($('#userstable').empty())
                    .then(mainTable)
            })
        }

        function allUsersSidebar() {
            $.getJSON("http://localhost:8080/api/users",
                function (data) {
                    $.each(data, function (key, user) {
                        $("<a href='/api/users/userid' class='list-group-item list-group-item-action text-capitalize border-0 userBtn'></a>"
                            .replace('userid', user.id))
                            .text(user.firstName)
                            .appendTo($(".allUsersSidebar li"));


                    $('.allUsersSidebar .userBtn').on('click', function (event) {
                        event.preventDefault()
                        let href = $(this).attr('href');


                            $('#userstable #userID').val(user.id);
                            $('#userstable #userFirstName').val(user.firstName);
                            $('#userstable #userLastName').val(user.lastName);
                            $('#userstable #userEmail').val(user.email);
                            $('#userstable #userAge').val(user.age);
                            $('#userstable #userRoles').empty();
                            $.each(user.roles, function (i, role) {
                                $('#userstable #roles').append('<td>' + role.name + '</td>');
                            });
                    })
                    })

                })

        }


        function mainTable() {
            $.getJSON("http://localhost:8080/api/users",
                function (data) {

                    let userTable = '';
                    let arr = [];
                    let editBtn =
                        '<a href="/api/users/userid" class="btn btn-info btn-sm eBtn">Edit</a>';
                    let deleteBtn =
                        '<a href="/api/users/userid" class="btn btn-danger btn-sm delBtn">Delete</a>';

                    $.each(data, function (key, user) {

                        $.each(user.roles, function (i, role) {
                            arr = role.name;
                        });

                        userTable += '<tr id="rowID">';
                        userTable += '<td id="userID">' + user.id + '</td>';
                        userTable += '<td id="userFirstName">' + user.firstName + '</td>';
                        userTable += '<td id="userLastName">' + user.lastName + '</td>';
                        userTable += '<td id="userEmail">' + user.email + '</td>';
                        userTable += '<td id="userAge">' + user.age + '</td>';
                        userTable += '<td id="userRoles">' + arr + '</td>';
                        userTable += '<td id="userEditBtn">' + editBtn.replace('userid', user.id) + '</td>';
                        userTable += '<td id="userDeleteBtn">' + deleteBtn.replace('userid', user.id) + '</td>';
                        userTable += '</tr>';

                    });
                    $('#userstable').append(userTable);

                    editModal();
                    deleteModal();
                });
        }


    }
);
