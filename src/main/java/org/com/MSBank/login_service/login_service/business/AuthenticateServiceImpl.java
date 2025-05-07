package org.com.MSBank.login_service.login_service.business;

import org.com.MSBank.login_service.login_service.exception.ForbiddenException;
import org.com.MSBank.login_service.login_service.exception.UnauthorizedException;
import org.com.MSBank.login_service.login_service.model.Customer;
import org.com.MSBank.login_service.login_service.repository.CustomerRepository;
import org.com.MSBank.login_service.login_service.service.AuthenticationService;
import org.com.MSBank.login_service.login_service.view.CredentialDto;
import org.springframework.stereotype.Service;
import java.util.Objects;

import java.util.function.BiPredicate;

@Service
public class AuthenticateServiceImpl implements AuthenticationService {

    private final CustomerRepository loginRepository;

    private final SessionManagmentImpl sessionManagmentImpl;

    BiPredicate<Customer, CredentialDto> customerValidation =
            ((customer, credentialDto) -> Objects.nonNull(customer)
                    && customer.getCustomerNumber().equals(credentialDto.getCustomerNumber())
                    && customer.getPassword().equals(credentialDto.getPassword()));

    public AuthenticateServiceImpl(CustomerRepository loginRepository,
                                   SessionManagmentImpl sessionManagment) {
        this.loginRepository = loginRepository;
        this.sessionManagmentImpl = sessionManagment;
    }

    @Override
    public String authenticate(CredentialDto credentialsDto) {
        Customer customer = loginRepository.findById(credentialsDto.getCustomerNumber()).
                orElseThrow(() -> new ForbiddenException("You are not authorized"));

        if(!customerValidation.test(customer, credentialsDto)){
            throw new UnauthorizedException("Customer or Password incorrect");
        }

        return sessionManagmentImpl.createSession(customer);
    }
}
