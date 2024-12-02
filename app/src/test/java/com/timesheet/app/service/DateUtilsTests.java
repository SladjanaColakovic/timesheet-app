package com.timesheet.app.service;

import com.timesheet.app.data.DateUtilsConstants;
import com.timesheet.app.helper.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DateUtilsTests {

    @Test
    void testGetFirstMondayForTimesheet(){
        LocalDate result = DateUtils.getFirstMondayForTimesheet(DateUtilsConstants.YEAR, DateUtilsConstants.MONTH);
        assertThat(result).isEqualTo(DateUtilsConstants.EXPECTED_FIRST_DATE_FOR_TIMESHEET);
    }

    @Test
    void testGetLastSundayForTimesheet(){
        LocalDate result = DateUtils.getLastSundayForTimesheet(DateUtilsConstants.YEAR, DateUtilsConstants.MONTH);
        assertThat(result).isEqualTo(DateUtilsConstants.EXPECTED_LAST_DATE_FOR_TIMESHEET);
    }

}
