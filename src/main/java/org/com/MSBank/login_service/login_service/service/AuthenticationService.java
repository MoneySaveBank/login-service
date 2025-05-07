package org.com.MSBank.login_service.login_service.service;

import org.com.MSBank.login_service.login_service.view.CredentialDto;

public interface AuthenticationService {

    public String authenticate(CredentialDto credentialsDto);
}
