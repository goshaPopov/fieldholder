package ru.popov.sixthgrain.fieldholder.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;

public interface AbstractService<Ent, ID> {

    Page<Ent> findAll(Pageable pageable);

    Ent findOneById(ID id) throws NotFound;

    Ent delete(ID id) throws NotFound;
}
