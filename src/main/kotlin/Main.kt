import java.util.*

fun courseProjectList(block: CourseProjectList.() -> Unit): CourseProjectList {
    val projectList = CourseProjectList()
    projectList.block()
    return projectList
}

fun CourseProjectList.print() {
    for (project in projectsList) {
        println("Номер: ${project.index}")
        println("Дисциплина: ${project.subject}")
        println("ФИО: ${project.author}")
        println("Группа: ${project.group}")
        println("Оценка: ${project.rating}")
        println("Дата сдачи: ${project.date}\n")
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
}

@Suppress("NAME_SHADOWING")
fun main() {
    val projects1 = courseProjectList {
        part {
            index = 1
            subject = "СиАОД"
            author = "Новоженов Иван Алексеевич"
            group = "21ВП2"
            rating = 4
            date = "21-05-2023"
        }
    }
    println("Первый список курсовых работ:")
    projects1.print()

    val projects2 = courseProjectList {
        part {
            index = 2
            subject = "СиАОД"
            author = "Антонов Илья Игоревич"
            group = "21ВП2"
            rating = 5
            date = "20-05-2023"
        }
    }
    println("Второй список курсовых работ:")
    projects2.print()

    // Бинарный оператор (объединяет два списка)
    val projects = projects1 + projects2
    println("Объединенный список курсовых работ:")
    projects.print()

    // Унарный оператор (возвращает размер коллекции)
    val projectsSize = +projects
    println("\nКоличество курсовых работ = $projectsSize \n\n")

    // Инфиксный метод (поиск по номеру)
    val project = projects.findByIndex(1)
    println("Курсовая работа с номером 1: ")
    printList(project)

    // Функция высшего порядка для поиска по предикату
    val someProject1 = projects.findBy { rating!! > 4 }
    println("Курсовые работы с оценкой выше 4:")
    printList(someProject1)

    // Лямбда-параметр, являющийся расширением класса
    val someProject2 = projects.findBy { date == "21-05-2023" }
    println("Курсовые работы сданные 21-05-2023")
    printList(someProject2)

    val scan = Scanner(System.`in`)
    var num = 1
    while (num == 1 || num == 2 || num == 3 || num == 4) {
        println("\nЧто хотите сделать?")
        println("Добавить курсовую работу – 1")
        println("Поиск по автору – 2")
        println("Поиск по дисциплине – 3")
        println("Поиск сдавших после срока – 4")
        println("Выход – любая другая кнопка")
        print("Введите число: ")
        num = scan.nextInt()

        if (num == 1) {
            val index = +projects + 1
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

            val project = CourseProject(index, subject,
                author, group, rating, date)
            projects.add(project)

            println("\nСейчас список курсовых работ выглядит так:\n")
            projects.print()
        }
        if (num == 2) {
            print("\nВведите ФИО автора: ")
            val name = readlnOrNull()
            val outputList = projects.findByAuthor(name)
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
            val outputList = projects.findBySubject(subject)
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
            val outputList = projects.findByDate(deadline)
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