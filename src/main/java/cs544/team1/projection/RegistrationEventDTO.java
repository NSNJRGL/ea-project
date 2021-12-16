package cs544.team1.projection;

import cs544.team1.model.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEventDTO {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Audit audit;
    private String status;
}
