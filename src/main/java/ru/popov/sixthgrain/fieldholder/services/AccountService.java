package ru.popov.sixthgrain.fieldholder.services;

import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;
import ru.popov.sixthgrain.fieldholder.model.Account;

public interface AccountService extends AbstractService<Account, Long>{

    Account update(Account ent) throws NotFound, UniqueException;

    Account findOrCreateAccount(String name, String email);

}
