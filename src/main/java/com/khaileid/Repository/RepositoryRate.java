package com.khaileid.Repository;

import com.khaileid.Entity.EntittRate;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRate extends JpaRepository<EntittRate,Long> {

      List<EntittRate> findAllByTidTicketid (Long tid);
      long countByTidTicketid(Long tid);

      long countByTidEidOrgnizerIDUserid(Long id);
      @Query ("select sum(r.attenderRate) from EntittRate r where r.tid.eid.orgnizerID.userid =?1")
      long getSumRating(Long id);



}
