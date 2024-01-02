package com.mcgill.examscheduler.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExamService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getExams(){
       return examRepository.findAll();
    }

    public List<Exam> getExamsByClass(String className) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getCourse().toLowerCase().contains(className.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Exam addExam(Exam newExam) {
        // Implement validation or other necessary logic
        return examRepository.save(newExam);
    }

    public Exam updateExam(String courseName, Exam updatedExam) {
        Optional<Exam> optionalExam = examRepository.findById(courseName);
        if (optionalExam.isPresent()) {
            Exam currExam = optionalExam.get();
            currExam.setCourse(updatedExam.getCourse());
            currExam.setSection(updatedExam.getSection());
            currExam.setcourse_title(updatedExam.getcourse_title());
            currExam.setexam_type(updatedExam.getexam_type());
            currExam.setexam_start_time(updatedExam.getexam_start_time());
            currExam.setexam_end_time(updatedExam.getexam_end_time());
            currExam.setBuilding(updatedExam.getBuilding());
            currExam.setRoom(updatedExam.getRoom());
            currExam.setRows(updatedExam.getRows());
            currExam.setRowStart(updatedExam.getRowStart());
            currExam.setRowEnd(updatedExam.getRowEnd());
            return examRepository.save(currExam);
        }
        return null;
    }
}
