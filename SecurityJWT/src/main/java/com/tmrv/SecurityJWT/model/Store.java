package com.tmrv.SecurityJWT.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NonNull
    private String name;
}
