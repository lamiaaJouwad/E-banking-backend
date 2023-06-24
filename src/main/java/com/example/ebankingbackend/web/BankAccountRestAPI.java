package com.example.ebankingbackend.web;

import com.example.ebankingbackend.Exceptions.BalanceNotSufficientException;
import com.example.ebankingbackend.Exceptions.BankAccountNotFoundException;
import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    public BankAccountRestAPI(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){

        return  bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId
                , @RequestParam(name="page" , defaultValue = "0") int page ,
                                               @RequestParam(name = "size" , defaultValue = "5") int size) throws BankAccountNotFoundException {
       return   bankAccountService.getAccountHistory(accountId, page , size); }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountID() , debitDTO.getAmount() , debitDTO.getDescription() );
    return debitDTO ;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountID() , creditDTO.getAmount() , creditDTO.getDescription() );
        return  creditDTO ;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer( transferRequestDTO.getAccountSource() ,
                transferRequestDTO.getAccountDestination()  ,
                transferRequestDTO.getAmount() );

    }


}

