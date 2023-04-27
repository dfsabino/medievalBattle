package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.exception.InvalidInputException;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.Battle;
import com.danieleForte.medievalBattle.repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class BattleService {

    @Autowired
    private BattleRepository repository;

    public Battle create( ) {

        Battle battle = new Battle();
        battle.setDateBattle(LocalDateTime.now( ));
        battle.setCodeShare( generateCode());
        return this.repository.save( battle );
    }

    public Battle update(Battle battle ) {
        if ( battle.getId( ) == null ) {
            throw new InvalidInputException( "There is no ID" );
        }
        return repository.save( battle );
    }
    public Battle findByCodeShare(String codeShare ) {
        return repository.getBattleByCodeShare( codeShare ).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Battle not found with codeShare: " + codeShare ) );
    }

    public Battle findById(Long id ) {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Character not found with ID: " + id ) );
    }

    private String generateCode()
    {
        long numberRandom = new Random().nextLong(1000000000);
        return intToString(numberRandom,10);
    }

    static String intToString(Long num, int digits) {
        StringBuffer s = new StringBuffer(digits);
        long zeroes = digits - (long) (Math.log(num) / Math.log(10)) - 1;
        for (long i = 0; i < zeroes; i++) {
            s.append(0);
        }
        return s.append(num).toString();
    }
}
