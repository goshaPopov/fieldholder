package ru.popov.sixthgrain.fieldholder.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.repositories.AccountRepository;
import ru.popov.sixthgrain.fieldholder.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account findOneById(Long aLong) throws NotFound {
        return accountRepository.findById(aLong).orElseThrow(() -> new NotFound("Account"));
    }

    @Override
    public Account update(Account account) throws NotFound, UniqueException {
        accountRepository.findById(account.getId()).orElseThrow(() -> new NotFound("Account"));
        Account existedAccount = accountRepository.findAccountByNameAndEmail(account.getName(), account.getEmail());
        if (existedAccount == null) {
            return accountRepository.save(account);
        }
        throw new UniqueException("Account");
    }

    @Override
    public Account findOrCreateAccount(String accountName, String accountEmail) {
        Account account = accountRepository.findAccountByNameAndEmail(accountName, accountEmail);
        if (account == null) {
            account = accountRepository.save(new Account(accountName, accountEmail));
        }
        return account;
    }

    @Override
    public Account delete(Long aLong) throws NotFound {
        Account toDelete = accountRepository.findById(aLong).orElseThrow(() -> new NotFound("Field"));
        accountRepository.delete(toDelete);
        return toDelete;
    }

}
