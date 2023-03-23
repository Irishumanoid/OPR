package frc.API.type;

import lombok.Data;

/**
 * Data class representing a frc team, using lombok @Data.
 */
@Data
public class Team {

    private final int teamNumber;
    private final String teamId;

    public Team(int teamNumber) {
        this.teamNumber = teamNumber;
        this.teamId = "frc" + teamNumber;
    }

}
