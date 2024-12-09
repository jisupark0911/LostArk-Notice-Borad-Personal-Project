package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.Model.*;
import com.example.LostArkNoticeBoard.Model.Character;
import com.example.LostArkNoticeBoard.service.LostArkApiService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Slf4j
public class LostArkApiController {
    private static final Logger logger = LoggerFactory.getLogger(LostArkApiController.class);
    private final LostArkApiService lostArkApiService;

    public LostArkApiController(LostArkApiService lostArkApiService) {
        this.lostArkApiService = lostArkApiService;
    }

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public void loginNickName(Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
    }

    @GetMapping("/news/notices")
    public String getNotices(Model model) {
        try {
            Notice[] notices = lostArkApiService.getNotices();
            model.addAttribute("notices", notices);
            logger.info("받은 notice 데이터: {}", (Object) notices);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "news/notices";
    }

    @GetMapping("/news/events")
    public String getEvents(Model model) {
        try {
            Event[] events = lostArkApiService.getEvents();
            model.addAttribute("events", events);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "news/events";
    }


    @GetMapping("/character/search")
    public String showSearchForm() {
        return "character/inputForm";
    }

    @GetMapping("/characters/{characterName}/siblings")
    public String getCharacters(@PathVariable String characterName, Model model) {
        try {
            Character[] characters = lostArkApiService.getCharacters(characterName);
            model.addAttribute("characters", characters);
            model.addAttribute("characterName", characterName);
        } catch (IOException e) {
            log.error("캐릭터 정보를 가져오는 데 실패했습니다.", e);
            model.addAttribute("error", "캐릭터 정보를 가져오는 데 실패했습니다.");
        }
        return "character/character";
    }

    @GetMapping("/characters/{characterName}")
    public String getCharacterDetails(@PathVariable String characterName, Model model) {
        long startTime = System.currentTimeMillis();

        try {
            CompletableFuture<CharacterProfile> characterProfileFuture = lostArkApiService.getCharacterProfileAsync(characterName);
            CompletableFuture<CharacterEquipment[]> characterEquipmentsFuture = lostArkApiService.getCharacterEquipmentAsync(characterName);
            CompletableFuture<CharacterEngraving> characterEngravingsFuture = lostArkApiService.getCharacterEngravingsAsync(characterName);
            CompletableFuture<CharacterCard> characterCardsFuture = lostArkApiService.getCharacterCardsAsync(characterName);
            CompletableFuture<CharacterGem> characterGemsFuture = lostArkApiService.getCharacterGemsAsync(characterName);
            CompletableFuture<CharacterArkPassive> characterArkPassivesFuture = lostArkApiService.getCharacterArkPassivesAsync(characterName);
            CompletableFuture<CharacterAvatar[]> characterAvatarsFuture = lostArkApiService.getCharacterAvatarsAsync(characterName);
            CompletableFuture<List<CharacterCombatSkill>> characterCombatSkillsFuture = lostArkApiService.getCharacterCombatSkillsAsync(characterName);
            CompletableFuture<List<CharacterCollectible>> characterCollectiblesFuture = lostArkApiService.getCharacterCollectiblesAsync(characterName);

            characterProfileFuture.thenAcceptAsync(characterProfile -> {
                model.addAttribute("characterProfile", characterProfile);
            });

            characterEquipmentsFuture.thenAcceptAsync(characterEquipments -> {
                List<CharacterEquipment> leftEquipments = Arrays.asList(characterEquipments).subList(0, 6);
                List<CharacterEquipment> rightEquipments = Arrays.asList(characterEquipments).subList(6, Math.min(13, characterEquipments.length)); // 13보다 작을때 오류 방지
                model.addAttribute("leftEquipments", leftEquipments);
                model.addAttribute("rightEquipments", rightEquipments);
            });

            characterEngravingsFuture.thenAcceptAsync(characterEngravings -> {
                model.addAttribute("characterEngravings", characterEngravings);
            });

            characterCardsFuture.thenAcceptAsync(characterCards -> {
                model.addAttribute("characterCards", characterCards);
            });

            characterGemsFuture.thenAcceptAsync(characterGems -> {
                model.addAttribute("characterGems", characterGems);
            });

            characterArkPassivesFuture.thenAcceptAsync(characterArkPassives -> {
                model.addAttribute("characterArkPassives", characterArkPassives);
            });

            characterAvatarsFuture.thenAcceptAsync(characterAvatars -> {
                int midIndex = characterAvatars.length / 2;
                List<CharacterAvatar> leftAvatars = Arrays.asList(characterAvatars).subList(0, midIndex);
                List<CharacterAvatar> rightAvatars = Arrays.asList(characterAvatars).subList(midIndex, characterAvatars.length);
                model.addAttribute("leftAvatars", leftAvatars);
                model.addAttribute("rightAvatars", rightAvatars);
            });

            characterCombatSkillsFuture.thenAcceptAsync(characterCombatSkills -> {
                model.addAttribute("characterCombatSkills", characterCombatSkills);
            });

            characterCollectiblesFuture.thenAcceptAsync(characterCollectibles -> {
                model.addAttribute("characterCollectibles", characterCollectibles);
            });

            model.addAttribute("characterName", characterName);

            CompletableFuture.allOf(
                    characterProfileFuture,
                    characterEquipmentsFuture,
                    characterEngravingsFuture,
                    characterCardsFuture,
                    characterGemsFuture,
                    characterArkPassivesFuture,
                    characterAvatarsFuture,
                    characterCombatSkillsFuture,
                    characterCollectiblesFuture
            ).join();

            long endTime = System.currentTimeMillis();
            log.info("캐릭터 이름: {}. 캐릭터 정보를 가져오는 전체 시간: {} ms", characterName, (endTime - startTime));

        } catch (IOException e) {
            log.error("캐릭터 정보를 가져오는 데 실패했습니다.", e);
            model.addAttribute("error", "캐릭터 정보를 가져오는 데 실패했습니다.");
        }
        return "character/character";
    }




    @GetMapping("/gamecontents/challenge")
    public String getGameContents(Model model) {
        try {
            Abyss[] abyss = lostArkApiService.getAbyss();
            Guardian guardians = lostArkApiService.getGuardian();
            System.out.println(guardians);


            model.addAttribute("abyss", abyss);
            model.addAttribute("guardians", guardians);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "gamecontents/challenge";
    }
}