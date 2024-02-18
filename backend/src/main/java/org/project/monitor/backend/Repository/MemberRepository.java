package org.project.monitor.backend.Repository;

import org.project.monitor.backend.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
  Member findByEmailAndPassword(String email, String password);
}
