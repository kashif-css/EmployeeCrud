package com.example.EmployeeCrud.Model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String B_name;
    private String Author;


}
