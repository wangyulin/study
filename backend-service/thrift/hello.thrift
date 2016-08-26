include "model.thrift"
include "exception.thrift"

namespace java com.wyl.backend.service

service Hello{
  string helloString(1:string para)
  i32 helloInt(1:i32 para)
  bool helloBoolean(1:bool para)
  void helloVoid()
  string helloNull() throws (1: exception.CatchableException ex)
 }

service StudentService {
 	list<model.Student> findAllStudents() throws (1: exception.CatchableException ex)
}