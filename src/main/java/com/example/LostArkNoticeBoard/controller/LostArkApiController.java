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
import java.util.List;
import java.util.Optional;

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
        try {
            CharacterProfile characterProfile = lostArkApiService.getCharacterProfile(characterName);
            model.addAttribute("characterProfile", characterProfile);

            CharacterEquipment[] characterEquipments = lostArkApiService.getCharacterEquipment(characterName);
            model.addAttribute("characterEquipments", characterEquipments);

            CharacterAvatar[] characterAvatars = lostArkApiService.getCharacterAvatars(characterName);
            model.addAttribute("characterAvatars", characterAvatars);

            List<CharacterCombatSkill> characterCombatSkills = lostArkApiService.getCharacterCombatSkills(characterName);
            model.addAttribute("characterCombatSkills", characterCombatSkills);

            CharacterEngraving characterEngravings = lostArkApiService.getCharacterEngravings(characterName);
            model.addAttribute("characterEngravings", characterEngravings);

            CharacterCard characterCards = lostArkApiService.getCharacterCards(characterName);
            model.addAttribute("characterCards", characterCards);

            CharacterGem characterGems = lostArkApiService.getCharacterGems(characterName);
            model.addAttribute("characterGems", characterGems);

            List<CharacterCollectible> characterCollectibles = lostArkApiService.getCharacterCollectibles(characterName);
            model.addAttribute("characterCollectibles", characterCollectibles);

            CharacterArkPassive characterArkPassives = lostArkApiService.getCharacterArkPassives(characterName);
            model.addAttribute("characterArkPassives", characterArkPassives);

            model.addAttribute("characterName", characterName);

        } catch (IOException e) {
            log.error("캐릭터 정보를 가져오는 데 실패했습니다.", e);
            model.addAttribute("error", "캐릭터 정보를 가져오는 데 실패했습니다.");
        }
        return "character/character";
    }
}