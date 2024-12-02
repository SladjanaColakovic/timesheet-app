package com.timesheet.app.data;

import com.timesheet.app.enums.ProjectStatus;
import com.timesheet.app.model.Client;
import com.timesheet.app.model.Country;
import com.timesheet.app.model.Employee;
import com.timesheet.app.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimesheetItemDataServiceConstants {
    public static final Long LEAD_ID = 1L;
    public static final String LEAD_NAME = "Test User";

    public static final Long EMPLOYEE_ID = 1L;
    public static final String EMPLOYEE_NAME = "Test User";


    public static final Long EMPLOYEE_ID_SUCCESSFULLY_ADDED_PROJECT_TO = 2L;
    public static final String EMPLOYEE_NAME_SUCCESSFULLY_ADDED_PROJECT_TO = "Test User For Adding Project";

    public static final boolean IS_DELETED = false;

    public static final Long PROJECT_ID_1 = 1L;
    public static final String PROJECT_NAME_1 = "Test Project 1";
    public static final String PROJECT_DESCRIPTION_1 = "Test Project 1 Description";
    public static final ProjectStatus PROJECT_STATUS_1 = ProjectStatus.Active;

    public static final Long PROJECT_ID_2 = 2L;
    public static final String PROJECT_NAME_2 = "Test Project 2";
    public static final String PROJECT_DESCRIPTION_2 = "Test Project 2 Description";
    public static final ProjectStatus PROJECT_STATUS_2 = ProjectStatus.Active;

    public static final Long PROJECT_ID_3 = 3L;
    public static final String PROJECT_NAME_3 = "Test Project 3";
    public static final String PROJECT_DESCRIPTION_3 = "Test Project 3 Description";
    public static final ProjectStatus PROJECT_STATUS_3 = ProjectStatus.Active;

    public static final Long CLIENT_ID_1 = 1L;
    public static final String CLIENT_NAME_1 = "Test Client 1";
    public static final String CLIENT_ADDRESS_1 = "Test Client Address 1";
    public static final String CLIENT_CITY_1 = "Test Client City 1";
    public static final String CLIENT_POSTAL_CODE_1 = "Test Client Postal Code 1";


    public static final Long CLIENT_ID_2 = 2L;
    public static final String CLIENT_NAME_2 = "Test Client 2";
    public static final String CLIENT_ADDRESS_2 = "Test Client Address 2";
    public static final String CLIENT_CITY_2 = "Test Client City 2";
    public static final String CLIENT_POSTAL_CODE_2 = "Test Client Postal Code 2";


    public static final Long CLIENT_ID_3 = 3L;
    public static final String CLIENT_NAME_3 = "Test Client 3";
    public static final String CLIENT_ADDRESS_3 = "Test Client Address 3";
    public static final String CLIENT_CITY_3 = "Test Client City 3";
    public static final String CLIENT_POSTAL_CODE_3 = "Test Client Postal Code 3";

    public static final Employee TEST_EMPLOYEE = new Employee(
            EMPLOYEE_ID,
            EMPLOYEE_NAME,
            new ArrayList<>(Arrays.asList(
                    new Project(
                            PROJECT_ID_1,
                            PROJECT_NAME_1,
                            PROJECT_DESCRIPTION_1,
                            PROJECT_STATUS_1,
                            new Client(
                                    CLIENT_ID_1,
                                    CLIENT_NAME_1,
                                    CLIENT_ADDRESS_1,
                                    CLIENT_CITY_1,
                                    CLIENT_POSTAL_CODE_1,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    ),
                    new Project(
                            PROJECT_ID_2,
                            PROJECT_NAME_2,
                            PROJECT_DESCRIPTION_2,
                            PROJECT_STATUS_2,
                            new Client(
                                    CLIENT_ID_2,
                                    CLIENT_NAME_2,
                                    CLIENT_ADDRESS_2,
                                    CLIENT_CITY_2,
                                    CLIENT_POSTAL_CODE_2,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    )

            ))

    );

    public static final Employee TEST_EMPLOYEE_SUCCESSFULLY_ADDED_TO_PROJECT_BEFORE = new Employee(
            EMPLOYEE_ID,
            EMPLOYEE_NAME,
            new ArrayList<>(Arrays.asList(
                    new Project(
                            PROJECT_ID_1,
                            PROJECT_NAME_1,
                            PROJECT_DESCRIPTION_1,
                            PROJECT_STATUS_1,
                            new Client(
                                    CLIENT_ID_1,
                                    CLIENT_NAME_1,
                                    CLIENT_ADDRESS_1,
                                    CLIENT_CITY_1,
                                    CLIENT_POSTAL_CODE_1,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    ),
                    new Project(
                            PROJECT_ID_2,
                            PROJECT_NAME_2,
                            PROJECT_DESCRIPTION_2,
                            PROJECT_STATUS_2,
                            new Client(
                                    CLIENT_ID_2,
                                    CLIENT_NAME_2,
                                    CLIENT_ADDRESS_2,
                                    CLIENT_CITY_2,
                                    CLIENT_POSTAL_CODE_2,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    )

            ))

    );

    public static final Employee TEST_EMPLOYEE_SUCCESSFULLY_ADDED_TO_PROJECT_AFTER = new Employee(
            EMPLOYEE_ID_SUCCESSFULLY_ADDED_PROJECT_TO,
            EMPLOYEE_NAME_SUCCESSFULLY_ADDED_PROJECT_TO,
            new ArrayList<>(Arrays.asList(
                    new Project(
                            PROJECT_ID_1,
                            PROJECT_NAME_1,
                            PROJECT_DESCRIPTION_1,
                            PROJECT_STATUS_1,
                            new Client(
                                    CLIENT_ID_1,
                                    CLIENT_NAME_1,
                                    CLIENT_ADDRESS_1,
                                    CLIENT_CITY_1,
                                    CLIENT_POSTAL_CODE_1,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    ),
                    new Project(
                            PROJECT_ID_2,
                            PROJECT_NAME_2,
                            PROJECT_DESCRIPTION_2,
                            PROJECT_STATUS_2,
                            new Client(
                                    CLIENT_ID_2,
                                    CLIENT_NAME_2,
                                    CLIENT_ADDRESS_2,
                                    CLIENT_CITY_2,
                                    CLIENT_POSTAL_CODE_2,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    ),
                    new Project(
                            PROJECT_ID_3,
                            PROJECT_NAME_3,
                            PROJECT_DESCRIPTION_3,
                            PROJECT_STATUS_3,
                            new Client(
                                    CLIENT_ID_3,
                                    CLIENT_NAME_3,
                                    CLIENT_ADDRESS_3,
                                    CLIENT_CITY_3,
                                    CLIENT_POSTAL_CODE_3,
                                    new Country(),
                                    IS_DELETED
                            ),
                            new Employee(LEAD_ID, LEAD_NAME),
                            IS_DELETED
                    )

            ))

    );

    public static final Project TEST_PROJECT = new Project(
            PROJECT_ID_3,
            PROJECT_NAME_3,
            PROJECT_DESCRIPTION_3,
            PROJECT_STATUS_3,
            new Client(
                    CLIENT_ID_3,
                    CLIENT_NAME_3,
                    CLIENT_ADDRESS_3,
                    CLIENT_CITY_3,
                    CLIENT_POSTAL_CODE_3,
                    new Country(),
                    IS_DELETED
            ),
            new Employee(LEAD_ID, LEAD_NAME),
            IS_DELETED
    );

    public static final List<Project> TEST_LEADING_PROJECTS = new ArrayList<>(Arrays.asList(new Project(
            PROJECT_ID_3,
            PROJECT_NAME_3,
            PROJECT_DESCRIPTION_3,
            PROJECT_STATUS_3,
            new Client(
                    CLIENT_ID_3,
                    CLIENT_NAME_3,
                    CLIENT_ADDRESS_3,
                    CLIENT_CITY_3,
                    CLIENT_POSTAL_CODE_3,
                    new Country(),
                    IS_DELETED
            ),
            new Employee(EMPLOYEE_ID, EMPLOYEE_NAME),
            IS_DELETED
    )));

    public static final Client TEST_CLIENT = new Client(
            CLIENT_ID_1,
            CLIENT_NAME_1,
            CLIENT_ADDRESS_1,
            CLIENT_CITY_1,
            CLIENT_POSTAL_CODE_1,
            new Country(),
            IS_DELETED
    );

    public static final int EXPECTED_CLIENT_LIST_SIZE = 3;
    public static final int EXPECTED_PROJECT_LIST_SIZE = 1;
    public static final int EXPECTED_EMPLOYEE_PROJECT_LIST_SIZE = 3;



}
