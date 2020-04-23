package com.it.contrader.repository;

import com.it.contrader.domain.Codes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Codes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodesRepository extends JpaRepository<Codes, Long> {

}
