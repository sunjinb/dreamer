package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallenge {

    private Long challengeId;
    private String title;
    private String period;

    public static ResponseChallenge from(Challenge challenge){

        ResponseChallenge response = new ResponseChallenge();

        response.challengeId = challenge.getChallengeId();
        response.title = challenge.getChallengeTitle();
        response.period = challenge.getPeriod();

        return response;
    }

}
