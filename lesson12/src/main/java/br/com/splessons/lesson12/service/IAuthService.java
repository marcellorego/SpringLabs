package br.com.splessons.lesson12.service;

import br.com.splessons.lesson12.exception.AppException;
import br.com.splessons.lesson12.model.User;
import br.com.splessons.lesson12.payload.SignUpRequest;
import br.com.splessons.lesson12.security.domain.UserInfo;

public interface IAuthService {

    UserInfo signUpUser(SignUpRequest signUpRequest) throws AppException;
}
