import java.text.SimpleDateFormat
import java.util.*

class CourseProjectList {
    val projectsList = mutableListOf<CourseProject>()

    fun part(block: CourseProjectBuilder.() -> Unit) {
        val builder = CourseProjectBuilder()
        builder.block()
        projectsList.add(builder.build())
    }

    fun add(project: CourseProject) {
        projectsList.add(project)
    }

    // Поиск по автору
    fun findByAuthor(author: String?): List<CourseProject> {
        return projectsList.filter { it.author == author }
    }

    // Поиск по дисциплине
    fun findBySubject(subject: String?): List<CourseProject> {
        return projectsList.filter { it.subject == subject }
    }

    // Поиск сдавших после срока
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

    // Инфиксный метод (поиск по номеру)
    infix fun findByIndex(index: Int): MutableList<CourseProject> {
        val project = mutableListOf<CourseProject>()
        project.add(projectsList.find { it.index == index }!!)
        return project
    }

    // Унарный оператор (возвращает размер коллекции)
    operator fun unaryPlus(): Int {
        return projectsList.size
    }

    // Бинарный оператор (объединяет два списка)
    operator fun plus(projects: CourseProjectList): CourseProjectList {
        val mergedList = CourseProjectList()
        mergedList.projectsList.addAll(projectsList)
        mergedList.projectsList.addAll(projects.projectsList)
        return mergedList
    }

    operator fun CourseProjectList.invoke(block: CourseProjectList.() -> Unit) {
        this.block()
    }

    fun findBy(block: CourseProject.() -> Boolean): List<CourseProject> {
        return projectsList.filter(block)
    }
}