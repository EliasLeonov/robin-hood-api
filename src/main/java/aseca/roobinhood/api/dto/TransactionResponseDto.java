package aseca.roobinhood.api.dto;

import aseca.roobinhood.api.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDto {
    private Long id;
    private TickerDto tickerDto;
    private UserDto userDto;
    private double price;
    private double amount;
    private LocalDateTime dateTime;

    public static TransactionResponseDto from(Transaction transaction){
        return TransactionResponseDto
                .builder()
                .id(transaction.getId())
                .tickerDto(TickerDto.from(transaction.getTicker()))
                .userDto(UserDto.from(transaction.getUser()))
                .price(transaction.getPrice())
                .amount(transaction.getAmount())
                .dateTime(transaction.getDateTime())
                .build();
    }

}
