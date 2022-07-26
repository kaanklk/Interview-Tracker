package tcs.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcs.interviewtracker.persistence.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
