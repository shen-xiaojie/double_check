package com.atguigu.controller;

import com.atguigu.bean.StuLogin;
import com.atguigu.bean.StudentScore;
import com.atguigu.dao.StudentDao;
import com.atguigu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/findRepeat")
    public String findRepeat(Model model)throws FileNotFoundException {
        String pathname1="F:\\ideaworkpace\\double_check\\src\\main\\webapp\\file\\1111.txt";
        String pathname2="F:\\ideaworkpace\\double_check\\src\\main\\webapp\\file\\2222.txt";
       double result = studentService.caculate(pathname1, pathname2);
       //将double类型数据转换为百分数
        DecimalFormat df = new DecimalFormat("0.00%");
        String rates = df.format(result);
        System.out.println(rates);
       model.addAttribute("rates",rates);
        return "student/findRepeat";

    }
    @RequestMapping("/getStudentScore")
    public String getStudentScore(HttpSession session,Model model){

        StuLogin stuLogin = (StuLogin) session.getAttribute("student");
        String loginNum = stuLogin.getLoginNum();
        List<StudentScore> studentScore = studentDao.getStudentScore(loginNum);
        model.addAttribute("studentScore",studentScore);
        return "student/listStudentScore";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file){

        String path = "F:\\double_check\\src\\main\\webapp\\file";
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "student/studentIndex";
    }
}
