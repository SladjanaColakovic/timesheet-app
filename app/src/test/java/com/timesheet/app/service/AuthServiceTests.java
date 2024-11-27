package com.timesheet.app.service;

import com.timesheet.app.data.AuthServiceConstants;
import com.timesheet.app.dto.auth.LoginRequest;
import com.timesheet.app.exception.DuplicateUsernameException;
import com.timesheet.app.model.Employee;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.security.service.CustomUserDetailsService;
import com.timesheet.app.security.token.TokenUtils;
import com.timesheet.app.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private TokenUtils tokenUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testLogin_Successful(){
        setUpLoginSuccessful();
        String result = authService.login(new LoginRequest());
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(AuthServiceConstants.JWT_TOKEN);
        verify(authenticationManager, times(1)).authenticate(any());
        verify(userDetailsService, times(1)).loadUserByUsername(any());
        verify(tokenUtils, times(1)).generateToken(any());

    }

    @Test
    void testLogin_Failure(){
        setUpLoginFailure();
        String result = null;
        try {
            result = authService.login(new LoginRequest());
        } catch(BadCredentialsException exc) {
            assertThat(result).isNull();
            verify(authenticationManager, times(1)).authenticate(any());
            verify(userDetailsService, never()).loadUserByUsername(any());
            verify(tokenUtils, never()).generateToken(any());
        }
    }

    @Test
    void testRegister_Successful(){
        setUpRegisterSuccessful();
        Employee employee = new Employee();
        employee.setPassword(AuthServiceConstants.PASSWORD);
        Employee result = authService.register(employee);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(AuthServiceConstants.EMPLOYEE_NAME);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void testRegister_Failure(){
        setUpRegister_Failure();
        Employee result = null;
        try {
            result = authService.register(new Employee());
        } catch(DuplicateUsernameException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByUsernameAndDeletedFalse(any());
            verify(employeeRepository, never()).save(any());
        }
    }

    private void setUpRegisterSuccessful(){
        when(employeeRepository.findByUsernameAndDeletedFalse(any())).thenReturn(Optional.empty());
        when(employeeRepository.save(any())).thenReturn(new Employee(AuthServiceConstants.EMPLOYEE_ID, AuthServiceConstants.EMPLOYEE_NAME));
    }

    private void setUpRegister_Failure(){
        when(employeeRepository.findByUsernameAndDeletedFalse(any())).thenReturn(Optional.of(new Employee(AuthServiceConstants.EMPLOYEE_ID, AuthServiceConstants.EMPLOYEE_NAME)));
    }

    private void setUpLoginSuccessful(){
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        User user = mock(User.class);
        when(userDetailsService.loadUserByUsername(any())).thenReturn(user);
        when(tokenUtils.generateToken(any())).thenReturn(AuthServiceConstants.JWT_TOKEN);
    }

    private void setUpLoginFailure(){
       when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException(AuthServiceConstants.BAD_CREDENTIALS_EXCEPTION_MESSAGE));
    }
}
