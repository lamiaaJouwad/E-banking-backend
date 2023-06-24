package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("2df0ec88-add9-409e-9f57-01a559af4ff3").orElse(null);
        if(bankAccount!=null){
            System.out.printf("*********************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount){
                System.out.println("Over draft =>"+((CurrentAccount)bankAccount).getOverDraft());

            }
            else if (bankAccount instanceof SavingAccount){
                System.out.println("Rate"+((SavingAccount)bankAccount).getInterestRate());

            }
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println("===============");
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());

            });
        }

    }
}
