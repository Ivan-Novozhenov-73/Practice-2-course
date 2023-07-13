import java.text.SimpleDateFormat
import java.util.*

class CourseProject(
    val subject: String?,
    val author: String?,
    val group: String?,
    val rating: Int?,
    val date: String?
) {
    val index: Int = ++numOfProjects

    companion object {
        private val projectsList = mutableListOf<CourseProject>()
        var numOfProjects = 0

        fun add(project: CourseProject) {
            projectsList.add(project)
        }

        fun print() {
            for (project in projectsList) {
                println("Номер: ${project.index}")
                println("Дисциплина: ${project.subject}")
                println("ФИО: ${project.author}")
                println("Группа: ${project.group}")
                println("Оценка: ${project.rating}")
                println("Дата сдачи: ${project.date}\n")
            }
            println()
        }

        fun findByAuthor(author: String?): List<CourseProject> {
            return projectsList.filter { it.author == author }
        }

        fun findBySubject(subject: String?): List<CourseProject> {
            return projectsList.filter { it.subject == subject }
        }

        fun findByDate(deadline: String?): List<CourseProject> {
            val outputList = mutableListOf<CourseProject>()
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val parsedDeadline = sdf.parse(deadline)

            for (project in projectsList) {
                val parsedDate = sdf.parse(project.date)

                if (parsedDate.after(parsedDeadline)) {
                    outputList.add(project)
                }
            }

            return outputList
        }
    }
}

fun printList(list: List<CourseProject>){
    for (project in list) {
        println("Номер: ${project.index}")
        println("Дисциплина: ${project.subject}")
        println("ФИО: ${project.author}")
        println("Группа: ${project.group}")
        println("Оценка: ${project.rating}")
        println("Дата сдачи: ${project.date}\n")
    }
    println()
}

fun main() {
    val scan = Scanner(System.`in`)
    var num = 1
    while (num == 1 || num == 2 || num == 3 || num == 4) {
        println("Что хотите сделать?")
        println("Добавить курсовую работу – 1")
        println("Поиск по автору – 2")
        println("Поиск по дисциплине – 3")
        println("Поиск сдавших после срока – 4")
        println("Выход – любая другая кнопка")
        print("Введите число: ")
        num = scan.nextInt()

        if (num == 1) {
            print("\nВведите дисциплину: ")
            val subject = readlnOrNull()
            print("Введите ФИО автора: ")
            val author = readlnOrNull()
            print("Введите группу автора: ")
            val group = readlnOrNull()
            print("Введите оценку: ")
            val rating = scan.nextInt()
            print("Введите дату сдачи: ")
            val date = readlnOrNull()

            val project = CourseProject(subject,
                author, group, rating, date)
            CourseProject.add(project)

            println("\nСейчас список курсовых работ выглядит так:\n")
            CourseProject.print()
        }
        if (num == 2) {
            print("\nВведите ФИО автора: ")
            val name = readlnOrNull()
            val outputList = CourseProject.findByAuthor(name)
            if (outputList.isNotEmpty()) {
                println("Курсовые работы с автором \"$name\"\n")
                printList(outputList)
            }
            else {
                println("Такого автора нет!\n")
            }
        }
        if (num == 3) {
            print("\nВведите название дисциплины: ")
            val subject = readlnOrNull()
            val outputList = CourseProject.findBySubject(subject)
            if (outputList.isNotEmpty()){
                println("Курсовые работы по дисциплине \"$subject\"\n")
                printList(outputList)
            }
            else {
                println("Такой дисциплины нет!\n")
            }
        }
        if (num == 4) {
            print("\nВведите крайний срок сдачи работ: ")
            val deadline = readlnOrNull()
            val outputList = CourseProject.findByDate(deadline)
            if (outputList.isNotEmpty()){
                println("Курсовые работы, сданные после $deadline\n")
                printList(outputList)
            }
            else {
                println("Все работы сданы вовремя\n")
            }
        }
    }
}