package com.example.coworkingspaces.other_activities.user_account

class PopulateUsers {
    companion object {
        val users = ArrayList<User>()

        fun populate() {
            users.add(
                User(
                    "Nistor",
                    "Andrei",
                    "NAndrei",
                    "pass1234",
                    "+1-202-555-0187",
                    "nAndrei88@gmail.com",
                    "1988-01-02",
                    "5340020296696152",
                    "564",
                    "2022-07"
                )
            )
            users.add(
                User(
                    "Avram",
                    "Denisa",
                    "ADenisa",
                    "pass1234",
                    "+1-202-555-0187",
                    "aDenisa@gmail.com",
                    "1994-03-12",
                    "5323020296696134",
                    "442",
                    "2025-07",
                )
            )
            users.add(
                User(
                    "Dumitrescu",
                    "Andrada",
                    "DAndrada",
                    "pass1234",
                    "+2-323-555-2354",
                    "dAndrada@gmail.com",
                    "1965-09-10",
                    "4564020296692453",
                    "653",
                    "2024-02"
                )
            )
        }

        fun verifyUser(username: String, password: String): Boolean {
            for (user in users) {
                if (user.username == username && user.password == password)
                    return true
            }
            return false
        }

        fun hasPackageSelected(username: String, password: String): Boolean{
            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.packageSelected;
                }
            }

            return false;
        }

        fun getUserIndex(username: String, password: String):Int{
            var i = 0
            for (user in users) {
                if (user.username == username && user.password == password){
                    return i;
                }
                i++
            }

            return i;
        }

        fun getName(username: String, password: String): String {

            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.firstName;
                }
            }

            return "";
        }

        fun getLastName(username: String, password: String): String {

            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.lastName;
                }
            }

            return "";
        }

        fun getPhone(username: String, password: String): String {

            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.phoneNumber;
                }
            }

            return "";
        }
        fun getEmail(username: String, password: String): String {

            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.email;
                }
            }

            return "";

        }

        fun getBirthdate(username: String, password: String): String {

            for (user in users) {
                if (user.username == username && user.password == password){
                    return user.birthDate;
                }
            }

            return "";

        }

    }
}