package aseca.roobinhood.api.domain;

import aseca.roobinhood.api.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Stoncks> stoncks;

    public UserDto toDto(){
        return UserDto
                .builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .stocks(stoncks
                        .stream()
                        .map(Stoncks::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
