package dddfaq.domain.student

/** 生徒 */
class Student(val id: StudentId, val name: String)

data class StudentId(val value: String)