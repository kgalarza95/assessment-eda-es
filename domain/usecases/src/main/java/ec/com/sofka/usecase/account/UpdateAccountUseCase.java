package ec.com.sofka.usecase.account;


import ec.com.sofka.aggregate.account.AccountAggregate;
import ec.com.sofka.gateway.AccountRepositoryGateway;
import ec.com.sofka.gateway.IEventStoreGateway;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.generics.interfaces.IUseCase;
import ec.com.sofka.request.CreateAccountRequest;
import ec.com.sofka.responses.CreateAccountResponse;

public class UpdateAccountUseCase implements IUseCase<CreateAccountRequest,CreateAccountResponse> {

    private final IEventStoreGateway iEventStoreGateway;
    private final AccountRepositoryGateway accountRepositoryGateway;

    public UpdateAccountUseCase(IEventStoreGateway repository, AccountRepositoryGateway accountRepository) {
        this.iEventStoreGateway = repository;
        this.accountRepositoryGateway = accountRepository;
    }


    public CreateAccountResponse execute(CreateAccountRequest cmd) {

        AccountAggregate customer = new AccountAggregate();

        customer.updateAccount(cmd.getBalance(), cmd.getNumber(), cmd.getCustomerName());

        accountRepositoryGateway.save(
                new AccountDTO(
                        customer.getAccount().getBalance().getValue(),
                        customer.getAccount().getNumber().getValue(),
                        customer.getAccount().getName().getValue()
                ));

        customer.getUncommittedEvents().forEach(iEventStoreGateway::save);

        customer.markEventsAsCommitted();

        return new CreateAccountResponse(
                customer.getId().getValue(),
                customer.getAccount().getNumber().getValue(),
                customer.getAccount().getName().getValue());
    }
}
