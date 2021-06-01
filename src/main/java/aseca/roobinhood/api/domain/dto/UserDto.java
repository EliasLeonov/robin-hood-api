package aseca.roobinhood.api.domain.dto;

import aseca.roobinhood.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private Set<StockDto> stocks;

    public static UserDto from(User user) {
        final Set<StockDto> stocks = user.getStocks().stream().map(StockDto::from).collect(Collectors.toSet());
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .stocks(stocks)
                .build();
    }
}
