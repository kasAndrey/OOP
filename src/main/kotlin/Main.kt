/*Реализовать иерархию классов приложения “заметки”. У каждой заметки должно быть названия (title), цвет(color)
и содержимое (content). Контент должен быть реализован в виде sealed класса с следующими наследниками:

Text - контект, который хранит в себе список строк
Image - хранит одну строку, которая представляет собой ссылку или путь в системе к файлу с картинкой
ToDoList - список элементов из названия задачи и отметки о том, выполнена она или нет

В функции main создать список из нескольких заметок с разным типом контента и вывести его в консоль.

Опционально добавить валидацию данных при создании объектов.*/

enum class Color {
    RED,
    GREEN,
    BLUE,
    GRAY,
    BLACK,
    YELLOW,
    MAGENTA,
    CYAN,
    WHITE,
}

data class ToDoListItem(var note: String, var checked: Boolean)

sealed class NoteContent {
    data class Text(val text: List<String>) : NoteContent()
    //контект, который хранит в себе список строк

    data class Image(val ref: String) : NoteContent() {
        init {
            require(ref.isNotBlank()) { "Blank ref" }
        }
    }
    //хранит одну строку, которая представляет собой ссылку или путь в системе к файлу с картинкой

    data class ToDoList(val tasks: List<ToDoListItem>) : NoteContent()
    //список элементов из названия задачи и отметки о том, выполнена она или нет

}

data class Note(val title: String, val color: Color, val noteContent: NoteContent)


fun main() {
    println("Hello World!")

    val users: MutableList<Note> = mutableListOf()
    users.add(Note("Exams balls", Color.BLACK, NoteContent.Image("exams.path")))
    users.add(Note("Students", Color.CYAN, NoteContent.Text(listOf("Ivan", "Sergey", "Katya"))))
    users.add(
        Note(
            "My Dreams", Color.BLUE, NoteContent.ToDoList(
                listOf(
                    ToDoListItem("Write labs", false),
                    ToDoListItem("Win lottery", false),
                    ToDoListItem("Buy car", true),
                )
            )
        )
    )

    (users[2].noteContent as NoteContent.ToDoList).tasks[1].checked = true

    println(users)
}