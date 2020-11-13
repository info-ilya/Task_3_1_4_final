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

        // function editModal() {
        //     $('#userstable .eBtn').on('click', function (event) {
        //         event.preventDefault();
        //         let href = $(this).attr('href');
        //
        //         {
        //             $.get(href, function (user, status) {
        //                 $('.myModal #id').val(user.id);
        //                 $('.myModal #name').val(user.name);
        //                 $('.myModal #lastName').val(user.lastName);
        //                 $('.myModal #age').val(user.age);
        //             })
        //             $('#editModal').modal();
        //
        //             const myForm = document.getElementById("editUserForm");
        //             myForm.addEventListener('submit', function (e) {
        //                 e.preventDefault();
        //
        //                 return fetch('http://localhost:8080/api/users/', {
        //                     method: 'PUT',
        //                     body: JSON.stringify({
        //                         id: window.editUserForm.id.value,
        //                         name: window.editUserForm.name.value,
        //                         lastName: window.editUserForm.lastName.value,
        //                         age: window.editUserForm.age.value,
        //                     }),
        //                     headers: {
        //                         'Content-Type': 'application/json',
        //                         'Accept': 'application/json'
        //                     }
        //                 }).then(response => {
        //                     $('#userstablebody').empty().append(mainTable())
        //                     $('#editModal').modal('hide')
        //                 })
        //
        //             })
        //
        //         }
        //     });
        // }
        //
        // function deleteModal() {
        //     $('#userstable .delBtn').on('click', function (event) {
        //         event.preventDefault();
        //         let href = $(this).attr('href');
        //         let userid;
        //         $.get(href, function (user, status) {
        //             $('.deleteModalNew #id1').val(user.id);
        //             $('.deleteModalNew #name1').val(user.name);
        //             $('.deleteModalNew #lastName1').val(user.lastName);
        //             $('.deleteModalNew #age1').val(user.age);
        //
        //             userid = user.id;
        //         })
        //
        //         let closestTr = $(this).closest("tr");
        //
        //         $('#deleteModal').modal();
        //
        //         const myForm = document.getElementById("deleteUserForm");
        //         myForm.addEventListener('submit', function (e) {
        //             e.preventDefault();
        //
        //             return fetch('http://localhost:8080/api/users/' + userid, {
        //                 method: 'DELETE',
        //             }).then(response => response.json())
        //                 .then($('#deleteModal').modal('hide'))
        //                 .then(closestTr.remove())
        //         })
        //     });
        // }

        function newUser() {
            const myForm = document.getElementById("formNewUser");
            myForm.addEventListener('submit', function (e) {
                e.preventDefault();

                fetch('http://localhost:8080/api/users', {
                    method: 'POST',
                    body: JSON.stringify({
                        name: window.formNewUser.name.value,
                        lastName: window.formNewUser.lastName.value,
                        age: window.formNewUser.age.value,
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


        function mainTable() {
            let userTable = '';
            let editBtn =
                '<a href="/api/users/userid" class="btn btn-info btn-sm eBtn">Edit</a>';
            let deleteBtn =
                '<a href="/api/users/userid" class="btn btn-danger btn-sm delBtn">Delete</a>';

            $.getJSON("http://91.241.64.178:7081/api/users",
                function (data) {
                    $.each(data, function (key, user) {

                        userTable += '<tr id="rowID">';
                        userTable += '<td id="userID">' + user.id + '</td>';
                        userTable += '<td id="username">' + user.name + '</td>';
                        userTable += '<td id="userLastName">' + user.lastName + '</td>';
                        userTable += '<td id="userAge">' + user.age + '</td>';
                        userTable += '<td id="userEditBtn">' + editBtn.replace('userid', user.id) + '</td>';
                        userTable += '<td id="userDeleteBtn">' + deleteBtn.replace('userid', user.id) + '</td>';
                        userTable += '</tr>';
                    });
                    $('#userstable').append(userTable);

                    // editModal();
                    // deleteModal();
                });
        }
    }
);
