package ec.com.sofka;

import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.database.account.IMongoRepository;
import ec.com.sofka.gateway.AccountRepositoryGateway;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountMongoAdapter implements AccountRepositoryGateway {

    private final IMongoRepository repository;
    private final MongoTemplate accountMongoTemplate;

    public AccountMongoAdapter(IMongoRepository repository, @Qualifier("accountMongoTemplate")  MongoTemplate accountMongoTemplate) {
        this.repository = repository;
        this.accountMongoTemplate = accountMongoTemplate;
    }

    @Override
    public AccountDTO findByAcccountId(String id) {
        AccountEntity found = repository.findById(id).get();

        return AccountMapper.toDTO(found);
    }

    @Override
    public AccountDTO save(AccountDTO account) {
        AccountEntity a = AccountMapper.toEntity(account);
        AccountEntity saved = repository.save(a);
        return AccountMapper.toDTO(saved);
    }
}
