package com.rbobko.spiralproject.repository;

import com.rbobko.spiralproject.model.StatusUpdateCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUpdateCardRepository extends JpaRepository<StatusUpdateCard, Long> {

}
