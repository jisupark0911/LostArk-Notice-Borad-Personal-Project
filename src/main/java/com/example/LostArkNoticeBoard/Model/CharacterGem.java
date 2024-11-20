package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CharacterGem {
    @JsonProperty("Gems")
    private List<Gem> gem;

    @JsonProperty("Effects")
    private ArmoryGemEffect armoryGemEffect;

    public List<Gem> getSortedGems() {
        return gem.stream()
                .sorted(Comparator.comparing((Gem g) -> {
                                    // "멸화"가 먼저 오도록 우선순위 설정
                                    if (g.getName().contains("멸화")) return 0;
                                    if (g.getName().contains("겁화")) return 1;
                                    if (g.getName().contains("홍염")) return 2;
                                    return 3; // 나머지 Gem은 가장 낮은 우선순위 -> 작열
                                })
                                .thenComparing((Gem g) -> {
                                    // "멸화"와 "겁화" 우선 정렬 후, 레벨 내림차순으로 정렬
                                    if (g.getName().contains("멸화") || g.getName().contains("겁화")) return g.getLevel();
                                    return Integer.MAX_VALUE; // 나머지 Gem은 가장 낮은 우선순위
                                }, Comparator.reverseOrder()) // 레벨 내림차순
                )
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class Gem {
        @JsonProperty("Slot")
        private int slot;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

    @Getter
    @Setter
    public static class ArmoryGemEffect {
        @JsonProperty("Description")
        private String description;

        @JsonProperty("Skills")
        private List<GemEffect> gemEffect;
    }

    @Getter
    @Setter
    public static class GemEffect {
        @JsonProperty("GemSlot")
        private int gemSlot;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private List<String> description;

        @JsonProperty("Option")
        private String option;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Tooltip")
        private String tooltip;
    }
}