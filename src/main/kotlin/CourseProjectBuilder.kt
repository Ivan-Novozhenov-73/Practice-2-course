class CourseProjectBuilder {
    var index: Int = 0
    lateinit var subject: String
    lateinit var author: String
    lateinit var group: String
    var rating: Int = 0
    lateinit var date: String

    fun build(): CourseProject {
        return CourseProject(index, subject, author, group, rating, date)
    }
}