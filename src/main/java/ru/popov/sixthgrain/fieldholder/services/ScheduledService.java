package ru.popov.sixthgrain.fieldholder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.popov.sixthgrain.fieldholder.repositories.AccountRepository;

import java.util.List;

@Component
@Profile("dev")
public class ScheduledService {

    private final AccountRepository accountRepository;

    @Autowired
    public ScheduledService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void findAccountsToRemove(){
        List<Long> ids = accountRepository.findAccountToDelete();
        ids.forEach(accountRepository::deleteById);
    }

}
