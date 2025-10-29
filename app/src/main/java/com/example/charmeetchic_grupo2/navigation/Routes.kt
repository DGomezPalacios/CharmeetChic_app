sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Login : Routes("login")
    object Register : Routes("register")
    object Catalog : Routes("catalog")
    object Cart : Routes("cart")
    object About    : Routes(route = "about")
    object Contact  : Routes(route = "contact")
}
