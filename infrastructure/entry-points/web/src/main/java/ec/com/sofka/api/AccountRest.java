package ec.com.sofka.api;

import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;
import ec.com.sofka.handlers.AccountHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountRest {

    private final AccountHandler accountHandler;

    public AccountRest(AccountHandler handler) {
        this.accountHandler = handler;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountHandler.createAccount(requestDTO));
    }

    @PutMapping
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody AccountRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(accountHandler.updateAccount(requestDTO));
    }
}
