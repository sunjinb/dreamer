package dream.card.dto.response;

import dream.card.domain.CardKeyword;
import dream.card.domain.DreamCard;
import dream.card.domain.Grade;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFlipDreamCardDetail {
    private long dreamCardId;
    private long dreamCardOwner;
    private String ownerNickname;
    private long dreamCardAuthor;
    private Grade grade;
    private LocalDateTime createdAt;
    private Grade positiveGrade;
    private Grade rareGrade;
    private BaseCheckType auctionStatus;
    private BaseCheckType isShow;
    private List<ResponseKeyword> keywords;

    public static ResponseFlipDreamCardDetail from(DreamCard dreamCard){
        ResponseFlipDreamCardDetail response = new ResponseFlipDreamCardDetail();

        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardOwner = dreamCard.getDreamCardOwner().getUserId();
        response.ownerNickname = dreamCard.getDreamCardOwner().getNickname();
        response.dreamCardAuthor = dreamCard.getDreamCardAuthor().getUserId();
        response.grade = dreamCard.getGrade();
        response.createdAt = dreamCard.getCreatedAt();
        response.positiveGrade = dreamCard.getPositiveGrade();
        response.rareGrade = dreamCard.getRareGrade();
        response.auctionStatus = dreamCard.getAuctionStatus();
        response.isShow = dreamCard.getIsShow();

        List<ResponseKeyword> keywords = new ArrayList<>();
        List<CardKeyword> cardKeywords = dreamCard.getCardKeyword();
        for (CardKeyword cardKeyword : cardKeywords) {
            keywords.add(ResponseKeyword.from(cardKeyword));
        }
        response.keywords = keywords;

        return response;
    }
}
