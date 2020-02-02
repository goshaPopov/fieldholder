package ru.popov.sixthgrain.fieldholder.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.popov.sixthgrain.fieldholder.model.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query("SELECT f FROM Field f WHERE f.account.id = :accountId")
    Page<Field> findFieldsByAccountId(@Param("accountId") Long accountId, Pageable pageable);

    @Query("SELECT f FROM Field f WHERE f.account.id = :accountId AND f.name = :name")
    Field findFieldByAccountIdAndName(@Param("accountId") Long accountId, @Param("name") String name);

}
