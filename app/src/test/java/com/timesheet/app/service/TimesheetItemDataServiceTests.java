package com.timesheet.app.service;

import com.timesheet.app.data.TimesheetItemDataServiceConstants;
import com.timesheet.app.exception.ClientNotFoundException;
import com.timesheet.app.exception.EmployeeNotFoundException;
import com.timesheet.app.exception.ProjectNotFoundException;
import com.timesheet.app.helper.EmployeeProject;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;
import com.timesheet.app.repository.ClientRepository;
import com.timesheet.app.repository.EmployeeRepository;
import com.timesheet.app.repository.ProjectRepository;
import com.timesheet.app.service.impl.TimesheetItemDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimesheetItemDataServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TimesheetItemDataServiceImpl service;


    @Test
    void testGetEmployeeClients_Successful(){
        setUpGetEmployeeClientsSuccessful();
        List<Client> result = service.getEmployeeClients(TimesheetItemDataServiceConstants.EMPLOYEE_ID);
        assertThat(result).hasSize(TimesheetItemDataServiceConstants.EXPECTED_CLIENT_LIST_SIZE);
        verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(projectRepository, times(1)).findByLeadIdAndDeletedFalse(any());
    }

    @Test
    void testGetEmployeeClients_Failure(){
        setUpGetEmployeeClientsFailure();
        List<Client> result = null;
        try {
           result = service.getEmployeeClients(TimesheetItemDataServiceConstants.EMPLOYEE_ID);
        } catch (EmployeeNotFoundException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(projectRepository, never()).findByLeadIdAndDeletedFalse(any());
        }
    }

    @Test
    void testGetEmployeeProjectsForCertainClient_Successful(){
        setUpGetEmployeeProjectsForCertainClientSuccessful();
        List<Project> result = service.getEmployeeProjectsForCertainClient(TimesheetItemDataServiceConstants.EMPLOYEE_ID, TimesheetItemDataServiceConstants.CLIENT_ID_1);
        assertThat(result).hasSize(TimesheetItemDataServiceConstants.EXPECTED_PROJECT_LIST_SIZE);
        verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(clientRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(projectRepository, times(1)).findByLeadIdAndDeletedFalse(any());
    }

    @Test
    void testGetEmployeeProjectsForCertainClient_Failure(){
        setUpGetEmployeeProjectsForCertainClientFailure();
        List<Project> result = null;
        try {
            result = service.getEmployeeProjectsForCertainClient(TimesheetItemDataServiceConstants.EMPLOYEE_ID, TimesheetItemDataServiceConstants.CLIENT_ID_2);
        } catch (ClientNotFoundException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(clientRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(projectRepository, never()).findByLeadIdAndDeletedFalse(any());
        }
    }

    @Test
    void testAddProjectToEmployee_Successful(){
        setUpAddProjectToEmployeeSuccessful();
        Employee result = service.addProjectToEmployee(new EmployeeProject());
        assertThat(result.getName()).isEqualTo(TimesheetItemDataServiceConstants.EMPLOYEE_NAME_SUCCESSFULLY_ADDED_PROJECT_TO);
        assertThat(result.getProjects()).hasSize(TimesheetItemDataServiceConstants.EXPECTED_EMPLOYEE_PROJECT_LIST_SIZE);
        verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(projectRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void testAddProjectToEmployee_Failure(){
        setUpAddProjectToEmployeeFailure();
        Employee result = null;
        try {
            result = service.addProjectToEmployee(new EmployeeProject());
        } catch (ProjectNotFoundException ex){
            assertThat(result).isNull();
            verify(employeeRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(projectRepository, times(1)).findByIdAndDeletedFalse(any());
            verify(employeeRepository, never()).save(any());
        }
    }

    private void setUpAddProjectToEmployeeFailure(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_EMPLOYEE_SUCCESSFULLY_ADDED_TO_PROJECT_BEFORE));
        when(projectRepository.findByIdAndDeletedFalse(any())).thenThrow(new ProjectNotFoundException());
    }

    private void setUpAddProjectToEmployeeSuccessful(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_EMPLOYEE_SUCCESSFULLY_ADDED_TO_PROJECT_BEFORE));
        when(projectRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_PROJECT));
        when(employeeRepository.save(any())).thenReturn(TimesheetItemDataServiceConstants.TEST_EMPLOYEE_SUCCESSFULLY_ADDED_TO_PROJECT_AFTER);
    }

    private void setUpGetEmployeeProjectsForCertainClientSuccessful(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_EMPLOYEE));
        when(clientRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_CLIENT));
        when(projectRepository.findByLeadIdAndDeletedFalse(any())).thenReturn(TimesheetItemDataServiceConstants.TEST_LEADING_PROJECTS);
    }

    private void setUpGetEmployeeProjectsForCertainClientFailure(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_EMPLOYEE));
        when(clientRepository.findByIdAndDeletedFalse(any())).thenThrow(new ClientNotFoundException());
    }

    private void setUpGetEmployeeClientsSuccessful(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenReturn(Optional.of(TimesheetItemDataServiceConstants.TEST_EMPLOYEE));
        when(projectRepository.findByLeadIdAndDeletedFalse(any())).thenReturn(TimesheetItemDataServiceConstants.TEST_LEADING_PROJECTS);
    }

    private void setUpGetEmployeeClientsFailure(){
        when(employeeRepository.findByIdAndDeletedFalse(any())).thenThrow(new EmployeeNotFoundException());
    }

}
