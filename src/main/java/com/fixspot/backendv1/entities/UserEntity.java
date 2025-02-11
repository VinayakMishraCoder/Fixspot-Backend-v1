package com.fixspot.backendv1.entities;

import com.fixspot.backendv1.generalUtil.constants.TableNames;
import jakarta.annotation.Nonnull;
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
@Table(
        name = TableNames.USER_TABLE,
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"username"}),
            @UniqueConstraint(columnNames = {"mobile_no"}),
        }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "house_no", nullable = false)
    private String houseNo;

    @Column(name = "area", nullable = false)
    private String area;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "landmark", nullable = false)
    private String landmark;

    @Column(name = "mobile_no", nullable = false, unique = true)
    private String mobileNo;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "issuer", cascade = CascadeType.ALL)
    private List<IssueEntity> reportedIssues;

    @ManyToMany(mappedBy = "upvoters")
    private List<IssueEntity> upvotedIssues;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;
}
