package tcs.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.persistence.Timeslot;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);

    @Query("SELECT ts.id, ts.is_completed FROM timeslot AS ts JOIN person_has_timeslot AS pht ON ts.id= pht.timeslot_id WHERE ts.is_completed=true AND (SELECT person_id FROM person_has_timeslot JOIN users_projects ON projects_id = ?#{project.id})")
    List<Timeslot> findByIsCompletedTrue(@Param("project") Project project);

    @Query("SELECT ts.id, ts.is_completed FROM timeslot AS ts JOIN person_has_timeslot AS pht ON ts.id= pht.timeslot_id WHERE ts.is_completed=true AND (SELECT person_id FROM person_has_timeslot JOIN users_projects ON projects_id = ?#{project.id})")
    List<Timeslot> findByIsCompletedFalse(@Param("project") Project project);
}
