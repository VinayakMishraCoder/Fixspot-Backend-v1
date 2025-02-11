package com.fixspot.backendv1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static com.fixspot.backendv1.generalUtil.constants.TableNames.ISSUE_TABLE;
import static com.fixspot.backendv1.generalUtil.constants.TableNames.USER_ISSUE_UP_VOTES;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ISSUE_TABLE)
public class IssueEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "issue_description", nullable = false)
    private String issueDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id_fk", nullable = false)
    private UserEntity issuer;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @Column
    @ElementCollection
    private List<String> imgUrls;

    @ManyToMany
    @JoinTable(
            name = USER_ISSUE_UP_VOTES,
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> upvoters;
}
