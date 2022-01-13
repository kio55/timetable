package org.kio55.timetableLab.config;

import org.kio55.timetableLab.core.repository.classRepository.IClassRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.IClassroomRepository;
import org.kio55.timetableLab.core.repository.groupRepository.IGroupRepository;
import org.kio55.timetableLab.core.repository.teacherRepository.ITeacherRepository;
import org.kio55.timetableLab.core.repository.typeRepository.ITypeRepository;
import org.kio55.timetableLab.web.service.classes.ClassService;
import org.kio55.timetableLab.web.service.classes.IClassService;
import org.kio55.timetableLab.web.service.classroom.ClassroomService;
import org.kio55.timetableLab.web.service.classroom.IClassroomService;
import org.kio55.timetableLab.web.service.group.GroupService;
import org.kio55.timetableLab.web.service.group.IGroupService;
import org.kio55.timetableLab.web.service.teacher.ITeacherService;
import org.kio55.timetableLab.web.service.teacher.TeacherService;
import org.kio55.timetableLab.web.service.type.ITypeService;
import org.kio55.timetableLab.web.service.type.TypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IGroupService groupService(final IGroupRepository groupRepository) {
        return new GroupService(groupRepository);
    }

    @Bean
    public ITeacherService teacherService(final ITeacherRepository teacherRepository) {
        return new TeacherService(teacherRepository);
    }

    @Bean
    public ITypeService typeService(final ITypeRepository typeRepository) {
        return new TypeService(typeRepository);
    }

    @Bean
    public IClassroomService classroomService(final IClassroomRepository classroomRepository, final IClassRepository classRepository) {
        return new ClassroomService(classroomRepository, classRepository);
    }

    @Bean
    public IClassService classService(final IGroupRepository groupRepository, final ITeacherRepository teacherRepository,
                                      final ITypeRepository typeRepository, final IClassroomRepository classroomRepository,
                                      final IClassRepository classRepository) {
        return new ClassService(classroomRepository, classRepository, groupRepository, teacherRepository, typeRepository);
    }
}
