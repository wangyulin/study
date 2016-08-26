namespace java com.wyl.backend.model

enum Gender {
	MAIL,
	FEMALE
}

struct Student{
	1: i64 id,
	2: string name,
	3: Gender gender,
	4: i32 age,
}