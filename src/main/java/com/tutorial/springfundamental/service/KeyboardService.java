package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.dto.KeyboardRecord;
import com.tutorial.springfundamental.entity.Keyboard;
import com.tutorial.springfundamental.exception.NotFoundException;
import com.tutorial.springfundamental.repository.KeyboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeyboardService {

    private final KeyboardRepository keyboardRepository;

    public List<Keyboard> getAllKeyboard() {
        return keyboardRepository.findAllBy();
    }

    public Keyboard getKeyboardById(String id) {
        return keyboardRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Keyboard not found"));
    }

    public Keyboard saveKeyboard(KeyboardRecord request) {
        var keyboard = new Keyboard();
        keyboard.setName(request.name());
        keyboard.setQuantity(request.quantity());
        keyboard.setPrice(BigDecimal.valueOf(request.price()));

        keyboardRepository.save(keyboard);

        return keyboard;
    }

    public Keyboard updateKeyboard(String id, KeyboardRecord request) {
        var existingKeyboard = getKeyboardById(id);
        existingKeyboard.setName(request.name());
        existingKeyboard.setQuantity(request.quantity());
        existingKeyboard.setPrice(BigDecimal.valueOf(request.price()));
        keyboardRepository.save(existingKeyboard);

        return existingKeyboard;
    }

    public void deleteKeyboard(String id) {
        var existingKeyboard = getKeyboardById(id);
        keyboardRepository.delete(existingKeyboard);
    }

}
