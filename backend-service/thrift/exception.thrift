namespace java com.wyl.backend.exception

exception CatchableException {
	1: required i64 errorCode,
	2: required string title,
	3: optional string description,
}