package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.dto.KeyboardRequest;
import com.tutorial.springfundamental.entity.Keyboard;
import com.tutorial.springfundamental.exception.InvalidException;
import com.tutorial.springfundamental.exception.NotFoundException;
import com.tutorial.springfundamental.repository.KeyboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.tutorial.springfundamental.constants.ErrorMessage.INVALID_SORT_BY;
import static com.tutorial.springfundamental.constants.ErrorMessage.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyboardService {

    private final KeyboardRepository keyboardRepository;

    public List<Keyboard> getAllKeyboard() {
        return keyboardRepository.findAll();
    }

    /*
     * @param
     * - page: page number, default 0
     * - size: size of record per page, default 10
     * - sortBy: sort by column name, default null
     * - sortDirection: sort direction, default null (asc)
     *
     */
    public List<Keyboard> getAllKeyboardWithPagination(int page, int size, String sortBy, String order) {
        Pageable pageable;

        // Setup default page and size value
        page = page <= 0 ? 1 : page - 1;
        size = size <= 0 ? 10 : size;

        if (StringUtils.isNotBlank(sortBy)) {
            // Setup order direction
            var orderBy = StringUtils.isBlank(order) ? Sort.Direction.ASC : Sort.Direction.valueOf(order.toUpperCase());

            // Validate sort by specific column
            if (!isSortByValid(sortBy)) {
                throw new InvalidException(INVALID_SORT_BY.formatted(sortBy));
            }

            pageable = PageRequest.of(page, size, orderBy, sortBy);
        } else {
            pageable = PageRequest.of(page, size);
        }

        var keyboardPagination = keyboardRepository.findAll(pageable);
        return keyboardPagination.getContent();
    }

    public Keyboard getKeyboardById(String id) {
        return keyboardRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException(NOT_FOUND.formatted("Keyboard")));
    }

    public Keyboard saveKeyboard(KeyboardRequest request) {
        var keyboard = new Keyboard();
        keyboard.setName(request.name());
        keyboard.setQuantity(request.quantity());
        keyboard.setPrice(BigDecimal.valueOf(request.price()));
        keyboard.setCategoryId(UUID.fromString(request.categoryId()));

        keyboardRepository.save(keyboard);

        return keyboard;
    }

    public Keyboard updateKeyboard(String id, KeyboardRequest request) {
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

    private boolean isSortByValid(String sortBy) {
        return switch (sortBy) {
            case "name", "price", "quantity" -> true;
            default -> false;
        };
    }

}
