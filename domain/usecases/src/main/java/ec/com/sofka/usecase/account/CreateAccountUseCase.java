package ec.com.sofka.usecase.account;


import ec.com.sofka.aggregate.account.AccountAggregate;
import ec.com.sofka.request.CreateAccountRequest;
import ec.com.sofka.gateway.AccountRepositoryGateway;
import ec.com.sofka.gateway.IEventStoreGateway;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.generics.interfaces.IUseCase;
import ec.com.sofka.responses.CreateAccountResponse;

//Usage of the IUseCase interface
public class CreateAccountUseCase implements IUseCase<CreateAccountRequest,CreateAccountResponse> {
    private final IEventStoreGateway iEventStoreGateway;
    private final AccountRepositoryGateway accountRepositoryGateway;

    public CreateAccountUseCase(IEventStoreGateway repository, AccountRepositoryGateway accountRepository) {
        this.iEventStoreGateway = repository;
        this.accountRepositoryGateway = accountRepository;
    }


    //Of course, you have to create that class Response in usecases module on a package called responses or you can also group the command with their response class in a folder (Screaming architecture)
    //You maybe want to check Jacobo's repository to see how he did it
    public CreateAccountResponse execute(CreateAccountRequest cmd) {
        //Create the aggregate, remember this usecase is to create the account the first time so just have to create it.
        AccountAggregate customer = new AccountAggregate();

        //Then we create the account
        customer.createAccount(cmd.getBalance(), cmd.getNumber(), cmd.getCustomerName());

        //Save the account on the account repository
        accountRepositoryGateway.save(
                new AccountDTO(
                        customer.getAccount().getBalance().getValue(),
                        customer.getAccount().getNumber().getValue(),
                        customer.getAccount().getName().getValue()
                ));

        //Last step for events to be saved
        customer.getUncommittedEvents().forEach(iEventStoreGateway::save);

        customer.markEventsAsCommitted();

        //Return the response
        return new CreateAccountResponse(
                customer.getId().getValue(),
                customer.getAccount().getNumber().getValue(),
                customer.getAccount().getName().getValue());
    }
}
