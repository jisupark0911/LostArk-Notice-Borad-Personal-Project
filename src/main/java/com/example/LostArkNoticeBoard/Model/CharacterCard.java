package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterCard {
    @JsonProperty("Cards")
    private List<Card> card;

    @JsonProperty("Effects")
    private List<CardEffect> cardEffect;

    @Getter
    @Setter
    public static class Card {
        @JsonProperty("Slot")
        private int slot;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("AwakeCount")
        private int awakeCount;

        @JsonProperty("AwakeTotal")
        private int awakeTotal;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

    @Getter
    @Setter
    public static class CardEffect {
        @JsonProperty("Index")
        private int index;

        @JsonProperty("CardSlots")
        private List<Integer> cardSlots; //이부분 리스트에 integer 감싸져있어서 주의해야함

        @JsonProperty("Items")
        private List<Effect> effect;
    }

    @Getter
    @Setter
    public static class Effect { //카드세트 효과
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private String description;
    }
}
