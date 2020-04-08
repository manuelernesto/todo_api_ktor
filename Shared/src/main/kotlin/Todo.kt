package io.github.manuelernesto

//import com.fasterxml.jackson.databind.annotation.JsonDeserialize
//import com.fasterxml.jackson.databind.annotation.JsonSerialize
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDate

data class Todo(
    val title: String,
    val details: String,
    val assignedTo: String,
//    @JsonSerialize(using = ToStringSerializer::class)
    val dueData: LocalDate,
    val importance: Importance
)


val todo1 = Todo(
    "Learn Kotlin Today",
    "Learn more about The best language",
    "Manuel Ernesto",
    LocalDate.of(2020, 4, 8),
    Importance.HIGH
)
val todo2 = Todo(
    "Learn Python",
    "Another great language",
    "Manuel Ernesto",
    LocalDate.of(2020, 4, 9),
    Importance.MEDIUM
)


enum class Importance {
    LOW, MEDIUM, HIGH
}


val todos = listOf(todo1, todo2)