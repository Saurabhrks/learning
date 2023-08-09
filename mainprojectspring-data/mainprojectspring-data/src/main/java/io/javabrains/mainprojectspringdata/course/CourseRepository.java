package io.javabrains.mainprojectspringdata.course;

import io.javabrains.mainprojectspringdata.course.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String>{
//    void delete(String id);
    public List<Course> findByTopicId(String topicId);
}
