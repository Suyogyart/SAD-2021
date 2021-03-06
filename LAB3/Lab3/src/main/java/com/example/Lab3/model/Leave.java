package com.example.Lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name = "LEAVE_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate start;
    private LocalDate end;
    String remarks;
    private boolean approved;

    @Column(name = "LEAVE_TYPE", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    public LeaveType leaveType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Employee emp;

    public Leave(Employee emp, boolean approved, String remarks, LocalDate start, LocalDate end) {
        super();
        this.start = start;
        this.end = end;
        this.remarks = remarks;
        this.approved = approved;
        this.emp = emp;
    }
}
