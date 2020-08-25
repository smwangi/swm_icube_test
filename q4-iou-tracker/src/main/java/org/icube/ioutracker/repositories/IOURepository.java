package org.icube.ioutracker.repositories;

import org.icube.ioutracker.models.IOU;
import org.icube.ioutracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface IOURepository extends JpaRepository<IOU,Long> {

    @Query("SELECT u FROM IOU u WHERE u.lenderId = ?1")
    List<IOU> findByLenderId(User lenderId);

    @Query("SELECT u FROM IOU u WHERE u.borrowerId = ?1")
    List<IOU> findByBorrowerId(User borrowerId);

}
