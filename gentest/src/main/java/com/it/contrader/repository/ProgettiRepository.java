package com.it.contrader.repository;

import com.it.contrader.domain.Progetti;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Progetti entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgettiRepository extends JpaRepository<Progetti, Long> {

}
