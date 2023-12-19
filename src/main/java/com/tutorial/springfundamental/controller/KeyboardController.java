package com.tutorial.springfundamental.controller;

import com.tutorial.springfundamental.dto.KeyboardRecord;
import com.tutorial.springfundamental.entity.Keyboard;
import com.tutorial.springfundamental.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyboard")
@RequiredArgsConstructor
public class KeyboardController {

    private final KeyboardService keyboardService;

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Keyboard> getAllKeyboard() {
        return keyboardService.getAllKeyboard();
    }

    @GetMapping(value = "/path/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Keyboard getKeyboardByIdWithPathVariable(@PathVariable("id") String id) {
        return keyboardService.getKeyboardById(id);
    }

    @GetMapping(value = "/req")
    @ResponseStatus(HttpStatus.OK)
    public Keyboard getKeyboardByIdWithRequestParam(@RequestParam("id") String id) {
        return keyboardService.getKeyboardById(id);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Keyboard createKeyboard(@RequestBody KeyboardRecord request) {
        return keyboardService.saveKeyboard(request);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Keyboard updateKeyboard(@PathVariable("id") String id, @RequestBody KeyboardRecord request) {
        return keyboardService.updateKeyboard(id, request);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyboard(@PathVariable("id") String id) {
        keyboardService.deleteKeyboard(id);
    }
}
