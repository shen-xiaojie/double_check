package com.atguigu.dao;


import com.atguigu.bean.ClassInfo;
import com.atguigu.bean.PaperInfo;
import com.atguigu.bean.StudentScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDao {

    @Select("SELECT sid,sname,cname,scscore \n" +
            "\t\tFROM student \n" +
            "\t\tINNER JOIN stu_course \n" +
            "\t\t\tON student.`sid`=stu_course.`scsid` \n" +
            "\t\tINNER JOIN course \n" +
            "\t\t\tON course.`cid`=stu_course.`sccid` \n" +
            "\t\tWHERE sid=#{sid}")
    List<StudentScore> getStudentScore(String sid);


    @Select("SELECT sid,sname,cname,scscore \n" +
            "\t\tFROM student \n" +
            "\t\tINNER JOIN stu_course \n" +
            "\t\t\tON student.`sid`=stu_course.`scsid` \n" +
            "\t\tINNER JOIN course \n" +
            "\t\t\tON course.`cid`=stu_course.`sccid` ")
    List<StudentScore> getStudentsScore();


    @Select("select * from class")
    List<ClassInfo> showClassInfo();

    @Select("select * from paper")
    List<PaperInfo> showPaperInfo();
}
