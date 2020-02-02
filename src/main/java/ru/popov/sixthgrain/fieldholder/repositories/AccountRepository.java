package ru.popov.sixthgrain.fieldholder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.popov.sixthgrain.fieldholder.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.name = :name OR a.email = :email")
    Account findAccountByNameAndEmail(@Param("name") String name, @Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT id FROM account WHERE id NOT IN (SELECT account_id FROM Field)")
    List<Long> findAccountToDelete();

}
