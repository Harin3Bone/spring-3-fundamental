package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.entity.Keyboard;
import com.tutorial.springfundamental.repository.KeyboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyboardService {

    private final KeyboardRepository keyboardRepository;

    public List<Keyboard> getAllKeyboard() {
        return keyboardRepository.findAllBy();
    }

}
