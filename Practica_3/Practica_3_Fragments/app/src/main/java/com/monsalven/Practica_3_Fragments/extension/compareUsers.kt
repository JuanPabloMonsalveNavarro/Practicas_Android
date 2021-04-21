package com.monsalven.Practica_3_Fragments.extension

import com.monsalven.Practica_3_Fragments.User

fun compare_users(user1: User, user2: User): Boolean {
    var ban = false;
    if ((user1.email == user2.email) && (user1.password == user2.password)) {
        ban = true;
    }
    return ban;
}

