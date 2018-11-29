package cn.jjsunw.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    @Column(name = "large_head_portrait")
    private String largeHeadPortrait;

    @Column(name = "small_head_portrait")
    private String smallHeadPortrait;

    private Boolean enable;

    @Column(name = "last_pwd_reset_time")
    private Date lastPwdResetTime;
    
    @Transient
    Set<Role> roles;

}