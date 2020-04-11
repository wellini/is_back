package com.nonfallable.taskKnight.services;

import com.nonfallable.taskKnight.models.ConfirmationToken;
import com.nonfallable.taskKnight.models.ConfirmationTokenType;
import com.nonfallable.taskKnight.repositories.ConfirmationTokenRepository;
import com.nonfallable.taskKnight.rest.authentication.exceptions.BadConfirmationCodeException;
import com.nonfallable.taskKnight.rest.authentication.exceptions.ConfirmationTokenNotFoundException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Transactional
    public void confirm(UUID id, String code, String subject, ConfirmationTokenType type) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findAndLockById(id)
                .orElseThrow(() -> new ConfirmationTokenNotFoundException("Not found token " + id.toString()));
        if(!confirmationToken.getCode().equals(code)) throw new BadConfirmationCodeException();
        if(!confirmationToken.getSubject().equals(subject) || !confirmationToken.getType().equals(type))
            throw new ConfirmationTokenNotFoundException("Not found token " + id.toString());
        LocalDateTime expiredAt = confirmationToken.getCreatedAt().plus(confirmationToken.getType().getDuration());

        confirmationTokenRepository.delete(confirmationToken);
        if(LocalDateTime.now().isAfter(expiredAt)) throw new ConfirmationTokenNotFoundException("Not found token " + id.toString());
    }

    @Transactional
    public ConfirmationToken createToken(String subject, ConfirmationTokenType type) {
        return confirmationTokenRepository.save(
                new ConfirmationToken()
                        .setCode(generateCode())
                        .setSubject(subject)
                        .setType(type)
        );
    }

    private String generateCode() {
        return String.valueOf(new RandomDataGenerator().nextLong(100001L, 999999L));
    }
}
