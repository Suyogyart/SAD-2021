package com.example.Lab3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = LeaveType.Values.SICK)
public class SickLeave extends Leave {

    private int daysAllowed = 30;

    public SickLeave(Employee emp,
                     boolean approved,
                     String remarks,
                     LocalDate start,
                     LocalDate end) {
        super(emp, approved, remarks, start, end);
    }
}
