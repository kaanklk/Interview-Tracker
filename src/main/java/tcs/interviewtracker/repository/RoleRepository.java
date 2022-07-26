package tcs.interviewtracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
