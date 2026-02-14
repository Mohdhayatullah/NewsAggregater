package org.currentaffairs.DailyBrief_AI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "client")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client{
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    private String password;
}
