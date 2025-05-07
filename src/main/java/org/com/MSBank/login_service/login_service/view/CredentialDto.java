package org.com.MSBank.login_service.login_service.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {
    private String customerNumber;
    private String password;

    @Override
    public String toString() {
        return "CredentialDto{" +
                "password='" + password + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                '}';
    }
}
