package tcs.interviewtracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Interview;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Timeslot;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByUuid(UUID uuid);

    Project findByName(String name);

    @Query(value = "SELECT P.id as projectid, I.id AS interviewid, I.type_id AS typeid FROM project AS P JOIN interview AS I ON P.id = I.project_id WHERE I.is_completed = true AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Timeslot> findByIsCompletedTrue(@Param("project") Project project);

    @Query(value = "SELECT P.id as projectid, I.id AS interviewid, I.type_id AS typeid FROM project AS P JOIN interview AS I ON P.id = I.project_id WHERE I.is_completed = false AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Timeslot> findByIsCompletedFalse(@Param("project") Project project);

    @Query(value = "SELECT C.id, C.project_id, C.person_id FROM candidate AS C JOIN project AS P ON C.id = P.id WHERE status='accepted' AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Candidate> findAcceptedCandidates(@Param("project") Project project);

    @Query(value = "SELECT C.id, C.project_id, C.person_id FROM candidate AS C JOIN project AS P ON C.id = P.id WHERE status='rejected' AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Candidate> findRejectedCandidates(@Param("project") Project project);

    @Query(value = "SELECT C.id, C.project_id, C.person_id FROM candidate AS C JOIN project AS P ON C.id = P.id WHERE NOT status='accepted' AND status='rejected' AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Candidate> findPendingCandidates(@Param("project") Project project);

    @Query(value = "SELECT count(I.id) FROM interview as I JOIN interview_type AS IT ON I.type_id = IT.id WHERE IT.type_name='technical_interview' AND I.project_id = ?#{project.id}", nativeQuery = true)
    Integer findTechnicalInterviewCount(@Param("project") Project project);

    @Query(value = "SELECT count(I.id) FROM interview as I JOIN interview_type AS IT ON I.type_id = IT.id WHERE IT.type_name='management_interview' AND I.project_id = ?#{project.id}", nativeQuery = true)
    Integer findManagementInterviewCount(@Param("project") Project project);

    @Query(value = "SELECT P.id AS projectid, I.id AS interviewId FROM project as P JOIN interview AS I ON P.id = I.project_id JOIN interview_type AS IT ON I.type_id = IT.id WHERE IT.type_name='technical_interview' AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Interview> findUpcomingTechnicalInterviews(@Param("project") Project project);

    @Query(value = "SELECT P.id AS projectid, I.id AS interviewId FROM project as P JOIN interview AS I ON P.id = I.project_id JOIN interview_type AS IT ON I.type_id = IT.id WHERE IT.type_name='management_interview' AND I.project_id = ?#{project.id}", nativeQuery = true)
    List<Interview> findUpcomingManagementInterviews(@Param("project") Project project);

    Optional<Project> getByUuid(UUID projectUuid);

    void delete(Optional<Project> project);
}
