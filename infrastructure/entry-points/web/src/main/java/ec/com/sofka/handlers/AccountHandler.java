package ec.com.sofka.handlers;

import ec.com.sofka.usecase.account.CreateAccountUseCase;
import ec.com.sofka.request.CreateAccountRequest;
import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;
import ec.com.sofka.usecase.account.UpdateAccountUseCase;
import org.springframework.stereotype.Component;

@Component
public class AccountHandler {
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    public AccountHandler(CreateAccountUseCase createAccountUseCase, UpdateAccountUseCase updateAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
    }

    public AccountResponseDTO createAccount(AccountRequestDTO request){

        var response = createAccountUseCase.execute(
                new CreateAccountRequest(
                        request.getBalance(),
                        request.getAccount(),
                        request.getCustomer()

                ));
        return new AccountResponseDTO(response.getCustomerId(), response.getName());
    }

    public AccountResponseDTO updateAccount(AccountRequestDTO request){

        var response = updateAccountUseCase.execute(
                new CreateAccountRequest(
                        request.getBalance(),
                        request.getAccount(),
                        request.getCustomer()

                ));
        return new AccountResponseDTO(response.getCustomerId(), response.getName());
    }
}
