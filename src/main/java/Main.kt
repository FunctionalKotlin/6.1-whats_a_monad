// Copyright © FunctionalKotlin.com 2017. All rights reserved.

sealed class Option<out A>

object None : Option<Nothing>()

data class Just<out A>(val value: A) : Option<A>()

fun <A, B> Option<A>.map(transform: (A) -> B): Option<B> = when(this) {
    is None -> None
    is Just -> Just(transform(value))
}

fun <A> Option<Option<A>>.flatten(): Option<A> = when(this) {
    is None -> None
    is Just -> this.value
}

fun <A> Option<A>.ifPresent(execute: (A) -> Unit) {
    if (this is Just) execute(this.value)
}

// User & Organization

data class User(val name: String, val organizationId: Int)

data class Organization(val name: String)

fun getUserById(userId: Int): Option<User> =
    Just(User("alex", 1))

fun getOrganizationById(organizationId: Int): Option<Organization> =
    Just(Organization("FunctionalHub"))

fun main(args: Array<String>) {
    val organization: Option<Organization> = getUserById(42)
        .map { user -> getOrganizationById(user.organizationId) }
        .flatten()

    organization.ifPresent { println(it) }
}
