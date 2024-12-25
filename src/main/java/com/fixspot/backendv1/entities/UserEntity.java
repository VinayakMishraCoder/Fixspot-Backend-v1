package com.fixspot.backendv1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String longitude;
    private String latitude;

    private String houseNo;
    private String area;
    private String city;
    private String pinCode;
    private String landmark;

    private String mobileNo;
    private String firstName;
    private String lastName;

    private String role;

    @OneToMany(mappedBy = "issuer", cascade = CascadeType.ALL)
    private List<IssueEntity> reportedIssues; // Issues reported by this user.
}