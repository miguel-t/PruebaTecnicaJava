package sat.recruitment.api.user.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID" )
    private Long userId;

    @Column(name="USER_NAME",nullable = false )
    private String userName;

    @Column(name="EMAIL",nullable = false)
    private String email;

    @Column(name="ADDRESS",nullable = false)
    private String address;

    @Column(name="PHONE",nullable = false)
    private String phone;

    @Column(name="USER_TYPE",nullable = true)
    private String userType;

    @Column(name="MONEY",nullable = true)
    private double money;




}
