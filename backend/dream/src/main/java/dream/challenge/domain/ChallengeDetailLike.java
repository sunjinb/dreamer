package dream.challenge.domain;


import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.common.domain.BaseCheckType;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeDetailLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeDetailLikeId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "detail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeDetail challengeDetail;

    @Enumerated(EnumType.STRING)
    private BaseCheckType isLike;

    public static ChallengeDetailLike createLike(ChallengeDetail challengeDetail, User user) {

        ChallengeDetailLike challengeDetailLike = new ChallengeDetailLike();
        challengeDetailLike.challengeDetail = challengeDetail;
        challengeDetailLike.user = user;

        return challengeDetailLike;
    }
}
