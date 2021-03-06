package org.kio55.timetableLab.config;

import org.kio55.timetableLab.core.model.*;
import org.kio55.timetableLab.core.repository.classRepository.IClassRepository;
import org.kio55.timetableLab.core.repository.classRepository.MapClassRepository;
import org.kio55.timetableLab.core.repository.classRepository.PostgresClassRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.IClassroomRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.MapClassroomRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.PostgresClassroomRepository;
import org.kio55.timetableLab.core.repository.groupRepository.IGroupRepository;
import org.kio55.timetableLab.core.repository.groupRepository.MapGroupRepository;
import org.kio55.timetableLab.core.repository.groupRepository.PostgresGroupRepository;
import org.kio55.timetableLab.core.repository.teacherRepository.ITeacherRepository;
import org.kio55.timetableLab.core.repository.teacherRepository.MapTeacherRepository;
import org.kio55.timetableLab.core.repository.teacherRepository.PostgresTeacherRepository;
import org.kio55.timetableLab.core.repository.typeRepository.ITypeRepository;
import org.kio55.timetableLab.core.repository.typeRepository.MapTypeRepository;
import org.kio55.timetableLab.core.repository.typeRepository.PostgresTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
public class RepoConfig {

    @Bean
    public Map<String, Group> groupMap() {
        Map<String, Group> groupMap = new HashMap<>();
        groupMap.put("SMB-801-O-1", new Group("SMB-801-O-1", 1, 4));
        groupMap.put("SBB-801-O-1", new Group("SBB-801-O-1", 1, 4));
        groupMap.put("SBS-801-O-1", new Group("SBS-801-O-1", 1, 4));
        return groupMap;
    }

    @Bean
    public Map<String, Teacher> teacherMap() {
        Map<String, Teacher> teacherMap = new HashMap<>();
        teacherMap.put("4d4318c6-73ac-11ec-90d6-0242ac120003", new Teacher("4d4318c6-73ac-11ec-90d6-0242ac120003", "????????????????", "??????????", "????????????????????"));
        teacherMap.put("a208377e-73ac-11ec-90d6-0242ac120003", new Teacher("a208377e-73ac-11ec-90d6-0242ac120003", "??????????", "??????????????????", "????????????????"));
        return teacherMap;
    }

    @Bean
    public Map<String, Type> typeMap() {
        Map<String, Type> typeMap = new HashMap<>();
        typeMap.put("e9aa69b2-73b6-11ec-90d6-0242ac120003", new Type("e9aa69b2-73b6-11ec-90d6-0242ac120003", "????????????"));
        typeMap.put("f9db2c0e-73b6-11ec-90d6-0242ac120003", new Type("f9db2c0e-73b6-11ec-90d6-0242ac120003", "????????????????"));
        return typeMap;
    }

    @Bean
    public Map<String, Classroom> classroomMap() {
        Map<String, Classroom> classroomMap = new HashMap<>();
        classroomMap.put("cee0c77c-73be-11ec-90d6-0242ac120003", new Classroom("cee0c77c-73be-11ec-90d6-0242ac120003", "103-a", "10"));
        classroomMap.put("e9521c78-73be-11ec-90d6-0242ac120003", new Classroom("e9521c78-73be-11ec-90d6-0242ac120003", "115", "6"));
        return classroomMap;
    }

    @Bean
    public Map<String, Classes> classMap() {
        Map<String, Classes> classesMap = new HashMap<>();
        classesMap.put("d963de98-742f-11ec-90d6-0242ac120003", new Classes("d963de98-742f-11ec-90d6-0242ac120003",
                new ArrayList<String>(List.of("SMB-801-O-1")), "???????????? ????????????????????????",
                "4d4318c6-73ac-11ec-90d6-0242ac120003", new ArrayList<LocalDateTime>(List.of(LocalDateTime.now())),
                "e9aa69b2-73b6-11ec-90d6-0242ac120003", "cee0c77c-73be-11ec-90d6-0242ac120003"));
        classesMap.put("3996e3d6-7431-11ec-90d6-0242ac120003", new Classes("3996e3d6-7431-11ec-90d6-0242ac120003",
                new ArrayList<String>(List.of("SMB-801-O-1", "SBB-801-O-1")), "???????????? ????????????????????????",
                "4d4318c6-73ac-11ec-90d6-0242ac120003", new ArrayList<LocalDateTime>(List.of(LocalDateTime.now())),
                "e9aa69b2-73b6-11ec-90d6-0242ac120003", "cee0c77c-73be-11ec-90d6-0242ac120003"));
        return classesMap;
    }

//    @Bean(name = "groupRepository")
//    public IGroupRepository groupRepository() {
//        return new MapGroupRepository(groupMap());
//    }

    @Bean(name = "groupRepository")
    public IGroupRepository groupRepository(final JdbcOperations jdbcOperations) {
        return new PostgresGroupRepository(jdbcOperations);
    }

//    @Bean(name = "teacherRepository")
//    public ITeacherRepository teacherRepository() {
//        return new MapTeacherRepository(teacherMap());
//    }

    @Bean(name = "teacherRepository")
    public ITeacherRepository teacherRepository(final JdbcOperations jdbcOperations) {
        return new PostgresTeacherRepository(jdbcOperations);
    }

//    @Bean(name = "typeRepository")
//    public ITypeRepository typeRepository() {
//        return new MapTypeRepository(typeMap());
//    }

    @Bean(name = "typeRepository")
    public ITypeRepository typeRepository(final JdbcOperations jdbcOperations) {
        return new PostgresTypeRepository(jdbcOperations);
    }

//    @Bean(name = "classroomRepository")
//    public IClassroomRepository classroomRepository() {
//        return new MapClassroomRepository(classroomMap());
//    }

    @Bean(name = "classroomRepository")
    public IClassroomRepository classroomRepository(final JdbcOperations jdbcOperations) {
        return new PostgresClassroomRepository(jdbcOperations);
    }

//    @Bean(name = "classRepository")
//    public IClassRepository classRepository() {
//        return new MapClassRepository(classMap());
//    }

    @Bean(name = "classRepository")
    public IClassRepository classRepository(final JdbcOperations jdbcOperations) {
        return new PostgresClassRepository(jdbcOperations);
    }

}
