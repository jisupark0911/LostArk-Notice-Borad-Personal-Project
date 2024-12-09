package com.example.LostArkNoticeBoard.service;

import com.example.LostArkNoticeBoard.Model.*;
import com.example.LostArkNoticeBoard.Model.Character;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
@Service
public class LostArkApiService {
    @Value("${lostark.api.url.base}")
    private String baseUrl;

    @Value("${lostark.api.token}")
    private String apiToken;

    public Notice[] getNotices() throws IOException {
        String url = baseUrl + "/news/notices";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 제이슨 notices 데이터: " + responseBody);
            return parseNotice(responseBody);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    public Event[] getEvents() throws IOException {
        String url = baseUrl + "/news/events";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 제이슨 event 데이터: " + responseBody);
            return parseEvent(responseBody);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    public Character[] getCharacters(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8"); //characterName 인코딩 안해주면 값 제대로 안들어 감
        //이 url주소가 api를 호출하는 url 인것이고 컨트롤러의 매핑주소는 그저 홈페이지의 주소를 나타내는것 뿐이다.
        String url = baseUrl + "/characters/" + encodedCharacterName + "/siblings";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 제이슨 데이터: " + responseBody);
            return parseCharacter(responseBody);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<CharacterProfile> getCharacterProfileAsync(String characterName) throws IOException { //단일객체 구조
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories/characters/" + encodedCharacterName + "/profiles";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 profile 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterProfile(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }


    @Async
    public CompletableFuture<CharacterEquipment[]> getCharacterEquipmentAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories" + "/characters/" + encodedCharacterName + "/equipment";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 equipment 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterEquipment(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }


    @Async
    public CompletableFuture<CharacterEngraving> getCharacterEngravingsAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories" + "/characters/" + encodedCharacterName + "/engravings";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 engraving 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterEngraving(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<CharacterCard> getCharacterCardsAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories" + "/characters/" + encodedCharacterName + "/cards";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 card 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterCard(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<CharacterGem> getCharacterGemsAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories" + "/characters/" + encodedCharacterName + "/gems";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 gem 데이터: " + responseBody);
            CharacterGem characterGem = parseCharacterGem(responseBody);


            List<CharacterGem.Gem> sortGems = characterGem.getSortedGems();
            characterGem.setGem(sortGems);

            return CompletableFuture.completedFuture(characterGem);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<CharacterArkPassive> getCharacterArkPassivesAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories/characters/" + encodedCharacterName + "/arkpassive";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 arkPassive 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterArkPassive(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<CharacterAvatar[]> getCharacterAvatarsAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories" + "/characters/" + encodedCharacterName + "/avatars";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 avatar 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterAvatar(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    @Async
    public CompletableFuture<List<CharacterCombatSkill>> getCharacterCombatSkillsAsync(String characterName) throws IOException { //베열 구조
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories/characters/" + encodedCharacterName + "/combat-skills";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 skill 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterCombatSkills(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }

    @Async
    public CompletableFuture<List<CharacterCollectible>> getCharacterCollectiblesAsync(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, "UTF-8");
        String url = baseUrl + "/armories/characters/" + encodedCharacterName + "/collectibles";
        log.info("Request URL: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 collect 데이터: " + responseBody);
            return CompletableFuture.completedFuture(parseCharacterCollectible(responseBody));
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }


    public Abyss[] getAbyss() throws IOException {
        String url = baseUrl + "/gamecontents/challenge-abyss-dungeons";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 abyss 데이터: " + responseBody);
            return parseAbyss(responseBody);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }

    public Guardian getGuardian() throws IOException { //단일구조 한 객체안에 배열 card와 구조같음
        String url = baseUrl + "/gamecontents/challenge-guardian-raids";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseBody = reader.lines().collect(Collectors.joining());
            reader.close();
            log.info("받은 guardian 데이터: " + responseBody);
            return parseGuardian(responseBody);
        } else {
            log.error("받기 실패: " + responseCode);
            return null;
        }
    }



    private Notice[] parseNotice(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, Notice[].class);
    }

    private Event[] parseEvent(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, Event[].class);
    }

    private Character[] parseCharacter(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, Character[].class);
    }

    public CharacterProfile parseCharacterProfile(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterProfile.class);
    }

    private CharacterEquipment[] parseCharacterEquipment(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterEquipment[].class);
    }


    private CharacterAvatar[] parseCharacterAvatar(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterAvatar[].class);
    }

    private List<CharacterCombatSkill> parseCharacterCombatSkills(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, new TypeReference<List<CharacterCombatSkill>>(){});
    }

    private CharacterEngraving parseCharacterEngraving(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterEngraving.class);
    }

    private CharacterCard parseCharacterCard(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterCard.class);
    }

    private CharacterGem parseCharacterGem(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterGem.class);
    }


    private List<CharacterCollectible> parseCharacterCollectible(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, new TypeReference<List<CharacterCollectible>>(){});
    }

    private CharacterArkPassive parseCharacterArkPassive(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, CharacterArkPassive.class);
    }

    private Abyss[] parseAbyss(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, Abyss[].class);
    }

    private Guardian parseGuardian(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, Guardian.class);
    }



}