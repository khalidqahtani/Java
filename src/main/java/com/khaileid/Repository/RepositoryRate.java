package com.khaileid.Repository;

import com.khaileid.Entity.EntittRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRate extends JpaRepository<EntittRate,Long> {

      List<EntittRate> findAllByTidTicketid (Long tid);
      long countByTidTicketid(Long tid);

}
