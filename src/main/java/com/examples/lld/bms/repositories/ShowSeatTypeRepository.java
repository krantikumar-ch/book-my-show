package com.examples.lld.bms.repositories;

import com.examples.lld.bms.models.Show;
import com.examples.lld.bms.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

   List<ShowSeatType> findAllByShow(Show show);
}
