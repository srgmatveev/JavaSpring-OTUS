package org.sergio.library.domain.security.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "acl_sid")
@Data
@NoArgsConstructor

public class AclSid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int principal;
    @NonNull
    @Size(max = 100)
    private String sid;


}
