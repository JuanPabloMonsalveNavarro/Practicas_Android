package com.example.deudoresapp.extension

import com.example.deudoresapp.data.entities.User

fun compare_users(user1: User, user2: User): Boolean {
    var ban = false;
    if ((user1.email == user2.email) && (user1.password == user2.password)) {
        ban = true;
    }
    return ban;
}

